package doe;

import java.util.HashMap;
import java.util.Map;

public class Doador extends Usuario {
	
	private String status;
	private Map<Integer, Item> itens;

	public Doador(String id, String nome, String email, String celular, String classe) {
		super(id, nome, email, celular, classe);
		status = "doador";
		this.itens = new HashMap<>();
	}
	
	public void adicionaItem(int id, String descricao, int quantidade, String tags) {
		this.itens.put(id, new Item(id, descricao, quantidade, tags));
	}

	@Override
	public String toString() {
		return String.format("%s/%s, %s, %s, status: %s", this.nome, this.id, this.email, this.celular, this.status);
	}

	
	public String getItem(int idItem) {
		return itens.get(idItem).toString();
	}
	
}
