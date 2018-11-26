package doe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ControllerItem {
	
	private Set<String> descritores;
	private int id;
	private Validador validador;
	
	public int getIdentificador() {
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
	
	public boolean contemDescritor(String descricao) {
		if(this.descritores.contains(descricao)) {
			return true;
		}
		
		return false;
	}


	public Set<String> getDescritores() {
		return descritores;
	}
	
}
