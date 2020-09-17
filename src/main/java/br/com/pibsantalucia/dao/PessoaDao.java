package br.com.pibsantalucia.dao;

import java.util.List;

import br.com.pibsantalucia.model.Contato;
import br.com.pibsantalucia.model.Pessoa;
import br.com.pibsantalucia.model.TipoContato;
import br.com.pibsantalucia.model.ViewContato;
import br.com.pibsantalucia.model.ViewMembro;
import br.com.pibsantalucia.model.ViewMembros;
import br.com.pibsantalucia.vo.SendPushNotificationVO;

public interface PessoaDao {

	/**
	 * 
	 * @return List<TipoContato>
	 * @throws Exception
	 */
	public List<TipoContato> listaTipoContatos() throws Exception;
	
	/**
	 * 
	 * @param tpcCodigo int
	 * @return TipoContato
	 * @throws Exception
	 */
	public TipoContato getTipoContato(int tpcCodigo) throws Exception;

	/**
	 * 
	 * @param pesCodigo long
	 * @return List<ViewContato>
	 * @throws Exception
	 */
	public List<ViewContato> listaContatos(long pesCodigo) throws Exception;

	/**
	 * 
	 * @param sexo
	 * @return
	 * @throws Exception
	 */
	public int totalMembroPorSexo(final String sexo) throws Exception;

	/**
	 * 
	 * @param pesCodigo
	 * @return ViewMembro
	 * @throws Exception
	 */
	public ViewMembro obterCongregado(long pesCodigo) throws Exception;

	/**
	 * 
	 * @param pesNome String
	 * @param pesFglMembro boolean
	 * @return
	 * @throws Exception
	 */
	public List<ViewMembros> listaCongregados(final Long pesCodigo, final String pesNome, final String pesFglMembro) throws Exception;
	
	/**
	 * 
	 * @param mes Lon
	 * @return
	 * @throws Exception
	 */
	public List<ViewMembros> listaAniversariantes(final int mes) throws Exception;

	/**
	 * 
	 * @param pesCodigo long
	 * @return Pessoa
	 * @throws Exception
	 */
	public Pessoa obterDadosPessoa(long pesCodigo) throws Exception;

	/**
	 * 
	 * @param pesCodigo
	 * @param pesFglMembro
	 * @throws Exception
	 */
	public void mudarStatusMembro(long pesCodigo, String pesFglMembro) throws Exception;
	
	/**
	 * 
	 * @param pesEmail
	 * @return boolean
	 * @throws Exception
	 */
	public List<Contato> listContatoPorEmail(String pesEmail) throws Exception;
	
	/**
	 * 
	 * @param pesCodigo
	 * @return
	 * @throws Exception
	 */
	public List<Contato> listContatoPorEmail(long pesCodigo) throws Exception;
	
	/**
	 * 
	 * @param pesCodigo
	 * @param namePhoto
	 * @throws Exception
	 */
	public void updateNamePhoto(long pesCodigo, String namePhoto) throws Exception;
	
	/**
	 * 
	 * @param pesCpf
	 * @return
	 * @throws Exception
	 */
	public List<Pessoa> listPessoaPorCpf(String pesCpf) throws Exception;
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<SendPushNotificationVO> listMembrosSendPush() throws Exception;
	
	public List<ViewMembro> listAniversariantesHoje() throws Exception;
	
	public void restorePessoa(String script) throws Exception;
}
