
//=================Users Methods: POST
function addUser(url, token, username, password, email, userRole, loginAttempts, activeUser){
//var url='http://localhost:8087/authenticate';
/*
e.g.:
URI: http://localhost:8087/register
HTTPMethod: DELETE
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
   
function deleteUser(token, username){
//var url='http://localhost:8087/authenticate';
/*
e.g.:
http://localhost:8087/user/delete/username/{username}
HTTPMethod: DELETE
HTTPBody:
{
  "username" :"Hermann80",
}
*/

}