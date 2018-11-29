package Comparators;

import java.util.Comparator;

import doe.Item;

public class ComparatorItemId implements Comparator<Item>{
	

	public int compare(Item i1, Item i2) {
		
		int i1Id = i1.getId();
		int i2Id = i2.getId();
		
		if(i1Id > i2Id) {
			return 1;
		}
		
		return -1;
		
		
		
	}

}
