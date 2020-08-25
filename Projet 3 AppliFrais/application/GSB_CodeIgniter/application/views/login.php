<div class="divDroite">
	<div class="divBas" style="margin-left:25%;margin-top:3.5%">
        <center>
        <h3 style="padding-top:20%">Connexion</h3>
        <?php 
            echo "<br/>";
			$this->load->helper('form');
			echo form_open("MainController/connection");
		?>

		<p>Login :</p>
		<?php 
        echo form_input("identifiant","", array('class' => 'form-control'));
        echo "<br/>";
		?>
		<p>Mot de passe :</p>
		<?php 
        echo form_input("password","", array('class' => 'form-control'));
        echo "<br/>";
		echo form_submit("login", "Connexion", array('class' => 'btn btn-primary'));
		echo form_close('</div>');
        ?>
        </center>
	</div>
</div>