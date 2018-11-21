package doe;

public class PessoaJuridica extends Usuario{
	private String cnpj;
	private String status = "Doador";
	
	public PessoaJuridica(String id, String nome, String email, String celular, String classe) {
		super(id, nome, email, celular, classe);
		// TODO Auto-generated constructor stub
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
