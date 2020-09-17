package br.com.pibsantalucia.dao;

import java.util.List;

import br.com.pibsantalucia.model.Anuncio;

public interface AnuncioDao {

	/**
	 * 
	 * @param anuDtLimite
	 * @return
	 * @throws Exception
	 */
	public List<Anuncio> lista() throws Exception;
}
