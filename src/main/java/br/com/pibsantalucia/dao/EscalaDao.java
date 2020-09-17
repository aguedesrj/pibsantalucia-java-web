package br.com.pibsantalucia.dao;

import java.util.List;
import br.com.pibsantalucia.model.Escala;
import br.com.pibsantalucia.model.ViewEscalaMembro;

public interface EscalaDao {

	/**
	 * 
	 * @param escala
	 * @return
	 * @throws Exception
	 */
	public List<Escala> getEscalaPorTipoAndAnoMes(Escala escala) throws Exception;
	
	/**
	 * 
	 * @param escAnoMes
	 * @param escFlgTipo
	 * @return
	 * @throws Exception
	 */
	public List<Escala> lista(int escAnoMes, int escFlgTipo) throws Exception;
	
	/**
	 * 
	 * @param escCodigo long
	 * @return List<ViewEscalaMembro>
	 * @throws Exception
	 */
	public List<ViewEscalaMembro> listaEscalaMembro(long escCodigo) throws Exception;
	
	/**
	 * 
	 * @param escCodigo
	 * @throws Exception
	 */
	public void deletarPorEscala(long escCodigo) throws Exception;
	
	/**
	 * 
	 * @param escCodigo
	 * @param pesCodigo
	 * @throws Exception
	 */
	public void insertPorEscala(long escCodigo, long pesCodigo) throws Exception;
}
