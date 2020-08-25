package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import modele.*;
import obj.*;

public class V_SuiviePaiement extends JPanel {

	//Attributs privés
		//JLabel
	private JLabel date;
	private JLabel descriptifElement;
	private JLabel elementFofaitises;
	private JLabel etatActuel;
	
    private JLabel saut ;
		//JPanel
	private JPanel panStatut;
	private JPanel panLesForfais;
		//String
	private String etatAct;
    private String idVisiteur;
		//Color
	private Color bgColor;
		//Tableaux JScrollPane
	private Object[][] donneesElFofaitises;
    private JTable tableauElFofaitises;
    private JScrollPane scrollElFofaitises;
    
    private Object[][] ElHorFofais;
    private JTable tblElHorFofais;
    private JScrollPane scrollElHorFofais;
		//JButton
    private JButton rembourse;
	private static JButton btnPdf ;
    
    

	public V_SuiviePaiement(final String visiteur, final String mois){
		
		/*
		 * Panel Statut de la fiche
		 */
		this.panStatut = new JPanel();
		this.panStatut.setPreferredSize(new Dimension(400,20));
		
		/*
		 * Panel des frais forfais
		 */
		this.panLesForfais = new JPanel();
		this.panLesForfais.setBackground(bgColor);
		
		/*
		 * Couleur du fond
		 */
		this.bgColor = Color.decode("#ff9933");
		this.panStatut.setBackground(bgColor);
		
		/*
		 * Date de la fiche de frais
		 */
		this.date = new JLabel("Fiche de frais du mois : "+mois+"",JLabel.CENTER);
		this.date.setPreferredSize(new Dimension(600,30));
		
		/*
		 * Cette fonction recupére l'idVisiteur en fonction de son nom
		 */
		for (int i = 0; i <Modele.getIdVisiteur(visiteur).size(); i++) {
			
			Visiteur visit = Modele.getIdVisiteur(visiteur).get(i);
			idVisiteur = visit.getId();
		}
		
		/*
		 * Cette fonction récupère le libelle de l'etat de la fiche
		 */
		for(int i=0; i<Modele.getEtatActuel(idVisiteur, mois).size();i++){
			
			Etat etat = Modele.getEtatActuel(idVisiteur, mois).get(i);
			this.etatAct = etat.getlibelle();
		
		}
		
		/*
		 * Etat actuel de la fiche du visiteur séléctionné
		 */
		this.etatActuel = new JLabel("Etat actuel : "+this.etatAct+"",JLabel.CENTER);
		
	
		this.etatActuel.setPreferredSize(new Dimension(600,30));
		this.elementFofaitises = new JLabel("Eléments fofaitisés :",JLabel.CENTER);
		this.elementFofaitises.setPreferredSize(new Dimension(600,30));
		
		/*-----------------------------------------------------------*/
		
		/*
		 * TABLEAU Eléments fofaitisés
		 */
			/*
			 * Entete
			 */
		String[]entetesElFofaitises = {"Libellé","quantité"};
			/*
			 * Définir la taille du tableau
			 */
		this.donneesElFofaitises = new Object[Modele.getLesFraisForfait(idVisiteur,mois).size()][entetesElFofaitises.length];
		
		this.tableauElFofaitises = new JTable(donneesElFofaitises, entetesElFofaitises);
		
		for (int i=0 ; i<Modele.getLesFraisForfait(idVisiteur,mois).size() ;i++){
			
			FraisForfait fiche = Modele.getLesFraisForfait(idVisiteur,mois).get(i);
			this.donneesElFofaitises[i][0] = fiche.getLibelle();
			this.donneesElFofaitises[i][1] = fiche.getQte();
		}
		
		this.scrollElFofaitises = new JScrollPane(tableauElFofaitises);
		this.scrollElFofaitises.setPreferredSize(new Dimension(570,85));	
		
		/*------------------------------------------------------------*/
		
		this.descriptifElement = new JLabel("Descriptif des éléments hors forfait :", JLabel.CENTER);
		this.descriptifElement.setPreferredSize(new Dimension(600,30));	
		
		/*------------------------------------------------------------*/
		
		/*
		 * TABLEAU Descriptif des éléments hors forfait
		 */
			/*
			 * Entete
			 */
		String[]entetesHorsForfait = {"Libellé","Date","Montant"};
			/*
			 * Définir la taille du tableau
			 */
    	this.ElHorFofais = new Object[Modele.getLesFraisHorsForfait(idVisiteur,mois).size()][entetesHorsForfait.length];
    	
    	this.tblElHorFofais = new JTable(ElHorFofais,entetesHorsForfait);
  
		for (int i=0 ; i<Modele.getLesFraisHorsForfait(idVisiteur,mois).size() ;i++){
			
			FraisHorsForfait fhf = Modele.getLesFraisHorsForfait(idVisiteur,mois).get(i);
			this.ElHorFofais[i][0] = fhf.getLibelle();
			this.ElHorFofais[i][1] = fhf.getDate();
			this.ElHorFofais[i][2] = fhf.getMontant();
		}

		this.scrollElHorFofais = new JScrollPane(tblElHorFofais);
		this.scrollElHorFofais.setPreferredSize(new Dimension(570,50));
		
		/*-------------------------------------------------------------*/
	
		/*
		 * Espace
		 */
		this.saut = new JLabel();
		this.saut.setPreferredSize(new Dimension(630,20));
		
		/*
		 * Bouton remboursé
		 */
		this.rembourse = new JButton("Rembourser");
	
		/*
		 * Bouton PDF
		 */
		btnPdf = new JButton("Télécharger sous format PDF");
	
		
		/*
		 * Ajouts
		 */
		this.add(this.date);
		this.add(this.etatActuel);
		
		
		
		/*
		 * Ajout du panel "panStatut & "elementFofaitises"
		 */
		this.add(this.panStatut);
		
		/*
		 * Tableau 1
		 */
		this.add(this.elementFofaitises);
		this.add(this.scrollElFofaitises);
	
		/*
		 * Tableau 2
		 */
		this.add(this.descriptifElement);
		this.add(this.scrollElHorFofais);
	
		
		this.add(this.saut);
		this.add(this.rembourse);
		this.add(this.btnPdf);

	
		/*
		 * Action boutton remboursé
		 */
		this.rembourse.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
					  
				int verifValidFiche = Modele.rembourserFiche(mois,idVisiteur);
				
				if(verifValidFiche==1){
					
					JOptionPane.showMessageDialog(null,"La fiche de frais a été mise en paiement","Remboursement",JOptionPane.INFORMATION_MESSAGE);
					
					for(int i=0; i<Modele.getEtatActuel(idVisiteur, mois).size();i++){
						Etat etat = Modele.getEtatActuel(idVisiteur, mois).get(i);
						etatAct = etat.getlibelle();
					}
					
					/**
					 * Affiche l'etat actuelle de la fiche
					 */
					etatActuel.setText("Etat actuel : "+etatAct);
					/**
					 * Désactive le bouton rembourser
					 */
					rembourse.setEnabled(false);
				}
				
				else{
					
					JOptionPane.showMessageDialog(null,"La remboursement a échoué","Erreur",JOptionPane.ERROR_MESSAGE);
				}
			
			}				
		});
	}
	
	/**
	 * @return le bouton "PDF"
	 */
	public static JButton getBtnPdf() {
		return btnPdf;
	}
}
