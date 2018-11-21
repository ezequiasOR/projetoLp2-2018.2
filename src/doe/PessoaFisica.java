package doe;

public class PessoaFisica extends Usuario {
	private String cpf;
	private String status = "Doador";
	
	public PessoaFisica(String id, String nome, String email, String celular, String classe) {
		super(id, nome, email, celular, classe);
		cpf = formataCpf(id);
	}
	
	public String formataCpf(String id) {
		cpf = "";
		
		for (int i = 0; i<id.length();i++) {
			cpf += id.charAt(i);
			if (i==2) {
				cpf += '.';
			}
			else if (i == 5) {
				cpf += '.';
			}
			else if (i == 8) {
				cpf += '-';
			}
		}
		return cpf;
	}

	public String getCpf() {
		return cpf;
	}

	@Override
	public String toString() {
		return String.format("%s/%s, %s, %s, status: %s", this.nome, this.cpf, this.email, this.celular, this.status );
	}
	
}
