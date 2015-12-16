package br.ufrpe.bcc.negocio;

import java.math.BigDecimal;

public class Panfleto {
	private String titulo;
	private String texto;
	private String link;
	private Double preco;
	
	public Panfleto(String titulo, String texto, String link, Double preco) {
		this.titulo = titulo;
		this.texto = texto;
		this.link = link;
		this.preco = preco;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "Panfleto [titulo=" + titulo + ", texto=" + texto + ", link="
				+ link + ", preco=" + preco + "]";
	}
 
}
