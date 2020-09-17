package br.com.pibsantalucia.facade;

import java.util.List;
import br.com.pibsantalucia.vo.AnuncioVO;

public interface AnuncioFacade {

	/**
	 * 
	 * @param escDtLimite
	 * @return
	 * @throws Exception
	 */
	public List<AnuncioVO> lista() throws Exception;
	
	/**
	 * 
	 * @param anuncioVO
	 * @throws Exception
	 */
	public void salvar(AnuncioVO anuncioVO) throws Exception;
}
