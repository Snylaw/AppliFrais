    <!-- Division pour le sommaire -->
    <div id="menuGauche">
    <div id="infosUtil">
    
    <?php if($_SESSION['role']==true) { ?>   
    </div>   
    <ul id="menuList">
    <li >
        Comptable :<br>
      <?php echo $_SESSION['prenom']."  ".$_SESSION['nom']  ?>
    </li>
          <li class="smenu">
              <a href="index.php?uc=etatFrais&action=ChoixFicheAValider" data-clicked="no" id="valider" title="Valider fiche de frais" style="color : #ff9933" onmouseover="this.style.background='#ff9933';this.style.color='#fff';"  onmouseout="this.style.background='';this.style.color='#ff9933';">Valider les fiches de frais</a>
      	   </li>

          <li class="smenu">
              <a href="index.php?uc=etatFrais&action=ChoixSuiviPaiementFiche" id="suivi" title="Suivie du paiement des fiches de frais" style='color : #ff9933' onmouseover="this.style.background='#ff9933';this.style.color='#fff';" onmouseout="this.style.background='';this.style.color='#ff9933';">Suivre le paiement des fiches de frais</a>
          </li>

          <li class="smenu">
              <a href="index.php?uc=connexion&action=deconnexion" title="Se déconnecter" style='color : #ff9933' onmouseover="this.style.background='#ff9933';this.style.color='#fff';" onmouseout="this.style.background='';this.style.color='#ff9933';">Déconnexion</a>
          </li>
        </ul>
      
  </div>


    <?php   
	  }
	  else{ 
    ?>
      </div> 
        <ul id="menuList">
      <li >
          Visiteur :<br>
        <?php echo $_SESSION['prenom']."  ".$_SESSION['nom']  ?>
      </li>
            <li class="smenu">
              <a href="index.php?uc=gererFrais&action=saisirFrais" title="Saisie fiche de frais ">Saisie fiche de frais</a>
            </li>
            <li class="smenu">
              <a href="index.php?uc=etatFrais&action=selectionnerMois" title="Consultation de mes fiches de frais">Mes fiches de frais</a>
            </li>
      <li class="smenu">
              <a href="index.php?uc=connexion&action=deconnexion" title="Se déconnecter">Déconnexion</a>
            </li>
          </ul>
          
      </div>
      <?php
  }

  ?>   