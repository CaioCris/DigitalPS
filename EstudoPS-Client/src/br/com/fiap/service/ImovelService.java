package br.com.fiap.service;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import br.com.fiap.to.Imovel;

public class ImovelService {
	
	private final static String url = "http://localhost:8080/EstudoPS-Server/rest/imovel";
	
	private Client client = Client.create();
	
	public Imovel buscar(long codigo) throws Exception{
		WebResource resource = client.resource(url).path(String.valueOf(codigo));
		ClientResponse response = resource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		if (response.getStatus() != 200) {
			throw new Exception("Erro: " + response.getStatus());
		}
		return response.getEntity(Imovel.class);
	}
	
	public List<Imovel> listar() throws Exception{
		WebResource resource = client.resource(url);
		ClientResponse response = resource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		if (response.getStatus() != 200) {
			throw new Exception("Erro: " + response.getStatus());
		}
		return Arrays.asList(response.getEntity(Imovel[].class));
	}
	
	public void cadastrar(Imovel imovel) throws Exception{
		WebResource resource = client.resource(url);
		ClientResponse response = resource.accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, imovel);
		if (response.getStatus() != 201) {
			throw new Exception("Erro: " + response.getStatus());
		}
	}
	
	public void atualizar(Imovel imovel) throws Exception{
		WebResource resource = client.resource(url).path(String.valueOf(imovel.getCodigo()));
		ClientResponse response = resource.accept(MediaType.APPLICATION_JSON).put(ClientResponse.class, imovel);
		if (response.getStatus() != 200) {
			throw new Exception("Erro: " + response.getStatus());
		}
	}
	
	public void remover(long codigo) throws Exception{
		WebResource resource = client.resource(url).path(String.valueOf(codigo));
		ClientResponse response = resource.delete(ClientResponse.class);
		if (response.getStatus() != 204) {
			throw new Exception("Erro: " + response.getStatus());
		}
	}
}
