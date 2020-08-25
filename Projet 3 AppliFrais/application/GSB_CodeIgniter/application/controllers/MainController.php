<?php
defined('BASEPATH') OR exit('No direct script access allowed');
class MainController extends CI_Controller{
	
	/*
	*Page de départ qui amène a la page de connection
	*/
	public function index(){
		$this->load->view('entete');
		//$this->load->view('menuCR');
		$this->load->view('login');
		$this->load->view('pied');
	}
	/*
	*Page qui amene sur l'acueil du site
	*/
	public function start(){
		$this->load->view('entete');
		$this->load->view('menuCR');
		$this->load->view('pied');
	}

	//Medicaments
	function choixMedicament(){
		//Récupere les fiches des médicaments 
		$data['listeMedicaments'] = $this->monModele->get_les_medicaments();
		
		$this->load->view('entete');
		$this->load->view('menuCR');
		$this->load->view('formMEDICAMENT', $data);
		$this->load->view('pied');
	}	

	function affichageMedicament(){
		//Récupère la fiche séléctionné
		$data['listeMedicaments'] = $this->monModele->get_les_medicaments();

		$medicement=$this->input->get_post('lstMedicament');
		$data['leMedicament'] = $this->monModele->get_un_medicament($medicement);

		$data['col_list'] = array('NOM COMMERCIAL', 'FAMILLE', 'COMPOSITION', 'EFFETS', 'CONTRE INDIC.', 'PRIX ECHANTILLON');

		$this->load->view('entete');
		$this->load->view('menuCR');
		$this->load->view('formMEDICAMENT', $data);
		$this->load->view('pied');
	}

	//Praticiens
	function choixPraticien(){
		//Récupere les fiches des médicaments 
		$data['listePraticiens'] = $this->monModele->get_les_praticiens();
		
		$this->load->view('entete');
		$this->load->view('menuCR');
		$this->load->view('formPRATICIEN', $data);
		$this->load->view('pied');
	}	

	function affichagePraticien(){
		//Récupère la fiche séléctionné
		$data['listePraticiens'] = $this->monModele->get_les_praticiens();

		$praticien=$this->input->get_post('lstPraticien');
		$data['lePraticien'] = $this->monModele->get_un_praticien($praticien);

		$data['col_list'] = array('NUMERO', 'NOM', 'PRENOM', 'ADRESSE', 'CODE POSTAL', 'VILLE', 'COEFF. NOTORIETE', 'TYPE');

		$this->load->view('entete');
		$this->load->view('menuCR');
		$this->load->view('formPRATICIEN', $data);
		$this->load->view('pied');
	}

	//Visiteurs
	function choixVisiteur(){
		//Récupere les fiches des médicaments 
		$data['listeVisiteurs'] = $this->monModele->get_les_visiteurs();
		
		$this->load->view('entete');
		$this->load->view('menuCR');
		$this->load->view('formVISITEUR', $data);
		$this->load->view('pied');
	}	

	function affichageVisiteur(){
		//Récupère la fiche séléctionné
		$data['listeVisiteurs'] = $this->monModele->get_les_visiteurs();

		$visiteur=$this->input->get_post('lstVisiteur');
		$data['leVisiteur'] = $this->monModele->get_un_visiteur($visiteur);

		$data['col_list'] = array('NOM', 'PRENOM', 'ADRESSE', 'CODE POSTAL', 'VILLE', 'DATE EMBAUCHE');

		$this->load->view('entete');
		$this->load->view('menuCR');
		$this->load->view('formVISITEUR', $data);
		$this->load->view('pied');
	}

	/*
	*Page qui amene sur le formulaire des rapports de visite
	*/
	function formRAPPORT_VISITE(){
		//$this->load->model('monModel');
		$prat =$this->monModele->getAllNomP();
		$data['prat']=$prat;
		$lesCoef = array();
		foreach($prat as $key=>$value){
			foreach($value as $cle=>$vlr){
				$lesCoef[] = $coef = $this->monModele->getCoeffP($vlr);
			}
		}
		$data['medicament']=$this->monModele->getAllM();
		$data['visiteur'] =$this->monModele->getAllV();
		$data['lesCoef']=$lesCoef;
		$this->load->view('entete');
		$this->load->view('menuCR');
		$this->load->view('formRAPPORT_VISITE',$data);
		$this->load->view('pied');
	}
	/*
	*Fonction du controleur qui permet de switch sur les bonnes fonction pour changer de page
	*/
	function connection(){
		//$this->load->model('monModel');
		$log = $this->input->get_post('identifiant');
		$mdp = $this->input->get_post('password');
		if($this->monModele->connection($log,$mdp)){
			$this->start();
		}else{
			$this->index();
		}
	}
	/*
	* Fonction qui récupere les informations saisie dans le formulaire pour etre envoyer dans la base de donnée
	*/
	function recupRAPPORT_VISITE(){
		$this->load->view('entete');
		$this->load->view('menuCR');
		$visiteur = $this->input->get_post('VISITEUR');
		$matricule_visiteur = $this->monModele->getMatriculeV($visiteur);
		$numero = $this->input->get_post('RAP_NUM');
		$praticien = $this->input->get_post('PRA_NUM');
		$p = $this->monModele->getIdP($praticien);
		$date = $this->input->get_post('RAP_DATE');
		$bilan = $this->input->get_post('RAP_BILAN');
		$motif = $this->input->get_post('RAP_MOTIF');
		$res = $this->monModele->setRapportVisite($matricule_visiteur,$numero,$p,$date,$bilan,$motif);
		//$res = $this->monModele->existe($matricule_visiteur,$numero,$p,$date,$bilan,$motif);
		if(!$res){
			$this->load->view('erreur');
		}else{
			$this->load->view('reussite');
		}
		$this->load->view('pied');
	}
	function formCONSULTER_RAPPORT(){
		$data['numero']= $this->monModele->getAllNumeroR();
		$this->load->view('entete');
		$this->load->view('menuCR');
		$this->load->view('formCONSULTER_RAPPORT',$data);
		$this->load->view('pied');
	}
	function recupCONSULTER_RAPPORT(){
		$data['numero']= $this->monModele->getAllNumeroR();
		$numero = $this->input->get_post('RAP_NUM');
		$res = $this->monModele->getAllInfoR($numero);
		$data['res'] = $res;

		$data['col_list'] = array('MATRICULE VISITEUR', 'NUMERO RAPPORT', 'NUMERO PRATICIEN', 'DATE RAPPORT', 'BILAN RAPPORT', 'MOTIF RAPPORT');

		$this->load->view('entete');
		$this->load->view('menuCR');
		$this->load->view('formCONSULTER_RAPPORT',$data);
		$this->load->view('pied');
	}
}