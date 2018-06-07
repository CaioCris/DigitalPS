package br.com.fiap.resource;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import br.com.fiap.dao.ImovelDAO;
import br.com.fiap.dao.impl.ImovelDAOImpl;
import br.com.fiap.entity.Imovel;
import br.com.fiap.exception.CommitException;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

@Entity
@Path("/imovel")
public class ImovelResource {
	
	private ImovelDAO dao;
	public ImovelResource() {
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		dao = new ImovelDAOImpl(em);
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Imovel buscar(@PathParam("id") long codigo) {
		return dao.buscar(codigo);
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Imovel> listar(){
		return dao.listar();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(Imovel imovel, @Context UriInfo uri) {
		try {
			dao.cadastrar(imovel);
			dao.commit();
		}catch (CommitException erro) {
			erro.printStackTrace();
			return Response.serverError().build();
		}
		UriBuilder url = uri.getAbsolutePathBuilder();
		url.path(String.valueOf(imovel.getCodigo()));
		return Response.created(url.build()).build();
		
	}
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizar(Imovel imovel, @PathParam("id") long codigo) {
		try {
			imovel.setCodigo(codigo);
			dao.atualizar(imovel);
			dao.commit();
		}catch (CommitException erro) {
			erro.printStackTrace();
			return Response.serverError().build();
		}
		return Response.ok().build();
	}
	
	@DELETE
	@Path("{id}")
	public void apagar(@PathParam("id") long codigo) {
		try {
			dao.remover(codigo);
			dao.commit();
		}catch (Exception erro) {
			erro.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}
}
