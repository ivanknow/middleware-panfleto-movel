package br.ufrpe.bcc.negocio;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class ServidorNomes {

	Map<String, ServicoLoja> servicos = new HashMap<String, ServicoLoja>();
	
	//ServicoLoja servico;
	
	private static final String loginMaster = "ADM";
	private static final String senhaMaster = "1234";

	public synchronized String cadastrarServicoLoja(ServicoLoja servico) {

		if (servicos.containsKey(servico.getNomeServico())) {

			System.out.println("Serviço já cadastrado!");
			
			return "Serviço já existente";
			
		} else {
			
			UUID id = UUID.randomUUID();
			servico.setIdentificador(id);
			servicos.put(servico.getNomeServico(), servico);
			System.out.println(servicos);
			return "Serviço cadastrado com sucesso";
		}

	}
	
	
	public synchronized String apagarServicoLoja(ServicoLoja servico){
		
		if (servicos.containsKey(servico.getNomeServico())) {
			servicos.remove(servico);
			
			return "Serviço Removido";
		}
		return null;
	}
	
	
	public synchronized String atualizarServicoLoja(ServicoLoja servico){
		
		if (servicos.containsKey(servico.getNomeServico())) {
			servicos.replace(servico.getNomeServico(), servico);
			
			return "Serviço Atualizado";
		}
		return null;
		
	}
	
	public synchronized ServicoLoja retornarLoja(String nomeServico){
		
		if (servicos.containsKey(nomeServico)) {
			
			ServicoLoja servico = servicos.get(nomeServico);
			
			return servico;
		}
		return null;
	}


	private boolean autenticar(String login, String senha, String ip, String porta) {

		ServicoLoja obj = new ServicoLoja();

		if (login.equalsIgnoreCase(obj.getLogin())&& senha.equals(obj.getSenha())) {
			return true;
		} else {
			return false;
		}

	}

	public void ativarDesativarLoja(String login, String senha, String ip, String porta, boolean ativarDesativar) {

		ServicoLoja servicoLoja = new ServicoLoja();
		if (servicos.get(servicoLoja.getLogin()).equals(login) && servicos.get(servicoLoja.getSenha()).equals(senha)){
			servicoLoja.setAtivo(ativarDesativar);
		}

	}
	
	

	public synchronized String nomesServicos() {
		StringBuilder nomes = new StringBuilder();
		Set<String> set = servicos.keySet();
		for (String s : set) {
			nomes.append("(" + s + ")");
		}
		return nomes.toString();
	}
	
	
//	public boolean atualizarOfertas(String login, String senha, String nomeServico, List<Oferta> ofertas) {
//	
//	if (servicos.containsKey(nomeServico)) {
//		ServicoLoja servico = servicos.get(nomeServico);
//		if (servico.getLogin().equalsIgnoreCase(login) && servico.getSenha().equalsIgnoreCase(senha)) {
//			servico.setOfertas(ofertas);
//		}
//		return true;
//	}
//	return false;
//}

}
