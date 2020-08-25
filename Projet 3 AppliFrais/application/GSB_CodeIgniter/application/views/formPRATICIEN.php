<div name="droite" class="divDroite">
	<div name="bas" class="divBas">
		<h1 style="text-align:center;padding-top:5%;padding-bottom:2%"> Praticiens </h1>
		<center>
		<form name="formPRATICIEN" method="post" action="affichagePraticien">
			<div class="form-group">    
				<select id="lstPraticien" name="lstPraticien" class="custom-select">		
					<?php 
					foreach($listePraticiens as $praticien)  
					{
						$id = $praticien->PRA_NUM;
						$nom = $praticien->PRA_NOM;
						$prenom = $praticien->PRA_PRENOM;
					?>
					<option name="idPraticSelect" value="<?php echo $id; ?>"><?php echo $nom. " ".$prenom; ?></option>
					<?php
					}
					?> 
				</select>
				<input id="ok" type="submit" value="Afficher" size="20" title="Demande de renseignement sur le praticien" class="btn btn-primary" />
    		</div>
		</form>
		<?php
			$i = 0;
			if(isset($lePraticien)){
				echo "<table class=\"table table-bordered\" style=\"width:50%;margin-top:5%\">";
				foreach($lePraticien as $key=>$value){
					echo "<tr><th style='text-align:center; width:40%'>".$col_list[$i]."</th><td>".$value."</td></tr>";
					$i += 1;
				}
				echo "</table>";
			}
		?>
		</center>
	</div>
</div>