package br.com.pibsantalucia.api;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.pibsantalucia.api.vo.AlterarPessoa;
import br.com.pibsantalucia.api.vo.ReturnApiObject;
import br.com.pibsantalucia.facade.DesligamentoFacade;
import br.com.pibsantalucia.facade.ObitoFacade;
import br.com.pibsantalucia.facade.PastoralFacade;
import br.com.pibsantalucia.facade.PessoaFacade;
import br.com.pibsantalucia.facade.ReconciliacaoFacade;
import br.com.pibsantalucia.facade.TransferenciaFacade;
import br.com.pibsantalucia.security.JWTUtil;
import br.com.pibsantalucia.util.BusinessException;
import br.com.pibsantalucia.util.Constantes;
import br.com.pibsantalucia.util.Util;
import br.com.pibsantalucia.vo.CadastroVO;
import br.com.pibsantalucia.vo.DesligamentoVO;
import br.com.pibsantalucia.vo.DeviceMobileVO;
import br.com.pibsantalucia.vo.ObitoVO;
import br.com.pibsantalucia.vo.PastoralVO;
import br.com.pibsantalucia.vo.PessoaVO;
import br.com.pibsantalucia.vo.ReconciliacaoVO;
import br.com.pibsantalucia.vo.TransferenciaVO;
import br.com.pibsantalucia.vo.UsuarioMembroVO;
import br.com.pibsantalucia.vo.ViaCepVO;
import br.com.pibsantalucia.vo.ViewMembroVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@RestController
@RequestMapping("/Api/PessoaRest")
public class PessoaRest extends BaseRest {

	private final Logger LOGGER = LoggerFactory.getLogger(PessoaRest.class);

	@Autowired
	private PessoaFacade pessoaFacade;
	
	@Autowired
	private ObitoFacade obitoFacade;
	
	@Autowired
	private TransferenciaFacade transferenciaFacade;
	
	@Autowired
	private ReconciliacaoFacade reconciliacaoFacade;
	
	@Autowired
	private DesligamentoFacade desligamentoFacade;
	
	@Autowired
	private PastoralFacade pastoralFacade;
	
	@Deprecated
	@RequestMapping(path = "/Pastoral", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ReturnApiObject> getPastoral() {
		
		try {
			String dataString = Util.converterDateParaString(new Date());
			String[] arrayDate = dataString.split("/");
			String dataParam = arrayDate[2]+"-"+arrayDate[1]+"-15";
			
			PastoralVO pastoralVO = pastoralFacade.obterPorDataReferencia(dataParam);
			String pasPastoral = pastoralVO.getPasPastoral();
			
			pastoralVO.setPasPastoral(htmlPastoral(pasPastoral));
			
			getObject().setObject(pastoralVO);
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.OK);
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			getObject().setCode(HttpStatus.BAD_REQUEST.value());
			if (exception instanceof NoResultException) {
				getObject().setMessage("Nenhuma pastoral encontrada.");
			} else {
				getObject().setMessage(Constantes.SYSTEM_UNAVAILABLE_MOMENT);
			}
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(path = "/GetPastoral", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ReturnApiObject> pastoral() {
		
		try {
			String dataString = Util.converterDateParaString(new Date());
			String[] arrayDate = dataString.split("/");
			String dataParam = arrayDate[2]+"-"+arrayDate[1]+"-15";
			
			PastoralVO pastoralVO = pastoralFacade.obterPorDataReferencia(dataParam);
			String pasPastoral = pastoralVO.getPasPastoral();
			
			pastoralVO.setPasPastoral(htmlPastoral(pasPastoral));
			
			getObject().setObject(pastoralVO);
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.OK);
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			getObject().setCode(HttpStatus.BAD_REQUEST.value());
			if (exception instanceof NoResultException) {
				getObject().setMessage("Nenhuma pastoral encontrada.");
			} else {
				getObject().setMessage(Constantes.SYSTEM_UNAVAILABLE_MOMENT);
			}
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.BAD_REQUEST);
		}
	}
	
	private String htmlPastoral(String htmlParam) {
		StringBuilder html = new StringBuilder();
		
		html.append("<!DOCTYPE html>");
		html.append("  <head>");
		html.append("    <meta charset=\'utf-8\'>");
		html.append("    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/KaTeX/0.7.1/katex.min.css' />");
		html.append("    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.12.0/styles/monokai-sublime.min.css' />");
		html.append("    <link rel='stylesheet' href='https://cdn.quilljs.com/1.3.7/quill.snow.css' />");
		html.append("    <script src='https://cdnjs.cloudflare.com/ajax/libs/KaTeX/0.7.1/katex.min.js'></script>");
		html.append("    <script src='https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.12.0/highlight.min.js'></script>");
		html.append("    <script src='https://cdn.quilljs.com/1.3.7/quill.min.js'></script>");
		html.append("  </head>");
		html.append("  <body>");
		html.append("    <div id='editor-container'></div>");
		html.append("    <script>");
		html.append("      const editorContent = '"+htmlParam+"';");
		html.append("      const editor = document.getElementById('editor-container');");
		html.append("      const quill = new Quill(editor, {});");
		html.append("      quill.disable();");
		html.append("      editor.firstChild.innerHTML = editorContent;");
		html.append("    </script>");
		html.append("  </body>");
		html.append("</html>");
		
		return html.toString();
	}
	
	@Deprecated
	@RequestMapping(path = "/PreparaCadastro", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ReturnApiObject> preparaCadastro() {

		try {
			CadastroVO cadastroVO = new CadastroVO();
			cadastroVO.setListaTipoContatoVO(pessoaFacade.listaTipoContatos());

			getObject().setObject(cadastroVO);
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.OK);
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			getObject().setCode(HttpStatus.BAD_REQUEST.value());
			getObject().setMessage(Constantes.SYSTEM_UNAVAILABLE_MOMENT);
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(path = "/GetPreparaCadastro", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ReturnApiObject> getPreparaCadastro() {

		try {
			CadastroVO cadastroVO = new CadastroVO();
			cadastroVO.setListaTipoContatoVO(pessoaFacade.listaTipoContatos());

			getObject().setObject(cadastroVO);
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.OK);
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			getObject().setCode(HttpStatus.BAD_REQUEST.value());
			getObject().setMessage(Constantes.SYSTEM_UNAVAILABLE_MOMENT);
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@Deprecated
	@RequestMapping(path = "/Lista", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ReturnApiObject> listar() {
		
		try {
			List<ViewMembroVO> lista = pessoaFacade.listaCongregados(null, null, null);
			
			getObject().setObject(lista);
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.OK);
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			getObject().setCode(HttpStatus.BAD_REQUEST.value());
			getObject().setMessage(Constantes.SYSTEM_UNAVAILABLE_MOMENT);
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(path = "/GetLista", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ReturnApiObject> getListar() {
		
		try {
			List<ViewMembroVO> lista = pessoaFacade.listaCongregados(null, null, null);
			
			getObject().setObject(lista);
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.OK);
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			getObject().setCode(HttpStatus.BAD_REQUEST.value());
			getObject().setMessage(Constantes.SYSTEM_UNAVAILABLE_MOMENT);
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@Deprecated
	@RequestMapping(path = "/ListaAniversariantes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ReturnApiObject> aniversariantes() {
		
		try {
			int mes = Calendar.getInstance().get(Calendar.MONTH);
			
			List<ViewMembroVO> lista = pessoaFacade.listaAniversariantes(mes+1);
			
			if (lista.isEmpty()) {
				getObject().setCode(HttpStatus.BAD_REQUEST.value());
				getObject().setMessage("Nenhum aniversariante encontrado para este mês.");
				return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.BAD_REQUEST);
			}
			
			getObject().setObject(lista);
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.OK);
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			getObject().setCode(HttpStatus.BAD_REQUEST.value());
			getObject().setMessage(Constantes.SYSTEM_UNAVAILABLE_MOMENT);
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(path = "/GetAniversariantes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ReturnApiObject> getAniversariantes() {
		
		try {
			int mes = Calendar.getInstance().get(Calendar.MONTH);
			
			List<ViewMembroVO> lista = pessoaFacade.listaAniversariantes(mes+1);
			
			if (lista.isEmpty()) {
				getObject().setCode(HttpStatus.BAD_REQUEST.value());
				getObject().setMessage("Nenhum aniversariante encontrado para este mês.");
				return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.BAD_REQUEST);
			}
			
			getObject().setObject(lista);
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.OK);
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			getObject().setCode(HttpStatus.BAD_REQUEST.value());
			getObject().setMessage(Constantes.SYSTEM_UNAVAILABLE_MOMENT);
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@Deprecated
	@RequestMapping(path = "/ProcuraCep", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<ReturnApiObject> produraCep(@RequestParam(value = "cep") String cep) {
		try {
			ViaCepVO viaCepVO = pessoaFacade.buscarCep(cep);

			getObject().setObject(viaCepVO);
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.OK);
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			getObject().setCode(HttpStatus.BAD_REQUEST.value());
			getObject().setMessage(Constantes.SYSTEM_UNAVAILABLE_MOMENT);
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(path = "/GetCep", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<ReturnApiObject> getCep(@RequestParam(value = "cep") String cep) {
		try {
			ViaCepVO viaCepVO = pessoaFacade.buscarCep(cep);

			getObject().setObject(viaCepVO);
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.OK);
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			getObject().setCode(HttpStatus.BAD_REQUEST.value());
			getObject().setMessage(Constantes.SYSTEM_UNAVAILABLE_MOMENT);
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@Deprecated
	@RequestMapping(path = "/GetPessoa", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ReturnApiObject> alterar(@RequestBody AlterarPessoa alterarPessoa) {
		try {
			PessoaVO pessoaVO = pessoaFacade.obterCongregado(alterarPessoa.getPesCodigo());
			getObject().setObject(pessoaVO);
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.OK);
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			getObject().setCode(HttpStatus.BAD_REQUEST.value());
			getObject().setMessage(Constantes.SYSTEM_UNAVAILABLE_MOMENT);
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(path = "/GetMembro", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ReturnApiObject> getMembro(@RequestParam(value = "pesCodigo") long pesCodigo) {
		try {
			PessoaVO pessoaVO = pessoaFacade.obterCongregado(pesCodigo);
			getObject().setObject(pessoaVO);
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.OK);
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			getObject().setCode(HttpStatus.BAD_REQUEST.value());
			getObject().setMessage(Constantes.SYSTEM_UNAVAILABLE_MOMENT);
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(path = "/SavePerson", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ReturnApiObject> salvar(@RequestBody PessoaVO pessoaVO, @RequestHeader(value="Authentication") String token) {
		try {
			
			// validações
			pessoaFacade.validarMembro(pessoaVO);
			
			// continua
			Jws<Claims> jws = JWTUtil.decode(token);
			String umeCodigo = jws.getBody().getSubject();
			
			UsuarioMembroVO usuarioMembroVO = new UsuarioMembroVO();
			usuarioMembroVO.setUmeCodigo(Long.parseLong(umeCodigo));
			
			pessoaVO.setUsuarioMembroVO(usuarioMembroVO);
			
			pessoaFacade.salvar(pessoaVO);

			if (pessoaVO.getPesCodigo() == 0) {
				getObject().setMessage("Cadastro realizado com sucesso!");
			} else {
				getObject().setMessage("Atualização realizado com sucesso!");
			}
			LOGGER.info(getObject().getMessage());
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.OK);
		} catch (Exception exception) {
			getObject().setCode(HttpStatus.BAD_REQUEST.value());
			LOGGER.error(exception.getMessage(), exception);
			if (exception instanceof BusinessException) {
				getObject().setMessage(exception.getMessage());
			} else {
				getObject().setMessage(Constantes.SYSTEM_UNAVAILABLE_MOMENT);
			}
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(path = "/SaveObito", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ReturnApiObject> salvarObito(@RequestBody ObitoVO obitoVO, @RequestHeader(value="Authentication") String token) {
		try {
			
			// validações
			validarObito(obitoVO);
			
			// continua
			Jws<Claims> jws = JWTUtil.decode(token);
			String umeCodigo = jws.getBody().getSubject();
			
			UsuarioMembroVO usuarioMembroVO = new UsuarioMembroVO();
			usuarioMembroVO.setUmeCodigo(Long.parseLong(umeCodigo));
			
			obitoVO.setUsuarioMembroVO(usuarioMembroVO);
			
			obitoFacade.salvar(obitoVO);

			getObject().setMessage("Cadastro realizado com sucesso!");
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.OK);
		} catch (Exception exception) {
			getObject().setCode(HttpStatus.BAD_REQUEST.value());
			LOGGER.error(exception.getMessage());
			if (exception instanceof BusinessException) {
				getObject().setMessage(exception.getMessage());
			} else {
				getObject().setMessage(Constantes.SYSTEM_UNAVAILABLE_MOMENT);
			}
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(path = "/SaveTransferencia", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ReturnApiObject> salvarTransferencia(@RequestBody TransferenciaVO transferenciaVO, @RequestHeader(value="Authentication") String token) {
		try {
			
			// validações
			validaTransferencia(transferenciaVO);
			
			// continua
			Jws<Claims> jws = JWTUtil.decode(token);
			String umeCodigo = jws.getBody().getSubject();
			
			UsuarioMembroVO usuarioMembroVO = new UsuarioMembroVO();
			usuarioMembroVO.setUmeCodigo(Long.parseLong(umeCodigo));
			
			transferenciaVO.setUsuarioMembroVO(usuarioMembroVO);
			
			transferenciaFacade.salvar(transferenciaVO);

			getObject().setMessage("Cadastro realizado com sucesso!");
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.OK);
		} catch (Exception exception) {
			getObject().setCode(HttpStatus.BAD_REQUEST.value());
			LOGGER.error(exception.getMessage());
			if (exception instanceof BusinessException) {
				getObject().setMessage(exception.getMessage());
			} else {
				getObject().setMessage(Constantes.SYSTEM_UNAVAILABLE_MOMENT);
			}
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(path = "/SaveReconciliacao", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ReturnApiObject> salvarReconciliacao(@RequestBody ReconciliacaoVO reconciliacaoVO, @RequestHeader(value="Authentication") String token) {
		try {
			
			// validações
			validaReconciliacao(reconciliacaoVO);
			
			// continua
			Jws<Claims> jws = JWTUtil.decode(token);
			String umeCodigo = jws.getBody().getSubject();
			
			UsuarioMembroVO usuarioMembroVO = new UsuarioMembroVO();
			usuarioMembroVO.setUmeCodigo(Long.parseLong(umeCodigo));
			
			reconciliacaoVO.setUsuarioMembroVO(usuarioMembroVO);
			
			reconciliacaoFacade.salvar(reconciliacaoVO);

			getObject().setMessage("Cadastro realizado com sucesso!");
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.OK);
		} catch (Exception exception) {
			getObject().setCode(HttpStatus.BAD_REQUEST.value());
			LOGGER.error(exception.getMessage());
			if (exception instanceof BusinessException) {
				getObject().setMessage(exception.getMessage());
			} else {
				getObject().setMessage(Constantes.SYSTEM_UNAVAILABLE_MOMENT);
			}
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(path = "/SaveDesligamento", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ReturnApiObject> salvarDesligamento(@RequestBody DesligamentoVO desligamentoVO, @RequestHeader(value="Authentication") String token) {
		try {
			
			// validações
			validaDesligamento(desligamentoVO);
			
			// continua
			Jws<Claims> jws = JWTUtil.decode(token);
			String umeCodigo = jws.getBody().getSubject();
			
			UsuarioMembroVO usuarioMembroVO = new UsuarioMembroVO();
			usuarioMembroVO.setUmeCodigo(Long.parseLong(umeCodigo));
			
			desligamentoVO.setUsuarioMembroVO(usuarioMembroVO);
			
			desligamentoFacade.salvar(desligamentoVO);

			getObject().setMessage("Cadastro realizado com sucesso!");
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.OK);
		} catch (Exception exception) {
			getObject().setCode(HttpStatus.BAD_REQUEST.value());
			LOGGER.error(exception.getMessage());
			if (exception instanceof BusinessException) {
				getObject().setMessage(exception.getMessage());
			} else {
				getObject().setMessage(Constantes.SYSTEM_UNAVAILABLE_MOMENT);
			}
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(path = "/SaveDeviceMobile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ReturnApiObject> salvarDeviceMobile(@RequestBody DeviceMobileVO deviceMobileVO, @RequestHeader(value="Authentication") String token) {
		try {
			
			pessoaFacade.salvarDevice(deviceMobileVO);

			getObject().setMessage("Cadastro realizado com sucesso!");
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.OK);
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			getObject().setCode(HttpStatus.BAD_REQUEST.value());
			getObject().setMessage(Constantes.SYSTEM_UNAVAILABLE_MOMENT);
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.BAD_REQUEST);
		}
	}
	
	private void validarObito(ObitoVO obitoVO) throws BusinessException {
		if (!Util.isCampoPreenchido(obitoVO.getObiDtObito())) {
			throw new BusinessException("A data do óbito é obrigatório.");
		} else if (!Util.isDataValida(obitoVO.getObiDtObito())) {
			throw new BusinessException("A data do óbito é inválida.");
		} else if (!Util.isDataMaiorQueAtual(obitoVO.getObiDtObito())) {
			throw new BusinessException("A data do óbito não pode ser maior ou igual a data atual.");
		}

		if (!Util.isCampoPreenchido(obitoVO.getObiCausa())) {
			throw new BusinessException("A causa do óbito é obrigatório.");
		}
	}
	
	private void validaTransferencia(TransferenciaVO transferenciaVO) throws BusinessException {
		if (!Util.isCampoPreenchido(transferenciaVO.getTraDtTransferencia())) {
			throw new BusinessException("A data da transferência é obrigatório.");
		} else if (!Util.isDataValida(transferenciaVO.getTraDtTransferencia())) {
			throw new BusinessException("A data da transferência é inválida.");
		} else if (!Util.isDataMaiorQueAtual(transferenciaVO.getTraDtTransferencia())) {
			throw new BusinessException("A data da transferência não pode ser maior ou igual a data atual.");
		}

		if (!Util.isCampoPreenchido(transferenciaVO.getTraMotivo())) {
			throw new BusinessException("O motivo da transferência é obrigatório.");
		}

		if (!Util.isCampoPreenchido(transferenciaVO.getTraStatus()) && (transferenciaVO.getTraStatus().equals("E") || transferenciaVO.getTraStatus().equals("S"))) {
			throw new BusinessException("O status da transferência é obrigatório.");
		}

		if (!Util.isCampoPreenchido(transferenciaVO.getTraNomeIgreja())) {
			throw new BusinessException("O nome da igreja é obrigatório.");
		}
	}
	
	private void validaReconciliacao(ReconciliacaoVO reconciliacaoVO) throws BusinessException {
		if (!Util.isCampoPreenchido(reconciliacaoVO.getRecDtReconciliacao())) {
			throw new BusinessException("A data da reconciliação é obrigatório.");
		} else if (!Util.isDataValida(reconciliacaoVO.getRecDtReconciliacao())) {
			throw new BusinessException("A data da reconciliação é inválida.");
		} else if (!Util.isDataMaiorQueAtual(reconciliacaoVO.getRecDtReconciliacao())) {
			throw new BusinessException("A data da reconciliação não pode ser maior ou igual a data atual.");
		}
	}
	
	private void validaDesligamento(DesligamentoVO desligamentoVO) throws BusinessException {
		if (!Util.isCampoPreenchido(desligamentoVO.getDesDtDesligamento())) {
			throw new BusinessException("A data do desligamento é obrigatório.");
		} else if (!Util.isDataValida(desligamentoVO.getDesDtDesligamento())) {
			throw new BusinessException("A data do desligamento é inválida.");
		} else if (!Util.isDataMaiorQueAtual(desligamentoVO.getDesDtDesligamento())) {
			throw new BusinessException("A data do desligamento não pode ser maior ou igual a data atual.");
		}
	}
}
