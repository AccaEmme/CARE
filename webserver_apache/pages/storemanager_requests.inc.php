<script src="./js/html5-qrcode.min.js"></script>
<link rel="stylesheet" href="./css/storedashboard.css">


<?php

$urlAPI = "http://localhost:8087/request/get/our";


$authorization = "Authorization: Bearer ".$token;
    $ch = curl_init();
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json' , $authorization ));
    curl_setopt($ch,CURLOPT_URL,$urlAPI);
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "GET");
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

    curl_setopt($ch, CURLOPT_FOLLOWLOCATION, 1);
    $result = curl_exec($ch);
    curl_close($ch);


$BagArray = (array) json_decode($result);




?>


<fieldset>
    <legend><img src="images\gestionerichieste.png" width="10%"><a name="management" ><b> Richieste </b></a><br><small>(visualizza stato delle richieste)</small></legend>


<!-- campi tabella -->
  
<form action="" method="POST">
  <center><table>
         <tr>
            <td align="center"><b>id_requester</b></td>
            <td align="center"><b>serial</b></td>
            <td align="center"><b>date</b></td>
            <td align="center"><b>note</b></td>
            <td align="center"><b>state</b></td>
        </tr>
        
<!-- in php lancio solo il for in html riempo la tabella -->
<tr>
<?php foreach (array_keys($BagArray ) as $key) { ?>
            <td><input type="text" class="textcss" name="id_<?php echo $BagArrayy[$key]->serial; ?>" value="<?php echo $BagArray[$key]->id_requester; ?>" size="40" disabled /></td>
            <td><input type="text" class="textcss" name="serial_<?php echo $BagArray[$key]->serial; ?>" value="<?php echo $BagArray[$key]->serial; ?>" disabled/></td>
            <td><input type="text" class="textcss" name="date_<?php echo $BagArray[$key]->serial; ?>" value="<?php echo $BagArray[$key]->date; ?>" disabled /></td>
            <td><input type="text" class="textcss" name="note_<?php echo $BagArray[$key]->serial; ?>" value="<?php echo $BagArray[$key]->note; ?>" disabled/></td>
            <td><input type="text" class="textcss" name="state_<?php echo $BagArray[$key]->serial; ?>" value="<?php echo $BagArray[$key]->state; ?>" disabled/></td>
        </tr>
<?php
}
?>
	
    
	
	
 </table></center>
</form>


   
     <?php

$urlAPI = "http://localhost:8087/bloodbag/get/state/Available";
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
</fieldset>

<fieldset>
     <legend><img src="images\Magazzino.png" width="10%"><a name="management" ><b> Magazzino </b></a><br><small>(Visualizza sacche spedibili)</small></legend>

<!-- campi tabella -->
<form action="" method="POST">
  <center><table>
         <tr>
            <td align="center"><b>serialr</b></td>
            <td align="center"><b>group</b></td>
            <td align="center"><b>donator</b></td>
            <td align="center"><b>creationDate</b></td>
            <td align="center"><b>xpirationDate</b></td>
            <td align="center"><b>state</b></td>
            <td align="center"><b>notes</b></td>
            <td align="center"><b>used</b></td>
        </tr>
        
<!-- in php lancio solo il for in html riempo la tabella -->
<tr>
    
        
<!-- in php lancio solo il for in html riempo la tabella -->
<!-- in php lancio solo il for in html riempo la tabella -->
<?php foreach (array_keys($bagArray ) as $key) { ?>
            <td><input type="text" class="textcss" name="id_<?php echo $bagArray[$key]->serial; ?>" value="<?php echo $bagArray[$key]->serial; ?>" size="40" disabled /></td>
            <td><input type="text" class="textcss" name="group_<?php echo $bagArray[$key]->serial; ?>" value="<?php echo $bagArray[$key]->group; ?>" disabled/></td>
            <td><input type="text" class="textcss" name="donator_<?php echo $bagArray[$key]->serial; ?>" value="<?php echo $bagArray[$key]->donator; ?>" disabled /></td>
            <td><input type="text" class="textcss" name="creationDate_<?php echo $bagArray[$key]->serial; ?>" value="<?php echo date('Y-m-d', $bagArray[$key]->creationDate/1000); ?>" disabled/></td>
            <td><input type="text" class="textcss" name="expirationDate_<?php echo $bagArray[$key]->serial; ?>" value="<?php echo date('Y-m-d', $bagArray[$key]->expirationDate/1000); ?>" disabled/></td>
  	        <td><input type="text" class="textcss" name="state_<?php echo $bagArray[$key]->serial; ?>" value="<?php echo $bagArray[$key]->state; ?>" disabled/></td>
	        <td><input type="text" class="textcss" name="notes_<?php echo $bagArray[$key]->serial; ?>" value="<?php echo $bagArray[$key]->notes; ?>" disabled/></td>
            <td><input type="text" class="textcss" name="usedTimeStamp_<?php echo $bagArray[$key]->serial; ?>" value="<?php echo date('Y-m-d', $bagArray[$key]->usedTimeStamp/1000); ?>" disabled/></td>
            <td>
                
            <input type="button" value="SPEDISCI" class="myButton" onclick="transferBloodBagC('http://localhost:8087/bloodbag/central/send', '<?php echo($token); ?>', '<?php echo $bagArray[$key]->serial; ?>');setTimeout(function () { location.reload(1); }, 2000);"> </input>
            </td>
        </tr>
<?php
}
?>
</table></center>
</form>
