package doe;

public class Receptor extends Usuario{
	private String status;
	
	public Receptor(String id, String nome, String email, String celular, String classe) {
		super(id, nome, email, celular, classe);
		this.status = "receptor";
	}

}
