package br.ufrpe.bcc.middleware;

import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import br.ufrpe.bcc.middleware.json.JsonConteiner;
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
				public String exec(String mIn) throws ParseException {
					//pegando entrada
					JSONParser parser = new JSONParser();
					Map json = (Map)parser.parse(mIn, new JsonConteiner());
					String op = (String) json.get("op");
					Map data = (Map)json.get("data");
					
					String valor = "";
					switch (op) {
					case "nomesServicos":
						valor = sn.nomesServicos();
						break;
						//falta metodos
					default:
						valor = "operacao desconhecoda";
					}
					
					//preparando saida
					
					JSONObject response = new JSONObject();
					response.put("result",valor);
					
					String mOut = new String();
					
					return mOut;
				}
			};
			
			new Thread(thread).start();
		}
	}
}