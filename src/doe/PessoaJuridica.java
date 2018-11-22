package doe;

public class PessoaJuridica extends Usuario{
	private String cnpj;
	private String status = "Doador";
	private String aquiVaiSerItensParaDoar;
	
	public PessoaJuridica(String id, String nome, String email, String celular, String classe) {
		super(id, nome, email, celular, classe);
		this.aquiVaiSerItensParaDoar = "";
	}
	
	public String formataCnpj(String id) {
		cnpj = "";
		
		for (int i = 0; i<id.length();i++) {
			cnpj += id.charAt(i);
			if (i==1) {
				cnpj += '.';
			}
			else if (i == 4) {
				cnpj += '.';
			}
			else if (i == 7) {
				cnpj += '/';
			}
			else if (i == 11) {
				cnpj += '-';
			}
		}
		return cnpj;
	}
	
}
