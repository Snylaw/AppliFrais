Visiteur : 
<select id="lstVisiteur" name="idVisSelect">
	<?php 
	foreach($visiteurFiche as $recup)  
	{
		$id = $recup["id"];
		$nom = $recup["nom"];
		$prenom = $recup["prenom"];
	?>
	<option name="idVisSelect" value="<?php echo $id; ?>"><?php echo $nom." ".$prenom; ?></option>
	<?php
	}
	?> 
</select>