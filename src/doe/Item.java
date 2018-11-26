package doe;

import java.util.Arrays;

public class Item {
	
	private int quantidade;
	private int id;
	private String descricaoItem;
	private String[] tags;
	
	/*
	 * faltam as exce��es.
	 */
	public Item(int id, String descricao, int quantidade, String tags) {
		this.descricaoItem = descricao;
		this.quantidade = quantidade;
		this.tags = tags.split(",");
		this.id = id;
	}
	
	public void adicionaQuantidade(int quant) {
		this.quantidade = this.quantidade + quant;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricaoItem == null) ? 0 : descricaoItem.hashCode());
		result = prime * result + quantidade;
		result = prime * result + Arrays.hashCode(tags);
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
		Item other = (Item) obj;
		if (descricaoItem == null) {
			if (other.descricaoItem != null)
				return false;
		} else if (!descricaoItem.equals(other.descricaoItem))
			return false;
		if (id != other.id)
			return false;
		if (quantidade != other.quantidade)
			return false;
		if (!Arrays.equals(tags, other.tags))
			return false;
		return true;
	}
	
	public String toString() {
		return String.format("%s - %s, tags: %s, quantidade: %d", Integer.toString(this.id), this.descricaoItem, this.tags, this.quantidade);
	}
	
}
