package br.ufrpe.bcc.middleware;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import br.ufrpe.bcc.middleware.json.JsonConteiner;
import br.ufrpe.bcc.negocio.Endereco;
import br.ufrpe.bcc.negocio.Panfleto;
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

		JSONObject request = new JSONObject();
		request.put("op","cadastrarLoja");
		JSONObject data = new JSONObject();
		data.put("ip","localhost");
		data.put("porta", "5551");
		//data.put("identificador","");
		data.put("nomeServico","cea");
		data.put("login","cea");
		data.put("senha","1234");
		request.put("data",data);

		//passa valores no json

		System.out.println(mCliente.requestAndReceive(request.toJSONString()));
		ServidorLoja sl = new ServidorLoja();

		while (true) {
			MiddlewareThread thread = new MiddlewareThread(mServidor.receiveThread()) {

				@Override
				public String exec(String m) throws ParseException{
					System.out.println("Server Loja");
					JSONParser parser = new JSONParser();
					
						Map json = (Map)parser.parse(m, new JsonConteiner());
						String op = (String) json.get("op");
						System.out.println();
						
						
						String valor = "";
						LinkedHashMap data = (LinkedHashMap) json.get("data");
						switch (op) {
						case "loginServidor":
							
							boolean b = sl.LoginServidor((String)data.get("login"), (String)data.get("senha"), (String)data.get("ip"), (String)data.get("porta"));
							valor = ""+b;
							break;
							
						case "atualizarPanfleto":
							
							System.out.println(parser.parse((String) data.get("panfletos")));
							JSONArray ba = (JSONArray) parser.parse((String) data.get("panfletos"));
							List<Panfleto> lista = new ArrayList<Panfleto>();
							for(int i = 0;i<ba.size();i++){
								JSONObject o = (JSONObject) ba.get(i);	
								Panfleto p = new Panfleto((String)o.get("titulo"),(String) o.get("texto"),(String) o.get("link"),(Double) o.get("preco"));
								lista.add(p);
							}
							
							sl.AtualizarPanfleto(lista);
							valor = "true";
							break;
							
						case "retornarPanfletos":
							
							JSONArray jsonArray = new JSONArray();
							
							List<Panfleto> panfletos = sl.RetornarPanfletos();
							for(Panfleto p:panfletos){
								JSONObject obj = new JSONObject();
								obj.put("titulo", p.getTitulo());
								
								obj.put("texto", p.getTexto());
								jsonArray.add(obj);
								
							}
								
							
							valor = jsonArray.toJSONString();
							break;
							
							
						default:
							valor = "operacao desconhecida";
						}
						
						
					
					//ServidorLoja servico = new ServidorLoja();
					
					//monta json
					
					JSONObject response = new JSONObject();
					response.put("result",valor);
					
					String mOut = response.toJSONString();
					
					return mOut;
				}
			};
			new Thread(thread).start();
		}
	}
}