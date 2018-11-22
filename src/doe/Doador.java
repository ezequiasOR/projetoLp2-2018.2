package doe;

public class Doador extends Usuario {
	private String status;
	
	public Doador(String id, String nome, String email, String celular, String classe) {
		super(id, nome, email, celular, classe);
		this.status = "doador";
	}

}
