package br.ufrpe.bcc.negocio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ServidorNomes {

	Map<String, List<RegistroLoja>> servicos;
	
	public ServidorNomes() {
		servicos = new HashMap<String, List<RegistroLoja>>();
	}

	private static final String loginMaster = "ADM";
	private static final String senhaMaster = "1234";

	public boolean CadastrarServicoLoja(RegistroLoja servico) {

		if (servicos.containsKey(servico.getNomeServico())) {

			List<RegistroLoja> listaServico = servicos.get(servico.getNomeServico());
			servico.setIdentificador("" + listaServico.size() + 1 + "");

			listaServico.add(servico);
			return true;
		} else {
			return false;
		}

	}

	public void atualizarOfertas(String login, String senha,
			List<Oferta> ofertas) {
		// TODO atualiza o cadastro de ofertas da loja
	}

	private boolean autenticar(String login, String senha, String ip, String porta) {

		RegistroLoja obj = new RegistroLoja(ip, porta);

		if (login.equalsIgnoreCase(obj.getLogin())
				&& senha.equals(obj.getSenha())) {
			return true;
		} else {
			return false;
		}

	}

	public void ativarDesativarLoja(String login, String senha, String ip, String porta, boolean ativarDesativar) {

		RegistroLoja servicoLoja = new RegistroLoja(ip, porta);
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
