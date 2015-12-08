package br.ufrpe.bcc.negocio;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class ServidorNomes {

	Map<String, ServicoLoja> servicos;
	
	private static final String loginMaster = "ADM";
	private static final String senhaMaster = "1234";

	public synchronized void CadastrarServicoLoja(ServicoLoja servico) {

		if (servicos.containsKey(servico.getNomeServico())) {

			System.out.println("Serviço já cadastrado!");
			
		} else {
			
			UUID id = UUID.randomUUID();
			servico.setIdentificador(id);
			servicos.put(servico.getNomeServico(), servico);
			
		}

	}

	public void atualizarOfertas(String login, String senha, String nomeServico, List<Oferta> ofertas) {
		
		if (servicos.containsKey(nomeServico)) {
			ServicoLoja servico = servicos.get(nomeServico);
			if (servico.getLogin().equalsIgnoreCase(login) && servico.getSenha().equalsIgnoreCase(senha)) {
				servico.setOfertas(ofertas);
			}
		}	
	}

	private boolean autenticar(String login, String senha, String ip, String porta) {

		ServicoLoja obj = new ServicoLoja(ip, porta);

		if (login.equalsIgnoreCase(obj.getLogin())&& senha.equals(obj.getSenha())) {
			return true;
		} else {
			return false;
		}

	}

	public void ativarDesativarLoja(String login, String senha, String ip, String porta, boolean ativarDesativar) {

		ServicoLoja servicoLoja = new ServicoLoja(ip, porta);
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

}
