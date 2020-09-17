package br.com.pibsantalucia.facade;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import br.com.pibsantalucia.dao.BackupDao;
import br.com.pibsantalucia.dao.HibernateDao;
import br.com.pibsantalucia.model.Anuncio;
import br.com.pibsantalucia.model.Contato;
import br.com.pibsantalucia.model.Desligamento;
import br.com.pibsantalucia.model.DeviceMobile;
import br.com.pibsantalucia.model.Endereco;
import br.com.pibsantalucia.model.Escala;
import br.com.pibsantalucia.model.EscalaMembro;
import br.com.pibsantalucia.model.Obito;
import br.com.pibsantalucia.model.Pastoral;
import br.com.pibsantalucia.model.Perfil;
import br.com.pibsantalucia.model.Pessoa;
import br.com.pibsantalucia.model.Reconciliacao;
import br.com.pibsantalucia.model.Transferencia;
import br.com.pibsantalucia.model.UsuarioMembro;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
public class BackupFacadeImpl extends HibernateDao implements BackupFacade {

	@Autowired
	BackupDao backupDao;

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Endereco> listaEndereco() throws Exception {
		return backupDao.listaEndereco();
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Obito> listaObito() throws Exception {
		return backupDao.listaObito();
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Desligamento> listaDesligamento() throws Exception {
		return backupDao.listaDesligamento();
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Contato> listaContato() throws Exception {
		return backupDao.listaContato();
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Pessoa> listaPessoa() throws Exception {
		return backupDao.listaPessoa();
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<UsuarioMembro> listaUsuarioMembro() throws Exception {
		return backupDao.listaUsuarioMembro();
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Transferencia> listaTransferencia() throws Exception {
		return backupDao.listaTransferencia();
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Reconciliacao> listaReconciliacao() throws Exception {
		return backupDao.listaReconciliacao();
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Perfil> listaPerfil() throws Exception {
		return backupDao.listaPerfil();
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<DeviceMobile> listaDevice() throws Exception {
		return backupDao.listaDevice();
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Pastoral> listaPastoral() throws Exception {
		return backupDao.listaPastoral();
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Escala> listaEscala() throws Exception {
		return backupDao.listaEscala();
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<EscalaMembro> listaEscalaMembro() throws Exception {
		return backupDao.listaEscalaMembro();
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Anuncio> listaAnuncio() throws Exception {
		return backupDao.listaAnuncio();
	}
}
