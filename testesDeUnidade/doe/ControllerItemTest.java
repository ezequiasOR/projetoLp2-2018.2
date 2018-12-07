package doe;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Controllers.ControllerItem;
import Controllers.ControllerUsuario;


class ControllerItemTest {
	
	private ControllerUsuario controllerUsuario;
	private ControllerItem controllerItem;
	
	@BeforeEach
	public void Before() throws Exception {
		controllerUsuario = new ControllerUsuario(controllerItem);
		controllerItem = new ControllerItem(controllerUsuario);
		controllerUsuario.cadastraDoador("123123123", "Daniel", "daniel@com", "(54) 95545-4545", "PESSOA_FISICA");
		controllerItem.adicionaDescritor("Livro");
		controllerItem.adicionaDescritor("jaqueta de couro");
		controllerItem.adicionaDescritor("curso de programacao");
		controllerUsuario.lerReceptores("testesDeUnidade/doe/testesDeUnidade.csv");
		controllerUsuario.adicionaItemNecessario("84473712044", "Livro", 2, "programacao");
		controllerUsuario.adicionaItem("123123123", "livro", 2, "");
	}

	@Test
	public void testAdicionaDescritorNulo() {
		try {
			controllerItem.adicionaDescritor(null);
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testAdicionaDescritorVazio() {
		try {
			controllerItem.adicionaDescritor("");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testAdicionaDescritorExistente() {
		try {
			controllerItem.adicionaDescritor("Livro");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testListarDescritores() {
		assertEquals("0 - curso de programacao | 0 - jaqueta de couro | 2 - livro", controllerItem.listarDescritores());
	}
	
	@Test
	public void testListaItensNecessarios() {
		assertEquals("1 - livro, tags: [programacao], quantidade: 2, Receptor: Murilo Luiz Brito/84473712044", controllerItem.listaItensNecessarios());
	}
	
	@Test
	public void testListaItemPorDescricao1() {
		assertEquals("", controllerItem.listaItemPorDescricao("curso de programacao"));
	}
	
	@Test
	public void testListaItemPorDescricao2() {
		assertEquals("", controllerItem.listaItemPorDescricao("jaqueta de couro"));
	}
	
	@Test
	public void testListaItemPorDescricaoNula() {
		try {
			controllerItem.listaItemPorDescricao(null);
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testListaItemPorDescricaoVazia() {
		try {
			controllerItem.listaItemPorDescricao("");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testListaItemPorDescricao3() {
		assertEquals("2 - livro, tags: [], quantidade: 2", controllerItem.listaItemPorDescricao("livro"));
	}
	
	
}
