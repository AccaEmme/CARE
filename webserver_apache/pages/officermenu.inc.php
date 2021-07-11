<?php $currenturl = 'http://' . apache_getenv("HTTP_HOST") . apache_getenv("REQUEST_URI"); ?>
<img src="images\scadenza.png" weight="3%" height="3%" /><a href=<?php echo ($currenturl . '&subpage=officerdashboard'); ?>>Richieste</a><br />
<img src="images\scadenza.png" weight="3%" height="3%" /><a href="<?php echo($currenturl . '&subpage=officer_personal_profile'); ?>">Profilo</a><br />
<img src="images\scadenza.png" weight="3%" height="3%" /><a href="./logout.php">Logout (<?php echo ($username); ?>)</a><br />