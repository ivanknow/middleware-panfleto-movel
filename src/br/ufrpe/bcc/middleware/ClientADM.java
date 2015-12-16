package br.ufrpe.bcc.middleware;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import br.ufrpe.bcc.negocio.Endereco;
import br.ufrpe.bcc.negocio.Panfleto;

public class ClientADM {
	
	public static void main(String[] args) throws Exception {
		
		Comm mCliente = new Comm(new Endereco("localhost", "5551"));
		
		JSONObject response = new JSONObject();
		response.put("op","loginServidor");
		JSONObject data = new JSONObject();
		data.put("login","admin");
		data.put("senha","1234");
		data.put("ip","localhost");
		data.put("porta","5551");
		response.put("data",data);

	

		String m = mCliente.requestAndReceive(response.toJSONString());
		JSONParser jp = new JSONParser();
		JSONObject json = (JSONObject) jp.parse(m);
		
		if (json.get("result").equals("true")) {
			
			List<Panfleto> panfletos = new ArrayList<Panfleto>(){{
				add(new Panfleto("Camiseta Nike","Desc", "www.cea.com.br/produto/2345",39.90));
				add(new Panfleto("Camiseta Puma","Desc", "www.cea.com.br/produto/2347",29.90));
			}};
			
			System.out.println(panfletos);
			
			JSONObject response2 = new JSONObject();
			response2.put("op","atualizarPanfleto");
			data = new JSONObject();
			data.put("panfletos","[{\"titulo\":\"Camiseta Nike\",\"texto\":\"Desc\", \"link\":\"www.cea.com.br/produto/2345\",\"preco\":39.90}]");
			//data.put("panfletos",panfletos);
			response2.put("data",data);
			
			mCliente.requestAndReceive(response2.toJSONString());
		}
		System.out.println(m);
		
		
	}

}
