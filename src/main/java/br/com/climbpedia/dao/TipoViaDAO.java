package br.com.climbpedia.dao;

import java.util.List;

import javax.persistence.EntityManager;
import br.com.climbpedia.model.TipoVia;

public class TipoViaDAO {
	private EntityManager em;
	
	public TipoViaDAO(EntityManager em) {
		this.em = em;
	}
	
	public TipoVia getTipoViaById(Long id) {

		return em.createQuery("SELECT tipoVia FROM TipoVia tipoVia WHERE tipoVia.id = :id", TipoVia.class)
				.setParameter("id", id)
				.getSingleResult();
	}
	
	public List<TipoVia> getAllTiposVia() {
		List<TipoVia> allTiposVia = em.createQuery("SELECT tipoVia FROM TipoVia tipoVia", TipoVia.class).getResultList();
		
		return allTiposVia;
	}
	
	public TipoVia insertTipoVia(TipoVia tipoVia) {

		em.persist(tipoVia);

		return tipoVia;
	}
	
	public TipoVia updateTipoVia(TipoVia tipoVia) {
		em.merge(tipoVia);
		
		return tipoVia;
	}

	public TipoVia deleteTipoViaById(Long id) {
		TipoVia tipoVia = this.getTipoViaById(id);
		em.remove(tipoVia);
		
		return tipoVia;
	}
}
	
	
