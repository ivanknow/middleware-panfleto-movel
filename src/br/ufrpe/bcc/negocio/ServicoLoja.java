package br.ufrpe.bcc.negocio;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class ServicoLoja implements Serializable{
	
	private static final long serialVersionUID = -2734963953080470754L;
	private String ip;
	private String porta;
	private UUID identificador;
	private String nomeServico;
	private int requisicoes;
	private String login;
	private String senha;
	private boolean ativo = true;
	private List<Oferta> ofertas; 
	
//	public ServicoLoja(String ip, String porta) {
//		super();
//		this.ip = ip;
//		this.porta = porta;
	
//	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPorta() {
		return porta;
	}
	public void setPorta(String porta) {
		this.porta = porta;
	}
	public UUID getIdentificador() {
		return identificador;
	}
	public void setIdentificador(UUID identificador) {
		this.identificador = identificador;
	}
	public String getNomeServico() {
		return nomeServico;
	}
	public void setNomeServico(String nomeServico) {
		this.nomeServico = nomeServico;
	}
	public int getRequisicoes() {
		return requisicoes;
	}
	public void setRequisicoes(int requisicoes) {
		this.requisicoes = requisicoes;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public List<Oferta> getOfertas() {
		return ofertas;
	}
	public void setOfertas(List<Oferta> ofertas) {
		this.ofertas = ofertas;
	}
	@Override
	public String toString() {
		return "RegistroServidor [ip=" + ip + ", porta=" + porta + ", identificador=" + identificador + ", nomeServico="
				+ nomeServico + ", requisicoes=" + requisicoes + "]";
	}

}
