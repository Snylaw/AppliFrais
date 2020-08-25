<?php
if(!isset($_REQUEST['action'])){
	$_REQUEST['action'] = 'demandeConnexion';
}
$action = $_REQUEST['action'];

/* Nouveautées */

$moisFicheActuel = date("Ym");

$ficheCR = $pdo->getVisiteurFicheCR($moisFicheActuel);

$ficheVA = $pdo->getVisiteurFicheVa();

/* / Nouveautées */

switch($action){
	case 'demandeConnexion':{
		include("vues/v_connexion.php");
		break;
	}
	case 'valideConnexion':{
		$login = $_REQUEST['login'];
		$mdp = md5($_REQUEST['mdp']);
		$visiteur = $pdo->getInfosVisiteur($login,$mdp);

		if(!is_array( $visiteur)){
			ajouterErreur("Login ou mot de passe incorrect");
			include("vues/v_erreurs.php");
			include("vues/v_connexion.php");
		}
		else{
			$id = $visiteur['id'];
			$nom =  $visiteur['nom'];
			$prenom = $visiteur['prenom'];
			$role = $visiteur['role'];

			connecter($id,$nom,$prenom,$role);
			if ($role == '1'){
				include("vues/v_sommaire.php");
				echo "<script>
						document.body.style.background = '#ff9900';
						var entt = document.getElementById('entete');
						entt.style.background = '#ffad33';
						var ecriture = document.getElementById('menuGauche');
						ecriture.style.color = '#ff9900';
						var liste_champs = document.getElementById('ul#menuList');
						liste_champs.style.color = '##ff9933';
					</script>";
			}
			else {
				include("vues/v_sommaire.php");
			}	
		}
		break;
	}
	default :{
		include("vues/v_connexion.php");
		break;
	}
}
?>