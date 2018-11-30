package doe;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Controllers.ControllerItem;
import Controllers.ControllerUsuario;

class FacadeTest {
	
	private ControllerUsuario controllerUsuario;
	private ControllerItem controllerItem;
	
	@BeforeEach
	public void Before() {
		controllerUsuario = new ControllerUsuario();
		controllerItem = new ControllerItem();
		controllerUsuario.cadastraDoador("59238650111", "Satya", "satya@br", "(83) 99221-2571", "PESSOA_FISICA");
		controllerUsuario.cadastraDoador("50270271338", "Lucas", "lucas12@br", "(83) 99982-9231", "PESSOA_FISICA");
		controllerUsuario.cadastraDoador("10357071312", "Lucas", "lucas34@br", "(83) 98249-1298", "PESSOA_FISICA");
		controllerUsuario.cadastraDoador("12094912484", "Lucas", "lucas56@br", "(83) 94813-4871", "PESSOA_FISICA");
		controllerUsuario.adicionaItem("59238650111", "cobertor", 5, "lencol,conforto", controllerItem);
		
		controllerItem.adicionaDescritor("Livro");
		controllerItem.adicionaDescritor("jaqueta de couro");
		controllerItem.adicionaDescritor("curso de programacao");
		controllerUsuario.adicionaItemNecessario("59238650111", "Livro", 2, "programacao", controllerItem);
		controllerUsuario.adicionaItemNecessario("59238650111", "Toalha de Banho", 2, "Adulto,TAM G,Azul", controllerItem);
	}
	
	@Test
	public void testLerReceptores() throws Exception {
		controllerUsuario.lerReceptores("testesDeUnidade/doe/testesDeUnidade.csv");
	}

	@Test
	public void testAdicionaDoadorIdNulo() {
		try {
			controllerUsuario.cadastraDoador(null, "joao", "joao@com", "(83) 11111-1111", "PESSOA_FISICA");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testAdicionaDoadorIdVazio() {
		try {
			controllerUsuario.cadastraDoador("", "joao", "joao@com", "(83) 11111-1111", "PESSOA_FISICA");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testAdicionaDoadorNomeNulo() {
		try {
			controllerUsuario.cadastraDoador("70513372911", null, "elizabethcalamity@br", "(83) 92918-0211", "PESSOA_FISICA");
			fail("Entrada invalida: nome nao pode ser vazio ou nulo.");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testAdicionaDoadorNomeVazio() {
		try {
			controllerUsuario.cadastraDoador("70513372911", "", "elizabethcalamity@br", "(83) 92918-0211", "PESSOA_FISICA");
			fail("Entrada invalida: nome nao pode ser vazio ou nulo.");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testAdicionaDoadorEmailNulo() {
		try {
			controllerUsuario.cadastraDoador("70513372911", "Elizabeth Ashe", null, "(83) 92918-0211", "PESSOA_FISICA");
			fail("Entrada invalida: email nao pode ser vazio ou nulo.");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testAdicionaDoadorEmailVazio() {
		try {
			controllerUsuario.cadastraDoador("70513372911", "Elizabeth Ashe", "", "(83) 92918-0211", "PESSOA_FISICA");
			fail("Entrada invalida: email nao pode ser vazio ou nulo.");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testAdicionaDoadorCelularNulo() {
		try {
			controllerUsuario.cadastraDoador("70513372911", "Elizabeth Ashe", "elizabethcalamity@br", null, "PESSOA_FISICA");
			fail("Entrada invalida: celular nao pode ser vazio ou nulo.");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testAdicionaDoadorCelularVazio() {
		try {
			controllerUsuario.cadastraDoador("70513372911", "Elizabeth Ashe", "elizabethcalamity@br", "", "PESSOA_FISICA");
			fail("Entrada invalida: celular nao pode ser vazio ou nulo.");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testAdicionaDoadorClasseNula() {
		try {
			controllerUsuario.cadastraDoador("70513372911", "Elizabeth Ashe", "elizabethcalamity@br", "(83) 92918-0211", null);
			fail("Entrada invalida: classe nao pode ser vazia ou nula.");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testAdicionaDoadorClasseVazia() {
		try {
			controllerUsuario.cadastraDoador("70513372911", "Elizabeth Ashe", "elizabethcalamity@br", "(83) 92918-0211", "");
			fail("Entrada invalida: classe nao pode ser vazia ou nula.");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testAdicionaDoadorJaExistente() {
		try {
			controllerUsuario.cadastraDoador("59238650111", "Satya", "satya@br", "(83) 99221-2571", "PESSOA_FISICA");
			fail("Usuario ja existente.");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testAdicionaDoador() {
		assertEquals("123123123", controllerUsuario.cadastraDoador("123123123", "carla", "carla", "(83 22222-2222)", "PESSOA_FISICA"));
	}
	
	@Test
	public void testPesquisaUsuarioPorIdNulo() {
		try {
			controllerUsuario.pesquisaUsuarioPorId(null);
			fail("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testPesquisaUsuarioPorIdVazio() {
		try {
			controllerUsuario.pesquisaUsuarioPorId("");
			fail("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testPesquisaUsuarioPorIdUsuarioNaoCadastrado() {
		try {
			controllerUsuario.pesquisaUsuarioPorId("123123123");
			fail("Usuario nao encontrado.");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testPesquisaUsuarioPorId() {
		assertEquals("Satya/59238650111, satya@br, (83) 99221-2571, status: doador", controllerUsuario.pesquisaUsuarioPorId("59238650111"));
	}
	
	@Test
	public void testPesquisaUsuarioPorNomeNulo() {
		try {
			controllerUsuario.pesquisaUsuarioPorId(null);
			fail("Entrada invalida: nome nao pode ser vazio ou nulo.");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testPesquisaUsuarioPorNomeVazio() {
		try {
			controllerUsuario.pesquisaUsuarioPorId("");
			fail("Entrada invalida: nome nao pode ser vazio ou nulo.");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testPesquisaUsuarioPorNomeNaoCadastrado() {
		try {
			controllerUsuario.pesquisaUsuarioPorId("abc");
			fail("Usuario nao encontrado.");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testPesquisaUsuarioPorNome1() {
		assertEquals("Satya/59238650111, satya@br, (83) 99221-2571, status: doador", controllerUsuario.pesquisaUsuarioPorNome("Satya"));
	}
	
	@Test
	public void testPesquisaUsuarioPorNome2() {
		assertEquals("Lucas/50270271338, lucas12@br, (83) 99982-9231, status: doador | Lucas/10357071312, lucas34@br, (83) 98249-1298, status: doador | Lucas/12094912484, lucas56@br, (83) 94813-4871, status: doador", 
				controllerUsuario.pesquisaUsuarioPorNome("Lucas"));
	}
	
	@Test
	public void testAtualizaUsuarioIdNulo() {
		try {
			controllerUsuario.atualizaUsuario(null, "Satya", "satya@br", "(83) 99221-2571");
			fail("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testAtualizaUsuarioIdVazio() {
		try {
			controllerUsuario.atualizaUsuario("", "Satya", "satya@br", "(83) 99221-2571");
			fail("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testAtualizaUsuarioNaoCadastrado() {
		try {
			controllerUsuario.atualizaUsuario("123123123", "Satya", "satya@br", "(83) 99221-2571");
			fail("Usuario nao encontrado.");
		}catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testAtualizaUsuario1() {
		assertEquals("Satya/59238650111, satya@com.br, (83) 99221-2571, status: doador", controllerUsuario.atualizaUsuario("59238650111", "", "satya@com.br", ""));
	}
	
	@Test
	public void testAtualizaUsuario2() {
		assertEquals("Satya/59238650111, satya@br, (83) 11111-1111, status: doador", controllerUsuario.atualizaUsuario("59238650111", "", "", "(83) 11111-1111"));
	}
	
	@Test
	public void testAtualizaUsuario3() {
		assertEquals("Maria/59238650111, satya@br, (83) 99221-2571, status: doador", controllerUsuario.atualizaUsuario("59238650111", "Maria", "", ""));
	}
	
	@Test
	public void testRemoveUsuarioIdNulo() {
		try {
			controllerUsuario.removeUsuario(null);
			fail("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testRemoveUsuarioIdVazio() {
		try {
			controllerUsuario.removeUsuario("");
			fail("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testRemoveUsuarioNaoCadastrado() {
		try {
			controllerUsuario.removeUsuario("123123123");
			fail("Usuario nao encontrado.");
		}catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testRemoveUsuario() {
		controllerUsuario.removeUsuario("50270271338");
		assertEquals("Lucas/10357071312, lucas34@br, (83) 98249-1298, status: doador | Lucas/12094912484, lucas56@br, (83) 94813-4871, status: doador", 
				controllerUsuario.pesquisaUsuarioPorNome("Lucas"));
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
	public void testAdicionaItemIdNulo() {
		try {
			controllerUsuario.adicionaItem(null, "camiseta", 2, "outfit,algodao", controllerItem);
			fail("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testAdicionaItemIdVazio() {
		try {
			controllerUsuario.adicionaItem("", "camiseta", 2, "outfit,algodao", controllerItem);
			fail("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testAdicionaItemDescricaoNula() {
		try {
			controllerUsuario.adicionaItem("59238650111", null, 2, "outfit,algodao", controllerItem);
			fail("Entrada invalida: descricao nao pode ser vazia ou nula.");
		}catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testAdicionaItemDescricaoVazia() {
		try {
			controllerUsuario.adicionaItem("59238650111", "", 2, "outfit,algodao", controllerItem);
			fail("Entrada invalida: descricao nao pode ser vazia ou nula.");
		}catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testAdicionaItemQuantidadeInvalida() {
		try {
			controllerUsuario.adicionaItem("59238650111", "camiseta", -1, "outfit,algodao", controllerItem);
			fail("Entrada invalida: quantidade deve ser maior que zero.");
		}catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testAdicionaItemUsuarioNaoCadastrado() {
		try {
			controllerUsuario.adicionaItem("123123123", "camiseta", 2, "outfit,algodao", controllerItem);
			fail("Usuario nao encontrado.");
		}catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testExibeItemDoadorNaoEmcontrado() {
		try {
			controllerUsuario.exibeItem(1, "123123123");
			fail("Usuario nao encontrado.");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testExibeItemNaoEncontrado() {
		try {
			controllerUsuario.exibeItem(50, "59238650111");
			fail("Item nao encontrado.");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testExibeItem() {
		assertEquals("1 - cobertor, tags: [lencol, conforto], quantidade: 5", controllerUsuario.exibeItem(1, "59238650111"));
	}
	
	@Test
	public void testAtualizaItemParaDoacaoIdItemNegativo() {
		try {
			controllerUsuario.atualizaItemParaDoacao(-5, "59238650111", 5, "lencol,conforto", controllerItem);
			fail("Entrada invalida: id do item nao pode ser negativo.");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testAtualizaItemParaDoacaoIdDoadorNulo() {
		try {
			controllerUsuario.atualizaItemParaDoacao(1, null, 5, "lencol,conforto", controllerItem);
			fail("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		} catch (IllegalArgumentException iae) {
		}
	}

	@Test
	public void testAtualizaItemParaDoacaoIdDoadorVazio() {
		try {
			controllerUsuario.atualizaItemParaDoacao(1, "", 5, "lencol,conforto", controllerItem);
			fail("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testAtualizaItemParaDoacaoQuantidade() {
		controllerUsuario.atualizaItemParaDoacao(1, "59238650111", 3, "", controllerItem);
		assertEquals("1 - cobertor, tags: [lencol, conforto], quantidade: 3", controllerUsuario.exibeItem(1, "59238650111"));
	}
	
	@Test
	public void testAtualizaItemParaDoacaoTags() {
		controllerUsuario.atualizaItemParaDoacao(1, "59238650111", 0, "lencol", controllerItem);
		assertEquals("1 - cobertor, tags: [lencol], quantidade: 5", controllerUsuario.exibeItem(1, "59238650111"));
	}
	
	@Test
	public void testRemoveItemParaDoacaoIdDoadorNulo() {
		try {
			controllerUsuario.removeItemParaDoacao("1", null, controllerItem);
			fail("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testRemoveItemParaDoacaoIdDoadorVazio() {
		try {
			controllerUsuario.removeItemParaDoacao("1", "", controllerItem);
			fail("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testRemoveItemParaDoacaoIdItemNulo() {
		try {
			controllerUsuario.removeItemParaDoacao(null, "59238650111", controllerItem);
			fail("Entrada invalida: id do item nao pode ser vazio ou nulo.");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testRemoveItemParaDoacaoIdItemVazio() {
		try {
			controllerUsuario.removeItemParaDoacao("", "59238650111", controllerItem);
			fail("Entrada invalida: id do item nao pode ser vazio ou nulo.");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testRemoveItemParaDoacaoIdItemNegativo() {
		try {
			controllerUsuario.removeItemParaDoacao("-1", "59238650111", controllerItem);
			fail("Entrada invalida: id do item nao pode ser negativo.");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testListarDescritores() {
		assertEquals("5 - cobertor | 0 - curso de programacao | 0 - jaqueta de couro | 0 - livro", controllerItem.listarDescritores());
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
		assertEquals("1 - cobertor, tags: [lencol, conforto], quantidade: 5", controllerItem.listaItemPorDescricao("cobertor"));
	}
	
	@Test
	public void testAdicionaItemNecessarioIdReceptorNulo() {
		try {
			controllerUsuario.adicionaItemNecessario(null, "cobertor", 3, "lencol,conforto", controllerItem);
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testAdicionaItemNecessarioIdReceptorVazio() {
		try {
			controllerUsuario.adicionaItemNecessario("", "cobertor", 3, "lencol,conforto", controllerItem);
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testAdicionaItemNecessarioIdReceptorNaoCadastrado() {
		try {
			controllerUsuario.adicionaItemNecessario("123123123", "cobertor", 3, "lencol,conforto", controllerItem);
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testAdicionaItemNecessarioIdReceptorDescricaoNula() {
		try {
			controllerUsuario.adicionaItemNecessario("84473712044", null, 3, "lencol,conforto", controllerItem);
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testAdicionaItemNecessarioIdReceptorDescricaoVazia() {
		try {
			controllerUsuario.adicionaItemNecessario("84473712044", "", 3, "lencol,conforto", controllerItem);
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testAdicionaItemNecessarioIdReceptorQuantidadeInvalida() {
		try {
			controllerUsuario.adicionaItemNecessario("84473712044", null, -5, "lencol,conforto", controllerItem);
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testAtualizaItemNecessarioIdReceptorNulo() {
		try {
			controllerUsuario.atualizaItemNecessario(null, 1, 5, "Adulto,TAM G,Azul", controllerItem);
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testAtualizaItemNecessarioIdReceptorVazio() {
		try {
			controllerUsuario.atualizaItemNecessario("", 1, 5, "Adulto,TAM G,Azul", controllerItem);
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testAtualizaItemNecessarioIdItemNegativo() {
		try {
			controllerUsuario.atualizaItemNecessario("84473712044", -1, 5, "Adulto,TAM G,Azul", controllerItem);
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testAtualizaItemNecessarioQuantidadeNegativa() {
		try {
			controllerUsuario.atualizaItemNecessario("84473712044", 1, -5, "Adulto,TAM G,Azul", controllerItem);
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testAtualizaItemNecessarioReceptorNaoCadastrado() {
		try {
			controllerUsuario.atualizaItemNecessario("123123123", 1, 5, "Adulto,TAM G,Azul", controllerItem);
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void AtualizaItemNecessarioQuantidade() {
		assertEquals("3 - toalha de banho, tags: [Adulto, TAM G, Azul], quantidade: 5", controllerUsuario.atualizaItemNecessario("59238650111", 3, 5, "", controllerItem));
	}
	
	@Test
	public void testRemoveItemNecessarioIdReceptorNulo() {
		try {
			controllerUsuario.removeItemNecessario(null, 1, controllerItem);
		} catch(IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testRemoveItemNecessarioIdReceptorVazio() {
		try {
			controllerUsuario.removeItemNecessario("", 1, controllerItem);
		} catch(IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testRemoveItemNecessarioIdItemNegativo() {
		try {
			controllerUsuario.removeItemNecessario("84473712044", -1, controllerItem);
		} catch(IllegalArgumentException iae) {
		}
	}
	
	@Test
	public void testRemoveItemNecessarioIdReceptorNaoCadastrado() {
		try {
			controllerUsuario.removeItemNecessario("123123123", 1, controllerItem);
		} catch(IllegalArgumentException iae) {
		}
	}
	
}
