package comparators;

import java.util.Comparator;

import doe.Item;

/**
 * Classe para comparar dois items pela quantidade.
 * 
 * @author João Vitor de Melo Cavalcante e Souza
 * @author Ezequias de Oliveira Rocha
 * @author Felipe Jerônimo Bernardo da Silva
 *
 */
public class ComparatorItemQuantidade implements Comparator<Item> {

	/**
	 * Compara a quantidade de dois itens.
	 */
	public int compare(Item i1, Item i2) {

		int i1Quant = i1.getQuantidade();
		int i2Quant = i2.getQuantidade();

		if (i1Quant > i2Quant) {
			return -1;
		} else if (i1Quant < i2Quant) {
			return 1;
		} else {
			String descI1 = i1.getDescricaoItem();
			String descI2 = i2.getDescricaoItem();

			return descI1.compareTo(descI2);
		}

	}

}
