package br.com.climbpedia.dao;

import java.util.List;

import javax.persistence.EntityManager;
import br.com.climbpedia.model.Imagem;

public class ImagemDAO {
	private EntityManager em;
	
	public ImagemDAO(EntityManager em) {
		this.em = em;
	}
	
	public Imagem getImagemById(Long id) {

		return em.createQuery("SELECT imagem FROM Imagem imagem WHERE imagem.id = :id", Imagem.class)
				.setParameter("id", id)
				.getSingleResult();
	}
	
	public List<Imagem> getAllImagens() {
				
		return em.createQuery("SELECT imagem FROM Imagem imagem", Imagem.class)
				.getResultList();
	}
	
	public Imagem insertImagem(Imagem imagem) {

		em.persist(imagem);

		return imagem;
	}
	
	public Imagem updateImagem(Imagem imagem) {
		em.merge(imagem);
		
		return imagem;
	}

	public Imagem deleteImagemById(Long id) {
		Imagem imagem = this.getImagemById(id);
		em.remove(imagem);
		
		return imagem;
	}
}
	
	
