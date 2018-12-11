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
	public void testGetId() {
		assertEquals("123456789", usuario1.getId());
	}

	@Test
	public void testGetNome() {
		assertEquals("Matheus", usuario1.getNome());
	}

	@Test
	public void testGetEmail() {
		assertEquals("matheus@br", usuario1.getEmail());
	}

	@Test
	public void testGetCelular() {
		assertEquals("(99) 99999-9999", usuario1.getCelular());
	}

	@Test
	public void testGetClasse() {
		assertEquals("PESSOA_FISICA", usuario1.getClasse());
	}

	@Test
	public void testSetNomeVazio() {
		try {
			this.usuario1.setNome("");
		} catch (IllegalArgumentException iae) {
		}
	}

	@Test
	public void testSetNomeNulo() {
		try {
			this.usuario1.setNome(null);
		} catch (IllegalArgumentException iae) {
		}
	}

	@Test
	public void testSetNome() {
		usuario1.setNome("Joao");
		assertEquals("Joao", usuario1.getNome());
	}
	
	@Test
	public void testSetEmailVazio() {
		try {
			this.usuario1.setEmail("");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testSetEmailNull() {
		try {
			this.usuario1.setEmail(null);
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testSetEmail() {
		this.usuario1.setEmail("joao@br");
		assertEquals("joao@br", usuario1.getEmail());
	}
	
	@Test
	public void testSetCelularVazio() {
		try {
			this.usuario1.setCelular("");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testSetCelularNull() {
		try {
			this.usuario1.setCelular(null);
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testSetCelular() {
		this.usuario1.setCelular("(83) 1234-1234");
		assertEquals("(83) 1234-1234", usuario1.getCelular());
	}

}
