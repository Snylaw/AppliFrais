<div id="contenu">
	<center>
		<!--<h2>Bienvenue dans l'espace Comptable</h2>-->
		<?php  ?>
			<table border="1" class="listeVisiteur">
				 <tr align="center">
					 <th colspan="3"><b>Listes des visiteurs</b></th>
				 </tr>
				 <tr align="center">
				 	 <td>Id</td>
					 <td>Nom</td>
					 <td>Prenom</td>
				 </tr>
			<?php			
				foreach ($lesVisiteurs as $recuPvisiteur){
			 ?>
				 <tr align=center >
				 	 <td><?php echo $recuPvisiteur['id']; ?></td>
					 <td><?php echo $recuPvisiteur['nom']; ?></td>
					 <td><?php echo $recuPvisiteur['prenom']; ?></td>
				 </tr>	
				<?php } ?>
	</table>
