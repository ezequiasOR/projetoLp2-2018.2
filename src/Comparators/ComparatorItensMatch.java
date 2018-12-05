package Comparators;

import java.util.Comparator;
import doe.Item;

public class ComparatorItensMatch implements Comparator<Item> {
	
	
	public int compare(Item i1, Item i2) {
		
		int i1Pont = i1.getPontos();
		int i2Pont = i2.getPontos();
		
		if(i1Pont > i2Pont) {
			return 1;
		}
		else if(i1Pont < i2Pont) {
			return -1;
		}
		else {
			int id1 = i1.getId();
			int id2 = i2.getId();
			
			if(id1 > id2) {
				return 1;
			}
			
			else {
				return -1;
			}
		}
				
	}

}