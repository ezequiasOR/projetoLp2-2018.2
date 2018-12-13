package Controllers;

import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

import Validador.Validador;
import comparators.ComparatorDescritor;
import comparators.ComparatorItemId;
import comparators.ComparatorItemNome;
import comparators.ComparatorItemQuantidade;
import comparators.ComparatorMatchItens;
import doe.Descritor;
import doe.Item;
import doe.Usuario;

/**
 * Representacao do controle de itens.
 * 
 * @author João Vitor de Melo Cavalcante e Souza
 * @author Ezequias de Oliveira Rocha
 * @author Felipe Jerônimo Bernardo da Silva
 */
public class ControllerItem {

	/**
	 * Descritores do sistema do controle de itens
	 */
	private Set<Descritor> descritoresSistema;

	/**
	 * Item do sistema do controle de itens
	 */
	private ArrayList<Item> itensSistema;

	/**
	 * Item do sistema necessario do controle de itens.
	 */
	private ArrayList<Item> itensSistemaNecessario;

	/**
	 * Id do controle de iten.
	 */
	private int id;

	/**
	 * Validadror de controle de item.
	 */
	private Validador validador;

	/**
	 * Atributo responsavel por controlar item.
	 */
	private ControllerUsuario ctlUsuario;

	/**
	 * @return O id atualizado.
	 */
	public int identificador() {
		this.id += 1;
		return this.id;
	}

	/**
	 * Confere se um descritor
	 * 
	 * @param item
	 * @param palavra
	 * @return
	 */
	private boolean inDescritor(Item item, String palavra) {
		String[] descritores = item.getDescricaoItem().split(" ");

		for (int h = 0; h < descritores.length; h++) {
			if (descritores[h].equals(palavra)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Construtor do controle de item.
	 * 
	 */
	public ControllerItem(ControllerUsuario ctlUsuario) {
		this.validador = new Validador();
		this.descritoresSistema = new HashSet<>();
		this.itensSistema = new ArrayList<>();
		this.itensSistemaNecessario = new ArrayList<>();
		this.id = 0;
		this.ctlUsuario = ctlUsuario;
	}

	/**
	 * Adiciona um descritor .
	 * 
	 * @param descricao Descricao a ser adicionada.
	 */
	public void adicionaDescritor(String descricao) {
		this.validador.validaDescritor(descricao);

		String descricaoAdicionar = descricao.toLowerCase().trim();
		if (this.contemDescritor(descricaoAdicionar)) {
			throw new IllegalArgumentException("Descritor de Item ja existente: " + descricaoAdicionar + ".");
		}

		this.descritoresSistema.add(new Descritor(descricaoAdicionar));
	}

	/**
	 * Modifica o descritor.
	 * 
	 * @param descricao
	 * @param quantidade
	 */
	public void modificaDescritorSistemaQuantidade(String descricao, int quantidade) {

		String descricaoAdicionar = descricao.toLowerCase().trim();

		for (Descritor d : this.descritoresSistema) {
			if (d.getDescritor().equals(descricaoAdicionar)) {
				d.setQuantidade(quantidade);
			}
		}
	}

	/**
	 * Adiciona item no sistema.
	 * 
	 * @param item Item para ser adicionado
	 */
	public void adicionaItemSistema(Item item) {
		for (int k = 0; k < this.itensSistema.size(); k++) {
			if (item.equals(this.itensSistema.get(k))) {
				this.itensSistema.get(k).setQuantidade(item.getQuantidade());
				Collections.sort(this.itensSistema, new ComparatorItemQuantidade());
				return;
			}
		}
		this.itensSistema.add(item);
		Collections.sort(this.itensSistema, new ComparatorItemQuantidade());
	}

	/**
	 * 
	 * Adiciona item necessario no sistema.
	 * 
	 * @param item Item necessario para ser adicionado
	 */
	public void adicionaItemSistemaNecessario(Item item) {

		for (int k = 0; k < this.itensSistemaNecessario.size(); k++) {
			if (item.equals(this.itensSistemaNecessario.get(k))) {
				this.itensSistemaNecessario.get(k).setQuantidade(item.getQuantidade());

				Collections.sort(this.itensSistemaNecessario, new ComparatorItemId());
				return;
			}
		}

		this.itensSistemaNecessario.add(item);
		Collections.sort(this.itensSistemaNecessario, new ComparatorItemId());
	}

	/**
	 * Altera a quantidade do item no sistema.
	 * 
	 * @param idItem     Id do iten
	 * @param quantidade Quantidade para alterar
	 */
	public void modificaQuantidadeItemSistema(int idItem, int quantidade) {
		for (int k = 0; k < this.itensSistema.size(); k++) {
			if (this.itensSistema.get(k).getId() == idItem) {
				this.itensSistema.get(k).setQuantidade(quantidade);
				Collections.sort(this.itensSistema, new ComparatorItemQuantidade());
				return;
			}
		}
	}

	/**
	 * Altera a quantidade do item necessario no sistema.
	 * 
	 * @param idItem     Id do item
	 * @param quantidade Quantidade para alterar
	 */
	public void modificaQuantidadeItemSistemaNecessario(int idItem, int quantidade) {
		for (int k = 0; k < this.itensSistemaNecessario.size(); k++) {
			if (this.itensSistemaNecessario.get(k).getId() == idItem) {
				this.itensSistemaNecessario.get(k).setQuantidade(quantidade);
				Collections.sort(this.itensSistemaNecessario, new ComparatorItemId());
				return;
			}
		}
	}

	/**
	 * Modifica as tags do item.
	 * 
	 * @param idItem Id do item
	 * @param tags   Tags do item.
	 */
	public void modificaTagsItemSistema(int idItem, String tags) {
		for (int k = 0; k < this.itensSistema.size(); k++) {
			if (this.itensSistema.get(k).getId() == idItem) {
				this.itensSistema.get(k).setTags(tags);
				Collections.sort(this.itensSistema, new ComparatorItemQuantidade());
				return;
			}
		}
	}

	/**
	 * Modifica as tags do item necesario.
	 * 
	 * @param idItem Id do item
	 * @param tags   Tags do item.
	 */
	public void modificaTagsItemSistemaNecessario(int idItem, String tags) {
		for (int k = 0; k < this.itensSistemaNecessario.size(); k++) {
			if (this.itensSistemaNecessario.get(k).getId() == idItem) {
				this.itensSistemaNecessario.get(k).setTags(tags);
				Collections.sort(this.itensSistemaNecessario, new ComparatorItemId());
				return;
			}
		}
	}

	/**
	 * Remove item do sistema atraves do id.
	 * 
	 * @param idItem Id para identificar o item.
	 */
	public void removeItemSistema(int idItem) {
		for (int j = 0; j < this.itensSistema.size(); j++) {
			if (this.itensSistema.get(j).getId() == idItem) {
				this.itensSistema.remove(j);
				return;
			}
		}
	}

	/**
	 * Remove item necessario do sistema atraves do id.
	 * 
	 * @param idItem Id para identificar o item.
	 */
	public void removeItemSistemaNecessario(int idItem) {
		for (int j = 0; j < this.itensSistemaNecessario.size(); j++) {
			if (this.itensSistemaNecessario.get(j).getId() == idItem) {
				this.itensSistemaNecessario.remove(j);
				return;
			}
		}
	}

	/**
	 * Verifica se a descricao ja existe.
	 * 
	 * @param descricao Descricao do item.
	 * @return true se o item foi encontrado.
	 */
	public boolean contemDescritor(String descricao) {
		HashSet<String> descritoresString = new HashSet<>();

		for (Descritor d : this.descritoresSistema) {
			descritoresString.add(d.getDescritor());
		}

		if (descritoresString.contains(descricao)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @return Os descritores.
	 */
	public Set<String> getDescritores() {

		HashSet<String> descritoresString = new HashSet<>();

		for (Descritor d : this.descritoresSistema) {
			descritoresString.add(d.getDescritor());
		}
		return descritoresString;
	}

	/**
	 * Concatena um array de itens em uma string.
	 * 
	 * @param arrayItens
	 * @return Representacao em string de um array de itens
	 */
	private String concatenador(ArrayList arrayItens) {
		String stringDeItens = "";

		for (int i = 0; i < arrayItens.size(); i++) {
			if (i == arrayItens.size() - 1) {
				stringDeItens = stringDeItens + arrayItens.get(i).toString();
			} else {
				stringDeItens = stringDeItens + arrayItens.get(i).toString() + " | ";
			}
		}

		return stringDeItens;
	}

	/**
	 * Lista os descritores.
	 * 
	 * @return Representacao em string de descritores
	 */
	public String listarDescritores() {
		ArrayList<Descritor> descritoresOrdenadosPorNome = new ArrayList<>();

		for (Descritor d : this.descritoresSistema) {
			descritoresOrdenadosPorNome.add(d);
		}

		Collections.sort(descritoresOrdenadosPorNome, new ComparatorDescritor());
		return this.concatenador(descritoresOrdenadosPorNome);
	}

	/**
	 * Lista os itens presentes no sistema de forma ordenada.
	 * 
	 * @return Representacao em string de itens no sistema
	 */
	public String listarItensNoSistema() {
		String itensOrdenados = "";

		for (int u = 0; u < this.itensSistema.size(); u++) {
			if (u == this.itensSistema.size() - 1) {
				itensOrdenados = itensOrdenados + this.itensSistema.get(u).toStringSistema();
			} else {
				itensOrdenados = itensOrdenados + this.itensSistema.get(u).toStringSistema() + " | ";
			}
		}
		return itensOrdenados;
	}

	/**
	 * Lista os itens necessarios de forma ordenada.
	 * 
	 * @return Representacao em string de uma lista de itens necessarios.
	 */
	public String listaItensNecessarios() {
		String itensOrdenadosNecessarios = "";

		for (int u = 0; u < this.itensSistemaNecessario.size(); u++) {
			if (u == this.itensSistemaNecessario.size() - 1) {
				itensOrdenadosNecessarios = itensOrdenadosNecessarios
						+ this.itensSistemaNecessario.get(u).toStringSistemaNecessario();
			} else {
				itensOrdenadosNecessarios = itensOrdenadosNecessarios
						+ this.itensSistemaNecessario.get(u).toStringSistemaNecessario() + " | ";
			}
		}
		return itensOrdenadosNecessarios;
	}

	/**
	 * Lista os itens de forma ordenada.
	 * 
	 * @param descricao Descricao do item
	 * @return itens ordenados pela descricao.
	 */
	public String listaItemPorDescricao(String descricao) {
		this.validador.validaPesquisa(descricao);
		ArrayList<Item> itensSelecionados = new ArrayList<>();

		for (int i = 0; i < this.itensSistema.size(); i++) {
			if (this.inDescritor(this.itensSistema.get(i), descricao)) {
				itensSelecionados.add(this.itensSistema.get(i));
			}
		}

		Collections.sort(itensSelecionados, new ComparatorItemNome());
		return this.concatenador(itensSelecionados);
	}

	private void verificaItemNecessario(int idItem) {
		for (int i = 0; i < this.itensSistemaNecessario.size(); i++) {
			if (this.itensSistemaNecessario.get(i).getId() == idItem) {
				return;
			}
		}

		throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
	}

	/*
	 * private void pontuacao(ArrayList<Item> itensMatch, Item itemNecessario) {
	 * this.resetaPontos(itensMatch);
	 * 
	 * for (Item i : itensMatch) {
	 * 
	 * if (Arrays.equals(i.getTags(),itemNecessario.getTags())) {
	 * i.setPontos(10*itemNecessario.getTags().length); }
	 * 
	 * else { for (int j = 0; j < itensMatch.size()-1; j++) {
	 * this.pontuaPelasTags(itensMatch.get(j), itemNecessario);
	 * 
	 * }
	 * 
	 * //String[] a = i.getTags().clone(); //String[] b =
	 * itemNecessario.getTags().clone();
	 * 
	 * //Arrays.sort(a); //Arrays.sort(b); //if(Arrays.equals(a, b)) {
	 * //i.setPontos(5);
	 * 
	 * }
	 * 
	 * } } }
	 * 
	 * 
	 * 
	 * private void pontuaPelasTags(Item item, Item itemNecessario) { for (int k =
	 * 0; k <= itemNecessario.getTags().length-1; k++) { for (int m = 0; m <=
	 * item.getTags().length-1; m++) { if (m != k &&
	 * itemNecessario.getTags()[k].equals(item.getTags()[m])) { item.setPontos(5);
	 * break; } else if (m == k &&
	 * itemNecessario.getTags()[k].equals(item.getTags()[m])) { item.setPontos(10);
	 * break; } } } }
	 * 
	 * public String match(String idReceptor, int idItemNecessario,
	 * ControllerUsuario ctlUsuario) { this.validador.validaId(idReceptor);
	 * this.validador.validaIdItem(idItemNecessario);
	 * ctlUsuario.pesquisaUsuarioPorId(idReceptor);
	 * this.verificaItemNecessario(idItemNecessario);
	 * ctlUsuario.verificaStatusReceptor(idReceptor);
	 * 
	 * Item itemNecessario = null;
	 * 
	 * for (Item i : this.itensSistemaNecessario) { if (i.getId() ==
	 * idItemNecessario) { itemNecessario = i; } }
	 * 
	 * ArrayList<Item> itensMatch = new ArrayList<>();
	 * 
	 * for (Item i : this.itensSistema) { if
	 * (i.getDescricaoItem().equals(itemNecessario.getDescricaoItem())) {
	 * itensMatch.add(i); } }
	 * 
	 * this.pontuacao(itensMatch, itemNecessario); Collections.sort(itensMatch, new
	 * ComparatorMatchItens());
	 * 
	 * String match = "";
	 * 
	 * 
	 * for (int i = 0; i < itensMatch.size(); i++) {
	 * 
	 * if (i == itensMatch.size() - 1) { match = match +
	 * itensMatch.get(i).toStringSistema(); }
	 * 
	 * 
	 * else {
	 * 
	 * match = match + itensMatch.get(i).toStringSistema() + " | "; }
	 * 
	 * 
	 * } return match;
	 * 
	 * 
	 * 
	 * //for (int i = 0; i < itensMatch.size(); i++) { //match = match +
	 * Integer.toString(itensMatch.get(i).getPontos()); //}
	 * 
	 * //return match;
	 * 
	 * }
	 * 
	 * private void resetaPontos(ArrayList<Item> itensMatch) { for (int i = 0; i <
	 * itensMatch.size(); i++) { itensMatch.get(i).resetaPontos(); }
	 * 
	 * }
	 */
	/*
	 * public String realizaDoacao(int idItemNec, int idItemDoado, String data) {
	 * this.validador.validaIdItem(idItemNec);
	 * this.validador.validaIdItem(idItemDoado); this.validador.validaData(data);
	 * 
	 * Item itemNecessario = null; Item itemDoacao = null;
	 * 
	 * for (Item i : this.itensSistemaNecessario) { if (i.getId() == idItemNec) {
	 * itemNecessario = i; } } this.validador.validaItem(itemNecessario, itemDoacao,
	 * idItemNec, idItemDoado);
	 * 
	 * for (Item i : this.itensSistema) { if
	 * (i.getDescricaoItem().equals(itemNecessario.getDescricaoItem())) { itemDoacao
	 * = i; break; } }
	 * 
	 * 
	 * this.validador.validaDoacao(itemDoacao, itemNecessario);
	 * 
	 * 
	 * // TODO Auto-generated method stub return null; }
	 */

	public void salvaDados() throws IOException {
		this.salvaDescritores();
		this.salvaItens();
		this.salvaItensNecessarios();
	}

	private void salvaDescritores() throws IOException {
		ObjectOutputStream gravaObjeto = new ObjectOutputStream(
				new FileOutputStream("src" + File.separator + "descritores.txt"));
		gravaObjeto.writeObject(this.descritoresSistema);
		gravaObjeto.close();

	}

	private void salvaItens() throws IOException {
		ObjectOutputStream gravaObjeto = new ObjectOutputStream(
				new FileOutputStream("src" + File.separator + "itens.txt"));
		gravaObjeto.writeObject(this.itensSistema);
		gravaObjeto.close();
	}

	private void salvaItensNecessarios() throws IOException {
		ObjectOutputStream gravaObjeto = new ObjectOutputStream(
				new FileOutputStream("src" + File.separator + "itensNecessarios.txt"));
		gravaObjeto.writeObject(this.itensSistemaNecessario);
		gravaObjeto.close();
	}

	public void recuperaDados() throws ClassNotFoundException, IOException {
		this.recuperaDescritores();
		this.recuperaItens();
		this.recuperaItensNecessarios();
	}

	@SuppressWarnings("unchecked")
	private void recuperaDescritores() throws ClassNotFoundException, IOException {
		ObjectInputStream objeto = new ObjectInputStream(
				new FileInputStream("src" + File.separator + "descritores.txt"));
		Object objLeitura = objeto.readObject();
		this.descritoresSistema = (Set<Descritor>) objLeitura;
		objeto.close();

	}

	@SuppressWarnings("unchecked")
	private void recuperaItens() throws ClassNotFoundException, IOException {
		ObjectInputStream objeto = new ObjectInputStream(new FileInputStream("src" + File.separator + "itens.txt"));
		Object objLeitura = objeto.readObject();
		this.itensSistema = (ArrayList<Item>) objLeitura;
		objeto.close();
	}

	@SuppressWarnings("unchecked")
	private void recuperaItensNecessarios() throws ClassNotFoundException, IOException {
		ObjectInputStream objeto = new ObjectInputStream(
				new FileInputStream("src" + File.separator + "itensNecessarios.txt"));
		Object objLeitura = objeto.readObject();
		this.itensSistemaNecessario = (ArrayList<Item>) objLeitura;
		objeto.close();
	}

}
