package doe;

import java.util.LinkedHashMap;

public class ControllerUsuario {
	
	private LinkedHashMap<String,Usuario> usuarios;
	
	public ControllerUsuario() {
		this.usuarios = new LinkedHashMap<String,Usuario>();
	}
	
	public void cadastraDoador(String id, String nome, String email, String celular, String classe) {
		if (nome == null || nome.equals("")) {
			throw new IllegalArgumentException("Entrada invalida: nome nao pode ser vazio ou nulo.");
		}
		if (email == null || email.equals("")) {
			throw new IllegalArgumentException("Entrada invalida: email nao pode ser vazio ou nulo.");
		}
		if (celular == null || celular.equals("")) {
			throw new IllegalArgumentException("Entrada invalida: celular nao pode ser vazio ou nulo.");
		}
		if (classe == null || classe.equals("")) {
			throw new IllegalArgumentException("Entrada invalida: classe nao pode ser vazia ou nula.");
		}
		if (id == null || id.equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		if (this.usuarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario ja existente: "+ id +".");
		}
		
		this.usuarios.put(id, new Usuario(id, nome, email, celular, classe));
	}
	
	public String pesquisaUsuarioPorId(String id) {
		if (id == null || id.equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		if (!this.usuarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
		}
		return this.usuarios.get(id).toString();
	}
	
	public String pesquisaUsuarioPorNome(String nome) {
		if (nome == null || nome.equals("")) {
			throw new IllegalArgumentException("Entrada invalida: nome nao pode ser vazio ou nulo.");
		}
		
		//TODO imprimir usuarios pelo nome e lancar excecao caso nao tenha usuario com o nome recebido
		return null;
	}

	public String atualizaUsuario(String id, String nome, String email, String celular) {
		if (id == null || id.equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		
		return null;
	}
	
	public void removeUsuario(String id) {
		if (id == null || id.equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		if (!this.usuarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
		}
	}



}
