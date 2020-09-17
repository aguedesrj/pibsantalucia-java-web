package br.com.pibsantalucia.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.pibsantalucia.model.Anuncio;

@Repository
public class AnuncioDaoImpl extends HibernateDao implements AnuncioDao {

	public List<Anuncio> lista() throws Exception {
		StringBuilder hql = new StringBuilder();
		hql.append("from Anuncio ");
		hql.append("where anuDtLimite between CURRENT_DATE ");
		hql.append("and anuDtLimite ");
		hql.append("order by anuDtLimite asc");
		
		return getSession().createQuery(hql.toString(), Anuncio.class).getResultList();
	}
}
