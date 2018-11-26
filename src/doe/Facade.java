package doe;

import java.io.IOException;

import easyaccept.EasyAccept;

public class Facade {
	private ControllerUsuario ctlUsuarios;
	private ControllerItem ctlItem;
	
	public static void main(String[] args) {
		args = new String[] { "doe.Facade", "testes/acceptance_tests/use_case_1.txt", "testes/acceptance_tests/use_case_2.txt"};
		EasyAccept.main(args);
	}
	
	public Facade() {
		ctlUsuarios = new ControllerUsuario();
		ctlItem = new ControllerItem();
	}
	
	public void lerReceptores(String caminho) throws IOException {
		ctlUsuarios.lerReceptores(caminho);
	}
	
	public void adicionaDoador(String id, String nome, String email, String celular, String classe) {
		ctlUsuarios.cadastraDoador(id, nome, email, celular, classe);
	}
	
	public String pesquisaUsuarioPorId(String id) {
		return ctlUsuarios.pesquisaUsuarioPorId(id);
	}
	
	public String pesquisaUsuarioPorNome(String nome) {
		return ctlUsuarios.pesquisaUsuarioPorNome(nome);
	}
	
	public String atualizaUsuario(String id, String nome, String email, String celular) {
		return ctlUsuarios.atualizaUsuario(id, nome, email, celular);
	}
	
	public void removeUsuario(String id) {
		ctlUsuarios.removeUsuario(id);
	}
	
	
	public void adicionaDescritor(String descricao) {
		ctlItem.adicionaDescritor(descricao);
	}
	
	public void adicionaItemParaDoacao(String idDoador, String descricao, int quantidade, String tags) {
		ctlUsuarios.adicionaItem(idDoador, descricao, quantidade, tags);
	}
	
	/*
	public String exibeItem(int idItem, String idDoador) {
		return ctlItem.exibeItem(idItem, idDoador);
	}
	
	public String atualizaItemParaDoacao(int idItem, String idDoador, int quantidade, String tags) {
		return ctlItem.atualizaItemParaDoacao(idItem, idDoador, quantidade, tags);
	}
	
	public void removeItemParaDoacao(int idItem, String idDoador) {
		ctlItem.removeItemParaDoacao(idItem, idDoador);
	}
	*/
	
}
