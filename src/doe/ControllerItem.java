package doe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ControllerItem {
	private Set<String> descritores;
	private Validador validador;
	
	public ControllerItem() {
		this.validador = new Validador();
		this.descritores = new HashSet<>();
	}
	
	
	public void adicionaDescritor(String descricao) {
		
		this.validador.validaDescritor(descricao);
		
		String descricaoAdicionar = descricao.toLowerCase().trim();
		
		if(this.descritores.contains(descricaoAdicionar)) {
			throw new IllegalArgumentException("Descritor de Item ja existente: " + descricaoAdicionar + ".");
		}
		
		this.descritores.add(descricaoAdicionar);
		
	}
}
