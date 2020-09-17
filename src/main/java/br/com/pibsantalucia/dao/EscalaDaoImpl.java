package br.com.pibsantalucia.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.pibsantalucia.model.Escala;
import br.com.pibsantalucia.model.ViewEscalaMembro;

@Repository
public class EscalaDaoImpl extends HibernateDao implements EscalaDao {

	public List<Escala> getEscalaPorTipoAndAnoMes(Escala escala) throws Exception {
		StringBuilder hql = new StringBuilder();
		hql.append("from Escala where escFlgTipo = ");
		hql.append(escala.getEscFlgTipo());
		hql.append(" and ");
		hql.append("escAnoMes = ");
		hql.append(escala.getEscAnoMes());
		
		return getSession().createQuery(hql.toString(), Escala.class).getResultList();
	}
	
	public List<Escala> lista(int escAnoMes, int escFlgTipo) throws Exception {
		StringBuilder hql = new StringBuilder();
		hql.append("from Escala where escFlgTipo = ");
		hql.append(escFlgTipo);
		hql.append(" and ");
		hql.append("escAnoMes = ");
		hql.append(escAnoMes);
		hql.append(" order by  ESC_ANOMES asc");
		
		return getSession().createQuery(hql.toString(), Escala.class).getResultList();
	}
	
	public List<ViewEscalaMembro> listaEscalaMembro(long escCodigo) throws Exception {
		return getSession().createQuery("from ViewEscalaMembro where escCodigo = " + escCodigo, ViewEscalaMembro.class).getResultList();
	}
	
	public void deletarPorEscala(long escCodigo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM TBL_ESCALA_MEMBROS ");
		sql.append("WHERE ESC_CODIGO = " +escCodigo);
		
		getSession().createNativeQuery(sql.toString()).executeUpdate();
	}
	
	public void insertPorEscala(long escCodigo, long pesCodigo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO TBL_ESCALA_MEMBROS (ESC_CODIGO, PES_CODIGO) VALUES (");
		sql.append(escCodigo);
		sql.append(", ");
		sql.append(pesCodigo);
		sql.append(")");
		
		getSession().createNativeQuery(sql.toString()).executeUpdate();
	}
}
