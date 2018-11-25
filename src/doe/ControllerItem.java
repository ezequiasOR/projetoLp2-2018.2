package doe;

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
		for (String descritor : descritores) {
			if (descricao.trim().equals(descritor)) {
				throw new IllegalArgumentException("Descritor de Item ja existente: " + descricao.trim() + ".");
			}
		}
		
		this.descritores.add(descricao.trim());
	}
}
