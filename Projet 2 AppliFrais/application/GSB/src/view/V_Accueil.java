package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import modele.*;


public class V_Accueil extends JFrame {
	
	//Attributs privés	
		//Panels
	private JPanel panelAccueil;
		//Panel autres classes
	private V_SuiviePaiement panelSuivieP;
	private V_ChoixVisiteur panelChoixV;
	private V_SuivieVisiteur panelSuivieV;
	private V_ChoixSuivi panelChoixSuivi;
	private V_FicheAValider panelFicheFraisAValider;

	/*
	private V_ficheValidee panFicheValidee;*/
		//Color
	private Color bgColor;
		//JLabel
	private JLabel lblLogo;
	
	public V_Accueil(){
		
		/*
		 * Squelette de base configuration fenetre
		 */
		this.setTitle("GSB AppliFrais");	// Titre de la fenetre
		this.setLocation(700,300);  	// Position la fenetre au centre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 	// Arrete le programme lorsqu'on ferme la Fenetre
		this.setSize(700 ,500); 	// Donne une taille a la fenetre à sont ouverture
		this.setResizable(false);	// Interdit la redimmension de la fenetre
		
		/*
		 * logo
		 */
		ImageIcon Icon = new ImageIcon("images/logo.png");
		this.lblLogo = new JLabel(Icon);
		
		/*
		 * Contenu du panel
		 */
		this.panelAccueil = new JPanel();
		
		this.setJMenuBar(new V_Menu(this,panelChoixSuivi,panelChoixV, this.panelAccueil, panelSuivieP,panelFicheFraisAValider,panelSuivieV));

		/*
		 * Couleur de fond du panel accueil
		 */
		this.bgColor = Color.decode("#ff9933");
		this.setBackground(bgColor);
		this.panelAccueil.setBackground(bgColor);
		
		/*
		 * Panel de l'accueil
		 */
		this.panelAccueil = new JPanel();
		this.panelAccueil.setBackground(this.bgColor);		
		
		this.panelAccueil.add(lblLogo);
		
		this.getContentPane().add(panelAccueil);

		this.setVisible(true); 	// Par defaut la Fenetre est invisible

	}	
}