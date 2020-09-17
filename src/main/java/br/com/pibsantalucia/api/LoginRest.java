package br.com.pibsantalucia.api;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.pibsantalucia.api.vo.Cadastro;
import br.com.pibsantalucia.api.vo.Login;
import br.com.pibsantalucia.api.vo.ReturnApiObject;
import br.com.pibsantalucia.api.vo.TrocaSenha;
import br.com.pibsantalucia.facade.PessoaFacade;
import br.com.pibsantalucia.facade.UsuarioMembroFacade;
import br.com.pibsantalucia.model.Contato;
import br.com.pibsantalucia.model.Perfil;
import br.com.pibsantalucia.model.Pessoa;
import br.com.pibsantalucia.model.UsuarioMembro;
import br.com.pibsantalucia.security.JWTUtil;
import br.com.pibsantalucia.util.BusinessException;
import br.com.pibsantalucia.util.Constantes;
import br.com.pibsantalucia.vo.UsuarioMembroVO;

@RestController
@RequestMapping("/Api/LoginRest")
public class LoginRest extends BaseRest {

	private final Logger LOGGER = LoggerFactory.getLogger(LoginRest.class);

	@Autowired
	private PessoaFacade pessoaFacade;
	
	@Autowired
	private UsuarioMembroFacade usuarioMembroFacade;
	
	@RequestMapping(path = "/Login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ReturnApiObject> efetuaLogin(@RequestBody Login login) {

		try {
			// valida campos
			if (login.getCpfOrEmail() == null || login.getCpfOrEmail().trim().equals("")) {
				getObject().setCode(HttpStatus.FORBIDDEN.value());
				getObject().setMessage(Constantes.MSG_CPF_EMAIL_INVALIDO);
				return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.FORBIDDEN);
			}
			
			if (login.getPassword() == null || login.getPassword().trim().equals("")) {
				getObject().setCode(HttpStatus.FORBIDDEN.value());
				getObject().setMessage(Constantes.MSG_SENHA_INVALIDO);
				return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.FORBIDDEN);
			}
			
			UsuarioMembro usuarioMembro = usuarioMembroFacade.dadosLoginExiste(login.getCpfOrEmail());
			if (usuarioMembro == null) {
	    		// erro usuário não cadastrado.
				getObject().setCode(HttpStatus.FORBIDDEN.value());
				getObject().setMessage(Constantes.MSG_CPF_EMAIL_SEM_CADASTRO_ENCONTRADO);
				return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.FORBIDDEN);
			}
			
			// efetua login.
			UsuarioMembroVO usuarioMembroVO = usuarioMembroFacade.login(login.getCpfOrEmail(), login.getPassword());
			if (usuarioMembroVO == null) {
				getObject().setCode(HttpStatus.UNAUTHORIZED.value());
				getObject().setMessage(Constantes.MSG_LOGIN_SENHA_INVALIDOS);
				return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.UNAUTHORIZED);
			}
			
			String token = JWTUtil.create(String.valueOf(usuarioMembroVO.getUmeCodigo()));
			usuarioMembroVO.setToken(token);

			getObject().setObject(usuarioMembroVO);
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.OK);
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			if (exception instanceof BusinessException) {
				getObject().setCode(HttpStatus.UNAUTHORIZED.value());
				getObject().setMessage(Constantes.MSG_LOGIN_SENHA_INVALIDOS);
				return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.UNAUTHORIZED);
			}
			getObject().setCode(HttpStatus.BAD_REQUEST.value());
			getObject().setMessage(Constantes.SYSTEM_UNAVAILABLE_MOMENT);
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(path = "/Cadastro", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ReturnApiObject> cadastro(@RequestBody Cadastro cadastro) {
		
		try {
			
			if (cadastro.getCpfOrEmail() == null || cadastro.getCpfOrEmail().trim().equals("")) {
				getObject().setCode(HttpStatus.FORBIDDEN.value());
				getObject().setMessage(Constantes.MSG_CPF_EMAIL_INVALIDO);
				return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.FORBIDDEN);
			}
		    
		    // 
			UsuarioMembro usuarioMembro = usuarioMembroFacade.dadosLoginExiste(cadastro.getCpfOrEmail());
			if (usuarioMembro != null) {
				
				// verifica se é email
				List<Contato> list = pessoaFacade.listContatoPorEmail(usuarioMembro.getPessoa().getPesCodigo());
				if (list != null && !list.isEmpty()) {
					UsuarioMembro usuario = usuarioMembroFacade.obterUsuarioMembro(usuarioMembro.getPessoa().getPesCodigo());
					if (usuario == null) {
				    	// gerar senha temporária
						String generatedPassword = RandomStringUtils.randomNumeric(6);
			    		
				    	// cadastrar
						UsuarioMembro usuarioMembroNew = new UsuarioMembro();
						usuarioMembroNew.setUsuDtCadastro(Calendar.getInstance());
						usuarioMembroNew.setUmeSenha(generatedPassword);
						usuarioMembroNew.setUmeSenhaTemp(true);
						Pessoa pessoa = new Pessoa();
						pessoa.setPesCodigo(usuarioMembro.getPessoa().getPesCodigo());
						usuarioMembroNew.setPessoa(pessoa);
			    		
			    		// Perfil de Membro
						Perfil perfil = new Perfil();
						perfil.setPerCodigo(1);
						usuarioMembroNew.setPerfil(perfil);
			    		
				    	usuarioMembroFacade.salvarComEnvioSenhaTemp(usuarioMembroNew, list);
				    	StringBuilder emails = new StringBuilder();
						for (Contato contato: list) {
							if (emails.toString().length() > 0) {
								emails.append("\n");
							}
							emails.append(contato.getCtoDescricaoEmail());
						}
				    	getObject().setCode(HttpStatus.OK.value());
						getObject().setMessage(Constantes.MSG_CADASTRO_REALIZADO_SUCESSO + emails.toString());
				    	return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.OK);
					} else {
			    		// erro usuário já cadastrado.
						getObject().setCode(HttpStatus.FORBIDDEN.value());
						getObject().setMessage(Constantes.MSG_USUARIO_JA_CADASTRADO);
						return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.FORBIDDEN);
					}
				} else {
					getObject().setCode(HttpStatus.FORBIDDEN.value());
					getObject().setMessage(Constantes.MSG_EMAIL_NAO_ENCONTRADO);
					return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.FORBIDDEN);
				}
			} else {
				getObject().setCode(HttpStatus.FORBIDDEN.value());
				getObject().setMessage(Constantes.MSG_CPF_EMAIL_NAO_ENCONTRADO);
				return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.FORBIDDEN);
			}
		} catch (Exception exception) {
			LOGGER.error("*********** 04");
			LOGGER.error(exception.getMessage());
			getObject().setCode(HttpStatus.BAD_REQUEST.value());
			getObject().setMessage(Constantes.SYSTEM_UNAVAILABLE_MOMENT);
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(path = "/EsquiciSenha", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ReturnApiObject> esqueciSenha(@RequestBody Login login) {
		
		try {
			if (login.getCpfOrEmail() == null || login.getCpfOrEmail().trim().equals("")) {
				getObject().setCode(HttpStatus.FORBIDDEN.value());
				getObject().setMessage(Constantes.MSG_CPF_EMAIL_INVALIDO);
				return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.FORBIDDEN);
			}
			
			// verifica se o login(CPF ou Email) existe como usuário.
			UsuarioMembro usuarioMembro = usuarioMembroFacade.verificaLoginExistente(login.getCpfOrEmail().trim());
			if (usuarioMembro != null) {
				
				// obter emails
				List<Contato> listEmails = pessoaFacade.listContatoPorEmail(usuarioMembro.getPessoa().getPesCodigo());
				if (listEmails != null && !listEmails.isEmpty()) {
					
					UsuarioMembro usuario = usuarioMembroFacade.obterUsuarioMembro(usuarioMembro.getPessoa().getPesCodigo());
					if (usuario != null) {
				    	// gerar senha temporária
						String generatedPassword = RandomStringUtils.randomNumeric(6);
						
						usuario.setUmeSenha(generatedPassword);
			    		usuario.setUmeSenhaTemp(true);
			    		
				    	usuarioMembroFacade.salvarComEnvioSenhaTemp(usuario, listEmails);
				    	
				    	StringBuilder emails = new StringBuilder();
						for (Contato contato: listEmails) {
							if (emails.toString().length() > 0) {
								emails.append("\n");
							}
							emails.append(contato.getCtoDescricaoEmail());
						}
				    	
				    	getObject().setCode(HttpStatus.OK.value());
						getObject().setMessage(Constantes.MSG_ESQUECI_SENHA_SUCESSO + emails.toString());
				    	return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.OK);
					} else {
			    		// erro usuário já cadastrado.
						getObject().setCode(HttpStatus.FORBIDDEN.value());
						getObject().setMessage(Constantes.MSG_DADOS_CADASTRO_NAO_ENCONTRADO);
						return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.FORBIDDEN);
					}
				} else {
		    		// erro usuário não cadastrado.
					getObject().setCode(HttpStatus.FORBIDDEN.value());
					getObject().setMessage(Constantes.MSG_EMAIL_NAO_ENCONTRADO);
					return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.FORBIDDEN);
				}
	    	} else {
	    		// erro usuário não cadastrado.
				getObject().setCode(HttpStatus.FORBIDDEN.value());
				getObject().setMessage(Constantes.MSG_CPF_EMAIL_SEM_CADASTRO_ENCONTRADO);
				return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.FORBIDDEN);
	    	}
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			getObject().setCode(HttpStatus.BAD_REQUEST.value());
			getObject().setMessage(Constantes.SYSTEM_UNAVAILABLE_MOMENT);
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(path = "/TrocaSenha", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ReturnApiObject> trocarSenha(@RequestBody TrocaSenha trocaSenha) {
		
		try {
			
			if (trocaSenha.getCpfOrEmail() == null || trocaSenha.getCpfOrEmail().trim().equals("")) {
				getObject().setCode(HttpStatus.FORBIDDEN.value());
				getObject().setMessage(Constantes.MSG_CPF_EMAIL_INVALIDO);
				return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.FORBIDDEN);
			}
			
			if (trocaSenha.getPasswordNew().length() <= 5) {
				getObject().setCode(HttpStatus.FORBIDDEN.value());
				getObject().setMessage(Constantes.MSG_NOVA_SENHA_MINIMO_6_LETRAS);
				return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.FORBIDDEN);
			}
			
			if (!trocaSenha.getPasswordNew().trim().toUpperCase().equals(trocaSenha.getPasswordNewConfirmation().trim().toUpperCase())) {
				getObject().setCode(HttpStatus.FORBIDDEN.value());
				getObject().setMessage(Constantes.MSG_SENHA_CONFIRMACAO_INCORRETA);
				return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.FORBIDDEN);
			}
		    
			// verifica se o login(CPF ou Email) existe como usuário.
			UsuarioMembro usuarioMembro = usuarioMembroFacade.verificaLoginExistente(trocaSenha.getCpfOrEmail().trim());
			if (usuarioMembro != null) {
	    		usuarioMembro.setUmeSenhaTemp(false);
	    		usuarioMembro.setUmeSenha(trocaSenha.getPasswordNew());
	    		usuarioMembro.setUsuDtCadastro(Calendar.getInstance());
	    		
		    	usuarioMembroFacade.salvar(usuarioMembro);
		    	
		    	UsuarioMembroVO usuarioMembroVO = usuarioMembroFacade.login(trocaSenha.getCpfOrEmail(), 
		    			trocaSenha.getPasswordNew());
		    	
		    	String token = JWTUtil.create(String.valueOf(usuarioMembroVO.getUmeCodigo()));
				usuarioMembroVO.setToken(token);
		    	
				getObject().setCode(HttpStatus.OK.value());
				getObject().setMessage(Constantes.MSG_SENHA_ALTERADA_COM_SUCESSO);
				getObject().setObjectWithMessage(usuarioMembroVO);

		    	return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.OK);
			} else {
	    		// erro usuário não cadastrado.
				getObject().setCode(HttpStatus.FORBIDDEN.value());
				getObject().setMessage(Constantes.MSG_DADOS_CADASTRO_NAO_ENCONTRADO);
				return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.FORBIDDEN);
			}
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			getObject().setCode(HttpStatus.BAD_REQUEST.value());
			getObject().setMessage(Constantes.SYSTEM_UNAVAILABLE_MOMENT);
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.BAD_REQUEST);
		}
	}
}
