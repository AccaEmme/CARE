<script src="./js/XMLHTTPRequest.js"></script>

<?php
$urlAPI = "http://localhost:8087/bloodbag/get/central";
$authorization = "Authorization: Bearer ". $token;
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
            <form action="" method="GET">
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
                        </th>
                    </tr>
            </form>
                    <tr>
                      <td>
                        <body style="margin:0px;">
                        <table>
                          <tr style="color:white;background-color:grey; font-size: 20px;">
                          <?php ?>
                            <th><input type="button" id="request_button" name="request_button" value="Richiedi"  onclick="addRequestes();" /></th>
                            <th align="center">Priority</th>
                            <th align="center"><b>Seriale</b></th>
                            <th align="center"><b>Data creazione</b></th>
                            <th align="center"><b>Donatore</b></th>
                            <th align="center"><b>Data di scadenza</b></th>
                            <th align="center"><b>Gruppo</b></th>
                            <th align="center"><b>Note</b></th>
                            <th align="center"><b>Stato</b></th>
                          </tr>
            <form action="" method="POST">
                        <?php foreach (array_keys($bagArray ) as $key) {  ?>
                          <tr>
                          <td align='center'><input type="checkbox" id="selected_bag" name="selected_bag[]" value="<?php echo ($bagArray[$key]->serial); ?>" />
                          <td>
                          <select id="selected_priority" name="selected_priority[]">
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
                        <?php ;} ?>
            </form>
                        <script>
                          function addRequestes(){

                            let serials = document.querySelectorAll('input[type=checkbox]:checked');
                            let priorities = document.getElementsByName('selected_priority[]');

                            var note = ' ' ;//prompt("Hai da dichiarare qualcosa?");

                            for(i = 0; i<serials.length; i++){

                              console.log(serials.item(i).value);
                              addRequest('http://localhost:8087/request/add', '<?php echo ($token) ?>', serials.item(i).value, priorities.item(i).value, note);
                            }
                          }
                        </script>
                         </table>
                         </body>
                      </td>
                    </tr>
                </table>
                </body>
                </div>
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
                        <tr>
                            <td><input type="text" id="id_requester" name="id_requester"></td>
                            <td><input type="text" id="serial" name="serial"></td>
                            <td><input type="text" id="date" name="date"></td>
                            <td><input type="text" id="note" name="note"></td>
                            <td><input type="text" id="state" name="state"></td>
                            <td><input type="text" id="priority" name="priority"></td>
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