package br.com.climbpedia.dao;

import java.util.List;
import javax.persistence.EntityManager;
import br.com.climbpedia.model.Usuario;

public class UsuarioDAO {
	private EntityManager em;
	
	public UsuarioDAO(EntityManager em) {
		this.em = em;
	}
	
	public Usuario getUsuarioById(Long id) {

		return em.createQuery("SELECT usuario FROM Usuario usuario WHERE usuario.id = :id", Usuario.class)
				.setParameter("id", id)
				.getSingleResult();
	}
	
	public List<Usuario> getAllUsuarios() {
				
		return em.createQuery("SELECT usuario FROM Usuario usuario", Usuario.class)
				.getResultList();
	}
	
	public Usuario insertUsuario(Usuario usuario) {

		em.persist(usuario);

		return usuario;
	}
	
	public Usuario updateUsuario(Usuario usuario) {
		em.merge(usuario);
		
		return usuario;
	}

	public Usuario deleteUsuarioById(Long id) {
		Usuario usuario = this.getUsuarioById(id);
		em.remove(usuario);
		
		return usuario;
	}
}
	
	
