package br.com.climbpedia.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.climbpedia.dao.ViasDAO;


@Path("/api/vias")
public class ViasResource {

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public static Response getVia(@PathParam("id") int id) {
        String dados = ViasDAO.getVias(id);
        
        return Response.status(200)
        		.entity(dados)
        		.build();
    }
    
    public static Response insertVia(Json)
}