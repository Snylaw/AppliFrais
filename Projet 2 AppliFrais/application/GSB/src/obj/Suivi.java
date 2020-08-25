package obj;

//class suivi
public class Suivi extends Visiteur {
	
	private String date ;
	private String etat;
	private float montant ;
	
	public Suivi (String id , String nom , String prenom ,String mois, String date ,  float montant , String etat ) {
		super(id,nom,prenom,mois);
		this.date = date ;
		this.montant = montant ;
		this.etat = etat ;
	}
	
	public String getDate() {
		return date ;
	}
	 
	public float getMontantValide(){
		return montant ;
	}
	
	public String getEtat(){
		return etat ;
	}

}