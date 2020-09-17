package br.com.pibsantalucia.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import br.com.pibsantalucia.model.Pastoral;

@Repository
public class PastoralDaoImpl extends HibernateDao implements PastoralDao {

	public List<Pastoral> lista() throws Exception {
		return getSession().createQuery("from Pastoral order by pasDtReferencia", Pastoral.class).getResultList();
	}

	public Pastoral obterPorId(long pasCodigo) throws Exception {
		return getSession().createQuery("from Pastoral where pasCodigo = "+pasCodigo, Pastoral.class).getSingleResult();
	}
	
	public Pastoral obterPorDataReferencia(String pasReferencia) throws Exception {
		Query<Pastoral> query = getSession().createQuery("from Pastoral where pasDtReferencia <= '"+pasReferencia+"' order by pasDtReferencia desc ", Pastoral.class);
		query.setMaxResults(1);
		
		return query.getSingleResult();
	}
}
