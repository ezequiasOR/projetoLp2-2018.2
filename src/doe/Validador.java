package doe;

public class Validador {

	
	public void validaCadastroDeDoador(String id, String nome, String email, String celular, String classe) {
		if (nome == null || nome.equals("")) {
			throw new IllegalArgumentException("Entrada invalida: nome nao pode ser vazio ou nulo.");
		}
		if (email == null || email.equals("")) {
			throw new IllegalArgumentException("Entrada invalida: email nao pode ser vazio ou nulo.");
		}
		if (celular == null || celular.equals("")) {
			throw new IllegalArgumentException("Entrada invalida: celular nao pode ser vazio ou nulo.");
		}
		if (classe == null || classe.equals("")) {
			throw new IllegalArgumentException("Entrada invalida: classe nao pode ser vazia ou nula.");
		}
		if (id == null || id.equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
	}
	
	public boolean validaTipoDeUsuario(String classe) {
		if (classe.equals("PESSOA_FISICA")) {
			return true;
		}
		else if (classe.toUpperCase().equals("IGREJA") || classe.toUpperCase().equals("ONG") || classe.toUpperCase().equals("ORGAO PUBLICO MUNICIPAL") || classe.toUpperCase().equals("ORGAO PUBLICO ESTADUAL")) {
			return false;
		}
		else if (classe.toUpperCase().equals("ORGAO PUBLICO FEDERAL") || classe.toUpperCase().equals("ASSOCIACAO") || classe.toUpperCase().equals("SOCIEDADE")) {
			return false;
		}
		return false;
	}
	
	public void verificaSeClasseExiste(String classe) {
		if (!classe.toUpperCase().equals("PESSOA_FISICA") && !classe.toUpperCase().equals("IGREJA") && !classe.toUpperCase().equals("ONG") && !classe.toUpperCase().equals("ORGAO PUBLICO MUNICIPAL")
				&& !classe.toUpperCase().equals("ORGAO PUBLICO ESTADUAL") && !classe.toUpperCase().equals("ORGAO PUBLICO FEDERAL") && !classe.toUpperCase().equals("ASSOCIACAO") && !classe.toUpperCase().equals("SOCIEDADE")) {
			throw new IllegalArgumentException("Entrada invalida: opcao de classe invalida.");
		}
		
	}

	public void validaPesquisaPorId(String id) {
		if (id == null || id.equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
	}

	public void validaPesquisaPorNome(String nome) {
		if (nome == null || nome.equals("")) {
			throw new IllegalArgumentException("Entrada invalida: nome nao pode ser vazio ou nulo.");
		}
	}
}
