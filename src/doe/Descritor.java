package doe;

/**
 * Representacao de um descritor no sistema.
 * Cada descritor e unico.
 * Todo descritor possui descricao e quantidade.
 * 
 * @author João Vitor de Melo Cavalcante e Souza
 * @author Ezequias de Oliveira Rocha
 * @author Felipe Jerônimo Bernardo da Silva
 *
 */
public class Descritor {
	
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
	
	public Descritor(String descritor) {
		this.descritor = descritor;
		this.quantidade = 0;
	}
	
	public Descritor(String descritor, int quantidade) {
		this.descritor = descritor;
		this.quantidade = quantidade;
	}
	
	public String getDescritor() {
		return this.descritor;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public String toString() {
		return this.quantidade + " - " + this.descritor;
	}
	
	

}
