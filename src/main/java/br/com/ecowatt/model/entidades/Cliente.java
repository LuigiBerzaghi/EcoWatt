package br.com.ecowatt.model.entidades;

public class Cliente {

	//atributos
	private String cpf;
	private String nome;
	private String email;
	private String senha;
	
	//método construtor vazio
	
	public Cliente() {
		super();
	}
	
	//método construtor parametrizado
	public Cliente(String cpf, String nome, String email, String senha) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

	//getters e setters
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "\nDados do cliente:\nCPF: " + cpf + "\nNome: " + nome + "\nEmail: " + email + "\nSenha: " + senha;
	}
	
}
