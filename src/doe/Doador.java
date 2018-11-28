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
		
		Item item = new Item(id, descricao, quantidade, tags);
		
		if (itens.containsValue(item)) {
			this.procuraItem(item, quantidade, tags);
		}
		else {
			this.itens.put(id, item);
		}
	}
	
	@Override
	public String toString() {
		return String.format("%s/%s, %s, %s, status: %s", this.nome, this.id, this.email, this.celular, this.status);
	}

	
	public String getItem(int idItem) {
		if (!(this.verificaItem(idItem))) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		}
		return itens.get(idItem).toString();
	}
	
	public Item getItemOb(int id) {
		return itens.get(id);
	}
	
	private void procuraItem(Item item, int quantidade, String tags) {
		for (Item itemSistema : itens.values()) {
			if (itemSistema.equals(item)) {
				itemSistema.setQuantidade(quantidade);
				itemSistema.setTags(tags);
			}
		}
	}
		
	public String atualizaItem(int idItem, int quantidade, String tags) {
        if (!(tags == null) && !(tags.equals(""))) {
            this.itens.get(idItem).setTags(tags);
        }    
        if ((quantidade > 0)) {
            this.itens.get(idItem).setQuantidade(quantidade);
        }
        
        return itens.get(idItem).toString();
    }
	
	public boolean verificaItem(int idItem) {
		if(!(this.itens.containsKey(idItem))) {
			return false;
		}
		
		return true;
	}
	
	public void removeItem(int idItem) {
		if(this.itens.isEmpty()) {
			throw new IllegalArgumentException("O Usuario nao possui itens cadastrados.");
		}
		if(!(this.verificaItem(idItem))) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		}
		this.itens.remove(idItem);
	}
}
