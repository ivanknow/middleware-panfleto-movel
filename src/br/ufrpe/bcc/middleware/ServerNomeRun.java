package br.ufrpe.bcc.middleware;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import br.ufrpe.bcc.middleware.json.JsonConteiner;
import br.ufrpe.bcc.negocio.Endereco;
import br.ufrpe.bcc.negocio.ServicoLoja;
import br.ufrpe.bcc.negocio.ServidorNomes;

public class ServerNomeRun {
	public static void main(String[] args) throws Exception {
		
		run("localhost","9999");
	}
	static ServidorNomes sn = new ServidorNomes();
	public static void run(String host,String port) throws Exception{
		
		System.out.println("Rodou o servidor");
		
		Comm m = new Comm(new Endereco(host, port));

		while (true) {
			MiddlewareThread thread = new MiddlewareThread(m.receiveThread()) {
				
				@Override
				public String exec(String mIn) throws ParseException {
					System.out.println(mIn);
					//pegando entrada
					JSONParser parser = new JSONParser();
					Map json = (Map)parser.parse(mIn, new JsonConteiner());
					String op = (String) json.get("op");
					Map data = (Map)json.get("data");
					
					ServicoLoja sl = new ServicoLoja();
					
					String valor = "";
					switch (op) {
					case "cadastrarLoja":
						
						sl.setNomeServico((String)data.get("nomeServico"));
						sl.setIp((String)data.get("ip"));
						sl.setPorta((String)data.get("porta"));
						//sl.setIdentificador((UUID)data.get("identificador"));
						sl.setLogin((String)data.get("login"));
						sl.setSenha((String)data.get("senha"));
						valor = sn.cadastrarServicoLoja(sl);
						break;
						
					case "apagarLoja":
						
						sl.setNomeServico((String)data.get("nomeServico"));
						valor = sn.apagarServicoLoja(sl);
						break;
						
					case "atualizarLoja":	
						
						sl.setNomeServico((String)data.get("nomeServico"));
						valor = sn.atualizarServicoLoja(sl);
						break;
						
					case "retornarLoja":
						
						ServicoLoja loja = sn.retornarLoja((String)data.get("nomeServico"));
						
						valor = "{\"ip\":\""+loja.getIp()+"\",\"porta\":\""+loja.getPorta()+"\"}";
						break;
					case "retornarNomesServicos":
						
						JSONArray jsonArray = new JSONArray();
						
						List<String> nomes = sn.nomesServicos();
						for (int i = 0; i < nomes.size(); i++) {
							JSONObject obj = new JSONObject();
							obj.put("nome", nomes.get(i));
							jsonArray.add(obj);
						}
						
						valor = jsonArray.toJSONString();
						break;
					default:
						valor = "operacao desconhecida";
						break;
					}
					
					//preparando saida
					
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