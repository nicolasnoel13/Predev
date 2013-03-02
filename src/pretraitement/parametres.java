package pretraitement;

import java.util.ArrayList;
import java.io.*;

public class parametres {

	private boolean utiliser_xml; //Si true on utilise le sujet.xml déjà présent sinon on en créera un autre
	private int num_sujet; // Dans le cas où le sujet existant est demandé
	public ArrayList<String> liste_projets; // La liste des dossiers/projets
	
	
	public parametres() { //Initialiser grace au fichier params.txt les attributs
		
		this.liste_projets = new ArrayList<String>();
		
		String fichier ="user/params.txt";
		
		//lecture du fichier texte	
		try{
			InputStream ips=new FileInputStream(fichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			
			//Lecture de la premiere ligne : présence du xml ou non
			ligne=br.readLine();
			if (ligne.equals("n")) {
				this.setUtiliser_xml(false);
				System.out.println("Parametres : L'utilisateur ne souhaite pas utiliser de sujet déjà présent, un nouveau sera créé");
			}
			else {
				try { 
					this.setUtiliser_xml(true);
					this.setNum_sujet(Integer.parseInt(ligne));
					System.out.println("Parametres : L'utilisateur souhaite utiliser le sujet déjà présent numero : "+ligne);
				} 
				catch (Exception e) { 
					System.out.println("Parametres : premiere ligne de params.txt incoherente : "+ligne);
				}
			}
			
			
			//Lecture des autres lignes (noms de dossier)
			while ((ligne=br.readLine())!=null){
				File f = new File ("user/"+ligne);
				if (f.exists() && f.isDirectory()) {
						this.liste_projets.add(ligne);
						System.out.println("Parametres : Ajout du dossier/projet suivant : "+ligne);			
				}
				else {
					System.out.println("Parametres : ERREUR : Le dossier suivant n'existe pas : "+ligne);
				}
				
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		
	}
	
	public boolean isUtiliser_xml() {
		return utiliser_xml;
	}

	public void setUtiliser_xml(boolean utiliser_xml) {
		this.utiliser_xml = utiliser_xml;
	}

	public int getNum_sujet() {
		return num_sujet;
	}

	public void setNum_sujet(int num_sujet) {
		this.num_sujet = num_sujet;
	}
	
}
