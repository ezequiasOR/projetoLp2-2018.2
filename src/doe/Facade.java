package doe;

import java.io.IOException;

import easyaccept.EasyAccept;

public class Facade {
	
	private ControllerGeral ctlGeral;

	
	public static void main(String[] args) {
		args = new String[] { "doe.Facade", "testes/acceptance_tests/use_case_1.txt", "testes/acceptance_tests/use_case_2.txt"};
		EasyAccept.main(args);
	}
	
	public Facade() {
		ctlGeral = new ControllerGeral();
	}
	
	public void lerReceptores(String caminho) throws IOException {
		ctlGeral.lerReceptores(caminho);
	}
	
	public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
		return ctlGeral.adicionaDoador(id, nome, email, celular, classe);
	}
	
	public String pesquisaUsuarioPorId(String id) {
		return ctlGeral.pesquisaUsuarioPorId(id);
	}
	
	public String pesquisaUsuarioPorNome(String nome) {
		return ctlGeral.pesquisaUsuarioPorNome(nome);
	}
	
	public String atualizaUsuario(String id, String nome, String email, String celular) {
		return ctlGeral.atualizaUsuario(id, nome, email, celular);
	}
	
	public void removeUsuario(String id) {
		ctlGeral.removeUsuario(id);
	}
	
	
	public void adicionaDescritor(String descricao) {
		ctlGeral.adicionaDescritor(descricao);
	}
	
	public int adicionaItemParaDoacao(String idDoador, String descricao, int quantidade, String tags) {
		return ctlGeral.adicionaItemParaDoacao(idDoador, descricao, quantidade, tags);
	}
	
	
	public String exibeItem(int idItem, String idDoador) {
		return ctlGeral.exibeItem(idItem, idDoador);
	}
	/*
	public String atualizaItemParaDoacao(int idItem, String idDoador, int quantidade, String tags) {
		return ctlItem.atualizaItemParaDoacao(idItem, idDoador, quantidade, tags);
	}
	
	public void removeItemParaDoacao(int idItem, String idDoador) {
		ctlItem.removeItemParaDoacao(idItem, idDoador);
	}
	*/
	
}
