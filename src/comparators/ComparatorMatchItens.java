package comparators;

import java.util.Comparator;

import doe.Item;


public class ComparatorMatchItens implements Comparator<Item> {
	
	/**
	 * Compara a quantidade de dois itens.
	 */
	public int compare(Item i1, Item i2) {
		
		int i1Pontos = i1.getPontos();
		int i2Pontos = i2.getPontos();
		
		if(i1Pontos > i2Pontos) {
			return -1;
		}
		
		else if(i1Pontos < i2Pontos) {
			return 1;
		}
		
		else {
			int i1Id = i1.getId();
			int i2Id = i2.getId();
			
			if(i1Id > i2Id) {
				return 1;
			}
			else {
				return -1;
			}
			
		}
	}
}