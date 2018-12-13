package doe;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;
import java.io.Serializable;

import Validador.Validador;


/**
 * Representacao de um usuario no sistema.
 * Cada usuario podera ser ou doador ou receptor de itens.
 * Todo usuario devera ter um Id, Nome, Email e uma classe a seres informados, alem do status.
 * 
 * @author Joao Vitor de Melo Cavalcante e Souza.
 * @author Ezequias de Oliveira Rocha.
 * @author Felipe Jeronimo Bernardo da Silva.
 *
 */
public class Usuario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7872987635271353383L;
	
	/**
	 * Id do usuario.
	 */
	private String id;
	/**
	 * Nome do usuario.
	 */
	private String nome;
	/**
	 * Email do usuario.
	 */
	private String email;
	/**
	 * Celular do usuario.
	 */
	private String celular;
	/**
	 * Classe do usuario(ex: pessoa fisica, ONG).
	 */
	private String classe;
	/**
	 * Status do usuario(doador ou receptor).
	 */
	private String status;
	/**
	 * Map contendo os itens a serem doados ou com interesse de receber, dependendo
	 * do status do usuario.
	 */
	private Map<Integer, Item> itens;
	
	/**
	 * Validador de usuario.
	 */
	private Validador validador = new Validador();
	
	
	/**
	 * Construtor do usuario.
	 * 
	 * @param id Id do usuario.
	 * @param nome Nome do usuario.
	 * @param email Email do usuario.
	 * @param celular Celular do usuario.
	 * @param classe Classe do usuario (so podera ser PESSOA_FISICA, IGREJA, ONG, ORGAO PUBLICO MUNICIPAL, ORGAO PUBLICO ESTADUAL, ORGAO PUBLICO FEDERAL, ASSOCIACAO, SOCIEDADE.
	 * @param status Status do usuário (so podera ser doador ou receptor).
	 */
	public Usuario(String id, String nome, String email, String celular, String classe, String status) {
		this.validador.validaCadastro(id, nome, email, celular, classe);
		
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		this.classe = classe;
		this.status = status;
		this.itens = new HashMap<>();
	}
	
	/**
	 * @return Retorna o Id do usuario.
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @return Retorna o Nome do usuario.
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Altera o nome do usuario.
	 * 
	 * @param nome novo Nome a ser alterado.
	 */
	public void setNome(String nome) {
		this.validador.validaNome(nome);
		this.nome = nome;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	/**
	 * @return Retorna o Email do usuario.
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Altera o email do usuario
	 * @param email novo email a ser alterado.
	 */
	public void setEmail(String email) {
		this.validador.validaEmail(email);
		this.email = email;
	}
	
	/**
	 * @return Retorna o numero do celular do usuario.
	 */
	public String getCelular() {
		return celular;
	}
	
	/**
	 * Altera o usuario do celular do usuario.
	 * @param celular Novo celular a ser alterado.
	 */
	public void setCelular(String celular) {
		this.validador.validaCelular(celular);
		this.celular = celular;
	}

	/**
	 * @return Retorna o tipo de "classe" do usuario.
	 */
	public String getClasse() {
		return classe;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	/**
	 * Atualiza um item a ser doado, podendo alterar seu Id, sua quantidade e suas tags.
	 * 
	 * @param idItem novo Id a ser alterado.
	 * @param quantidade nova Quantidade a ser alterada.
	 * @param tags novas Tags a serem alteradas.
	 * @return Retorna a representação atual do item alterado.
	 * 
	 */
	public String atualizaItem(int idItem, int quantidade, String tags) {
		if (!(tags == null) && !(tags.equals(""))) {
			this.itens.get(idItem).setTags(tags);
		}
		if ((quantidade > 0)) {
			this.itens.get(idItem).setQuantidade(quantidade);
		}

		return itens.get(idItem).toString();
	}
	
	/**
	 * Procura um item e o altera quando um novo item igual for ser adicionado.
	 * 
	 * @param item Item a ser alterado.
	 * @param quantidade Nova quantidade a ser alterada.
	 * @param tags Novas tasg a serem alteradas.
	 * 
	 */
	private int procuraItem(Item item, int quantidade, String tags) {
		for (Item itemSistema : itens.values()) {
			if (itemSistema.equals(item)) {
				itemSistema.setQuantidade(quantidade);
				itemSistema.setTags(tags);
				return itemSistema.getId();
			}
		}
		return 0;
	}
	
	/**
	 * @param idItem id do item a ser retornado.
	 * @return Retorna a representação textual de um item.
	 */
	public String getItem(int idItem) {
		this.validador.validaIdItem(idItem);
		
		if (!(this.verificaItem(idItem))) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		}
		return itens.get(idItem).toString();
	}
	
	/**
	 * Remove um item do usuario.
	 * @param idItem Id do item a ser removido.
	 */
	public void removeItem(int idItem) {
		this.validador.validaIdItem(idItem);
		
		if (this.itens.isEmpty()) {
			throw new IllegalArgumentException("O Usuario nao possui itens cadastrados.");
		}
		if (!(this.verificaItem(idItem))) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		}
		this.itens.remove(idItem);
	}
	
	/**
	 * @param id Id do item a ser retornado.
	 * @return Retorna o objeto completo do item desejado.
	 */
	public Item getItemOb(int id) {
		return itens.get(id);
	}
	
	/**
	 * Verifica se um item existe no usuario.
	 * 
	 * @param idItem Id do item a ser verificado.
	 * @return true caso exista e false caso nao exista.
	 */
	public boolean verificaItem(int idItem) {
		this.validador.validaIdItem(idItem);
		
		if (!(this.itens.containsKey(idItem))) {
			return false;
		}

		return true;
	}

	/**
	 * Adiciona um novo item no usuario.
	 * O metodo adiciona no Map (Integer => Item) o novo item, sendo identificado pelo seu id.
	 * 
	 * @param id Id do novo item.
	 * @param descricao Descricao do novo item.
	 * @param quantidade Quantidade do novo item.
	 * @param tags Tags do novo item.
	 */
	public int adicionaItem(int id, String descricao, int quantidade, String tags) {
		this.validador.validaIdItem(id);
		this.validador.validaDescritor(descricao);
		this.validador.validaQuantidade(quantidade);

		Item item = new Item(id, descricao, quantidade, tags, this.nome, this.id);

		if (itens.containsValue(item)) {
			return this.procuraItem(item, quantidade, tags);
		} else {
			this.itens.put(id, item);
			return id;
		}
	}

	/**
	 * @return A representacao em String do usuario, no formato: (nome)/(id), (email), (celular), status: (status).
	 */
	@Override
	public String toString() {
		return String.format("%s/%s, %s, %s, status: %s", this.nome, this.id, this.email, this.celular, this.status);
	}
	
	/**
	 * Atualiza um item necessario (em que ha interesse).
	 * 
	 * @param idItem Novo id a ser alterado.
	 * @param novaQuantidade Nova quantidade a ser alterada.
	 * @param novasTags Novas tags a serem alteradas.
	 * @return Retorna a atual representação em string do objeto.
	 */
	public String atualizaItemNecessario(int idItem, int novaQuantidade, String novasTags) {
		this.validador.validaIdItem(idItem);
		
		if (!this.itens.containsKey(idItem)) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		}
		
		if (!(novasTags == null) && !(novasTags.equals(""))) {
			this.itens.get(idItem).setTags(novasTags);
		}
		if ((novaQuantidade > 0)) {
			this.itens.get(idItem).setQuantidade(novaQuantidade);
		}

		return itens.get(idItem).toString();

	}
	
	/**
	 * Remove um item necessario (existente) a partir de seu id.
	 * 
	 * @param idItem Id do item a ser removido.
	 */
	public void removeItemNecessario(int idItem) {
		this.validador.validaIdItem(idItem);
		
		if (this.itens.isEmpty()) {
			throw new IllegalArgumentException("O Usuario nao possui itens cadastrados.");
		}
		if (!this.itens.containsKey(idItem)){
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		}
		this.itens.remove(idItem);
		
	}

	public Map<Integer, Item> getItens() {
		return itens;
	}

	public ArrayList<Item> verificaMatch(Item itemNecessario) {
		ArrayList<Item> itensMatch = new ArrayList<>();
		
		for (Integer i : itens.keySet()) {
			if (itens.get(i).getDescricaoItem().equals(itemNecessario.getDescricaoItem())) {
				itensMatch.add(this.itens.get(i));
			}
		}
		return itensMatch;
	}

}
