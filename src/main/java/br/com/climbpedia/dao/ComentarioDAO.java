package br.com.climbpedia.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.RollbackException;
import javax.transaction.Transactional;

import org.jboss.resteasy.spi.UnhandledException;

import br.com.climbpedia.model.Comentario;

public class ComentarioDAO {
	private EntityManager em;
	
	public ComentarioDAO(EntityManager em) {
		this.em = em;
	}
	
	public Comentario getComentarioById(Long id) {

		return em.createQuery("SELECT comentario FROM Comentario comentario WHERE comentario.id = :id", Comentario.class)
				.setParameter("id", id)
				.getSingleResult();
	}
	
	public List<Comentario> getAllComentarios() {
				
		return em.createQuery("SELECT comentario FROM Comentario comentario", Comentario.class)
				.getResultList();
	}
	
	public Comentario insertComentario(Comentario comentario) {

		comentario.setDataHora(new Timestamp(new Date().getTime()));

		em.persist(comentario);

		return comentario;
	}

	public Comentario updateComentario(Comentario comentario) {

		comentario.setDataHora(new Timestamp(new Date().getTime()));
		em.merge(comentario);
		/* 
		*  flush() necessario para caso de erro de Constraint a Exception estoure dentro do bloco 
		*  try catch no resource, e não no fim do método que é onde o boundary da @Transactional 
		*  sincroniza o banco x objeto 
		*/
		em.flush(); 
		
		return comentario;
	}

	public Comentario deleteComentarioById(Long id) {
		Comentario comentario = this.getComentarioById(id);
		em.remove(comentario);
		
		return comentario;
	}
}
	
	
