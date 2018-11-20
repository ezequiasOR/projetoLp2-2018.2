package doe;

public class Facade {
	private ControllerUsuario ctlUsuarios;
	
	public void adicionaDoador(int id, String nome, String email, String celular, String classe) {
		ctlUsuarios.cadastraDoador(id, nome, email, celular, classe);
	}
	
	public String pesquisaUsuarioPorId(int id) {
		return ctlUsuarios.pesquisaUsuarioPorId(id);
	}
	
	public String pesquisaUsuarioPorNome(String nome) {
		return ctlUsuarios.pesquisaUsuarioPorNome(nome);
	}
	
	public String atualizaUsuario(int id, String nome, String email, String celular) {
		return ctlUsuarios.atualizaUsuario(id, nome, email, celular);
	}
	
	public void removeUsuario(int id) {
		ctlUsuarios.removeUsuario(id);
	}
	
	
	
	
}
