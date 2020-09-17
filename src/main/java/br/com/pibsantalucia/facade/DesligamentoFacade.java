package br.com.pibsantalucia.facade;

import br.com.pibsantalucia.vo.DesligamentoVO;

public interface DesligamentoFacade {

	/**
	 * 
	 * @param desligamentoVO DesligamentoVO
	 * @throws Exception
	 */
	public void salvar(final DesligamentoVO desligamentoVO) throws Exception;
}
