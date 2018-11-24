package doe;

public class Doador extends Usuario {
	private String status;

	public Doador(String id, String nome, String email, String celular, String classe) {
		super(id, nome, email, celular, classe);
		status = "doador";
	}

	@Override
	public String toString() {
		return String.format("%s/%s, %s, %s, status: %s", this.nome, this.id, this.email, this.celular, this.status);
	}
	
}
