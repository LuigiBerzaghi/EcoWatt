package br.com.ecowatt.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ecowatt.model.dao.util.Conexao;
import br.com.ecowatt.model.entidades.Cliente;

public class ClienteDAO {
	
	//Método inserir
	
	public void inserir(Cliente cliente) throws SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = Conexao.getConexao();
			conn.setAutoCommit(false);
			
			//inserir o cliente
			
			String sqlCliente = "INSERT INTO cliente (cpf, nome, email, senha) VALUES (?, ?, ?, ?)";
			stmt = conn.prepareStatement(sqlCliente);
			stmt.setString(1, cliente.getCpf());
			stmt.setString(2, cliente.getNome());
			stmt.setString(3, cliente.getEmail());
			stmt.setString(4, cliente.getSenha());
			stmt.executeUpdate();
			
			conn.commit();
			System.out.println("\nCliente cadastrado com sucesso!");
			}catch(SQLException e){
				if (conn != null) {
		            conn.rollback();
				}
				throw e;
		
			}finally {
				// Fechando os recursos
				if(stmt != null) stmt.close();
				System.out.println("\nRecursos fechados com sucesso!");
			}
	}
	
	//Método excluir
	public void excluir(String cpf) throws SQLException {
		String sqlDeleteCliente = "DELETE FROM cliente WHERE cpf = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = Conexao.getConexao();
			stmt = conn.prepareStatement(sqlDeleteCliente);
			stmt.setString(1, cpf);
			int linhasAfetadas = stmt.executeUpdate();
			if (linhasAfetadas > 0) {
                System.out.println("\nCliente excluído com sucesso!");
            } else {
                System.out.println("\nNenhum cliente encontrado com o CPF fornecido.");
            }
        } catch (SQLException e) {
            System.err.println("\nErro ao tentar excluir o cliente: " + e.getMessage());
        } finally {
        	if(stmt != null) stmt.close();
			System.out.println("\nRecursos fechados com sucesso!\n");
        }
    }
	
	public void atualizar(Cliente cliente) throws SQLException {
	    String sqlAtualizaCliente = "UPDATE cliente SET nome = ?, email = ?, senha = ? WHERE cpf = ?";
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    try {
	        conn = Conexao.getConexao();
	        stmt = conn.prepareStatement(sqlAtualizaCliente);
	        stmt.setString(1, cliente.getNome());
	        stmt.setString(2, cliente.getEmail());
	        stmt.setString(3, cliente.getSenha());
	        stmt.setString(4, cliente.getCpf());  
	        stmt.executeUpdate();
	        System.out.println("\nCliente atualizado com sucesso!");
	    } catch(SQLException e) {
	        System.err.println("\nErro ao atualizar os dados do cliente: " + e.getMessage());
	    } finally {
	        fecharRecursos(stmt, conn);
	    }
	}

	
	// Método listar
	public List<Cliente> selecionar() throws SQLException {
	    List<Cliente> listaCliente = new ArrayList<>();
	    String sqlAtualizaCliente = "SELECT * FROM cliente";
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        conn = Conexao.getConexao();
	        stmt = conn.prepareStatement(sqlAtualizaCliente);
	        rs = stmt.executeQuery();

	        while (rs.next()) {
	            Cliente cliente = new Cliente();
	            cliente.setCpf(rs.getString("cpf"));
	            cliente.setNome(rs.getString("nome"));
	            cliente.setEmail(rs.getString("email"));
	            cliente.setSenha(rs.getString("senha"));
	            
	            listaCliente.add(cliente);
	            System.out.println("\nCliente adicionado: " + cliente.getNome());
	        }
	    } catch (SQLException e) {
	        System.err.println("\nErro ao listar clientes: " + e.getMessage());
	    } finally {
	        fecharRecursos(stmt, conn, rs);
	    }
	    return listaCliente;
	}

	
    // Método para fechar recursos
    private void fecharRecursos(PreparedStatement pstmt, Connection conexao) {
        try {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException e) {
            System.err.println("\nErro ao fechar os recursos: " + e.getMessage());
        }
    }

    // Sobrecarga do método para incluir ResultSet
    private void fecharRecursos(PreparedStatement pstmt, Connection conexao, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            fecharRecursos(pstmt, conexao);
        } catch (SQLException e) {
            System.err.println("\nErro ao fechar o ResultSet: " + e.getMessage());
        }
        
    }	
    
    public Cliente validarLogin(String email, String senha) throws SQLException {
        String sql = "SELECT * FROM cliente WHERE email = ? AND senha = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = Conexao.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setCpf(rs.getString("cpf"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setSenha(rs.getString("senha"));
                return cliente;  // Retorna o cliente encontrado
            } else {
                return null;  // Retorna null se não encontrar nenhum cliente com o email e senha
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao validar login: " + e.getMessage());
        } finally {
            fecharRecursos(stmt, conn, rs);
        }
    }
    
 // Método para buscar cliente por CPF
    public Cliente obterPorCpf(String cpf) throws SQLException {
        String sql = "SELECT * FROM cliente WHERE cpf = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = Conexao.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpf);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setCpf(rs.getString("cpf"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setSenha(rs.getString("senha"));
                return cliente;
            } else {
                return null;  
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao obter cliente por CPF: " + e.getMessage());
        } finally {
            fecharRecursos(stmt, conn, rs);
        }
    }

}