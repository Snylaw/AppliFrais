<?php

$moisFicheActuel = date("Ym");
$ficheCR = $pdo->getVisiteurFicheCR($moisFicheActuel);

$ficheVA = $pdo->getVisiteurFicheVa();

include("vues/v_sommaire.php");
$action = $_REQUEST['action'];
$idVisiteur = $_SESSION['idVisiteur'];

$mois = getMois(date("d/m/Y"));



switch($action){
	case 'selectionnerMois':{
		$lesMois=$pdo->getLesMoisDisponibles($idVisiteur);
		// Afin de sélectionner par défaut le dernier mois dans la zone de liste
		// on demande toutes les clés, et on prend la première,
		// les mois étant triés décroissants
		$lesCles = array_keys( $lesMois );
		$moisASelectionner = $lesCles[0];
		include("vues/v_listeMois.php");
		break;
	}
	case 'voirEtatFrais':{
		$leMois = $_REQUEST['lstMois']; 
		$lesMois=$pdo->getLesMoisDisponibles($idVisiteur);
		$moisASelectionner = $leMois;
		include("vues/v_listeMois.php");
		$lesFraisHorsForfait = $pdo->getLesFraisHorsForfait($idVisiteur,$leMois);
		$lesFraisForfait= $pdo->getLesFraisForfait($idVisiteur,$leMois);
		$lesInfosFicheFrais = $pdo->getLesInfosFicheFrais($idVisiteur,$leMois);
		$numAnnee =substr( $leMois,0,4);
		$numMois =substr( $leMois,4,2);
		$libEtat = $lesInfosFicheFrais['libEtat'];
		$montantValide = $lesInfosFicheFrais['montantValide'];
		$nbJustificatifs = $lesInfosFicheFrais['nbJustificatifs'];
		$dateModif =  $lesInfosFicheFrais['dateModif'];
		$dateModif =  dateAnglaisVersFrancais($dateModif);
		include("vues/v_etatFrais.php");
		break;
	}
	/* Nouveautées */

	case 'voirSuiviPaiement':{
		$leMois = $_REQUEST['lstMois']; 

		$idVisSelect = $_REQUEST['idVisSelect'];
		
		$lesFraisHorsForfait = $pdo->getLesFraisHorsForfait($idVisSelect,$leMois);
		$lesFraisForfait= $pdo->getLesFraisForfait($idVisSelect,$leMois);
		$lesInfosFicheFrais = $pdo->getLesInfosFicheFrais($idVisSelect,$leMois);
		
		$numAnnee =substr( $leMois,0,4);
		$numMois =substr( $leMois,4,2);
		$libEtat = $lesInfosFicheFrais['libEtat'];
		$montantValide = $lesInfosFicheFrais['montantValide'];
		$nbJustificatifs = $lesInfosFicheFrais['nbJustificatifs'];
		$dateModif =  $lesInfosFicheFrais['dateModif'];
		$dateModif =  dateAnglaisVersFrancais($dateModif);
		$ficheFraiVisiteur=$pdo->getLesFraisHorsForfait($idVisSelect,$leMois);
		 
		include("vues/v_suiviPaiement.php");
		break;
	}

	case 'ChoixSuiviPaiementFiche':{
		//Récupere la fiche du visiteur 
		$visiteurFiche = $pdo->getVisiteurFicheVa();
		//Récupere le mois de la fiche
		$moisFiche = $pdo->getVisiteurFicheVa();
		
		//Si la fonction getVisiteurFicheCR renvoie null affiche un msg et renvoie à la page d'accueil.
		if($visiteurFiche==null){
			include("vues/v_infosFiche.php");
		}
		else{
		include("vues/v_miseEnPaimentFicheDeFrais.php");
		}
		break;
	}

	case 'voirFicheFraisVisiteur':{	
		
		$leMois = $_REQUEST['lstMois']; 
		$idVisSelect = $_REQUEST['idVisSelect'];
		
		$lesFraisHorsForfait = $pdo->getLesFraisHorsForfait($idVisSelect,$leMois);
		$lesFraisForfait= $pdo->getLesFraisForfait($idVisSelect,$leMois);
		$lesInfosFicheFrais = $pdo->getLesInfosFicheFrais($idVisSelect,$leMois);
		
		$numAnnee =substr( $leMois,0,4);
		$numMois =substr( $leMois,4,2);
		$libEtat = $lesInfosFicheFrais['libEtat'];
		$montantValide = $lesInfosFicheFrais['montantValide'];
		$nbJustificatifs = $lesInfosFicheFrais['nbJustificatifs'];
		$dateModif =  $lesInfosFicheFrais['dateModif'];
		$dateModif =  dateAnglaisVersFrancais($dateModif);
		$ficheFraiVisiteur=$pdo->getLesFraisHorsForfait($idVisSelect,$leMois);

		include("vues/v_ficheFrais.php");
		break; 
	}

	case 'ChoixFicheAValider':{
		
		$lesMois = $pdo->getVisiteurFicheCR($moisFicheActuel);

		$visiteurFiche = $pdo->getVisiteurFicheCR($moisFicheActuel);

		//Si la fonction getVisiteurFicheCR renvoie null affiche un msg.
		if($visiteurFiche==null){
			include("vues/v_infosFiche.php");
		}
		else{
		   include("vues/v_validerFicheDeFrais.php");
		}

		break;
	}

	case 'telechargerPDF':{
	
		$mois = $pdo->dernierMoisSaisi($idVisiteur);
		$lstInfo = $pdo->getinfo($idVisiteur,$mois);
		$lstInfoHorsforfait = $pdo->getinfohors($idVisiteur,$mois);

		include("c_pdf.php");
		break;
	}

	/* /Nouveautées */
}
?>