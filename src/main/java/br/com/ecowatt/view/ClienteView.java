package br.com.ecowatt.view;

import br.com.ecowatt.model.entidades.Cliente;

public class ClienteView {
	
    public static String exibirCliente(Cliente cliente) {
        return cliente.toString();
    }

}
