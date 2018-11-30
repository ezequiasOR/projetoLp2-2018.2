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
	public void getId() {
		assertEquals(1, item1.getId());
	}
	
	@Test
	public void getDescricao() {
		assertEquals("casaco", item1.getDescricaoItem());
	}
	
	@Test
	public void getQuantidade() {
		assertEquals(2, item1.getQuantidade());
	}

}
