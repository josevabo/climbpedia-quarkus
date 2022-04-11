package br.com.climbpedia.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.climbpedia.model.Via;

public class ViaDAO {
	private EntityManager em;
	
	public ViaDAO(EntityManager em) {
		this.em = em;
	}
	
	public Via getVia(Long id) {
        
		return em.createQuery("SELECT via FROM Via via WHERE via.id = :id", Via.class)
				.setParameter("id", id)
				.getSingleResult();
	}
	
	@Transactional
	public Via insertVia(Via via) {
		System.out.println(via.toString());
		em.persist(via);
		
		return via;
	}
	
}
