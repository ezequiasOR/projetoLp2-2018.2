package doe;

import java.util.HashSet;
import java.util.Set;

public class Doador extends Usuario {
	
	private String status;
	private Set<Item> itens;

	public Doador(String id, String nome, String email, String celular, String classe) {
		super(id, nome, email, celular, classe);
		status = "doador";
		this.itens = new HashSet<>();
	}
	
	public void adicionaItem(String descricao, int quantidade, String tags) {
		this.itens.add(new Item(descricao, quantidade, tags));
	}

	@Override
	public String toString() {
		return String.format("%s/%s, %s, %s, status: %s", this.nome, this.id, this.email, this.celular, this.status);
	}
	
	
	
}
