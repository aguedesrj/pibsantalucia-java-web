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
import br.com.pibsantalucia.model.Transferencia;
import br.com.pibsantalucia.model.UsuarioMembro;
import br.com.pibsantalucia.util.Util;
import br.com.pibsantalucia.vo.TransferenciaVO;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
public class TransferenciaFacadeImpl extends HibernateDao implements TransferenciaFacade {

	private final Logger LOGGER = LoggerFactory.getLogger(TransferenciaFacadeImpl.class);

	@Autowired
	PessoaDao pessoaDao;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void salvar(final TransferenciaVO transferenciaVO) throws Exception {
		try {
			Transferencia transferencia = new Transferencia();
			Pessoa pessoa = new Pessoa();
			UsuarioMembro usuario = new UsuarioMembro();

			usuario.setUmeCodigo(transferenciaVO.getUsuarioMembroVO().getUmeCodigo());
			pessoa.setPesCodigo(transferenciaVO.getPesCodigo());

			transferencia.setTraDtTransferencia(Util.converterStringParaDate(transferenciaVO.getTraDtTransferencia()));
			transferencia.setTraMotivo(transferenciaVO.getTraMotivo());
			transferencia.setTraNomeIgreja(transferenciaVO.getTraNomeIgreja());
			transferencia.setTraObservacao(transferenciaVO.getTraObservacao());
			transferencia.setTraStatus(transferenciaVO.getTraStatus());
			transferencia.setTraDtCadastro(Calendar.getInstance());
			transferencia.setPessoa(pessoa);
			transferencia.setUsuarioMembro(usuario);

			// deixa de ser membro.
			if (transferencia.getTraStatus().equals("E")) { //Entrada
				pessoaDao.mudarStatusMembro(pessoa.getPesCodigo(), "S");
			} else if (transferencia.getTraStatus().equals("S")) { //Saída
				pessoaDao.mudarStatusMembro(pessoa.getPesCodigo(), "N");
			} else {
				throw new Exception("ERRO.SISTEMA.INDISPONIVEL");
			}

			getSession().saveOrUpdate(transferencia);
			getSession().flush();

		} catch (Exception exception) {
			LOGGER.error("Erro salvar transferência do membro.", exception);
			throw new Exception("ERRO.SISTEMA.INDISPONIVEL");
		}
	}
}
