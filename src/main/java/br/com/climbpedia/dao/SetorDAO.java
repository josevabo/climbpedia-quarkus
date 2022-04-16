package br.com.climbpedia.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.climbpedia.model.Setor;

public class SetorDAO {
	private EntityManager em;
	
	public SetorDAO(EntityManager em) {
		this.em = em;
	}
	
	public Setor getSetorById(Long id) {
        
		return em.createQuery("SELECT setor FROM Setor setor WHERE setor.id = :id", Setor.class)
				.setParameter("id", id)
				.getSingleResult();
	}
	
	public List<Setor> getAllSetores() {
		List<Setor> allSetores = em.createQuery("SELECT setor FROM Setor setor", Setor.class).getResultList();
		
		return allSetores;
	}
	
	public Setor insertSetor(Setor setor) {

		em.persist(setor);

		return setor;
	}
	
	public Setor updateSetor(Setor setor) {
		em.merge(setor);
		
		return setor;
	}

	public Setor deleteSetorById(Long id) {
		Setor setor = this.getSetorById(id);
		em.remove(setor);
		
		return setor;
	}	
	
}
