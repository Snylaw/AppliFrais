package obj;

public class Visiteur {
	
	/*
	 * Attributs privés
	 */
	private String id;
	private String nom;
	private String prenom;
	private String mois;
	
	/*
	 * Constructeur
	 */
	public Visiteur(String unId, String unNom, String unPrenom, String unMois){
		this.id = unId;
		this.nom = unNom;
		this.prenom = unPrenom;
		this.mois = unMois;
	}

	public String getId() {
		return id;
	}
	
	public String getNom() {
		return nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public String getMois() {
		return mois;
	}
		
}