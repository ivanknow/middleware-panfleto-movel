package br.ufrpe.bcc.middleware;

import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import br.ufrpe.bcc.middleware.json.JsonConteiner;
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

		JSONObject response = new JSONObject();
		response.put("op","cadastrarLoja");
		JSONObject data = new JSONObject();
		data.put("ip","localhost");
		data.put("porta", "5001");
		//data.put("identificador","");
		data.put("nomeServico","cea");
		data.put("login","cea");
		data.put("senha","1234");
		response.put("data",data);

		//passa valores no json

		mCliente.requestAndReceive(response.toJSONString());

		while (true) {
			MiddlewareThread thread = new MiddlewareThread(mServidor.receiveThread()) {

				@Override
				public String exec(String m) {
					String mOut = new String();
					JSONParser parser = new JSONParser();
					try {
						Map json = (Map)parser.parse(m, new JsonConteiner());
						String op = (String) json.get("op");
						System.out.println();
						
					} catch (ParseException e) {
						
						e.printStackTrace();
					}
					ServidorLoja servico = new ServidorLoja();
					
					//monta json
					return mOut;
				}
			};
			new Thread(thread).start();
		}
	}
}