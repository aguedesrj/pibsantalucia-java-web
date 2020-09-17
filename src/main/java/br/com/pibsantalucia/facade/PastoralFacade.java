package br.com.pibsantalucia.facade;

import java.util.List;

import br.com.pibsantalucia.vo.PastoralVO;

public interface PastoralFacade {

	/**
	 * 
	 * @param pastoralVO
	 * @throws Exception
	 */
	public void salvar(final PastoralVO pastoralVO) throws Exception;
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<PastoralVO> lista() throws Exception;
	
	/**
	 * 
	 * @param pasCodigo
	 * @return
	 * @throws Exception
	 */
	public PastoralVO obterPorId(long pasCodigo) throws Exception;
	
	/**
	 * 
	 * @param pasReferencia
	 * @return
	 * @throws Exception
	 */
	public PastoralVO obterPorDataReferencia(String pasReferencia) throws Exception;
}
