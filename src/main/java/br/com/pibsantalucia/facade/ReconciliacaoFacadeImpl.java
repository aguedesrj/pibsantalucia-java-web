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
import br.com.pibsantalucia.model.Pessoa;
import br.com.pibsantalucia.model.Reconciliacao;
import br.com.pibsantalucia.model.UsuarioMembro;
import br.com.pibsantalucia.util.Util;
import br.com.pibsantalucia.vo.ReconciliacaoVO;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
public class ReconciliacaoFacadeImpl extends HibernateDao implements ReconciliacaoFacade {

	private final Logger LOGGER = LoggerFactory.getLogger(ReconciliacaoFacadeImpl.class);

	@Autowired
	PessoaDao pessoaDao;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void salvar(final ReconciliacaoVO reconciliacaoVO) throws Exception {
		try {
			Reconciliacao reconciliacao = new Reconciliacao();
			Pessoa pessoa = new Pessoa();
			UsuarioMembro usuario = new UsuarioMembro();

			usuario.setUmeCodigo(reconciliacaoVO.getUsuarioMembroVO().getUmeCodigo());
			pessoa.setPesCodigo(reconciliacaoVO.getPesCodigo());

			reconciliacao.setRecDtReconciliacao(Util.converterStringParaDate(reconciliacaoVO.getRecDtReconciliacao()));
			reconciliacao.setRecObservacao(reconciliacaoVO.getRecObservacao());
			reconciliacao.setRecDtCadastro(Calendar.getInstance());
			reconciliacao.setPessoa(pessoa);
			reconciliacao.setUsuarioMembro(usuario);

			// volta a ser membro.
			pessoaDao.mudarStatusMembro(pessoa.getPesCodigo(), "S");

			getSession().saveOrUpdate(reconciliacao);
			getSession().flush();

		} catch (Exception exception) {
			LOGGER.error("Erro salvar reconciliação do membro.", exception);
			throw new Exception("ERRO.SISTEMA.INDISPONIVEL");
		}
	}
}
