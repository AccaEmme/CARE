Incomplete:
si necessita di un metodo che, passando una {username} verifica che sia la stessa del token e possa elaborarne sia la GET sia la PUT?
mi serve per consentire a ogni utente di aggiornare il proprio profilo, sennò la password temporanea è inutile

   <div align="center">
    <h2>Profile:</h2>
    <form action="" method="POST">
     <table style="border-collapse:separate; border:solid black 1px;  border-radius:6px; -moz-border-radius:6px;">
         <tr>
            <td align="right"><b>idUser</b></td>
            <td align="center"><input type="text" name="id_<?php echo $usersArray[$key]->idUser; ?>" value="<?php echo $usersArray[$key]->idUser; ?>" disabled /></td>
         </tr>
         <tr>
            <td align="right"><b>Username</b></td>
            <td align="center"><input type="text" name="user_<?php echo $usersArray[$key]->username; ?>" value="<?php echo $usersArray[$key]->username; ?>" /></td>
         </tr>
         <tr>
            <td align="right"><b>temppass</b></td>
            <td align="center"><input type="text" name="temppass_<?php echo $usersArray[$key]->username; ?>" value="<?php echo $usersArray[$key]->temppass; ?>" disabled /></td>
         </tr>
         <tr>
            <td align="right"><b>Password(*)</b></td>
            <td align="center"><input type="password" name="pass_<?php echo $usersArray[$key]->username; ?>" value="" placeholder="Enter a valid new password" /></td>
         </tr>
         <tr>
            <td align="right"><b>Confirm Password</b></td>
            <td align="center"><input type="password" name="pass_<?php echo $usersArray[$key]->username; ?>" value="" placeholder="Retype password again" /></td>
         </tr>
         <tr>
            <td align="right"><b>E-Mail</b></td>
            <td align="center"><input type="text" name="email_<?php echo $usersArray[$key]->username; ?>" value="<?php echo $usersArray[$key]->email; ?>" /></td>
        </tr>
         <tr>
            <td align="right"><b>Role-Based Access Control</b></td>
            <td align="center">
                <select name="role" id="role" disabled >
			<?php
			   foreach($roles as $r) {
			     echo('<option value="' . $r . '"');
			     if( $r == $usersArray[$key]->userRole ) echo(' selected');
			     echo('>'.$r.'</option>');
			   }
			?>
                </select>
            </td>
         </tr>
         <tr>
            <td align="right"><b>creationDate</b></td>
            <td align="center"><input type="text" name="creationDate_<?php echo $usersArray[$key]->username; ?>" value="<?php echo date('Y-m-d', $usersArray[$key]->creationDate/1000); ?>" disabled /></td>
         </tr>
         <tr>
            <td align="right"><b>lastAccess</b></td>
            <td align="center"><input type="text" name="lastAccess_<?php echo $usersArray[$key]->username; ?>" value="<?php echo date('Y-m-d', $usersArray[$key]->lastAccess/1000); ?>" disabled /></td>
        </tr>
         <tr>
            <td align="right"><b>loginAttempts</b></td>
            <td align="center">
             <select name="loginAttempts_<?php echo $usersArray[$key]->username; ?>" id="loginAttempts_<?php echo $usersArray[$key]->username; ?>" width="100%" disabled >
              <?php
              $currentAttempts = $usersArray[$key]->loginAttempts;
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
              $currentActiveUser = $usersArray[$key]->activeUser;
              ?>
             <select name="activeUser_<?php echo $usersArray[$key]->username; ?>" id="activeUser_<?php echo $usersArray[$key]->username; ?>" disabled >
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
                <input type="button" value="Salva">
            </td>
        </tr>
     </table>
     </form>
     <small><i>
      * Password must contain at least one digit [0-9].<br>
      * Password must contain at least one lowercase Latin character [a-z].<br>
      * Password must contain at least one uppercase Latin character [A-Z].<br>
      * Password must contain at least one special character like ! @ # & ( ).<br>
      * Password must contain a length of at least 8 characters and a maximum of 20 characters.<br>
     </i></small>
    </div>
</div>
    