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
		Item i = new Item(id, descricao, quantidade, tags);
		
		if (itens.containsValue(i)) {
			this.percorre(i, quantidade, tags);
		}
		else {
			this.itens.put(id, i);
		}
	}
	
	@Override
	public String toString() {
		return String.format("%s/%s, %s, %s, status: %s", this.nome, this.id, this.email, this.celular, this.status);
	}

	
	public String getItem(int idItem) {
		return itens.get(idItem).toString();
	}
	
	private void percorre(Item i, int quantidade, String tags) {
		for (Item item : itens.values()) {
			if (item.equals(i)) {
				item.setQuantidade(quantidade);
				item.setTags(tags);
			}
		}
	}
		
	public String atualizaItem(int idItem, int quantidade, String tags) {
		this.itens.get(idItem).setQuantidade(quantidade);
		this.itens.get(idItem).setTags(tags);
		
		return itens.get(idItem).toString();
	}
	
	public void removeItem(int idItem) {
		this.itens.remove(idItem);
	}
}
