package comparators;

import java.util.Comparator;

import doe.Item;

public class ComparatorItensDoados implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {

		String[] data1 = o1.split("/");
		String[] data2 = o2.split("/");

		int ano1 = Integer.parseInt(data1[2]);
		int ano2 = Integer.parseInt(data2[2]);

		int mes1 = Integer.parseInt(data1[1]);
		int mes2 = Integer.parseInt(data1[1]);

		int dia1 = Integer.parseInt(data1[0]);
		int dia2 = Integer.parseInt(data1[0]);

		if (ano1 < ano2) {
			return 1;
		} else if (ano2 < ano1) {
			return -1;
		} else {
			if (mes1 < mes2) {
				return 1;
			} else if (mes2 < mes1) {
				return -1;
			} else {
				if (dia1 < dia2) {
					return 1;
				}
				else if (dia2 < dia1) {
					return -1;
				}
			}
			return 0;
		}

	}

}
