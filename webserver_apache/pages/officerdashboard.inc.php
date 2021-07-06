<script src="./js/XMLHTTPRequest.js"></script>

<?php
$urlAPI = "http://localhost:8087/bloodbag/get/central";
$authorization = "Authorization: Bearer ".$token;
    $ch = curl_init();
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json' , $authorization ));
    curl_setopt($ch,CURLOPT_URL,$urlAPI);
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "GET");
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

    curl_setopt($ch, CURLOPT_FOLLOWLOCATION, 1);
    $result = curl_exec($ch);
    curl_close($ch);


$bagArray = (array) json_decode($result);
?>


          <fieldset>
            <legend><img src="images\gestionerichieste.png" width="10%"><a name="management"><b> Gestione Richieste </b></a><br><small>(role-user-visibility: Officer)</small></legend>

          <!-- START: BloodBags viewer -->
            <form action="" method="POST">
                <div align="center">
                <body style="margin:0px;">
                <table>
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
                        <table>
                          <tr style="color:white;background-color:grey; font-size: 20px;">
                          <?php ?>
                            <th align="center">Notes</th>
                            <th><input type="button" id="request_button" name="request_button" value="Richiedi"  onclick="addRequest('http://localhost:8087/request/add', '<?php echo($token); ?>', document.getElementById('selected_bag').nodeValue,  );"></th>
                            <th align="center">Priority</th>
                            <th align="center"><b>Seriale</b></th>
                            <th align="center"><b>Data creazione</b></th>
                            <th align="center"><b>Donatore</b></th>
                            <th align="center"><b>Data di scadenza</b></th>
                            <th align="center"><b>Gruppo</b></th>
                            <th align="center"><b>Note</b></th>
                            <th align="center"><b>Stato</b></th>
                          </tr>
                          <tr>
                        <?php foreach (array_keys($bagArray ) as $key) { ?>
                          <td><input type="text" id="notes_box" name="notes_box" /></td>
                          <td align='center'><input type="checkbox" id="selected_bag" name="selected_bad" value="<?php $bagArray[$key]->serial; ?>" />
                          <td>
                          <select id="selected_priority" name="selected_priority">
                            <option value="green">GREEN</option>
                            <option value="yellow">YELLOW</option>
                            <option value="red">RED</option>
                          </select>
                          </td>
                          </td>
                            <td><input size="30px" type="text" id="serial" name="serial" value="<?php echo $bagArray[$key]->serial; ?>" disabled /></td>
                            <td><input type="text" id="creation_date" name="creation_date" value="<?php echo $bagArray[$key]->creationDate; ?>" disabled /></td>
                            <td><input type="text" id="donator" name="donator" value="<?php echo $bagArray[$key]->donator; ?>" disabled /></td>
                            <td><input type="text" id="expiration_date" name="expiration_date" value="<?php echo $bagArray[$key]->expirationDate; ?>" disabled /></td>
                            <td><input type="text" id="group" name="group" value="<?php echo $bagArray[$key]->group; ?>" disabled /></td>
                            <td><input type="text" id="notes" name="notes" value="<?php echo $bagArray[$key]->notes; ?>" disabled /></td>
                            <td><input type="text" id="state" name="state" value="<?php echo $bagArray[$key]->state; ?>" disabled /></td>
                          </tr>
                        <?php ; } ?>
                         </table>
                         </body>
                      </td>
                    </tr>
                </table>
                </body>
                </div>
            </form>
        <!-- END: BloodBags viewer-->

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