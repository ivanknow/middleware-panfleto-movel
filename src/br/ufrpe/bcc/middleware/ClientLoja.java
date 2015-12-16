package br.ufrpe.bcc.middleware;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import br.ufrpe.bcc.middleware.json.JsonConteiner;
import br.ufrpe.bcc.negocio.Endereco;
import br.ufrpe.bcc.negocio.ServidorLoja;
import br.ufrpe.bcc.negocio.ServidorNomes;

public class ClientLoja {
	public static void main(String[] args) throws Exception {
		run("localhost", "5551");
	}

	public static void run(String host, String port) throws Exception {

		Comm mCliente = new Comm(new Endereco("localhost", "5000"));

		JSONObject response = new JSONObject();
		response.put("op", "retornarNomesServicos");
		JSONObject data = new JSONObject();

		response.put("data", data);

		// passa valores no json
		// System.out.println(response.toJSONString());

		JSONParser jp = new JSONParser();

		JSONObject m = (JSONObject) jp.parse(mCliente
				.requestAndReceive(response.toJSONString()));
		System.out.println(m);
		JSONArray n = (JSONArray) jp.parse((String) m.get("result"));
		JSONObject nomeServer = (JSONObject) n.get(0);

		response = new JSONObject();
		response.put("op", "retornarLoja");
		data = new JSONObject();
		data.put("nomeServico", nomeServer.get("nome"));
		response.put("data", data);

		String enderecoServidor = mCliente.requestAndReceive(response
				.toJSONString());

		System.out.println(enderecoServidor);
		
		JSONObject endereco  = (JSONObject) jp.parse(enderecoServidor);
		
		JSONObject obj =  (JSONObject) jp.parse((String) endereco.get("result"));
		
	
		String ip = (String) obj.get("ip");
		String porta = (String) obj.get("porta");
		Comm mCliente2 = new Comm(new Endereco(ip, porta));
		
		JSONObject response2 = new JSONObject();
		response2.put("op", "retornarPanfletos");
	
		System.out.println(mCliente2.requestAndReceive(response2.toJSONString()));
	}

}