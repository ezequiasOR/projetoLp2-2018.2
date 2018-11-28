package doe;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

public class ControllerItem {
	
	private Set<String> descritores;
	private Set<Descritor> descritoresSistema;
	private ArrayList<Item> itensSistema;
	private int id;
	private Validador validador;
	
	public int identificador() {
		this.id += 1;
		return this.id;
	}
	
	public ControllerItem() {
		this.validador = new Validador();
		this.descritoresSistema = new HashSet<>();
		this.descritores = new HashSet<>();
		this.itensSistema = new ArrayList<>();
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
	
	public void modificaQuantidadeItemSistema(int idItem, int quantidade) {
		for(int k=0; k < this.itensSistema.size(); k++) {
			if(this.itensSistema.get(k).getId() == idItem) {
				this.itensSistema.get(k).setQuantidade(quantidade);
				Collections.sort(this.itensSistema, new ComparatorItemQuantidade());
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
	
	public void removeItemSistema(int idItem) {
		for(int j=0; j <this.itensSistema.size(); j++) {
			if(this.itensSistema.get(j).getId() == idItem) {
				this.itensSistema.remove(j);
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
	
	public String listarDescritores() {
		
		ArrayList<Descritor> descritoresOrdenadosPorNome = new ArrayList<>();
		
		for(Descritor d: this.descritoresSistema) {
			descritoresOrdenadosPorNome.add(d);
		}
		
		Collections.sort(descritoresOrdenadosPorNome, new ComparatorDescritor());
		
		String descritoresOrdenados = "";
		
		for(int i = 0; i < descritoresOrdenadosPorNome.size(); i++) {
			if(i == descritoresOrdenadosPorNome.size()-1) {
				descritoresOrdenados = descritoresOrdenados + descritoresOrdenadosPorNome.get(i).toString();
			}
			else {
				descritoresOrdenados = descritoresOrdenados + descritoresOrdenadosPorNome.get(i).toString() + " | ";
			}
		}
		
		return descritoresOrdenados;
		
		
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

		
}
	

