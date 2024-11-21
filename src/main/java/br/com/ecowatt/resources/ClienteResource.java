package br.com.ecowatt.resources;

import java.sql.SQLException;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import br.com.ecowatt.model.bo.ClienteBO;
import br.com.ecowatt.model.entidades.Cliente;

@Path("clientes")
public class ClienteResource {
	private ClienteBO clienteBO = new ClienteBO();

	@GET
	@Path("/teste")
	@Produces(MediaType.TEXT_PLAIN)
	public String testarRota() {
	    return "API funcionando!";
	}

	
	//inserir (POST)
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastroRs(Cliente cliente, @Context UriInfo uriInfo) {
	    try {
	        clienteBO.inserirBO(cliente);
	        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
	        builder.path(cliente.getCpf());
	        return Response.created(builder.build()).build();
	    } catch (SQLException e) {
	        // Log e tratamento de erro específico para SQL
	        e.printStackTrace();
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao inserir cliente: " + e.getMessage()).build();
	    } catch (Exception e) {
	        // Log e tratamento de erro genérico
	        e.printStackTrace();
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno do servidor: " + e.getMessage()).build();
	    }
	}

	
	//atualizar (PUT)
	@PUT
	@Path("/{cpf}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizaRs(Cliente cliente, @PathParam("cpf") String cpf) {
	    try {
	        cliente.setCpf(cpf);
	        clienteBO.atualizarBO(cliente);
	        return Response.ok().build();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar cliente: " + e.getMessage()).build();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno do servidor: " + e.getMessage()).build();
	    }
	}
	
	
	//deletar (DELETE)
	@DELETE
	@Path("/{cpf}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deletarRs(@PathParam("cpf") String cpf) throws ClassNotFoundException, SQLException {
		clienteBO.deletarBO(cpf);
		return Response.ok().build();
	}
	
	//pegar (GET)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cliente> selecionarRs() throws ClassNotFoundException {
	    try {
	        return clienteBO.selecionarBO();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Erro ao listar clientes: " + e.getMessage());
	    }
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(Cliente cliente) {
	    try {
	        Cliente clienteAutenticado = clienteBO.validarLoginBO(cliente.getEmail(), cliente.getSenha());
	        
	        if (clienteAutenticado != null) {

	            return Response.ok(clienteAutenticado).build();
	        } else {
	            return Response.status(Response.Status.UNAUTHORIZED).entity("Email ou senha incorretos").build();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao validar login: " + e.getMessage()).build();
	    }
	}
	
	@GET
	@Path("/{cpf}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obterClientePorCpf(@PathParam("cpf") String cpf) throws SQLException, ClassNotFoundException {
	    try {
	        Cliente cliente = clienteBO.obterClientePorCpfBO(cpf);
	        if (cliente == null) {
	            return Response.status(Response.Status.NOT_FOUND).entity("Cliente não encontrado").build();
	        }
	        return Response.ok(cliente).build();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao obter cliente: " + e.getMessage()).build();
	    }
	}
	
	@PUT
	@Path("/{cpf}/senha")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizarSenha(@PathParam("cpf") String cpf, Cliente cliente) {
	    try {
	        if (cliente.getSenha() == null || cliente.getSenha().isEmpty()) {
	            return Response.status(Response.Status.BAD_REQUEST)
	                           .entity("A senha não pode ser vazia.").build();
	        }
	        clienteBO.atualizarSenha(cpf, cliente.getSenha());

	        return Response.ok("Senha atualizada com sucesso!").build();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
	                       .entity("Erro ao atualizar a senha: " + e.getMessage()).build();
	    }
	}
	
}