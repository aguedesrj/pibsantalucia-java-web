package br.com.pibsantalucia.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.pibsantalucia.facade.TransferenciaFacade;
import br.com.pibsantalucia.util.BusinessException;
import br.com.pibsantalucia.util.Constantes;
import br.com.pibsantalucia.util.Util;
import br.com.pibsantalucia.vo.MensagemUsuarioVO;
import br.com.pibsantalucia.vo.TransferenciaVO;
import br.com.pibsantalucia.vo.UsuarioMembroVO;
import br.com.pibsantalucia.vo.ViewMembroVO;

@Namespace(value = "/CongregadoTransferencia")
public class PessoaTransferenciaAction extends BasicAction {

	private static final long serialVersionUID = -3619237873784920273L;
	private final Logger LOGGER = LoggerFactory.getLogger(PessoaTransferenciaAction.class);

	@Autowired
	private TransferenciaFacade transferenciaFacade;

	private TransferenciaVO transferenciaVO;
	private List<ViewMembroVO> listaViewMembroVO;
	private MensagemUsuarioVO mensagemUsuarioVO;

	/**
	 * 
	 * @return
	 */
	@Action(value = "/Transferencia", results = { @Result(name = SUCCESS, location = "congregado.transferencia.tiles", type = "tiles"), @Result(name = ERROR, location = "error.tiles", type = "tiles"), @Result(name = LOGIN, location = "/login.jsp") })
	public String inicia() {
		return SUCCESS;
	}

	@Action(value = "/TransferenciaPesquisaNome", results = { @Result(name = SUCCESS, type = "json"), @Result(name = LOGIN, location = "/login.jsp") })
	public String pesquisaNome() {
		try {
//			setListaViewMembroVO(pessoaFacade.listaCongregados(null, getTransferenciaVO().getViewMembroVO().getPesNome(), null));
			return SUCCESS;
		} catch (Exception exception) {
			LOGGER.error("Erro ao buscar lista de nomes de membros.", exception);
		}
		return SUCCESS;
	}

	@Action(value = "/SalvaTransferencia", results = { @Result(name = SUCCESS, type = "json"), @Result(name = LOGIN, location = "/login.jsp") })
	public String salva() {
		try {
			// valida campos
			validaCamposSalvar();

			getTransferenciaVO().setUsuarioMembroVO((UsuarioMembroVO) this.getRequest().getSession().getAttribute(Constantes.KEY_USUARIO_SESSION));

			transferenciaFacade.salvar(getTransferenciaVO());
			mensagemUsuarioVO.setMensagem(getText("MSG.CADASTRO.SUCESSO"));

			return SUCCESS;
		} catch (Exception exception) {
			LOGGER.error("Erro ao salvar transferência.", exception);
			if (!(exception instanceof BusinessException)) {
				setMensagemUsuarioVO(new MensagemUsuarioVO());
				getMensagemUsuarioVO().getMensagemErro().add(getText("ERRO.SISTEMA.INDISPONIVEL"));
			}
		}
		return SUCCESS;
	}

	private void validaCamposSalvar() throws BusinessException {
		setMensagemUsuarioVO(new MensagemUsuarioVO());

//		if (!Util.isCampoPreenchido(getTransferenciaVO().getViewMembroVO().getPesNome())) {
//			getMensagemUsuarioVO().getMensagemErro().add("<li>" + getText("ERRO.CADASTRO.NOME.OBRIGATORIO") + "</li>");
//		}

		if (!Util.isCampoPreenchido(getTransferenciaVO().getTraDtTransferencia())) {
			getMensagemUsuarioVO().getMensagemErro().add("<li>" + getText("ERRO.TRANSFERENCIA.DATA.OBRIGATORIO") + "</li>");
		} else if (!Util.isDataValida(getTransferenciaVO().getTraDtTransferencia())) {
			getMensagemUsuarioVO().getMensagemErro().add("<li>" + getText("ERRO.TRANSFERENCIA.DATA.INVALIDA") + "</li>");
		} else if (!Util.isDataMaiorQueAtual(getTransferenciaVO().getTraDtTransferencia())) {
			getMensagemUsuarioVO().getMensagemErro().add("<li>" + getText("ERRO.TRANSFERENCIA.DATA.MAIOR.IGUAL.ATUAL") + "</li>");
		}

		if (!Util.isCampoPreenchido(getTransferenciaVO().getTraMotivo())) {
			getMensagemUsuarioVO().getMensagemErro().add("<li>" + getText("ERRO.TRANSFERENCIA.MOTIVO.OBRIGATORIO") + "</li>");
		}

		if (!Util.isCampoPreenchido(getTransferenciaVO().getTraStatus()) && (getTransferenciaVO().getTraStatus().equals("E") || getTransferenciaVO().getTraStatus().equals("S"))) {
			getMensagemUsuarioVO().getMensagemErro().add("<li>" + getText("ERRO.TRANSFERENCIA.STATUS.OBRIGATORIO") + "</li>");
		}

		if (!Util.isCampoPreenchido(getTransferenciaVO().getTraNomeIgreja())) {
			getMensagemUsuarioVO().getMensagemErro().add("<li>" + getText("ERRO.TRANSFERENCIA.NOME.IGREJA.OBRIGATORIO") + "</li>");
		}

		if (getMensagemUsuarioVO().getMensagemErro().size() > 0) {
			throw new BusinessException("");
		}
	}

	public TransferenciaVO getTransferenciaVO() {
		return transferenciaVO;
	}

	public void setTransferenciaVO(TransferenciaVO transferenciaVO) {
		this.transferenciaVO = transferenciaVO;
	}

	public MensagemUsuarioVO getMensagemUsuarioVO() {
		return mensagemUsuarioVO;
	}

	public void setMensagemUsuarioVO(MensagemUsuarioVO mensagemUsuarioVO) {
		this.mensagemUsuarioVO = mensagemUsuarioVO;
	}

	public List<ViewMembroVO> getListaViewMembroVO() {
		return listaViewMembroVO;
	}

	public void setListaViewMembroVO(List<ViewMembroVO> listaViewMembroVO) {
		this.listaViewMembroVO = listaViewMembroVO;
	}
}
