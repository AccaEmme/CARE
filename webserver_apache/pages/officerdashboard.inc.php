<!DOCTYPE HTML>
<html>

<head>
  <title>CARE - Centro Accoglienza Regionale Ematica</title>
  <script src="./js/http_request.js"></script>
  <link rel="stylesheet" href="./css/officerdashboard.css">
</head>

<script>
  function addRequests() {

    let serials = document.getElementsByName('selected_bags[]');
    let priorities;

    var note = prompt("Hai da dichiarare qualcosa?");

    for (i = 0; i < serials.length; i++) {
      if (serials.item(i).checked) {
        priorities = document.getElementsByName('priority_' + serials.item(i).value + '[]');
        console.log(serials.item(i).value + priorities);

        for (j = 0; j < 3; j++)
          if (priorities.item(j).checked) {
            console.log(serials.item(i).value + priorities.item(j).value);
            addRequest('<?php echo ($token); ?>', serials.item(i).value, priorities.item(j).value, note);
          }
      }
    }
    setTimeout(function() {
      location.reload(1);
    }, 5000);
  }

  function deleteRequests() {

    let serials = document.getElementsByName('selected_requests[]');

    for (i = 0; i < serials.length; i++) {

      if (serials.item(i).checked) {
        console.log(serials.item(i).value);
        deleteRequest('<?php echo ($token) ?>', serials.item(i).value);
      }
    }
    setTimeout(function() {
      location.reload(1);
    }, 5000);
  }



groups =  document.getElementsByName('group').values;

for(i=0; i<groups.length; i++)
if (groups.item(i).selected) {
  $group = groups.item(i).value;
  $urlAPI = "http://localhost:8087/bloodbag/get/central/group/" . $group;
  $authorization = "Authorization: Bearer " . $token;
  $ch = curl_init();

  curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json', $authorization));
  curl_setopt($ch, CURLOPT_URL, $urlAPI);
  curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "GET");
  curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

  curl_setopt($ch, CURLOPT_FOLLOWLOCATION, 1);
  $result = curl_exec($ch);
  curl_close($ch);

  <?php $bagsArray = (array) json_decode($result); ?>
} else {
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

  <?php $bagsArray = (array) json_decode($result); ?>
}

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

  $requestsArray = (array) json_decode($result);
  ?>
</script>

<fieldset>
  <legend><img src="images\gestionerichieste.png" width="10%"><a name="management"><b> Gestione Richieste </b></a><br><small>(role-user-visibility: Officer)</small></legend>

  <!-- START: BloodBags viewer -->
  <div align="center">

    <body style="margin:0px;">
      <table>
        <tr> Sacche disponibili </tr>
        <tr style="color:white;background-color:rgb(150, 145, 145); font-size: 16px;">
          <th>
            Ho bisogno di:
            <form action="" method="GET">
              <select name="group">
                <option <?php if (isset($group) && $group == "") echo "selected"; ?>></option>
                <option value="Apos" <? //php if (isset($group) && $group=="Apos") echo "selected";
                                      ?>>A+ </option>
                <option value="Bpos" <? //php if (isset($group) && $group=="Bpos") echo "selected";
                                      ?>>B+</option>
                <option value="ABpos" <? //php if (isset($group) && $group=="ABpos") echo "selected";
                                      ?>>AB+</option>
                <option value="ZEROpos" <? //php if (isset($group) && $group=="ZEROpos") echo "selected";
                                        ?>>0+</option>
                <option value="Aneg" <? //php if (isset($group) && $group=="Aneg") echo "selected";
                                      ?>>A-</option>
                <option value="Bneg" <? //php if (isset($group) && $group=="Bneg") echo "selected";
                                      ?>>B-</option>
                <option value="ABneg" <? //php if (isset($group) && $group=="ABneg") echo "selected";
                                      ?>>AB-</option>
                <option value="ZEROneg" <? //php if (isset($group) && $group=="ZEROneg") echo "selected";
                                        ?>>0-</option>
              </select>
              <input type="button" id="group_button" name="group_button" value="Filtra" onclick="    setTimeout(function() {
      location.reload(1);
    }, 5000);" />
            </form>
          </th>
        </tr>
        <tr>
          <td>

            <body style="margin:0px;">
              <table>
                <tr style="color:white;background-color:grey;">
                  <?php ?>
                  <th><input type="button" id="request_button" name="request_button" value="Richiedi" onclick="addRequests();" /></th>
                  <th align="center">Priorità Bassa</th>
                  <th align="center">Priorità Media</th>
                  <th align="center">Priorità Alta</th>
                  <th align="center"><b>Seriale</b></th>
                  <th align="center"><b>Data creazione</b></th>
                  <th align="center"><b>Donatore</b></th>
                  <th align="center"><b>Data di scadenza</b></th>
                  <th align="center"><b>Gruppo</b></th>
                  <th align="center"><b>Note</b></th>
                  <th align="center"><b>Stato</b></th>
                </tr>
                <form action="" method="POST">
                  <?php foreach (array_keys($bagsArray) as $key) {  ?>
                    <tr>
                      <td align='center'><input type="checkbox" id="selected_bags" name="selected_bags[]" value="<?php echo ($bagsArray[$key]->serial); ?>"></td>
                      <td align='center'><label class="container">
                          <input type="radio" id="priority_<?php echo ($bagsArray[$key]->serial); ?>" name="priority_<?php echo ($bagsArray[$key]->serial) . '[]'; ?>" value="green" checked>
                          <span class="checkmark green"></span>
                        </label></td>
                      <td align='center'><label class="container">
                          <input type="radio" id="priority_<?php echo ($bagsArray[$key]->serial); ?>" name="priority_<?php echo ($bagsArray[$key]->serial . '[]'); ?>" value="yellow">
                          <span class="checkmark yellow"></span>
                        </label></td>
                      <td align='center'><label class="container">
                          <input type="radio" id="priority_<?php echo ($bagsArray[$key]->serial); ?>" name="priority_<?php echo ($bagsArray[$key]->serial . '[]'); ?>" value="red">
                          <span class="checkmark red"></span>
                        </label></td>
                      <td><input size="30px" type="text" id="serial" name="serial" value="<?php echo $bagsArray[$key]->serial; ?>" disabled /></td>
                      <td><input type="text" id="creation_date" name="creation_date" value="<?php echo date('Y-m-d', $bagsArray[$key]->creationDate / 1000); ?>" disabled /></td>
                      <td><input type="text" id="donator" name="donator" value="<?php echo $bagsArray[$key]->donator; ?>" disabled /></td>
                      <td><input type="text" id="expiration_date" name="expiration_date" value="<?php echo date('Y-m-d', $bagsArray[$key]->expirationDate / 1000); ?>" disabled /></td>
                      <td><input type="text" id="group" name="group" value="<?php echo $bagsArray[$key]->group; ?>" disabled /></td>
                      <td><input type="text" id="notes" name="notes" value="<?php echo $bagsArray[$key]->notes; ?>" disabled /></td>
                      <td><input type="text" id="state" name="state" value="<?php echo $bagsArray[$key]->state; ?>" disabled /></td>
                    </tr>
                  <?php
                  }; ?>
                </form>
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
        <table>
          <tr style="color:white;background-color:rgb(150, 145, 145);">
            <th>Richieste:</th>
          </tr>
          <tr>
            <td>

              <body style="margin:0px;">
                <table>
                  <tr style="color:white;background-color:grey;">
                    <th><input type="button" id="del_request_button" name="del_request_button" value="Annulla richiesta" onclick="deleteRequests();" /></th>
                    <th align="center">Id del nodo</th>
                    <th align="center">Seriale</th>
                    <th align="center">Data</th>
                    <th align="center">Note</th>
                    <th align="center">Stato</th>
                    <th align="center">Priorità</th>
                  </tr>
                  <?php foreach (array_keys($requestsArray) as $key) { ?>
                    <tr>
                      <td align='center'><input type="checkbox" id="selected_requests" name="selected_requests[]" value="<?php echo ($requestsArray[$key]->serial); ?>" /></td>
                      <td><input type="text" id="id_requester" name="id_requester" value="<?php echo $requestsArray[$key]->id_requester ?>" disabled></td>
                      <td><input size="30px" type="text" id="serial" name="serial" value="<?php echo $requestsArray[$key]->serial ?>" disabled></td>
                      <td><input type="text" id="date" name="date" value="<?php echo $requestsArray[$key]->date ?>" disabled></td>
                      <td><input type="text" id="note" name="note" value="<?php echo $requestsArray[$key]->note ?>" disabled></td>
                      <td><input type="text" id="state" name="state" value="<?php echo $requestsArray[$key]->state ?>" disabled></td>
                      <td align="center">
                        <!-- <input type="text" id="priority" name="priority" value="<?php echo $requestsArray[$key]->priority ?>" disabled> -->
                        <!-- <span class="dot <?php echo $requestsArray[$key]->priority ?>"></span> -->
                        <label class="container_out"><?php if ($requestsArray[$key]->priority == 'green')
                                                        echo 'Bassa';
                                                      elseif ($requestsArray[$key]->priority == 'yellow')
                                                        echo 'Media';
                                                      elseif ($requestsArray[$key]->priority == 'red')
                                                        echo 'Alta'; ?>
                          <span class="checkmark_out <?php echo $requestsArray[$key]->priority; ?>"></span>
                        </label>
                      </td>
                    </tr>
                  <?php } ?>
                </table>
              </body>
            </td>
          </tr>
        </table>
      </body>
    </div>
  </form>
  <!-- END:  Officer Requests -->
</fieldset>