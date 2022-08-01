package br.com.climbpedia.resources;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
//import javax.ws.rs.core.Response.Status;

import br.com.climbpedia.dao.ViaDAO;
import br.com.climbpedia.dto.ViaDTO;
import br.com.climbpedia.model.Via;

//FIXME criar viaDTO, transferir dados de via onde ao inves de passar id da url da imagem , passar url string
@Path("/vias")
public class ViasResource {
		
	@Inject
	private EntityManager em;
	
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getViaById(@PathParam("id") Long id) {
    	
    	try {
    		ViaDAO viaDAO = new ViaDAO(em);
    		Via via = viaDAO.getViaById(id);
    		
    		return Response.ok()
    				.entity(via)
    				.build();
    	} catch(NoResultException e) {
    		return Response.status(Response.Status.BAD_REQUEST)
    				.entity("Id da via não encontrado")
    				.build();    		
    	} catch (Exception e) {
    		return Response.status(Response.Status.BAD_REQUEST)
    				.entity("Ocorreu um erro na sua busca: " + e.getClass())
    				.build();
    	}
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getaAllVias() {

    	ViaDAO viaDAO = new ViaDAO(em);
        List<Via> allVias = viaDAO.getAllVias();

        return Response.status(200)
        		.entity(allVias)
        		.build();
    }
    
    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertVia(Via viaRequest) {

		HashMap<String, String> erros = (HashMap<String, String>) this.validaCamposObrigatorios(viaRequest, "insert");
    	
    	if (!erros.isEmpty()) {
    		Map<String, Map> retorno = new HashMap<String, Map>();
    		retorno.put("Ocorreu erro(s) na validação dos dados:", erros);
    		
    		return Response.status(Response.Status.BAD_REQUEST)
    				.entity(retorno)
    				.build();
    	}
    	
		ViaDAO viaDAO = new ViaDAO(em);
    	try {
    		Via via = viaDAO.insertVia(viaRequest);  
    		
    		return Response.ok()
    				.entity(via)
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
    public Response updateVia(Via viaRequest) {
    	
    	Map<String, String> erros = this.validaCamposObrigatorios(viaRequest, "update");

    	if (viaRequest.getId() == null) erros.put("id", "id deve ser informado");
    	
    	if (!erros.isEmpty()) {
    		return Response.status(Response.Status.BAD_REQUEST)
    				.entity(erros)
    				.build();
    	}

    	ViaDAO viaDAO = new ViaDAO(em);
    	Via viaResult = new Via();
    	try {
    		viaResult = viaDAO.updateVia(viaRequest);
    	} catch(Exception e) {
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
    				.entity("Ocorre um erro interno no servidor. Causa: " + e.getMessage())
    				.build();
    	}
    	
		return Response.ok()
				.entity("Atualização realizada com sucesso!")
				.entity(viaResult)
				.build();
    	
    }
    
    
    @DELETE
    @Transactional
    @Path("{id}")
    public Response deleteById(@PathParam("id") Long id) {
    	Via via = new Via();
    	
    	try {
    		ViaDAO viaDAO = new ViaDAO(em);
    		via = viaDAO.deleteViaById(id);

		} catch (Exception e) {
	    	return Response.status(Response.Status.NOT_FOUND)
	    			.entity("Via informada não existe: Id " + id.toString())
	    			.build();
		}
    	
		return Response.ok()
				.entity("Via deletada com sucesso: " + via.toString())
				.build();

    }
    
	private Map<String, String> validaCamposObrigatorios(Via via, String op) {
		// operacao = "insert" || "update"
		Map<String, String> erros = new HashMap<String, String>();
		String nome = via.getNome();
		String descricao = via.getDescricao();
		String graduacao = via.getGraduacao();

		
		if ( (op == "insert" && (nome == null || nome.isBlank())) || (op =="update" && nome != null && nome.isBlank())) {
			erros.put("nome", "Nome deve ser preenchido");
		}
		if ( (op == "insert" && (descricao == null || descricao.isBlank())) || (op =="update" && descricao != null && descricao.isBlank())) {
			erros.put("descricao", "Descrição deve ser preenchida");
		}
		if ( (op == "insert" && (graduacao == null ||graduacao.isBlank())) || (op =="update" && graduacao != null && graduacao.isBlank())) {
			erros.put("graduacao", "Graduação deve ser preenchida");
		}
				
		return erros; 
		
	}
//	
//	private ViaDTO createViaDTO(Via rawVia) {
//		String urlImg = Via
//		return viaDTO;
//	}

    
}