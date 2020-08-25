package obj;

public class Etat {
	String id;
	String libelle;
	
	public Etat(String id,String unLibelle) {
		
		this.id = id;
		this.libelle = unLibelle;
		
	}

	public String getId() {
		return id;
	}

	public String getlibelle() {
		return libelle;
	}	
	
}