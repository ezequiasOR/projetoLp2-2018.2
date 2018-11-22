package doe;

public class PessoaFisica extends Usuario {
	private String status = "Doador";
	
	public PessoaFisica(String id, String nome, String email, String celular, String classe) {
		super(id, nome, email, celular, classe);
	}
	

	
	@Override
	public String toString() {
		return String.format("%s/%s, %s, %s, status: %s", this.nome, this.id, this.email, this.celular, this.status );
	}
	
}
