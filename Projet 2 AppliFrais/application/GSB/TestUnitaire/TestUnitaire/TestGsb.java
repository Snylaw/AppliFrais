package TestUnitaire;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modele.Modele;
import obj.Visiteur;

public class TestGsb {
	Modele modele;
	String id;

	
	@Before
	public void setUp() throws Exception {
		
	}

	
	@Test
	public void testConnexion() {
		
		String login = "rdaigneau";
		String mdp ="azerty";		
		boolean result = true;
		
		assertEquals("Erreur de connexion",result,Modele.connexionAdmin(login, mdp));
	}

	
	@Test
	public void testGetIdVisiteur() {
		
		for(int i=0; i<Modele.getIdVisiteur("Daigneau").size(); i++){
			
			Visiteur visiteur = Modele.getIdVisiteur("Daigneau").get(i);
			id = visiteur.getId();
		}
		//On verifie si l'id a21 est correct
		assertEquals("L'Id du visiteur est invalide","g4",id);
	}

	
	@Test
	public void testValiderFicheFrais() {
		//fail("Not yet implemented");
	}

}























































































