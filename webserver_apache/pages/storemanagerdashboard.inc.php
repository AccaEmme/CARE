<script src="./js/html5-qrcode.min.js"></script>
   

<?php

$urlAPI = "http://localhost:8087/bloodbag/get/all";
$authorization = "Authorization: Bearer ".$token;
    $ch = curl_init();
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json' , $authorization ));
    curl_setopt($ch,CURLOPT_URL,$urlAPI);
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "GET");
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

    curl_setopt($ch, CURLOPT_FOLLOWLOCATION, 1);
    $result = curl_exec($ch);
    curl_close($ch);


$usersArray = (array) json_decode($result);

foreach (array_keys($usersArray ) as $key) {
  
/*
 echo(	  "<br>"
	. "serial:"
	. $usersArray[$key]->serial
	. " - group: "
	. $usersArray[$key]->group
	. " - donator: "
	. $usersArray[$key]->donator
	. " - creationDate: "
	. $usersArray[$key]->creationDate
	. " - expirationDate: "
	. $usersArray[$key]->expirationDate
	. " - state: "
	. $usersArray[$key]->state
	. " - notes: "
	. $usersArray[$key]->notes
	. " - usedTimeStamp: "
	. $usersArray[$key]->usedTimeStamp
	. "<br>");
*/
    //print_r($arr);

}

//unset($usersArray);

?>


<!-- campi tabella -->
    <center><h2>========STORE MANAGER========</h2></center>
    <form action="" method="POST">
     <table>
         <tr>
            <td align="center" width="10%"><b>serial</b></td>
            <td align="center"><b>group</b></td>
            <td align="center"><b>donator</b></td>
            <td align="center"><b>creationDate</b></td>
            <td align="center"><b>expirationDate</b></td>
            <td align="center"><b>state</b></td>
            <td align="center"><b>notes</b></td>
            <td align="center"><b>usedTimeStamp</b></td>
            <td align="center"><b>Actions</b></td>
        </tr>
        <tr>
<!-- in php lancio solo il for in html riempo la tabella -->
<?php foreach (array_keys($usersArray ) as $key) { ?>
            <td><input type="text" name="id_<?php echo $usersArray[$key]->serial; ?>" value="<?php echo $usersArray[$key]->serial; ?>" size="40" disabled /></td>
            <td><input type="text" name="group_<?php echo $usersArray[$key]->serial; ?>" value="<?php echo $usersArray[$key]->group; ?>" disabled/></td>
            <td><input type="text" name="donator_<?php echo $usersArray[$key]->serial; ?>" value="<?php echo $usersArray[$key]->donator; ?>" disabled /></td>
            <td><input type="text" name="creationDate_<?php echo $usersArray[$key]->serial; ?>" value="<?php echo date('Y-m-d', $usersArray[$key]->creationDate/1000); ?>" disabled/></td>
            <td><input type="text" name="expirationDate_<?php echo $usersArray[$key]->serial; ?>" value="<?php echo date('Y-m-d', $usersArray[$key]->expirationDate/1000); ?>" disabled/></td>
  	    <td><input type="text" name="state_<?php echo $usersArray[$key]->serial; ?>" value="<?php echo $usersArray[$key]->state; ?>" disabled/></td>
	    <td><input type="text" name="notes_<?php echo $usersArray[$key]->serial; ?>" value="<?php echo $usersArray[$key]->notes; ?>" disabled/></td>
            <td><input type="text" name="usedTimeStamp_<?php echo $usersArray[$key]->serial; ?>" value="<?php echo $usersArray[$key]->usedTimeStamp; ?>" disabled/></td>
            <td>
                <input type="button" value="Elimina">
            </td>
        </tr>
<?php
}
?>
	<!-- START: new user fields -->
        <tr>
            <td><input type="text" size="40" disabled/></td>
            <td><select name="group" id="group">
 		<option>Apos</option>
 		<option>Aneg</option>
		<option>Bpos</option>
 		<option>Bneg</option>
		<option>ZEROpos</option>
 		<option>ZEROneg</option>
		<option>ABneg</option>
		<option>ABpos</option>
		</select>
	    </td>
            <td><input type="text" name="donator" id="donator" placeholder="cod.fiscale CF_DONATORE" /></td>
            <td><input type="text" disabled/></td>
            <td><input type="text" disabled/></td>
            <td><input type="text" disabled/></td>
            <td><input type="text" name="note" id="note" /></td>
            <td><input type="text" disabled/></td>
            <td>
		<input type="text" name="addBloodBagURL" id="addBloodBagURL" value="http://localhost:8087/bloodbag/add" hidden="yes" />
                <input type="submit" value="Crea" onclick="addBloodBag(document.getElementById('addBloodBagURL').value, '<?php echo($token); ?>', document.getElementById('group').value, document.getElementById('donator').value, document.getElementById('note').value);setTimeout(function () { location.reload(1); }, 5000)">
            </td>
        </tr>
	
	 <tr>
            <td><input type="text" name="serial" id="serial" placeholder="seriale sacca" size="40" /></td>
            <td><input type="text" disabled/></td>
            <td><input type="text" disabled/></td>
            <td><input type="text" disabled/></td>
            <td><input type="text" disabled/></td>
            <td><input type="text" disabled/></td>
            <td><input type="text" disabled/></td>
 	    <td><input type="text" disabled/></td>
            <td>
		<input type="text" name="addBloodBagCURL" id="addBloodBagCURL" value="http://localhost:8087/bloodbag/import" hidden="yes" />
                <input type="submit" value="import sacca" onclick="addBloodBagCentral(document.getElementById('addBloodBagCURL').value, '<?php echo($token); ?>', document.getElementById('serial').value);">
            </td>
        </tr>
     </table>
     </form>

<!- START: QRCODE Scanner Reader -->
<div align="center">
 <div style="width:500px;" id="reader"></div>
</div>
<script type="text/javascript">
function onScanSuccess(qrCodeMessage) {
 var qrObj = JSON.parse(qrCodeMessage);
 document.getElementById('serial').value = qrObj['serial'];
 document.getElementById("serial").style.border = "thick solid #33cc33";

}
function onScanError(errorMessage) {
  //handle scan error
}
var html5QrcodeScanner = new Html5QrcodeScanner(
    "reader", { fps: 10, qrbox: 250 });
html5QrcodeScanner.render(onScanSuccess, onScanError);
</script>
<!- END: QRCODE Scanner Reader -->

<!--fine parte buona-->



  <!-- START: Expiring BloodBags -->

  <?php
  /*
        <fieldset>
            <legend><img src="images\scadenza.png" width="10%"><a name="expiring"><b> In Scadenza</b></a></legend>
            <table id="BloodBags-table" border="1" style="font-size: 20px;" >
                <tr>
                    <td align="center">-</td>
                   <!--  <td align="center"><p>In Locale:</p></td> -->
                    <td align="center"><p>Sede Centrale:</p></td>
                    <td align="center"><p>Altri Nodi:</p></td>
                </tr>
                <tr>
                    <td align="center">
                        <p>A+</p>
                    </td>
                    <!--  <td align="center">
                        <p class="error">5 sacche</p>
                    </td>-->
                    <td align="center">
                        <p class="correct">0 sacche</p>
                    </td>
                    <td align="center">
                        <p class="warning">1 sacche</p>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <p>A-</p>
                    </td>
                    <!--  <td align="center">
                        <p class="error">2 sacche</p>
                    </td>-->
                    <td align="center">
                        <p class="warning">4 sacche</p>
                    </td>
                    <td align="center">
                        <p class="correct">0 sacche</p>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <p>B+</p>
                    </td>
                    <!--  <td align="center">
                        <p class="error">3 sacche</p>
                    </td>-->
                    <td align="center">
                        <p class="correct">0 sacche</p>
                    </td>
                    <td align="center">
                        <p class="warning">2 sacche</p>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <p>B-</p>
                    </td>
                    <!--  <td align="center">
                        <p class="error">5 sacche</p>
                    </td>-->
                    <td align="center">
                        <p class="correct">0 sacche</p>
                    </td>
                    <td align="center">
                        <p class="error">6 sacche</p>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <p>AB+</p>
                    </td>
                    <!--  <td align="center">
                        <p class="error">0 sacche</p>
                    </td>-->
                    <td align="center">
                        <p class="warning">4 sacche</p>
                    </td>
                    <td align="center">
                        <p class="correct">0 sacche</p>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <p>AB-</p>
                    </td>
                    <!--  <td align="center">
                        <p class="error">4 sacche</p>
                    </td>-->
                    <td align="center">
                        <p class="warning">3 sacche</p>
                    </td>
                    <td align="center">
                        <p class="warning">2 sacche</p>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <p>0+</p>
                    </td>
                    <!--  <td align="center">
                        <p class="error">0 sacche</p>
                    </td>-->
                    <td align="center">
                        <p class="correct">0 sacche</p>
                    </td>
                    <td align="center">
                        <p class="correct">0 sacche</p>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <p>0-</p>
                    </td>
                    <!--  <td align="center">
                        <p class="error">0 sacche</p>
                    </td>-->
                    <td align="center">
                        <p class="correct">0 sacche</p>
                    </td>
                    <td align="center">
                        <p class="correct">0 sacche</p>
                    </td>
                </tr>

            </table>


          <!-- START:  BAGS THAT ARE EXPIRING-->
          <form action="#" method="POST">
            <div align="center">
                <body style="margin:0px;">
                <table cellspacing="0" cellpadding="0" height:"100%" width="100%" >
                <tr style="color:white;background-color:rgb(150, 145, 145);">
                    <th>Lista:</th>
                </tr>
                <tr>
                  <td>
                    <body style="margin:0px;">
                        <table cellspacing="0" cellpadding="0" height:"100%" width="100%" >
                       <tr style="color:white;background-color:grey; font-size: 20px;">
                        <th width="100px">Data Creazione</th>
                        <th width="250px">Seriale</th>
                        <th width="80px">Gruppo sanguigno</th>
                        <th width="85px">Puo' donare a</th>
                        <th width="116px">Azione</th>
                       </tr>
                     </table>
                     </body>
                  </td>
                </tr>

                <br></br>

                <tr>
                  <td>
                     <div style=" overflow-y: scroll; height:100px; margin:0px;">
                       <table cellspacing="0" cellpadding="1" border="1" width="100%" style="font-size: 20px;" >
                        <tr bgcolor="red">
                            <td width="125px">01-11-2020 08:11</td>
                            <td width="240px">IT-NA206003-APOS-20201101-0010</td>
                            <td width="80px">Apos</td>
                            <td witdth="80px">A+, A-,0+,0-</td>
                            <td width="80px">
                                <div align ="center">
                                    <input type="button" name="accept_request" value="Utilizza" />
                                </div>
                            </td>
                        </tr>
                        <tr bgcolor="red">
                            <td width="140px">17-12-2020 09:16</td>
                            <td width="277px">IT-AV207005-APOS-20201217-0010</td>
                            <td width="80px">Apos</td>
                            <td width="111px">A+, A-,0+,0-</td>
                            <td width="111px">
                                <div align ="center">
                                    <input type="button" name="accept_request" value="Utilizza" />
                                </div>
                            </td>
                        </tr>
                       </table>  
                     </div>
                  </td>
                </tr>
            </table>
            </body>
            </div>  
        </form>
        <!-- END:  StoreManager Requests -->
        </fieldset>
        <!-- END:   Expiring BloodBags -->


          <fieldset>
            <legend><img src="images\gestionerichieste.png" width="10%"><a name="management"><b> Gestione Richieste </b></a><br><small>(role-user-visibility: Officer)</small></legend>
          <!-- START:   Management  BloodBags -->
            <form action="#" method="POST">
                <div align="center">
                <body style="margin:0px;">
                <table cellspacing="0" cellpadding="0" height:"100%" width="100%" >
                    <tr style="color:white;background-color:rgb(150, 145, 145); font-size: 16px;">
                        <th>
                            Ho bisogno di:
                            <select name="needsof">
                                <option>A+</option>
                                <option>B+</option>
                                <option>AB+</option>
                                <option>0+</option>
                                <option>A-</option>
                                <option>B-</option>
                                <option>AB-</option>
                                <option>0-</option>
                            </select>
                            <table>
                                <tr>
                                    <td><input type="checkbox" name="show_mine" checked /> Mostra proprio magazzino</td>
                                    <td><input type="checkbox" name="show_others" checked /> Mostra di altri Nodi</td>
                                    <td><input type="checkbox" name="show_central" checked /> Mostra di sede centrale</td>
                                    <td><input type="checkbox" name="show_requests" checked /> Mostra richieste sacche da altri nodi</td>
                                    <td><input type="checkbox" name="show_expired" checked /> Mostra scaduti</td>
                                </tr>
                            </table>
                        </th>
                    </tr>
                    <tr>
                      <td>
                        <body style="margin:0px;">
                        <table cellspacing="0" cellpadding="0" height:"100%" width="100%" >
                           <tr style="color:white;background-color:grey; font-size: 20px;">
                            <th width="108px">Data Creazione</th>
                            <th width="200px">Seriale</th>
                            <th width="5px">Gruppo sanguigno</th>
                            <th width="200px">Puo' donare a</th>
                            <th width="65px">Sede Magazzino</th>
                            <th width="134px">
                                <input type="button" name="request" value="Richiedi"  />
                                <input type="button" name="cancel_request" value="Annulla richiesta" disabled/>
                                <input type="button" name="report" value="Segnala" />
                            </th>
                           </tr>
                         </table>
                         </body>
                      </td>
                    </tr>
                    <tr>
                      <td>
                         <div style="margin:0px; overflow-y: scroll; height:250px;">
                           <table cellspacing="0" cellpadding="1" border="1" width="100%" style="font-size: 20px;" >
                            <tr bgcolor="red">
                                <td width="125px">01-11-2020 08:11</td>
                                <td width="240px">IT-NA206003-APOS-20201101-0010</td>
                                <td width="80px">Apos</td>
                                <td witdth="80px">A+, A-,0+,0-</td>
                                <td width="200px">NA206</td>
                                <td width="80px">
                                    <div align ="center">
                                        <input type="checkbox" name="XXX_SERIAL_XXX" />
                                    </div>
                                </td>
                            </tr>
                            <tr bgcolor="red">
                                <td width="140px">17-12-2020 09:16</td>
                                <td width="277px">IT-AV207005-APOS-20201217-0010</td>
                                <td width="80px">Apos</td>
                                <td width="111px">A+, A-,0+,0-</td>
                                <td width="80px">AV207</td>
                                <td width="111px">
                                    <div align ="center">
                                        <input type="checkbox" name="XXX_SERIAL_XXX" />
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td width="140px">16-04-2021 11:36</td>
                                <td width="277px">IT-NA206001-APOS-20210416-0001</td>
                                <td width="80px">Apos</td>
                                <td width="111px">A+, A-,0+,0-</td>
                                <td width="80px">NA206</td>
                                <td width="111px">
                                    <div align ="center">
                                        <input type="checkbox" name="XXX_SERIAL_XXX" />
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td width="140px">16-04-2021 11:36</td>
                                <td width="277px">IT-NA206001-ABPOS-20210416-0002</td>
                                <td width="80px">ABpos</td>
                                <td width="111px">A+,A-,B+,B-,AB+,AB-,0+,0-</td>
                                <td width="80px">NA206</td>
                                <td width="111px">
                                    <div align ="center">
                                        <input type="checkbox" name="XXX_SERIAL_XXX" />
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td width="140px">16-04-2021 11:36</td>
                                <td width="277px">IT-NA206003-BNEG-20210416-0003</td>
                                <td width="80px">Bneg</td>
                                <td width="111px">B-,0-</td>
                                <td width="80px">NA206</td>
                                <td width="111px">
                                    <div align ="center">
                                        <input type="checkbox" name="XXX_SERIAL_XXX" />
                                    </div>
                                </td>
                            </tr>
                            <tr bgcolor="yellow">
                                <td width="140px">17-04-2021 11:36</td>
                                <td width="277px">IT-AV207005-APOS-20210417-0010</td>
                                <td width="80px">Apos</td>
                                <td width="111px">A+,A-,0+,0-</td>
                                <td width="80px">AV207</td>
                                <td width="111px">
                                    <div align ="center">
                                        <input type="checkbox" name="XXX_SERIAL_XXX" />
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td width="140px">18-04-2021 11:36</td>
                                <td width="277px">IT-NA206005-APOS-20210418-0001</td>
                                <td width="111px">Apos</td>
                                <td width="111px">A+,A-,0+,0-</td>
                                <td width="80px">NA206</td>
                                <td width="111px">
                                    <div align ="center">
                                        <input type="checkbox" name="XXX_SERIAL_XXX" />
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td width="140px">19-04-2021 11:36</td>
                                <td width="277px">IT-NA206002-APOS-20210419-0001</td>
                                <td width="111px">Apos</td>
                                <td width="111px">A+,A-,0+,0-</td>
                                <td width="80px">NA206</td>
                                <td width="111px">
                                    <div align ="center">
                                        <input type="checkbox" name="XXX_SERIAL_XXX" />
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td width="140px">19-04-2021 11:36</td>
                                <td width="277px">IT-NA206003-BNEG-20210419-0003</td>
                                <td width="80px">Bneg</td>
                                <td width="111px">B-,0-</td>
                                <td width="80px">NA206</td>
                                <td width="111px">
                                    <div align ="center">
                                        <input type="checkbox" name="XXX_SERIAL_XXX" />
                                    </div>
                                </td>
                            </tr>
                            <tr bgcolor="yellow">
                                <td width="140px">19-04-2021 11:36</td>
                                <td width="277px">IT-AV207005-APOS-20210419-0010</td>
                                <td width="80px">Apos</td>
                                <td width="111px">A+,A-,0+,0-</td>
                                <td width="80px">AV207</td>
                                <td width="111px">
                                    <div align ="center">
                                        <input type="checkbox" name="XXX_SERIAL_XXX" />
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td width="140px">19-04-2021 11:36</td>
                                <td width="277px">IT-NA206002-APOS-20210419-0001</td>
                                <td width="111px">Apos</td>
                                <td width="111px">A+,A-,0+,0-</td>
                                <td width="80px">NA206</td>
                                <td width="111px">
                                    <div align ="center">
                                        <input type="checkbox" name="XXX_SERIAL_XXX" />
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td width="140px">19-04-2021 11:36</td>
                                <td width="277px">IT-NA206003-BNEG-20210419-0003</td>
                                <td width="80px">Bneg</td>
                                <td width="111px">B-,0-</td>
                                <td width="80px">NA206</td>
                                <td width="111px">
                                    <div align ="center">
                                        <input type="checkbox" name="XXX_SERIAL_XXX" />
                                    </div>
                                </td>
                            </tr>
                           </table>  
                         </div>
                      </td>
                    </tr>
                </table>
                </body>
                </div>
            </form>
        <!-- END:   Management BloodBags -->
        <br /><br /><br />
        <!-- START:   Officer Requests -->
          <form action="#" method="POST">
            <div align="center">
                <body style="margin:0px;">
                <table cellspacing="0" cellpadding="0" height:"100%" width="100%" >
                <tr style="color:white;background-color:rgb(150, 145, 145);">
                    <th>Richieste:</th>
                </tr>
                <tr>
                  <td>
                    <body style="margin:0px;">
                        <table cellspacing="0" cellpadding="0" height:"100%" width="100%" >
                       <tr style="color:white;background-color:grey; font-size: 20px;">
                        <th width="100px">Data richiesta</th>
                        <th width="325px">Seriale</th>
                        <th width="80px">Gruppo sanguigno</th>
                        <th width="85px">Centro locale</th>
                        <th width="116px">Azione</th>
                       </tr>
                     </table>
                     </body>
                  </td>
                </tr>
                <tr>
                  <td>
                     <div style=" overflow-y: scroll; height:100px; margin:0px;">
                       <table cellspacing="0" cellpadding="1" border="1" width="100%" style="font-size: 20px;" >
                        <tr bgcolor="red">
                            <td width="140px">01-11-2020 08:11</td>
                            <td width="277px">IT-NA206003-APOS-20201101-0010</td>
                            <td width="80px">Apos</td>
                            <td width="80px">NA206</td>
                            <td width="111px">
                                <div align ="center">
                                    <input type="button" name="cancel_request" value="Annulla richiesta" disabled/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td width="140px">16-04-2021 11:36</td>
                            <td width="277px">IT-NA206001-APOS-20210416-0001</td>
                            <td width="80px">Apos</td>
                            <td width="80px">NA206</td>
                            <td width="111px">
                                <div align ="center">
                                    <input type="button" name="cancel_request" value="Annulla richiesta" disabled/>
                                </div>
                            </td>
                        </tr>
                       </table>  
                     </div>
                  </td>
                </tr>
            </table>
            </body>
            </div>  
        </form>
        <!-- END:  Officer Requests -->
          </fieldset>



        <!-- START:   Availability BloodBags -->
        <fieldset>
            <legend><img src="images\magazzino.png" width="10%"><a name="warehouse"><b>In Magazzino</b></a><br><small>(role-user-visibility: StoreManager)</small></legend>           
            <table id="BloodBags-table">
                <tr>
                    <td id="BloodBags-td">
                        <table border="0">
                            <tr>
                                <td>
                                <td align="left" ><p>A+</p>
                                </td>
                            </tr>
                                <td align="left"><a href="#"><img src="images\SAccaVuota.png" alt="plusbutton" width="150%" /></a></td>
                                </td>
                            <td>&nbsp;</td>
                                <td align="left"><a href="#"><img src="images\plus.png" alt="plusbutton" width="20%" /></a></td>
                            </tr>
                        </table>
                    </td>
                    <td id="BloodBags-td">
                        <table border="0">
                            <tr>
                                <td>
                                <td align="left"><p>B+</p>
                                </td>
                            </tr>
                            <td align="left"><a href="#"><img src="images\SAccaMetà.png" alt="plusbutton" width="150%" /></a></td>
                        </td>
                    <td>&nbsp;</td>
                        <td align="left"><a href="#"><img src="images\plus.png" alt="plusbutton" width="20%" /></a></td>
                    </tr>
                </table>
                    </td>
                    <td id="BloodBags-td">
                        <table border="0">
                            <tr>
                                <td>
                                <td align="left"><p>0+</p>
                                </td>
                            </tr>
                                <td align="left">
                                    <p class="error">5 sacche</p>
                                </td>
                                <td align="leftyìt"><a href="#"><img src="images\plus.png" alt="plusbutton" width="20%" /></a></td>
                                
                            </tr>
                        </table>
                    </td>
                    <td id="BloodBags-td">
                        <table border="0">
                            <tr>
                                <td>
                                <td align="left"><p>AB+</p>
                                    </td>
                            </tr>
                                <td align="left">
                                    <p class="warning">5 sacche</p>
                                </td>
                                <td align="left"><a href="#"><img src="images\plus.png" alt="plusbutton" width="20%" /></a></td>
                                
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td id="BloodBags-td">
                        <table border="0">
                            <tr>
                                <td>
                                <td align="left"><p>A-</p>
                                    </td>
                            </tr>
                                <td align="left">
                                    <p class="warning">5 sacche</p>
                                </td>
                                <td align="left"><a href="#"><img src="images\plus.png" alt="plusbutton" width="20%" /></a></td>
                                
                            </tr>
                        </table>
                    </td>
                    <td id="BloodBags-td">
                        <table border="0">
                            <tr>
                                <td>
                                <td align="left"><p>B-</p>
                                    </td>
                            </tr>
                                <td align="left">
                                    <p class="warning">5 sacche</p>
                                </td>
                                <td align="left"><a href="#"><img src="images\plus.png" alt="plusbutton" width="20%" /></a></td>
                                
                            </tr>
                        </table>
                    </td>
                    <td id="BloodBags-td">
                        <table border="0">
                            <tr>
                                <td>
                                <td align="left"><p>0-</p>
                                    </td>
                            </tr>
                                <td align="left">
                                    <p class="correct">5 sacche</p>
                                </td>
                                <td align="left"><a href="#"><img src="images\plus.png" alt="plusbutton" width="20%" /></a></td>
                                
                            </tr>
                        </table>
                    </td>
                    <td id="BloodBags-td">
                        <table border="0">
                            <tr>
                                <td>
                                <td align="left"><p>AB-</p>
                                    </td>
                            </tr>
                                <td align="left">
                                    <p class="correct">5 sacche</p>
                                </td>
                                <td align="left"><a href="#"><img src="images\plus.png" alt="plusbutton" width="20%" /></a></td>
                                
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>

            <br></br>
            <!-- START:  StoreManager Requests -->
          <form action="#" method="POST">
            <div align="center">
                <body style="margin:0px;">
                <table cellspacing="0" cellpadding="0" height:"100%" width="100%" >
                <tr style="color:white;background-color:rgb(150, 145, 145);">
                    <th>Richieste:</th>
                </tr>
                <tr>
                  <td>
                    <body style="margin:0px;">
                        <table cellspacing="0" cellpadding="0" height:"100%" width="100%" >
                       <tr style="color:white;background-color:grey; font-size: 20px;">
                        <th width="100px">Data richiesta</th>
                        <th width="325px">Seriale</th>
                        <th width="80px">Gruppo sanguigno</th>
                        <th width="85px">Centro locale</th>
                        <th width="116px">Azione</th>
                       </tr>
                     </table>
                     </body>
                  </td>
                </tr>
                <tr>
                  <td>
                     <div style=" overflow-y: scroll; height:100px; margin:0px;">
                       <table cellspacing="0" cellpadding="1" border="1" width="100%" style="font-size: 20px;" >
                        <tr bgcolor="red">
                            <td width="140px">01-11-2020 08:11</td>
                            <td width="277px">IT-NA206003-APOS-20201101-0010</td>
                            <td width="80px">Apos</td>
                            <td width="80px">NA206</td>
                            <td width="111px">
                                <div align ="center">
                                    <input type="button" name="accept_request" value="Approva" />
                                    <input type="button" name="cancel_request" value="Annulla" />
                            </div>
                            </td>
                        </tr>
                        <tr>
                            <td width="140px">16-04-2021 11:36</td>
                            <td width="277px">IT-NA206001-APOS-20210416-0001</td>
                            <td width="80px">Apos</td>
                            <td width="80px">NA206</td>
                            <td width="111px">
                                <div align ="center">
                                    <input type="button" name="accept_request" value="Approva" />
                                    <input type="button" name="cancel_request" value="Annulla" />
                                </div>
                            </td>
                        </tr>
                       </table>  
                     </div>
                  </td>
                </tr>
            </table>
            </body>
            </div>  
        </form>
        <!-- END:  StoreManager Requests -->

        </fieldset>

       
        <!-- END:   Availability BloodBags -->
        */
        ?>
 