package doe;

public class Facade {
	private ControllerUsuarios ctlUsuarios;
	
	public void adicionaDoador(int id, String nome, String email, String celular, String classe) {
		ctlUsuarios.adicionaDoador(id, nome, email, celular, classe);
	}
}
