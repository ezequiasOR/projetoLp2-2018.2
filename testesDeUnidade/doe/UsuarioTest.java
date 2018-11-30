package doe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UsuarioTest {
	
	private Usuario usuario1;
	
	@BeforeEach
	public void Before() {
		usuario1 = new Usuario("123456789", "Matheus", "matheus@br", "(99) 99999-9999", "PESSOA_FISICA", "doador");
	}
	
	
	@Test
	public void testDoadorNomeNulo() {
		try {
			usuario1 = new Usuario("123123123", null, "carla@br", "(21) 21212-1212", "PESSOA_FISICA", "doador");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testDoadorNomeVazio() {
		try {
			usuario1 = new Usuario("123123123", "", "carla@br", "(21) 21212-1212", "PESSOA_FISICA", "doador");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testDoadorEmailNulo() {
		try {
			usuario1 = new Usuario("123123123", "Carla", null, "(21) 21212-1212", "PESSOA_FISICA", "doador");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testDoadorEmailVazio() {
		try {
			usuario1 = new Usuario("123123123", "Carla", "", "(21) 21212-1212", "PESSOA_FISICA", "doador");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testDoadorCelularNulo() {
		try {
			usuario1 = new Usuario("123123123", "Carla", "carla@br", null, "PESSOA_FISICA", "doador");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testDoadorCelularVazio() {
		try {
			usuario1 = new Usuario("123123123", "Carla", "carla@br", "", "PESSOA_FISICA", "doador");
		} catch (IllegalArgumentException iae) {
		}
	}
	

	@Test
	public void testDoadorClasseNula() {
		try {
			usuario1 = new Usuario("123123123", "Carla", "carla@br", "(21) 21212-1212", null, "doador");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testDoadorClasseVazia() {
		try {
			usuario1 = new Usuario("123123123", "Carla", "carla@br", "(21) 21212-1212", "", "doador");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void getId() {
		assertEquals("123456789", usuario1.getId());
	}
	
	@Test
	public void getNome() {
		assertEquals("Matheus", usuario1.getNome());
	}
	
	@Test
	public void getEmail() {
		assertEquals("matheus@br", usuario1.getEmail());
	}
	
	@Test
	public void getCelular() {
		assertEquals("(99) 99999-9999", usuario1.getCelular());
	}
	
	@Test
	public void getClasse() {
		assertEquals("PESSOA_FISICA", usuario1.getClasse());
	}
}
