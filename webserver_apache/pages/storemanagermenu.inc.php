<?php $currenturl='http://'.apache_getenv("HTTP_HOST") . apache_getenv("REQUEST_URI"); ?>
       <img src="images\magazzino.png" weight="3%" height="3%" /><a href="<?php echo($currenturl . '&subpage=storemanagerdashboard'); ?>">In Magazzino</a><br />
       <img src="images\gestionerichieste.png" weight="3%" height="3%" /><a href="<?php echo($currenturl . '&subpage=storemanager_requests'); ?>">Richiedi/spedisci</a><br />
       <img src="images\exit.png" weight="3%" height="3%" /><a href="./logout.php">Logout (<?php echo($username); ?>)</a><br />