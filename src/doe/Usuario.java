package doe;

import java.util.Set;

public abstract class Usuario {

	protected String id;
	protected String nome;
	protected String email;
	protected String celular;
	protected String classe;

	public Usuario(String id, String nome, String email, String celular, String classe) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		this.classe = classe;
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
	
	public void adicionaItem(int id, String descricao, int quantidade, String tags) {
	}
	
	public String atualizaItem(int idItem, int quantidade, String tags) {
		return "";
	}
	
	public String getItem(int idItem) {
		return "";
	}
	
}
