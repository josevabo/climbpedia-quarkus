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

import org.hibernate.exception.ConstraintViolationException;

import br.com.climbpedia.dao.ComentarioDAO;
import br.com.climbpedia.model.Comentario;


@Path("/comentarios")
public class ComentariosResource {
		
	@Inject
	private EntityManager em;
	
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getComentarioById(@PathParam("id") Long id) {

		try {
    		ComentarioDAO comentarioDAO = new ComentarioDAO(em);
    		Comentario comentario = comentarioDAO.getComentarioById(id);

	   		return Response.ok()
    				.entity(comentario)
    				.build();
    	} catch(NoResultException e) {
    		return Response.status(Response.Status.BAD_REQUEST)
    				.entity("Comentario não encontrado")
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
    public Response getAllComentarios() {
    	
    	try {
    		ComentarioDAO comentarioDAO = new ComentarioDAO(em);
    		List<Comentario> allComentarios = comentarioDAO.getAllComentarios();
    		
    		return Response.ok()
    				.entity(allComentarios)
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
    public Response insertComentario(Comentario comentarioRequest) {
    	ComentarioDAO comentarioDAO = new ComentarioDAO(em);

    	try {
    		Comentario comentario = comentarioDAO.insertComentario(comentarioRequest);  
		
			return Response.ok()
					.entity(comentario)
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
    public Response updateComentarioById(Comentario comentarioRequest) {
		ComentarioDAO comentarioDAO = new ComentarioDAO(em);
    	Comentario comentarioResult = new Comentario();
    	
    	try {
			comentarioDAO.getComentarioById(comentarioRequest.getId());
			comentarioResult = comentarioDAO.updateComentario(comentarioRequest);
    	} catch(NoResultException | ConstraintViolationException e) {
    		return Response.status(Response.Status.BAD_REQUEST)
    				.entity("Solicitação não pode ser atendida. Dados passados inconsistentes" )
    				.build();
    	} catch(Exception e) {
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
    				.entity("Ocorreu um erro interno no servidor. Causa: " + e.getMessage())
    				.build();
    	}
    	
		return Response.ok()
				.entity("Atualização realizada com sucesso!")
				.entity(comentarioResult)
				.build();
    	
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response deleteComentarioById(@PathParam("id") Long id) {
    	ComentarioDAO comentarioDAO = new ComentarioDAO(em);
    	Comentario comentarioResult = new Comentario();
    	
    	try {
    		comentarioResult = comentarioDAO.deleteComentarioById(id);

		} catch (NoResultException e) {
	    	return Response.status(Response.Status.NOT_FOUND)
	    			.entity("Comentario informado não existe: Id " + id.toString())
	    			.build();
		} catch (Exception e) {
	    	return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
	    			.entity("Operação não realizada. Ocorreu um erro interno")
	    			.build();
		}
    	
		return Response.ok()
				.entity("Comentario deletado com sucesso: " + comentarioResult.toString())
				.build();

    }
}