package br.ufrpe.bcc.negocio;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.bcc.middleware.interfaces.IServidorLoja;

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
	public List<Panfleto> RetornarPanfletos() {
		//duvida
		return this.panfletos;
	}

	@Override
	public boolean AtualizarPanfleto(List<Panfleto> panfletos) {
		this.panfletos = panfletos;
		return true;
	}
}
