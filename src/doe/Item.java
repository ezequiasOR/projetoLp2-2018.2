package doe;

import java.io.Serializable;
import java.util.Arrays;

import Validador.Validador;

/**
 * Representação de um item no sistema.
 * Cada item pertence a um doador ou um receptor.
 * Todo item e representado por Id, Quantidade, Descricao e Tags.
 * 
 * @author Joao Vitor de Melo Cavalcante e Souza.
 * @author Ezequias de Oliveira Rocha.
 * @author Felipe Jeronimo Bernardo da Silva.
 * 
 */
public class Item implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3875270557684443654L;

	/**
	 * Quantidade do item.
	 */
	private int quantidade;
	
	/**
	 * Id do item.
	 */
	private int id;
	
	/**
	 * Descricao do item.
	 */
	private String descricaoItem;
	
	/**
	 * Tags do item.
	 */
	private String[] tags;
	
	/**
	 * Nome do doador do item.
	 */
	private String nomeUsuario;
	
	/**
	 * Id do doador do item.
	 */
	private String idUsuario;
	
	/**
	 * Validador de usuario.
	 */
	private Validador validador = new Validador();
	
	private int pontos;
	
	
	/**
	 * Construtor do item.
	 * 
	 * @param id Id do item.
	 * @param descricao Descricao do item.
	 * @param quantidade Quantidade do item.
	 * @param tags Tags do item.
	 * @param nomeDoador Nome do doador do item.
	 * @param idDoador Id do doador do item.
	 */
	
	public Item(int id, String descricao, int quantidade, String tags, String nomeUsuario, String idDoador) {
		this.validador.validaIdItem(id);
		this.validador.validaDescritor(descricao);
		this.validador.validaQuantidade(quantidade);
		
		this.descricaoItem = descricao;
		this.quantidade = quantidade;
		this.tags = tags.split(",");
		this.id = id;
		this.nomeUsuario = nomeUsuario;
		this.idUsuario = idDoador;
		this.pontos = 0;
	}
	
	/**
	 * Adiciona uma nova quantidade para o item.
	 * 
	 * @param quant Quantidade do item.
	 */
	public void adicionaQuantidade(int quantidade) {
		this.validador.validaQuantidade(quantidade);
		this.quantidade = this.quantidade + quantidade;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricaoItem == null) ? 0 : descricaoItem.hashCode());
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
		if (!Arrays.equals(tags, other.tags))
			return false;
		return true;
	}
	
	/**
	 * @return Retorna a quantidade do item.
	 */
	public int getQuantidade() {
		return quantidade;
	}
	
	/**
	 * Altera o nome do usuario.
	 * @param nome Novo nome a ser alterado.
	 */
	public void setNomeUsuario(String nome) {
		this.validador.validaNome(nome);
		
		this.nomeUsuario = nome;
	}

	/**
	 * Altera a quantidade do item.
	 * @param quantidade Nova quantidade a ser alterada.
	 */
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	/**
	 * @return Retorna a descricao do item.
	 */
	public String getDescricaoItem() {
		return descricaoItem;
	}

	/**
	 * Altera a descricao do item.
	 * @param descricaoItem Nova descricao do item.
	 */
	public void setDescricaoItem(String descricaoItem) {
		this.validador.validaDescritor(descricaoItem);
		
		this.descricaoItem = descricaoItem;
	}

	/**
	 * 
	 * @return Retorna as tags referentes ao item.
	 */
	public String[] getTags() {
		return tags;
	}
	
	/**
	 * Altera as tags que o item possui.
	 * @param tags Novas tags do item.
	 */
	public void setTags(String tags) {
		this.tags = tags.split(",");
	}

	/**
	 * 
	 * @return Retorna o id do item.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * 
	 * @return Retorna a pontuacao em um match.
	 */
	public int getPontos() {
		return pontos;
	}

	/**
	 * 
	 * @param pontos Altera a pontuacao enquanto realiza um match.
	 */
	public void setPontos(int pontos) {
		this.pontos += pontos;
	}
	

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	/**
	 * 
	 * @return Retorna a quantidade e a descricao do item
	 */
	public String nomesPesquisa() {
		String nomePesquisa = this.quantidade + " - " + this.descricaoItem;
		return nomePesquisa;
	}

	/**
	 * @return Retorna a representacao do item, no formato: (id) - (descricao do item), tags: (tags), quantidade: (quantidade).
	 */
	public String toString() {
		return String.format("%d - %s, tags: %s, quantidade: %d", this.id, this.descricaoItem, Arrays.toString(this.tags), this.quantidade);
	}
	
	/**
	 * @return Retorna a  representacao do sistema, no formato: (id) - (descricao do item), tags: (tags), quantidade: (quantidade), doador: (nome do doador)/(id do doador)"
	 */
	public String toStringSistema() {
		return String.format("%d - %s, tags: %s, quantidade: %d, doador: %s/%s", this.id, this.descricaoItem, Arrays.toString(this.tags), this.quantidade, this.nomeUsuario,this.idUsuario);

	}
	
	/**
	 * 
	 * @return Retorna a representacao de um item pedido por um receptor, no formato: (id) - (descricao), tags: (tags), quantidade: (quantidade), Receptor: (nomeReceptor)/id(idUsuario).
	 */
	public String toStringSistemaNecessario() {
		return String.format("%d - %s, tags: %s, quantidade: %d, Receptor: %s/%s", this.id, this.descricaoItem, Arrays.toString(this.tags), this.quantidade, this.nomeUsuario,this.idUsuario);

	}

	public void resetaPontos() {
		this.pontos = 0;
	}
	
	
}
