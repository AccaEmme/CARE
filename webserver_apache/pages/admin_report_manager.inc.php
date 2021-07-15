<head>
  <link rel="stylesheet" href="./css/admindashboard.css">
</head>

<?php
//echo("ciao ".$token."<br><br>");

function httpRequest($urlAPI, $token, $method, $jsonBody)
{
  //$urlAPI = "http://localhost:8087/user/get/all";
  //$urlAPI = "http://localhost:8087/user/get/notdeleted";
  $authorization = "Authorization: Bearer " . $token;
  $ch = curl_init();
  curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json', $authorization));
  curl_setopt($ch, CURLOPT_URL, $urlAPI);
  curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "GET");
  curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
  curl_setopt($ch, CURLOPT_FOLLOWLOCATION, 1);
  $result = curl_exec($ch);
  curl_close($ch);
  //return json_decode($result);
  //print_r(json_decode($result));

  return ((array) json_decode($result));
}

$usersArray = httpRequest("http://localhost:8087/user/get/notdeleted", $token, "GET", null);
//echo("user: ".$usersArray['idUser'] . " token:" . $usersArray['username']);
foreach (array_keys($usersArray) as $key) {
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

<div align="center">
  <fieldset>
    <legend><img src=""><a><b>Report</b></a></legend>
    <b>User Report</b>:
    <input type="button" value="Download CSV report"  onclick="window.location = 'report/reportUser.csv';" />
    <input type="button" value="Download JSON report" onclick="window.location = 'report/reportUser.json';" />
    <input type="button" value="Download TXT report" onclick="window.location = 'report/reportUser.txt';" />
    <br />
    <b>Bags Report</b>:
    <input type="button" value="Download CSV report"  onclick="window.location = 'report/reportBag.csv';" />
    <input type="button" value="Download JSON report" onclick="window.location = 'report/reportBag.json';" />
    <input type="button" value="Download TXT report" onclick="window.location = 'report/reportBag.txt';" />
    <br />
  </fieldset>
</div>

