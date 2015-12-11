package br.ufrpe.bcc.middleware;

import br.ufrpe.bcc.negocio.Endereco;
import br.ufrpe.bcc.negocio.ServidorLoja;
import br.ufrpe.bcc.negocio.ServidorNomes;

public class ServerLojaRun {
	public static void main(String[] args) throws Exception {
		run("localhost", "5551");
	}

	public static void run(String host, String port) throws Exception {

		ServidorLoja servico = new ServidorLoja();
		Endereco rs = new Endereco(host, port);

		//rs.(servico.getClass().getSimpleName()); nome da loja

		ServidorNomes sn = new ServidorNomes();

		Comm mCliente = new Comm(new Endereco("localhost", "5000"));
		Comm mServidor = new Comm(rs);

		String reqMsg = new String();

		//passa valores no json

		mCliente.requestAndReceive(reqMsg);

		while (true) {
			MiddlewareThread thread = new MiddlewareThread(mServidor.receiveThread()) {

				@Override
				public String exec(String m) {
					String mOut = new String();
					ServidorLoja servico = new ServidorLoja();
					
					//monta json
					return mOut;
				}
			};
			new Thread(thread).start();
		}
	}
}