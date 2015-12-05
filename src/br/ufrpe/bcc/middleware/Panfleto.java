package br.ufrpe.bcc.middleware;

public class Panfleto {
	private String titulo;
	private String texto;
	private String link;
	
	public Panfleto(String titulo, String texto, String link) {
		this.titulo = titulo;
		this.texto = texto;
		this.link = link;
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
}
