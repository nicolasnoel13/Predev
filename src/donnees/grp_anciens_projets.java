package donnees;

import java.util.ArrayList;

public class grp_anciens_projets {

	protected ArrayList<projet> liste_projets; // Les anciens projets
	
	public grp_anciens_projets(ArrayList<projet> liste_projets) {
		super();
		this.liste_projets = liste_projets;
	}
	
	public grp_anciens_projets() {
		ArrayList<projet> liste_projets = new ArrayList<projet>();
	}
}

