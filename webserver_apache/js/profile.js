//=================Profile Methods: POST
function updateProfile(url, token, username, plaintextpassword, plaintextpassword_2, email){
 //var url='/profile/set/'+token;
 /*
 URI: http://localhost:8087/profile/set/{token}
 e.g.:
 HTTPMethod: PUT
 HTTPBody: 
  {
    "username": "PasswordBuona1@",
    "password": "PasswordBuona1@",
    "email": "lamianuovaemail@care.it"
  }
 */
 var k=0;

 if(url == "")      				{ alert("empty url"); 			k=1; }
 if(token == "")    				{ alert("empty token");			k=1; }
 if(username == "") 				{ alert("empty username"); 		k=1; }
 if(plaintextpassword == "") 			{ alert("empty password"); 		k=1; }
 if(plaintextpassword != plaintextpassword_2) 	{ alert("pass1 mismatch pass2"); 	k=1; }
 if(email == "")				{ alert("empty username"); 		k=1; }

 if(k==1){
  alert("No changes");
 } else { 
  alert(url + " - " + username + " - " + plaintextpassword + " - " + email);
  var   jsonBody  = '{';
	jsonBody += '"username": "'   + username + '"';
        if(plaintextpassword!="") { jsonBody += ', "password": "' + plaintextpassword + '"'; }
        if(email!="")    { jsonBody += ', "email": "'    + email    + '"'; }
	jsonBody += '}';
  alert("jsonBody: " + jsonBody);
  console.log("jsonBody: " + jsonBody);
  var positiveMsg='Profilo Aggiornato';
  HTTPPut(url, token, jsonBody, positiveMsg);
 }
}