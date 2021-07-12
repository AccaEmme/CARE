<?php
//echo("ciao ".$token."<br><br>");

//$urlAPI = "http://localhost:8087/user/get/all";
$urlAPI = "http://localhost:8087/profile/get/" . $token . "/".$_GET['username'];

$authorization = "Authorization: Bearer ".$token;
    $ch = curl_init();
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json' , $authorization ));
    curl_setopt($ch,CURLOPT_URL,$urlAPI);
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "GET");
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_FOLLOWLOCATION, 1);
    $result = curl_exec($ch);
    curl_close($ch);
    //return json_decode($result);
//print_r(json_decode($result));

$profileObj = (object) json_decode($result);
//print_r( $profileObj );
?>

   <?php echo("<small><i>subtoken:" . $decodedToken . "</i></small>"); ?>
   <div align="center">
    <h2>Profile:</h2>
    <form action="" method="POST">
     <table style="border-collapse:separate; border:solid black 1px;  border-radius:6px; -moz-border-radius:6px;">
         <tr>
            <td align="right"><b>idUser</b></td>
            <td align="center"><input type="text" name="id_<?php echo $profileObj->idUser; ?>" value="<?php echo $profileObj->idUser; ?>" disabled /></td>
         </tr>
         <tr>
            <td align="right"><b>Username</b></td>
            <td align="center"><input type="text" name="user_<?php echo $profileObj->username; ?>" id="user_<?php echo $profileObj->username; ?>" value="<?php echo $profileObj->username; ?>" disabled /></td>
         </tr>
         <tr>
            <td align="right"><b>temppass</b></td>
            <td align="center"><input type="text" name="temppass_<?php echo $profileObj->username; ?>" value="<?php echo $profileObj->temppass; ?>" disabled /></td>
         </tr>
         <tr>
            <td align="right"><b>New Password(*)</b></td>
            <td align="center"><input type="password" name="pass1_<?php echo $profileObj->username; ?>" id="pass1_<?php echo $profileObj->username; ?>" value="" placeholder="Enter a valid new password" /></td>
         </tr>
         <tr>
            <td align="right"><b>Confirm Password</b></td>
            <td align="center"><input type="password" name="pass2_<?php echo $profileObj->username; ?>" id="pass2_<?php echo $profileObj->username; ?>" value="" placeholder="Retype new password again" /></td>
         </tr>
         <tr>
            <td align="right"><b>E-Mail</b></td>
            <td align="center"><input type="text" name="email_<?php echo $profileObj->username; ?>" id="email_<?php echo $profileObj->username; ?>" value="<?php echo $profileObj->email; ?>" /></td>
        </tr>
         <tr>
            <td align="right"><b>Role-Based Access Control</b></td>
            <td align="center">
                <select name="role" id="role" disabled>
			<?php
			   foreach($roles as $r) {
			     echo('<option value="' . $r . '"');
			     if( $r == $profileObj->userRole ) echo(' selected');
			     echo('>'.$r.'</option>');
			   }
			?>
                </select>
            </td>
         </tr>
         <tr>
            <td align="right"><b>creationDate</b></td>
            <td align="center"><input type="text" name="creationDate_<?php echo $profileObj->username; ?>" value="<?php echo date('Y-m-d', $profileObj->creationDate/1000); ?>" disabled /></td>
         </tr>
         <tr>
            <td align="right"><b>lastAccess</b></td>
            <td align="center"><input type="text" name="lastAccess_<?php echo $profileObj->username; ?>" value="<?php echo date('Y-m-d', $profileObj->lastAccess/1000); ?>" disabled /></td>
        </tr>
         <tr>
            <td align="right"><b>loginAttempts</b></td>
            <td align="center">
             <select name="loginAttempts_<?php echo $profileObj->username; ?>" id="loginAttempts_<?php echo $profileObj->username; ?>" width="100%" disabled >
              <?php
              $currentAttempts = $profileObj->loginAttempts;
              if($currentAttempts=="0") {
                echo('<option value="0" selected >0</option>');
              } else {
                echo('<option value="0">0</option>');
                echo('<option value="' . $currentAttempts . '" selected>' . $currentAttempts . '</option>');
              }
              ?>
             </select>
            </td>
         </tr>
         <tr>
            <td align="right"><b>activeUser</b></td>
            <td align="center">
              <?php
              $currentActiveUser = $profileObj->activeUser;
              ?>
             <select name="activeUser_<?php echo $profileObj->username; ?>" id="activeUser_<?php echo $profileObj->username; ?>" disabled >
              <option value="1"  <?php if($currentActiveUser=="1")  echo(" selected"); ?> >1:attivo</option>
              <option value="0"  <?php if($currentActiveUser=="0")  echo(" selected"); ?> >0:disabilitato</option>
              <option value="-1" <?php if($currentActiveUser=="-1") echo(" selected"); ?> >-1:blacklist</option>
              <option value="1"  <?php if($currentActiveUser=="-2") echo(" selected"); ?> >-2:deleted</option>
             </select>
            </td>
        </tr>
        <tr>
            <td align="center">
                <input type="reset" value="Annulla">
            </td>
            <td align="center">
                <input type="button" value="Salva" onclick="updateProfile(
									'http://localhost:8087/profile/set',
									'<?php echo($token); ?>',
									document.getElementById('user_<?php echo $profileObj->username; ?>').value,
									document.getElementById('pass1_<?php echo $profileObj->username; ?>').value,
									document.getElementById('pass2_<?php echo $profileObj->username; ?>').value,
									document.getElementById('email_<?php echo $profileObj->username; ?>').value
							);  setTimeout(function () { location.reload(1); }, 5000);">
            </td>
        </tr>
     </table>
     </form>
     <small><i><br>
      * Password must contain at least one digit [0-9].<br>
      * Password must contain at least one lowercase Latin character [a-z].<br>
      * Password must contain at least one uppercase Latin character [A-Z].<br>
      * Password must contain at least one special character like ! @ # & ( ).<br>
      * Password must contain a length of at least 8 characters and a maximum of 20 characters.<br>
     </i></small>
    </div>
</div>

<?php
unset($profileObj);
?>