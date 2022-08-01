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

import br.com.climbpedia.dao.TipoViaDAO;
import br.com.climbpedia.model.TipoVia;

@Path("/tiposvia")
public class TiposViaResource {

	@Inject
	private EntityManager em;

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTipoViaById(@PathParam("id") Long id) {

		try {
			TipoViaDAO tipoViaDAO = new TipoViaDAO(em);
			TipoVia tipoVia = tipoViaDAO.getTipoViaById(id);

			return Response.ok().entity(tipoVia).build();
		} catch (NoResultException e) {
			return Response.status(Response.Status.NOT_FOUND).entity("Tipo de via não encontrado").build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Ocorreu um erro na sua busca: " + e.getMessage()).build();
		}
	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllTiposVia() {

		try {
			TipoViaDAO tipoViaDAO = new TipoViaDAO(em);
			List<TipoVia> allTiposVia = tipoViaDAO.getAllTiposVia();

			return Response.ok().entity(allTiposVia).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Houve um problema na requisição").build();
		}
	}

	@POST
	@Transactional
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertTipoVia(TipoVia tipoViaRequest) {
		TipoViaDAO tipoViaDAO = new TipoViaDAO(em);

		try {
			TipoVia tipoVia = tipoViaDAO.insertTipoVia(tipoViaRequest);

			return Response.ok().entity(tipoVia).build();
		} catch (Exception e) {

			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Dados enviados não puderam ser gravados. Causa :" + e.getMessage()).build();
		}
	}

	@POST
	@Path("/update")
	@Transactional
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateTipoViaById(TipoVia tipoViaRequest) {
		TipoViaDAO tipoViaDAO = new TipoViaDAO(em);

		TipoVia tipoViaResult = new TipoVia();

		try {
			tipoViaDAO.getTipoViaById(tipoViaRequest.getId());
			tipoViaResult = tipoViaDAO.updateTipoVia(tipoViaRequest);
		} catch (NoResultException e) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("Solicitação não pode ser atendida. Não foi localizado o dado a ser atualizado").build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("Ocorreu um erro interno no servidor. Causa: " + e.getMessage()).build();
		}

		return Response.ok().entity("Atualização realizada com sucesso!").entity(tipoViaResult).build();

	}

	@DELETE
	@Transactional
	@Path("{id}")
	public Response deleteTipoViaById(@PathParam("id") Long id) {
		TipoViaDAO tipoViaDAO = new TipoViaDAO(em);
		TipoVia tipoViaResult = new TipoVia();

		try {
			tipoViaResult = tipoViaDAO.deleteTipoViaById(id);

		} catch (NoResultException e) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("Tipo Via informada não existe: Id " + id.toString()).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("Operação não realizada. Ocorreu um erro interno").build();
		}

		return Response.ok().entity("Tipo Via deletada com sucesso: " + tipoViaResult.toString()).build();

	}
}