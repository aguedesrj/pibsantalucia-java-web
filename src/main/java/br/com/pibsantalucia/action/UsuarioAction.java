package br.com.pibsantalucia.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.pibsantalucia.api.vo.Login;
import br.com.pibsantalucia.facade.PessoaFacade;
import br.com.pibsantalucia.facade.UsuarioMembroFacade;
import br.com.pibsantalucia.util.BusinessException;
import br.com.pibsantalucia.util.Constantes;
import br.com.pibsantalucia.vo.ErroVO;
import br.com.pibsantalucia.vo.MensagemUsuarioVO;
import br.com.pibsantalucia.vo.QuantidadeMembroVO;
import br.com.pibsantalucia.vo.UsuarioMembroVO;

@InterceptorRef(value = Constantes.STACK_PIBSANTALUCIA)
@Namespace(value = "/Usuario")
public class UsuarioAction extends BasicAction {

	private static final long serialVersionUID = -8441111073820612396L;

	private final Logger LOGGER = LoggerFactory.getLogger(UsuarioAction.class);

	@Autowired
	private PessoaFacade pessoaFacade;
	
	@Autowired
	private UsuarioMembroFacade usuarioMembroFacade;

	private MensagemUsuarioVO mensagemUsuarioVO;
	private Login login;
	private ErroVO erroVO;
	private QuantidadeMembroVO quantidadeMembroVO;

	@Action(value = "/IniciaLogin", results = { @Result(name = SUCCESS, location = "/login.jsp") })
	public String iniciaLogin() {
		return SUCCESS;
	}

	@Action(value = "/EfetuaLogin", results = { @Result(name = SUCCESS, location = "home.tiles", type = "tiles"), @Result(name = ERROR, location = "/login.jsp") })
	public String efetuaLogin() {
		try {
			mensagemUsuarioVO = new MensagemUsuarioVO();
			// valida campos
			validaCamposEfetuaLogin();
			if (mensagemUsuarioVO.getMensagemErro().size() > 0) {
				return ERROR;
			}
			// efetua login.
			UsuarioMembroVO usuarioMembroVO = usuarioMembroFacade.login(getLogin().getCpfOrEmail().trim(), 
					getLogin().getPassword().trim());
			
			if (usuarioMembroVO == null) {
				addActionError(getText("ERRO.LOGIN.SENHA.INVALIDOS"));
				return ERROR;
			}
			
			if (usuarioMembroVO.getPerCodigo() != 2) {
				addActionError(getText("ERRO.USUARIO.SEM.PERMISSAO"));
				return ERROR;
			}
			
			// colocar na sessao os dados do usuario.
			this.getRequest().getSession().setAttribute(Constantes.KEY_USUARIO_SESSION, usuarioMembroVO);
			return SUCCESS;
		} catch (Exception exception) {
			LOGGER.error("Erro ao efetuar login.", exception);
			erroVO = new ErroVO();
			if (exception instanceof BusinessException) {
				addActionError(getText("ERRO.LOGIN.SENHA.INVALIDOS"));
			} else {
				addActionError(getText("ERRO.SISTEMA.INDISPONIVEL"));
			}
			return ERROR;
		}
	}

	@Action(value = "/Home", results = { @Result(name = SUCCESS, location = "home.tiles", type = "tiles") })
	public String home() {
		return SUCCESS;
	}

	@Action(value = "/QuantidadeMembros", results = { @Result(name = SUCCESS, type = "json"), @Result(name = LOGIN, location = "/login.jsp") })
	public String quantidadeMembros() {
		try {
			int membroFeminino = pessoaFacade.totalMembroPorSexo("F");
			int membroMasculino = pessoaFacade.totalMembroPorSexo("M");
			int totalMembros = membroFeminino + membroMasculino;
			setQuantidadeMembroVO(new QuantidadeMembroVO());
			if (totalMembros > 0) {
				getQuantidadeMembroVO().setPercentualFemninino((membroFeminino * 100) / totalMembros);
				getQuantidadeMembroVO().setPercentualMasculino((membroMasculino * 100) / totalMembros);
				getQuantidadeMembroVO().setQuantidadeMembros(membroFeminino + membroMasculino);
			} else {
				getQuantidadeMembroVO().setPercentualFemninino(0);
				getQuantidadeMembroVO().setPercentualMasculino(0);
				getQuantidadeMembroVO().setQuantidadeMembros(0);
			}
		} catch (Exception exception) {
			LOGGER.error("Erro ao obter a quantidade de membros.", exception);
			setErroVO(new ErroVO(getText("ERRO.SISTEMA.INDISPONIVEL")));
		}
		return SUCCESS;
	}

	@Action(value = "/Logout", results = { @Result(name = SUCCESS, location = "/login.jsp") })
	public String logout() {

		this.getRequest().getSession().removeAttribute(Constantes.KEY_USUARIO_SESSION);
		this.getRequest().getSession().removeAttribute(Constantes.KEY_LISTA_CONTATOS_SESSION);

		return SUCCESS;
	}

	private void validaCamposEfetuaLogin() {
		if (getLogin().getCpfOrEmail() == null || getLogin().getCpfOrEmail().trim().equals("")) {
			getMensagemUsuarioVO().getMensagemErro().add(getText("ERRO.LOGIN.OBRIGATORIO"));
		} else if (getLogin().getCpfOrEmail().length() <= 5) {
			getMensagemUsuarioVO().getMensagemErro().add(getText("ERRO.LOGIN.INVALIDO"));
		}
		// verifica se já tem alguma mensagem de erro.
		if (getMensagemUsuarioVO().getMensagemErro().size() == 0) {
			if (getLogin().getPassword() == null || getLogin().getPassword().trim().equals("")) {
				getMensagemUsuarioVO().getMensagemErro().add(getText("ERRO.SENHA.OBRIGATORIA"));
			} else if (getLogin().getPassword().length() <= 5) {
				getMensagemUsuarioVO().getMensagemErro().add(getText("ERRO.SENHA.INVALIDA"));
			}
		}
	}

	public MensagemUsuarioVO getMensagemUsuarioVO() {
		return mensagemUsuarioVO;
	}

	public void setMensagemUsuarioVO(MensagemUsuarioVO mensagemUsuarioVO) {
		this.mensagemUsuarioVO = mensagemUsuarioVO;
	}

	public ErroVO getErroVO() {
		return erroVO;
	}

	public void setErroVO(ErroVO erroVO) {
		this.erroVO = erroVO;
	}

	public QuantidadeMembroVO getQuantidadeMembroVO() {
		return quantidadeMembroVO;
	}

	public void setQuantidadeMembroVO(QuantidadeMembroVO quantidadeMembroVO) {
		this.quantidadeMembroVO = quantidadeMembroVO;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
}
