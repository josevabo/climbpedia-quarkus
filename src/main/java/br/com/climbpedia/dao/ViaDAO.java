package br.com.climbpedia.dao;

import java.util.List;
import javax.persistence.EntityManager;
import br.com.climbpedia.model.Via;

public class ViaDAO {
	private EntityManager em;
	
	public ViaDAO(EntityManager em) {
		this.em = em;
	}
	
	public Via getViaById(Long id) {
        
		return em.createQuery("SELECT via FROM Via via WHERE via.id = :id", Via.class)
				.setParameter("id", id)
				.getSingleResult();
	}
	
	public List<Via> getAllVias() {
		List<Via> allVias = em.createQuery("SELECT via FROM Via via", Via.class).getResultList();
		
		return allVias;
	}
	
	public Via insertVia(Via via) {

		em.persist(via);
		
		return via;
	}
	
	public Via updateVia(Via via) {

		em.merge(via);
		
		return via;
	}


	public Via deleteViaById(Long id) {
		Via via = new Via();

		via = em.createQuery("SELECT via FROM Via via WHERE via.id = :id", Via.class)
				.setParameter("id", id).getSingleResult();

		em.remove(via);			

		return via;		
	}
}
