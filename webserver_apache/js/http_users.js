
//=================Users Methods: POST
function addUser(url, token, username, password, email, userRole, loginAttempts, activeUser){
//var url='http://localhost:8087/authenticate';
/*
e.g.:
URI: http://localhost:8087/register
HTTPMethod: POST
HTTPBody:
{
  "username" :"Hermann80",
  "password" : "PasswordBuona1@",
  "email" : "luca1@gmail.com",
  "userRole" : "ROLE_ADMINISTRATOR"
}
*/

 k=0;
 if(url == "") 		{ alert("url null"); 		k=1; }
 if(token == "") 	{ alert("token null"); 		k=1; }
 if(username == "") 	{ alert("username null"); 	k=1; }
 //if(password == "") 	{ alert("password null");  	k=1; }
 //if(email == "") 	{ alert("email null");  	k=1; }
 if(userRole == "") 	{ alert("userRole null");  	k=1; }

 if(k==0){
  alert(url + " - " + token + " - " + username + " - " + password + " - " + email + " - " + userRole);
  var jsonBody 	 = '{' + '"username": "' + username + '", "password": "' + password + '", "email": ';
  if(email=="") { jsonBody += 'null'; } else { jsonBody +='"'+email+'"'; }
  jsonBody 	+= ', "userRole": "' + userRole + '", "loginAttempts": ' + loginAttempts + ', "activeUser": ' + activeUser + '}';
  alert("jsonBody: " + jsonBody);
  console.log("jsonBody: " + jsonBody);
  HTTPPost(url, token, jsonBody);
 }
}


function updateUser(url, token, id, username, temppass, email, role, loginAttempts, activeUser){
alert("dentro");
//var url='http://localhost:8087/user/update';
/*
e.g.:
HTTPMethod: POST
HTTPBody: 
 {
  "idUser": 1,
  "username": "Hermann80",
  "email": "heraaaamann@care.it",
  "userRole": "ROLE_ADMINISTRATOR",
  "loginAttempts": 0,
  "activeUser": 2
 }
*/
 k=0;
 if(url == "") 		{ k=1; } else { k=0; }
 if(token == "") 	{ k=1; } else { k=0; }
 if(username == "") 	{ k=1; } else { k=0; }
 //if(password == "") 	{ k=1; } else { k=0; }
 //if(email == "") 	{ k=1; } else { k=0; }
 if(userRole == "") 	{ k=1; } else { k=0; }

 if(k==0 || id==""){
  alert("No changes");
 } else { 
  alert(url + " - " + token + " - " + username + " - " + temppass + " - " + email + " - " + userRole + " - " + loginAttempts + " - " + activeUser);
  var   jsonBody  = '{';
	jsonBody += '"username": "' + username + '"';
        if(temppass!="") { jsonBody += ', "temppass": "' + temppass + '"'; }
        if(email!="")    { jsonBody += ', "email": "'    + email    + '"'; }
	jsonBody += ', "userRole": "' + userRole + '"';
	jsonBody += ', "loginAttempts": "' + loginAttempts + '"';
	jsonBody += ', "activeUser": "' + activeUser + '"';
	jsonBody += '}';
  alert("jsonBody: " + jsonBody);
  console.log("jsonBody: " + jsonBody);
  HTTPPost(url, token, jsonBody);
 }
}

//=================Users Methods: DELETE
function deleteUser(url, token){
//var url='http://localhost:8087/authenticate';
/*
e.g.:
http://localhost:8087/user/delete/username/{username}
HTTPMethod: DELETE
HTTPBody: none
*/
	var positiveMsg='Utente Eliminato';
	HTTPDelete(url, token, positiveMsg);
	location.reload();
}


//=================Users Methods: PATCH
function resetUserPass(url, token){
/*
e.g.
http://localhost:8087/user/patch/resetpassword/username/FakeUser5
HTTPMethod: PATCH
HTTPBody: none
*/
	var jsonBody="";
	var positiveMsg='Eseguito reset password con successo';
//alert(url + "-" + token + "-" + jsonBody + "-" + positiveMsg);
	HTTPPatch(url, token, jsonBody, positiveMsg);
	location.reload();
}