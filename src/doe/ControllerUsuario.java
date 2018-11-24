package doe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class ControllerUsuario {
	
	private LinkedHashMap<String,Usuario> usuarios;
	private Validador validador;
	
	public ControllerUsuario() {
		this.usuarios = new LinkedHashMap<String,Usuario>();
		this.validador = new Validador();
	}
	
	public void lerReceptores(String caminho) throws IOException {
		Scanner sc = new Scanner(new File(caminho));
		String linha = null;
		while (sc.hasNextLine()) {
			linha = sc.nextLine();
			if (linha.equals("id,nome,e-mail,celular,classe")) {
				continue;
			}
			String[] dadosReceptor = linha.split(",");
			//excecoes
			this.usuarios.put(dadosReceptor[0], new Receptor(dadosReceptor[0], dadosReceptor[1], 
					dadosReceptor[2], dadosReceptor[3], dadosReceptor[4]));
		}
	}

	public void cadastraDoador(String id, String nome, String email, String celular, String classe) {
		this.validador.validaCadastroDeDoador(id, nome, email, celular, classe);
		this.validador.verificaSeClasseExiste(classe);
		
		if (this.usuarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario ja existente: " + id + ".");
		}
		
		this.usuarios.put(id, new Doador(id, nome, email, celular, classe));
	}
	
	public String pesquisaUsuarioPorId(String id) {
		this.validador.validaPesquisaPorId(id);
		
		if (!this.usuarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
		}
		return this.usuarios.get(id).toString();
	}
	
	public String pesquisaUsuarioPorNome(String nome) {
		this.validador.validaPesquisaPorNome(nome);
		
		//TODO imprimir usuarios pelo nome e lancar excecao caso nao tenha usuario com o nome recebido
		return null;
	}

	public String atualizaUsuario(String id, String nome, String email, String celular) {
		if (id == null || id.equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		this.usuarios.get(id).setNome(nome); 
		this.usuarios.get(id).setEmail(email); 
		this.usuarios.get(id).setCelular(celular);
		
		return this.usuarios.get(id).toString();
	}
	
	public void removeUsuario(String id) {
		if (id == null || id.equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		if (!this.usuarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
		}
		
		this.usuarios.remove(id);
	}




}
