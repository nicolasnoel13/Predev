package donnees;

public class modele_comportement {

	protected String nom;
	protected String matiere;
	protected String type;
	protected String module_a_utiliser;

	public modele_comportement(String nom,String matiere,String type,String module_a_utiliser) {
		this.nom = nom;
		this.matiere = matiere;
		this.type = type;
		this.module_a_utiliser = module_a_utiliser;
		//Quels types d'ancens projets on va chercher (meme année, meme matiere, meme eleve)
		//Est ce qu'on va cherche sur internet
		//Quel genre de rapport on va rendre
		//Comment se comporter pa rapport au elements_plagiat
		//La tolerance par rapport au plagiat, le seuil de déclenchement
		
		System.out.println("modele_comportement : initialisation du modele de comportement : "+this.nom);
	}
}
