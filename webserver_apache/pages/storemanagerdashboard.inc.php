<script src="./js/html5-qrcode.min.js"></script>
<body style="background-color:aliceblue">


	<!-- START: new user fields -->
      <center><h2>===========NEW SACCA===========</h2></center>
 <center> <table>
        <tr>
         
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
         
            <td><input type="text" name="note" id="note" /></td>
         
            <td>
		<input type="text" name="addBloodBagURL" id="addBloodBagURL" value="http://localhost:8087/bloodbag/add" hidden="yes" />
                <input type="submit" value="Crea" onclick="addBloodBag(document.getElementById('addBloodBagURL').value, '<?php echo($token); ?>', document.getElementById('group').value, document.getElementById('donator').value, document.getElementById('note').value);setTimeout(function () { location.reload(1); }, 1000)">
            </td>
        </tr>
        </table> <center>
	<center><h2>===========IMPORT SACCHE===========</h2></center>
    <center>scannerizza il QR code della sacca premi il tasto "import sacca" per confermare</center>
    <center> <table>
	 <tr>
            <td><input type="text" name="serial" id="serial" placeholder="seriale sacca" size="100" disabled/></td>
        
            <td>
		<input type="text" name="addBloodBagCURL" id="addBloodBagCURL" value="http://localhost:8087/bloodbag/import" hidden="yes" />
                <input type="submit" value="import sacca" onclick="addBloodBagCentral(document.getElementById('addBloodBagCURL').value, '<?php echo($token); ?>', document.getElementById('serial').value);setTimeout(function () { location.reload(1); }, 1000)">
            </td>
        </tr>
     
        </table> <center>
   

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
            <legend><img src="images\Magazzino.png" width="10%"><a name="management" ><b> Magazzino </b></a><br><small>(Visualizza sacche disponibili nel magazzino)</small></legend>

<!-- campi tabella -->
 
    <form action="" method="POST">
     <table>
    
        <tr>
<!-- in php lancio solo il for in html riempo la tabella -->
<?php foreach (array_keys($bagArray ) as $key) { ?>
            <td><input type="text" name="id_<?php echo $bagArray[$key]->serial; ?>" value="<?php echo $bagArray[$key]->serial; ?>" size="40" disabled /></td>
            <td><input type="text" name="group_<?php echo $bagArray[$key]->serial; ?>" value="<?php echo $bagArray[$key]->group; ?>" disabled/></td>
            <td><input type="text" name="donator_<?php echo $bagArray[$key]->serial; ?>" value="<?php echo $bagArray[$key]->donator; ?>" disabled /></td>
            <td><input type="text" name="creationDate_<?php echo $bagArray[$key]->serial; ?>" value="<?php echo date('Y-m-d', $bagArray[$key]->creationDate/1000); ?>" disabled/></td>
            <td><input type="text" name="expirationDate_<?php echo $bagArray[$key]->serial; ?>" value="<?php echo date('Y-m-d', $bagArray[$key]->expirationDate/1000); ?>" disabled/></td>
  	    <td><input type="text" name="state_<?php echo $bagArray[$key]->serial; ?>" value="<?php echo $bagArray[$key]->state; ?>" disabled/></td>
	    <td><input type="text" name="notes_<?php echo $bagArray[$key]->serial; ?>" value="<?php echo $bagArray[$key]->notes; ?>" disabled/></td>
            <td><input type="text" name="usedTimeStamp_<?php echo $bagArray[$key]->serial; ?>" value="<?php echo $bagArray[$key]->usedTimeStamp; ?>" disabled/></td>
            <td>
                
                <input type="submit" value="PRELEVA" onclick="useBloodBag('http://localhost:8087/bloodbag/use/<?php echo $bagArray[$key]->serial; ?>', '<?php echo($token); ?>'); /*setTimeout(function () { location.reload(1); }, 1000)*/">
            </td>
        </tr>
<?php
}
?>