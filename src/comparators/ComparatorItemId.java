package comparators;

import java.util.Comparator;

import doe.Item;

/**
 * Classe para comparar dois items pelo id.
 * 
 * @author João Vitor de Melo Cavalcante e Souza
 * @author Ezequias de Oliveira Rocha
 * @author Felipe Jerônimo Bernardo da Silva
 */
public class ComparatorItemId implements Comparator<Item> {

	/**
	 * 
	 * Compara o id de dois itens.
	 */
	public int compare(Item i1, Item i2) {

		int i1Id = i1.getId();
		int i2Id = i2.getId();

		if (i1Id > i2Id) {
			return 1;
		}

		return -1;

	}

}
