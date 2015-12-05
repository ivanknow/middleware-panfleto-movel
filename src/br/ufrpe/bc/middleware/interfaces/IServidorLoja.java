package br.ufrpe.bc.middleware.interfaces;

import java.util.List;

import br.ufrpe.bcc.middleware.Panfleto;;

public interface IServidorLoja {
	boolean LoginServidor(String login, String senha, String ip, String port);
	boolean Logout(String login, String senha);
	List<Panfleto> RetornarPanfletos();
	boolean AtualizarPanfleto(List<Panfleto> panfletos);
}
