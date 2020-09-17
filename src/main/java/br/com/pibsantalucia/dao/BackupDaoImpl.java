package br.com.pibsantalucia.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

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

@Repository
public class BackupDaoImpl extends HibernateDao implements BackupDao {

	public List<Endereco> listaEndereco() throws Exception {
		return getSession().createQuery("from Endereco", Endereco.class).getResultList();
	}
	
	public List<Obito> listaObito() throws Exception {
		return getSession().createQuery("from Obito", Obito.class).getResultList();
	}
	
	public List<Desligamento> listaDesligamento() throws Exception {
		return getSession().createQuery("from Desligamento", Desligamento.class).getResultList();
	}
	
	public List<Contato> listaContato() throws Exception {
		return getSession().createQuery("from Contato", Contato.class).getResultList();
	}
	
	public List<Pessoa> listaPessoa() throws Exception {
		return getSession().createQuery("from Pessoa", Pessoa.class).getResultList();
	}
	
	public List<Transferencia> listaTransferencia() throws Exception {
		return getSession().createQuery("from Transferencia", Transferencia.class).getResultList();
	}
	
	public List<UsuarioMembro> listaUsuarioMembro() throws Exception {
		return getSession().createQuery("from UsuarioMembro", UsuarioMembro.class).getResultList();
	}
	
	public List<Reconciliacao> listaReconciliacao() throws Exception {
		return getSession().createQuery("from Reconciliacao", Reconciliacao.class).getResultList();
	}
	
	public List<Perfil> listaPerfil() throws Exception {
		return getSession().createQuery("from Perfil", Perfil.class).getResultList();
	}
	
	public List<DeviceMobile> listaDevice() throws Exception {
		return getSession().createQuery("from DeviceMobile", DeviceMobile.class).getResultList();
	}
	
	public List<Pastoral> listaPastoral() throws Exception {
		return getSession().createQuery("from Pastoral", Pastoral.class).getResultList();
	}
	
	public List<Escala> listaEscala() throws Exception {
		return getSession().createQuery("from Escala", Escala.class).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<EscalaMembro> listaEscalaMembro() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ESC_CODIGO, PES_CODIGO ");
		sql.append("FROM TBL_ESCALA_MEMBROS ");

		List<EscalaMembro> listReturn = new ArrayList<EscalaMembro>();
		List<Object[]> list = getSession().createNativeQuery(sql.toString()).getResultList();
		for (Object[] item: list) {
			Integer escCodigo = (Integer) item[0];
			Integer pesCodigo = (Integer) item[1];
			
			EscalaMembro escalaMembro = new EscalaMembro();
			escalaMembro.setEscCodigo((long) escCodigo);
			escalaMembro.setPesCodigo((long) pesCodigo);
			
			listReturn.add(escalaMembro);
		}
		return listReturn;
	}
	
	public List<Anuncio> listaAnuncio() throws Exception {
		return getSession().createQuery("from Anuncio", Anuncio.class).getResultList();
	}
}
