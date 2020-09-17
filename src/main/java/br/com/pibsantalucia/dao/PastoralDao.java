package br.com.pibsantalucia.dao;

import java.util.List;

import br.com.pibsantalucia.model.Pastoral;

public interface PastoralDao {

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Pastoral> lista() throws Exception;
	
	/**
	 * 
	 * @param pasCodigo
	 * @return
	 * @throws Exception
	 */
	public Pastoral obterPorId(long pasCodigo) throws Exception;
	
	/**
	 * 
	 * @param pasReferencia
	 * @return
	 * @throws Exception
	 */
	public Pastoral obterPorDataReferencia(String pasReferencia) throws Exception;
}
