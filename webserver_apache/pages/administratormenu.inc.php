<?php $currenturl='http://'.apache_getenv("HTTP_HOST") . apache_getenv("REQUEST_URI"); ?>
       <img src="images\magazzino.png" weight="3%" height="3%" /><a href="<?php echo($currenturl . '&subpage=admin_user_manager'); ?>">Gestione utenti</a><br />
       <img src="images\scadenza.png" weight="3%" height="3%" /><a href="<?php echo($currenturl . '&subpage=admin_log_manager'); ?>">Gestione Log</a><br />
       <img src="images\scadenza.png" weight="3%" height="3%" /><a href="<?php echo($currenturl . '&subpage=admin_node_manager'); ?>">Gestione Nodo</a><br />