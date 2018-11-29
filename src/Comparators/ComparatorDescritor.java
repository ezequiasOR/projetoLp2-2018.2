package Comparators;

import java.util.Comparator;

import doe.Descritor;

public class ComparatorDescritor implements Comparator<Descritor>{
	

	public int compare(Descritor d1, Descritor d2) {
		
		String d1Nome = d1.getDescritor();
		String d2Nome = d2.getDescritor();
		
		return d1Nome.compareTo(d2Nome);
	}

}
