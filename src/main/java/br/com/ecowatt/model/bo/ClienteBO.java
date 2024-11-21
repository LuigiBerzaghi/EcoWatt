package br.com.ecowatt.model.bo;

import java.sql.SQLException;

import java.util.ArrayList;

import br.com.ecowatt.model.dao.ClienteDAO;
import br.com.ecowatt.model.entidades.Cliente;

public class ClienteBO {

    // Instancia o ClienteDAO uma única vez
    private ClienteDAO clienteDAO;

    // Construtor que inicializa o ClienteDAO
    public ClienteBO() {
        this.clienteDAO = new ClienteDAO();
		System.out.println("\nClienteDAO foi inicializado com sucesso.");
    }

    // Método para inserir um novo cliente
    public void inserirBO(Cliente cliente) throws ClassNotFoundException, SQLException {
        if (clienteDAO == null) {
            throw new IllegalStateException("\nClienteDAO não foi inicializado.");
        }
        clienteDAO.inserir(cliente);
    }

    // Método para atualizar os dados de um cliente existente
    public void atualizarBO(Cliente cliente) throws ClassNotFoundException, SQLException {
        if (clienteDAO == null) {
            throw new IllegalStateException("ClienteDAO não foi inicializado.");
        }
        clienteDAO.atualizar(cliente);
    }

    // Método para deletar um cliente pelo ID
    public void deletarBO(String cpf) throws ClassNotFoundException, SQLException {
        if (clienteDAO == null) {
            throw new IllegalStateException("ClienteDAO não foi inicializado.");
        }
        clienteDAO.excluir(cpf);
    }

    // Método para selecionar todos os clientes
    public ArrayList<Cliente> selecionarBO() throws ClassNotFoundException, SQLException {
        if (clienteDAO == null) {
            throw new IllegalStateException("ClienteDAO não foi inicializado.");
        }
        return (ArrayList<Cliente>) clienteDAO.selecionar();
    }
    // Método para validar o login
    public Cliente validarLoginBO(String email, String senha) throws SQLException {
        if (clienteDAO == null) {
            throw new IllegalStateException("ClienteDAO não foi inicializado.");
        }
        return clienteDAO.validarLogin(email, senha);
    }
    
 // Método para buscar cliente por CPF
    public Cliente obterClientePorCpfBO(String cpf) throws SQLException {
        if (clienteDAO == null) {
            throw new IllegalStateException("ClienteDAO não foi inicializado.");
        }
        return clienteDAO.obterPorCpf(cpf);
    }
    
 // Método para atualizar a senha
    public void atualizarSenha(String cpf, String novaSenha) throws SQLException {
     
        String senhaAtual = clienteDAO.obterSenhaAtual(cpf);
        if (senhaAtual != null && senhaAtual.equals(novaSenha)) {
            throw new IllegalArgumentException("A nova senha não pode ser a mesma que a senha atual.");
        }
        clienteDAO.atualizarSenha(cpf, novaSenha);
    }

}
