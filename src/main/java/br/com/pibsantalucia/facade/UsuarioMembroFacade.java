package br.com.pibsantalucia.facade;

import java.util.List;

import br.com.pibsantalucia.model.Contato;
import br.com.pibsantalucia.model.UsuarioMembro;
import br.com.pibsantalucia.vo.UsuarioMembroVO;

public interface UsuarioMembroFacade {

	/**
	 * 
	 * @param usuarioMembro
	 * @throws Exception
	 */
	public void salvar(final UsuarioMembro usuarioMembro) throws Exception;
	
	/**
	 * 
	 * @param usuarioMembro
	 * @param senhaTemp
	 * @throws Exception
	 */
	public void salvarComEnvioSenhaTemp(UsuarioMembro usuarioMembro, List<Contato> listEmails) throws Exception;
	
	/**
	 * 
	 * @param login
	 * @param senha
	 * @return
	 * @throws Exception
	 */
	public UsuarioMembroVO login(String login, String senha) throws Exception;
	
	/**
	 * 
	 * @param pesCodigo
	 * @return
	 * @throws Exception
	 */
	public UsuarioMembro obterUsuarioMembro(long pesCodigo) throws Exception;
	
	/**
	 * 
	 * @param login
	 * @return
	 * @throws Exception
	 */
	public UsuarioMembro dadosLoginExiste(final String login) throws Exception;
	
	/**
	 * 
	 * @param login
	 * @return
	 * @throws Exception
	 */
	public UsuarioMembro verificaLoginExistente(final String login) throws Exception;
}
