<div class="divDroite">
	<div class="divBas">
    <center>
    <h1 style="text-align:center;padding-top:5%;padding-bottom:2%"> Rapport de visite </h1>
	<?php 
		$this->load->helper('form');
		echo form_open('MainController/recupCONSULTER_RAPPORT');
		
		echo "<select name=\"RAP_NUM\" class=\"custom-select\" style=\"margin-right:0.3%\" >";
		if(isset($numero)){
			foreach($numero as $key=>$value){
				foreach($value as $cle=>$vlr){
					echo "<option value=\"".$vlr."\">".$vlr."</option>";
				}
			}
		}
		echo "</select>";
		echo form_submit("valider","CHERCHER", array('class' => 'btn btn-primary'));
        echo form_close();
        $i = 0;
		if(isset($res)){
			echo "<table class=\"table table-bordered\" style=\"width:60%;margin-top:5%\">";
			foreach($res as $key=>$value){
				foreach($value as $cle=>$vlr){
					echo "<tr><td style='text-align:center; width:40%'>".$col_list[$i]."</td><td>".$vlr."</td></tr>";
                    $i += 1;
                }
			}
			echo "</table>";
		}
		$string = '</div></div>';
		echo $string;
	?>
    </center>
    