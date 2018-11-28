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
	
	public void adicionaItem(Item item) {
		this.itensSistema.add(item);
		Collections.sort(this.itensSistema,new ComparatorItemQuantidade());
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
	

