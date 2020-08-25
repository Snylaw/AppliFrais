package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*; 

import modele.*;


public class V_Login extends JFrame{
	
	// Attributs privés
		//Panels
	private JPanel panelFormulaire;
	private JPanel panelForm;
		//Labels
	private JLabel lblLogin;
	private JLabel lblPassword;
	private JLabel lblVide;
	private JLabel lblLogo;
		//JTextField
	private static JTextField jtfLogin;
	private JPasswordField jtfPassword;
		//JButton
	private JButton btnValider;
	private JButton btnAnnuler;
		//Color
	private Color bgColor;

	
	public V_Login(){
		
		// Squelette de base configuration fenetre
		this.setTitle("GSB AppliFrais");	// Titre de la fenetre
		this.setLocation(600,300);  	// Position la fenetre au centre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 	// Arrete le programme lorsqu'on ferme la Fenetre
		this.setSize(800,550); 	// Donne une taille a la fenetre à sont ouverture
		this.setResizable(false);	// Interdit la redimmension de la fenetre
		
		/*
		 * logo
		 */
		ImageIcon Icon = new ImageIcon("images/logo.png");
		this.lblLogo = new JLabel(Icon);
		
		//Connexion
		this.panelForm = new JPanel();
		this.panelForm.setBorder(new TitledBorder("Login"));
		this.panelForm.setPreferredSize(new Dimension(300,140));
		
		this.panelFormulaire = new JPanel();
		
		//Label Login
		this.lblLogin = new JLabel("Login : ");
		this.lblLogin.setPreferredSize(new Dimension(80,10));
		
		//JTextField Login
		jtfLogin = new JTextField(15);
		
		//Label Password
		this.lblPassword = new JLabel("Password : ");
		this.lblPassword.setPreferredSize(new Dimension(80,10));
		
		//JTextField Password
		this.jtfPassword = new JPasswordField(15);
		
		this.lblVide = new JLabel();
		this.lblVide.setPreferredSize(new Dimension(220,15));
		
		//JButton
		this.btnValider = new JButton("Valider");
		this.btnAnnuler = new JButton("Effacer");
		
		//Couleur de fond des panels
		this.bgColor = Color.decode("#ff9933");
		this.setBackground(bgColor);
		this.panelForm.setBackground(bgColor);
		this.panelFormulaire.setBackground(bgColor);
		
		//Ajout panel panelFormulaire
		this.panelFormulaire.add(this.lblLogo);
		this.panelFormulaire.add(this.panelForm);
		
		//Ajout des composants dans panelForm
		this.panelForm.add(this.lblLogin);
		this.panelForm.add(jtfLogin);
		this.panelForm.add(this.lblPassword);
		this.panelForm.add(this.jtfPassword);
		this.panelForm.add(this.lblVide);
		this.panelForm.add(this.btnValider);
		this.panelForm.add(this.btnAnnuler);
		
		//Ajout des actions
		this.btnValider.addActionListener(new ActionBtnValider());
		this.btnAnnuler.addActionListener(new ActionBtnAnnuler());
		
		//Ajout du formulaire dans le panel
		this.getContentPane().add(this.panelFormulaire);
		
		// Par defaut la Fenetre est invisible
		this.setVisible(true);
	}
	
	public static JTextField getJtfLogin(){		//Recuperer le login sur toutes les pages
		return jtfLogin;
	}
	
	class ActionBtnValider implements ActionListener{		
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {
			
			boolean result = Modele.connexionAdmin(jtfLogin.getText(), jtfPassword.getText());
			
			if(jtfLogin.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Veuillez saisir un login", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			else{
				if(jtfPassword.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Veuillez saisir un password", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				else{
					if(result == false){
						JOptionPane.showMessageDialog(null, "Login ou mot de passe incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
					else{
						dispose();
						V_Accueil fenetreAccueil = new V_Accueil();	
					}
				}
			}			
		}
	}
	
	class ActionBtnAnnuler implements ActionListener{		
		public void actionPerformed(ActionEvent e) {
			jtfLogin.setText("");
			jtfPassword.setText("");
		}
	}	
}
