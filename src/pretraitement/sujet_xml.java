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
	protected int annee; //Pour l'ann�e scol 2012-2013 on notera 2012
	protected ArrayList<String> liste_fichiers_attendus;
	protected ArrayList<String> parties_a_omettre; // Le texte du sujet, ne pas consid�rer lors de la d�tection de plagiat.
	
	public sujet_xml() {
		this.parties_a_omettre= new ArrayList<String>();
		this.liste_fichiers_attendus= new ArrayList<String>();
	}

	
	
	public void demande_infos() { // A terme une interface graphique pour remplacer cette m�thode !
		//Demande les infos � l'utilisateur et les stocke dans les attributs
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Veuillez saisir le numero/identifiant de sujet : ");
		String str = sc.nextLine();
		System.out.println("Numero = " + str);
		this.numero=Integer.parseInt(str);
		
		System.out.println("Veuillez saisir la matiere : ");
		str = sc.nextLine();
		System.out.println("Matiere = " + str);
		this.matiere=str;
		
		System.out.println("Veuillez saisir l'ann�e scolaire de premiere parution du sujet: ");
		str = sc.nextLine();
		System.out.println("Ann�e = " + str);
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
		//G�n�re le sujet.xml � partir des infos stockees dans les attributs
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
	    
	    for(int i = 0; i < this.liste_fichiers_attendus.size(); i++)
	    {
	    	Element fichier = new Element("fichier_"+(i+1));
		    fichier.setText(this.liste_fichiers_attendus.get(i));
		    racine.addContent(fichier);
	    }
	    
	    for(int i = 0; i < this.parties_a_omettre.size(); i++)
	    {
	    	Element partie = new Element("partie_"+(i+1));
		    partie.setText(this.parties_a_omettre.get(i));
		    racine.addContent(partie);
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
			//Remarquez qu'il suffit simplement de cr�er une instance de FileOutputStream
			//avec en argument le nom du fichier pour effectuer la s�rialisation.
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
	         //On cr�e un nouveau document JDOM avec en argument le fichier XML
	         //Le parsing est termin�
	         document = sxb.build(new File("user/sujet_"+num_sujet+".xml"));
	         //On initialise un nouvel �l�ment racine avec l'�l�ment racine du document.
		     sujet = document.getRootElement();
		     
		     this.numero=num_sujet;
		     System.out.println("\nLECTURE DU XML");
		     System.out.println("annee : "+sujet.getChild("annee").getText());
		     this.annee=Integer.parseInt(sujet.getChild("annee").getText());
		     System.out.println("matiere : "+sujet.getChild("matiere").getText());
		     this.matiere=sujet.getChild("matiere").getText();
		     System.out.println("type : "+sujet.getChild("type").getText());
		     this.type=sujet.getChild("type").getText();
		   
		     //Gestion des fichiers
 
		     int j=0;
		     boolean suivant_existe=true;
		     while(suivant_existe)
		     {
		    	try {
		    	j++;
		    	String temp =sujet.getChild("fichier_"+j).getText();
		        this.liste_fichiers_attendus.add(temp);
		        System.out.println("ajout du fichier suivant � partir du xml : "+temp);
		    	}
		    	catch (Exception e) {
		    		suivant_existe=false;
		    	}
		     }
		        
		    //Gestion des parties
		     j=0;
		     suivant_existe=true;
		     while(suivant_existe)
		     {
		    	try {
		    	j++;
		    	String temp =sujet.getChild("partie_"+j).getText();
		        this.parties_a_omettre.add(temp);
		        System.out.println("ajout du fichier suivant � partir du xml : "+temp);
		    	}
		    	catch (Exception e) {
		    		suivant_existe=false;
		    	}
		     }       

	      }
	      catch(Exception e){
	    	  System.out.println(e.toString());
	      }

	      
	}
	
}
