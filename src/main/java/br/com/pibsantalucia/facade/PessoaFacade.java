package br.com.pibsantalucia.facade;

import java.util.List;

import br.com.pibsantalucia.model.Contato;
import br.com.pibsantalucia.model.Pessoa;
import br.com.pibsantalucia.util.BusinessException;
import br.com.pibsantalucia.vo.ContatoVO;
import br.com.pibsantalucia.vo.DeviceMobileVO;
import br.com.pibsantalucia.vo.PessoaVO;
import br.com.pibsantalucia.vo.SendPushNotificationVO;
import br.com.pibsantalucia.vo.TipoContatoVO;
import br.com.pibsantalucia.vo.ViaCepVO;
import br.com.pibsantalucia.vo.ViewMembroVO;

public interface PessoaFacade {

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<TipoContatoVO> listaTipoContatos() throws Exception;

	/**
	 * 
	 * @param sexo
	 * @return
	 * @throws Exception
	 */
	public int totalMembroPorSexo(final String sexo) throws Exception;

	/**
	 * 
	 * @param pessoaVO
	 * @return
	 * @throws Exception
	 */
	public long salvar(final PessoaVO pessoaVO) throws BusinessException, Exception;

	/**
	 * 
	 * @throws Exception
	 */
	public void carregarFotosCongregados() throws Exception;

	/**
	 * 
	 * @param pesCodigo long
	 * @return PessoaVO
	 * @throws Exception
	 */
	public PessoaVO obterCongregado(long pesCodigo) throws Exception;

	/**
	 * 
	 * @param pesNome
	 * @param pesCodigo
	 * @param pesNome
	 * @param pesFglMembro
	 * @return
	 * @throws Exception
	 */
	public List<ViewMembroVO> listaCongregados(final Long pesCodigo, 
			final String pesNome, final String pesFglMembro) throws Exception;
	
	/**
	 * 
	 * @param pesNome
	 * @param pesCodigo
	 * @param pesNome
	 * @param pesFglMembro
	 * @return
	 * @throws Exception
	 */
	public List<ViewMembroVO> listaAniversariantes(final int mes) throws Exception;

	/**
	 * 
	 * @param cep
	 * @return ViaCepVO
	 * @throws Exception
	 */
	public ViaCepVO buscarCep(String cep) throws Exception;
	
	/**
	 * 
	 * @param pesEmail
	 * @return
	 * @throws Exception
	 */
	public List<ContatoVO> listContatoPorEmail(String pesEmail) throws Exception;
	
	/**
	 * 
	 * @param pessoaVO
	 * @throws Exception
	 */
	public void validarMembro(PessoaVO pessoaVO) throws Exception;
	
	/**
	 * 
	 * @param pesCodigo
	 * @return
	 * @throws Exception
	 */
	public List<Contato> listContatoPorEmail(long pesCodigo) throws Exception;
	
	/**
	 * 
	 * @param deviceMobileVO
	 * @throws Exception
	 */
	public void salvarDevice(final DeviceMobileVO deviceMobileVO) throws Exception;
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<SendPushNotificationVO> listMembrosSendPush() throws Exception;
	
	public List<ViewMembroVO> listAniversariantesHoje() throws Exception;
	
	public void restorePessoa(String script) throws Exception;
	
	public Pessoa obterDadosPessoa(long pesCodigo) throws Exception;
	
	public void restoreFotoPessoa(Pessoa pessoa) throws Exception;
}
