package br.com.pibsantalucia.facade;

import java.util.List;

import br.com.pibsantalucia.model.Escala;
import br.com.pibsantalucia.vo.EscalaMesesVO;

public interface EscalaFacade {

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<EscalaMesesVO> lista(List<EscalaMesesVO> listaEscalaMeses, int escFlgTipo) throws Exception;
	
	/**
	 * 
	 * @param lista
	 * @throws Exception
	 */
	public void salvarEscalarFuturas(List<Escala> lista) throws Exception;
	
	/**
	 * 
	 * @param escCodigo
	 * @throws Exception
	 */
	public void salvar(List<EscalaMesesVO> lista) throws Exception;
}
