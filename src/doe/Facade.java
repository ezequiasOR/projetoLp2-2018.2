package doe;

import java.io.IOException;

import easyaccept.EasyAccept;

public class Facade {
	private ControllerUsuario ctlUsuarios;
	
	public static void main(String[] args) {
		args = new String[] { "doe.Facade", "testes/acceptance_tests/use_case_1.txt"};
		EasyAccept.main(args);
	}
	
	public Facade() {
		ctlUsuarios = new ControllerUsuario();
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
	/*
	public String atualizaUsuario(String id, String nome, String email, String celular) {
		return ctlUsuarios.atualizaUsuario(id, nome, email, celular);
	}
	
	public void removeUsuario(String id) {
		ctlUsuarios.removeUsuario(id);
	}
	
	*/
	
	
}
