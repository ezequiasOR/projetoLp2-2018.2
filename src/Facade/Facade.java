package Facade;

import java.io.IOException;

import Controllers.ControllerItem;
import Controllers.ControllerUsuario;
import easyaccept.EasyAccept;

public class Facade {
	
	private ControllerItem ctlItem;
	private ControllerUsuario ctlUsuarios;

	
	public static void main(String[] args) {
		args = new String[] { "Facade.Facade", "testes/acceptance_tests/use_case_1.txt", "testes/acceptance_tests/use_case_2.txt", "testes/acceptance_tests/use_case_3.txt", "testes/acceptance_tests/use_case_4.txt"};
		EasyAccept.main(args);
	}
	
	public Facade() {
		ctlItem = new ControllerItem();
		ctlUsuarios = new ControllerUsuario();
	}
	
	public void lerReceptores(String caminho) throws IOException {
		ctlUsuarios.lerReceptores(caminho);
	}
	
	public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
		return ctlUsuarios.cadastraDoador(id, nome, email, celular, classe);
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
	
	public int adicionaItemParaDoacao(String idDoador, String descricao, int quantidade, String tags) {
		return ctlUsuarios.adicionaItem(idDoador, descricao, quantidade, tags, ctlItem);
	}
	
	
	public String exibeItem(int idItem, String idDoador) {
		return ctlUsuarios.exibeItem(idItem, idDoador);
	}
	
	public String atualizaItemParaDoacao(int idItem, String idDoador, int quantidade, String tags) {
		return ctlUsuarios.atualizaItemParaDoacao(idItem, idDoador, quantidade, tags,ctlItem);
	}
	
	
	public void removeItemParaDoacao(String idItem, String idDoador) {
		ctlUsuarios.removeItemParaDoacao(idItem, idDoador, ctlItem);
	}
	
	public String listaDescritorDeItensParaDoacao() {
		return ctlItem.listarDescritores();
	}
	
	public String listaItensParaDoacao() {
		return ctlItem.listarItensNoSistema();
	}
	
	
	public String pesquisaItemParaDoacaoPorDescricao(String descricao) {
		return ctlItem.listaItemPorDescricao(descricao);
	}
	
	
	public int adicionaItemNecessario(String idReceptor, String descricaoItem, int quantidade, String tags) {
		return ctlUsuarios.adicionaItemNecessario(idReceptor, descricaoItem, quantidade, tags);
	}
	/*
	public String listaItensNecessarios() {
		return ctlItem.listaItensNecessarios();
	}
	*/
	
	public String atualizaItemNecessario(String idReceptor, int idItem, int novaQuantidade, String novasTags) {
		return ctlUsuarios.atualizaItemNecessario(idReceptor, idItem, novaQuantidade, novasTags);
	}
	
	public void removeItemNecessario(String idReceptor, int idItem) {
		ctlUsuarios.removeItemNecessario(idReceptor, idItem);
	}
	
}

