<head>
  <link rel="stylesheet" href="./css/officerdashboard.css">
</head>

<script>
  function filter() {
    setTimeout(function() {
      select = document.getElementById('selected_group');
      group = select['options'][select.selectedIndex].value;
      location.replace('dashboard.php?token=' + '<?php echo ($token);  ?>' + '&subpage=officerdashboard&subpage=officerdashboard&group=' + group);
    }, 1000);
  }



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
    }, 3000);
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
    }, 3000);
  }
</script>


<script>
  <?php
  $group = $_GET['group'];
  if (isset($group) && $group != "none")
    $urlAPI = "http://localhost:8087/bloodbag/get/central/group/" . $group;
  else
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

  $bagsArray = (array) json_decode($result);

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

<body>
  <div>
    <fieldset>
      <legend><img class="imagine" src="images\gestionerichieste.png"><a name="management"><b> Sacche disponibili</b></a><br></legend>
      <label class="bag_filter">
        Ho bisogno di:
        <select class="textcs" id="selected_group" name="selected_group">
          <option value="none">none</option>
          <option value="Apos">A+ </option>
          <option value="Bpos">B+</option>
          <option value="ABpos">AB+</option>
          <option value="ZEROpos">0+</option>
          <option value="Aneg">A-</option>
          <option value="Bneg">B-</option>
          <option value="ABneg">AB-</option>
          <option value="ZEROneg">0-</option>
        </select>
        <input type="button" class="myButton" id="group_button" name="group_button" value="Filtra" onclick="filter();" />
      </label>
      <table>
        <tr>
          <?php ?>
          <th><input type="button" class="myButton" id="request_button" name="request_button" value="Richiedi" onclick="addRequests();" /></th>
          <th align="center">Priorit?? Bassa</th>
          <th align="center">Priorit?? Media</th>
          <th align="center">Priorit?? Alta</th>
          <th align="center"><b>Seriale</b></th>
          <th align="center"><b>Data creazione</b></th>
          <th align="center"><b>Donatore</b></th>
          <th align="center"><b>Data di scadenza</b></th>
          <th align="center"><b>Gruppo</b></th>
          <th align="center"><b>Note</b></th>
          <th align="center"><b>Stato</b></th>
        </tr>
        <tr>
          <?php foreach (array_keys($bagsArray) as $key) {  ?>
            <tr>
              <td align='center'><input type="checkbox" class="textcss" id="selected_bags" name="selected_bags[]" value="<?php echo ($bagsArray[$key]->serial); ?>"></td>
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
              <td><input type="text" class="textcss" id="serial" name="serial" value="<?php echo $bagsArray[$key]->serial; ?>" disabled /></td>
              <td><input type="text" class="textcss" id="creation_date" name="creation_date" value="<?php echo date('Y-m-d', $bagsArray[$key]->creationDate / 1000); ?>" disabled /></td>
              <td><input type="text" class="textcss" id="donator" name="donator" value="<?php echo $bagsArray[$key]->donator; ?>" disabled /></td>
              <td><input type="text" class="textcss" id="expiration_date" name="expiration_date" value="<?php echo date('Y-m-d', $bagsArray[$key]->expirationDate / 1000); ?>" disabled /></td>
              <td><input type="text" class="textcss" id="group" name="group" value="<?php echo $bagsArray[$key]->group; ?>" disabled /></td>
              <td><input type="text" class="textcss" id="notes" name="notes" value="<?php echo $bagsArray[$key]->notes; ?>" disabled /></td>
              <td><input type="text" class="textcss" id="state" name="state" value="<?php echo $bagsArray[$key]->state; ?>" disabled /></td>
            </tr>
        </tr>
          <?php
          }; ?>
      </table>
    </fieldset>
  </div>
  <!-- END: BloodBags viewer-->

  <br /><br /><br />
  <!-- START:   Officer Requests -->
  <div>
    <fieldset>
      <legend><img class="imagine" src="images\broadcastchat.png"><b> Richieste:</b></legend>
      <table>
        <tr>
          <th><input type="button" class="myButton" id="del_request_button" name="del_request_button" value="Annulla richiesta" onclick="deleteRequests();" /></th>
          <th align="center">Id del nodo</th>
          <th align="center">Seriale</th>
          <th align="center">Data</th>
          <th align="center">Note</th>
          <th align="center">Stato</th>
          <th align="center">Priorit??</th>
        </tr>
        <?php foreach (array_keys($requestsArray) as $key) { ?>
          <tr>
            <td align='center'><input type="checkbox" type="text" class="textcss" id="selected_requests" name="selected_requests[]" value="<?php echo ($requestsArray[$key]->serial); ?>" /></td>
            <td><input type="text" class="textcss" id="id_requester" name="id_requester" value="<?php echo $requestsArray[$key]->id_requester ?>" disabled></td>
            <td><input size="30px" class="textcss" type="text" id="serial" name="serial" value="<?php echo $requestsArray[$key]->serial ?>" disabled></td>
            <td><input type="text" class="textcss" id="date" name="date" value="<?php echo $requestsArray[$key]->date ?>" disabled></td>
            <td><input type="text" class="textcss" id="note" name="note" value="<?php echo $requestsArray[$key]->note ?>" disabled></td>
            <td><input type="text" class="textcss" id="state" name="state" value="<?php echo $requestsArray[$key]->state ?>" disabled></td>
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
    </fieldset>
  </div>
  <!-- END:  Officer Requests -->