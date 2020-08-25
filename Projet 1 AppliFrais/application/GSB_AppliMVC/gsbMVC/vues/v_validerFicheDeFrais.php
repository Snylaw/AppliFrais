<div id="contenu">
  <h2>Valider fiche de frais :</h2>
  <h3>Selectionner un mois et un visiteur : </h3>
  <center>
  <form action="index.php?uc=etatFrais&action=voirFicheFraisVisiteur" method="post">
    <div class="corpsForm">    
      
      <!-- Vue liste Mois -->
      <?php
        include("v_listeMoisValidation.php");
      ?>

      <!-- Vue liste Visiteur -->
      <?php
        include("v_listeVisiteur.php");
      ?>

    </div>
    <div class="piedForm">
      <center>
      <p>
        <input id="ok" type="submit" value="Valider" size="20" />
      </p>
      </center>
    </div>
  </form>
  </center>
</body>
</html>
