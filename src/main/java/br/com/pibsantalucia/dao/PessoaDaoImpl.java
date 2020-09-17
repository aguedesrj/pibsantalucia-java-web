package br.com.pibsantalucia.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.pibsantalucia.model.Contato;
import br.com.pibsantalucia.model.Pessoa;
import br.com.pibsantalucia.model.TipoContato;
import br.com.pibsantalucia.model.ViewContato;
import br.com.pibsantalucia.model.ViewMembro;
import br.com.pibsantalucia.model.ViewMembros;
import br.com.pibsantalucia.vo.SendPushNotificationVO;

@Repository
public class PessoaDaoImpl extends HibernateDao implements PessoaDao {

	/*
	 * (non-Javadoc)
	 * @see br.com.pibsantalucia.dao.PessoaDao#mudarStatusMembro(long, String)
	 */
	public void mudarStatusMembro(long pesCodigo, String pesFglMembro) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE TBL_PESSOA");
		sql.append(" SET PES_FLGMEMBRO = '" + pesFglMembro + "'");
		sql.append(" WHERE PES_CODIGO = " + pesCodigo);
		getSession().createNativeQuery(sql.toString()).executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<Contato> listContatoPorEmail(String pesEmail) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT C.PES_CODIGO, C.CTO_DESCRICAOEMAIL ");
		sql.append("FROM TBL_CONTATO C, TBL_TIPOCONTATO T ");
		sql.append("WHERE C.TPC_CODIGO = T.TPC_CODIGO ");
		sql.append("AND T.TPC_CODIGO = 1 ");
		sql.append("AND upper(CTO_DESCRICAOEMAIL) = '" + pesEmail.toUpperCase() + "'");

		List<Contato> listReturn = new ArrayList<Contato>();
		List<Object[]> listResults = getSession().createNativeQuery(sql.toString()).getResultList();
		for (Object[] item: listResults) {
			Contato contato = new Contato();
			Integer pesCodigo = (Integer) item[0];
			Pessoa pessoa = new Pessoa();
			pessoa.setPesCodigo((long) pesCodigo);
			
			contato.setPessoa(pessoa);
			contato.setCtoDescricaoEmail((String) item[1]);
			listReturn.add(contato);
		}
		return listReturn;
	}
	
	@SuppressWarnings("unchecked")
	public List<Contato> listContatoPorEmail(long pesCodigo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT C.CTO_DESCRICAOEMAIL ");
		sql.append("FROM TBL_CONTATO C, TBL_TIPOCONTATO T ");
		sql.append("WHERE C.TPC_CODIGO = T.TPC_CODIGO ");
		sql.append("AND T.TPC_CODIGO = 1 ");
		sql.append("AND C.PES_CODIGO = " + pesCodigo);

		List<Contato> listReturn = new ArrayList<Contato>();
		List<String> list = getSession().createNativeQuery(sql.toString()).getResultList();
		for (String item: list) {
			Contato contato = new Contato();
			Pessoa pessoa = new Pessoa();
			pessoa.setPesCodigo((long) pesCodigo);
			
			contato.setPessoa(pessoa);
			contato.setCtoDescricaoEmail((String) item);
			listReturn.add(contato);
		}
		return listReturn;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.pibsantalucia.dao.PessoaDao#totalMembroPorSexo(java.lang.String)
	 */
	public int totalMembroPorSexo(final String sexo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(P.PES_CODIGO) as TOTAL ");
		sql.append("from TBL_PESSOA P ");
		sql.append("where P.PES_SEXO = '" + sexo + "'");
		sql.append("  and P.PES_FLGMEMBRO = 'S'");
		//		sql.append("and NOT exists (SELECT 1 FROM ");
		//		sql.append("pibsan_pibsantalucia.TBL_EXMEMBRO E WHERE E.MEE_CODIGO = M.MEE_CODIGO)");

		Object qdtMembros = (Object) getSession().createNativeQuery(sql.toString()).getSingleResult();

		return ((BigInteger) qdtMembros).intValue();
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.pibsantalucia.dao.PessoaDao#listaTipoContatos()
	 */
	public List<TipoContato> listaTipoContatos() throws Exception {
		return getSession().createQuery("from TipoContato order by tpcDescricao", TipoContato.class).getResultList();
	}
	
	/*
	 * 
	 */
	public TipoContato getTipoContato(int tpcCodigo) throws Exception {
		return getSession().createQuery("from TipoContato where tpcCodigo = "+tpcCodigo, TipoContato.class).getSingleResult();
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.pibsantalucia.dao.PessoaDao#listaCongregados(java.lang.String)
	 */
	public List<ViewMembros> listaCongregados(final Long pesCodigo, final String pesNome, final String pesFglMembro) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("from ViewMembros ");
		if (pesCodigo != null || pesNome != null || pesFglMembro != null) {
			sql.append("where ");
			if (pesCodigo != null) {
				sql.append("pesCodigo = " + pesCodigo + " ");
			} else {
				if (pesNome != null) {
					sql.append("upper(pesNome) like upper('%" + pesNome + "%') ");
				}
				
				if (pesFglMembro != null) {
					if (pesNome != null) {
						sql.append("and ");
					}
					
					if (pesFglMembro.equals("S")) {
						sql.append("pesFglMembro = true ");
					} else {
						sql.append("pesFglMembro = false ");
					}
				}
			}
		}
		sql.append("order by pesNome");

		return getSession().createQuery(sql.toString(), ViewMembros.class).getResultList();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.pibsantalucia.dao.PessoaDao#listaAniversariantes(java.lang.Long)
	 */
	public List<ViewMembros> listaAniversariantes(final int mes) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("from ViewMembros where pesFglMembro = true and month(pesDtNascimento) = month(now()) ");
		sql.append("order by day(pesDtNascimento) asc");

		return getSession().createQuery(sql.toString(), ViewMembros.class).getResultList();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.pibsantalucia.dao.PessoaDao#listaContatos(long)
	 */
	public List<ViewContato> listaContatos(long pesCodigo) throws Exception {
		return getSession().createQuery("from ViewContato where pesCodigo = " + pesCodigo, ViewContato.class).getResultList();
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.pibsantalucia.dao.PessoaDao#listaMembros(long)
	 */
	public List<ViewMembros> listaCongregados(Long pesCodigo) throws Exception {
		if (pesCodigo == null) {
			return getSession().createQuery("from ViewMembros order by pesNome", ViewMembros.class).getResultList();
		} else {
			return getSession().createQuery("from ViewMembros where pesCodigo = " + pesCodigo, ViewMembros.class).getResultList();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.pibsantalucia.dao.PessoaDao#obterMembro(long)
	 */
	public ViewMembro obterCongregado(long pesCodigo) throws Exception {
		return getSession().createQuery("from ViewMembro where pesCodigo = " + pesCodigo, ViewMembro.class).getSingleResult();
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.pibsantalucia.dao.PessoaDao#obterDadosPessoa(java.lang.String)
	 */
	public Pessoa obterDadosPessoa(long pesCodigo) throws Exception {
		return getSession().createQuery("from Pessoa where pesCodigo = " + pesCodigo, Pessoa.class).getSingleResult();
	}
	
	public void updateNamePhoto(long pesCodigo, String namePhoto) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE TBL_PESSOA");
		sql.append(" SET PES_NAMEPHOTO = '" + namePhoto + "'");
		sql.append(" WHERE PES_CODIGO = " + pesCodigo);
		getSession().createNativeQuery(sql.toString()).executeUpdate();
	}
	
	public List<Pessoa> listPessoaPorCpf(String pesCpf) throws Exception {
		return getSession().createQuery("from Pessoa where pesCpf = '" + pesCpf + "'", Pessoa.class).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<SendPushNotificationVO> listMembrosSendPush() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT P.PES_CODIGO, D.DEM_TOKENFIREBASE ");
		sql.append("FROM TBL_PESSOA P, TBL_DEVICE_MOBILE D ");
		sql.append("WHERE P.PES_CODIGO = D.PES_CODIGO ");

		List<SendPushNotificationVO> listReturn = new ArrayList<SendPushNotificationVO>();
		List<Object[]> listResults = getSession().createNativeQuery(sql.toString()).getResultList();
		for (Object[] item: listResults) {
			SendPushNotificationVO sendPushNotificationVO = new SendPushNotificationVO();
			
			sendPushNotificationVO.setPesCodigo((Integer) item[0]);
			sendPushNotificationVO.setDemTokenFirebase((String) item[1]);

			listReturn.add(sendPushNotificationVO);
		}
		return listReturn;
	}
	
	@SuppressWarnings("unchecked")
	public List<ViewMembro> listAniversariantesHoje() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT PES_CODIGO, PES_NOME, PES_SEXO ");
		sql.append("FROM VIW_MEMBROS ");
		sql.append("WHERE PES_FLGMEMBRO = TRUE ");
		sql.append("AND MONTH(PES_DTNASCIMENTO) = MONTH(NOW()) AND DAY(PES_DTNASCIMENTO) = DAY(NOW()) ");

		List<ViewMembro> listReturn = new ArrayList<ViewMembro>();
		List<Object[]> listResults = getSession().createNativeQuery(sql.toString()).getResultList();
		for (Object[] item: listResults) {
			ViewMembro viewMembro = new ViewMembro();
			
			viewMembro.setPesCodigo((Integer) item[0]);
			viewMembro.setPesNome((String) item[1]);
			viewMembro.setPesSexo((String) item[2]);

			listReturn.add(viewMembro);
		}
		return listReturn;
	}
	
	public void restorePessoa(String script) throws Exception {
		getSession().createNativeQuery(script).executeUpdate();
	}
}
