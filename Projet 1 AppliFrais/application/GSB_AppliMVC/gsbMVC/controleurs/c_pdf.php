<?PHP

//recuperation de la date
$_SESSION['date']=date( 'm',time());
$dateNow =$_SESSION['date'];


//variable texte du PDF
		$texte="";
		
		$texte.='<table  align="center" style="width:100%;border-collapse: collapse;">';
				$texte.="<tr>";
					$texte.='<td  style="text-align: center;width:100%;">';
					$logo = './images/logo.jpg';
					$texte.='<img src="'.$logo.'" width="100">';
					$texte.='</td>';
				$texte.="</tr>";
		$texte.="</table>";	
		$texte.="<br><br><br><br>";
		$texte.='<fieldset>';
		$texte.='<table  align="center" style="border-collapse: collapse;">';
				$texte.="<tr>";
					$texte.='<td  style="text-align: center;width:100%;color:rgb(31,75,125);">';
					$texte.='ETAT DE FRAIS ENGAGES';
					$texte.='</td>';
				$texte.="</tr>";
				$texte.="<tr>";
					$texte.='<td  style="text-align: center;width:100%;color:rgb(79,129,189);">';
					$texte.="<br><br><br><br>";
					$texte.='A retourner accompagné des justificatifs au plus tard le 10 du mois qui suit l\'engagement des frais';
					$texte.='</td>';
				$texte.="</tr>";
		$texte.="</table>";	
		$texte.="<br><br><br><br>";		
		
		//tableau pour afficher les informations du comptable et la date
		$texte.='<table  align="center" style="border-collapse: collapse;">';
				$texte.="<tr>";
					$texte.='<td style="text-align: center;width:40%;color:rgb(31,75,125);">';
					$texte.='<b>VISITEUR</b>';
					$texte.='</td>';
					$texte.='<td style="width:30%">';
					$texte.='Matricule :';
					$texte.='</td>';
					$texte.='<td style="width:30%">';
					$texte.=$_SESSION['idVisiteur'];
					$texte.='</td>';
				$texte.="</tr>";
				$texte.="<tr>";
					$texte.='<td style="text-align: center;width:40%" >';
					$texte.='</td>';
					$texte.='<td style="width:30%" >';
					$texte.='Nom :';
					$texte.='</td>';
					$texte.='<td style="width:30%" >';
					$texte.=$_SESSION['nom'];
					$texte.='</td>';
				$texte.="</tr>";
				$texte.="<br><br>";
				$texte.="<tr>";
					$texte.='<td style="text-align: center;width:40%;color:rgb(31,75,125);">';
					$texte.='<b>MOIS</b>';
					$texte.='</td>';
					$texte.='<td style="width:30%">';
					$texte.="$dateNow";
					$texte.='</td>';
					$texte.='<td style="width:30%">';
					$texte.='</td>';
				$texte.="</tr>";
		$texte.="</table>";
		
		$texte.="<br><br>";

		//tableau frais forfaitaires
		$texte.='<table border=1 align="center" style="width:90%;border-collapse: collapse;">';
			$texte.="<tr>";
					$texte.='<td style="text-align: center;width:30%;color:rgb(31,75,125);">';
					$texte.='<i>Frais<sup>1</sup>Forfaitaires</i>';
					$texte.='</td>';
					$texte.='<td style="text-align: center;width:20%;color:rgb(31,75,125);">';
					$texte.='<i>Quantité</i>';
					$texte.='</td>';
					$texte.='<td style="text-align: center;width:40%;color:rgb(31,75,125);">';
					$texte.='<i>Montant unitaire<sup>2</sup></i>';
					$texte.='</td>';
					$texte.='<td style="text-align: center;width:10%;color:rgb(31,75,125);">';
					$texte.='<i>Total</i>';
					$texte.='</td>';
				$texte.="</tr>";
				$tabLibelle=array("forfait etape","frais kilometrique","nuitée","repas");
				$tabTarif=array(110,0.62,80,25);
				$i=0;
				foreach ($lstInfo as $unFrais) {
							$idFrais = $unFrais[3];
							$idFrais = intval($idFrais);
							$libelle=$tabLibelle[$i];
							$tarif=$tabTarif[$i];
							$total=($idFrais*$tarif);
							
					$texte.="<tr>";
					$texte.='<td style="text-align: center;width:30%;color:rgb(31,75,125);">';
					$texte.="$libelle";
					$texte.='</td>';
					$texte.='<td style="text-align: center;width:20%;color:rgb(31,75,125);">';
					$texte.="$idFrais";
					$texte.='</td>';
					$texte.='<td style="text-align: center;width:40%;color:rgb(31,75,125);">';
					$texte.="$tarif";
					$texte.='</td>';
					$texte.='<td style="text-align: center;width:10%;color:rgb(31,75,125);">';
					$texte.="$total";
					$texte.='</td>';
				$texte.="</tr>";
					$i=$i+1;		
				}

				
		$texte.="</table>";
		$texte.="<br><br>";
		$texte.='<i style="align: center;color:rgb(31,75,125);">Autre Frais<sup>4</sup></i>';
		//tableau autres frais
		$texte.='<table border=1 align="center" style="width:90%;border-collapse: collapse;">';
			$texte.="<tr>";
					$texte.='<td style="text-align: center;width:25%;color:rgb(31,75,125);">';
					$texte.='<i>Date</i>';
					$texte.='</td>';
					$texte.='<td style="text-align: center;width:50%;color:rgb(31,75,125);">';
					$texte.='<i>Libellé</i>';
					$texte.='</td>';
					$texte.='<td style="text-align: center;width:25%;color:rgb(31,75,125);">';
					$texte.='<i>Montant</i>';
					$texte.='</td>';
					
				$texte.="</tr>";
				
				foreach ($lstInfoHorsforfait as $unFrais) {
							$libellehors = $unFrais[3];
							$datehors = $unFrais[4];
							$montanthors = $unFrais[5];
			$texte.="<tr>";
					$texte.='<td style="text-align: center;width:25%;color:rgb(31,75,125);">';
					$texte.="$datehors";
					$texte.='</td>';
					$texte.='<td style="text-align: center;width:50%;color:rgb(31,75,125);">';
					$texte.="$libellehors";
					$texte.='</td>';
					$texte.='<td style="text-align: center;width:25%;color:rgb(31,75,125);">';
					$texte.="$montanthors";
					$texte.='</td>';
					
				$texte.="</tr>";		
				}
		$texte.="</table>";
		
		$texte.="<table>";
		
		$texte.="</table>";
		$texte.="<br><br>";
		$texte.='</fieldset>';
//creation du pdf via html2pdf
	$content="";
	$content.=$texte;

	$nom_fich="Fiches de frais.pdf";
	require_once('./html2pdf/html2pdf.class.php');
	try
	{
		$html2pdf = new HTML2PDF("P","A4","fr");
		$html2pdf->writeHTML($content);
		ob_end_clean();
		$html2pdf->Output($nom_fich,'D');
	}
	catch(HTML2PDF_exception $e)
	{
		echo $e;
		echo "probleme de genenration";
		exit;
	}
	?>