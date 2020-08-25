<!-- Liste Mois -->
Mois :
<select id="lstMois" name="lstMois">
	<?php
	foreach ($lesMois as $unMois)
	{
		$mois = $unMois['mois'];
	}
	$numAnnee =substr( $mois,0,4);
	$numMois =substr( $mois,4,2);
	?>
	<option selected value="<?php echo $mois ?>"><?php echo  $numMois."/".$numAnnee ?> </option>
</select>