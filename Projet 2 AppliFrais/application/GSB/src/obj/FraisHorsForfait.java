package obj;

import java.util.Date;

public class FraisHorsForfait {

	
	private String libelle;
	private Date date;
	private float montant;
	
	public FraisHorsForfait(String libelle, Date date, float montant) {
		this.libelle = libelle;
		this.date = date;
		this.montant = montant;
	}

	
	public String getLibelle() {
		return libelle;
	}

	
	public Date getDate() {
		return date;
	}

	
	public float getMontant() {
		return montant;
	}

}