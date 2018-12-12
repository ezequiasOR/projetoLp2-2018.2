package doe;

import java.io.Serializable;

/**
 * Representacao de um descritor no sistema.
 * Cada descritor e unico.
 * Todo descritor possui descricao e quantidade.
 * 
 * @author Joao Vitor de Melo Cavalcante e Souza.
 * @author Ezequias de Oliveira Rocha.
 * @author Felipe Jeronimo Bernardo da Silva.
 *
 */
public class Descritor implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6777904562205984086L;

	/**
	 * Descritor do item.
	 */
	private String descritor;
	
	/**
	 * Quantidade do item.
	 */
	private int quantidade;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descritor == null) ? 0 : descritor.hashCode());
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
		Descritor other = (Descritor) obj;
		if (descritor == null) {
			if (other.descritor != null)
				return false;
		} else if (!descritor.equals(other.descritor))
			return false;
		return true;
	}
	
	/**
	 * Construtor do descritor.
	 * @param descritor Descritor do item.
	 */
	public Descritor(String descritor) {
		this.descritor = descritor;
		this.quantidade = 0;
	}
	
	/**
	 * Construtor do descritor.
	 * @param descritor Descritor do item.
	 * @param quantidade Quantidade do item.
	 */
	public Descritor(String descritor, int quantidade) {
		this.descritor = descritor;
		this.quantidade = quantidade;
	}
	
	/**
	 * @return Retorna o descritor.
	 */
	public String getDescritor() {
		return this.descritor;
	}
	
	/**
	 * Altera a quantidade do item.
	 * 
	 * @param quantidade Nova quantidade.
	 */
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	/**
	 * @return A representacao em String do descritor, no formato: (quantidade) - (descritor).
	 */
	public String toString() {
		return this.quantidade + " - " + this.descritor;
	}
	
	

}
