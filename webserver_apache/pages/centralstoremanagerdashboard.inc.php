<script src="./js/html5-qrcode.min.js"></script>
<link rel="stylesheet" href="./css/storedashboard.css">


<fieldset>
    <legend><img src="images\Magazzino.png" width="10%"><a name="management"><b> MAGAZZINO CENTRALE </b></a><br><small>(Rende disponibili le sacche che gli vengono spedite)</small></legend>
    <center>
        <table>
            <tr>
                <td><input type="text" name="serial" class="textcs" id="serial" placeholder="seriale sacca" size="40" /></td>
                <input type="text" name="addBloodBagCURL" id="addBloodBagCURL" value="http://localhost:8087/bloodbag/central/confirm" hidden="yes" />
                <td> <input type="submit" value="import sacca" class="myButton" onclick="confirmBloodBagCentral(document.getElementById('addBloodBagCURL').value, '<?php echo ($token); ?>', document.getElementById('serial').value);">
                <td>
                </td>
            </tr>
        </table>
    </center>
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
                "reader", {
                    fps: 10,
                    qrbox: 250
                });
            html5QrcodeScanner.render(onScanSuccess, onScanError);
        </script>
</fieldset>

<fieldset>
    <legend><img src="images\Magazzino.png" width="10%"><a name="management"><b> MAGAZZINO CENTRALE </b></a><br><small>(Assegna seriale alla sacca e la aggiunge al magazzino CTT centrale)</small></legend>

    <center>
        <table>
            <tr>

                <td><select name="group" id="group" class="textcs">
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
                <td><input type="text" name="donator" id="donator" class="textcs" placeholder="cod.fiscale CF_DONATORE" /></td>

                <td><input type="text" name="note" id="note" class="textcs" placeholder="Note" /></td>

                <td>
                    <input type="text" name="addBloodBagURL" id="addBloodBagURL" value="http://localhost:8087/bloodbag/add" hidden="yes" />
                    <input type="button" value="CREA" class="myButton" onclick="addBloodBag(document.getElementById('addBloodBagURL').value, '<?php echo ($token); ?>', document.getElementById('group').value, document.getElementById('donator').value, document.getElementById('note').value);setTimeout(function () { location.reload(1); }, 3000)">
                </td>
            </tr>
        </table>
        <center>
</fieldset>


<fieldset>
    <legend><img src="images\Magazzino.png" width="10%"><a name="management"><b> MAGAZZINO CENTRALE </b></a><br><small>(Rende disponibili le sacche del magazzino CCS per i nodi periferici)</small></legend>

    <center>
        <table>
            <tr>

                <td><input type="text" name="inserted_serial" id="inserted_serial" class="textcs" placeholder="seriale della sacca" /></td>

                <td>
                    <input type="text" name="centralAddURL" id="centralAddURL" value="http://localhost:8087/bloodbag/central/add" hidden="yes" />
                    <input type="button" value="CONDIVIDI" class="myButton" onclick="addBloodBagCentral(document.getElementById('centralAddURL').value, '<?php echo ($token); ?>', document.getElementById('inserted_serial').value);setTimeout(function () { location.reload(1); }, 3000)">
                </td>
            </tr>
        </table>
        <center>
</fieldset>


<!- END: QRCODE Scanner Reader -->
    <fieldset>
        <legend><img src="images\broadcastchat.png" width="10%"><a name="management"><b> RICHIESTE </b></a><br></legend>
        <?php

        $urlAPI = "http://localhost:8087/request/get/state/pending";
        $authorization = "Authorization: Bearer " . $token;
        $ch = curl_init();
        curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json', $authorization));
        curl_setopt($ch, CURLOPT_URL, $urlAPI);
        curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "GET");
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

        curl_setopt($ch, CURLOPT_FOLLOWLOCATION, 1);
        $result = curl_exec($ch);
        curl_close($ch);


        $requestArray = (array) json_decode($result);




        ?>


        <!-- campi tabella -->

        <form action="" method="POST">
            <center>
                <table>
                    <tr>
                        <td align="center"><b>serial</b></td>
                        <td align="center"><b>idRequester</b></td>
                        <td align="center"><b>sate</b></td>
                        <td align="center"><b>notes</b></td>
                        <td align="center"><b>state</b></td>
                        <td align="center"><b>priority</b></td>
                    </tr>
                    <tr>
                        <!-- in php lancio solo il for in html riempo la tabella -->
                        <?php foreach (array_keys($requestArray) as $key) { ?>
                            <td><input type="text" class="textcss" name="id_<?php echo $requestArray[$key]->serial; ?>" value="<?php echo $requestArray[$key]->serial; ?>" size="40" disabled /></td>
                            <td><input type="text" class="textcss" name="idRequester_<?php echo $requestArray[$key]->serial; ?>" value="<?php echo $requestArray[$key]->id_requester; ?>" disabled /></td>
                            <td><input type="text" class="textcss" name="date_<?php echo $requestArray[$key]->serial; ?>" value="<?php echo  $requestArray[$key]->date; ?>" disabled /></td>
                            <td><input type="text" class="textcss" name="notes_<?php echo $requestArray[$key]->serial; ?>" value="<?php echo $requestArray[$key]->note; ?>" disabled /></td>
                            <td><input type="text" class="textcss" name="state_<?php echo $requestArray[$key]->serial; ?>" value="<?php echo $requestArray[$key]->state; ?>" disabled /></td>
                            <td><input type="text" class="textcss" name="priority_<?php echo $requestArray[$key]->serial; ?>" value="<?php echo $requestArray[$key]->priority; ?>" disabled /></td>

                            <td>

                                <input type="submit" value="ACCETTA" class="myButton" onclick="acceptRequest('http://localhost:8087/request/accept','<?php echo ($token); ?>','<?php echo $requestArray[$key]->id_requester; ?>','<?php echo $requestArray[$key]->serial; ?>');setTimeout(function () { location.reload(1); }, 1000);">
                            </td>
                            <td>
                                <input type="submit" value="RIFIUTA" class="myButtonNeg" onclick="refuseRequest('http://localhost:8087/request/refuse','<?php echo ($token); ?>','<?php echo $requestArray[$key]->id_requester; ?>','<?php echo $requestArray[$key]->serial; ?>');setTimeout(function () { location.reload(1); }, 1000);">
                            </td>
                    </tr>
        </form>
    <?php
                        }
    ?>
    </table>
    </center>

    </fieldset>

    <?php

    $urlAPI = "http://localhost:8087/bloodbag/get/state/Available";
    $authorization = "Authorization: Bearer " . $token;
    $ch = curl_init();
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json', $authorization));
    curl_setopt($ch, CURLOPT_URL, $urlAPI);
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "GET");
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

    curl_setopt($ch, CURLOPT_FOLLOWLOCATION, 1);
    $result = curl_exec($ch);
    curl_close($ch);


    $bagArrayc = (array) json_decode($result);



    ?>


    <fieldset>
    <legend><img src="images\Magazzino.png" width="10%"><a name="management"><b> MAGAZZINO CCS </b></a><br><small>(Sacche in magazzino)</small></legend>
    <!-- campi tabella -->
    <center>
        <table>
            <tr>
                <td align="center"><b>seriale</b></td>
                <td align="center"><b>group</b></td>
                <td align="center"><b>donator</b></td>
                <td align="center"><b>creationDate</b></td>
                <td align="center"><b>expirationDate</b></td>
                <td align="center"><b>state</b></td>
                <td align="center"><b>notes</b></td>
                <td align="center"><b>used</b></td>
            </tr>

            <!-- in php lancio solo il for in html riempo la tabella -->
            <tr>


                <!-- in php lancio solo il for in html riempo la tabella -->
                <!-- in php lancio solo il for in html riempo la tabella -->
                <?php foreach (array_keys($bagArrayc) as $key) { ?>
                    <td><input type="text" class="textcss" name="id_<?php echo $bagArrayc[$key]->serial; ?>" value="<?php echo $bagArrayc[$key]->serial; ?>" size="40" disabled /></td>
                    <td><input type="text" class="textcss" name="group_<?php echo $bagArrayc[$key]->serial; ?>" value="<?php echo $bagArrayc[$key]->group; ?>" disabled /></td>
                    <td><input type="text" class="textcss" name="donator_<?php echo $bagArrayc[$key]->serial; ?>" value="<?php echo $bagArrayc[$key]->donator; ?>" disabled /></td>
                    <td><input type="text" class="textcss" name="creationDate_<?php echo $bagArrayc[$key]->serial; ?>" value="<?php echo date('Y-m-d', $bagArrayc[$key]->creationDate / 1000); ?>" disabled /></td>
                    <td><input type="text" class="textcss" name="expirationDate_<?php echo $bagArrayc[$key]->serial; ?>" value="<?php echo date('Y-m-d', $bagArrayc[$key]->expirationDate / 1000); ?>" disabled /></td>
                    <td><input type="text" class="textcss" name="state_<?php echo $bagArrayc[$key]->serial; ?>" value="<?php echo $bagArrayc[$key]->state; ?>" disabled /></td>
                    <td><input type="text" class="textcss" name="notes_<?php echo $bagArrayc[$key]->serial; ?>" value="<?php echo $bagArrayc[$key]->notes; ?>" disabled /></td>
                    <td><input type="text" class="textcss" name="usedTimeStamp_<?php echo $bagArrayc[$key]->serial; ?>" value="<?php echo date('Y-m-d', $bagArrayc[$key]->usedTimeStamp / 1000); ?>" disabled /></td>
                
            </tr>
        <?php
                }
        ?>
        </table>
    </center>


  </fieldset>

    <?php

$urlAPI = "http://localhost:8087/bloodbag/get/central";
$authorization = "Authorization: Bearer " . $token;
$ch = curl_init();
curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json', $authorization));
curl_setopt($ch, CURLOPT_URL, $urlAPI);
curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "GET");
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

curl_setopt($ch, CURLOPT_FOLLOWLOCATION, 1);
$result = curl_exec($ch);
curl_close($ch);


$bagArraycr = (array) json_decode($result);



?>


<fieldset>
<legend><img src="images\Magazzino.png" width="10%"><a name="management"><b> SACCHE ESPOSTE </b></a><br><small>(Sacche richiedibili dai ctt)</small></legend>
<!-- campi tabella -->
<center>
    <table>
        <tr>
            <td align="center"><b>seriale</b></td>
            <td align="center"><b>group</b></td>
            <td align="center"><b>donator</b></td>
            <td align="center"><b>creationDate</b></td>
            <td align="center"><b>expirationDate</b></td>
            <td align="center"><b>state</b></td>
            <td align="center"><b>notes</b></td>
            <td align="center"><b>used</b></td>
        </tr>

        <!-- in php lancio solo il for in html riempo la tabella -->
        <tr>


            <!-- in php lancio solo il for in html riempo la tabella -->
            <!-- in php lancio solo il for in html riempo la tabella -->
            <?php foreach (array_keys($bagArraycr) as $key) { ?>
                <td><input type="text" class="textcss" name="id_<?php echo $bagArraycr[$key]->serial; ?>" value="<?php echo $bagArraycr[$key]->serial; ?>" size="40" disabled /></td>
                <td><input type="text" class="textcss" name="group_<?php echo $bagArraycr[$key]->serial; ?>" value="<?php echo $bagArraycr[$key]->group; ?>" disabled /></td>
                <td><input type="text" class="textcss" name="donator_<?php echo $bagArraycr[$key]->serial; ?>" value="<?php echo $bagArraycr[$key]->donator; ?>" disabled /></td>
                <td><input type="text" class="textcss" name="creationDate_<?php echo $bagArraycr[$key]->serial; ?>" value="<?php echo date('Y-m-d', $bagArraycr[$key]->creationDate / 1000); ?>" disabled /></td>
                <td><input type="text" class="textcss" name="expirationDate_<?php echo $bagArraycr[$key]->serial; ?>" value="<?php echo date('Y-m-d', $bagArraycr[$key]->expirationDate / 1000); ?>" disabled /></td>
                <td><input type="text" class="textcss" name="state_<?php echo $bagArraycr[$key]->serial; ?>" value="<?php echo $bagArraycr[$key]->state; ?>" disabled /></td>
                <td><input type="text" class="textcss" name="notes_<?php echo $bagArraycr[$key]->serial; ?>" value="<?php echo $bagArraycr[$key]->notes; ?>" disabled /></td>
                <td><input type="text" class="textcss" name="usedTimeStamp_<?php echo $bagArraycr[$key]->serial; ?>" value="<?php echo date('Y-m-d', $bagArraycr[$key]->usedTimeStamp / 1000); ?>" disabled /></td>
            
        </tr>
    <?php
            }
    ?>
    </table>
</center>