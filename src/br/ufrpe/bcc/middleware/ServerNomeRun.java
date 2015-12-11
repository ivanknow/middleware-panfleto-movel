package br.ufrpe.bcc.middleware;

import br.ufrpe.bcc.negocio.Endereco;
import br.ufrpe.bcc.negocio.ServidorNomes;

public class ServerNomeRun {
	public static void main(String[] args) throws Exception {
		
		run("localhost","5000");
	}
	static ServidorNomes sn = new ServidorNomes();
	public static void run(String host,String port) throws Exception{
		Comm m = new Comm(new Endereco(host, port));

		
		while (true) {
			MiddlewareThread thread = new MiddlewareThread(m.receiveThread()) {
				
				@Override
				public String exec(String mIn) {
					String mOut = new String();
				
					
					
					
					return mOut;
				}
			};
			
			new Thread(thread).start();
		}
	}
}