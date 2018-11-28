package doe;

public class Descritor {
	
	
	private String descritor;
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
