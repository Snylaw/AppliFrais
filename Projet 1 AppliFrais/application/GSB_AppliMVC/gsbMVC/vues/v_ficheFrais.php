<div id="contenu">
    <h2>Fiche de frais du mois <?php echo $numMois."-".$numAnnee ?>: </h2>
    <div class="encadre">
    <p>
        Etat : <?php echo $libEtat ?> et mise en paiement depuis le <?php echo $numMois."-".$numAnnee ;?> <br> Montant validé : <?php echo $montantValide." €"?>           
    </p>
    <form method="POST"  action="index.php?uc=gererFrais&action=validerFiche&idVisSelect=<?php echo $idVisSelect;?>&moisSelected=<?php echo $leMois;?>">
    <table class="listeLegere">
       <caption>Eléments forfaitisés </caption>
        <tr>
         <?php
         foreach ( $lesFraisForfait as $unFraisForfait ) 
     {
      $libelle = $unFraisForfait['libelle'];
    ?>  
      <th> <?php echo $libelle?></th>
     <?php
        }
    ?>
    </tr>
        <tr>
        <?php
          foreach (  $lesFraisForfait as $unFraisForfait){
        $quantite = $unFraisForfait['quantite'];
       
    ?>
                <td class="qteForfait"><input type="text" name="quantite" value="<?php echo $quantite ?>" size="14"> </td>
     <?php
          }
    ?>
    </tr>
    </table>
    
    <table class="listeLegere">
       <caption>Descriptif des éléments hors forfait -<input type="text" size="2" name="nbJustifs" required="required">- justificatifs reçus -
       </caption>
             <tr>
                <th class="date">Date</th>
				        <th class="libelle">Libellé</th>  
                <th class="montant">Montant</th>  
                <th class="action">&nbsp;</th>                
             </tr>
        <?php      
        foreach ($lesFraisHorsForfait as $unFraisHorsForfait)
        {
          $libelle = $unFraisHorsForfait['libelle'];
          $date = $unFraisHorsForfait['date'];
          $montant=$unFraisHorsForfait['montant'];
          $id = $unFraisHorsForfait['id'];
    ?>
             <tr>
                <td><input type="text" name="date" value="<?php echo $date ?>"  size="14"></td>
                <td><input type="text" name="libelle" value="<?php echo $libelle ?>"  size="14"></td>
                <td><input type="text" name="montant" value="<?php echo $montant ?>"  size="14"></td>
                <td><a href="index.php?uc=gererFrais&action=supprimerFrais&idFrais=<?php echo $id ?>" 
				onclick="return confirm('Voulez-vous vraiment supprimer ce frais?');">Supprimer ce frais</a></td>
             </tr>
        <?php 
        }
    ?>
    </table>
    <center>
    <input type="submit" value="Valider cette fiche" />
    </center>
  </form>
  </div>
      
