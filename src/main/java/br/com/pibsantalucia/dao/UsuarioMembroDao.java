package br.com.pibsantalucia.dao;

import br.com.pibsantalucia.model.UsuarioMembro;

public interface UsuarioMembroDao {

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
	 * @param senha
	 * @return
	 * @throws Exception
	 */
	public UsuarioMembro login(final String login, final String senha) throws Exception;
	
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
