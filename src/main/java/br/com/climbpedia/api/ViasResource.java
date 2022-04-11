package br.com.climbpedia.api;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.climbpedia.dao.ViaDAO;
import br.com.climbpedia.model.Via;


@Path("/vias")
public class ViasResource {
		
	@Inject
	private EntityManager em;
	
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVia(@PathParam("id") Long id) {

    	ViaDAO viaDAO = new ViaDAO(em);
        Via via = viaDAO.getVia(id);

        return Response.status(200)
        		.entity(via)
        		.build();
    }
    
    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertVia(Via viaRequest) {
    	ViaDAO viaDAO = new ViaDAO(em);
    	Via via = viaDAO.insertVia(viaRequest);    	

    	return Response.status(200)
    			.entity(via).build();
    			
    }
    
//    public static Response insertVia(Json) {}
}