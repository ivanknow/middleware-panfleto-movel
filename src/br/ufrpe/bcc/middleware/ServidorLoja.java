package br.ufrpe.bcc.middleware;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.bc.middleware.interfaces.IServidorLoja;

public class ServidorLoja implements IServidorLoja{
	
	List<Panfleto> panfletos;
	
	public ServidorLoja() {
		this.panfletos = new ArrayList<Panfleto>();
	}
	
	@Override
	public boolean LoginServidor(String login, String senha, String ip,
			String port) {
		List<Usuario> consultaUsuarios = new ArrayList<Usuario>();
		
		Usuario usuario = new Usuario(login,senha);
		Endereco endereco = new Endereco(ip,port);//duvida
		
		for(Usuario user : consultaUsuarios){
			if(user.getLogin().equals(usuario.getLogin()) &&
					user.getSenha().equals(usuario.getSenha())){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean Logout(String login, String senha) {
		//duvida
		return false;
	}

	@Override
	public Panfleto RetornarPanfleto() {
		//duvida
		return null;
	}

	@Override
	public boolean AtualizarPanfleto(Panfleto panfleto, String titulo, String texto, String link) {
		int index = panfletos.indexOf(panfleto);
		panfleto.setTitulo(titulo);
		panfleto.setTexto(texto);
		panfleto.setLink(link);
		
		panfletos.set(index, panfleto);
		return true;
	}

}
