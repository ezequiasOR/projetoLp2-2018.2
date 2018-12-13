package Controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import Comparators.ComparatorMatchItens;
import Validador.Validador;
import doe.Item;
import doe.Usuario;

/**
 * Representacao do controle de usuarios.
 * 
 * @author Joao Vitor de Melo Cavalcante e Souza
 * @author Ezequias de Oliveira Rocha
 * @author Felipe Jeronimo Bernardo da Silva
 *
 */
public class ControllerUsuario {

	/**
	 * Validador de controle de usuario.
	 */
	private Validador validador;

	/**
	 * Map responsavel por armazenar usuarios.
	 */
	private LinkedHashMap<String, Usuario> usuarios;

	/**
	 * Atributo responsavel por controlar item.
	 */
	private ControllerItem ctlItem;

	private List<String> itensDoados;

	/**
	 * Construtor de controle de usuario.
	 * 
	 * @param ctlItem
	 */
	public ControllerUsuario(ControllerItem ctlItem) {
		this.usuarios = new LinkedHashMap<>();
		this.validador = new Validador();
		this.ctlItem = ctlItem;
		this.itensDoados = new ArrayList<>();
	}

	/**
	 * Cadastra um doador no sistema.
	 * 
	 * @param id      Id do usuario (doador)
	 * @param nome    Nome do usuario.
	 * @param email   Email do usuario.
	 * @param celular Numero do celular do usuario.
	 * @param classe  Classe do usuario (so podera ser PESSOA_FISICA, IGREJA, ONG,
	 *                ORGAO PUBLICO MUNICIPAL, ORGAO PUBLICO ESTADUAL, ORGAO PUBLICO
	 *                FEDERAL, ASSOCIACAO, SOCIEDADE).
	 * @return Retorna o Id do usuario cadastrado.
	 */
	public String cadastraDoador(String id, String nome, String email, String celular, String classe) {
		this.validador.validaCadastro(id, nome, email, celular, classe);
		this.validador.validaClasse(classe);

		if (this.usuarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario ja existente: " + id + ".");
		}
		this.usuarios.put(id, new Usuario(id, nome, email, celular, classe, "doador"));
		return id;
	}

	/**
	 * Le usuarios receptores de um arquivo de texto.
	 * 
	 * @param caminho Arquivo de texto em string.
	 * @throws IOException
	 */
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
		sc.close();

	}

	/**
	 * Pesquisa um usuario pelo seu id no sistema.
	 * 
	 * @param id Id do usuario a ser pesquisado.
	 * @return Retorna a representacao textual do usuario pesquisado.
	 */
	public String pesquisaUsuarioPorId(String id) {
		this.validador.validaId(id);

		if (!this.usuarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
		}
		return this.usuarios.get(id).toString();
	}

	/**
	 * Pesquisa por todos os usuarios no sistema com um mesmo nome.
	 * 
	 * @param nome Nome a ser consultado no sistema.
	 * @return Uma representacao em string de todos os usuarios com o mesmo nome.
	 */
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

	/**
	 * Atualiza as informarcoes de um usuario ja cadastrado.
	 * 
	 * @param id      Id do usuario (este e imutavel).
	 * @param nome    Novo nome desejado.
	 * @param email   Novo email desejado.
	 * @param celular Novo numero de celular desejado.
	 * @return Retorna a nova representacao textual do usuario.
	 */
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

	/**
	 * Remove um usuario cadastrado no sistema.
	 * 
	 * @param id Id do usuario a ser removido.
	 */
	public void removeUsuario(String id) {
		this.validador.validaId(id);

		if (!this.usuarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
		}

		this.usuarios.remove(id);
	}

	/**
	 * Adiciona um item em um usuario (doador). A Id do item e gerada
	 * automaticamente pelo sistema.
	 * 
	 * @param id         Id do usuario a adicionar o item em sua conta (e no
	 *                   sistema).
	 * @param descricao  Descricao do item a ser cadastrado.
	 * @param quantidade Quantidade do item a ser cadastrado.
	 * @param tags       Tags do item a ser cadastrado.
	 * @return O Id gerado automaticamente do item(que e sua ordem de cadastrado no
	 *         sistema).
	 */
	public int adicionaItem(String id, String descricao, int quantidade, String tags) {
		this.validador.verificaCadastroDeItem(id, descricao, quantidade);
		if (!this.usuarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
		}

		int idItem = ctlItem.identificador();

		if (!(ctlItem.contemDescritor(descricao))) {
			ctlItem.adicionaDescritor(descricao);
			if (quantidade > 0) {
				ctlItem.modificaDescritorSistemaQuantidade(descricao, quantidade);
			}
		}

		else {
			ctlItem.modificaDescritorSistemaQuantidade(descricao, quantidade);
		}

		this.usuarios.get(id).adicionaItem(idItem, descricao.trim().toLowerCase(), quantidade, tags);
		ctlItem.adicionaItemSistema(new Item(idItem, descricao.trim().toLowerCase(), quantidade, tags,
				this.usuarios.get(id).getNome(), id));

		return idItem;
	}

	/**
	 * Exibe um item de um doador.
	 * 
	 * @param idItem   Id do item a ser exibido.
	 * @param idDoador Id do doador portado do item.
	 * @return Retorna a representacao textual do item.
	 */
	public String exibeItem(int idItem, String idDoador) {
		if (!this.usuarios.containsKey(idDoador)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
		}
		return this.usuarios.get(idDoador).getItem(idItem);
	}

	/**
	 * Atualiza um item de um doador.
	 * 
	 * @param Id         do item (este e imutavel, assim como o ID do doador).
	 * @param idDoador   Id do doador portador do item a ser modificado.
	 * @param quantidade Nova quantidade desejada do item.
	 * @param tags       Novas tags desejadas do item.
	 * @return Retorna a nova representacao textual do Item.
	 */
	public String atualizaItemParaDoacao(int idItem, String idDoador, int quantidade, String tags) {
		this.validador.validaId(idDoador);
		this.validador.validaIdItem(idItem);

		if (!this.usuarios.containsKey(idDoador)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
		}
		if (this.usuarios.get(idDoador).getItem(idItem) == null) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		}

		if (quantidade > 0) {
			ctlItem.modificaDescritorSistemaQuantidade(this.usuarios.get(idDoador).getItemOb(idItem).getDescricaoItem(),
					quantidade);
			ctlItem.modificaQuantidadeItemSistema(idItem, quantidade);
		}

		if (!(tags == null)) {
			ctlItem.modificaTagsItemSistema(idItem, tags);
		}

		return this.usuarios.get(idDoador).atualizaItem(idItem, quantidade, tags);
	}

	/**
	 * Remove um item de um doador.
	 * 
	 * @param idItem   Id do item a ser removido.
	 * @param idDoador Id do doador portador do item a ser removido.
	 */
	public void removeItemParaDoacao(String idItem, String idDoador) {
		this.validador.validaId(idDoador);
		this.validador.validaId(idItem);
		this.validador.validaIdItem(Integer.parseInt(idItem));

		if (!(this.usuarios.containsKey(idDoador))) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
		}

		int idItemInt = Integer.parseInt(idItem);

		if (this.usuarios.get(idDoador).verificaItem(idItemInt)) {
			ctlItem.modificaDescritorSistemaQuantidade(
					this.usuarios.get(idDoador).getItemOb(idItemInt).getDescricaoItem(), 0);
		}

		this.usuarios.get(idDoador).removeItem(idItemInt);
		ctlItem.removeItemSistema(idItemInt);

	}

	/**
	 * Adiciona um item a um receptor, um pedido de um item em uma melhor descricao.
	 * 
	 * @param idReceptor    Id do usuario receptor.
	 * @param descricaoItem Descricao do item desejado.
	 * @param quantidade    Quantidade desejada do item desejado.
	 * @param tags          Tags do item desejado.
	 * @return Retorna o id do item gerado automaticamente pelo sistema.
	 */
	public int adicionaItemNecessario(String idReceptor, String descricaoItem, int quantidade, String tags) {
		this.validador.verificaCadastroDeItem(idReceptor, descricaoItem, quantidade);

		if (!this.usuarios.containsKey(idReceptor)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idReceptor + ".");
		}

		int idItem = ctlItem.identificador();

		ctlItem.adicionaItemSistemaNecessario(new Item(idItem, descricaoItem.toLowerCase(), quantidade, tags,
				this.usuarios.get(idReceptor).getNome(), idReceptor));

		return this.usuarios.get(idReceptor).adicionaItem(idItem, descricaoItem.toLowerCase(), quantidade, tags);

	}

	/**
	 * Atualiza um pedido de um receptor.
	 * 
	 * @param idReceptor     Id do receptor.
	 * @param idItem         Id do item (este e imutavel, assim como o ID do
	 *                       receptor).
	 * @param novaQuantidade Nova quantidade do pedido.
	 * @param novasTags      Novas tags do pedido.
	 * @return Retorna a nova representacao textual do pedido.
	 */
	public String atualizaItemNecessario(String idReceptor, int idItem, int novaQuantidade, String novasTags) {
		this.validador.validaId(idReceptor);
		this.validador.validaIdItem(idItem);

		if (!this.usuarios.containsKey(idReceptor)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idReceptor + ".");
		}

		if (novaQuantidade > 0) {
			ctlItem.modificaQuantidadeItemSistema(idItem, novaQuantidade);
		}

		if (!(novasTags == null)) {
			ctlItem.modificaTagsItemSistemaNecessario(idItem, novasTags);
		}

		return this.usuarios.get(idReceptor).atualizaItemNecessario(idItem, novaQuantidade, novasTags);
	}

	/**
	 * Remove um pedido de um receptor.
	 * 
	 * @param idReceptor Id do receptor a ter seu pedido removido.
	 * @param idItem     Id do pedido a ser removido.
	 */
	public void removeItemNecessario(String idReceptor, int idItem) {
		this.validador.validaId(idReceptor);
		this.validador.validaIdItem(idItem);

		if (!this.usuarios.containsKey(idReceptor)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idReceptor + ".");
		}

		ctlItem.removeItemSistemaNecessario(idItem);
		this.usuarios.get(idReceptor).removeItemNecessario(idItem);

	}

	/**
	 * Verifica se um usuario e receptor ou doador.
	 * 
	 * @param idUsuario Id do usuario a ser checado.
	 */
	public void verificaStatusReceptor(String idUsuario) {
		this.pesquisaUsuarioPorId(idUsuario);
		if (this.usuarios.get(idUsuario).getStatus().equals("doador")) {
			throw new IllegalArgumentException("O Usuario deve ser um receptor: " + idUsuario + ".");
		}
	}

	public String match(String idReceptor, int idItemNecessario) {
		this.validador.validaId(idReceptor);
		this.validador.validaIdItem(idItemNecessario);
		if (!this.usuarios.containsKey(idReceptor)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idReceptor + ".");
		}
		this.verificaStatusReceptor(idReceptor);
		if (!this.usuarios.get(idReceptor).getItens().containsKey(idItemNecessario)) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItemNecessario + ".");
		}

		Item itemNecessario = this.usuarios.get(idReceptor).getItemOb(idItemNecessario);

		ArrayList<Item> itensMatch = new ArrayList<>();

		for (String chave : this.usuarios.keySet()) {
			if (this.usuarios.get(chave).getStatus().equals("doador")) {
				itensMatch.addAll(this.usuarios.get(chave).verificaMatch(itemNecessario));
			}
		}

		this.pontuacao(itensMatch, itemNecessario);
		Collections.sort(itensMatch, new ComparatorMatchItens());

		String match = "";

		for (int i = 0; i < itensMatch.size(); i++) {
			if (i == itensMatch.size() - 1) {
				match = match + itensMatch.get(i).toStringSistema();
			} else {
				match = match + itensMatch.get(i).toStringSistema() + " | ";
			}

		}
		return match;
	}

	private void pontuacao(ArrayList<Item> itensMatch, Item itemNecessario) {
		this.resetaPontos(itensMatch);

		for (Item i : itensMatch) {

			if (Arrays.equals(i.getTags(), itemNecessario.getTags())) {
				i.setPontos(10 * itemNecessario.getTags().length);
			}

			else {
				for (int j = 0; j < itensMatch.size() - 1; j++) {
					this.pontuaPelasTags(itensMatch.get(j), itemNecessario);

				}
			}
		}
	}

	private void pontuaPelasTags(Item item, Item itemNecessario) {
		for (int k = 0; k <= itemNecessario.getTags().length - 1; k++) {
			for (int m = 0; m <= item.getTags().length - 1; m++) {
				if (m != k && itemNecessario.getTags()[k].equals(item.getTags()[m])) {
					item.setPontos(5);
					break;
				} else if (m == k && itemNecessario.getTags()[k].equals(item.getTags()[m])) {
					item.setPontos(10);
					break;
				}
			}
		}
	}

	private void resetaPontos(ArrayList<Item> itensMatch) {
		for (int i = 0; i < itensMatch.size(); i++) {
			itensMatch.get(i).resetaPontos();
		}
	}

	public String realizaDoacao(int idItemNec, int idItemDoado, String data) {
		this.validador.validaIdItem(idItemNec);
		this.validador.validaIdItem(idItemDoado);
		this.validador.validaData(data);
		this.verificaExistenciaDoItem(idItemNec);
		this.verificaExistenciaDoItem(idItemDoado);

		Item itemNecessario = getItem(idItemNec);
		Item itemDoacao = getItem(idItemDoado);
		int quantidadeDoada;
		if (itemNecessario.getQuantidade() <= itemDoacao.getQuantidade()) {
			quantidadeDoada = itemNecessario.getQuantidade();
		} else {
			quantidadeDoada = itemDoacao.getQuantidade();
		}

		this.validador.validaDoacao(itemDoacao, itemNecessario);
		String string = String.format("%s - doador: %s/%s, item: %s, quantidade: %d, receptor: %s/%s", data,
				itemDoacao.getNomeUsuario(), itemDoacao.getIdUsuario(), itemNecessario.getDescricaoItem(),
				quantidadeDoada, itemNecessario.getNomeUsuario(), itemNecessario.getIdUsuario());

		if (itemNecessario.getQuantidade() - quantidadeDoada == 0) {
			String id = itemNecessario.getIdUsuario();
			this.usuarios.get(id).removeItemNecessario(idItemNec);
		} else {
			itemNecessario.setQuantidade(-1 * quantidadeDoada);
		}

		if (itemDoacao.getQuantidade() - quantidadeDoada == 0) {
			String id = itemDoacao.getIdUsuario();
			this.usuarios.get(id).removeItem(idItemDoado);
		} else {
			itemDoacao.setQuantidade(-1 * quantidadeDoada);
		}

		this.itensDoados.add(string);

		return string;
	}

	private void verificaExistenciaDoItem(int idItem) {
		boolean existencia = false;
		for (Usuario u : usuarios.values()) {
			if (u.getItens().containsKey(idItem)) {
				existencia = true;
				break;
			}
		}
		if (!existencia) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		}
	}

	private Item getItem(int idItem) {
		Item item = null;
		for (Usuario u : usuarios.values()) {
			if (u.getItens().containsKey(idItem)) {
				item = u.getItemOb(idItem);
				break;
			}
		}
		return item;
	}

	/**
	 * Salva o Mapa de usuarios em um arquivo.
	 * 
	 * @throws IOException
	 */
	public void salvaDados() throws IOException {
		ObjectOutputStream gravaObjeto;
		gravaObjeto = new ObjectOutputStream(new FileOutputStream("src" + File.separator + "usuarios.txt"));
		gravaObjeto.writeObject(this.usuarios);
		gravaObjeto.close();
	}

	/**
	 * Le e recupera o Mapa de usuarios de um arquivo.
	 * 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void recuperaDados() throws ClassNotFoundException, IOException {
		ObjectInputStream objeto;
		objeto = new ObjectInputStream(new FileInputStream("src" + File.separator + "usuarios.txt"));
		Object objLeitura = objeto.readObject();
		this.usuarios = (LinkedHashMap<String, Usuario>) objLeitura;
		objeto.close();

	}
}
