package br.com.pibsantalucia.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.pibsantalucia.facade.PastoralFacade;
import br.com.pibsantalucia.util.BusinessException;
import br.com.pibsantalucia.util.Constantes;
import br.com.pibsantalucia.util.Util;
import br.com.pibsantalucia.vo.MensagemUsuarioVO;
import br.com.pibsantalucia.vo.PastoralVO;
import br.com.pibsantalucia.vo.UsuarioMembroVO;

@InterceptorRef(value = Constantes.STACK_PIBSANTALUCIA)
@Namespace(value = "/Pastoral")
public class PastoralAction extends BasicAction {

	private static final long serialVersionUID = 2439115561571786015L;
	
	private final Logger LOGGER = LoggerFactory.getLogger(PastoralAction.class);
	
	@Autowired
	private PastoralFacade pastoralFacade;
	
	private MensagemUsuarioVO mensagemUsuarioVO;
	private PastoralVO pastoralVO;
	private List<PastoralVO> listaPastoralVO;
	
	@Action(value = "/CadastroPastoral", results = { @Result(name = SUCCESS, location = "pastoral.cadastro.tiles", type = "tiles") })
	public String home() {
		return SUCCESS;
	}
	
	@Action(value = "/ListarPastoral", results = { @Result(name = SUCCESS, location = "pastoral.pesquisa.tiles", type = "tiles"), @Result(name = LOGIN, location = "/login.jsp") })
	public String listar() {
		try {
			setListaPastoralVO(pastoralFacade.lista());
		} catch (Exception exception) {
			LOGGER.error("Erro ao listar pastoral.", exception);
			mensagemUsuarioVO = new MensagemUsuarioVO();
			mensagemUsuarioVO.getMensagemErro().add(getText("ERRO.SISTEMA.INDISPONIVEL"));
		}
		return SUCCESS;
	}
	
	@Action(value = "/AlteraPastoral", results = { @Result(name = SUCCESS, location = "pastoral.cadastro.tiles", type = "tiles"), @Result(name = ERROR, location = "error.tiles", type = "tiles"), @Result(name = LOGIN, location = "/login.jsp") })
	public String exibirManutencaoAlteracao() {
		try {
			setPastoralVO(pastoralFacade.obterPorId(getPastoralVO().getPasCodigo()));
			return SUCCESS;
		} catch (Exception exception) {
			LOGGER.error("Erro ao exibir alteração da pastoral", exception);
			return ERROR;
		}
	}
	
	@Action(value = "/SalvaPastoral", results = { @Result(name = SUCCESS, type = "json"), @Result(name = LOGIN, location = "/login.jsp") })
	public String salvar() {
		try {
			
			LOGGER.debug(pastoralVO.getPasDtReferencia());

			// valida campos
			validaCamposSalvar();
			
			getPastoralVO().setUsuarioMembroVO((UsuarioMembroVO) this.getRequest().getSession().getAttribute(Constantes.KEY_USUARIO_SESSION));
			
			pastoralFacade.salvar(pastoralVO);

			if (getPastoralVO().getPasCodigo() == 0) {
				mensagemUsuarioVO.setMensagem(getText("MSG.CADASTRO.SUCESSO"));
			} else {
				mensagemUsuarioVO.setMensagem(getText("MSG.ATUALIZACAO.SUCESSO"));
			}
		} catch (Exception exception) {
			LOGGER.error("Erro ao salvar pastoral.", exception);
			if (exception instanceof BusinessException) {
				getMensagemUsuarioVO().getMensagemErro().add(getText(exception.getMessage()));
			} else {
				getMensagemUsuarioVO().getMensagemErro().add(getText("ERRO.SISTEMA.INDISPONIVEL"));
			}
		}
		return SUCCESS;
	} 
	
	private void validaCamposSalvar() throws BusinessException {
		setMensagemUsuarioVO(new MensagemUsuarioVO());
		
		if (!Util.isCampoPreenchido(getPastoralVO().getPasDtReferencia())) {
			getMensagemUsuarioVO().getMensagemErro().add("<li>" + getText("ERRO.CADASTRO.DATA.REFERENCIA.OBRIGATORIO") + "</li>");
		} else if (!Util.isDataValida("01/"+getPastoralVO().getPasDtReferencia())) {
			getMensagemUsuarioVO().getMensagemErro().add("<li>" + getText("ERRO.CADASTRO.DATA.REFERENCIA.INVALIDA") + "</li>");
		}
		
		if (!Util.isCampoPreenchido(getPastoralVO().getPasPastoral())) {
			getMensagemUsuarioVO().getMensagemErro().add("<li>" + getText("ERRO.CADASTRO.PASTORAL.OBRIGATORIO") + "</li>");
		}

		if (getMensagemUsuarioVO().getMensagemErro().size() > 0) {
			throw new BusinessException("");
		}
	}

	public MensagemUsuarioVO getMensagemUsuarioVO() {
		return mensagemUsuarioVO;
	}

	public void setMensagemUsuarioVO(MensagemUsuarioVO mensagemUsuarioVO) {
		this.mensagemUsuarioVO = mensagemUsuarioVO;
	}

	public PastoralVO getPastoralVO() {
		return pastoralVO;
	}

	public void setPastoralVO(PastoralVO pastoralVO) {
		this.pastoralVO = pastoralVO;
	}

	public List<PastoralVO> getListaPastoralVO() {
		return listaPastoralVO;
	}

	public void setListaPastoralVO(List<PastoralVO> listaPastoralVO) {
		this.listaPastoralVO = listaPastoralVO;
	}
}
