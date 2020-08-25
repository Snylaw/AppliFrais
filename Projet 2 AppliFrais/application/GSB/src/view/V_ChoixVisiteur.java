package view;
import java.awt.Color;

import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import obj.Visiteur;

/**
 * Affiche les visiteurs ayant une fiche dont l'id de l'etat est <b>"CR"</b> 
 * c'est à dire <b>"fiche créée, saisie en cours"</b> et le mois dont la fiche
 * a été crée dans une liste déroulante
 */

public class V_ChoixVisiteur extends JPanel{
	
	//Attributs privés	
		//Labels
	private JLabel lblChoixVisiteur;
	private JLabel lblChoixMois;
	
	private JLabel espace;
	
	private JLabel lblDate;
	private JLabel msg;// s'il n y a aucune fiche 
		//JComboBox
	private JComboBox choixVisiteur;
	private JComboBox choixMois;
		//JPanel
	private JPanel panelForm;
		//Color
	private Color bgColor;
		//JButton
	private static JButton btnValider;
		//String
	private String date;


	
	/**
	 * Constructeur V_ficheFrais
	 */
	public V_ChoixVisiteur(ArrayList<Visiteur> infosFicheCL){
		
		/*
		 * COULEUR ARRIERE-PLAN DU PANEL
		 */
		this.bgColor = Color.decode("#ff9933");
		this.setBackground(bgColor);
		
		/*
		 * Panel pour le formulaire
		 */
		this.panelForm = new JPanel();
		this.panelForm.setPreferredSize(new Dimension(250,140));
		this.panelForm.setBackground(bgColor);
		
		/*
		 * On récupère la date du jours
		 */
		this.date = new SimpleDateFormat("dd-MM-yyyy", Locale.FRANCE).format(new Date());
		
		
		this.lblDate = new JLabel("Date du jour : "+date,JLabel.CENTER);
		this.lblDate.setPreferredSize(new Dimension(700,50));
		
		/*
		 * Label Visiteur
		 */
		this.lblChoixVisiteur = new JLabel("Visiteur :");
		this.lblChoixVisiteur.setPreferredSize(new Dimension(70,10));
		
		
		/*
		 * Liste deroulante Visiteur
		 */
		this.choixVisiteur = new JComboBox();
		
		for(int i=0; i<infosFicheCL.size();i++){
			
			Visiteur visiteur = infosFicheCL.get(i);
			this.choixVisiteur.addItem(visiteur.getNom());
			
		}
		
		this.choixVisiteur.setPreferredSize(new Dimension(150,20));
		
		/*
		 * Label mois
		 */
		this.lblChoixMois = new JLabel("Mois :");
		this.lblChoixMois.setPreferredSize(new Dimension(70,10));
		
		/*
		 * Liste deroulante mois
		 */
		this.choixMois = new JComboBox();
		this.choixMois.setPreferredSize(new Dimension(150,20));
		
		/*
		 * Affiche le mois de la fiche du visiteur selectioné dans une liste déroulante
		 */
		for(int i=0; i<infosFicheCL.size();i++){
			
			Visiteur visiteur = infosFicheCL.get(i);
			this.choixMois.addItem(visiteur.getMois());
		
		}
		
		/*
		 * Espace entre les labels est les bouttons
		 */
		this.espace = new JLabel();
		this.espace.setPreferredSize(new Dimension(220,15));
		
		/*
		 * Boutton valider
		 */
		this.btnValider = new JButton("Valider");
		
		/*
		 * Ce message va s'afficher que quand il n y' aucune fiche 
		 */
		this.msg = new JLabel("Il n'y a aucune fiche a valider !",JLabel.CENTER);
		this.msg.setPreferredSize(new Dimension(700,50));		
		
		/*
		 * Ajout de la date dans le panel
		 */
		this.add(this.lblDate);
		
		if(this.choixVisiteur.getSelectedItem()==null){
			this.btnValider.setEnabled(false);
			this.add(this.msg);
		}
		else {
			/*
			 * Ajout des composants dans le panel "panelForm"
			 */
			this.panelForm.setBorder(new TitledBorder("Choix visiteur"));
			this.panelForm.add(lblChoixVisiteur);
			this.panelForm.add(choixVisiteur);
			this.panelForm.add(lblChoixMois);
			this.panelForm.add(choixMois);
			
			this.panelForm.add(espace);
			
			this.panelForm.add(btnValider);
		}		
		
		
		/*
		 * Ajout du formulaire dans le panel
		 */
		this.add(panelForm);
		
	}
	
	/**
	 * @return le visiteur
	 */
	public String getChoixVisiteur() {
		String visiteur = choixVisiteur.getSelectedItem().toString();
		return visiteur;
	}
	/**
	 * @return le mois
	 */
	public String  getChoixMois() {
		String mois =  (String) choixMois.getSelectedItem();
		return mois;
	}
	
	/**
	 * @return la liste deroulante choixVisiteur
	 */
	public JComboBox<String> getListeVisiteur() {
		return choixVisiteur;
	}
	
	/**
	 * @return La liste déroulant choixMois
	 */
	public JComboBox<String>  getListeMois() {
		return choixMois;
	}

	/**
	 * @return le boutton "Valider"
	 */
	public static JButton getBtnValider() {
		return btnValider;
	}
}