package br.com.pibsantalucia.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.pibsantalucia.facade.ReconciliacaoFacade;
import br.com.pibsantalucia.util.BusinessException;
import br.com.pibsantalucia.util.Constantes;
import br.com.pibsantalucia.util.Util;
import br.com.pibsantalucia.vo.MensagemUsuarioVO;
import br.com.pibsantalucia.vo.ReconciliacaoVO;
import br.com.pibsantalucia.vo.UsuarioMembroVO;
import br.com.pibsantalucia.vo.ViewMembroVO;

@Namespace(value = "/CongregadoReconciliacao")
public class PessoaReconciliacaoAction extends BasicAction {

	private static final long serialVersionUID = -1030548192910041183L;
	private final Logger LOGGER = LoggerFactory.getLogger(PessoaReconciliacaoAction.class);

	@Autowired
	private ReconciliacaoFacade reconciliacaoFacade;

	private ReconciliacaoVO reconciliacaoVO;
	private List<ViewMembroVO> listaViewMembroVO;
	private MensagemUsuarioVO mensagemUsuarioVO;

	/**
	 * 
	 * @return
	 */
	@Action(value = "/Reconciliacao", results = { @Result(name = SUCCESS, location = "congregado.reconciliacao.tiles", type = "tiles"), @Result(name = ERROR, location = "error.tiles", type = "tiles"), @Result(name = LOGIN, location = "/login.jsp") })
	public String inicia() {
		return SUCCESS;
	}

	@Action(value = "/ReconciliacaoPesquisaNome", results = { @Result(name = SUCCESS, type = "json"), @Result(name = LOGIN, location = "/login.jsp") })
	public String pesquisaNome() {
		try {
//			setListaViewMembroVO(pessoaFacade.listaCongregados(null, getReconciliacaoVO().getViewMembroVO().getPesNome(), "N"));
			return SUCCESS;
		} catch (Exception exception) {
			LOGGER.error("Erro ao buscar lista de membros.", exception);
		}
		return SUCCESS;
	}

	@Action(value = "/SalvaReconciliacao", results = { @Result(name = SUCCESS, type = "json"), @Result(name = LOGIN, location = "/login.jsp") })
	public String salva() {
		try {
			// valida campos
			validaCamposSalvar();

			getReconciliacaoVO().setUsuarioMembroVO((UsuarioMembroVO) this.getRequest().getSession().getAttribute(Constantes.KEY_USUARIO_SESSION));

			reconciliacaoFacade.salvar(getReconciliacaoVO());
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

//		if (!Util.isCampoPreenchido(getReconciliacaoVO().getViewMembroVO().getPesNome())) {
//			getMensagemUsuarioVO().getMensagemErro().add("<li>" + getText("ERRO.CADASTRO.NOME.OBRIGATORIO") + "</li>");
//		}

		if (!Util.isCampoPreenchido(getReconciliacaoVO().getRecDtReconciliacao())) {
			getMensagemUsuarioVO().getMensagemErro().add("<li>" + getText("ERRO.RECONCILIACAO.DATA.OBRIGATORIO") + "</li>");
		} else if (!Util.isDataValida(getReconciliacaoVO().getRecDtReconciliacao())) {
			getMensagemUsuarioVO().getMensagemErro().add("<li>" + getText("ERRO.RECONCILIACAO.DATA.INVALIDA") + "</li>");
		} else if (!Util.isDataMaiorQueAtual(getReconciliacaoVO().getRecDtReconciliacao())) {
			getMensagemUsuarioVO().getMensagemErro().add("<li>" + getText("ERRO.RECONCILIACAO.DATA.MAIOR.IGUAL.ATUAL") + "</li>");
		}

		if (getMensagemUsuarioVO().getMensagemErro().size() > 0) {
			throw new BusinessException("");
		}
	}

	public ReconciliacaoVO getReconciliacaoVO() {
		return reconciliacaoVO;
	}

	public void setReconciliacaoVO(ReconciliacaoVO reconciliacaoVO) {
		this.reconciliacaoVO = reconciliacaoVO;
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
