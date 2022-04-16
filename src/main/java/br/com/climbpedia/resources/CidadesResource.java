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

import br.com.climbpedia.dao.CidadeDAO;
import br.com.climbpedia.model.Cidade;


@Path("/cidades")
public class CidadesResource {
		
	@Inject
	private EntityManager em;
	
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCidadeById(@PathParam("id") Long id) {

    	try {
    		CidadeDAO cidadeDAO = new CidadeDAO(em);
    		Cidade cidade = cidadeDAO.getCidadeById(id);
    		
    		return Response.ok()
    				.entity(cidade)
    				.build();
    	} catch(NoResultException e) {
    		return Response.status(Response.Status.BAD_REQUEST)
    				.entity("Id da cidade não encontrado")
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
    public Response getAllCidades() {
    	
    	try {
    		CidadeDAO cidadeDAO = new CidadeDAO(em);
    		List<Cidade> allCidades = cidadeDAO.getAllCidades();
    		
    		return Response.ok()
    				.entity(allCidades)
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
    public Response insertCidade(Cidade cidadeRequest) {
    	CidadeDAO cidadeDAO = new CidadeDAO(em);

    	try {
    		Cidade cidade = cidadeDAO.insertCidade(cidadeRequest);  
		
			return Response.ok()
					.entity(cidade)
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
    public Response updateCidadeById(Cidade cidadeRequest) {

    	CidadeDAO cidadeDAO = new CidadeDAO(em);
    	Cidade cidadeResult = new Cidade();
    	
    	try {
    		cidadeResult = cidadeDAO.updateCidade(cidadeRequest);
    	} catch(Exception e) {
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
    				.entity("Ocorreu um erro interno no servidor. Causa: " + e.getMessage())
    				.build();
    	}
    	
		return Response.ok()
				.entity("Atualização realizada com sucesso!")
				.entity(cidadeResult)
				.build();
    	
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response deleteCidadeById(@PathParam("id") Long id) {
    	CidadeDAO cidadeDAO = new CidadeDAO(em);
    	Cidade cidadeResult = new Cidade();
    	
    	try {
    		cidadeResult = cidadeDAO.deleteCidadeById(id);

		} catch (NoResultException e) {
	    	return Response.status(Response.Status.NOT_FOUND)
	    			.entity("Cidade informada não existe: Id " + id.toString())
	    			.build();
		} catch (Exception e) {
	    	return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
	    			.entity("Operação não realizada. Ocorreu um erro interno")
	    			.build();
		}
    	
		return Response.ok()
				.entity("Cidade deletada com sucesso: " + cidadeResult.toString())
				.build();

    }
}