package br.ufrpe.bcc.middleware;

import java.util.Map;

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
		response.put("op","retornarNomesServicos");
		JSONObject data = new JSONObject();
	
		response.put("data",data);

		//passa valores no json
		System.out.println(response.toJSONString());

		String m = mCliente.requestAndReceive(response.toJSONString());
		System.out.println(m);
	}
}