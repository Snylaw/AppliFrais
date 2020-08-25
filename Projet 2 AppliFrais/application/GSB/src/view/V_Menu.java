package view;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import modele.*;


public class V_Menu extends JMenuBar{		
		// Création d'un "bouton menu" Produit dans la barre de menu
		private JMenu menuValider = new JMenu("Valider Fiche de Frais");
		private JMenu menuRemboursement = new JMenu("Suivre le paiement des fiches de Frais");
		private JMenu menuListe = new JMenu("Liste de toutes les fiches de Frais");
		private JMenu menuDeconnexion = new JMenu("Deconnexion");


		// Création d'un élément du menu BDD
		private JMenuItem valider = new JMenuItem("Valider Fiche de Frais");
		private JMenuItem remboursement = new JMenuItem("Suivre le paiement des fiches de Frais");
		private JMenuItem listeV = new JMenuItem("Liste des fiches de Frais");
		private JMenuItem deconnexion = new JMenuItem("Deconnexion");
		private V_Accueil maVue;
		
		//Panels
		private JPanel panelChoixSuivi;
		private JPanel panelChoixV;
		private JPanel panelSuivieP;
		private JPanel panelFicheFraisAValider;
		private JPanel panelSuivieV;
		
		private JPanel panelAccueil;
		
		//Color
		private Color bgColor;
		
	public V_Menu(V_Accueil vue, JPanel panelChoixSuivi, JPanel panelChoixV, JPanel panelAccueil, JPanel panelSuivieP, JPanel panelFicheFraisAValider, JPanel panelSuivieV){
		// Ajout des éléments au menu nommé menuBDD
		this.menuValider.add(valider);
		this.menuRemboursement.add(remboursement);
		this.menuListe.add(listeV);
		this.menuDeconnexion.add(deconnexion);
		
		// Ajout du menuBDD dans la barre de menu
		this.add(this.menuValider);
		this.add(this.menuRemboursement);
		this.add(this.menuListe);
		this.add(this.menuDeconnexion);
		
		this.maVue = vue;
		
		this.panelChoixSuivi = panelChoixSuivi;
		this.panelChoixV = panelChoixV;
		this.panelSuivieP = panelSuivieP;
		this.panelFicheFraisAValider = panelFicheFraisAValider;
		this.panelSuivieV = panelSuivieV;
		
		this.panelAccueil = panelAccueil;
		
		
		// Ajout d'un listener appelant la classe menuActionQuitter lorsque l'on clique sur Quitter
		valider.addActionListener(new ActionValider());
		remboursement.addActionListener(new ActionRemboursement());
		listeV.addActionListener(new ActionListeV());
		deconnexion.addActionListener(new ActionDeconnexion());
		
		/*
		 * Couleur de fond du panel accueil
		 */
		this.bgColor = Color.decode("#ff9933");
	
		
		
	
	}
	
	/*----------------------- Valider la fiche ---------------------------*/
	
	/*
	 * ActionListener choix de la fiche a valider
	 */
	class ActionValider implements ActionListener{		
		public void actionPerformed(ActionEvent e) {
			
			panelChoixV = new V_ChoixVisiteur(Modele.getVisiteursFicheCL());
			/*
			 * Action bouton "BtnValider" de la classe V_choixVisiteur
			 * Cette action permet d'ouvrir le panel "ficheFrais"
			 */
			V_ChoixVisiteur.getBtnValider().addActionListener(new ActionBtnValiderPanelChoixVisiteur());
			
			panelAccueil.removeAll();
			
			maVue.setContentPane(panelChoixV);
			maVue.getContentPane().revalidate();
			
			setVisible(true);
		}
	}
	
	/*
	 * ActionListener du bouton validant le choix de la fiche de frais a valider
	 */
	class ActionBtnValiderPanelChoixVisiteur implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			panelFicheFraisAValider = new V_FicheAValider(((V_ChoixVisiteur) panelChoixV).getChoixVisiteur(),((V_ChoixVisiteur) panelChoixV).getChoixMois());
			panelFicheFraisAValider.setBackground(bgColor);
			
			panelAccueil.removeAll();
			
			maVue.setContentPane(panelFicheFraisAValider);
			maVue.getContentPane().revalidate();
			
			setVisible(true);
		}
		
	}
	
	/*----------------------- Suivi du remboursement de la fiche ---------------------------*/
	
	
	/*
	 * ActionListener 
	 * Cette action permet d'ouvrir le panel "listeVisiteurs"
	 */
	class ActionRemboursement implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			/*
			 * Panel choixSuivi
			 */
			panelChoixSuivi = new V_ChoixSuivi(Modele.getVisiteursFicheVA());
			V_ChoixSuivi.getBtnValider().addActionListener(new ActionBtnValiderPanelChoixSuvi());
			
			panelAccueil.removeAll();
			
			maVue.setContentPane(panelChoixSuivi);
			maVue.getContentPane().revalidate();
			
			setVisible(true);
		}
		
	}
	
	/*
	 * ActionListener du bouton validant le choix de la fiche de frais a suivre le remboursement
	 */
	class ActionBtnValiderPanelChoixSuvi implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			/*
			 * Panel recap
			 */
			panelSuivieP = new V_SuiviePaiement(((V_ChoixSuivi) panelChoixSuivi).getChoixVisiteur(),((V_ChoixSuivi) panelChoixSuivi).getChoixMois());
			panelSuivieP.setBackground(bgColor);
			
			//V_SuiviePaiement.getBtnPdf().addActionListener(new ActionBtnPdfPanelSuiviePaiement());
			
			panelAccueil.removeAll();
			
			maVue.setContentPane(panelSuivieP);
			maVue.getContentPane().revalidate();
			
			setVisible(true);
		}
		
	}
	
	/*
	 * ActionListener du bouton le pdf de la fiche de frais
	 */
	/*class ActionBtnValiderPanelChoixSuvi implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
		}
	}*/
	
	/*------------------- Liste des visiteurs ayant une fiche peut importe sont état ----------------------*/
	
	class ActionListeV implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			/*
			 * Panel suivi
			 */
			panelSuivieV = new V_SuivieVisiteur(Modele.getSuivi());
			panelSuivieV.setBackground(bgColor);
			
			panelAccueil.removeAll();
			
			maVue.setContentPane(panelSuivieV);
			maVue.getContentPane().revalidate();

			setVisible(true);
		}
		
	}
	
	/*----------------------- Deconnexion ---------------------------*/
	
	class ActionDeconnexion implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			maVue.dispose();
			V_Login fenetreLogin = new V_Login();	
		}
	}
	
}
