package br.ufrpe.bc.middleware.interfaces;

import br.ufrpe.bcc.middleware.Panfleto;;

public interface IServidorLoja {
	boolean LoginServidor(String login, String senha, String ip, String port);
	boolean Logout(String login, String senha);
	Panfleto RetornarPanfleto();
	boolean AtualizarPanfleto(String titulo, String texto, String link);
}
