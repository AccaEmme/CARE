<?php $currenturl='http://'.apache_getenv("HTTP_HOST") . apache_getenv("REQUEST_URI"); ?> 
        <img src="images\scadenza.png" weight="3%" height="3%" /><a href=<?php echo($currenturl . '&subpage=officerdashboard'); ?> >Richieste</a><br />
        <img src="images\exit.png" weight="3%" height="3%" /><a href="login.php">Logout <small>(206-001)</small></a><br />