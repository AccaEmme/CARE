<script src="./js/html5-qrcode.min.js"></script>
   


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
            <td><input type="text" name="id_<?php echo $BagArrayy[$key]->serial; ?>" value="<?php echo $BagArray[$key]->id_requester; ?>" size="40" disabled /></td>
            <td><input type="text" name="serial_<?php echo $BagArray[$key]->serial; ?>" value="<?php echo $BagArray[$key]->serial; ?>" disabled/></td>
            <td><input type="text" name="date_<?php echo $BagArray[$key]->serial; ?>" value="<?php echo $BagArray[$key]->date; ?>" disabled /></td>
            <td><input type="text" name="note_<?php echo $BagArray[$key]->serial; ?>" value="<?php echo $BagArray[$key]->note; ?>" disabled/></td>
            <td><input type="text" name="state_<?php echo $BagArray[$key]->serial; ?>" value="<?php echo $BagArray[$key]->state; ?>" disabled/></td>
        </tr>
<?php
}
?>
	
    
	
	
      </table></center>
     </form>


 