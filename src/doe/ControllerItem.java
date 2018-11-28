package doe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

public class ControllerItem {
	
	private Set<String> descritores;
	private Set<Descritor> descritoresSistema;
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
		
		ArrayList<Descritor> itensOrdenadosPorNome = new ArrayList<>();
		
		for(Descritor d: this.descritoresSistema) {
			itensOrdenadosPorNome.add(d);
		}
		
		Collections.sort(itensOrdenadosPorNome, new ComparatorDescritor());
		
		String itensOrdenados = "";
		
		for(int i = 0; i < itensOrdenadosPorNome.size(); i++) {
			if(i == itensOrdenadosPorNome.size()-1) {
				itensOrdenados = itensOrdenados + itensOrdenadosPorNome.get(i).toString();
			}
			else {
				itensOrdenados = itensOrdenados + itensOrdenadosPorNome.get(i).toString() + " | ";
			}
		}
		
		return itensOrdenados;
		
		
	}
	/*
	public boolean cadastraItemSistema(int idItem, String descricao, int quantidade, String tags) {
		
		Item novoItem = new Item(idItem, descricao, quantidade, tags);
		
		for(Item itemSistema: this.itensCadastrados) {
			if(itemSistema.equals(novoItem)) {
				this.procuraItemCadastrado(itemSistema, quantidade);
				return false;
			}
		}
		
		this.itensCadastrados.add(novoItem);
		return true;
		
	}
	
	private void procuraItemCadastrado(Item item, int quantidade) {
		for (Item itemSistema : this.itensCadastrados) {
			if (itemSistema.equals(item)) {
				itemSistema.setQuantidade(quantidade);
			
			}
			
	*/		
		
	}
	

