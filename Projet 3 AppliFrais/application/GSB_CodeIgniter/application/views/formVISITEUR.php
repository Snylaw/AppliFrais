<div name="droite" class="divDroite">
	<div name="bas" class="divBas">
		<h1 style="text-align:center;padding-top:5%;padding-bottom:2%"> Visiteurs </h1>
		<center>
		<form name="formVISITEUR" method="post" action="affichageVisiteur">
			<div class="form-group">    
				<select id="lstVisiteur" name="lstVisiteur" class="custom-select">		
					<?php 
					foreach($listeVisiteurs as $visiteur)  
					{
						$id = $visiteur->VIS_MATRICULE;
						$nom = $visiteur->VIS_NOM;
						$prenom = $visiteur->Vis_PRENOM;
					?>
					<option name="idVisitSelect" value="<?php echo $id; ?>"><?php echo $nom. " ".$prenom; ?></option>
					<?php
					}
					?> 
				</select>
				<input id="ok" type="submit" value="Afficher" size="20" title="Demande de renseignement sur le visiteur" class="btn btn-primary" />
    		</div>
		</form>
		<?php
			$i = 0;
			if(isset($leVisiteur)){
				echo "<table class=\"table table-bordered\" style=\"width:50%;margin-top:5%\">";
				foreach($leVisiteur as $key=>$value){
					echo "<tr><th style='text-align:center; width:40%'>".$col_list[$i]."</th><td>".$value."</td></tr>";
					$i += 1;
				}
				echo "</table>";
			}
		?>
		</center>
	</div>
</div>