package comparators;

import java.util.Comparator;

import doe.Item;

/**
 * Classe para comparar dois items pelo nome.
 * 
 * @author João Vitor de Melo Cavalcante e Souza
 * @author Ezequias de Oliveira Rocha
 * @author Felipe Jerônimo Bernardo da Silva
 *
 */
public class ComparatorItemNome implements Comparator<Item> {

	/**
	 * Compara o nome de dois itens.
	 */
	public int compare(Item i1, Item i2) {

		String i1Nome = i1.getDescricaoItem();
		String i2Nome = i2.getDescricaoItem();

		return i1Nome.compareTo(i2Nome);
	}

}
