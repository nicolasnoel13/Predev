package donnees;

public class epsilon {
	
	protected int type; // 1 SVN, 2 local, 3 internet
	protected String adresse;
	protected float pourcentage;
	protected String commentaires;
	
	public epsilon(int type, String adresse, float pourcentage,
			String commentaires) {
		super();
		this.type = type;
		this.adresse = adresse;
		this.pourcentage = pourcentage;
		this.commentaires = commentaires;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public float getPourcentage() {
		return pourcentage;
	}
	public void setPourcentage(float pourcentage) {
		this.pourcentage = pourcentage;
	}
	public String getCommentaires() {
		return commentaires;
	}
	public void setCommentaires(String commentaires) {
		this.commentaires = commentaires;
	}

}
