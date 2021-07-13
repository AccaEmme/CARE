<head>
  <link rel="stylesheet" href="./css/admindashboard.css">
</head>

<?php
//echo("ciao ".$token."<br><br>");

function httpRequest($urlAPI, $token, $method, $jsonBody)
{
  //$urlAPI = "http://localhost:8087/user/get/all";
  //$urlAPI = "http://localhost:8087/user/get/notdeleted";
  $authorization = "Authorization: Bearer " . $token;
  $ch = curl_init();
  curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json', $authorization));
  curl_setopt($ch, CURLOPT_URL, $urlAPI);
  curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "GET");
  curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
  curl_setopt($ch, CURLOPT_FOLLOWLOCATION, 1);
  $result = curl_exec($ch);
  curl_close($ch);
  //return json_decode($result);
  //print_r(json_decode($result));

  return ((array) json_decode($result));
}

$usersArray = httpRequest("http://localhost:8087/user/get/notdeleted", $token, "GET", null);
//echo("user: ".$usersArray['idUser'] . " token:" . $usersArray['username']);
foreach (array_keys($usersArray) as $key) {
  //echo("user: ".$usersArray[$key]['idUser'] . " token:" . $usersArray[$key]['username'] . "<br>");
  // print_r($usersArray[0]);
  /*
 echo(	  "<br>"
	. "userId:"
	. $usersArray[$key]->idUser 
	. " - username: "
	. $usersArray[$key]->username
	. " - role: "
	. $usersArray[$key]->userRole
	. " - creationDate: "
	. $usersArray[$key]->creationDate
	. "<br>");
*/
  //print_r($arr);
}

//unset($usersArray);

?>

<div align="center">
  <fieldset>
    <legend><img src=""><a><b>Utenti</b></a></legend>
    <table>
      <tr>
        <td align="center"><b>idUser</b></td>
        <td align="center"><b>Username</b></td>
        <td align="center"><b>Password</b></td>
        <td align="center"><b>temppass</b></td>
        <td align="center"><b>E-Mail</b></td>
        <td align="center"><b>Role-Based Access Control</b></td>
        <td align="center"><b>creationDate</b></td>
        <td align="center"><b>lastAccess</b></td>
        <td align="center"><b>loginAttempts</b></td>
        <td align="center"><b>activeUser</b></td>
        <td align="center"><b>Actions</b></td>
      </tr>
      <!-- START: new user fields -->
      <tr>
        <td><input type="text" class="textcss" name="newiduser" id="newiduser" value="#" disabled /></td>
        <td><input type="text" class="textcss" name="newusername" id="newusername" /></td>
        <td><input type="text" class="textcss" name="newpassword" id="newpassword" placeholder="if null auto generated" /></td>
        <td><input type="text" class="textcss" disabled /></td>
        <td><input type="text" class="textcss" name="newemail" id="newemail" placeholder="e-mail here" /></td>
        <td>
          <select name="newrole" class="textcss" id="newrole">
            <?php
            foreach ($roles as $r) {
              echo ('<option value="' . $r . '"');
              echo ('>' . $r . '</option>');
            }
            ?>
          </select>
        </td>
        <td><input type="text" class="textcss" value="<?php echo (date('Y-m-d')); ?>" disabled /></td>
        <td><input type="text" class="textcss" value="1900-01-01" disabled /></td>
        <td align="center">
          <select name="newloginattempts" class="textcss" id="newloginattempts" disabled>
            <option value="0">0</option>
            <option value="1">1</option>
            <option value="2">2</option>
          </select>
        <td style="padding-top: 15px; padding-bottom:15px; text-align: center;">
          <select name="newactiveuser" class="textcss" id="newactiveuser" disabled>
            <option value="1">1:attivo</option>
            <option value="0">0:disabilitato</option>
            <option value="-1">-1:blacklist</option>
            <option value="1">-2:deleted</option>
          </select>
        </td>
        <td style="padding-top: 15px; padding-bottom:15px; text-align: center;">
          <input type="text" class="textcss" name="addUserURL" id="addUserURL" value="http://localhost:8087/register" hidden="yes" />
          <input type="submit" class="myButton" value="Crea" onclick="addUser(document.getElementById('addUserURL').value, '<?php echo ($token); ?>', document.getElementById('newusername').value, document.getElementById('newpassword').value, document.getElementById('newemail').value, document.getElementById('newrole').value, document.getElementById('newloginattempts').value, document.getElementById('newactiveuser').value); setTimeout(function () { location.reload(1); }, 5000);">
        </td>
      </tr>
      <!-- END: new user fields -->
      <?php foreach (array_keys($usersArray) as $key) { ?>
        <tr>
          <td style="text-align: center;"><input type="text" class="textcss" name="id_<?php echo $usersArray[$key]->idUser; ?>" id="id_<?php echo $usersArray[$key]->idUser; ?>" value="<?php echo $usersArray[$key]->idUser;   ?>" disabled /></td>
          <td style="text-align: center;"><input type="text" class="textcss" name="user_<?php echo $usersArray[$key]->username; ?>" id="user_<?php echo $usersArray[$key]->username; ?>" value="<?php echo $usersArray[$key]->username; ?>" disabled /></td>
          <td style="text-align: center;"><input type="password" class="textcss" name="pass_<?php echo $usersArray[$key]->username; ?>" id="pass_<?php echo $usersArray[$key]->username; ?>" value="<?php echo $usersArray[$key]->password; ?>" disabled /></td>
          <td style="text-align: center;"><input type="text" class="textcss" name="temppass_<?php echo $usersArray[$key]->username; ?>" id="temppass_<?php echo $usersArray[$key]->username; ?>" value="<?php echo $usersArray[$key]->temppass; ?>" /></td>
          <td style="text-align: center;"><input type="text" class="textcss" name="email_<?php echo $usersArray[$key]->username; ?>" id="email_<?php echo $usersArray[$key]->username; ?>" value="<?php echo $usersArray[$key]->email;    ?>" /></td>
          <td style="text-align: center;">
            <select name="role_<?php echo $usersArray[$key]->username; ?>" class="textcss" id="role_<?php echo $usersArray[$key]->username; ?>">
              <?php
              foreach ($roles as $r) {
                echo ('<option value="' . $r . '"');
                if ($r == $usersArray[$key]->userRole) echo (' selected');
                echo ('>' . $r . '</option>');
              }
              ?>
            </select>
          </td>
          <td style="text-align: center;"><input type="text" class="textcss" name="creationDate_<?php echo $usersArray[$key]->username; ?>" id="creationDate_<?php echo $usersArray[$key]->username; ?>" value="<?php echo date('Y-m-d', $usersArray[$key]->creationDate / 1000); ?>" disabled /></td>
          <td style="text-align: center;"><input type="text" class="textcss" name="lastAccess_<?php echo $usersArray[$key]->username; ?>" id="lastAccess_<?php echo $usersArray[$key]->username; ?>" value="<?php echo date('Y-m-d', $usersArray[$key]->lastAccess / 1000); ?>" disabled /></td>
          <td style="text-align: center;">
            <select name="loginAttempts_<?php echo $usersArray[$key]->username; ?>" class="textcss" id="loginAttempts_<?php echo $usersArray[$key]->username; ?>" width="100%">
              <?php
              $currentAttempts = $usersArray[$key]->loginAttempts;
              if ($currentAttempts == "0") {
                echo ('<option value="0" selected >0</option>');
              } else {
                echo ('<option value="0">0</option>');
                echo ('<option value="' . $currentAttempts . '" selected>' . $currentAttempts . '</option>');
              }
              ?>
            </select>
          </td>
          <td style="text-align: center;">
            <?php
            $currentActiveUser = $usersArray[$key]->activeUser;
            ?>
            <select name="activeUser_<?php echo $usersArray[$key]->username; ?>" class="textcss" id="activeUser_<?php echo $usersArray[$key]->username; ?>">
              <option value="1" <?php if ($currentActiveUser == "1")  echo (" selected"); ?>>1:attivo</option>
              <option value="0" <?php if ($currentActiveUser == "0")  echo (" selected"); ?>>0:disabilitato</option>
              <option value="-1" <?php if ($currentActiveUser == "-1") echo (" selected"); ?>>-1:blacklist</option>
              <option value="-2" <?php if ($currentActiveUser == "-2") echo (" selected"); ?>>-2:deleted</option>
            </select>
          </td>
          <td style="text-align: center;">
            <input type="button" class="myButton" value="Salva" onclick="updateUser(
										'http://localhost:8087/user/update',
										'<?php echo ($token); ?>',
										document.getElementById('id_<?php echo $usersArray[$key]->idUser; ?>').value,
										document.getElementById('user_<?php echo $usersArray[$key]->username; ?>').value,
										document.getElementById('temppass_<?php echo $usersArray[$key]->username; ?>').value,
										document.getElementById('email_<?php echo $usersArray[$key]->username; ?>').value,
										document.getElementById('role_<?php echo $usersArray[$key]->username; ?>').value,
										document.getElementById('loginAttempts_<?php echo $usersArray[$key]->username; ?>').value,
										document.getElementById('activeUser_<?php echo $usersArray[$key]->username; ?>').value
									      );
									      setTimeout(function () { location.reload(1); }, 2000);
                ">
            <input type="button" class="myButtonNeg" value="Elimina" onclick="deleteUser('<?php echo ('http://localhost:8087/user/delete/username/' . $usersArray[$key]->username); ?>', '<?php echo ($token); ?>'); setTimeout(function () { location.reload(1); }, 2000);">
            <input type="button" class="myButton" value="ResetPassword" onclick="resetUserPass('<?php echo ('http://localhost:8087/user/patch/resetpassword/username/' . $usersArray[$key]->username); ?>', '<?php echo ($token); ?>'); setTimeout(function () { location.reload(1); }, 2000);">
          </td>
        </tr>
      <?php
      }
      ?>
    </table>
  </fieldset>
</div>

<br /><br />
<?php
$usersArray = httpRequest("http://localhost:8087/user/get/deleted", $token, "GET", null);
?>
<div align="center">
  <fieldset>
    <legend><img src=""><a><b>Utenti cancellati</b></a></legend>
    <table>
      <tr>
        <td align="center"><b>idUser</b></td>
        <td align="center"><b>Username</b></td>
        <td align="center"><b>Password</b></td>
        <td align="center"><b>temppass</b></td>
        <td align="center"><b>E-Mail</b></td>
        <td align="center"><b>Role-Based Access Control</b></td>
        <td align="center"><b>creationDate</b></td>
        <td align="center"><b>lastAccess</b></td>
        <td align="center"><b>loginAttempts</b></td>
        <td align="center"><b>activeUser</b></td>
        <td align="center"><b>Actions</b></td>
      </tr>
      <?php foreach (array_keys($usersArray) as $key) { ?>
        <tr>
          <td style="text-align: center;"><input type="text" class="textcss" name="id_<?php echo $usersArray[$key]->idUser; ?>" id="id_<?php echo $usersArray[$key]->idUser; ?>" value="<?php echo $usersArray[$key]->idUser; ?>" disabled /></td>
          <td style="text-align: center;"><input type="text" class="textcss" name="user_<?php echo $usersArray[$key]->username; ?>" id="user_<?php echo $usersArray[$key]->username; ?>" value="<?php echo $usersArray[$key]->username; ?>" disabled /></td>
          <td style="text-align: center;"><input type="password" class="textcss" name="pass_<?php echo $usersArray[$key]->username; ?>" id="pass_<?php echo $usersArray[$key]->username; ?>" value="<?php echo $usersArray[$key]->password; ?>" disabled /></td>
          <td style="text-align: center;"><input type="text" class="textcss" name="temppass_<?php echo $usersArray[$key]->username; ?>" id="temppass_<?php echo $usersArray[$key]->username; ?>" value="<?php echo $usersArray[$key]->temppass; ?>" disabled /></td>
          <td style="text-align: center;"><input type="text" class="textcss" name="email_<?php echo $usersArray[$key]->username; ?>" id="email_<?php echo $usersArray[$key]->username; ?>" value="<?php echo $usersArray[$key]->email; ?>" disabled /></td>
          <td style="text-align: center;">
            <select name="role_<?php echo $usersArray[$key]->username; ?>" id="role_<?php echo $usersArray[$key]->username; ?>" disabled>
              <?php
              foreach ($roles as $r) {
                echo ('<option value="' . $r . '"');
                if ($r == $usersArray[$key]->userRole) echo (' selected');
                echo ('>' . $r . '</option>');
              }
              ?>
            </select>
          </td>
          <td style="text-align: center;"><input type="text" class="textcss" name="creationDate_<?php echo $usersArray[$key]->username; ?>" id="creationDate_<?php echo $usersArray[$key]->username; ?>" value="<?php echo date('Y-m-d', $usersArray[$key]->creationDate / 1000); ?>" disabled /></td>
          <td style="text-align: center;"><input type="text" class="textcss" name="lastAccess_<?php echo $usersArray[$key]->username; ?>" id="lastAccess_<?php echo $usersArray[$key]->username; ?>" value="<?php echo date('Y-m-d', $usersArray[$key]->lastAccess / 1000); ?>" disabled /></td>
          <td style="text-align: center;">

            <select name="loginAttempts_<?php echo $usersArray[$key]->username; ?>" id="loginAttempts_<?php echo $usersArray[$key]->username; ?>" width="100%" disabled>
              <?php
              $currentAttempts = $usersArray[$key]->loginAttempts;
              if ($currentAttempts == "0") {
                echo ('<option value="0" selected >0</option>');
              } else {
                echo ('<option value="0">0</option>');
                echo ('<option value="' . $currentAttempts . '" selected>' . $currentAttempts . '</option>');
              }
              ?>
            </select>
          </td>
          <td style="text-align: center;">
            <?php
            $currentActiveUser = $usersArray[$key]->activeUser;
            ?>
            <select name="activeUser_<?php echo $usersArray[$key]->username; ?>" class="textcss" id="activeUser_<?php echo $usersArray[$key]->username; ?>">
              <option value="1" <?php if ($currentActiveUser == "1")  echo (" selected"); ?>>1:attivo</option>
              <option value="0" <?php if ($currentActiveUser == "0")  echo (" selected"); ?>>0:disabilitato</option>
              <option value="-1" <?php if ($currentActiveUser == "-1") echo (" selected"); ?>>-1:blacklist</option>
              <option value="1" <?php if ($currentActiveUser == "-2") echo (" selected"); ?>>-2:deleted</option>
            </select>
          </td>
          <td style="text-align: center;">
            <input type="button" class="myButton" value="Salva" onclick="updateUser(
										'http://localhost:8087/user/update',
										'<?php echo ($token); ?>',
										document.getElementById('id_<?php echo $usersArray[$key]->idUser; ?>').value,
										document.getElementById('user_<?php echo $usersArray[$key]->username; ?>').value,
										document.getElementById('temppass_<?php echo $usersArray[$key]->username; ?>').value,
										document.getElementById('email_<?php echo $usersArray[$key]->username; ?>').value,
										document.getElementById('role_<?php echo $usersArray[$key]->username; ?>').value,
										document.getElementById('loginAttempts_<?php echo $usersArray[$key]->username; ?>').value,
										document.getElementById('activeUser_<?php echo $usersArray[$key]->username; ?>').value
									      );
									      setTimeout(function () { location.reload(1); }, 5000);
                ">
          </td>
        </tr>
      <?php
      }
      ?>
    </table>
  </fieldset>
</div>