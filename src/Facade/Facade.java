package Facade;

import java.io.IOException;

import Controllers.ControllerItem;
import Controllers.ControllerUsuario;
import easyaccept.EasyAccept;


/**
 * Representacao da fachada com os metodos publicos.
 *  
 * @author Joao Vitor de Melo Cavalcante e Souza
 * @author Ezequias de Oliveira Rocha
 * @author Felipe Jeronimo Bernardo da Silva
 *
 */
public class Facade {
	
	/**
	 * Controlador dos Itens.
	 */
	private ControllerItem ctlItem;
	/**
	 * Controlador dos usuarios.
	 */
	private ControllerUsuario ctlUsuarios;

	/**
	 * Main com easyaccept
	 * @param args testes do easyaccept.
	 */
	public static void main(String[] args) {
		args = new String[] { "Facade.Facade", "testes/acceptance_tests/use_case_1.txt", "testes/acceptance_tests/use_case_2.txt", "testes/acceptance_tests/use_case_3.txt", "testes/acceptance_tests/use_case_4.txt"};
		EasyAccept.main(args);
	}
	
	/**
	 * Construtor da fachada.
	 */
	public Facade() {
		ctlItem = new ControllerItem();
		ctlUsuarios = new ControllerUsuario(ctlItem);
	}
	
	/**
	 * Le os receptores a partir de um csv.
	 * 
	 * @param caminho Conteudo do arquivo csv.
	 * 
	 * @throws IOException lanca excecao.
	 */
	public void lerReceptores(String caminho) throws IOException {
		ctlUsuarios.lerReceptores(caminho);
	}
	
	/**
	 * Adiciona um doador no sistema
	 * 
	 * @param id id do usuario.
	 * @param nome Nome do usuario.
	 * @param email Email do usuario.
	 * @param celular Celular do usuario.
	 * @param classe Classe do usuario (podendo ser Fisica ou Igreja por exemplo).
	 * 
	 * @return O Id do doador.
	 */
	public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
		return ctlUsuarios.cadastraDoador(id, nome, email, celular, classe);
	}
	
	/**
	 * Pesquisa um usuario no sistema pelo id.
	 * 
	 * @param id Id do usuario.
 	 * @return Retorna a representacao textual do usu�rio.
	 */
	public String pesquisaUsuarioPorId(String id) {
		return ctlUsuarios.pesquisaUsuarioPorId(id);
	}
	
	/**
	 * Pesquisa um usuario no sistema pelo nome.
	 * 
	 * @param nome Nome do usuario.
 	 * @return Retorna a representacao textual do usuario.
	 */
	public String pesquisaUsuarioPorNome(String nome) {
		return ctlUsuarios.pesquisaUsuarioPorNome(nome);
	}
	
	/**
	 * Atualiza um usuario do sistema, caso necessario e por partes.
	 * 
	 * @param id Id do usuario a ser alterado.
	 * @param nome Novo nome desejado.
	 * @param email Novo email desejado.
	 * @param celular Novo celular desejado.
	 * @return Retorna a nova representacao textual do usuario.
	 */
	public String atualizaUsuario(String id, String nome, String email, String celular) {
		return ctlUsuarios.atualizaUsuario(id, nome, email, celular);
	}
	
	/**
	 * Remove um usuario do sistema pelo id.
	 * @param id Id do usuario.
	 */
	public void removeUsuario(String id) {
		ctlUsuarios.removeUsuario(id);
	}
	
	
	/**
	 * Adiciona um descritor no sistema.
	 * 
	 * @param descricao Nome do descritor.
	 */
	public void adicionaDescritor(String descricao) {
		ctlItem.adicionaDescritor(descricao);
	}
	
	/**
	 * Adiciona um item para doacao no sistema.
	 * 
	 * @param idDoador Id do doador que ira doar.
	 * @param descricao Descricao do item a ser doado.
	 * @param quantidade Quantidade do item a ser doado.
	 * @param tags Tags do item a ser doado.
	 * @return Retorna o id do item.
	 */
	public int adicionaItemParaDoacao(String idDoador, String descricao, int quantidade, String tags) {
		return ctlUsuarios.adicionaItem(idDoador, descricao, quantidade, tags);
	}
	
	/**
	 * Exibe um item de um doador.
	 * 
	 * @param idItem Id do item.
	 * @param idDoador Id do doador.
	 * @return Retorna a representacao textual do item.
	 */
	public String exibeItem(int idItem, String idDoador) {
		return ctlUsuarios.exibeItem(idItem, idDoador);
	}
	
	/**
	 * Atualiza um item do sistema, caso necessario e por partes.
	 * @param idItem Id do item a ser atualizado.
	 * @param idDoador Id do doador possuidor do item.
	 * @param quantidade Nova quantidade desejada.
	 * @param tags Novas tags desejadas.
	 * @return Retorna a nova representacao textual do item.
	 */
	public String atualizaItemParaDoacao(int idItem, String idDoador, int quantidade, String tags) {
		return ctlUsuarios.atualizaItemParaDoacao(idItem, idDoador, quantidade, tags);
	}
	
	/**
	 * Remove um item de um doador.
	 * @param idItem Id do item a ser removido.
	 * @param idDoador Id do doador que possui o item.
	 */
	public void removeItemParaDoacao(String idItem, String idDoador) {
		ctlUsuarios.removeItemParaDoacao(idItem, idDoador);
	}
	
	/**
	 * @return Retorna representacao textual de  todos os descritores cadastrados no sistema por ordem de quantidade,
	 */
	public String listaDescritorDeItensParaDoacao() {
		return ctlItem.listarDescritores();
	}
	
	/**
	 * @return Retorna representacao textual de  todos os itens cadastrados no sistema por ordem de quantidade. Caso haja quantidades iguais, o sistema ordenara alfabeticamente pelo descritor.
	 */
	public String listaItensParaDoacao() {
		return ctlItem.listarItensNoSistema();
	}
	
	/**
	 * Pesquisa itens que atendem a descricao, cadastrados no sistema.
	 * @param descricao Descricao desejada
	 * @return Retorna representacao textual de todos os itens cadastrados que atendem a descricao.
	 */
	public String pesquisaItemParaDoacaoPorDescricao(String descricao) {
		return ctlItem.listaItemPorDescricao(descricao);
	}
	
	/**
	 * Adiciona um novo item necessario/em que ha interesse de se receber.
	 * @param idReceptor Id do receptor.
	 * @param descricaoItem Descricao do item desejado.
	 * @param quantidade Quantidade desejada.
	 * @param tags Tags do item desejado.
	 * @return Retorna representacao textual do pedido do item.
	 */
	public int adicionaItemNecessario(String idReceptor, String descricaoItem, int quantidade, String tags) {
		return ctlUsuarios.adicionaItemNecessario(idReceptor, descricaoItem, quantidade, tags);
	}
	
	/**
	 * @return Retorna representacao textual de todos os pedidos de itens necessarios.
	 */
	public String listaItensNecessarios() {
		return ctlItem.listaItensNecessarios();
	}
	
	/**
	 * Atualiza um pedido do sistema, caso necessario e por partes.
	 * @param idReceptor Id do receptor.
	 * @param idItem id do pedido.
	 * @param novaQuantidade Nova quantidade desejada.
	 * @param novasTags Novas tags desejadas.
	 * @return Retorna a nova representacao textual do pedido.
	 */
	public String atualizaItemNecessario(String idReceptor, int idItem, int novaQuantidade, String novasTags) {
		return ctlUsuarios.atualizaItemNecessario(idReceptor, idItem, novaQuantidade, novasTags);
	}
	
	/**
	 * Remove um item necessario/pedido.
	 * @param idReceptor Id do receptor.
	 * @param idItem Id do pedido.
	 */
	public void removeItemNecessario(String idReceptor, int idItem) {
		ctlUsuarios.removeItemNecessario(idReceptor, idItem);
	}
	
	public String match(String idReceptor, int idItemNecessario) {
		return ctlItem.match(idReceptor, idItemNecessario);
	}
	
}

