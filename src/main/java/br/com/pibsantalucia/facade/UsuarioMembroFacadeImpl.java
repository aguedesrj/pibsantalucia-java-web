package br.com.pibsantalucia.facade;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.pibsantalucia.dao.HibernateDao;
import br.com.pibsantalucia.dao.UsuarioMembroDao;
import br.com.pibsantalucia.model.Contato;
import br.com.pibsantalucia.model.UsuarioMembro;
import br.com.pibsantalucia.util.BusinessException;
import br.com.pibsantalucia.util.Util;
import br.com.pibsantalucia.vo.UsuarioMembroVO;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
public class UsuarioMembroFacadeImpl extends HibernateDao implements UsuarioMembroFacade {
	
	private final Logger LOGGER = LoggerFactory.getLogger(UsuarioMembroFacadeImpl.class);

	@Autowired
	private UsuarioMembroDao usuarioMembroDao;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void salvar(UsuarioMembro usuarioMembro) throws Exception {
		try {
			getSession().saveOrUpdate(usuarioMembro);
			getSession().flush();
			
		} catch (Exception exception) {
			LOGGER.error("Erro salvar usuário.", exception);
			throw new Exception("ERRO.SISTEMA.INDISPONIVEL");
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void salvarComEnvioSenhaTemp(UsuarioMembro usuarioMembro, List<Contato> listEmails) throws Exception {
		try {
			getSession().saveOrUpdate(usuarioMembro);
			getSession().flush();
			for (Contato contato: listEmails) {
				// envio de email temporário
				this.sendEmailSenhaTemp(contato.getCtoDescricaoEmail(), usuarioMembro.getUmeSenha());
			}	
		} catch (Exception exception) {
			LOGGER.error("Erro salvar usuário.", exception);
			if (exception instanceof BusinessException) {
				throw new Exception(exception.getMessage());
			}
			throw new Exception("ERRO.SISTEMA.INDISPONIVEL");
		}
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public UsuarioMembroVO login(String login, String senha) throws Exception {
		UsuarioMembro usuarioMembro = usuarioMembroDao.login(login, senha);
		UsuarioMembroVO usuarioMembroVO = new UsuarioMembroVO();
		
		usuarioMembroVO.setUmeCodigo(usuarioMembro.getUmeCodigo());
		usuarioMembroVO.setPesCodigo(usuarioMembro.getPessoa().getPesCodigo());
		usuarioMembroVO.setPesNome(usuarioMembro.getPessoa().getPesNome());
		usuarioMembroVO.setPerCodigo(usuarioMembro.getPerfil().getPerCodigo());
		usuarioMembroVO.setUmeSenhaTemp(usuarioMembro.getUmeSenhaTemp());
		usuarioMembroVO.setUrlPhoto(Util.getPathPhotoCongregado(usuarioMembro.getPessoa().getPesCodigo(), 
				usuarioMembro.getPessoa().getPesNamePhoto()));
		
		return usuarioMembroVO;
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public UsuarioMembro obterUsuarioMembro(long pesCodigo) throws Exception {
		return usuarioMembroDao.obterUsuarioMembro(pesCodigo);
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public UsuarioMembro dadosLoginExiste(final String login) throws Exception {
		return usuarioMembroDao.dadosLoginExiste(login);
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public UsuarioMembro verificaLoginExistente(final String login) throws Exception {
		return usuarioMembroDao.verificaLoginExistente(login);
	}
	
	/**
	 * 
	 * @param email
	 * @param senhaTemp
	 * @throws Exception
	 */
	private void sendEmailSenhaTemp(String email, String senhaTemp) throws BusinessException {
		try {
			LOGGER.info("Iniciando o envio de email!");
			// preparando o email
			Properties properties = System.getProperties();
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "587");
			// configurando o email
			Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("pibsantalucia@gmail.com", "60slPIB60");
				}
			});
			
			// envio do email.
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("pibsantalucia@gmail.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			message.setSubject("Sistema PIB Santa Lúcia - Senha de cadastro");
			message.setText("Prezado(a), segue a sua senha temporária para utilizar no aplicativo da Igreja: "+senhaTemp);
			Transport.send(message);
			LOGGER.info("Finalizado o envio de email!");
		} catch (Exception exception) {
			// erro: Username and Password not accepted. Learn more at, habilitar:
			//https://myaccount.google.com/lesssecureapps?pli=1
			
			LOGGER.error("Erro enviar email:", exception);
			throw new BusinessException("ERRO.SEND.EMAIL.TEMP");
		}
	}
}
