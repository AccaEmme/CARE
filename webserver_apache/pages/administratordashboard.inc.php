<?php
//echo("ciao ".$token."<br><br>");

$urlAPI = "http://localhost:8087/user/get/all";
$authorization = "Authorization: Bearer ".$token;
    $ch = curl_init();
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json' , $authorization ));
    curl_setopt($ch,CURLOPT_URL,$urlAPI);
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "GET");
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
//    curl_setopt($ch, CURLOPT_POSTFIELDS,$post);
    curl_setopt($ch, CURLOPT_FOLLOWLOCATION, 1);
    $result = curl_exec($ch);
    curl_close($ch);
    //return json_decode($result);
//print_r(json_decode($result));

$usersArray = (array) json_decode($result);
//echo("user: ".$usersArray['idUser'] . " token:" . $usersArray['username']);
foreach (array_keys($usersArray ) as $key) {
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

    <h2>Local Users:</h2>
<!--    <form action="" method="POST"> -->
     <table>
         <tr>
            <td align="center"><b>idUser</b></td>
            <td align="center"><b>Username</b></td>
            <td align="center"><b>Password</b></td>
            <td align="center"><b>temppass</b></td>
            <td align="center"><b>E-Mail</b></td>
            <td align="center"><b>Ruolo</b></td>
            <td align="center"><b>creationDate</b></td>
            <td align="center"><b>lastAccess</b></td>
            <td align="center"><b>loginAttempts</b></td>
            <td align="center"><b>activeUser</b></td>
            <td align="center"><b>Actions</b></td>
        </tr>
        <tr>
<?php foreach (array_keys($usersArray ) as $key) { ?>
            <td><input type="text" name="id_<?php echo $usersArray[$key]->idUser; ?>" value="<?php echo $usersArray[$key]->idUser; ?>" /></td>
            <td><input type="text" name="user_<?php echo $usersArray[$key]->username; ?>" value="<?php echo $usersArray[$key]->username; ?>" /></td>
            <td><input type="password" name="pass_<?php echo $usersArray[$key]->username; ?>" value="<?php echo $usersArray[$key]->password; ?>" disabled /></td>
            <td><input type="text" name="temppass_<?php echo $usersArray[$key]->username; ?>" value="<?php echo $usersArray[$key]->temppass; ?>" /></td>
            <td><input type="text" name="email_<?php echo $usersArray[$key]->username; ?>" value="<?php echo $usersArray[$key]->email; ?>" /></td>
            <td>
                <select name="role" id="role">
                    <optgroup label="Role-Based Access Control">
			<?php
			   foreach($roles as $r) {
			     echo('<option value="' . $r . '"');
			     if( $r == $usersArray[$key]->userRole ) echo(' selected');
			     echo('>'.$r.'</option>');
			   }
			?>
                    </optgroup>
                </select>
            </td>
            <td><input type="text" name="creationDate_<?php echo $usersArray[$key]->username; ?>" value="<?php echo $usersArray[$key]->creationDate; ?>" /></td>
            <td><input type="text" name="creationDate_<?php echo $usersArray[$key]->username; ?>" value="<?php echo $usersArray[$key]->lastAccess; ?>" /></td>
            <td><input type="text" name="creationDate_<?php echo $usersArray[$key]->username; ?>" value="<?php echo $usersArray[$key]->loginAttempts; ?>" /></td>
            <td><input type="text" name="creationDate_<?php echo $usersArray[$key]->username; ?>" value="<?php echo $usersArray[$key]->activeUser; ?>" /></td>
            <td>
                <input type="submit" value="Salva">
                <input type="button" value="Elimina">
            </td>
        </tr>
<?php
}
?>
	<!-- START: new user fields -->
        <tr>
            <td><input type="text" name="newiduser" id="newiduser" value="#" disabled /></td>
            <td><input type="text" name="newusername" id="newusername" /></td>
            <td><input type="text" name="newpassword" id="newpassword" placeholder="if null auto generated" /></td>
            <td><input type="text" disabled/></td>
            <td><input type="text" name="newemail" id="newemail" placeholder="e-mail here" /></td>
            <td>
                <select name="newrole" id="newrole">
  			<?php
			   foreach($roles as $r) {
			     echo('<option value="' . $r . '"');
			     echo('>'.$r.'</option>');
			   }
			?>
                </select>
            </td>
            <td><input type="text" value="<?php echo(date('Y-m-d')); ?>" disabled /></td>
            <td><input type="text" value="1900-01-01" disabled /></td>
            <td><input type="text" name="newloginattempts" id="newloginattempts" value="0" /></td>
            <td><select name="newactiveuser" id="newactiveuser"><option value="1">1:attivo</option><option value="0">0:disabilitato</option><option value="-1">-1:blacklist</option><option value="1">-2:deleted</option></select></td>
            <td>
		<input type="text" name="addUserURL" id="addUserURL" value="http://localhost:8087/register" hidden="yes" />
                <input type="submit" value="Crea" onclick="addUser(document.getElementById('addUserURL').value, '<?php echo($token); ?>', document.getElementById('newusername').value, document.getElementById('newpassword').value, document.getElementById('newemail').value, document.getElementById('newrole').value, document.getElementById('newloginattempts').value, document.getElementById('newactiveuser').value)">
            </td>
        </tr>
	<!-- END: new user fields -->


     </table>

<!-- ----------------OLD-------------------- -->
    <div align="center">
    <h2>old Local Users:</h2>
    <form action="#">
     <table>
         <tr>
            <td align="center"><b>Username</b></td>
            <td align="center"><b>Password</b></td>
            <td align="center"><b>Ruolo</b></td>
            <td align="center"><b>Actions</b></td>
        </tr>
        <tr>
            <td><input type="text" name="user_GCanfora" value="DBA" disabled /></td>
            <td><input type="password" name="pass_GCanfora" value="whoknows?" disabled /></td>
            <td>
                <select name="role" id="role">
                    <optgroup label="Database">
                        <option value="DBA" selected disabled>DBA</option>
                        <option value="DBConfigurer" disabled>DBConfigurer</option>
                        <option value="DBWriter" disabled>DBWriter</option>
                        <option value="DBViewer" disabled>DBViewer</option>
                        <option value="DBSync" disabled>DBSync</option>
                        <option value="DBBackup" disabled>DBBackup</option>
                    </optgroup>
                    <optgroup label="Role-Based Access Control">
                        <option value="Administrator">Administrator</option>
                        <option value="StoreManager">StoreManager</option>
                        <option value="Officer">Officer</option>
                    </optgroup>
                </select>
            </td>
            <td>
                <input type="submit" value="Salva" disabled>
                <input type="button" value="Elimina" disabled>
            </td>
        </tr>
        <tr>
            <td><input type="text" name="user_GCanfora" value="DBConfigurer" disabled /></td>
            <td><input type="password" name="pass_GCanfora" value="whoknows?" disabled /></td>
            <td>
                <select name="role" id="role">
                    <optgroup label="Database">
                        <option value="DBA" disabled>DBA</option>
                        <option value="DBConfigurer" selected disabled>DBConfigurer</option>
                        <option value="DBWriter" disabled>DBWriter</option>
                        <option value="DBViewer" disabled>DBViewer</option>
                        <option value="DBSync" disabled>DBSync</option>
                        <option value="DBBackup" disabled>DBBackup</option>
                    </optgroup>
                    <optgroup label="Role-Based Access Control">
                        <option value="Administrator">Administrator</option>
                        <option value="StoreManager">StoreManager</option>
                        <option value="Officer">Officer</option>
                    </optgroup>
                </select>
            </td>
            <td>
                <input type="submit" value="Salva" disabled>
                <input type="button" value="Elimina" disabled>
            </td>
        </tr>
        <tr>
            <td><input type="text" name="user_GCanfora" value="DBWriter" disabled /></td>
            <td><input type="password" name="pass_GCanfora" value="whoknows?" disabled /></td>
            <td>
                <select name="role" id="role">
                    <optgroup label="Database">
                        <option value="DBA" disabled>DBA</option>
                        <option value="DBConfigurer" disabled>DBConfigurer</option>
                        <option value="DBWriter" selected disabled>DBWriter</option>
                        <option value="DBViewer" disabled>DBViewer</option>
                        <option value="DBSync" disabled>DBSync</option>
                        <option value="DBBackup" disabled>DBBackup</option>
                    </optgroup>
                    <optgroup label="Role-Based Access Control">
                        <option value="Administrator">Administrator</option>
                        <option value="StoreManager">StoreManager</option>
                        <option value="Officer">Officer</option>
                    </optgroup>
                </select>
            </td>
            <td>
                <input type="submit" value="Salva" disabled>
                <input type="button" value="Elimina" disabled>
            </td>
        </tr>
        <tr>
            <td><input type="text" name="user_GCanfora" value="DBViewer" disabled /></td>
            <td><input type="password" name="pass_GCanfora" value="whoknows?" disabled /></td>
            <td>
                <select name="role" id="role">
                    <optgroup label="Database">
                        <option value="DBA" disabled>DBA</option>
                        <option value="DBConfigurer" disabled>DBConfigurer</option>
                        <option value="DBWriter" disabled>DBWriter</option>
                        <option value="DBViewer" selected disabled>DBViewer</option>
                        <option value="DBSync" disabled>DBSync</option>
                        <option value="DBBackup" disabled>DBBackup</option>
                    </optgroup>
                    <optgroup label="Role-Based Access Control">
                        <option value="Administrator">Administrator</option>
                        <option value="StoreManager">StoreManager</option>
                        <option value="Officer">Officer</option>
                    </optgroup>
                </select>
            </td>
            <td>
                <input type="submit" value="Salva" disabled>
                <input type="button" value="Elimina" disabled>
            </td>
        </tr>
        <tr>
            <td><input type="text" name="user_GCanfora" value="DBSync" disabled /></td>
            <td><input type="password" name="pass_GCanfora" value="whoknows?" disabled /></td>
            <td>
                <select name="role" id="role">
                    <optgroup label="Database">
                        <option value="DBA" disabled>DBA</option>
                        <option value="DBConfigurer" disabled>DBConfigurer</option>
                        <option value="DBWriter" disabled>DBWriter</option>
                        <option value="DBViewer" disabled>DBViewer</option>
                        <option value="DBSync" selected disabled>DBSync</option>
                        <option value="DBBackup" disabled>DBBackup</option>
                    </optgroup>
                    <optgroup label="Role-Based Access Control">
                        <option value="Administrator">Administrator</option>
                        <option value="StoreManager">StoreManager</option>
                        <option value="Officer">Officer</option>
                    </optgroup>
                </select>
            </td>
            <td>
                <input type="submit" value="Salva" disabled>
                <input type="button" value="Elimina" disabled>
            </td>
        </tr>
        <tr>
            <td><input type="text" name="user_GCanfora" value="DBBackup" disabled /></td>
            <td><input type="password" name="pass_GCanfora" value="whoknows?" disabled /></td>
            <td>
                <select name="role" id="role">
                    <optgroup label="Database">
                        <option value="DBA" disabled>DBA</option>
                        <option value="DBConfigurer" disabled>DBConfigurer</option>
                        <option value="DBWriter" disabled>DBWriter</option>
                        <option value="DBViewer" disabled>DBViewer</option>
                        <option value="DBSync" disabled>DBSync</option>
                        <option value="DBBackup" selected disabled>DBBackup</option>
                    </optgroup>
                    <optgroup label="Role-Based Access Control">
                        <option value="Administrator">Administrator</option>
                        <option value="StoreManager">StoreManager</option>
                        <option value="Officer">Officer</option>
                    </optgroup>
                </select>
            </td>
            <td>
                <input type="submit" value="Salva" disabled>
                <input type="button" value="Elimina" disabled>
            </td>
        </tr>
        <tr>
            <td><input type="text" name="user_GCanfora" value="GCanfora" /></td>
            <td><input type="text" name="pass_GCanfora" value="872fd71d0229901ae6b4aa0bf4ba5246" /></td>
            <td>
                <select name="role" id="role">
                    <optgroup label="Database">
                        <option value="DBA" disabled>DBA</option>
                        <option value="DBConfigurer" disabled>DBConfigurer</option>
                        <option value="DBWriter" disabled>DBWriter</option>
                        <option value="DBViewer" disabled>DBViewer</option>
                        <option value="DBSync" disabled>DBSync</option>
                        <option value="DBBackup" disabled>DBBackup</option>
                    </optgroup>
                    <optgroup label="Role-Based Access Control">
                        <option value="Administrator" selected>Administrator</option>
                        <option value="StoreManager">StoreManager</option>
                        <option value="Officer">Officer</option>
                    </optgroup>
                </select>
            </td>
            <td>
                <input type="submit" value="Salva">
                <input type="button" value="Elimina">
            </td>
        </tr>
        <tr>
            <td><input type="text" name="user_GCanfora" value="Mario" /></td>
            <td><input type="text" name="pass_GCanfora" value="8af503a13e7a4e67266b57d1d94fc85c" /></td>
            <td>
                <select name="role" id="role">
                    <optgroup label="Database">
                        <option value="DBA" disabled>DBA</option>
                        <option value="DBConfigurer" disabled>DBConfigurer</option>
                        <option value="DBWriter" disabled>DBWriter</option>
                        <option value="DBViewer" disabled>DBViewer</option>
                        <option value="DBSync" disabled>DBSync</option>
                        <option value="DBBackup" disabled>DBBackup</option>
                    </optgroup>
                    <optgroup label="Role-Based Access Control">
                        <option value="Administrator">Administrator</option>
                        <option value="StoreManager"selected>StoreManager</option>
                        <option value="Officer">Officer</option>
                    </optgroup>
                </select>
            </td>
            <td>
                <input type="submit" value="Salva">
                <input type="button" value="Elimina">
            </td>
        </tr>
        <tr>
            <td><input type="text" name="user_GCanfora" value="Mario" /></td>
            <td><input type="text" name="pass_GCanfora" value="dcf046dcc2bbff05a419769c1b83dae7" /></td>
            <td>
                <select name="role" id="role">
                    <optgroup label="Database">
                        <option value="DBA" disabled>DBA</option>
                        <option value="DBConfigurer" disabled>DBConfigurer</option>
                        <option value="DBWriter" disabled>DBWriter</option>
                        <option value="DBViewer" disabled>DBViewer</option>
                        <option value="DBSync" disabled>DBSync</option>
                        <option value="DBBackup" disabled>DBBackup</option>
                    </optgroup>
                    <optgroup label="Role-Based Access Control">
                        <option value="Administrator">Administrator</option>
                        <option value="StoreManager">StoreManager</option>
                        <option value="Officer" selected>Officer</option>
                    </optgroup>
                </select>
            </td>
            <td>
                <input type="submit" value="Salva">
                <input type="button" value="Elimina">
            </td>
        </tr>
     </table>
     <input type="button" value="Aggiungi utente">
    </form>

    <div align="center">
        <h2>Database:</h2>
        <form action="#">
         <table>
            <tr>
                <td>Local DBMS:</td>
                <td>
                    <select name="DBMS_type">
                        <option name="MySQL">MySQL</option>
                        <option name="MongoDB" selected>MongoDB</option>
                    </select>
                </td>
           </tr>
           <tr>
            <td>Local DB:</td>
            <td>
               Upload dump: <input type="file" name="db_upload_dump" /><br/>
               Download dump: <input type="button" name="db_download_dump" value="Download current dump" />
            </td>
           </tr>
           <tr>
                 <td>Master endpoint:<br><small>(sede centrale)</small></td>
                 <td><input type="text" name="master_ip" placeholder="IP:PORT;..." /> <small> ( i.g. 92.86.104.10:3308;www.care.it/Molise:27017 )</small></td>
            </tr>
            <tr>
                <td>Sync:</td>
                <td>
                   <input type="checkbox" name="sync_auto_active" checked /> Attiva Sincronizzazioni automatica <small>(Scrive in config.xml)</small>
                </td>
               </tr>
        </table>
        <table cellspacing="0" cellpadding="0" border="0" width="325">
            <tr style="color:white;background-color:grey">
                <th>Log: <input type="text" name="log_finder" placeholder="Ricerca: " /></th>
            </tr>
            <tr>
              <td>
                 <table cellspacing="0" cellpadding="1" border="1" width="666" >
                   <tr style="color:white;background-color:grey">
                    <th width="80px">Data</th>
                    <th width="111px">Utente</th>
                    <th width="111px">Operazione</th>
                    <th width="111px">Azione</th>
                    <th width="111px">Seriale</th>
                    <th width="111px">Note</th>
                   </tr>
                 </table>
              </td>
            </tr>
            <tr>
              <td>
                 <div style="width:666px; height:180px; overflow:auto;">
                   <table cellspacing="0" cellpadding="1" border="1" width="666" style="font-size: 11px;" >
                    <tr>
                        <td width="80px">16-04-2021 11:36</td>
                        <td width="111px">ManualSync: 206002</td>
                        <td width="111px">ELIMINAZIONE</td>
                        <td width="111px">Franco ha prelevato la sacca dal magazzino locale per darla al magazzino centrale</td>
                        <td width="111px">YYY_ID_SACCA_YYY</td>
                        <td width="111px">IP: 95.249.112.71 user_agent: Mozilla Firefox</td>
                    </tr>
                    <tr>
                        <td>16-04-2021 11:35</td>
                        <td>206001</td>
                        <td>LOGOUT</td>
                        <td>Mario si è sloggato dal sistema</td>
                        <td>null</td>
                        <td>null</td>
                    </tr>
                    <tr>
                        <td>16-04-2021 11:30</td>
                        <td>AutoSync</td>
                        <td>ELIMINAZIONE</td>
                        <td>AutoSync ha prelevato la sacca dal magazzino locale per darla al magazzino centrale</td>
                        <td>XXX_ID_SACCA_XXX</td>
                        <td>null</td>
                    </tr>
                    <tr>
                        <td>16-04-2021 11:10</td>
                        <td>206001</td>
                        <td>INSERIMENTO</td>
                        <td>Mario ha raccolto la sacca mettendola ne magazzino</td>
                        <td>XXX_ID_SACCA_XXX</td>
                        <td>null</td>
                    </tr>
                    <tr>
                        <td>16-04-2021 11:01</td>
                        <td>206001</td>
                        <td>LOGIN</td>
                        <td>Mario si è loggato nel sistema</td>
                        <td>null</td>
                        <td>null</td>
                    </tr>
                   </table>  
                 </div>
              </td>
            </tr>
          </table>
    </form>
    </div>

    <!-- START: routing table -->
    <div align="center">
        <h2>Routing Table:</h2>
	 <input type="radio" name="routingtable_useEndpoint" value="downloadRoutingTable" id="downloadRoutingTable" checked /> <label for="downloadRoutingTable">Scarica e utilizza da Master Endpoint ( 92.86.104.10:3308;www.care.it/Molise:27017 )</label><br />
	 <input type="radio" name="routingtable_useEndpoint" value="createRoutingTable" id="createRoutingTable" /> <label for="createRoutingTable">Compila routing table</label>
        <form action="#">
        <table cellspacing="0" cellpadding="0" border="0" width="325">
            <tr>
              <td>
                 <table cellspacing="0" cellpadding="1" border="1" width="1000" >
                   <tr style="color:white;background-color:grey">
                    <th align="center" width="40px">Usa</th>
                    <th align="center" width="430px">IP/DNS</th>
                    <th align="center" width="120px">Nome Nodo</th>
                    <th align="center" width="50px">Nazione</th>
                    <th align="center" width="50px">Regione</th>
                    <th align="center" width="50px">Prov.</th>
                    <th align="center" width="50px">Comune</th>
                   </tr>
                 </table>
              </td>
            </tr>
            <tr>
              <td>
                 <div style="width:1000px; height:180px; overflow:auto;">
                   <table cellspacing="0" cellpadding="1" border="1" width="1000" style="font-size: 11px;" >
                    <tr>
                        <td align="center" width="50px"><input type="checkbox" checked /></td>
                        <td align="center">https://www.care.it/Campania/AV/Avellino/Malzoni</td>
                        <td align="center" width="50px">Malzoni</td>
                        <td align="center" width="50px">Italy</td>
                        <td align="center" width="50px">Campania</td>
                        <td align="center" width="50px">AV</td>
                        <td align="center" width="50px">Avellino</td>
                    </tr>
                    <tr>
                        <td align="center"><input type="checkbox" checked /></td>
                        <td align="center">https://www.care.it/Campania/AV/Avellino/Moscati</td>
                        <td align="center">Malzoni</td>
                        <td align="center">Italy</td>
                        <td align="center">Campania</td>
                        <td align="center">AV</td>
                        <td align="center">Avellino</td>
                    </tr>
                    <tr>
                        <td align="center"><input type="checkbox" checked /></td>
                        <td align="center">https://www.care.it/Campania/AV/Avellino/Salvoni</td>
                        <td align="center">Salvoni</td>
                        <td align="center">Italy</td>
                        <td align="center">Campania</td>
                        <td align="center">AV</td>
                        <td align="center">Avellino</td>
                    </tr>
                    <tr>
                        <td align="center"><input type="checkbox" checked /></td>
                        <td align="center">https://www.care.it/Campania/AV/Mercogliano/ClinicaMontevergine</td>
                        <td align="center">Clinica Montevergine</td>
                        <td align="center">Italy</td>
                        <td align="center">Campania</td>
                        <td align="center">AV</td>
                        <td align="center">Mercogliano</td>
                    </tr>
                    <tr>
                        <td align="center"><input type="checkbox" checked /></td>
                        <td align="center">https://www.care.it/Campania/AV/Solofra/Landolfi</td>
                        <td align="center">Landolfi</td>
                        <td align="center">Italy</td>
                        <td align="center">Campania</td>
                        <td align="center">AV</td>
                        <td align="center">Solofra</td>
                    </tr>
                    <tr>
                        <td align="center"><input type="checkbox" checked /></td>
                        <td align="center">https://www.care.it/Campania/BN/Benevento/SanPio</td>
                        <td align="center">San Pio</td>
                        <td align="center">Italy</td>
                        <td align="center">Campania</td>
                        <td align="center">BN</td>
                        <td align="center">Benevento</td>
                    </tr>
                    <tr>
                        <td align="center"><input type="checkbox" checked /></td>
                        <td align="center">https://www.care.it/Campania/BN/Benevento/Rummo</td>
                        <td align="center">Rummo</td>
                        <td align="center">Italy</td>
                        <td align="center">Campania</td>
                        <td align="center">BN</td>
                        <td align="center">Benevento</td>
                    </tr>
                    <tr>
                        <td align="center"><input type="checkbox" checked /></td>
                        <td align="center">https://www.care.it/Campania/CE/Caserta/SanSebastiano</td>
                        <td align="center">San Sebastiano</td>
                        <td align="center">Italy</td>
                        <td align="center">Campania</td>
                        <td align="center">CE</td>
                        <td align="center">Caserta</td>
                    </tr>
                    <tr>
                        <td align="center"><input type="checkbox" checked /></td>
                        <td align="center">https://www.care.it/Campania/CE/Caserta/SanSebastiano</td>
                        <td align="center">San Sebastiano</td>
                        <td align="center">Italy</td>
                        <td align="center">Campania</td>
                        <td align="center">CE</td>
                        <td align="center">Caserta</td>
                    </tr>
                    <tr>
                        <td align="center"><input type="checkbox" checked /></td>
                        <td align="center">https://www.care.it/Campania/SA/Salerno/SanLeonardo</td>
                        <td align="center">SanLeonardo</td>
                        <td align="center">Italy</td>
                        <td align="center">Campania</td>
                        <td align="center">SA</td>
                        <td align="center">Salerno</td>
                    </tr>
                    <tr>
                        <td align="center"><input type="checkbox" checked /></td>
                        <td align="center">https://www.care.it/Campania/SA/Salerno/Ruggi</td>
                        <td align="center">Ruggi</td>
                        <td align="center">Italy</td>
                        <td align="center">Campania</td>
                        <td align="center">SA</td>
                        <td align="center">Salerno</td>
                    </tr>
                    <tr>
                        <td align="center"><input type="checkbox" checked /></td>
                        <td align="center">https://www.care.it/Campania/NA/Napoli/Cardarelli</td>
                        <td align="center">Cardarelli</td>
                        <td align="center">Italy</td>
                        <td align="center">Campania</td>
                        <td align="center">NA</td>
                        <td align="center">Napoli</td>
                    </tr>
                   </table>
                 </div>
              </td>
            </tr>
          </table>
    </form>
    </div>
    <!-- END:   routing table -->
</div>