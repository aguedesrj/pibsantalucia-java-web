package br.com.pibsantalucia.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.pibsantalucia.facade.DesligamentoFacade;
import br.com.pibsantalucia.util.BusinessException;
import br.com.pibsantalucia.util.Constantes;
import br.com.pibsantalucia.util.Util;
import br.com.pibsantalucia.vo.DesligamentoVO;
import br.com.pibsantalucia.vo.MensagemUsuarioVO;
import br.com.pibsantalucia.vo.UsuarioMembroVO;
import br.com.pibsantalucia.vo.ViewMembroVO;

@Namespace(value = "/CongregadoDesligamento")
public class PessoaDesligamentoAction extends BasicAction {

	private static final long serialVersionUID = 1138109722379030255L;
	private final Logger LOGGER = LoggerFactory.getLogger(PessoaDesligamentoAction.class);

	@Autowired
	private DesligamentoFacade desligamentoFacade;

	private DesligamentoVO desligamentoVO;
	private List<ViewMembroVO> listaViewMembroVO;
	private MensagemUsuarioVO mensagemUsuarioVO;

	/**
	 * 
	 * @return
	 */
	@Action(value = "/Desligamento", results = { @Result(name = SUCCESS, location = "congregado.desligamento.tiles", type = "tiles"), @Result(name = ERROR, location = "error.tiles", type = "tiles"), @Result(name = LOGIN, location = "/login.jsp") })
	public String inicia() {
		return SUCCESS;
	}

	@Action(value = "/DesligamentoPesquisaNome", results = { @Result(name = SUCCESS, type = "json"), @Result(name = LOGIN, location = "/login.jsp") })
	public String pesquisaNome() {
		try {
//			setListaViewMembroVO(pessoaFacade.listaCongregados(null, getDesligamentoVO().getViewMembroVO().getPesNome(), "S"));
			return SUCCESS;
		} catch (Exception exception) {
			LOGGER.error("Erro ao buscar lista de nomes de membros.", exception);
		}
		return SUCCESS;
	}

	@Action(value = "/SalvaDesligamento", results = { @Result(name = SUCCESS, type = "json"), @Result(name = LOGIN, location = "/login.jsp") })
	public String salva() {
		try {
			// valida campos
			validaCamposSalvar();

			getDesligamentoVO().setUsuarioMembroVO((UsuarioMembroVO) this.getRequest().getSession().getAttribute(Constantes.KEY_USUARIO_SESSION));

			desligamentoFacade.salvar(getDesligamentoVO());
			mensagemUsuarioVO.setMensagem(getText("MSG.CADASTRO.SUCESSO"));

			return SUCCESS;
		} catch (Exception exception) {
			LOGGER.error("Erro ao salvar reconciliação.", exception);
			if (!(exception instanceof BusinessException)) {
				setMensagemUsuarioVO(new MensagemUsuarioVO());
				getMensagemUsuarioVO().getMensagemErro().add(getText("ERRO.SISTEMA.INDISPONIVEL"));
			}
		}
		return SUCCESS;
	}

	private void validaCamposSalvar() throws BusinessException {
		setMensagemUsuarioVO(new MensagemUsuarioVO());

//		if (!Util.isCampoPreenchido(getDesligamentoVO().getViewMembroVO().getPesNome())) {
//			getMensagemUsuarioVO().getMensagemErro().add("<li>" + getText("ERRO.CADASTRO.NOME.OBRIGATORIO") + "</li>");
//		}

		if (!Util.isCampoPreenchido(getDesligamentoVO().getDesDtDesligamento())) {
			getMensagemUsuarioVO().getMensagemErro().add("<li>" + getText("ERRO.DESLIGAMENTO.DATA.OBRIGATORIO") + "</li>");
		} else if (!Util.isDataValida(getDesligamentoVO().getDesDtDesligamento())) {
			getMensagemUsuarioVO().getMensagemErro().add("<li>" + getText("ERRO.DESLIGAMENTO.DATA.INVALIDA") + "</li>");
		} else if (!Util.isDataMaiorQueAtual(getDesligamentoVO().getDesDtDesligamento())) {
			getMensagemUsuarioVO().getMensagemErro().add("<li>" + getText("ERRO.DESLIGAMENTO.DATA.MAIOR.IGUAL.ATUAL") + "</li>");
		}

		if (getMensagemUsuarioVO().getMensagemErro().size() > 0) {
			throw new BusinessException("");
		}
	}

	public DesligamentoVO getDesligamentoVO() {
		return desligamentoVO;
	}

	public void setDesligamentoVO(DesligamentoVO desligamentoVO) {
		this.desligamentoVO = desligamentoVO;
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
