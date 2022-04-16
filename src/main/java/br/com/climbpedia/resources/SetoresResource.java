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
import br.com.climbpedia.dao.SetorDAO;
import br.com.climbpedia.model.Setor;


@Path("/setores")
public class SetoresResource {
		
	@Inject
	private EntityManager em;
	
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSetorById(@PathParam("id") Long id) {
    	
    	try {
    		SetorDAO setorDAO = new SetorDAO(em);
    		Setor setor = setorDAO.getSetorById(id);
    		
    		return Response.ok()
    				.entity(setor)
    				.build();
    	} catch(NoResultException e) {
    		return Response.status(Response.Status.BAD_REQUEST)
    				.entity("Id do setor não encontrado")
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
    public Response getAllSetores() {
    	
    	try {
    		SetorDAO setorDAO = new SetorDAO(em);
    		List<Setor> allSetores = setorDAO.getAllSetores();
    		
    		return Response.ok()
    				.entity(allSetores)
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
    public Response insertSetor(Setor setorRequest) {
    	
		SetorDAO setorDAO = new SetorDAO(em);
    	try {
    		Setor setor = setorDAO.insertSetor(setorRequest);  
    		
    		return Response.ok()
    				.entity(setor)
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
    public Response updateSetor(Setor setorRequest) {

    	SetorDAO setorDAO = new SetorDAO(em);
    	Setor setorResult = new Setor();
    	try {
    		setorResult = setorDAO.updateSetor(setorRequest);
    	} catch(Exception e) {
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
    				.entity("Ocorreu um erro interno no servidor. Causa: " + e.getMessage())
    				.build();
    	}
    	
		return Response.ok()
				.entity("Atualização realizada com sucesso!")
				.entity(setorResult)
				.build();
    	
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response deleteSetorById(@PathParam("id") Long id) {
    	SetorDAO setorDAO = new SetorDAO(em);
    	Setor setor = new Setor();
    	
    	try {
    		setor = setorDAO.deleteSetorById(id);

		} catch (NoResultException e) {
	    	return Response.status(Response.Status.NOT_FOUND)
	    			.entity("Setor informado não existe: Id " + id.toString())
	    			.build();
		} catch (Exception e) {
	    	return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
	    			.entity("Operação não realizada. Ocorreu um erro interno")
	    			.build();
		}
    	
		return Response.ok()
				.entity("Setor deletado com sucesso: " + setor.toString())
				.build();

    }
    
}