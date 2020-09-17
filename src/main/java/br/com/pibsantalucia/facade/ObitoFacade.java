package br.com.pibsantalucia.facade;

import br.com.pibsantalucia.vo.ObitoVO;

public interface ObitoFacade {

	/**
	 * 
	 * @param obitoVO
	 * @throws Exception
	 */
	public void salvar(final ObitoVO obitoVO) throws Exception;
}
