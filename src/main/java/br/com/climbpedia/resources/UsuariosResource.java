package br.com.climbpedia.resources;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.climbpedia.dao.UsuarioDAO;
import br.com.climbpedia.model.Usuario;


@Path("/usuarios")
public class UsuariosResource {
		
	@Inject
	private EntityManager em;
	
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuarioById(@PathParam("id") Long id) {

		try {
    		UsuarioDAO usuarioDAO = new UsuarioDAO(em);
    		Usuario usuario = usuarioDAO.getUsuarioById(id);

	   		return Response.ok()
    				.entity(usuario)
    				.build();
    	} catch(NoResultException e) {
    		return Response.status(Response.Status.BAD_REQUEST)
    				.entity("Usuario não encontrado")
    				.build();    		
    	} catch (Exception e) {
    		return Response.status(Response.Status.BAD_REQUEST)
    				.entity("Ocorreu um erro na sua busca: " + e.getMessage())
    				.build();
    	}
    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsuarios() {
    	
    	try {
    		UsuarioDAO usuarioDAO = new UsuarioDAO(em);
    		List<Usuario> allUsuarios = usuarioDAO.getAllUsuarios();
    		
    		return Response.ok()
    				.entity(allUsuarios)
    				.build();
    	} catch (Exception e) {
    		return Response.status(Response.Status.BAD_REQUEST)
    				.entity("Houve um problema na requisição")
    				.build();
    	}
    }
    
    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertUsuario(Usuario usuarioRequest) {
    	UsuarioDAO usuarioDAO = new UsuarioDAO(em);

    	try {
    		Usuario usuario = usuarioDAO.insertUsuario(usuarioRequest);  
		
			return Response.ok()
					.entity(usuario)
					.build();
		} catch (Exception e) {
			
			return Response.status(Response.Status.BAD_REQUEST)
				.entity("Dados enviados não puderam ser gravados. Causa :" + e.getMessage())
				.build();
		}
    }


    @POST
    @Path("/update")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUsuarioById(Usuario usuarioRequest) {
		UsuarioDAO usuarioDAO = new UsuarioDAO(em);

    	Usuario usuarioResult = new Usuario();
    	
    	try {
			usuarioDAO.getUsuarioById(usuarioRequest.getId());
    		usuarioResult = usuarioDAO.updateUsuario(usuarioRequest);
    	} catch(NoResultException e) {
    		return Response.status(Response.Status.BAD_REQUEST)
    				.entity("Solicitação não pode ser atendida. Não foi localizado o dado a ser atualizado" )
    				.build();
    	} catch(Exception e) {
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
    				.entity("Ocorreu um erro interno no servidor. Causa: " + e.getMessage())
    				.build();
    	}
    	
		return Response.ok()
				.entity("Atualização realizada com sucesso!")
				.entity(usuarioResult)
				.build();
    	
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response deleteUsuarioById(@PathParam("id") Long id) {
    	UsuarioDAO usuarioDAO = new UsuarioDAO(em);
    	Usuario usuarioResult = new Usuario();
    	
    	try {
    		usuarioResult = usuarioDAO.deleteUsuarioById(id);

		} catch (NoResultException e) {
	    	return Response.status(Response.Status.NOT_FOUND)
	    			.entity("Usuário informado não existe: Id " + id.toString())
	    			.build();
		} catch (Exception e) {
	    	return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
	    			.entity("Operação não realizada. Ocorreu um erro interno")
	    			.build();
		}
    	
		return Response.ok()
				.entity("Usuário deletado com sucesso: " + usuarioResult.toString())
				.build();

    }
}