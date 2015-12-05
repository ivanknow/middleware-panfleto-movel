package br.ufrpe.bcc.middleware;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.bc.middleware.interfaces.IServidorLoja;

public class ServidorLoja implements IServidorLoja{

	@Override
	public boolean LoginServidor(String login, String senha, String ip,
			String port) {
		List<Usuario> consultaUsuarios = new ArrayList<Usuario>();
		
		Usuario usuario = new Usuario(login,senha);
		Endereco endereco = new Endereco(ip,port);
		
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
		
		return false;
	}

	@Override
	public Panfleto RetornarPanfleto() {
		
		return null;
	}

	@Override
	public boolean AtualizarPanfleto(String titulo, String texto, String link) {
		
		return false;
	}

}
