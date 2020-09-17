package br.com.pibsantalucia.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.pibsantalucia.facade.ObitoFacade;
import br.com.pibsantalucia.util.BusinessException;
import br.com.pibsantalucia.util.Constantes;
import br.com.pibsantalucia.util.Util;
import br.com.pibsantalucia.vo.MensagemUsuarioVO;
import br.com.pibsantalucia.vo.ObitoVO;
import br.com.pibsantalucia.vo.UsuarioMembroVO;
import br.com.pibsantalucia.vo.ViewMembroVO;

@Namespace(value = "/CongregadoObito")
public class PessoaObitoAction extends BasicAction {

	private static final long serialVersionUID = 1729552396309009081L;
	private final Logger LOGGER = LoggerFactory.getLogger(PessoaObitoAction.class);

	@Autowired
	private ObitoFacade obitoFacade;

	private ObitoVO obitoVO;
	private List<ViewMembroVO> listaViewMembroVO;
	private MensagemUsuarioVO mensagemUsuarioVO;

	/**
	 * 
	 * @return
	 */
	@Action(value = "/Obito", results = { @Result(name = SUCCESS, location = "congregado.obito.tiles", type = "tiles"), @Result(name = ERROR, location = "error.tiles", type = "tiles"), @Result(name = LOGIN, location = "/login.jsp") })
	public String inicia() {
		return SUCCESS;
	}

	@Action(value = "/SalvaObito", results = { @Result(name = SUCCESS, type = "json"), @Result(name = LOGIN, location = "/login.jsp") })
	public String salva() {
		try {
			// valida campos
			validaCamposSalvar();

			getObitoVO().setUsuarioMembroVO((UsuarioMembroVO) this.getRequest().getSession().getAttribute(Constantes.KEY_USUARIO_SESSION));

			obitoFacade.salvar(getObitoVO());
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

		if (!Util.isCampoPreenchido(getObitoVO().getObiDtObito())) {
			getMensagemUsuarioVO().getMensagemErro().add("<li>" + getText("ERRO.OBITO.DATA.OBRIGATORIO") + "</li>");
		} else if (!Util.isDataValida(getObitoVO().getObiDtObito())) {
			getMensagemUsuarioVO().getMensagemErro().add("<li>" + getText("ERRO.OBITO.DATA.INVALIDA") + "</li>");
		} else if (!Util.isDataMaiorQueAtual(getObitoVO().getObiDtObito())) {
			getMensagemUsuarioVO().getMensagemErro().add("<li>" + getText("ERRO.OBITO.DATA.MAIOR.IGUAL.ATUAL") + "</li>");
		}

		if (!Util.isCampoPreenchido(getObitoVO().getObiCausa())) {
			getMensagemUsuarioVO().getMensagemErro().add("<li>" + getText("ERRO.OBITO.CAUSA.OBRIGATORIO") + "</li>");
		}

		if (getMensagemUsuarioVO().getMensagemErro().size() > 0) {
			throw new BusinessException("");
		}
	}

	public ObitoVO getObitoVO() {
		return obitoVO;
	}

	public void setObitoVO(ObitoVO obitoVO) {
		this.obitoVO = obitoVO;
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
