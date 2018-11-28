package doe;

import java.util.Comparator;

public class ComparatorItemNome implements Comparator<Item>{
	

	public int compare(Item i1, Item i2) {
		
		String i1Nome = i1.getDescricaoItem();
		String i2Nome = i2.getDescricaoItem();
		
		return i1Nome.compareTo(i2Nome);
	}

}
