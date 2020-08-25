<div name="droite" class="divDroite">
	<div name="bas" class="divBas">
		<h1 style="text-align:center;padding-top:5%;padding-bottom:2%"> Médicaments </h1>
		<center>
		<form name="formMEDICAMENT" method="post" action="affichageMedicament">
			<div class="form-group">    
				<select id="lstMedicament" name="lstMedicament" class="custom-select">		
					<?php 
					foreach($listeMedicaments as $medicament)  
					{
						$nom = $medicament->MED_NOMCOMMERCIAL;
					?>
					<option name="idMedicSelect" value="<?php echo $nom; ?>"><?php echo $nom; ?></option>
					<?php
					}
					?> 
				</select>
				<input id="ok" type="submit" value="Afficher" size="20" title="Demande de renseignement sur le médicaments" class="btn btn-primary" />
    		</div>
		</form>
		<?php
			$i = 0;
			if(isset($leMedicament)){
				echo "<table class=\"table table-bordered\" style=\"width:50%;margin-top:5%\">";
				foreach($leMedicament as $key=>$value){
					echo "<tr><th style='text-align:center; width:25%'>".$col_list[$i]."</th><td>".$value."</td></tr>";
					$i += 1;
				}
				echo "</table>";
			}
		?>
		</center>
	</div>
</div>