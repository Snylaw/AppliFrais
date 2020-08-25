package obj;

public class MontantFraisForfait {

	
	private String libelle;
	private double montant;
	
	public MontantFraisForfait(String libelle, double montant) {
		this.libelle = libelle;
		this.montant = montant;
	}

	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * @return the qte
	 */
	public double getMontant() {
		return montant;
	}
	
}