package br.com.climbpedia.dao;

import java.util.List;

import javax.persistence.EntityManager;
import br.com.climbpedia.model.Cidade;

public class CidadeDAO {
	private EntityManager em;
	
	public CidadeDAO(EntityManager em) {
		this.em = em;
	}
	
	public Cidade getCidadeById(Long id) {
        
		return em.createQuery("SELECT cidade FROM Cidade cidade WHERE cidade.id = :id", Cidade.class)
				.setParameter("id", id)
				.getSingleResult();
	}
	
	public List<Cidade> getAllCidades() {
		List<Cidade> allCidades = em.createQuery("SELECT cidade FROM Cidade cidade", Cidade.class).getResultList();
		
		return allCidades;
	}
	
	public Cidade insertCidade(Cidade cidade) {

		em.persist(cidade);

		return cidade;
	}
	
	public Cidade updateCidade(Cidade cidade) {
		em.merge(cidade);
		
		return cidade;
	}

	public Cidade deleteCidadeById(Long id) {
		Cidade cidade = this.getCidadeById(id);
		em.remove(cidade);
		
		return cidade;
	}
}
	
	
