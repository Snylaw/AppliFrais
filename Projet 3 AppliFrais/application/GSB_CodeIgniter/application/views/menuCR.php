<div name="haut" class="divHaut">
	<?php 
		$image_properties = array(
			'src'   => 'images/logo.png',
			'width' => '10%',
			'height' => '200%'
		);

		echo img($image_properties);
	?>
</div>
<div name="gauche" class="divGauche">
	<?php 
		echo "<h2>Gestion des visites</h2>";
		echo "<h3 style='margin-left:6%'>Outils</h3>"; 
	?>
	<ul><li><?php echo "<h4>Comptes-Rendus</h4>"; ?></li>
		<ul>
			<li><?php echo anchor('MainController/formRAPPORT_VISITE',' Nouveaux ', array('class' => 'list')); ?></li>
			<li><?php echo anchor('MainController/formCONSULTER_RAPPORT',' Consulter ', array('class' => 'list')); ?></li>
		</ul>
		<li><?php echo "<h4>Consulter</h4>"; ?></li>
		<ul>
			<li><?php echo anchor('MainController/choixMedicament',' Medicaments ', array('class' => 'list')); ?></li>
			<li><?php echo anchor('MainController/choixPraticien',' Praticiens ', array('class' => 'list')); ?></li>
			<li><?php echo anchor('MainController/choixVisiteur',' Autres visiteurs ', array('class' => 'list')); ?></li>
		</ul>	
	</ul>
</div>