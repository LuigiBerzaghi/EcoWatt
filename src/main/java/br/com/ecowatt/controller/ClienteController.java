package br.com.ecowatt.controller;

import br.com.ecowatt.view.ClienteView;
import br.com.ecowatt.model.entidades.Cliente;

public class ClienteController {
	
	//Método que edita os dados do Cliente
	public void editaDados(Cliente cliente, String nome, String cpf, String email, String senha) {
		
		cliente.setNome(nome);
		cliente.setCpf(cpf);
		cliente.setEmail(email);
		cliente.setSenha(senha);
		
		System.out.printf("\nOs dados do usuário %s foram atualizados com sucesso! \nSeus novos dados são: %s \n", cliente.getNome(),cliente.toString());	
		
	}

	
    // Método para consultar os dados de um cliente    
	public void consultarDadosCliente(Cliente cliente) {
		 System.out.println(ClienteView.exibirCliente(cliente));
  }
	
	
	
}
