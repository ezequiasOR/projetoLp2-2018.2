package doe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

public class ControllerItem {
	
	private ArrayList<Item> itensCadastrados;
	private Set<String> descritores;
	private int id;
	private Validador validador;
	
	public int identificador() {
		this.id += 1;
		return this.id;
	}
	
	public ControllerItem() {
		this.validador = new Validador();
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
	}
	
	public void registraItem(Item item) {
		this.itensCadastrados.add(item);
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
		
		ArrayList<Item> itensOrdenadosPorNome = new ArrayList<>();
		
		for(Item i: this.itensCadastrados) {
			itensOrdenadosPorNome.add(i);
		}
		
		Collections.sort(itensOrdenadosPorNome, new ComparatorNomesItens());
		
		String itensOrdenados = "";
		
		for(int i = 0; i < itensOrdenadosPorNome.size(); i++) {
			if(i == itensOrdenadosPorNome.size()-1) {
				itensOrdenados = itensOrdenados + itensOrdenadosPorNome.get(i).nomesPesquisa();
			}
			else {
				itensOrdenados = itensOrdenados + itensOrdenadosPorNome.get(i).nomesPesquisa() + " | ";
			}
		}
		
		return itensOrdenados;
		
		
	}
	
}
