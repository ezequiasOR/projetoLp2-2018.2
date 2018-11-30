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

public class ControllerItem {
	
	private Set<String> descritores;
	private Set<Descritor> descritoresSistema;
	private ArrayList<Item> itensSistema;
	private ArrayList<Item> itensSistemaNecessario;
	private int id;
	private Validador validador;
	
	public int identificador() {
		this.id += 1;
		return this.id;
	}
	
	private boolean inDescritor(Item item, String palavra) {
		String[] descritores = item.getDescricaoItem().split(" ");
		
		for(int h = 0; h < descritores.length; h++) {
			if(descritores[h].equals(palavra)) {
				return true;
			}
		}
		return false;
	}
	
	public ControllerItem() {
		this.validador = new Validador();
		this.descritoresSistema = new HashSet<>();
		this.descritores = new HashSet<>();
		this.itensSistema = new ArrayList<>();
		this.itensSistemaNecessario = new ArrayList<>();
		this.id = 0;
	}
	
	public void adicionaDescritor(String descricao) {
		this.validador.validaDescritor(descricao);
		
		String descricaoAdicionar = descricao.toLowerCase().trim();
		if(this.contemDescritor(descricaoAdicionar)) {
			throw new IllegalArgumentException("Descritor de Item ja existente: " + descricaoAdicionar + ".");
		}
		
		this.descritores.add(descricaoAdicionar);
		this.descritoresSistema.add(new Descritor(descricaoAdicionar));
	}
	
	public void modificaDescritorSistemaQuantidade(String descricao, int quantidade) {
		
		String descricaoAdicionar = descricao.toLowerCase().trim();
		
		for(Descritor d: this.descritoresSistema) {
			if(d.getDescritor().equals(descricaoAdicionar)) {
					d.setQuantidade(quantidade);
			}
		}
	}
	
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
	
	public void modificaQuantidadeItemSistema(int idItem, int quantidade) {
		for(int k=0; k < this.itensSistema.size(); k++) {
			if(this.itensSistema.get(k).getId() == idItem) {
				this.itensSistema.get(k).setQuantidade(quantidade);
				Collections.sort(this.itensSistema, new ComparatorItemQuantidade());
				return;
			}
		}
	}
	
	public void modificaQuantidadeItemSistemaNecessario(int idItem, int quantidade) {
		for(int k=0; k < this.itensSistemaNecessario.size(); k++) {
			if(this.itensSistemaNecessario.get(k).getId() == idItem) {
				this.itensSistemaNecessario.get(k).setQuantidade(quantidade);
				Collections.sort(this.itensSistemaNecessario, new ComparatorItemId());
				return;
			}
		}
	}
	
	public void modificaTagsItemSistema(int idItem, String tags) {
		for(int k=0; k < this.itensSistema.size(); k++) {
			if(this.itensSistema.get(k).getId() == idItem) {
				this.itensSistema.get(k).setTags(tags);
				Collections.sort(this.itensSistema, new ComparatorItemQuantidade());
				return;
			}
		}
	}
	
	public void modificaTagsItemSistemaNecessario(int idItem, String tags) {
		for(int k=0; k < this.itensSistemaNecessario.size(); k++) {
			if(this.itensSistemaNecessario.get(k).getId() == idItem) {
				this.itensSistemaNecessario.get(k).setTags(tags);
				Collections.sort(this.itensSistemaNecessario, new ComparatorItemId());
				return;
			}
		}
	}
	
	
	public void removeItemSistema(int idItem) {
		for(int j=0; j <this.itensSistema.size(); j++) {
			if(this.itensSistema.get(j).getId() == idItem) {
				this.itensSistema.remove(j);
				return;
			}
		}
	}
	
	public void removeItemSistemaNecessario(int idItem) {
		for(int j=0; j <this.itensSistemaNecessario.size(); j++) {
			if(this.itensSistemaNecessario.get(j).getId() == idItem) {
				this.itensSistemaNecessario.remove(j);
				return;
			}
		}
	}
	
	
	public boolean contemDescritor(String descricao) {
		if(this.descritores.contains(descricao)) {
			return true;
		}
		return false;
	}

	public Set<String> getDescritores() {
		return descritores;
	}
	
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
	
	public String listarDescritores() {
		ArrayList<Descritor> descritoresOrdenadosPorNome = new ArrayList<>();
		
		for(Descritor d: this.descritoresSistema) {
			descritoresOrdenadosPorNome.add(d);
		}
		
		Collections.sort(descritoresOrdenadosPorNome, new ComparatorDescritor());
		return this.concatenador(descritoresOrdenadosPorNome);
	}
	
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
