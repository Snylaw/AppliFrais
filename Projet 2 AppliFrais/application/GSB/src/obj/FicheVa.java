package obj;

import java.util.Date;

public class FicheVa extends Visiteur {
	
	private Date date ;
	private String etat;
	private float montant ;
	
	public FicheVa(String id, String nom, String prenom,String mois, Date date, String etat, float montant) {
		
		super(id, nom, prenom,mois);
		
		this.date = date;
		this.etat = etat;
		this.montant = montant;
		
	}

	/**
	 * @return la date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @return l'etat
	 */
	public String getEtat() {
		return etat;
	}

	/**
	 * @return le montant
	 */
	public float getMontant() {
		return montant;
	}
	
}