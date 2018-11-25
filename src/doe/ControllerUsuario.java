package doe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ControllerUsuario {
	private Validador validador;
	private LinkedHashMap<String, Usuario> usuarios;
	
	public ControllerUsuario() {
		this.usuarios = new LinkedHashMap<>();
		this.validador = new Validador();
	}

	public void cadastraDoador(String id, String nome, String email, String celular, String classe) {
		this.validador.validaCadastro(id, nome, email, celular, classe);
		this.validador.validaClasse(classe);

		if (this.usuarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario ja existente: " + id + ".");
		}
		this.usuarios.put(id, new Doador(id, nome, email, celular, classe));
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

			this.validador.validaCadastro(dadosReceptor[0], dadosReceptor[1], dadosReceptor[2], dadosReceptor[3],
					dadosReceptor[4]);

			this.usuarios.put(dadosReceptor[0], new Receptor(dadosReceptor[0], dadosReceptor[1], dadosReceptor[2],
					dadosReceptor[3], dadosReceptor[4]));
		}

	}

	public String pesquisaUsuarioPorId(String id) {
		this.validador.validaId(id);

		if (!this.usuarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
		}
		return this.usuarios.get(id).toString();
	}

	public String pesquisaUsuarioPorNome(String nome) {
		this.validador.validaNome(nome);
		List<String> chaveUsuarios = new ArrayList<>();
		int aux = 0;
		int qtdMesmoNome = 0;
		String saida = "";

		for (String u : this.usuarios.keySet()) {
			chaveUsuarios.add(u);
		}

		for (int j = 0; j < chaveUsuarios.size(); j++) {
			if (this.usuarios.get(chaveUsuarios.get(j)).getNome().equals(nome)) {
				aux += 1;
			}
		}

		for (int i = 0; i < chaveUsuarios.size(); i++) {
			System.out.println();
			if (this.usuarios.get(chaveUsuarios.get(i)).getNome().equals(nome)) {
				if (qtdMesmoNome == aux - 1) {
					saida += this.usuarios.get(chaveUsuarios.get(i)).toString();
				} else {
					saida += this.usuarios.get(chaveUsuarios.get(i)).toString() + " | ";
					qtdMesmoNome += 1;
				}
			}
		}

		if (saida.equals("")) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + nome + ".");
		}
		return saida;
	}

	public String atualizaUsuario(String id, String nome, String email, String celular) {
		this.validador.validaId(id);

		if (!this.usuarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
		}

		if (!(nome == null) && !(nome.equals(""))) {
			this.usuarios.get(id).setNome(nome);
		}
		if (!(email == null) && !(email.equals(""))) {
			this.usuarios.get(id).setEmail(email);
		}
		if (!(celular == null) && !(celular.equals(""))) {
			this.usuarios.get(id).setCelular(celular);
		}

		return this.usuarios.get(id).toString();
	}

	public void removeUsuario(String id) {
		this.validador.validaId(id);
		
		if (!this.usuarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
		}

		this.usuarios.remove(id);
	}
	
}
