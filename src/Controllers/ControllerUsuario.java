package Controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import Validador.Validador;
import doe.Item;
import doe.Usuario;

public class ControllerUsuario {
	private Validador validador;
	private LinkedHashMap<String, Usuario> usuarios;
	private ControllerItem ctlItem;
	
	public ControllerUsuario() {
		this.usuarios = new LinkedHashMap<>();
		this.validador = new Validador();
		this.ctlItem = new ControllerItem();
	}

	public String cadastraDoador(String id, String nome, String email, String celular, String classe) {
		this.validador.validaCadastro(id, nome, email, celular, classe);
		this.validador.validaClasse(classe);

		if (this.usuarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario ja existente: " + id + ".");
		}
		this.usuarios.put(id, new Usuario(id, nome, email, celular, classe, "doador"));
		return id;
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

			this.usuarios.put(dadosReceptor[0], new Usuario(dadosReceptor[0], dadosReceptor[1], dadosReceptor[2],
					dadosReceptor[3], dadosReceptor[4], "receptor"));
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
	
	public int adicionaItem(String id, String descricao, int quantidade, String tags, ControllerItem ctlItem) {
		this.validador.verificaCadastroDeItem(id, descricao, quantidade);
		if (!this.usuarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
		}
		
		int idItem = ctlItem.identificador();
		
		if(!(ctlItem.contemDescritor(descricao))) {
			ctlItem.adicionaDescritor(descricao);
			if(quantidade > 0) {
				ctlItem.modificaDescritorSistemaQuantidade(descricao, quantidade);
			}
		}
		
		else {
			ctlItem.modificaDescritorSistemaQuantidade(descricao, quantidade);
		}
		
		this.usuarios.get(id).adicionaItem(idItem, descricao.trim().toLowerCase(), quantidade, tags);
		ctlItem.adicionaItemSistema(new Item(idItem, descricao.trim().toLowerCase(), quantidade,tags,this.usuarios.get(id).getNome(),id));
		
		return idItem;
	}
	
	

	public String exibeItem(int idItem, String idDoador) {
		if (!this.usuarios.containsKey(idDoador)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
		}
		return this.usuarios.get(idDoador).getItem(idItem);
	}
	
	public String atualizaItemParaDoacao(int idItem, String idDoador, int quantidade, String tags, ControllerItem ctlItem) {
        this.validador.validaId(idDoador);
        this.validador.validaIdItem(idItem);
        
        if (!this.usuarios.containsKey(idDoador)) {
            throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
        }
        if (this.usuarios.get(idDoador).getItem(idItem) == null) {
            throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
        }
        
        if(quantidade > 0) {
        	ctlItem.modificaDescritorSistemaQuantidade(this.usuarios.get(idDoador).getItemOb(idItem).getDescricaoItem(), quantidade);
        	ctlItem.modificaQuantidadeItemSistema(idItem, quantidade);
        }
        
        if(!(tags == null)) {
        	ctlItem.modificaTagsItemSistema(idItem, tags);
        }
        
        return this.usuarios.get(idDoador).atualizaItem(idItem, quantidade, tags);
    }
	
	public void removeItemParaDoacao(String idItem, String idDoador, ControllerItem ctlItem) {
		this.validador.validaId(idDoador);
		this.validador.validaId(idItem);
		this.validador.validaIdItem(Integer.parseInt(idItem));
		
		if(!(this.usuarios.containsKey(idDoador))) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
		}
		
		int idItemInt = Integer.parseInt(idItem);
		
		if(this.usuarios.get(idDoador).verificaItem(idItemInt)) {
			ctlItem.modificaDescritorSistemaQuantidade(this.usuarios.get(idDoador).getItemOb(idItemInt).getDescricaoItem(), 0);
		}
		
		this.usuarios.get(idDoador).removeItem(idItemInt);
		ctlItem.removeItemSistema(idItemInt);
		

	}

	public int adicionaItemNecessario(String idReceptor, String descricaoItem, int quantidade, String tags, ControllerItem ctlItem) {
		this.validador.verificaCadastroDeItem(idReceptor, descricaoItem, quantidade);
		
		if (!this.usuarios.containsKey(idReceptor)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idReceptor + ".");
		}
		
		int idItem = ctlItem.identificador();
		
		ctlItem.adicionaItemSistemaNecessario(new Item(idItem, descricaoItem.toLowerCase(), quantidade, tags, this.usuarios.get(idReceptor).getNome(), idReceptor));
		
		return this.usuarios.get(idReceptor).adicionaItem(idItem, descricaoItem.toLowerCase(), quantidade, tags);
		
	}

	public String atualizaItemNecessario(String idReceptor, int idItem, int novaQuantidade, String novasTags, ControllerItem ctlItem) {
		this.validador.validaId(idReceptor);
        this.validador.validaIdItem(idItem);

        if (!this.usuarios.containsKey(idReceptor)) {
            throw new IllegalArgumentException("Usuario nao encontrado: " + idReceptor + ".");
        }
        
        if(novaQuantidade > 0) {
        	ctlItem.modificaQuantidadeItemSistema(idItem, novaQuantidade);
        }
        
        if(!(novasTags == null)) {
        	ctlItem.modificaTagsItemSistemaNecessario(idItem, novasTags);
        }
        
        
        return this.usuarios.get(idReceptor).atualizaItemNecessario(idItem, novaQuantidade, novasTags);
	}
	
	public void removeItemNecessario(String idReceptor, int idItem, ControllerItem ctlItem) {
		this.validador.validaId(idReceptor);
		this.validador.validaIdItem(idItem);
		
		if (!this.usuarios.containsKey(idReceptor)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idReceptor + ".");
		}
		
		ctlItem.removeItemSistemaNecessario(idItem);
		this.usuarios.get(idReceptor).removeItemNecessario(idItem);
		
	}
}
