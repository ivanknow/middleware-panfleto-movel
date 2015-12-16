package br.ufrpe.bcc.negocio;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.bcc.middleware.interfaces.IServidorLoja;

public class ServidorLoja implements IServidorLoja{
	
	List<Panfleto> panfletos;
	Usuario usuario = new Usuario("admin","1234");
	Endereco endereco;
	
	public ServidorLoja() {
		this.panfletos = new ArrayList<Panfleto>();
	}
	
	@Override
	public boolean LoginServidor(String login, String senha, String ip,
			String port) {
		List<Usuario> consultaUsuarios = new ArrayList<Usuario>();
		
		Usuario user = new Usuario(login,senha);
		endereco = new Endereco(ip,port);//duvida
		
			if(user.getLogin().equals(usuario.getLogin()) &&
					user.getSenha().equals(usuario.getSenha())){
				return true;
			}
		
		return false;
	}


	@Override
	public List<Panfleto> RetornarPanfletos() {
		
		return this.panfletos;
	}

	@Override
	public boolean AtualizarPanfleto(List<Panfleto> panfletos) {
		System.out.println("As ofertas são: " + panfletos);
		this.panfletos = panfletos;
		return true;
	}
	

	public List<Panfleto> getPanfletos() {
		return panfletos;
	}

	public void setPanfletos(List<Panfleto> panfletos) {
		this.panfletos = panfletos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
}
