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
import br.com.pibsantalucia.model.Desligamento;
import br.com.pibsantalucia.model.Pessoa;
import br.com.pibsantalucia.model.UsuarioMembro;
import br.com.pibsantalucia.util.Util;
import br.com.pibsantalucia.vo.DesligamentoVO;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
public class DesligamentoFacadeImpl extends HibernateDao implements DesligamentoFacade {

	private final Logger LOGGER = LoggerFactory.getLogger(DesligamentoFacadeImpl.class);

	@Autowired
	PessoaDao pessoaDao;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void salvar(final DesligamentoVO desligamentoVO) throws Exception {
		try {
			Desligamento desligamento = new Desligamento();
			Pessoa pessoa = new Pessoa();
			UsuarioMembro usuario = new UsuarioMembro();

			usuario.setUmeCodigo(desligamentoVO.getUsuarioMembroVO().getUmeCodigo());
			pessoa.setPesCodigo(desligamentoVO.getPesCodigo());

			desligamento.setDesDtDesligamento(Util.converterStringParaDate(desligamentoVO.getDesDtDesligamento()));
			desligamento.setDesObservacao(desligamentoVO.getDesObservacao());
			desligamento.setDesDtCadastro(Calendar.getInstance());
			desligamento.setPessoa(pessoa);
			desligamento.setUsuarioMembro(usuario);

			// deixa de ser membro.
			pessoaDao.mudarStatusMembro(pessoa.getPesCodigo(), "N");

			getSession().saveOrUpdate(desligamento);
			getSession().flush();

		} catch (Exception exception) {
			LOGGER.error("Erro salvar desligamento do membro.", exception);
			throw new Exception("ERRO.SISTEMA.INDISPONIVEL");
		}
	}
}
