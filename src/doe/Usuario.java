package doe;

import java.util.HashMap;
import java.util.Map;

public class Usuario {

	private String id;
	private String nome;
	private String email;
	private String celular;
	private String classe;
	private String status;
	private Map<Integer, Item> itens;

	public Usuario(String id, String nome, String email, String celular, String classe, String status) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		this.classe = classe;
		this.status = status;
		this.itens = new HashMap<>();
	}

	public String getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getClasse() {
		return classe;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
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

	private void procuraItem(Item item, int quantidade, String tags) {
		for (Item itemSistema : itens.values()) {
			if (itemSistema.equals(item)) {
				itemSistema.setQuantidade(quantidade);
				itemSistema.setTags(tags);
			}
		}
	}

	public String getItem(int idItem) {
		if (!(this.verificaItem(idItem))) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		}
		return itens.get(idItem).toString();
	}

	public void removeItem(int idItem) {
		if (this.itens.isEmpty()) {
			throw new IllegalArgumentException("O Usuario nao possui itens cadastrados.");
		}
		if (!(this.verificaItem(idItem))) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		}
		this.itens.remove(idItem);
	}

	public Item getItemOb(int id) {
		return itens.get(id);
	}

	public boolean verificaItem(int idItem) {
		if (!(this.itens.containsKey(idItem))) {
			return false;
		}

		return true;
	}

	public void adicionaItem(int id, String descricao, int quantidade, String tags) {

		Item item = new Item(id, descricao, quantidade, tags, this.nome, this.id);

		if (itens.containsValue(item)) {
			this.procuraItem(item, quantidade, tags);
		} else {
			this.itens.put(id, item);
		}
	}

	@Override
	public String toString() {
		return String.format("%s/%s, %s, %s, status: %s", this.nome, this.id, this.email, this.celular, this.status);
	}

	public String atualizaItemNecessario(int idItem, int novaQuantidade, String novasTags) {
		if (!this.itens.containsKey(idItem)) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		}
		
		if (!(novasTags == null) && !(novasTags.equals(""))) {
			this.itens.get(idItem).setTags(novasTags);
		}
		if ((novaQuantidade > 0)) {
			this.itens.get(idItem).setQuantidade(novaQuantidade);
		}

		return itens.get(idItem).toString();

	}

	public void removeItemNecessario(int idItem) {
		this.itens.remove(idItem);
	}
	
	public boolean vazio() {
		if(itens.keySet().size() == 0) {
			return true;
		}
		return false;
	}
}
