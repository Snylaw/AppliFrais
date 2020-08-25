package view;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import obj.Suivi;


public class V_SuivieVisiteur extends JPanel{
	
	// Attributs privés
		//JScrollPane
	private Object[][] donnees;
    private JTable tab;
    private JScrollPane scroll;
    	//JPanel
    private JPanel suiviEtat ;
    	//JLabel
    private JLabel titre;
	private JLabel lblVide;    
    	//Color
    private Color bgColor;  
           
     public  V_SuivieVisiteur(ArrayList<Suivi> leSuivi) {
    	
		 /*
		 * Couleur arriere-plan de la fenetre
		 */
		this.bgColor = Color.decode("#ff9933");
		
		this.suiviEtat = new JPanel();
		this.suiviEtat.setPreferredSize(new Dimension(700,600));
		this.suiviEtat.setBackground(bgColor);
		
		this.titre = new JLabel("<html><h1>Suivi fiche de frais</h1>",JLabel.CENTER);
    	 
		this.titre.setPreferredSize(new Dimension(700,30));

		/*
		 * Label vide
		 */
		this.lblVide = new JLabel();
		this.lblVide.setPreferredSize(new Dimension(220,15));

	    /*
	     * Creation de l'entête du tableau 
	     */
		String[]entetes = {"Id","Nom","Prenom","Date","Montant Valide","Etat"}; {
       
	       /*
	        * Définition de la taille du tableau
	        */
	       this.donnees = new Object[leSuivi.size()][entetes.length];
	       
	       /*
	        * Ajout des données dans le tableau JTable
	        */
	       this.tab = new JTable(donnees, entetes);
       
            /*
             * Boucle qui parcours la fonction getSuivi() 
             */
            for (int i=0 ; i<leSuivi.size();i++){
            		Suivi suivi = leSuivi.get(i);
                    
                    this.donnees[i][0] = suivi.getId();
                    this.donnees[i][1] = suivi.getNom();
                    this.donnees[i][2] = suivi.getPrenom();
                    this.donnees[i][3] = suivi.getDate();
                    this.donnees[i][4] = suivi.getMontantValide();
                    this.donnees[i][5] = suivi.getEtat();
            }
           
            this.scroll = new JScrollPane(tab);
            this.scroll.setPreferredSize(new Dimension(500, 250));
            
            /*
             * Ajout au panel
             */
            this.suiviEtat.add(this.titre);
    		this.suiviEtat.add(this.lblVide);
            this.suiviEtat.add(this.scroll);

            
            this.add(this.suiviEtat);
      }

     }

    
}