package donnees;

import java.io.File;
import java.util.ArrayList;

public class ancien_projet {

	protected File xml; // Le fichier xml de desccription du projet
	protected ArrayList<File> liste_docs; // Les documents qui constituent le projet
	
	public ancien_projet() {
		// TODO : Déclaration des fichiers a faire
		ArrayList<File> liste_docs = new ArrayList<File>();
		ArrayList<epsilon> liste_epsilons = new ArrayList<epsilon>();
	}
	
	public ancien_projet(File xml, ArrayList<File> liste_docs) {
		super();
		this.xml = xml;
		this.liste_docs = liste_docs;
	}
}
