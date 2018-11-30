package Controllers;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

import Comparators.ComparatorItemId;
import Comparators.ComparatorDescritor;
import Comparators.ComparatorItemNome;
import Comparators.ComparatorItemQuantidade;
import Validador.Validador;
import doe.Descritor;
import doe.Item;

/**
 * Representacao do controle de itens.
 * 
 * @author João Vitor de Melo Cavalcante e Souza
 * @author Ezequias de Oliveira Rocha
 * @author Felipe Jerônimo Bernardo da Silva
 */
public class ControllerItem {
	
	/**
	 * Descritores do controle de itens
	 */
	private Set<String> descritores;
	
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
		
		for(int h = 0; h < descritores.length; h++) {
			if(descritores[h].equals(palavra)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Construtor do controle de item.
	 */
	public ControllerItem() {
		this.validador = new Validador();
		this.descritoresSistema = new HashSet<>();
		this.descritores = new HashSet<>();
		this.itensSistema = new ArrayList<>();
		this.itensSistemaNecessario = new ArrayList<>();
		this.id = 0;
	}
	
	/**
	 * Adiciona um descritor .
	 * @param descricao Descricao a ser adicionada.
	 */
	public void adicionaDescritor(String descricao) {
		this.validador.validaDescritor(descricao);
		
		String descricaoAdicionar = descricao.toLowerCase().trim();
		if(this.contemDescritor(descricaoAdicionar)) {
			throw new IllegalArgumentException("Descritor de Item ja existente: " + descricaoAdicionar + ".");
		}
		
		this.descritores.add(descricaoAdicionar);
		this.descritoresSistema.add(new Descritor(descricaoAdicionar));
	}
	
	
	/**
	 * Modifica o descritor.
	 * @param descricao 
	 * @param quantidade
	 */
	public void modificaDescritorSistemaQuantidade(String descricao, int quantidade) {
		
		String descricaoAdicionar = descricao.toLowerCase().trim();
		
		for(Descritor d: this.descritoresSistema) {
			if(d.getDescritor().equals(descricaoAdicionar)) {
					d.setQuantidade(quantidade);
			}
		}
	}
	
	
	/**
	 * Adiciona item no sistema.
	 * @param item Item para ser adicionado
	 */
	public void adicionaItemSistema(Item item) {
		for(int k=0; k < this.itensSistema.size(); k++) {
			if(item.equals(this.itensSistema.get(k))) {
				this.itensSistema.get(k).setQuantidade(item.getQuantidade());
				Collections.sort(this.itensSistema, new ComparatorItemQuantidade());
				return;
			}
		}
		this.itensSistema.add(item);
		Collections.sort(this.itensSistema,new ComparatorItemQuantidade());
	}
	
	/**
	 * 
	 * Adiciona item necessario no sistema.
	 * @param item Item necessario para ser adicionado
	 */
	public void adicionaItemSistemaNecessario(Item item) {
		
		for(int k=0; k < this.itensSistemaNecessario.size(); k++) {
			if(item.equals(this.itensSistemaNecessario.get(k))) {
				this.itensSistemaNecessario.get(k).setQuantidade(item.getQuantidade());
				
				Collections.sort(this.itensSistemaNecessario, new ComparatorItemId());
				return;
			}
		}
		
		this.itensSistemaNecessario.add(item);
		Collections.sort(this.itensSistemaNecessario,new ComparatorItemId());
	}
	
	
	/**
	 * Altera a quantidade do item no sistema.
	 * @param idItem Id do iten
	 * @param quantidade Quantidade para alterar
	 */
	public void modificaQuantidadeItemSistema(int idItem, int quantidade) {
		for(int k=0; k < this.itensSistema.size(); k++) {
			if(this.itensSistema.get(k).getId() == idItem) {
				this.itensSistema.get(k).setQuantidade(quantidade);
				Collections.sort(this.itensSistema, new ComparatorItemQuantidade());
				return;
			}
		}
	}
	
	/**
	 * Altera a quantidade do item necessario no sistema.
	 * 
	 * @param idItem Id do item
	 * @param quantidade Quantidade para alterar
	 */
	public void modificaQuantidadeItemSistemaNecessario(int idItem, int quantidade) {
		for(int k=0; k < this.itensSistemaNecessario.size(); k++) {
			if(this.itensSistemaNecessario.get(k).getId() == idItem) {
				this.itensSistemaNecessario.get(k).setQuantidade(quantidade);
				Collections.sort(this.itensSistemaNecessario, new ComparatorItemId());
				return;
			}
		}
	}
	
	
	/**
	 * Modifica as tags do item.
	 * @param idItem Id do item
	 * @param tags Tags do item.
	 */
	public void modificaTagsItemSistema(int idItem, String tags) {
		for(int k=0; k < this.itensSistema.size(); k++) {
			if(this.itensSistema.get(k).getId() == idItem) {
				this.itensSistema.get(k).setTags(tags);
				Collections.sort(this.itensSistema, new ComparatorItemQuantidade());
				return;
			}
		}
	}
	
	
	/**
	 * Modifica as tags do item necesario.
	 * @param idItem Id do item
	 * @param tags Tags do item.
	 */
	public void modificaTagsItemSistemaNecessario(int idItem, String tags) {
		for(int k=0; k < this.itensSistemaNecessario.size(); k++) {
			if(this.itensSistemaNecessario.get(k).getId() == idItem) {
				this.itensSistemaNecessario.get(k).setTags(tags);
				Collections.sort(this.itensSistemaNecessario, new ComparatorItemId());
				return;
			}
		}
	}
	
	
	/**
	 * Remove item do sistema atraves
	 * @param idItem Id para identificar o item.
	 */
	public void removeItemSistema(int idItem) {
		for(int j=0; j <this.itensSistema.size(); j++) {
			if(this.itensSistema.get(j).getId() == idItem) {
				this.itensSistema.remove(j);
				return;
			}
		}
	}
	
	
	/**
	 * Remove item do sistema atraves
	 * @param idItem Id para identificar o item.
	 */
	public void removeItemSistemaNecessario(int idItem) {
		for(int j=0; j <this.itensSistemaNecessario.size(); j++) {
			if(this.itensSistemaNecessario.get(j).getId() == idItem) {
				this.itensSistemaNecessario.remove(j);
				return;
			}
		}
	}
	
	
	/**
	 * Verifica se a descricao ja existe.
	 * @param descricao Descricao do item.
	 * @return true se o item foi encontrado.
	 */
	public boolean contemDescritor(String descricao) {
		if(this.descritores.contains(descricao)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @return Os descritores.
	 */
	public Set<String> getDescritores() {
		return descritores;
	}
	
	
	/**
	 * 
	 * @param arrayItens
	 * @return Representacao em string de um array de itens
	 */
	private String concatenador(ArrayList arrayItens) {
		String stringDeItens = "";
		
		for(int i = 0; i < arrayItens.size(); i++) {
			if(i == arrayItens.size()-1) {
				stringDeItens = stringDeItens + arrayItens.get(i).toString();
			}
			else {
				stringDeItens = stringDeItens + arrayItens.get(i).toString() + " | ";
			}
		}
		
		return stringDeItens;
	}
	
	
	/**
	 * 
	 * @return Representacao em string de descritores
	 */
	public String listarDescritores() {
		ArrayList<Descritor> descritoresOrdenadosPorNome = new ArrayList<>();
		
		for(Descritor d: this.descritoresSistema) {
			descritoresOrdenadosPorNome.add(d);
		}
		
		Collections.sort(descritoresOrdenadosPorNome, new ComparatorDescritor());
		return this.concatenador(descritoresOrdenadosPorNome);
	}
	
	
	/**
	 * 
	 * @return Representacao em string de itens no sistema
	 */
	public String listarItensNoSistema() {
		String itensOrdenados = "";
		
		for(int u = 0; u < this.itensSistema.size();u++) {
			if(u == this.itensSistema.size()-1) {
				itensOrdenados = itensOrdenados + this.itensSistema.get(u).toStringSistema();
			}
			else {
				itensOrdenados = itensOrdenados + this.itensSistema.get(u).toStringSistema() + " | ";
			}
		}
		return itensOrdenados;
	}
	
	
	/**
	 * 
	 * @return Representacao em string de uma lista de itens necessarios ordenados.
	 */
public String listaItensNecessarios() {
		String itensOrdenadosNecessarios = "";
		
		for(int u = 0; u < this.itensSistemaNecessario.size();u++) {
			if(u == this.itensSistemaNecessario.size()-1) {
				itensOrdenadosNecessarios = itensOrdenadosNecessarios + this.itensSistemaNecessario.get(u).toStringSistemaNecessario();
			}
			else {
				itensOrdenadosNecessarios = itensOrdenadosNecessarios + this.itensSistemaNecessario.get(u).toStringSistemaNecessario() + " | ";
			}
		}
		return itensOrdenadosNecessarios;
	}
	
	
	/**
	 * 
	 * @param descricao Descricao do item
	 * @return itens ordenados pela descricao.
	 */
	public String listaItemPorDescricao(String descricao) {
		this.validador.validaPesquisa(descricao);
		ArrayList<Item> itensSelecionados = new ArrayList<>();
		
		for(int i = 0; i < this.itensSistema.size(); i ++) {
			if(this.inDescritor(this.itensSistema.get(i), descricao)) {
				itensSelecionados.add(this.itensSistema.get(i));
			}
		}
		
		Collections.sort(itensSelecionados, new ComparatorItemNome());
		return this.concatenador(itensSelecionados);
	}

}
