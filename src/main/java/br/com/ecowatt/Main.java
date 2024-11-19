package br.com.ecowatt;

import java.sql.SQLException;

import br.com.ecowatt.controller.ClienteController;
import br.com.ecowatt.model.dao.ClienteDAO;
import br.com.ecowatt.model.entidades.Cliente;

public class Main {

	public static void main(String[] args) throws SQLException {
		 
		Cliente c1 = new Cliente("240.141.448-79", "Luigi", "lbhsg520741778@gmail.com","senha");
		ClienteController cc1 = new ClienteController();
		ClienteDAO cd = new ClienteDAO();
		
		cc1.consultarDadosCliente(c1);
		cd.inserir(c1);
		cd.selecionar();
		cc1.editaDados(c1, "Luiza", "240.141.448-79", "lbhsg520741778@gmail.com", "senhanova");
		cd.atualizar(c1);
		cd.selecionar();
	}

}
