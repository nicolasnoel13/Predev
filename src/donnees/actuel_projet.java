package donnees;

import java.util.ArrayList;

public class actuel_projet {

	protected String chemin;
	protected ArrayList<element_plagiat> liste_elem_plagiat;

	public actuel_projet(String chemin) {
		this.chemin = chemin;
		this.liste_elem_plagiat=new ArrayList<element_plagiat>();
	}	

	public void ajouter_elem_plagiat(element_plagiat p) {
		this.liste_elem_plagiat.add(p);
	}
}
