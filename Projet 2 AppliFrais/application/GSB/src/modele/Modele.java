package modele;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;
import java.security.*;
import java.text.SimpleDateFormat;

import javax.swing.JTable;

import obj.Etat;
import obj.FicheVa;
import obj.FraisForfait;
import obj.FraisHorsForfait;
import obj.MontantFraisForfait;
import obj.Suivi;
import obj.Visiteur;
import view.V_Login;


public class Modele {
	
	//Attributs privés
	private static Connection connexion;
	private static PreparedStatement st;
	private static ResultSet rs;
	private static String req = "";
	private static MessageDigest md;
	
	//Methodes statiques
	
	
	/**
	 * Methode de connexion à la BDD
	 * @return
	 */
	public static boolean connexionBDD(){
		boolean rep = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connexion = DriverManager.getConnection("jdbc:mysql://172.16.203.100/2018daigneau","rdaigneau","123456");
			//connexion = DriverManager.getConnection("jdbc:mysql://localhost/gsb","root","");
			rep = true;
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			System.out.println("Driver non chargé ! " + e);
		}
		catch (SQLException e) 
		{	
			e.printStackTrace();
			System.out.println("Erreur de connexion à la base de donnée : " + e);
		}
		return rep;
	}
	
	//Methode de déconnexion de la BDD
	public static boolean deconnexionBDD(){
		//Fermeture de la connexion
		boolean rep = false;
		try {
			connexion.close();
			rep = true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erreur de déconnexion à la base de donnée : " +e);
		}
		return rep;
	}
	
	
	/**
	 * Methode pour la connexion d'un utilisateur(comptable)
	 * @param login
	 * @param pwd
	 * @return
	 */
	public static boolean connexionAdmin(String login, String pwd){
		connexionBDD();
		int count = 0;
	        
		try{
			byte[] bytesOfMessage = pwd.getBytes("UTF-8");
			
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(bytesOfMessage); 
			// Le hash est présent sous forme de tableau de byte

			BigInteger bigInt = new BigInteger(1,thedigest);
			String hashtext = bigInt.toString(16);
			while(hashtext.length() < 32 ){
			  hashtext = "0"+hashtext;
			}
			// Le hash est présent sous la forme de String
			
			st = connexion.prepareStatement("SELECT COUNT(login) FROM visiteur WHERE login= ? AND mdp = ?");
			
			st.setString(1, login);
			st.setString(2, hashtext);
			
			rs = st.executeQuery();
			
			while(rs.next()){
				count = rs.getInt(1);
			}
			
			rs.close();
			st.close();

		deconnexionBDD();
			
		} catch (SQLException | UnsupportedEncodingException | NoSuchAlgorithmException e){
			return false;
		}
		
		if(count == 1){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Methode qui retourne la liste des visiteurs
	 * @return
	 */
	public static  ArrayList<Visiteur> getLesVisiteurs() {
		connexionBDD();
		/*Collection les visiteurs*/
		ArrayList<Visiteur>lesVisiteurs = new ArrayList<Visiteur>();
		
		try {
			PreparedStatement st = connexion.prepareStatement("SELECT id, nom, prenom FROM visiteur WHERE role=0 ORDER BY id");
			ResultSet rs = st.executeQuery(); 
			while(rs.next()){
				String id = rs.getString("id");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				lesVisiteurs.add(new Visiteur(id, nom, prenom,null));	
			}
		} 
		catch (SQLException e) {
			System.out.println(e);
		}
		finally{
			
			   try{
				   /*fermeture de la connexion*/
				   connexion.close();
			   }
			   catch(Exception e){
				   e.printStackTrace();
			   }
			 }
		
		deconnexionBDD();
		
		return lesVisiteurs ;
	}
	
	/**
	 * Methode qui retourne les fiches cloturé, à valider
	 * @return
	 */
	public static  ArrayList<Visiteur> getVisiteursFicheCL() {
		connexionBDD();

		String moisEnCour = new SimpleDateFormat("yMM", Locale.FRANCE).format(new java.util.Date());
		/*Collection les visiteurs*/
		ArrayList<Visiteur>lesVisiteurs = new ArrayList<Visiteur>();
		
		try {
			PreparedStatement st = connexion.prepareStatement("SELECT distinct id, nom, prenom,mois FROM visiteur,fichefrais WHERE visiteur.id = fichefrais.idVisiteur AND role=0 AND idEtat='CL'AND mois= '" + moisEnCour + "' ORDER BY id");
			ResultSet rs = st.executeQuery(); 
			while(rs.next()){
				String id = rs.getString("id");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String mois = rs.getString("mois");
				lesVisiteurs.add(new Visiteur(id, nom, prenom,mois));	
			}
		} 
		catch (SQLException e) {
			System.out.println(e);
		}
		finally{
			
			   try{
				   /*fermeture de la connexion*/
				   connexion.close();
			   }
			   catch(Exception e){
				   e.printStackTrace();
			   }
			 }
		
		deconnexionBDD();
		
		return lesVisiteurs ;
	}
	
	/**
	 * Methode qui retourne les fiches valider, à mettre en paiement
	 * @return
	 */
	public static  ArrayList<Visiteur> getVisiteursFicheVA(){
		connexionBDD();

		/*Collection les visiteurs*/
		ArrayList<Visiteur>lesVisiteurs = new ArrayList<Visiteur>();
		
		try {
			PreparedStatement st = connexion.prepareStatement("SELECT distinct id, nom, prenom,mois FROM visiteur,fichefrais WHERE visiteur.id = fichefrais.idVisiteur AND role=0 AND idEtat='VA' ORDER BY id");
			ResultSet rs = st.executeQuery(); 
			while(rs.next()){
				String id = rs.getString("id");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String mois = rs.getString("mois");
				lesVisiteurs.add(new Visiteur(id, nom, prenom,mois));	
			}
		} 
		catch (SQLException e) {
			System.out.println(e);
		}
		finally{
			
			   try{
				   /*fermeture de la connexion*/
				   connexion.close();
			   }
			   catch(Exception e){
				   e.printStackTrace();
			   }
			 }
		
		deconnexionBDD();
		
		return lesVisiteurs ;
	}
	
	/**
	 * Methode qui retourne l'id du visiteur dont le nom est entré en paramètre
	 * @param nom
	 * @return
	 */
	public static  ArrayList<Visiteur> getIdVisiteur(String nom) {
		connexionBDD();

		/*Collection les visiteurs*/
		ArrayList<Visiteur>lesVisiteurs = new ArrayList<Visiteur>();
		
		try {
			PreparedStatement st = connexion.prepareStatement("SELECT id FROM visiteur WHERE nom='"+nom+"'");
			ResultSet rs = st.executeQuery(); 
			while(rs.next()){
				String id = rs.getString("id");
				lesVisiteurs.add(new Visiteur(id, null, null,null));	
			}
		} 
		catch (SQLException e) {
			System.out.println(e);
		}
		finally{
			
			   try{
				   /*fermeture de la connexion*/
				   connexion.close();
			   }
			   catch(Exception e){
				   e.printStackTrace();
			   }
			 }
		
		deconnexionBDD();
		
		return lesVisiteurs ;
	}		
		
		

	/**
	 *  Methode qui retourne l'etat actuel de la fiche, pour le visiteur et le mois entré en paramètre
	 * @param idVisiteur
	 * @param mois
	 * @return
	 */
	public static  ArrayList<Etat> getEtatActuel(String idVisiteur,String mois) {
		
		connexionBDD();
		
		ArrayList<Etat> lesEtats = new ArrayList<Etat>();
		
		try {
			PreparedStatement st = connexion.prepareStatement("SELECT libelle FROM etat, fichefrais WHERE etat.id=fichefrais.idEtat AND idVisiteur ='"+idVisiteur+"' AND mois ='"+mois+"'");
			ResultSet rs = st.executeQuery(); 	
			while(rs.next()){
			
			String libelle = rs.getString("libelle");
			lesEtats.add(new Etat(null,libelle));
			
			}
		} 
		catch (SQLException e) {
			System.out.println(e);
		}
		finally{
			   try{
				   /*fermeture de la connexion*/
				   connexion.close();
			   }
			   catch(Exception e){
				   e.printStackTrace();
			   }
			 }
		
		deconnexionBDD();
		
		return lesEtats ;
	}
	
	public static ArrayList<MontantFraisForfait> getLesMontantFraisForfait(){
		
		connexionBDD();
		
		ArrayList<MontantFraisForfait> montantFraisForfait = new ArrayList<MontantFraisForfait>();
		
		try {
			PreparedStatement st = connexion.prepareStatement("SELECT libelle,montant FROM fraisforfait ORDER BY fraisforfait.id");
			ResultSet	rs = st.executeQuery(); 	
			
			while(rs.next()){
				
				String lib = rs.getString("libelle");
				float montant = rs.getInt("montant");
				montantFraisForfait.add(new MontantFraisForfait(lib, montant));
			}
			
		} 
		catch (SQLException e) {
			System.out.println(e);
		}
		finally{
			   try{
				   /*fermeture de la connexion*/
				   connexion.close();
			   }
			   catch(Exception e){
				   e.printStackTrace();
			   }
			 }
		
		deconnexionBDD();
		
		return montantFraisForfait;
	}
	
	/**
	 * Methode qui retourne les frais forfais d'une fiche de frais pour un visiteur et un mois entré en paramètre
	 * @param idVisiteur
	 * @param mois
	 * @return
	 */
	public static  ArrayList<FraisForfait> getLesFraisForfait(String idVisiteur, String mois) {
		
		connexionBDD();
		
		ArrayList<FraisForfait> fraisForfait = new ArrayList<FraisForfait>();
		
		try {
			PreparedStatement st = connexion.prepareStatement("SELECT libelle,quantite FROM lignefraisforfait,fraisforfait WHERE idVisiteur ='"+idVisiteur+"' AND mois ='"+mois+"' AND fraisforfait.id = lignefraisforfait.idFraisForfait ORDER BY idFraisForfait");
			ResultSet	rs = st.executeQuery(); 	
			
			while(rs.next()){
				
				String lib = rs.getString("libelle");
				int qte = rs.getInt("quantite");
				fraisForfait.add(new FraisForfait(lib, qte));
			}
			
		} 
		catch (SQLException e) {
			System.out.println(e);
		}
		finally{
			   try{
				   /*fermeture de la connexion*/
				   connexion.close();
			   }
			   catch(Exception e){
				   e.printStackTrace();
			   }
			 }
		
		deconnexionBDD();
		
		return fraisForfait ;
	}
	
	
	/**
	 * Methode qui retourne les frais hors-forfais d'une fiche de frais pour un visiteur et un mois entré en paramètre
	 * @param idVisiteur
	 * @param mois
	 * @return
	 */
	public static  ArrayList<FraisHorsForfait> getLesFraisHorsForfait(String idVisiteur, String mois) {
		
		connexionBDD();
		
		ArrayList<FraisHorsForfait> fraisHorsForfait = new ArrayList<FraisHorsForfait>();
		
		try {
			PreparedStatement st = connexion.prepareStatement("SELECT libelle,date,montant FROM lignefraishorsforfait  WHERE lignefraishorsforfait.idvisiteur ='"+idVisiteur+"'  AND lignefraishorsforfait.mois = '"+mois+"'");
			ResultSet rs = st.executeQuery(); 	
			
			while(rs.next()){
				
				String lib = rs.getString("libelle");
				Date date = rs.getDate("date");
				float montant = rs.getFloat("montant");
				
				fraisHorsForfait.add(new FraisHorsForfait(lib,date, montant));
			}
			
		} 
		catch (SQLException e) {
			System.out.println(e);
		}
		finally{
			   try{
				   /*fermeture de la connexion*/
				   connexion.close();
			   }
			   catch(Exception e){
				   e.printStackTrace();
			   }
			 }
		
		deconnexionBDD();
		
		return fraisHorsForfait ;
	}
	
	
	/**
	 * Methode pour récupérer toutes les fiches VA pour tous les mois
	 * @return
	 */
	public static  ArrayList<FicheVa> getFicheValidees(){
		
		connexionBDD();

		
		ArrayList<FicheVa>lesInfos = new ArrayList<FicheVa>();
		
		try {
			
			PreparedStatement st = connexion.prepareStatement("SELECT idVisiteur,nom,prenom,mois,dateModif,montantValide,idEtat FROM etat, visiteur, fichefrais WHERE visiteur.id=fichefrais.idVisiteur AND fichefrais.idEtat=etat.id AND etat.id='VA'");
			ResultSet rs = st.executeQuery(); 
			
			while(rs.next()){
				
				String id = rs.getString("idVisiteur");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				Date  date = rs.getDate("dateModif");
				float montantValide = rs.getFloat("montantValide");
				String idEtat = rs.getString("idEtat");
				String mois = rs.getString("mois");
				
				lesInfos.add(new FicheVa(id,nom, prenom,mois, date, idEtat, montantValide));	
			}
		} 
		catch (SQLException e) {
			System.out.println(e);
		}
		finally{
			   try{
				   /*fermeture de la connexion*/
				   connexion.close();
			   }
			   catch(Exception e){
				   e.printStackTrace();
			   }
			   
			 }
		
		deconnexionBDD();
		
		return lesInfos;
		
	}

	
	/* ------------------------ Suivi ------------------------- */
	
	/**
	 * Methode qui retourne l'id , le nom , le prenom,le mois,le montant validé et l'idEtat des visiteurs ayant une fiche "VA"
	 * @return
	 */
	public static  ArrayList<Suivi> getSuivi() {
		
		connexionBDD();

		/*Collection les visiteurs*/
		ArrayList<Suivi>lesSuivis = new ArrayList<Suivi>();
		
		try {
			PreparedStatement st = connexion.prepareStatement("SELECT mois,idVisiteur,nom,prenom,montantValide,idEtat"
					+ " FROM fichefrais , visiteur  WHERE visiteur.id = fichefrais.idVisiteur ");
			ResultSet rs = st.executeQuery(); 
			while(rs.next()){
				String date = rs.getString("mois");
				String id = rs.getString("idVisiteur");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				int montant = rs.getInt("montantValide");
				String etat = rs.getString("idEtat");
				String mois = rs.getString("mois");
				
				lesSuivis.add(new Suivi(id, nom, prenom,mois,date,montant,etat));	
			}
		} 
		catch (SQLException e) {
			System.out.println(e);
		}
		finally{
			
			   try{
				   /*fermeture de la connexion*/
				   connexion.close();
			   }
			   catch(Exception e){
				   e.printStackTrace();
			   }
			 }
		
		deconnexionBDD();
		
		return lesSuivis ;
	}
	
	/* ------------------- Validation de la fiche de frais ------------------------ */
	
	/**
	 * Methode qui met à jour la fiche de frais à l"etat "VA"
	 * @param mois
	 * @param idVis
	 * @param montant
	 * @param nbJustificatifs
	 * @return nbLignes
	 */
	public static  int validerFicheFrais(String mois,String idVis,float montant,int nbJustificatifs) {	
		
		connexionBDD();
		
		int nbLignes = 0;
		try {
			PreparedStatement st = connexion.prepareStatement("UPDATE fichefrais SET idEtat='VA', montantValide='"+montant+"' , nbJustificatifs='"+nbJustificatifs+"' WHERE mois='"+mois+"' AND idVisiteur='"+idVis+"'");
				
			nbLignes = st.executeUpdate();
		} 
		catch (SQLException e) {
			System.out.println(e);
		}
		finally{
			   try{
				   /*fermeture de la connexion*/
				   connexion.close();
			   }
			   catch(Exception e){
				   e.printStackTrace();
			   }
			 }
		
		deconnexionBDD();
		
		if(nbLignes != 0){
			return nbLignes;
		}
		else{
			return 0;
		}
	}
	
	/**
	 * Methode qui met à jour la fiche de frais à l"etat "RB"
	 * @param mois
	 * @param idVisiteur
	 * @return nbLignes
	 */
	public static  int rembourserFiche(String mois,String idVisiteur) {
		
		connexionBDD();
	
		int nbLignes = 0;
		try {
			PreparedStatement st = connexion.prepareStatement("UPDATE fichefrais SET idEtat='RB' WHERE mois='"+mois+"' AND idVisiteur='"+idVisiteur+"'");
				
			nbLignes = st.executeUpdate();
		} 
		catch (SQLException e) {
			System.out.println(e);
		}
		finally{
			   try{
				   /*fermeture de la connexion*/
				   connexion.close();
			   }
			   catch(Exception e){
				   e.printStackTrace();
			   }
			 }
		
		deconnexionBDD();
		
		return nbLignes;
	}
}
