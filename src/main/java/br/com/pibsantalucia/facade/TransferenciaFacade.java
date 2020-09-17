package br.com.pibsantalucia.facade;

import br.com.pibsantalucia.vo.TransferenciaVO;

public interface TransferenciaFacade {

	/**
	 * 
	 * @param transferenciaVO TransferenciaVO
	 * @throws Exception
	 */
	public void salvar(final TransferenciaVO transferenciaVO) throws Exception;
}
