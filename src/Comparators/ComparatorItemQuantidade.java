package Comparators;

import java.util.Comparator;

import doe.Item;

public class ComparatorItemQuantidade implements Comparator<Item> {
	
	public int compare(Item i1, Item i2) {
		
		int i1Quant = i1.getQuantidade();
		int i2Quant = i2.getQuantidade();
		
		if(i1Quant > i2Quant) {
			return -1;
		}
		else if(i1Quant < i2Quant) {
			return 1;
		}
		else {
			String descI1 = i1.getDescricaoItem();
			String descI2 = i2.getDescricaoItem();
			
			return descI1.compareTo(descI2);
		}
				
	}

}
