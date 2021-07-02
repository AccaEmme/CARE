<?php
//?token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJnaXVsaWFubzgwIiwiaXNST0xFX0FETUlOSVNUUkFUT1IiOnRydWUsImV4cCI6MTYyNTA4MTQ1MCwiaWF0IjoxNjI1MDc2NDUwfQ.jJJT9s-opT_R7gl0sSZr3MRpdlCxCPoQ97-nAkB1YKjGgzfu_bBz8PLjaltoQQZr1sOBx5Fs_SdZBMlnVh9cbw

@$token = $_GET['token'];

$arrayToken 		= explode(".", $token);
$subtoken   		= $arrayToken['1'];
$decodedToken 		= base64_decode($subtoken);
echo("subtoken:".$decodedToken);

$arrayTokenDecoded 	= (array) json_decode($decodedToken, true);
$username 		= $arrayTokenDecoded['sub'];
$role			= array_keys($arrayTokenDecoded)[1];
$roleValue		= $arrayTokenDecoded[				// value of role
				array_keys($arrayTokenDecoded)[1]	// role
		  	  ];
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
   <script src="./js/countdown.js"></script>
   <link rel="stylesheet" href="./css/dashboard.css">
  </head>

  <body onload="javascript:document.getElementById('textarea_chatbroadcast_msgs').scrollTop=document.getElementById('textarea_chatbroadcast_msgs').scrollHeight">

<?php
echo("<br>");
echo("Ciao " . $username . "<br>");
if( $roleValue == 1  ) { echo("il tuo ruolo è: " . $role ); }
echo("<br>");
echo("Hai creato il token: ");
echo date('l dS \o\f F Y h:i:s A', $iat);
echo("<br>");
echo("Scadenza token: ");
echo date('l dS \o\f F Y h:i:s A', $exp);
echo("<br>");
switch($role){
 case "isROLE_ADMINISTRATOR": 
	echo("Faccio quello che voglio");
	break;
 case "isBoh":
	echo("chitisape");
	break;
 default:
	echo("Unauthorized");
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
        <img src="images\dashboard.png" weight="3%" height="3%" /><a href="#">Dashboard</a><br />
        <img src="images\magazzino.png" weight="3%" height="3%" /><a href="#warehouse">In Magazzino</a><br />
        <img src="images\scadenza.png" weight="3%" height="3%" /><a href="#expiring">In Scadenza</a><br />
        <img src="images\broadcastchat.png" weight="3%" height="3%" /><a href="#broadcastchat">Broadcast Chat</a><br />
        <img src="images\exit.png" weight="3%" height="3%" /><a href="login.html">Logout <small>(206-001)</small></a><br />
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

           <!-- START: Expiring BloodBags -->
        <fieldset>
            <legend><img src="images\scadenza.png" width="10%"><a name="expiring"><b> In Scadenza</b></a></legend>
            <table id="BloodBags-table" border="1" style="font-size: 20px;" >
                <tr>
                    <td align="center">-</td>
                   <!--  <td align="center"><p>In Locale:</p></td> -->
                    <td align="center"><p>Sede Centrale:</p></td>
                    <td align="center"><p>Altri Nodi:</p></td>
                </tr>
                <tr>
                    <td align="center">
                        <p>A+</p>
                    </td>
                    <!--  <td align="center">
                        <p class="error">5 sacche</p>
                    </td>-->
                    <td align="center">
                        <p class="correct">0 sacche</p>
                    </td>
                    <td align="center">
                        <p class="warning">1 sacche</p>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <p>A-</p>
                    </td>
                    <!--  <td align="center">
                        <p class="error">2 sacche</p>
                    </td>-->
                    <td align="center">
                        <p class="warning">4 sacche</p>
                    </td>
                    <td align="center">
                        <p class="correct">0 sacche</p>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <p>B+</p>
                    </td>
                    <!--  <td align="center">
                        <p class="error">3 sacche</p>
                    </td>-->
                    <td align="center">
                        <p class="correct">0 sacche</p>
                    </td>
                    <td align="center">
                        <p class="warning">2 sacche</p>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <p>B-</p>
                    </td>
                    <!--  <td align="center">
                        <p class="error">5 sacche</p>
                    </td>-->
                    <td align="center">
                        <p class="correct">0 sacche</p>
                    </td>
                    <td align="center">
                        <p class="error">6 sacche</p>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <p>AB+</p>
                    </td>
                    <!--  <td align="center">
                        <p class="error">0 sacche</p>
                    </td>-->
                    <td align="center">
                        <p class="warning">4 sacche</p>
                    </td>
                    <td align="center">
                        <p class="correct">0 sacche</p>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <p>AB-</p>
                    </td>
                    <!--  <td align="center">
                        <p class="error">4 sacche</p>
                    </td>-->
                    <td align="center">
                        <p class="warning">3 sacche</p>
                    </td>
                    <td align="center">
                        <p class="warning">2 sacche</p>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <p>0+</p>
                    </td>
                    <!--  <td align="center">
                        <p class="error">0 sacche</p>
                    </td>-->
                    <td align="center">
                        <p class="correct">0 sacche</p>
                    </td>
                    <td align="center">
                        <p class="correct">0 sacche</p>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <p>0-</p>
                    </td>
                    <!--  <td align="center">
                        <p class="error">0 sacche</p>
                    </td>-->
                    <td align="center">
                        <p class="correct">0 sacche</p>
                    </td>
                    <td align="center">
                        <p class="correct">0 sacche</p>
                    </td>
                </tr>

            </table>


          <!-- START:  BAGS THAT ARE EXPIRING-->
          <form action="#" method="POST">
            <div align="center">
                <body style="margin:0px;">
                <table cellspacing="0" cellpadding="0" height:"100%" width="100%" >
                <tr style="color:white;background-color:rgb(150, 145, 145);">
                    <th>Lista:</th>
                </tr>
                <tr>
                  <td>
                    <body style="margin:0px;">
                        <table cellspacing="0" cellpadding="0" height:"100%" width="100%" >
                       <tr style="color:white;background-color:grey; font-size: 20px;">
                        <th width="100px">Data Creazione</th>
                        <th width="250px">Seriale</th>
                        <th width="80px">Gruppo sanguigno</th>
                        <th width="85px">Puo' donare a</th>
                        <th width="116px">Azione</th>
                       </tr>
                     </table>
                     </body>
                  </td>
                </tr>

                <br></br>

                <tr>
                  <td>
                     <div style=" overflow-y: scroll; height:100px; margin:0px;">
                       <table cellspacing="0" cellpadding="1" border="1" width="100%" style="font-size: 20px;" >
                        <tr bgcolor="red">
                            <td width="125px">01-11-2020 08:11</td>
                            <td width="240px">IT-NA206003-APOS-20201101-0010</td>
                            <td width="80px">Apos</td>
                            <td witdth="80px">A+, A-,0+,0-</td>
                            <td width="80px">
                                <div align ="center">
                                    <input type="button" name="accept_request" value="Utilizza" />
                                </div>
                            </td>
                        </tr>
                        <tr bgcolor="red">
                            <td width="140px">17-12-2020 09:16</td>
                            <td width="277px">IT-AV207005-APOS-20201217-0010</td>
                            <td width="80px">Apos</td>
                            <td width="111px">A+, A-,0+,0-</td>
                            <td width="111px">
                                <div align ="center">
                                    <input type="button" name="accept_request" value="Utilizza" />
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
        <!-- END:  StoreManager Requests -->
        </fieldset>
        <!-- END:   Expiring BloodBags -->


          <fieldset>
            <legend><img src="images\gestionerichieste.png" width="10%"><a name="management"><b> Gestione Richieste </b></a><br><small>(role-user-visibility: Officer)</small></legend>
          <!-- START:   Management  BloodBags -->
            <form action="#" method="POST">
                <div align="center">
                <body style="margin:0px;">
                <table cellspacing="0" cellpadding="0" height:"100%" width="100%" >
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
                        <table cellspacing="0" cellpadding="0" height:"100%" width="100%" >
                           <tr style="color:white;background-color:grey; font-size: 20px;">
                            <th width="108px">Data Creazione</th>
                            <th width="200px">Seriale</th>
                            <th width="5px">Gruppo sanguigno</th>
                            <th width="200px">Puo' donare a</th>
                            <th width="65px">Sede Magazzino</th>
                            <th width="134px">
                                <input type="button" name="request" value="Richiedi"  />
                                <input type="button" name="cancel_request" value="Annulla richiesta" disabled/>
                                <input type="button" name="report" value="Segnala" />
                            </th>
                           </tr>
                         </table>
                         </body>
                      </td>
                    </tr>
                    <tr>
                      <td>
                         <div style="margin:0px; overflow-y: scroll; height:250px;">
                           <table cellspacing="0" cellpadding="1" border="1" width="100%" style="font-size: 20px;" >
                            <tr bgcolor="red">
                                <td width="125px">01-11-2020 08:11</td>
                                <td width="240px">IT-NA206003-APOS-20201101-0010</td>
                                <td width="80px">Apos</td>
                                <td witdth="80px">A+, A-,0+,0-</td>
                                <td width="200px">NA206</td>
                                <td width="80px">
                                    <div align ="center">
                                        <input type="checkbox" name="XXX_SERIAL_XXX" />
                                    </div>
                                </td>
                            </tr>
                            <tr bgcolor="red">
                                <td width="140px">17-12-2020 09:16</td>
                                <td width="277px">IT-AV207005-APOS-20201217-0010</td>
                                <td width="80px">Apos</td>
                                <td width="111px">A+, A-,0+,0-</td>
                                <td width="80px">AV207</td>
                                <td width="111px">
                                    <div align ="center">
                                        <input type="checkbox" name="XXX_SERIAL_XXX" />
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td width="140px">16-04-2021 11:36</td>
                                <td width="277px">IT-NA206001-APOS-20210416-0001</td>
                                <td width="80px">Apos</td>
                                <td width="111px">A+, A-,0+,0-</td>
                                <td width="80px">NA206</td>
                                <td width="111px">
                                    <div align ="center">
                                        <input type="checkbox" name="XXX_SERIAL_XXX" />
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td width="140px">16-04-2021 11:36</td>
                                <td width="277px">IT-NA206001-ABPOS-20210416-0002</td>
                                <td width="80px">ABpos</td>
                                <td width="111px">A+,A-,B+,B-,AB+,AB-,0+,0-</td>
                                <td width="80px">NA206</td>
                                <td width="111px">
                                    <div align ="center">
                                        <input type="checkbox" name="XXX_SERIAL_XXX" />
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td width="140px">16-04-2021 11:36</td>
                                <td width="277px">IT-NA206003-BNEG-20210416-0003</td>
                                <td width="80px">Bneg</td>
                                <td width="111px">B-,0-</td>
                                <td width="80px">NA206</td>
                                <td width="111px">
                                    <div align ="center">
                                        <input type="checkbox" name="XXX_SERIAL_XXX" />
                                    </div>
                                </td>
                            </tr>
                            <tr bgcolor="yellow">
                                <td width="140px">17-04-2021 11:36</td>
                                <td width="277px">IT-AV207005-APOS-20210417-0010</td>
                                <td width="80px">Apos</td>
                                <td width="111px">A+,A-,0+,0-</td>
                                <td width="80px">AV207</td>
                                <td width="111px">
                                    <div align ="center">
                                        <input type="checkbox" name="XXX_SERIAL_XXX" />
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td width="140px">18-04-2021 11:36</td>
                                <td width="277px">IT-NA206005-APOS-20210418-0001</td>
                                <td width="111px">Apos</td>
                                <td width="111px">A+,A-,0+,0-</td>
                                <td width="80px">NA206</td>
                                <td width="111px">
                                    <div align ="center">
                                        <input type="checkbox" name="XXX_SERIAL_XXX" />
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td width="140px">19-04-2021 11:36</td>
                                <td width="277px">IT-NA206002-APOS-20210419-0001</td>
                                <td width="111px">Apos</td>
                                <td width="111px">A+,A-,0+,0-</td>
                                <td width="80px">NA206</td>
                                <td width="111px">
                                    <div align ="center">
                                        <input type="checkbox" name="XXX_SERIAL_XXX" />
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td width="140px">19-04-2021 11:36</td>
                                <td width="277px">IT-NA206003-BNEG-20210419-0003</td>
                                <td width="80px">Bneg</td>
                                <td width="111px">B-,0-</td>
                                <td width="80px">NA206</td>
                                <td width="111px">
                                    <div align ="center">
                                        <input type="checkbox" name="XXX_SERIAL_XXX" />
                                    </div>
                                </td>
                            </tr>
                            <tr bgcolor="yellow">
                                <td width="140px">19-04-2021 11:36</td>
                                <td width="277px">IT-AV207005-APOS-20210419-0010</td>
                                <td width="80px">Apos</td>
                                <td width="111px">A+,A-,0+,0-</td>
                                <td width="80px">AV207</td>
                                <td width="111px">
                                    <div align ="center">
                                        <input type="checkbox" name="XXX_SERIAL_XXX" />
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td width="140px">19-04-2021 11:36</td>
                                <td width="277px">IT-NA206002-APOS-20210419-0001</td>
                                <td width="111px">Apos</td>
                                <td width="111px">A+,A-,0+,0-</td>
                                <td width="80px">NA206</td>
                                <td width="111px">
                                    <div align ="center">
                                        <input type="checkbox" name="XXX_SERIAL_XXX" />
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td width="140px">19-04-2021 11:36</td>
                                <td width="277px">IT-NA206003-BNEG-20210419-0003</td>
                                <td width="80px">Bneg</td>
                                <td width="111px">B-,0-</td>
                                <td width="80px">NA206</td>
                                <td width="111px">
                                    <div align ="center">
                                        <input type="checkbox" name="XXX_SERIAL_XXX" />
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
        <!-- END:   Management BloodBags -->
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



        <!-- START:   Availability BloodBags -->
        <fieldset>
            <legend><img src="images\magazzino.png" width="10%"><a name="warehouse"><b>In Magazzino</b></a><br><small>(role-user-visibility: StoreManager)</small></legend>           
            <table id="BloodBags-table">
                <tr>
                    <td id="BloodBags-td">
                        <table border="0">
                            <tr>
                                <td>
                                <td align="left" ><p>A+</p>
                                </td>
                            </tr>
                                <td align="left"><a href="#"><img src="images\SAccaVuota.png" alt="plusbutton" width="150%" /></a></td>
                                </td>
                            <td>&nbsp;</td>
                                <td align="left"><a href="#"><img src="images\plus.png" alt="plusbutton" width="20%" /></a></td>
                            </tr>
                        </table>
                    </td>
                    <td id="BloodBags-td">
                        <table border="0">
                            <tr>
                                <td>
                                <td align="left"><p>B+</p>
                                </td>
                            </tr>
                            <td align="left"><a href="#"><img src="images\SAccaMetà.png" alt="plusbutton" width="150%" /></a></td>
                        </td>
                    <td>&nbsp;</td>
                        <td align="left"><a href="#"><img src="images\plus.png" alt="plusbutton" width="20%" /></a></td>
                    </tr>
                </table>
                    </td>
                    <td id="BloodBags-td">
                        <table border="0">
                            <tr>
                                <td>
                                <td align="left"><p>0+</p>
                                </td>
                            </tr>
                                <td align="left">
                                    <p class="error">5 sacche</p>
                                </td>
                                <td align="leftyìt"><a href="#"><img src="images\plus.png" alt="plusbutton" width="20%" /></a></td>
                                
                            </tr>
                        </table>
                    </td>
                    <td id="BloodBags-td">
                        <table border="0">
                            <tr>
                                <td>
                                <td align="left"><p>AB+</p>
                                    </td>
                            </tr>
                                <td align="left">
                                    <p class="warning">5 sacche</p>
                                </td>
                                <td align="left"><a href="#"><img src="images\plus.png" alt="plusbutton" width="20%" /></a></td>
                                
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td id="BloodBags-td">
                        <table border="0">
                            <tr>
                                <td>
                                <td align="left"><p>A-</p>
                                    </td>
                            </tr>
                                <td align="left">
                                    <p class="warning">5 sacche</p>
                                </td>
                                <td align="left"><a href="#"><img src="images\plus.png" alt="plusbutton" width="20%" /></a></td>
                                
                            </tr>
                        </table>
                    </td>
                    <td id="BloodBags-td">
                        <table border="0">
                            <tr>
                                <td>
                                <td align="left"><p>B-</p>
                                    </td>
                            </tr>
                                <td align="left">
                                    <p class="warning">5 sacche</p>
                                </td>
                                <td align="left"><a href="#"><img src="images\plus.png" alt="plusbutton" width="20%" /></a></td>
                                
                            </tr>
                        </table>
                    </td>
                    <td id="BloodBags-td">
                        <table border="0">
                            <tr>
                                <td>
                                <td align="left"><p>0-</p>
                                    </td>
                            </tr>
                                <td align="left">
                                    <p class="correct">5 sacche</p>
                                </td>
                                <td align="left"><a href="#"><img src="images\plus.png" alt="plusbutton" width="20%" /></a></td>
                                
                            </tr>
                        </table>
                    </td>
                    <td id="BloodBags-td">
                        <table border="0">
                            <tr>
                                <td>
                                <td align="left"><p>AB-</p>
                                    </td>
                            </tr>
                                <td align="left">
                                    <p class="correct">5 sacche</p>
                                </td>
                                <td align="left"><a href="#"><img src="images\plus.png" alt="plusbutton" width="20%" /></a></td>
                                
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>

            <br></br>
            <!-- START:  StoreManager Requests -->
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
                                    <input type="button" name="accept_request" value="Approva" />
                                    <input type="button" name="cancel_request" value="Annulla" />
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
                                    <input type="button" name="accept_request" value="Approva" />
                                    <input type="button" name="cancel_request" value="Annulla" />
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
        <!-- END:  StoreManager Requests -->

        </fieldset>
        <!-- END:   Availability BloodBags -->

       
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