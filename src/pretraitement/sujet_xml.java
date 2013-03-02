package pretraitement;

import java.util.ArrayList;
import java.util.Scanner;
import org.jdom.*;
import org.jdom.output.*;
import java.io.*;
import org.jdom.input.*;

import java.util.List;
import java.util.Iterator;


public class sujet_xml {


	protected int numero;
	protected String matiere;
	protected String type;
	protected int annee; //Pour l'année scol 2012-2013 on notera 2012
	protected ArrayList<String> liste_fichiers_attendus;
	protected ArrayList<String> parties_a_omettre; // Le texte du sujet, ne pas considérer lors de la détection de plagiat.
	
	public sujet_xml() {
		this.parties_a_omettre= new ArrayList<String>();
		this.liste_fichiers_attendus= new ArrayList<String>();
	}

	
	
	public void demande_infos() { // A terme une interface graphique pour remplacer cette méthode !
		//Demande les infos à l'utilisateur et les stocke dans les attributs
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Veuillez saisir le numero/identifiant de sujet : ");
		String str = sc.nextLine();
		System.out.println("Numero = " + str);
		this.numero=Integer.parseInt(str);
		
		System.out.println("Veuillez saisir la matiere : ");
		str = sc.nextLine();
		System.out.println("Matiere = " + str);
		this.matiere=str;
		
		System.out.println("Veuillez saisir l'année scolaire de premiere parution du sujet: ");
		str = sc.nextLine();
		System.out.println("Année = " + str);
		this.annee=Integer.parseInt(str);
		
		System.out.println("Veuillez saisir le type de rendu : ");
		str = sc.nextLine();
		System.out.println("Type de rendu = " + str);
		this.type=str;
		
		System.out.println("Veuillez saisir un nom de fichier attendu ou n pour passer a la suite: ");
		str = sc.nextLine();
		while (str.equals("n")==false) {
			this.liste_fichiers_attendus.add(str);
			System.out.println("Veuillez saisir un nom de fichier attendu ou n pour passer a la suite: ");
			str = sc.nextLine();
		}
		
		System.out.println("Veuillez saisir du texte a omettre ou n pour passer a la suite: ");
		str = sc.nextLine();
		while (str.equals("n")==false) {
			this.parties_a_omettre.add(str);
			System.out.println("Veuillez saisir du texte a omettre ou n pour passer a la suite: ");
			str = sc.nextLine();
		}

		sc.close();
	}
	
	public void creation_xml() {
		//Génère le sujet.xml à partir des infos stockees dans les attributs
	    Element racine = new Element("sujet");
		org.jdom.Document document = new Document(racine);
	    Attribute id = new Attribute("id",String.valueOf(this.numero));
	    racine.setAttribute(id);
	    
	    Element annee = new Element("annee");
	    annee.setText(String.valueOf(this.annee));
	    racine.addContent(annee);
	    
	    Element matiere = new Element("matiere");
	    matiere.setText(this.matiere);
	    racine.addContent(matiere);
	    
	    Element type = new Element("type");
	    type.setText(this.type);
	    racine.addContent(type);
	    
	    Element liste_fichiers = new Element("liste_fichiers");
	    racine.addContent(liste_fichiers);
	    
	    for(int i = 0; i < this.liste_fichiers_attendus.size(); i++)
	    {
	    	Element fichier = new Element("fichier");
		    fichier.setText(this.liste_fichiers_attendus.get(i));
		    liste_fichiers.addContent(fichier);
	    }
	    
	    Element parties = new Element("parties");
	    racine.addContent(parties);
	    
	    for(int i = 0; i < this.parties_a_omettre.size(); i++)
	    {
	    	Element partie = new Element("partie");
		    partie.setText(this.parties_a_omettre.get(i));
		    parties.addContent(partie);
	    }
	    
		try
		{
	       //On utilise ici un affichage classique avec getPrettyFormat()
	       XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
	       sortie.output(document, System.out);
		}
		catch (java.io.IOException e){
			System.out.println(e.toString());
		}

		try
		{
			//On utilise ici un affichage classique avec getPrettyFormat()
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			//Remarquez qu'il suffit simplement de créer une instance de FileOutputStream
			//avec en argument le nom du fichier pour effectuer la sérialisation.
			sortie.output(document, new FileOutputStream("user/sujet_"+this.numero+".xml"));
		}
	    catch (java.io.IOException e){
	    	System.out.println(e.toString());
	    }
	}
	
	public void lecture_xml(int num_sujet) {
		// On lit le sujet.xml deja existant ... et on remplit les attributs
		
		SAXBuilder sxb = new SAXBuilder();
		org.jdom.Document document;
		Element sujet;
	      try
	      {
	         //On crée un nouveau document JDOM avec en argument le fichier XML
	         //Le parsing est terminé
	         document = sxb.build(new File("user/sujet_"+num_sujet+".xml"));
	         //On initialise un nouvel élément racine avec l'élément racine du document.
		     sujet = document.getRootElement();
		     
		     this.numero=num_sujet;
		     System.out.println("\nLECTURE DU XML");
		     System.out.println("annee : "+sujet.getChild("annee").getText());
		     this.annee=Integer.parseInt(sujet.getChild("annee").getText());
		     System.out.println("matiere : "+sujet.getChild("matiere").getText());
		     this.matiere=sujet.getChild("matiere").getText();
		     System.out.println("type : "+sujet.getChild("type").getText());
		     this.type=sujet.getChild("type").getText();
		   
//EN DEVELOPPEMENT
		     
		     // NOTE PERSO : sujet.getChild("fichiers").getChildren("fichier")
		     
		     /*
		     //Gestion des fichiers
		     System.out.println("fichier : "+sujet.getChild("fichiers").getChild("fichier").getText());
		     System.out.println("fichier : "+sujet.getChild("fichier").getText());
		     System.out.println("fichier : "+sujet.getChild("fichier").getText());
			ArrayList<Element> listTemp = new ArrayList<Element>(sujet.getChildren("fichier"));
		    

		     System.out.println("avant test");
		     Element pourtest = listTemp.get(0);
		     System.out.println("pendant test");
		     System.out.println(pourtest.getText());
		     System.out.println("apres test");

		     
		     Iterator<?> i = listTemp.iterator();
		     while(i.hasNext())
		     {
		    	System.out.println("on est dans la boucle !");
		        Element courant = (Element)i.next();
		        this.liste_fichiers_attendus.add(courant.getText());
		        System.out.println("ajout du fichier suivant à partir du xml : "+courant.getText());
		     }
		     
		     //Gestion des parties
		     
		     List<?> listTemp2 = sujet.getChildren("partie");
		     Iterator<?> j = listTemp2.iterator();
		     while(j.hasNext())
		     {
		        Element courant = (Element)j.next();
		        this.parties_a_omettre.add(courant.getText());
		        System.out.println("ajout de la partie à omettre suivante à partir du xml : "+courant.getText());
		     }
	      	      */
//FIN DE EN DEVELOPPEMENT
	      }
	      catch(Exception e){
	    	  System.out.println(e.toString());
	      }

	      
	}
	
}
