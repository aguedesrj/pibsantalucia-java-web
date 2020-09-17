package br.com.pibsantalucia.facade;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import br.com.pibsantalucia.dao.HibernateDao;
import br.com.pibsantalucia.dao.PessoaDao;
import br.com.pibsantalucia.model.Contato;
import br.com.pibsantalucia.model.DeviceMobile;
import br.com.pibsantalucia.model.Endereco;
import br.com.pibsantalucia.model.Pessoa;
import br.com.pibsantalucia.model.TipoContato;
import br.com.pibsantalucia.model.UsuarioMembro;
import br.com.pibsantalucia.model.ViewContato;
import br.com.pibsantalucia.model.ViewMembro;
import br.com.pibsantalucia.model.ViewMembros;
import br.com.pibsantalucia.util.BusinessException;
import br.com.pibsantalucia.util.Constantes;
import br.com.pibsantalucia.util.Util;
import br.com.pibsantalucia.vo.ContatoVO;
import br.com.pibsantalucia.vo.DeviceMobileVO;
import br.com.pibsantalucia.vo.EnderecoVO;
import br.com.pibsantalucia.vo.PessoaVO;
import br.com.pibsantalucia.vo.SendPushNotificationVO;
import br.com.pibsantalucia.vo.TipoContatoVO;
import br.com.pibsantalucia.vo.ViaCepVO;
import br.com.pibsantalucia.vo.ViewMembroVO;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
public class PessoaFacadeImpl extends HibernateDao implements PessoaFacade {

	private final Logger LOGGER = LoggerFactory.getLogger(PessoaFacade.class);

	@Autowired
	private PessoaDao pessoaDao;

	/*
	 * (non-Javadoc)
	 * @see br.com.pibsantalucia.facade.PessoaFacade#totalMembroPorSexo(java.lang.String)
	 */
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public int totalMembroPorSexo(final String sexo) throws Exception {
		return pessoaDao.totalMembroPorSexo(sexo);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<ViewMembroVO> listaCongregados(final Long pesCodigo, final String pesNome, final String pesFglMembro) throws Exception {
		List<ViewMembros> list = pessoaDao.listaCongregados(pesCodigo, pesNome, pesFglMembro);
		if (list != null) {
			List<ViewMembroVO> listaRetorno = new ArrayList<ViewMembroVO>();
			for (ViewMembros viewMembros : list) {
				ViewMembroVO viewMembroVO = new ViewMembroVO();

				viewMembroVO.setPesCodigo(viewMembros.getPesCodigo());
				viewMembroVO.setPesNome(viewMembros.getPesNome());
				viewMembroVO.setPesFotoPath(Util.getPathPhotoCongregado(viewMembros.getPesCodigo(), viewMembros.getPesNamePhoto()));
				viewMembroVO.setPesSexo(viewMembros.getPesSexo());
				viewMembroVO.setPesFglMembro(viewMembros.isPesFglMembro());
				viewMembroVO.setPesObito(viewMembros.isPesObito());
				viewMembroVO.setPesDesligamento(viewMembros.isPesDesligamento());
				viewMembroVO.setPesTransferencia(viewMembros.isPesTransferencia());
				
				listaRetorno.add(viewMembroVO);
			}
			return listaRetorno;
		}
		return null;
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<ViewMembroVO> listaAniversariantes(final int mes) throws Exception {
		List<ViewMembros> list = pessoaDao.listaAniversariantes(mes);
		if (list != null) {
			List<ViewMembroVO> listaRetorno = new ArrayList<ViewMembroVO>();
			for (ViewMembros viewMembros : list) {
				ViewMembroVO viewMembroVO = new ViewMembroVO();

				viewMembroVO.setPesCodigo(viewMembros.getPesCodigo());
				viewMembroVO.setPesNome(viewMembros.getPesNome());
				viewMembroVO.setPesFotoPath(Util.getPathPhotoCongregado(viewMembros.getPesCodigo(), viewMembros.getPesNamePhoto()));
				viewMembroVO.setAniversarioFmt(Util.getAniversario(viewMembros.getPesDtNascimento()));
				viewMembroVO.setDtAniversarioFmt(Util.getAniversarioNew(viewMembros.getPesDtNascimento()));
				
				listaRetorno.add(viewMembroVO);
			}
			return listaRetorno;
		}
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.pibsantalucia.facade.PessoaFacade#listaTipoContatos()
	 */
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<TipoContatoVO> listaTipoContatos() throws Exception {
		List<TipoContato> list = pessoaDao.listaTipoContatos();
		if (list != null) {
			List<TipoContatoVO> listaRetorno = new ArrayList<TipoContatoVO>();
			for (TipoContato tipoContato : list) {
				TipoContatoVO tipoContatoVO = new TipoContatoVO();
				tipoContatoVO.setTpcCodigo(tipoContato.getTpcCodigo());
				tipoContatoVO.setTpcDescricao(tipoContato.getTpcDescricao());

				listaRetorno.add(tipoContatoVO);
			}
			return listaRetorno;
		}
		return null;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public long salvar(final PessoaVO pessoaVO) throws BusinessException, Exception {
		try {
			
			Pessoa pessoa = new Pessoa();
			pessoa.setUsuarioMembro(new UsuarioMembro());
			pessoa.getUsuarioMembro().setUmeCodigo(pessoaVO.getUsuarioMembroVO().getUmeCodigo());

			// dados do endereço.
			Endereco endereco = new Endereco();
			endereco.setEndCodigo(pessoaVO.getEnderecoVO().getEndCodigo());
			endereco.setEndLogradouro(pessoaVO.getEnderecoVO().getEndLogradouro());
			endereco.setEndComplemento(pessoaVO.getEnderecoVO().getEndComplemento());
			endereco.setEndNumero(pessoaVO.getEnderecoVO().getEndNumero());
			endereco.setEndBairro(pessoaVO.getEnderecoVO().getEndBairro());
			endereco.setEndCidade(pessoaVO.getEnderecoVO().getEndCidade());
			endereco.setEndCep(pessoaVO.getEnderecoVO().getEndCep());
			endereco.setEndEstado(pessoaVO.getEnderecoVO().getEndEstado());
			
			getSession().saveOrUpdate(endereco);

			pessoa.setPesCodigo(pessoaVO.getPesCodigo());
			pessoa.setPesNome(pessoaVO.getPesNome());
			pessoa.setPesDtNascimento(Util.converterStringParaDate(pessoaVO.getPesDtNascimento()));
			pessoa.setPesSexo(pessoaVO.getPesSexo());
			pessoa.setPesCpf(Util.somenteNumeros(pessoaVO.getPesCpf()));
			pessoa.setPesTipoSanguineo(pessoaVO.getPesTipoSanguineo());
			pessoa.setPesNaturalidade(pessoaVO.getPesNaturalidade());
			pessoa.setPesNacionalidade(pessoaVO.getPesNacionalidade());
			pessoa.setPesNomeMae(pessoaVO.getPesNomeMae());
			pessoa.setPesNomePai(pessoaVO.getPesNomePai());
			pessoa.setPesDtCasamento(Util.converterStringParaDate(pessoaVO.getPesDtCasamento()));
			pessoa.setEndereco(endereco);
			pessoa.setPesFglMembro(pessoaVO.getPesFglMembro());
			pessoa.setPesProfissao(pessoaVO.getPesProfissao());
			
			// pega a foto da base.
			try {
				ViewMembro viewMembro = pessoaDao.obterCongregado(pessoa.getPesCodigo());
				if (viewMembro != null && viewMembro.getPesFoto() != null) {
					pessoa.setPesFoto(viewMembro.getPesFoto());
				}
			} catch (Exception e) {
				LOGGER.info("Pessoa ainda sem cadasto.");
			}
			if (pessoaVO.getPesFoto() != null && Base64.isBase64(pessoaVO.getPesFoto())) {
				pessoa.setPesFoto(Base64.decodeBase64(pessoaVO.getPesFoto()));
			}
			pessoa.setPesObservacao(pessoaVO.getPesObservacao());
			pessoa.setPesDtBatismo(Util.converterStringParaDate(pessoaVO.getPesDtBatismo()));
			pessoa.setPesDtCadastro(Calendar.getInstance());

			getSession().saveOrUpdate(pessoa);

			// salvar contatos.
			if (pessoaVO.getListaContatoVO() != null) {
				for (ContatoVO contatoVO : pessoaVO.getListaContatoVO()) {					
					Contato contato = new Contato();
					if (contatoVO.isExcluidoContato() && !contatoVO.isNovoContato()) {
						contato.setCtoCodigo(contatoVO.getCtoCodigo());
						getSession().remove(contato);
					} else if ((contatoVO.isNovoContato() && !contatoVO.isExcluidoContato()) || 
							(!contatoVO.isNovoContato() && !contatoVO.isExcluidoContato())) {
						
						if (contatoVO.isNovoContato()) {
							// novo contato.
							contato.setCtoCodigo(0);
						} else {
							contato.setCtoCodigo(contatoVO.getCtoCodigo());
						}
						contato.setCtoDdd(contatoVO.getCtoDdd());
						contato.setCtoNumeroTelefone(contatoVO.getCtoNumeroTelefone());
						contato.setCtoDescricaoEmail(contatoVO.getCtoDescricaoEmail());
						contato.setPessoa(pessoa);
						// obter o tipo de contato.
						contato.setTipoContato(pessoaDao.getTipoContato(contatoVO.getTipoContatoVO().getTpcCodigo()));
						contato.getTipoContato().setTpcCodigo(contatoVO.getTipoContatoVO().getTpcCodigo());

						getSession().saveOrUpdate(contato);
					}
				}
			}
			getSession().flush();
			// carrega foto...
			if (pessoa.getPesFoto() != null) {
				String namePhoto = Util.gerarNomeFotoCongregado();
				pessoaDao.updateNamePhoto(pessoa.getPesCodigo(), namePhoto);
				
				Util.carregaFotosCongregados(pessoa.getPesCodigo(), pessoa.getPesFoto(), namePhoto);
			}
			return pessoa.getPesCodigo();
		} catch (Exception exception) {
			LOGGER.error("Erro salvar os dados da pessoa.", exception);
			if (exception instanceof BusinessException) {
				throw exception;
			}
			throw new Exception("ERRO.SISTEMA.INDISPONIVEL");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.pibsantalucia.facade.PessoaFacade#carregarFotosCongregados()
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void carregarFotosCongregados() throws Exception {
		List<ViewMembros> list = pessoaDao.listaCongregados(null, null, null);
		if (list != null) {
			for (ViewMembros viewMembros : list) {
				if (viewMembros.getPesFoto() != null) {
					String namePhoto = Util.gerarNomeFotoCongregado();
					
					pessoaDao.updateNamePhoto(viewMembros.getPesCodigo(), namePhoto);
					
					Util.carregaFotosCongregados(viewMembros.getPesCodigo(), viewMembros.getPesFoto(), namePhoto);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.pibsantalucia.facade.PessoaFacade#obterCongregado(long)
	 */
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public PessoaVO obterCongregado(long pesCodigo) throws Exception {
		ViewMembro viewMembro = pessoaDao.obterCongregado(pesCodigo);
		PessoaVO pessoaVO = new PessoaVO();

		// dados do endereço.
		EnderecoVO enderecoVO = new EnderecoVO();
		enderecoVO.setEndCodigo(viewMembro.getEndCodigo());
		enderecoVO.setEndLogradouro(viewMembro.getEndLogradouro());
		enderecoVO.setEndComplemento(viewMembro.getEndComplemento());
		enderecoVO.setEndNumero(viewMembro.getEndNumero());
		enderecoVO.setEndBairro(viewMembro.getEndBairro());
		enderecoVO.setEndCidade(viewMembro.getEndCidade());
		enderecoVO.setEndCep(viewMembro.getEndCep());
		enderecoVO.setEndEstado(viewMembro.getEndEstado());

		// dados do membro.
		pessoaVO.setPesCodigo(viewMembro.getPesCodigo());
		pessoaVO.setPesNome(viewMembro.getPesNome());
		pessoaVO.setPesDtNascimento(Util.converterDateParaString(viewMembro.getPesDtNascimento()));
		pessoaVO.setPesSexo(viewMembro.getPesSexo());
		pessoaVO.setPesCpf(Util.somenteNumeros(viewMembro.getPesCpf()));
		pessoaVO.setPesTipoSanguineo(viewMembro.getPesTipoSanguineo());
		pessoaVO.setPesNaturalidade(viewMembro.getPesNaturalidade());
		pessoaVO.setPesNacionalidade(viewMembro.getPesNacionalidade());
		pessoaVO.setPesNomeMae(viewMembro.getPesNomeMae());
		pessoaVO.setPesNomePai(viewMembro.getPesNomePai());
		pessoaVO.setPesDtCasamento(Util.converterDateParaString(viewMembro.getPesDtCasamento()));
		pessoaVO.setEnderecoVO(enderecoVO);
		pessoaVO.setPesFglMembro(viewMembro.isPesFglMembro());
		pessoaVO.setPesProfissao(viewMembro.getPesProfissao());
		pessoaVO.setPesObservacao(viewMembro.getPesObservacao());
		pessoaVO.setPesDtBatismo(Util.converterDateParaString(viewMembro.getPesDtBatismo()));
		pessoaVO.setPesFotoPath(Util.getPathPhotoCongregado(viewMembro.getPesCodigo(), viewMembro.getPesNamePhoto()));

		// dados de contato.
		List<ViewContato> listaContatos = pessoaDao.listaContatos(viewMembro.getPesCodigo());
		if (listaContatos != null && listaContatos.size() > 0) {
			pessoaVO.setListaContatoVO(new ArrayList<ContatoVO>());
			for (ViewContato viewContato : listaContatos) {
				ContatoVO contatoVO = new ContatoVO();
				contatoVO.setCtoCodigo(viewContato.getCtoCodigo());
				contatoVO.setCtoDdd(viewContato.getCtoDdd());
				contatoVO.setCtoDescricaoEmail(viewContato.getCtoDescricaoEmail());
				contatoVO.setCtoNumeroTelefone(viewContato.getCtoNumeroTelefone());
				contatoVO.setTipoContatoVO(new TipoContatoVO());
				contatoVO.getTipoContatoVO().setTpcCodigo(viewContato.getTpcCodigo());
				contatoVO.getTipoContatoVO().setTpcDescricao(viewContato.getTpcDescricao());
				pessoaVO.getListaContatoVO().add(contatoVO);
			}
		}
		return pessoaVO;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.pibsantalucia.facade.PessoaFacade#buscarCep(java.lang.String)
	 */
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public ViaCepVO buscarCep(String cep) throws Exception {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("https://viacep.com.br/ws/");
		stringBuilder.append(Util.somenteNumeros(cep));
		stringBuilder.append("/json/");

		URL url = new URL(stringBuilder.toString());
		HttpsURLConnection request = (HttpsURLConnection) url.openConnection();
		request.connect();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		JsonElement jsonElement = new JsonParser().parse(bufferedReader);
		Gson gson = new GsonBuilder().create();
		ViaCepVO viaCepVO = gson.fromJson(jsonElement, ViaCepVO.class);
		viaCepVO.setEstado(Util.setEstadoPorUf(viaCepVO.getUf()));
		return viaCepVO;
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<ContatoVO> listContatoPorEmail(String pesEmail) throws Exception {
		List<ContatoVO> listReturn = new ArrayList<ContatoVO>();
		List<Contato> list = pessoaDao.listContatoPorEmail(pesEmail);
		for (Contato contato: list) {
			ContatoVO contatoVO = new ContatoVO();
			contatoVO.setCtoDescricaoEmail(contato.getCtoDescricaoEmail());
			contatoVO.setPesCodigo(contato.getPessoa().getPesCodigo());
			
			listReturn.add(contatoVO);
		}
		return listReturn;
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public void validarMembro(PessoaVO pessoaVO) throws Exception {
		
		// validar CPF já cadastrado.
		String pesCpf = Util.somenteNumeros(pessoaVO.getPesCpf());
		if (pesCpf != null && !pesCpf.isEmpty()) {
			List<Pessoa> listPessoas = pessoaDao.listPessoaPorCpf(pesCpf);
			for (Pessoa pessoa: listPessoas) {
				if (pessoa.getPesCodigo() != pessoaVO.getPesCodigo()) {
					// CPF já cadastrado
					throw new BusinessException(Constantes.ERRO_CPF_MEMBRO_JA_CADASTRADO + pessoa.getPesNome());
				}
			}
		}
		
		// validar EMAIL já cadastrado
		if (pessoaVO.getListaContatoVO() != null) {
			for (ContatoVO contatoVO : pessoaVO.getListaContatoVO()) {
				if (contatoVO.getCtoDescricaoEmail() != null && !contatoVO.getCtoDescricaoEmail().isEmpty()) {
					List<Contato> listEmails = pessoaDao.listContatoPorEmail(contatoVO.getCtoDescricaoEmail());
					for (Contato contato: listEmails) {
						if (contato.getPessoa().getPesCodigo() != pessoaVO.getPesCodigo()) {
							// Email já cadastrado
							throw new BusinessException(Constantes.ERRO_EMAIL_JA_CADASTRADO + contato.getCtoDescricaoEmail());
						}
					}
				}
			}
		}
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Contato> listContatoPorEmail(long pesCodigo) throws Exception {
		return pessoaDao.listContatoPorEmail(pesCodigo);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void salvarDevice(final DeviceMobileVO deviceMobileVO) throws Exception {
		try {
			DeviceMobile deviceMobile = new DeviceMobile();
			deviceMobile.setDemId(deviceMobileVO.getDemId());
			deviceMobile.setDemOs(deviceMobileVO.getDemOs());
			deviceMobile.setDemVersao(deviceMobileVO.getDemVersao());
			deviceMobile.setDemTokenFirebase(deviceMobileVO.getDemTokenFirebase());
			deviceMobile.setDemDtCadastro(Calendar.getInstance());
			
			Pessoa pessoa = new Pessoa();
			pessoa.setPesCodigo(deviceMobileVO.getPesCodigo());
			deviceMobile.setPessoa(pessoa);
			
			getSession().saveOrUpdate(deviceMobile);
			getSession().flush();
			
		} catch (Exception exception) {
			LOGGER.error("Erro salvar device.", exception);
			throw new Exception("ERRO.SISTEMA.INDISPONIVEL");
		}
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<SendPushNotificationVO> listMembrosSendPush() throws Exception {
		return pessoaDao.listMembrosSendPush();
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<ViewMembroVO> listAniversariantesHoje() throws Exception {
		List<ViewMembroVO> listReturn = new ArrayList<ViewMembroVO>();
		List<ViewMembro> list = pessoaDao.listAniversariantesHoje();
		for (ViewMembro viewMembro: list) {
			ViewMembroVO viewMembroVO = new ViewMembroVO();
			
			viewMembroVO.setPesCodigo(viewMembro.getPesCodigo());
			viewMembroVO.setPesNome(viewMembro.getPesNome());
			viewMembroVO.setPesSexo(viewMembro.getPesSexo());
			
			listReturn.add(viewMembroVO);
		}
		return listReturn;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void restorePessoa(String script) throws Exception {
		pessoaDao.restorePessoa(script);
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Pessoa obterDadosPessoa(long pesCodigo) throws Exception {
		return pessoaDao.obterDadosPessoa(pesCodigo);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void restoreFotoPessoa(Pessoa pessoa) throws Exception {
		getSession().saveOrUpdate(pessoa);
	}
}
