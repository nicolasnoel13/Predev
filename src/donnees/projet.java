package donnees;

import java.io.File;
import java.util.ArrayList;

public class projet {
	
	protected File xml; // Le fichier xml de desccription du projet
	protected ArrayList<File> liste_docs; // Les documents qui constituent le projet
	protected ArrayList<epsilon> liste_epsilons; // La liste des epsilons (éléments de plagiat) détectés
	
	public projet() {
		// TODO : Déclaration des fichiers a faire
		ArrayList<File> liste_docs = new ArrayList<File>();
		ArrayList<epsilon> liste_epsilons = new ArrayList<epsilon>();
	}
		
	public projet(File xml, ArrayList<File> liste_docs,ArrayList<epsilon> liste_epsilons) {
		super();
		this.xml = xml;
		this.liste_docs = liste_docs;
		this.liste_epsilons = liste_epsilons;
	}

}
