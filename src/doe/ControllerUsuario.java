package doe;

import java.util.LinkedHashMap;

public class ControllerUsuario {
	
	private LinkedHashMap<String,Usuario> usuarios;
	
	public ControllerUsuario() {
		this.usuarios = new LinkedHashMap<String,Usuario>();
	}
	
	public void cadastraDoador(String nome, String email, String celular, String id, String classe) {
		
	}
	
	public String pesquisaUsuarioPorId(String id) {
		return null;
	}
	
	public void removerUsuario(String id) {
		
	}

}
