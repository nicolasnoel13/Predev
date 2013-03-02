package donnees;

import java.util.Hashtable;

public class comportement {

	protected Hashtable<String,modele_comportement> ht;
	
	public comportement() {
		this.ht = new Hashtable<String,modele_comportement>();
		modele_comportement algpr_tp_c = new donnees.modele_comportement("algpr_tp_c","algpr","C","plaggie");
		modele_comportement melog_tp_java = new donnees.modele_comportement("melog_tp_java","melog","Java","plaggie");
		this.ht.put("algpr_tp_c",algpr_tp_c);
		this.ht.put("melog_tp_java",melog_tp_java);
	}

	public void ajouter(modele_comportement model,String s) {
		this.ht.put(s, model);
	}
	
}
