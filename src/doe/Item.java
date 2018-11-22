package doe;



public class Item {
	
	private int quantidade;
	private int id;
	private String descricaoItem;
	private String[] tags;
	
	/*
	 * faltam as exceções.
	 */
	public Item(int id, String desc, int quant, String tags) {
		this.id = id;
		this.descricaoItem = desc;
		this.quantidade = quantidade;
		this.tags = tags.split(",");
		
	}
	
	public void adicionaQuantidade(int quant) {
		this.quantidade = this.quantidade + quant;
	}
	
	public String toString() {
		return this.id + " - " + this.descricaoItem +", tags: " +  this.tags + ", quantidade: " + this.quantidade;
	}
}
