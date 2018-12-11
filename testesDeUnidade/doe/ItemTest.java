package doe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItemTest {

	private Item item1;

	@BeforeEach
	public void Before() {
		item1 = new Item(1, "casaco", 2, "conforto,algodao", "Matheus", "123456789");
	}

	@Test
	public void testItemIdNegativo() {
		try {
			item1 = new Item(-100, "casaco", 2, "conforto,algodao", "Matheus", "123456789");
		} catch (IllegalArgumentException iae) {
		}
	}

	@Test
	public void testItemDescricaoVazia() {
		try {
			item1 = new Item(2, "", 2, "conforto,algodao", "Matheus", "123456789");
		} catch (IllegalArgumentException iae) {
		}
	}

	@Test
	public void testItemDescricaoNula() {
		try {
			item1 = new Item(2, null, 2, "conforto,algodao", "Matheus", "123456789");
		} catch (IllegalArgumentException iae) {
		}
	}

	@Test
	public void testItemQuantidadeNegativa() {
		try {
			item1 = new Item(2, "casaco", -2, "conforto,algodao", "Matheus", "123456789");
		} catch (IllegalArgumentException iae) {
		}
	}

	@Test
	public void testItemTagsVazia() {
		try {
			item1 = new Item(2, "casaco", 2, "", "Matheus", "123456789");
		} catch (IllegalArgumentException iae) {
		}
	}

	@Test
	public void testGetId() {
		assertEquals(1, item1.getId());
	}

	@Test
	public void testGetDescricao() {
		assertEquals("casaco", item1.getDescricaoItem());
	}

	@Test
	public void testGetQuantidade() {
		assertEquals(2, item1.getQuantidade());
	}

	@Test
	public void testAdicionaQuantidadeZero() {
		try {
			item1.adicionaQuantidade(0);
		} catch (IllegalArgumentException iae) {
		}
	}

	@Test
	public void testAdicionaQuantidadeNegativa() {
		try {
			item1.adicionaQuantidade(-522);
		} catch (IllegalArgumentException iae) {
		}
	}

	@Test
	public void testAdicionaQuantidade() {
		item1.adicionaQuantidade(3);
		assertEquals(5, item1.getQuantidade());
	}

	@Test
	public void testSetNomeVazio() {
		try {
			item1.setNomeUsuario("");
		} catch (IllegalArgumentException iae) {
		}
	}

	@Test
	public void testSetNomeNulo() {
		try {
			item1.setNomeUsuario(null);
		} catch (IllegalArgumentException iae) {
		}
	}

	@Test
	public void testSetQuantidadeZero() {
		try {
			item1.setQuantidade(0);
		} catch (IllegalArgumentException iae) {
		}
	}

	@Test
	public void testSetQuantidadeNegativa() {
		try {
			item1.setQuantidade(-1);
		} catch (IllegalArgumentException iae) {
		}
	}

	@Test
	public void testSetQuantidade() {
		item1.setQuantidade(5);
		assertEquals(5, item1.getQuantidade());
	}
	
	@Test
	public void testSetDescricaoItemVazia() {
		try {
			item1.setDescricaoItem("");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testSetDescricaoItemNula() {
		try {
			item1.setDescricaoItem(null);
		} catch (IllegalArgumentException iae) {
		}
	}

}
