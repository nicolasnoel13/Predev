import java.util.ArrayList;

public class programme_principal {

	
	public static void main(String[] args) {

		System.out.println("Main : D�but du programme");
		
		//Initialisation des parametres grace � params.txt
		pretraitement.parametres param = new pretraitement.parametres();
		
		//Cr�ation des actuel_projets et stockage dans liste_actuel_projets � partir des chemins de params.txt
		ArrayList<donnees.actuel_projet> liste_actuel_projets = new ArrayList<donnees.actuel_projet>();
		for(int i = 0; i < param.liste_projets.size(); i++)
		{
			donnees.actuel_projet projet = new donnees.actuel_projet (param.liste_projets.get(i));
			liste_actuel_projets.add(projet);
		}   
		
		//Creation du sujet
		pretraitement.sujet_xml sujet = new pretraitement.sujet_xml();
		
		//Si sujet.xml n'est pas � utiliser : demande d'infos, remplissage des attributs, g�n�ration du sujet.xml
		if (param.isUtiliser_xml()==false) {
			sujet.demande_infos();
			sujet.creation_xml();
		}
		else {
		//Par contre si on veut utiliser un sujet.xml d�j� pr�sent : remplissage des attributs � partir de ce sujet
		sujet.lecture_xml(param.getNum_sujet());
		}
		
		// Initialisation des modeles de comportement
		donnees.comportement le_comportement = new donnees.comportement();
		
		//Import des anciens projets � partir de SVN
		
		
		//Cr�ations des ancien_projets et stockage dans une liste
		
		
		//Lancement du traitement.superviseur.run()
		
		
		//Lancement du rapport.rapport.run()
		
	
		System.out.println("Main : Fin du programme");
	}
}