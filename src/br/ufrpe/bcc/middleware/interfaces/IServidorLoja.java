package br.ufrpe.bcc.middleware.interfaces;

import java.util.List;

import br.ufrpe.bcc.negocio.Panfleto;;

public interface IServidorLoja {
	boolean LoginServidor(String login, String senha, String ip, String port);
	List<Panfleto> RetornarPanfletos();
	boolean AtualizarPanfleto(List<Panfleto> panfletos);
}
