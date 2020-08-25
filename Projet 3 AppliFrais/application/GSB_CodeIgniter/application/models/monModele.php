<?php
defined('BASEPATH') OR exit('No direct script access allowed');
class monModele extends CI_Model
{
	private $tableMedicament = "medicament";
	private $tableVisiteur = "visiteur";
	private $tablePraticien = "praticien";

	function __construct()
	{
		parent::__construct();
		$this->load->database();
	}
	
	/* ---------- Medicaments ---------- */

	/**
	 * Fonction qui retourne un tableau de tous les médicament
	 * @return $query->row
	 */
	function get_les_medicaments()
	{
		//Requête
		$this->db->order_by('MED_NOMCOMMERCIAL', 'asc');
		$query = $this->db->get($this->tableMedicament);
		return $query->result();
	}
	
	/**
	 * Fonction qui retourne sous forme de ligne le médicament entré en paramètre
	 * @param $id
	 * @return $query->row
	 */
	function get_un_medicament($id)
	{
		//Requête
		$this->db->select('MED_NOMCOMMERCIAL, FAM_CODE,	MED_COMPOSITION, MED_EFFETS, MED_CONTREINDIC, MED_PRIXECHANTILLON');
		$this->db->from('medicament');
		$this->db->where('MED_NOMCOMMERCIAL =', $id);
		$query = $this->db->get();

		return $query->row();
	}

	/* ---------- Visiteurs ---------- */

	/**
	 * Fonction qui retourne un tableau de tous les visiteurs
	 * @return $query->row
	 */
	function get_les_visiteurs()
	{
		//Requête
		$this->db->order_by('VIS_NOM', 'asc');
		$query = $this->db->get($this->tableVisiteur);
		return $query->result();
	}
	
	/**
	 * Fonction qui retourne sous forme de ligne le visiteur entré en paramètre
	 * @param $id
	 * @return $query->row
	 */
	function get_un_visiteur($id)
	{
		//Requête
		$this->db->select('VIS_NOM, Vis_PRENOM,	VIS_ADRESSE, VIS_CP, VIS_VILLE, VIS_DATEEMBAUCHE');
		$this->db->from('visiteur');
		$this->db->where('VIS_MATRICULE =', $id);
		$query = $this->db->get();
		
		return $query->row();
	}

	/* ----------- Praticien ----------- */
	
	/**
	 * Fonction qui retourne un tableau de tous les praticiens
	 * @return $query->row
	 */
	function get_les_praticiens()
	{
		//Requête
		$this->db->order_by('PRA_NOM', 'asc');
		$query = $this->db->get($this->tablePraticien);
		return $query->result();
	}
	
	/**
	 * Fonction qui retourne sous forme de ligne le praticien entré en paramètre
	 * @param $id
	 * @return $query->row
	 */
	function get_un_praticien($id)
	{
		//Requête
		$this->db->select('PRA_NUM, PRA_NOM, PRA_PRENOM, PRA_ADRESSE, PRA_CP, PRA_VILLE, PRA_COEFNOTORIETE, TYP_CODE');
		$this->db->from('praticien');
		$this->db->where('PRA_NUM =', $id);
		$query = $this->db->get();

		return $query->row();
	}
	

	/* ------------ Partie Adrien --------------- */

	function connection($log,$mdp){
		$rep = false;
		$a = substr($mdp,7,4);//on recupere l'année
		$m = substr($mdp,3,3);//on recupere le mois
		$j = substr($mdp,0,2);//on recupere le jour
		
		//$sql = "SELECT * FROM visiteur WHERE VIS_NOM = ? AND VIS_DATEEMBAUCHE = ?";
		//Liste des mois et de leur chiffre correspondant
		$listeMois = array("jan"=>1,"feb"=>2,"mar"=>3,"apr"=>4,"may"=>5,"jun"=>6,"jul"=>7,"aug"=>8,"sep"=>9,"oct"=>10,"nov"=>11,"dec"=>12);
		$mois = 'rien';
		foreach($listeMois as $key=>$valeur){
			if($key == $m){
				$mois = $valeur;
			}
		}
		//Méthode pour convertir en int les chaines
		$a = $a +0;
		$mois = $mois +0;
		$j = $j +0;
		//On recompose la date en format que sql comprend
		$mdpRecompose = $j.'-'.$mois.'-'.$a." 00:00:00";
		$dateSql = date("Y-m-d H:i:s",mktime(0, 0, 0, $mois, $j, $a));   
		//Création de la requete
		$this->db->select('count(VIS_NOM)');
		$this->db->where('VIS_NOM', $log);
		$this->db->where('VIS_DATEEMBAUCHE', $dateSql);
		$query = $this->db->get('visiteur');
		foreach($query->row() as $item){
			if($item == 1){
				//Si on trouve bien un utilisateur avec ce nom et cette date d'embauche la fonction repond true
				$rep = true;
			}
		}
		return $rep;
	}
	
	/*
	* Récupere tout les nom des praticiens
	*/
	function getAllNomP(){
		$this->db->select('PRA_NOM ');
		$prat = $this->db->get('praticien');
		return $prat->result(); 
	}
	
	function getIdP($nom){
		$this->db->select('PRA_NUM');
		$this->db->where('PRA_NOM',$nom);
		$prat = $this->db->get('praticien');
		foreach($prat->row() as $key=>$vlr){
			$res = $vlr;
		}
		return $res;
	}

	function getMatriculeV($nom){
		$this->db->select('VIS_MATRICULE');
		$this->db->where('VIS_NOM',$nom);
		$prat = $this->db->get('visiteur');
		foreach($prat->row() as $key=>$vlr){
			$res = $vlr;
		}
		return $res;
	}

	function getCoeffP($nom){
		$this->db->select('PRA_COEFNOTORIETE');
		$this->db->where('PRA_NOM',$nom);
		$prat = $this->db->get('praticien');
		return $prat;
	}

	function getAllM(){
		$this->load->database();
		$this->db->select('MED_NOMCOMMERCIAL ');
		$prat = $this->db->get('medicament');
		return $prat->result();
	}

	function getAllV(){
		$this->load->database();
		$this->db->select('VIS_NOM');
		$prat = $this->db->get('visiteur');
		return $prat->result();
	}

	function setRapportVisite($matricule,$numero,$practitien,$date,$bilan,$motif){
		if(!$this->existe($matricule,$numero,$practitien,$date,$bilan,$motif) == "1"){
			$a = substr($date,6,4);//on recupere l'année
			$m = substr($date,3,2);//on recupere le mois
			$j = substr($date,0,2);//on recupere le jour
			$dateSql = date("Y-m-d H:i:s",mktime(0, 0, 0, $m, $j, $a)); 
			$data = array(
				'VIS_MATRICULE' => $matricule,
				'RAP_NUM' => $numero,
				'PRA_NUM' => $practitien,
				'RAP_DATE' => $dateSql,
				'RAP_BILAN' => $bilan,
				'RAP_MOTIF' => $motif
			);
			$this->db->insert('rapport_visite', $data);
			$res=true;
		}else{
			$res=false;
		}
		return $res;
	}

	private function existe($matricule,$numero,$practitien,$date,$bilan,$motif){
		$a = substr($date,6,4);//on recupere l'année
		$m = substr($date,3,2);//on recupere le mois
		$j = substr($date,0,2);//on recupere le jour
		$dateSql = date("Y-m-d H:i:s",mktime(0, 0, 0, $m, $j, $a)); 
		$data = array(
			'VIS_MATRICULE' => $matricule,
			'RAP_NUM' => $numero,
			'PRA_NUM' => $practitien,
			'RAP_DATE' => $dateSql,
			'RAP_BILAN' => $bilan,
			'RAP_MOTIF' => $motif
		);
		
		$res=$this->db->select('count(*)')
				->from('rapport_visite')
				->where('VIS_MATRICULE',$data['VIS_MATRICULE'])
				->where('RAP_NUM',$data['RAP_NUM'])
				->where('PRA_NUM',$data['PRA_NUM'])
				->where('RAP_DATE',$data['RAP_DATE'])
				->where('RAP_BILAN',$data['RAP_BILAN'])
				->where('RAP_MOTIF',$data['RAP_MOTIF'])
				->get();
		return $res->row();
	}

	public function getAllNumeroR(){
		$res=$this->db->select('RAP_NUM')
				->from('rapport_visite')
				->get();
		return $res->result();
	}

	public function getAllInfoR($num){
		$res=$this->db->select('*')
				->from('rapport_visite')
				->where('RAP_NUM',$num)
				->get();
		return $res->result();
	}
}