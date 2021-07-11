<?php
//?token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJnaXVsaWFubzgwIiwiaXNST0xFX0FETUlOSVNUUkFUT1IiOnRydWUsImV4cCI6MTYyNTA4MTQ1MCwiaWF0IjoxNjI1MDc2NDUwfQ.jJJT9s-opT_R7gl0sSZr3MRpdlCxCPoQ97-nAkB1YKjGgzfu_bBz8PLjaltoQQZr1sOBx5Fs_SdZBMlnVh9cbw

$roles[0] = "ROLE_ADMINISTRATOR";
$roles[1] = "ROLE_STOREMANAGER";
$roles[2] = "ROLE_OFFICER";
$roles[3] = "ROLE_CENTRAL_ADMINISTRATOR";
$roles[4] = "ROLE_CENTRAL_STOREMANAGER";

@$token   = $_GET['token'];
@$subpage = $_GET['subpage'];

$arrayToken 		= explode(".", $token);
$subtoken   		= $arrayToken['1'];
$decodedToken 		= base64_decode($subtoken);
echo("subtoken:".$decodedToken);

$arrayTokenDecoded 	= (array) json_decode($decodedToken, true);
$username 		= $arrayTokenDecoded['sub'];
/*
$role			= array_keys($arrayTokenDecoded)[1];
$roleValue		= $arrayTokenDecoded[				// value of role
				array_keys($arrayTokenDecoded)[1]	// role
		  	  ];
*/
$role			= $arrayTokenDecoded['userRole'];
$exp 			= $arrayTokenDecoded['exp'];
$iat 			= $arrayTokenDecoded['iat'];

if(!isset($token) OR time()>$exp ) {
    //header('WWW-Authenticate: Basic realm="My Realm"');
    header('HTTP/1.0 401 Unauthorized');
    echo 'HTTP/1.0 401 Unauthorized';
    exit;
}
?>

<!DOCTYPE HTML> 
<html>
 <head>
   <title>CARE - Centro Accoglienza Regionale Ematica</title>
   <script src="./js/XMLHTTPRequest.js"></script>
   <script src="./js/http_request.js"></script>
   <script src="./js/http_users.js"></script>
   <script src="./js/http_localbloodbags.js"></script>
   <script src="./js/http_centralbloodbags.js"></script>
   <script src="./js/countdown.js"></script>
   <link rel="stylesheet" href="./css/dashboard.css">
  </head>

  <body onload="javascript:document.getElementById('textarea_chatbroadcast_msgs').scrollTop=document.getElementById('textarea_chatbroadcast_msgs').scrollHeight">

<?php
echo("<br>" . "Ciao " . $username . "<br>");
echo("il tuo ruolo è: " . $role );
echo("<br>" . "Hai creato il token: ");
echo date('l dS \o\f F Y h:i:s A', $iat);
echo("<br>" . "Scadenza token: ");
echo date('l dS \o\f F Y h:i:s A', $exp); echo("<br>");

switch($role){
 case "ROLE_ADMINISTRATOR": 
	echo("sono Admin Faccio quello che voglio");
	break;
 case "ROLE_STOREMANAGER":
	echo("Magazziniere");
	break;
 case "ROLE_OFFICER":
	echo("Segretaria");
	break;
case "ROLE_CENTRAL_STOREMANAGER":
    echo("Segretaria");
    break;
 default:
	echo("Unauthorized");
	break;
}
?>

<div align="right">
 Token countdown:
 <script>
  // Set the date we're counting down to
  //var countDownDate = new Date("Jul 12, 2021 15:37:25").getTime();
  //var countDownDate =new Date(1625215858 * 1000).getTime();
  var countDownDate =new Date(<?php echo($exp); ?> * 1000).getTime();
 </script>
 <p id="countdown"></p>
</div>

    <div class="sidenav">
        <div id="logo">
            <img src="images\logo.png" alt="logo" width="30%" />
        </div>
	<?php
	 switch($role){
	   case "ROLE_ADMINISTRATOR": 	     
	     include("./pages/administratormenu.inc.php");
	     break;
	   case "ROLE_STOREMANAGER":	     
	     include("./pages/storemanagermenu.inc.php");
	     break;
	   case "ROLE_OFFICER":
	     include("./pages/officermenu.inc.php");
	     break;
     case "ROLE_CENTRAL_STOREMANAGER":
       include("./pages/centralstoremanagermenu.inc.php");
       break;
	   default:
	     echo("Unauthorized");
	     break;
	}
 	?>
        <hr>
        <p>Next Version:</p>
            <ul>
                <li>Manage Patients</li>
                <li>Manage Donators</li>
            </ul>
      </div>

      <div class="main">
           <!--
        <div class="alert warningg">
            <span class="closebtn">&times;</span>  
            <strong>Warning!</strong> Stai per superare il quantitativo di scorta massima prevista per A+
         </div>
         <div class="alert">
            <span class="closebtn">&times;</span>  
            <strong>Danger!</strong> La sacca XXX_SERIAL_XXX gruppo A- è scaduta
          </div>
          
          
          <div class="alert success">
            <span class="closebtn">&times;</span>  
            <strong>Success!</strong> Indicates a successful or positive action.
          </div>
         

          <div class="alert info">
            <span class="closebtn">&times;</span>  
            <strong>Info!</strong> Dai priorità alle sacche in scadenza.
          </div>

          <div class="alert warning">
            <span class="closebtn">&times;</span>  
            <strong>Warning!</strong> Sangue AB+ in scadenza tra una settimana nella struttura codice 201
          </div>
        -->

	<?php
         @$subpage = $_GET['subpage'];
	 switch($role){
	   case "ROLE_ADMINISTRATOR": 
	     $allowed_pages 	= array("admin_users_manager", "admin_log_manager", "admin_node_manager", "admin_personal_profile"); // admin allowed pages
	     break;
	   case "ROLE_STOREMANAGER":
	     $allowed_pages      = array("storemanagerdashboard", "storemanager_requests");
	     break;
	   case "ROLE_OFFICER":
	     $allowed_pages 	= array("officerdashboard", "officer_personal_profile");
	     break;
     case "ROLE_CENTRAL_STOREMANAGER":
        $allowed_pages 	= array("centralstoremanagerdashboard");
        break;
	   default:
	     echo("Unauthorized");
	     break;
	}
 	 if( in_array($subpage, $allowed_pages) ) {
  	   include("./pages/" . $subpage . ".inc.php");
         } else {
	   echo('Accedi dal menù nella pagina a te concessa.'.$subpage);
	 }
 	?>

	<?php
/*
	 switch($role){
	   case "ROLE_ADMINISTRATOR": 
	     include("./pages/administratordashboard.inc.php");
	     break;
	   case "ROLE_STOREMANAGER":
	     include("./pages/storemanagerdashboard.inc.php");
	     break;
	   case "ROLE_OFFICER":
	     include("./pages/officerdashboard.inc.php");
	     break;
	   default:
	     echo("Unauthorized");
	     break;
	}
*/
 	?>
       
        <!-- START: BROADCAST CHAT 
        <fieldset>
            <legend><img src="images\broadcastchat.png" width="10%"><a name="broadcastchat"><b> Broadcast Chat</b></a></legend>
            <textarea id="textarea_chatbroadcast_msgs" disabled>



[31/03/2021 11:15:16 (UTC+01:00)]   @Cardarelli>   Abbiamo 2 sacche di A- in scadenza se vi servono sennò in settimana dobbiamo cestinarle che scadono

[31/03/2021 11:15:16 (UTC+01:00)]   @Malzoni>   Abbiamo 5 sacche di AB+ in scadenza, possono servire a qualcuno?

[01/04/2021 14:50:31 (UTC+01:00)]   @Rummo>   Noi abbiamo 0- in scadenza, qualcuno lo vuole?

[01/04/2021 15:01:18 (UTC+01:00)]   @Malzoni>   No grazie...

[16/04/2021 12:41:48 (UTC+01:00)]   @Rummo>   Abbiamo terminato le scorte di A+ che vogliamo fare? Dobbiamo usare lo 0- o ci aiutate?

[16/04/2021 12:50:09 (UTC+01:00)]   @Malzoni>   Purtroppo noi non stiamo messi meglio

[16/04/2021 13:01:15 (UTC+01:00)]   @CARE>   Provvedrà la sede centrale a far partire un approvvigionamento di scorte straordinario.

             </textarea>
            <form action="#" method="POST">
                <textarea id="textarea_chatbroadcast_msg"></textarea>
                <input type="submit" value=">" />
            </form>
        </fieldset>
        END: BROADCAST CHAT -->
      </div>
      <script>
        /* START: alert */
        var close = document.getElementsByClassName("closebtn");
        var i;
        
        for (i = 0; i < close.length; i++) {
          close[i].onclick = function(){
            var div = this.parentElement;
            div.style.opacity = "0";
            setTimeout(function(){ div.style.display = "none"; }, 600);
          }
        }
        /* END: alert */
    </script>
  </body>
</html>