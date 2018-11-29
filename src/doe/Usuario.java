package doe;

import java.util.HashMap;
import java.util.Map;


/**
 * Representação de um usuário no sistema.
 * Cada usuário poderá ser ou doador ou receptor de itens.
 * Todo usuário deverá ter um Id, Nome, Email e uma classe a seres informados, além do status.
 * 
 * @author João Vitor de Melo Cavalcante e Souza
 * @author Ezequias de Oliveira Rocha
 * @author Felipe Jerônimo Bernardo da Silva
 *
 */
public class Usuario {
	
	/**
	 * Id do usuário.
	 */
	private String id;
	/**
	 * Nome do usuário.
	 */
	private String nome;
	/**
	 * Email do usuário.
	 */
	private String email;
	/**
	 * Celular do usuário.
	 */
	private String celular;
	/**
	 * Classe do usuário(ex: pessoa física, ONG).
	 */
	private String classe;
	/**
	 * Status do usuário(doador ou receptor).
	 */
	private String status;
	/**
	 * Map contendo os itens a serem doados ou com interesse de receber, dependendo
	 * do status do usuário.
	 */
	private Map<Integer, Item> itens;
	
	
	/**
	 * Construtor do usuário.
	 * 
	 * @param id Id do usuário.
	 * @param nome Nome do usuário.
	 * @param email Email do usuário.
	 * @param celular Celular do usuário.
	 * @param classe Classe do usuário (só poderá ser PESSOA_FISICA, IGREJA, ONG, ORGAO PUBLICO MUNICIPAL, ORGAO PUBLICO ESTADUAL, ORGAO PUBLICO FEDERAL, ASSOCIACAO, SOCIEDADE.
	 * @param status Status do usuário (sí poderá ser doador ou receptor).
	 */
	public Usuario(String id, String nome, String email, String celular, String classe, String status) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		this.classe = classe;
		this.status = status;
		this.itens = new HashMap<>();
	}
	
	/**
	 * @return Retorna o Id do usuário.
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @return Retorna o Nome do usuário.
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Altera o nome do usuário.
	 * 
	 * @param nome novo Nome a ser alterado.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * @return Retorna o Email do usuário.
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Altera o email do usuário
	 * @param email novo email a ser alterado.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return Retorna o número do celular do usuário.
	 */
	public String getCelular() {
		return celular;
	}
	
	/**
	 * Altera o número do celular do usuário.
	 * @param celular Novo celular a ser alterado.
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}

	/**
	 * @return Retorna o tipo de "classe" do usuário.
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
	private void procuraItem(Item item, int quantidade, String tags) {
		for (Item itemSistema : itens.values()) {
			if (itemSistema.equals(item)) {
				itemSistema.setQuantidade(quantidade);
				itemSistema.setTags(tags);
			}
		}
	}
	
	/**
	 * @param idItem id do item a ser retornado.
	 * @return Retorna a representação textual de um item.
	 */
	public String getItem(int idItem) {
		if (!(this.verificaItem(idItem))) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		}
		return itens.get(idItem).toString();
	}
	
	/**
	 * Remove um item do usuário.
	 * @param idItem Id do item a ser removido.
	 */
	public void removeItem(int idItem) {
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
	 * Verifica se um item existe no usuário.
	 * 
	 * @param idItem Id do item a ser verificado.
	 * @return true caso exista e false caso não exista.
	 */
	public boolean verificaItem(int idItem) {
		if (!(this.itens.containsKey(idItem))) {
			return false;
		}

		return true;
	}

	/**
	 * Adiciona um novo item no usuário.
	 * O método adiciona no Map (Integer => Item) o novo item, sendo identificado pelo seu id.
	 * 
	 * @param id Id do novo item.
	 * @param descricao Descricao do novo item.
	 * @param quantidade Quantidade do novo item.
	 * @param tags Tags do novo item.
	 */
	public void adicionaItem(int id, String descricao, int quantidade, String tags) {

		Item item = new Item(id, descricao, quantidade, tags, this.nome, this.id);

		if (itens.containsValue(item)) {
			this.procuraItem(item, quantidade, tags);
		} else {
			this.itens.put(id, item);
		}
	}

	/**
	 * @return A representação em String do usuário, no formato: (nome)/(id), (email), (celular), status: (status).
	 */
	@Override
	public String toString() {
		return String.format("%s/%s, %s, %s, status: %s", this.nome, this.id, this.email, this.celular, this.status);
	}
	
	/**
	 * Atualiza um item necessario (em que há interesse).
	 * 
	 * @param idItem Novo id a ser alterado.
	 * @param novaQuantidade Nova quantidade a ser alterada.
	 * @param novasTags Novas tags a serem alteradas.
	 * @return Retorna a atual representação em string do objeto.
	 */
	public String atualizaItemNecessario(int idItem, int novaQuantidade, String novasTags) {
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
	 * Remove um item necessário a partir de seu id.
	 * 
	 * @param idItem
	 */
	public void removeItemNecessario(int idItem) {
		this.itens.remove(idItem);
	}
	
	public boolean vazio() {
		if(itens.keySet().size() == 0) {
			return true;
		}
		return false;
	}
}
