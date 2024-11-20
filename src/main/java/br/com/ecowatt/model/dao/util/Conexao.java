package br.com.ecowatt.model.dao.util;

import java.sql.Connection;

import java.sql.SQLException;
import oracle.jdbc.datasource.impl.OracleDataSource;

public class Conexao {

private static final String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
	
	public static Connection getConexao() throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		
		//configurando a url
		ods.setURL(url);
		
		//configurando o user
		ods.setUser(Credenciais.user);
		
		//configurando a senha
		ods.setPassword(Credenciais.pwd);
		
		//obtendo e retornando a conexão com o JDBC
		return ods.getConnection();
		
		}
	
}
