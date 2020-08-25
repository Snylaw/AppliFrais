<script language="javascript">
		function selectionne(pValeur, pSelection,  pObjet) {
			//active l'objet pObjet du formulaire si la valeur sélectionnée (pSelection) est égale à la valeur attendue (pValeur)
			if (pSelection==pValeur) 
				{ formRAPPORT_VISITE.elements[pObjet].disabled=false; }
			else { formRAPPORT_VISITE.elements[pObjet].disabled=true; }
		}
        function ajoutLigne( pNumero){//ajoute une ligne de produits/qté à la div "lignes"     
			//masque le bouton en cours
			document.getElementById("but"+pNumero).setAttribute("hidden","true");	
			pNumero++;										//incrémente le numéro de ligne
            var laDiv=document.getElementById("lignes");	//récupère l'objet DOM qui contient les données
			var titre = document.createElement("label") ;	//crée un label
			laDiv.appendChild(titre) ;						//l'ajoute à la DIV
			titre.setAttribute("class","titre") ;			//définit les propriétés
			titre.innerHTML= "   Produit : ";
			var liste = document.createElement("select");	//ajoute une liste pour proposer les produits
			laDiv.appendChild(liste) ;
			liste.setAttribute("name","PRA_ECH"+pNumero) ;
			liste.setAttribute("class","zone");
			//remplit la liste avec les valeurs de la première liste construite en PHP à partir de la base
			liste.innerHTML=formRAPPORT_VISITE.elements["PRA_ECH1"].innerHTML;
			var qte = document.createElement("input");
			laDiv.appendChild(qte);
			qte.setAttribute("name","PRA_QTE"+pNumero);
			qte.setAttribute("size","2"); 
			qte.setAttribute("class","zone");
			qte.setAttribute("type","text");
			var bouton = document.createElement("input");
			laDiv.appendChild(bouton);
			//ajoute une gestion évenementielle en faisant évoluer le numéro de la ligne
			bouton.setAttribute("onClick","ajoutLigne("+ pNumero +");");
			bouton.setAttribute("type","button");
			bouton.setAttribute("value","+");
			bouton.setAttribute("class","zone");	
			bouton.setAttribute("id","but"+ pNumero);				
        }
    </script>
<div class="divDroite">
	<div class="divBas" style="height:100%">
		<?php 
			$this->load->helper('form');
			//<form name="formRAPPORT_VISITE" method="post" action="MainController/recupRAPPORT_VISITE">
			echo form_open('MainController/recupRAPPORT_VISITE');
		?>
			<h1 style="text-align:center;"> Rapport de visite </h1>
			<label class="titre">NUMERO :</label><input type="text" size="10" name="RAP_NUM" class="form-control" />
			<label class="titre">VISITEUR :</label><select name="VISITEUR" class="custom-select">
			<?php 
					if(isset($visiteur)){
						foreach($visiteur as $key=>$value){
							foreach($value as $cle=>$vlr){
								echo "<option value=\"".$vlr."\">".$vlr."</option>";
							}
						}
					}
				?></select><br/>
			<label class="titre">DATE VISITE :</label><input type="text" size="10" name="RAP_DATEVISITE" class="form-control" />
			<label class="titre">PRATICIEN :</label>
			<select  name="PRA_NUM" class="custom-select" >
				<?php 
					if(isset($prat)){
						foreach($prat as $key=>$value){
							foreach($value as $cle=>$vlr){
								echo "<option value=\"".$vlr."\">".$vlr."</option>";
							}
						}
					}
				?>
			</select><br/>
			<label class="titre">COEFFICIENT :</label><input type="text" size="6" name="PRA_COEFF" class="form-control" />
			<label class="titre">REMPLACANT :</label><input type="checkbox" class="zone" checked="checked" onClick="selectionne(true,this.checked,'PRA_REMPLACANT');"/>
			<select name="PRA_REMPLACANT" class="custom-select" >
			<?php if(isset($prat)){
						foreach($prat as $key=>$value){
							foreach($value as $cle=>$vlr){
								echo "<option value=\"".$vlr."\">".$vlr."</option>";
							}
						}
					}
			?></select><br/>
			<label class="titre">DATE (jj/mm/aaaa):</label><input type="text" size="19" name="RAP_DATE" class="form-control" />
			<label class="titre">MOTIF :</label><select  name="RAP_MOTIF" class="custom-select" onClick="selectionne('AUT',this.value,'RAP_MOTIFAUTRE');"><br/>
											<option value="PRD">Périodicité</option>
											<option value="ACT">Actualisation</option>
											<option value="REL">Relance</option>
											<option value="SOL">Sollicitation praticien</option>
											<option value="AUT">Autre</option>
										</select><!--<input type="text" name="RAP_MOTIFAUTRE" class="zone" disabled="disabled" />--><br/>
			<label class="titre">BILAN :</label><textarea rows="5" cols="50" name="RAP_BILAN" class="zone" ></textarea>
			<label class="titre" ><h3> Eléments présentés </h3></label><br/>
			<label class="titre" >PRODUIT 1 : </label><select name="PROD1" class="custom-select">
			<?php 
			if(isset($medicament)){
				foreach($medicament as $key=>$value){
					foreach($value as $cle=>$vlr){
						echo "<option value=\"".$vlr."\">".$vlr."</option>";
					}
				}
			}
			?>
			</select><br/>
			<label class="titre" >PRODUIT 2 : </label><select name="PROD2" class="custom-select">
			<?php
			if(isset($medicament)){
				foreach($medicament as $key=>$value){
					foreach($value as $cle=>$vlr){
						echo "<option value=\"".$vlr."\">".$vlr."</option>";
					}
				}
			}?>
			</select><br/>
			<label class="titre">DOCUMENTATION OFFERTE :</label><input name="RAP_DOC" type="checkbox" class="zone" checked="checked" />
			<label class="titre"><h3>Echanitllons</h3></label><br/>
			<div class="titre" id="lignes">
				<label class="titre" >Produit : </label><br/>
				<select name="PRA_ECH1" class="custom-select" style="width:100%"><option>Produits</option></select class="custom-select" style="width:100%"><input type="text" name="PRA_QTE1" size="2" class="form-control"/><br/>
				<input type="button" id="but1" value="+" onclick="ajoutLigne(1);" class="zone" />
			</div>
			<label class="titre">SAISIE DEFINITIVE :</label><input name="RAP_LOCK" type="checkbox" class="zone" checked="checked" />
			<label class="titre"></label><div class="zone"><input type="reset" value="Annuler" class="btn btn-primary" style="margin-right:0.5%"/><input type="submit" class="btn btn-primary"/><br/>
		</form>
	</div>
</div>