package comparators;

import java.util.Comparator;

import doe.Descritor;

/**
 * Classe responsavel por comparar dois descritores pelo nome.
 * 
 * @author João Vitor de Melo Cavalcante e Souza
 * @author Ezequias de Oliveira Rocha
 * @author Felipe Jerônimo Bernardo da Silva
 */
public class ComparatorDescritor implements Comparator<Descritor>{
	
	/**
	 * 
	 * Compara o nome de dois descritores.
	 */
	public int compare(Descritor d1, Descritor d2) {
		
		String d1Nome = d1.getDescritor();
		String d2Nome = d2.getDescritor();
		
		return d1Nome.compareTo(d2Nome);
	}

}
