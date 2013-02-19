package donnees;

import java.io.File;
import java.util.ArrayList;

public class grp_projets {
	
	protected ArrayList<projet> liste_projets; // Les projets considérés à cette éxécution
	
	public grp_projets(ArrayList<projet> liste_projets) {
		super();
		this.liste_projets = liste_projets;
	}
	
	public grp_projets() {
		ArrayList<projet> liste_projets = new ArrayList<projet>();
	}


	
}
