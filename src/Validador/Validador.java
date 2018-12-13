package Validador;

import doe.Item;

/**
 * Classe auxiliar em que valida parametros de metodos de outras classes e objetos.
 * 
 * @author Joao Vitor de Melo Cavalcante e Souza.
 * @author Ezequias de Oliveira Rocha.
 * @author Felipe Jeronimo Bernardo da Silva.
 *
 */
public class Validador {
	
	/**
	 * Valida os parametros de um usuario a ser cadastrado.
	 * Um Usuario nao pode ter nenhum de seus parametros null ou espacos vazios (com espacos tambem).
	 * 
	 * @param id Do usuario a ser cadastrado.
	 * @param nome Nome do usuario a ser cadastrado.
	 * @param email Email do usuario a ser cadastrado.
	 * @param celular Numero do usuario a ser cadastrado.
	 * @param classe Classe do usuario a ser cadastrado.
	 */
	public void validaCadastro(String id, String nome, String email, String celular, String classe) {
		if (id == null || id.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		if (nome == null || nome.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: nome nao pode ser vazio ou nulo.");
		}
		if (email == null || email.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: email nao pode ser vazio ou nulo.");
		}
		if (celular == null || celular.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: celular nao pode ser vazio ou nulo.");
		}
		if (classe == null || classe.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: classe nao pode ser vazia ou nula.");
		}
	}
	
	/**
	 * Valida a classe de um usuario a ser cadastrado.
	 * Um usuario so pode ter como classe: PESSOA_FISICA, IGREJA, ONG, ORGAO PUBLICO MUNICIPAL, ORGAO PUBLICO ESTADUAL, ORGAO PUBLICO FEDERAL,
	 * ASSOCIACAO e SOCIEDADE.
	 * 
	 * @param classe Classe a ser validada.
	 */
	public void validaClasse(String classe) {
		if (!classe.toUpperCase().equals("PESSOA_FISICA") && !classe.toUpperCase().equals("IGREJA") && !classe.toUpperCase().equals("ONG") 
				&& !classe.toUpperCase().equals("ORGAO PUBLICO MUNICIPAL") && !classe.toUpperCase().equals("ORGAO PUBLICO ESTADUAL") 
				&& !classe.toUpperCase().equals("ORGAO PUBLICO FEDERAL") && !classe.toUpperCase().equals("ASSOCIACAO") && !classe.toUpperCase().equals("SOCIEDADE")) {
			throw new IllegalArgumentException("Entrada invalida: opcao de classe invalida.");
		}
	}
	
	/**
	 * Valida o parametro id de um usuario separadamente.
	 * 
	 * @param id Id do usuario a ser validado, o id nao pode ser null nem vazio.
	 */
	public void validaId(String id) {
		if (id == null || id.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
	}
	
	/**
	 * Valida o parametro nome de um usuario separadamente.
	 * 
	 * @param nome Nome do usuario a ser validado, o nome nao pode ser null nem vazio.
	 */
	public void validaNome(String nome) {
		if (nome == null || nome.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: nome nao pode ser vazio ou nulo.");
		}
	}
	
	/**
	 * Valida o parametro email de um usuario separadamente.
	 * 
	 * @param email Email do usuario a ser validado, o email nao pode ser null nem vazio.
	 */
	public void validaEmail(String email) {
		if (email == null || email.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: email nao pode ser vazio ou nulo.");
		}
	}
	
	/**
	 * Valida o parametro numero celular de um usuario separadamente.
	 * 
	 * @param celular Numero celular de um usuario a ser validado, o celular nao pode ser null nem vazio.
	 */
	public void validaCelular(String celular) {
		if (celular == null || celular.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: celular nao pode ser vazio ou nulo.");
		}
	}
	
	/**
	 * Valida o parametro quantidade de um item separadamente.
	 * 
	 * @param quantidade Quantidade de um item separadamente, a quantidade nao pode ser negativa.
	 */
	public void validaQuantidade(int quantidade) {
		if (quantidade <= 0) {
			throw new IllegalArgumentException("Entrada invalida: quantidade deve ser maior que zero.");
		}
	}
	
	/**
	 * Valida uma descricao a ser cadastrada, de um descritor ou item(doacao ou recepcao).
	 * 
	 * @param descricao Descricao a ser validada, nao podendo ser null nem vazia.
	 */
	public void validaDescritor(String descricao) {
		if (descricao == null || descricao.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: descricao nao pode ser vazia ou nula.");
		}
	}
	
	/**
	 * Valida os parametros do cadastro de um item.
	 * 
	 * @param id Id do usuario a ser validado, nao podendo ser null nem vazio.
	 * @param descricao Descricao do item a ser validada, nao podedo ser null nem vazio.
	 * @param quantidade Quantidade do item a ser validada, nao podendo ser negativa.
	 */
	public void verificaCadastroDeItem(String id, String descricao, int quantidade) {
		if (id == null || id.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		if (descricao == null || descricao.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: descricao nao pode ser vazia ou nula.");
		}
		if (quantidade <= 0) {
			throw new IllegalArgumentException("Entrada invalida: quantidade deve ser maior que zero.");
		}
	}
	
	/**
	 * Valida o Id de um item.
	 * 
	 * @param idItem Id do item, nao podendo ser nem vazio nem negativo.
	 */
	public void validaIdItem(int idItem) {
		if(idItem < 0) {
			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		}
		
		else if(idItem == 0) {
			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser vazio ou nulo.");
		}
	}
	
	/**
	 * Valida os parametros de uma pequisa sobre itens com a mesma descricao(descritores).
	 * 
	 * @param descricao Descricao a ser validada, nao podendo ser null nem vazia.
	 */
	public void validaPesquisa(String descricao) {
		if (descricao == null || descricao.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: texto da pesquisa nao pode ser vazio ou nulo.");
		}
	}

	public void validaData(String data) {
		if (data == null || data.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: data nao pode ser vazia ou nula.");
		}
	}

	public void validaItem(Item itemNecessario, Item itemDoacao, int idItemNec, int idItemDoado) {
		if (itemNecessario == null) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItemNec +".");
		}
		if (itemDoacao == null) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItemDoado +".");
		}
	}

	public void validaDoacao(Item itemDoacao, Item itemNecessario) {
		if (!itemDoacao.getDescricaoItem().equals(itemNecessario.getDescricaoItem())) {
			throw new IllegalArgumentException("Os itens nao tem descricoes iguais.");
		}
	}

}
