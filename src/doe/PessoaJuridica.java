package doe;

public class PessoaJuridica extends Usuario{
	private String status = "Doador";
	private String aquiVaiSerItensParaDoar;
	
	public PessoaJuridica(String id, String nome, String email, String celular, String classe) {
		super(id, nome, email, celular, classe);
		this.aquiVaiSerItensParaDoar = "";
	}
	
	
	
}
