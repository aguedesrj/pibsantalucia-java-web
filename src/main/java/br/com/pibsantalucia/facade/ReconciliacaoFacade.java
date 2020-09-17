package br.com.pibsantalucia.facade;

import br.com.pibsantalucia.vo.ReconciliacaoVO;

public interface ReconciliacaoFacade {

	/**
	 * 
	 * @param reconciliacaoVO ReconciliacaoVO
	 * @throws Exception
	 */
	public void salvar(final ReconciliacaoVO reconciliacaoVO) throws Exception;
}
