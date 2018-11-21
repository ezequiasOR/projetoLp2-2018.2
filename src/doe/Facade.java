package doe;

public class Facade {
	private ControllerUsuario ctlUsuarios;
	
	public Facade() {
		ctlUsuarios = new ControllerUsuario();
	}
	
	public void adicionaDoador(String id, String nome, String email, String celular, String classe) {
		ctlUsuarios.cadastraDoador(id, nome, email, celular, classe);
	}
	
	public String pesquisaUsuarioPorId(String id) {
		return ctlUsuarios.pesquisaUsuarioPorId(id);
	}
	
	public String pesquisaUsuarioPorNome(String nome) {
		return ctlUsuarios.pesquisaUsuarioPorNome(nome);
	}
	
	public String atualizaUsuario(String id, String nome, String email, String celular) {
		return ctlUsuarios.atualizaUsuario(id, nome, email, celular);
	}
	
	public void removeUsuario(String id) {
		ctlUsuarios.removeUsuario(id);
	}
	
	
	
	
}
