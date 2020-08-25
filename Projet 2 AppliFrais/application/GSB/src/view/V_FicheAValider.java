package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import modele.*;
import obj.*;

/**
 * Affiche la fiche forfais et horsforfais du visiteur séléctionné
 */

public class V_FicheAValider extends JPanel{
	
		//Attributs privés
			//JLabel
		private JLabel date;
		private JLabel descriptifElement;
		private JLabel elementFofaitises;
		private JLabel etatActuel;
		
		private JLabel justificatif;
		private JLabel saut;
			//JPanel
		private JPanel panelStatut;
		private JPanel panelLesForfais;
			//String
		private String etatAct;
			//Color
		private Color bgColor;
			//Tableaux JScrollPane
		private Object[][] donneesElFofaitises;
	    private JTable tableauElFofaitises;
	    private JScrollPane scrollElFofaitises;
	    
	    private Object[][] montantFraisForfait;
	    
	    private Object[][] ElHorFofais;
	    private JTable tblElHorFofais;
	    private JScrollPane scrollElHorFofais;
	    	//JButton
	    private JButton bntValider;
	    	//Int
	    private int nbJustificatifs = 0;
	    private float montantFraisForfaitValider = 0;
	    private float montantFraisHorsForfaitValider = 0;
	    	//String
	    private String idVisiteur;
	    private String montantValider = "";
	    

	    	//JTextField
	    private JTextField jtfJustif;
	    
	    
	public V_FicheAValider(final String visiteur, final String mois){
		
		/*
		 * Panel Statut de la fiche
		 */
		this.panelStatut = new JPanel();
		this.panelStatut.setPreferredSize(new Dimension(400,20));
		
		/*
		 * Panel des frais forfais
		 */
		this.panelLesForfais = new JPanel();
		this.panelLesForfais.setBackground(bgColor);
		
		/*
		 * Couleur du fond
		 */
		this.bgColor = Color.decode("#ff9933");
		this.panelStatut.setBackground(bgColor);
				
		/*
		 * Date de la fiche de frais
		 */
		this.date = new JLabel("Fiche de frais du mois : "+mois + "", JLabel.CENTER);
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
		for(int i=0; i<Modele.getEtatActuel(idVisiteur, mois).size();i++) {
			
			Etat etat = Modele.getEtatActuel(idVisiteur, mois).get(i);
			this.etatAct = etat.getlibelle();
			
		}
		
		/*
		 * Etat actuel de la fiche du visiteur séléctionné
		 */
		this.etatActuel = new JLabel("Etat actuel : "+this.etatAct+ "",JLabel.CENTER);	
		
		
		this.etatActuel.setPreferredSize(new Dimension(600,30));
		this.elementFofaitises = new JLabel("Eléments fofaitisés :", JLabel.CENTER);
		this.elementFofaitises.setPreferredSize(new Dimension(600,30));
		
		/*-----------------------------------------------------------*/
		
		/*
		 * Tableau Eléments fofaitisés
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
		
		
		/*
		 * Montant fiche frais forfait
		 */
		int taille = Modele.getLesFraisForfait(idVisiteur,mois).size();
		
		for(int i = 0 ; i<taille ; i++){
			montantFraisForfaitValider += Modele.getLesFraisForfait(idVisiteur,mois).get(i).getQte() * Modele.getLesMontantFraisForfait().get(i).getMontant();

		}
		montantFraisForfaitValider += Modele.getLesFraisForfait(idVisiteur, mois).get(1).getQte() * 0.62;

		/*------------------------------------------------------------*/
		
		this.descriptifElement = new JLabel("Éléments hors forfait : ", JLabel.CENTER);
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
		 * Affichage du nb de justificatifs
		 */
		this.justificatif = new JLabel("Nombre de justificatifs :",JLabel.CENTER);
		
		this.jtfJustif = new JTextField(2);
		
		/*
		 * Espace
		 */
		this.saut = new JLabel();
		this.saut.setPreferredSize(new Dimension(630,20));
		
		/*
		 * Bouton valider
		 */
		this.bntValider = new JButton("Valider");
		//this.bntValider.setEnabled(false);
		
	
		
		/*
		 * Ajouts
		 */
		this.add(this.date);
		
		this.add(this.etatActuel);

		
		/*
		 * Ajout du panel "paneStatut & "elementFofaitises"
		 */
		this.add(this.panelStatut);
		
		/*
		 * Tableau 1
		 */
		this.add(this.elementFofaitises);
		this.add(this.scrollElFofaitises);

		/*
		 * Tabeau 2
		 */
		this.add(this.descriptifElement);
		this.add(this.scrollElHorFofais);
		
		this.add(this.justificatif);
		this.add(this.jtfJustif);
		
		
		this.add(this.saut);
		this.add(this.bntValider );

		/*
		 * Action boutton valider
		 */
		this.bntValider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
				
				if(jtfJustif.getText().isEmpty()){
					
					JOptionPane.showMessageDialog(null,"Veuillez mettre le nombre de justificatifs","Erreur",JOptionPane.ERROR_MESSAGE);
				}
				
				else{
				
					nbJustificatifs = Integer.parseInt(jtfJustif.getText());
					//System.out.println(nbJustificatifs);
	
					/*
					 * Récapitulatif avant la validation de la fiche
					 */
					
					
					
					if(nbJustificatifs == 0){
						montantFraisHorsForfaitValider = 0;
					}
					else{
						montantFraisHorsForfaitValider = (float) tblElHorFofais.getValueAt(0, 2);
					}
					
					montantValider = "Montant validée -> "+ (montantFraisForfaitValider + montantFraisHorsForfaitValider )+" €";
					
					int verifRecap = JOptionPane.showConfirmDialog(null,
							
							"" + montantValider +
							
							"\n\nSouhaitez-vous valider cette fiche ?",
							
							"Détails de la validation",JOptionPane.YES_NO_OPTION);

					if(verifRecap==0){
						
						montantFraisForfaitValider += montantFraisHorsForfaitValider;
						
						int verifValidFiche = Modele.validerFicheFrais(mois,idVisiteur,montantFraisForfaitValider,nbJustificatifs);
						System.out.println(2);

						if(verifValidFiche==1){
							
							JOptionPane.showMessageDialog(null,"La fiche frais du visiteur "+visiteur+" a bien été validée","Validation",JOptionPane.INFORMATION_MESSAGE);
								
							for(int i=0; i<Modele.getEtatActuel(idVisiteur, mois).size();i++){
								Etat etat = Modele.getEtatActuel(idVisiteur, mois).get(i);
								etatAct = etat.getlibelle();
							}
							
							etatActuel.setText("Etat actuel : "+etatAct);
							
							bntValider.setEnabled(false);
						}
						else{
							
							JOptionPane.showMessageDialog(null,"Validation échouée","Erreur",JOptionPane.ERROR_MESSAGE);
						}
						
					}
					else{
						System.out.println("Validation de la fiche annulée");
					}
						
				}
					
			 }
			
		});
	}
}