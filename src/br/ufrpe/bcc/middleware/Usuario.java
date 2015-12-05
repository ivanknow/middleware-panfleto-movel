package br.ufrpe.bcc.middleware;

public class Usuario {
	private int id;
	private String login;
	private String senha;
	
	public Usuario(String login, String senha){
	this.login = login;
	this.senha = senha;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getLogin(){
		return this.login;
	}
	
	public void setLogin(String login){
		this.login = login;
	}
	
	public String getSenha(){
		return this.senha;
	}
	
	public void setSenha(String senha){
		this.senha = senha;
	}
}
