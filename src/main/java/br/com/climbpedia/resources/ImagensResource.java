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

import br.com.climbpedia.dao.ImagemDAO;
import br.com.climbpedia.model.Imagem;


@Path("/imagens")
public class ImagensResource {
		
	@Inject
	private EntityManager em;
	
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getImagemById(@PathParam("id") Long id) {

		try {
    		ImagemDAO imagemDAO = new ImagemDAO(em);
    		Imagem imagem = imagemDAO.getImagemById(id);

	   		return Response.ok()
    				.entity(imagem)
    				.build();
    	} catch(NoResultException e) {
    		return Response.status(Response.Status.BAD_REQUEST)
    				.entity("Imagem não encontrada")
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
    public Response getAllImagens() {
    	
    	try {
    		ImagemDAO imagemDAO = new ImagemDAO(em);
    		List<Imagem> allImagens = imagemDAO.getAllImagens();
    		
    		return Response.ok()
    				.entity(allImagens)
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
    public Response insertImagem(Imagem imagemRequest) {
    	ImagemDAO imagemDAO = new ImagemDAO(em);

    	try {
    		Imagem imagem = imagemDAO.insertImagem(imagemRequest);  
		
			return Response.ok()
					.entity(imagem)
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
    public Response updateImagemById(Imagem imagemRequest) {
		ImagemDAO imagemDAO = new ImagemDAO(em);

    	Imagem imagemResult = new Imagem();
    	
    	try {
			imagemDAO.getImagemById(imagemRequest.getId());
    		imagemResult = imagemDAO.updateImagem(imagemRequest);
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
				.entity(imagemResult)
				.build();
    	
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response deleteImagemById(@PathParam("id") Long id) {
    	ImagemDAO imagemDAO = new ImagemDAO(em);
    	Imagem imagemResult = new Imagem();
    	
    	try {
    		imagemResult = imagemDAO.deleteImagemById(id);

		} catch (NoResultException e) {
	    	return Response.status(Response.Status.NOT_FOUND)
	    			.entity("Imagem informada não existe: Id " + id.toString())
	    			.build();
		} catch (Exception e) {
	    	return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
	    			.entity("Operação não realizada. Ocorreu um erro interno")
	    			.build();
		}
    	
		return Response.ok()
				.entity("Imagem deletada com sucesso: " + imagemResult.toString())
				.build();

    }
}