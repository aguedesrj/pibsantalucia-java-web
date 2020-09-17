package br.com.pibsantalucia.facade;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.pibsantalucia.dao.HibernateDao;
import br.com.pibsantalucia.dao.PessoaDao;
import br.com.pibsantalucia.model.Obito;
import br.com.pibsantalucia.model.Pessoa;
import br.com.pibsantalucia.model.UsuarioMembro;
import br.com.pibsantalucia.util.Util;
import br.com.pibsantalucia.vo.ObitoVO;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
public class ObitoFacadeImpl extends HibernateDao implements ObitoFacade {

	private final Logger LOGGER = LoggerFactory.getLogger(ObitoFacadeImpl.class);

	@Autowired
	PessoaDao pessoaDao;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void salvar(final ObitoVO obitoVO) throws Exception {
		try {
			Pessoa pessoa = new Pessoa();
			Obito obito = new Obito();
			
			UsuarioMembro usuarioMembro = new UsuarioMembro();
			usuarioMembro.setUmeCodigo(obitoVO.getUsuarioMembroVO().getUmeCodigo());

			obito.setObiDtObito(Util.converterStringParaDate(obitoVO.getObiDtObito()));
			obito.setObiCausa(obitoVO.getObiCausa());
			obito.setObiObservacao(obitoVO.getObiObservacao());
			obito.setObiDtCadastro(Calendar.getInstance());
			obito.setUsuarioMembro(usuarioMembro);
			
			pessoa.setPesCodigo(obitoVO.getPesCodigo());
			obito.setPessoa(pessoa);

			// deixa de ser membro.
			pessoaDao.mudarStatusMembro(obitoVO.getPesCodigo(), "N");

			getSession().saveOrUpdate(obito);
			getSession().flush();

		} catch (Exception exception) {
			LOGGER.error("Erro salvar óbito.", exception);
			throw new Exception("ERRO.SISTEMA.INDISPONIVEL");
		}
	}
}
