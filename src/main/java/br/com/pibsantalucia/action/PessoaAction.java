package br.com.pibsantalucia.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.pibsantalucia.facade.PessoaFacade;
import br.com.pibsantalucia.util.BusinessException;
import br.com.pibsantalucia.util.Constantes;
import br.com.pibsantalucia.util.Util;
import br.com.pibsantalucia.vo.ContatoVO;
import br.com.pibsantalucia.vo.MensagemUsuarioVO;
import br.com.pibsantalucia.vo.PessoaVO;
import br.com.pibsantalucia.vo.TipoContatoVO;
import br.com.pibsantalucia.vo.UsuarioMembroVO;
import br.com.pibsantalucia.vo.ViaCepVO;
import br.com.pibsantalucia.vo.ViewMembroVO;

@InterceptorRef(value = Constantes.STACK_PIBSANTALUCIA)
@Namespace(value = "/Congregado")
public class PessoaAction extends BasicAction {

	private final Logger LOGGER = LoggerFactory.getLogger(PessoaAction.class);
	private static final long serialVersionUID = -6224734807749747733L;

	@Autowired
	private PessoaFacade pessoaFacade;

	private PessoaVO pessoaVO;
	private List<TipoContatoVO> listaTipoContatoVO;
	private List<ContatoVO> listaContatoVO;
	private List<ViewMembroVO> listaViewMembroVO;
	private ViaCepVO viaCepVO;
	private MensagemUsuarioVO mensagemUsuarioVO;
	private ContatoVO contatoVO;

	/**
	 * 
	 * @return
	 */
	@Action(value = "/CadastroCongregado", results = { @Result(name = SUCCESS, location = "congregado.manutencao.tiles", type = "tiles"), @Result(name = ERROR, location = "error.tiles", type = "tiles"), @Result(name = LOGIN, location = "/login.jsp") })
	public String cadastro() {
		try {
			setPessoaVO(new PessoaVO());
			getPessoaVO().setPesFotoPath("../resources/photo/congregados/default.png");

			this.getRequest().getSession().removeAttribute(Constantes.KEY_LISTA_CONTATOS_SESSION);

			setListaTipoContatoVO(pessoaFacade.listaTipoContatos());
			return SUCCESS;
		} catch (Exception exception) {
			LOGGER.error("Erro ao buscar lista de tipo de contatos.", exception);
		}
		return SUCCESS;
	}

	@Action(value = "/AlteraCongregado", results = { @Result(name = SUCCESS, location = "congregado.manutencao.tiles", type = "tiles"), @Result(name = ERROR, location = "error.tiles", type = "tiles"), @Result(name = LOGIN, location = "/login.jsp") })
	public String exibirManutencaoAlteracao() {
		try {
			setPessoaVO(pessoaFacade.obterCongregado(getPessoaVO().getPesCodigo()));

			setListaTipoContatoVO(pessoaFacade.listaTipoContatos());

			this.getRequest().getSession().setAttribute(Constantes.KEY_LISTA_CONTATOS_SESSION, getPessoaVO().getListaContatoVO());

			return SUCCESS;
		} catch (Exception exception) {
			LOGGER.error("Erro ao exibir manutenção de alteração", exception);
			return ERROR;
		}
	}

	/**
	 * 
	 * @return
	 */
	@Action(value = "/BuscarCep", results = { @Result(name = SUCCESS, type = "json"), @Result(name = LOGIN, location = "/login.jsp") })
	public String buscarCep() {
		try {
			setViaCepVO(pessoaFacade.buscarCep(getPessoaVO().getEnderecoVO().getEndCep()));
		} catch (Exception exception) {
			setMensagemUsuarioVO(new MensagemUsuarioVO());
			getMensagemUsuarioVO().getMensagemErro().add(getText("ERRO.BUSCANDO.CEP"));
			LOGGER.error(getText("ERRO.BUSCANDO.CEP"), exception);
		}
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@Action(value = "/SalvaCongregado", results = { @Result(name = SUCCESS, type = "json"), @Result(name = LOGIN, location = "/login.jsp") })
	public String salvar() {
		try {
			// obtém os contatos...
			setListaContatoVO((ArrayList<ContatoVO>) this.getRequest().getSession().getAttribute(Constantes.KEY_LISTA_CONTATOS_SESSION));
			getPessoaVO().setListaContatoVO(getListaContatoVO());
			getPessoaVO().setUsuarioMembroVO((UsuarioMembroVO) this.getRequest().getSession().getAttribute(Constantes.KEY_USUARIO_SESSION));
			
			// valida campos
			validaCamposSalvar();
			
			// validações
			pessoaFacade.validarMembro(getPessoaVO());

			long pesCodigo = getPessoaVO().getPesCodigo();
			getPessoaVO().setPesCodigo(pessoaFacade.salvar(getPessoaVO()));
			if (pesCodigo == 0) {
				mensagemUsuarioVO.setMensagem(getText("MSG.CADASTRO.SUCESSO"));
			} else {
				mensagemUsuarioVO.setMensagem(getText("MSG.ATUALIZACAO.SUCESSO"));
			}
		} catch (Exception exception) {
			LOGGER.error("Erro ao salvar membro.", exception);
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

		if (!Util.isCampoPreenchido(getPessoaVO().getPesNome())) {
			getMensagemUsuarioVO().getMensagemErro().add("<li>" + getText("ERRO.CADASTRO.NOME.OBRIGATORIO") + "</li>");
		} else if (getPessoaVO().getPesNome().length() <= 5) {
			getMensagemUsuarioVO().getMensagemErro().add("<li>" + getText("ERRO.CADASTRO.NOME.INVALIDO") + "</li>");
		}

		if (!Util.isCampoPreenchido(getPessoaVO().getPesDtNascimento())) {
			getMensagemUsuarioVO().getMensagemErro().add("<li>" + getText("ERRO.CADASTRO.DATA.NASCIMENTO.OBRIGATORIO") + "</li>");
		} else if (!Util.isDataValida(getPessoaVO().getPesDtNascimento())) {
			getMensagemUsuarioVO().getMensagemErro().add("<li>" + getText("ERRO.CADASTRO.DATA.NASCIMENTO.INVALIDA") + "</li>");
		} else if (!Util.isDataMaiorQueAtual(getPessoaVO().getPesDtNascimento())) {
			getMensagemUsuarioVO().getMensagemErro().add("<li>" + getText("ERRO.CADASTRO.DATA.NASCIMENTO.MAIOR.IGUAL.ATUAL") + "</li>");
		}

		if (!Util.isCampoPreenchido(getPessoaVO().getPesSexo())) {
			getMensagemUsuarioVO().getMensagemErro().add("<li>" + getText("ERRO.CADASTRO.SEXO.OBRIGATORIO") + "</li>");
		}

		//		if (!Util.isCampoPreenchido(getPessoaVO().getPesCpf())) {
		//			getMensagemUsuarioVO().getMensagemErro().add("<li>"+getText("ERRO.CADASTRO.CPF.OBRIGATORIO")+"</li>");
		//		} else if (!Util.isCpfValido(getPessoaVO().getPesCpf().trim())) {
		//			getMensagemUsuarioVO().getMensagemErro().add("<li>"+getText("ERRO.CADASTRO.CPF.INVALIDO")+"</li>");
		//		}

		if (Util.isCampoPreenchido(getPessoaVO().getPesDtCasamento())) {
			if (!Util.isDataValida(getPessoaVO().getPesDtCasamento())) {
				getMensagemUsuarioVO().getMensagemErro().add("<li>" + getText("ERRO.CADASTRO.DATA.CASAMENTO.INVALIDA") + "</li>");
			} else if (!Util.isDataMaiorQueAtual(getPessoaVO().getPesDtCasamento())) {
				getMensagemUsuarioVO().getMensagemErro().add("<li>" + getText("ERRO.CADASTRO.DATA.CASAMENTO.MAIOR.IGUAL.ATUAL") + "</li>");
			}
		}

		if (!Util.isCampoPreenchido(getPessoaVO().getPesFglMembro())) {
			getMensagemUsuarioVO().getMensagemErro().add("<li>" + getText("ERRO.CADASTRO.MEMBRO.IGREJA.OBRIGATORIO") + "</li>");
		}
		//		else if (getPessoaVO().getPesFglMembro().equals("S")) {
		//			if (!Util.isCampoPreenchido(getPessoaVO().getPesDtBatismo())) {
		//				getMensagemUsuarioVO().getMensagemErro().add("<li>"+getText("ERRO.CADASTRO.DATA.BATISMO.OBRIGATORIO")+"</li>");
		//			} else if (!Util.isDataValida(getPessoaVO().getPesDtBatismo())) {
		//				getMensagemUsuarioVO().getMensagemErro().add("<li>"+getText("ERRO.CADASTRO.DATA.BATISMO.INVALIDA")+"</li>");
		//			} else if (!Util.isDataMaiorQueAtual(getPessoaVO().getPesDtBatismo())) {
		//				getMensagemUsuarioVO().getMensagemErro().add("<li>"+getText("ERRO.CADASTRO.DATA.BATISMO.MAIOR.IGUAL.ATUAL")+"</li>");
		//			}
		//		}

		if (getMensagemUsuarioVO().getMensagemErro().size() > 0) {
			throw new BusinessException("");
		}
	}

//	/**
//	 * 
//	 * @return
//	 */
//	@Action(value = "/SalvaPhoto", results = { @Result(name = SUCCESS, type = "json") })
//	public String salvarPhoto() {
//		try {
//			File arquivoFoto;
//			StringBuilder stringCaminhoFoto = new StringBuilder();
//			stringCaminhoFoto.append(System.getProperty("catalina.base"));
//
//			if (System.getenv("DSV") == null) {
//				stringCaminhoFoto.append("/webapps");
//			} else {
//				stringCaminhoFoto.append("/wtpwebapps");
//			}
//			stringCaminhoFoto.append("/PIBSantaLucia/resources/photo/congregados/");
//			// se nao estiver alterando...
//			if (getPessoaVO().getPesCodigo() == 0) {
//				SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//				String nomeFoto = format.format(Calendar.getInstance().getTime());
//				nomeFoto = nomeFoto.replace(":", "-");
//
//				stringCaminhoFoto.append(nomeFoto).append(".png");
//				arquivoFoto = new File(stringCaminhoFoto.toString());
//
//				FileUtils.copyFile(getPessoaVO().getFotoUpload(), arquivoFoto);
//				
//				Thumbnails.of(arquivoFoto)
//					.size(150, 150)
//					.outputFormat("png")
//					.outputQuality(1.00)
//					.toFiles(Rename.PREFIX_DOT_THUMBNAIL);
//
//				getPessoaVO().setPesFotoPath("../resources/photo/congregados/" + nomeFoto + ".png");
//			} else {
//				stringCaminhoFoto.append(getPessoaVO().getPesCodigo()).append(".png");
//				arquivoFoto = new File(stringCaminhoFoto.toString());
//
//				FileUtils.copyFile(getPessoaVO().getFotoUpload(), arquivoFoto);
//
//				// novo arquivo.
//				getPessoaVO().setPesFotoPath(stringCaminhoFoto.toString());
//				//				pessoaFacade.salvaFotoMembro(getPessoaVO());
//				getPessoaVO().setPesFotoPath("../resources/photo/congregados/" + getPessoaVO().getPesCodigo() + ".png");
//			}
//		} catch (Exception exception) {
//			setMensagemUsuarioVO(new MensagemUsuarioVO());
//			getMensagemUsuarioVO().getMensagemErro().add(getText("ERRO.CADASTRO.FOTO"));
//			LOGGER.error(getText("ERRO.CADASTRO.FOTO"), exception);
//		}
//		return SUCCESS;
//	}

	@SuppressWarnings("unchecked")
	@Action(value = "/BuscarContatos", results = { @Result(name = SUCCESS, type = "json"), @Result(name = LOGIN, location = "/login.jsp") })
	public String buscarContatos() {
		setListaContatoVO((ArrayList<ContatoVO>) this.getRequest().getSession().getAttribute(Constantes.KEY_LISTA_CONTATOS_SESSION));
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@Action(value = "/IncluiContato", results = { @Result(name = SUCCESS, type = "json") })
	public String incluirContato() {
		setListaContatoVO((ArrayList<ContatoVO>) this.getRequest().getSession().getAttribute(Constantes.KEY_LISTA_CONTATOS_SESSION));
		if (getListaContatoVO() == null) {
			setListaContatoVO(new ArrayList<ContatoVO>());
		}

		// verifica se é novo contato...
		if (getContatoVO().getCtoCodigo() == 0) {
			getContatoVO().setCtoCodigo(RandomUtils.nextInt());
			getContatoVO().setNovoContato(true);
			getListaContatoVO().add(getContatoVO());
		} else {
			for (ContatoVO contat : getListaContatoVO()) {
				if (contat.getCtoCodigo() == getContatoVO().getCtoCodigo()) {
					contat.setCtoDescricaoEmail(getContatoVO().getCtoDescricaoEmail());
					contat.setCtoDdd(getContatoVO().getCtoDdd());
					contat.setCtoNumeroTelefone(getContatoVO().getCtoNumeroTelefone());
					contat.setTipoContatoVO(new TipoContatoVO());
					contat.getTipoContatoVO().setTpcCodigo(getContatoVO().getTipoContatoVO().getTpcCodigo());
					contat.getTipoContatoVO().setTpcDescricao(getContatoVO().getTipoContatoVO().getTpcDescricao());
					break;
				}
			}
		}
		this.getRequest().getSession().setAttribute(Constantes.KEY_LISTA_CONTATOS_SESSION, getListaContatoVO());
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@Action(value = "/ExcluiContato", results = { @Result(name = SUCCESS, type = "json"), @Result(name = LOGIN, location = "/login.jsp") })
	public String excluirContato() {
		setListaContatoVO((ArrayList<ContatoVO>) this.getRequest().getSession().getAttribute(Constantes.KEY_LISTA_CONTATOS_SESSION));
		for (ContatoVO contat : getListaContatoVO()) {
			if (contat.getCtoCodigo() == getContatoVO().getCtoCodigo()) {
				if (contat.isNovoContato()) {
					getListaContatoVO().remove(contat);
				} else {
					contat.setExcluidoContato(true);
				}
				break;
			}
		}
		this.getRequest().getSession().setAttribute(Constantes.KEY_LISTA_CONTATOS_SESSION, getListaContatoVO());
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@Action(value = "/AlteraContato", results = { @Result(name = SUCCESS, type = "json"), @Result(name = LOGIN, location = "/login.jsp") })
	public String alterarContato() {
		setListaContatoVO((ArrayList<ContatoVO>) this.getRequest().getSession().getAttribute(Constantes.KEY_LISTA_CONTATOS_SESSION));
		for (ContatoVO contat : getListaContatoVO()) {
			if (contat.getCtoCodigo() == getContatoVO().getCtoCodigo()) {
				getContatoVO().setCtoCodigo(contat.getCtoCodigo());
				getContatoVO().setCtoDescricaoEmail(contat.getCtoDescricaoEmail());
				getContatoVO().setCtoDdd(contat.getCtoDdd());
				getContatoVO().setCtoNumeroTelefone(contat.getCtoNumeroTelefone());
				getContatoVO().setNovoContato(contat.isNovoContato());

				getContatoVO().setTipoContatoVO(new TipoContatoVO());
				getContatoVO().getTipoContatoVO().setTpcCodigo(contat.getTipoContatoVO().getTpcCodigo());
				getContatoVO().getTipoContatoVO().setTpcDescricao(contat.getTipoContatoVO().getTpcDescricao());
				break;
			}
		}
		return SUCCESS;
	}

	@Action(value = "/PesquisaCongregado", results = { @Result(name = SUCCESS, location = "congregado.pesquisa.tiles", type = "tiles"), @Result(name = LOGIN, location = "/login.jsp") })
	public String pesquisa() {
		try {
			this.getRequest().getSession().removeAttribute(Constantes.KEY_LISTA_CONTATOS_SESSION);
			String pesFglMembro = "S";
			if (getPessoaVO() != null && getPessoaVO().getPesFglMembro() != null) {
				pesFglMembro = getPessoaVO().getPesFglMembro();
			}
			
			setListaViewMembroVO(pessoaFacade.listaCongregados(null, null, pesFglMembro));
		} catch (Exception exception) {
			LOGGER.error("Erro ao listar membros.", exception);
			mensagemUsuarioVO = new MensagemUsuarioVO();
			mensagemUsuarioVO.getMensagemErro().add(getText("ERRO.SISTEMA.INDISPONIVEL"));
		}
		return SUCCESS;
	}

	public PessoaVO getPessoaVO() {
		return pessoaVO;
	}

	public void setPessoaVO(PessoaVO pessoaVO) {
		this.pessoaVO = pessoaVO;
	}

	public List<TipoContatoVO> getListaTipoContatoVO() {
		return listaTipoContatoVO;
	}

	public void setListaTipoContatoVO(List<TipoContatoVO> listaTipoContatoVO) {
		this.listaTipoContatoVO = listaTipoContatoVO;
	}

	public ViaCepVO getViaCepVO() {
		return viaCepVO;
	}

	public void setViaCepVO(ViaCepVO viaCepVO) {
		this.viaCepVO = viaCepVO;
	}

	public MensagemUsuarioVO getMensagemUsuarioVO() {
		return mensagemUsuarioVO;
	}

	public void setMensagemUsuarioVO(MensagemUsuarioVO mensagemUsuarioVO) {
		this.mensagemUsuarioVO = mensagemUsuarioVO;
	}

	public ContatoVO getContatoVO() {
		return contatoVO;
	}

	public void setContatoVO(ContatoVO contatoVO) {
		this.contatoVO = contatoVO;
	}

	public List<ContatoVO> getListaContatoVO() {
		return listaContatoVO;
	}

	public void setListaContatoVO(List<ContatoVO> listaContatoVO) {
		this.listaContatoVO = listaContatoVO;
	}

	public List<ViewMembroVO> getListaViewMembroVO() {
		return listaViewMembroVO;
	}

	public void setListaViewMembroVO(List<ViewMembroVO> listaViewMembroVO) {
		this.listaViewMembroVO = listaViewMembroVO;
	}
}
