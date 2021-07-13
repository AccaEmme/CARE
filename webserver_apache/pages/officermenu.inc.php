<?php $currenturl = 'http://' . apache_getenv("HTTP_HOST") . apache_getenv("REQUEST_URI"); ?>
<img src="images\broadcastchat.png" weight="3%" height="3%" /><a href=<?php echo ($currenturl . '&subpage=officerdashboard'); ?>>Richieste</a><br />
       <img src="images\icon-settings.png" weight="3%" height="3%" /><a href="<?php echo($currenturl . '&subpage=profile&username='.$username); ?>">Profilo</a><br />
<img src="images\exit.png" weight="3%" height="3%" /><a href="./logout.php">Logout (<?php echo ($username); ?>)</a><br />