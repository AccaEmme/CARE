function hello(){
 alert("ciao");
}

//=================Method:POST
function login(){
	event.preventDefault();
	let url;
        var ok=0;

	url = 'http://localhost:8087/authenticate';
	let request = new XMLHttpRequest();   // new HttpRequest instance

	let username = document.getElementById("username").value;
	let password = document.getElementById("psw").value;

	request.open("POST", url, true);
	request.setRequestHeader("content-type", "application/json");
	request.send(JSON.stringify({"username": username, "password": password}));
	request.onreadystatechange = function() {
	 if (this.readyState === 4 &&  this.status === 200 ) {
	  ok=1;
	  console.log("token:"+ this.responseText);
	  let results = JSON.parse(this.responseText);
  	  //alert(" provola " + results.token ); 
	  window.open("dashboard.php?token="+results.token);
         } else {
          response = this.responseText; 
	 }
       };
       if(ok==0) alert("===Login failed===\n\n"+response);
}

//================= Methods: HTTP_POST
function HTTPPost(url, token, jsonBodyString){
	event.preventDefault();
	let request = new XMLHttpRequest();   // new HttpRequest instance

	request.open("POST", url, true);
	request.setRequestHeader('Authorization', 'Bearer ' + token);
	request.setRequestHeader("content-type", "application/json");
    request.onerror = function () {
    console.log("** An error occurred during the transaction");
    alert("** An error occurred during the transaction");
};
//	request.send(JSON.stringify(jsonBodyString));
	request.send(jsonBodyString);
	request.onreadystatechange = function() {
	 if (this.readyState === 4 &&  this.status === 200 ) {
	  console.log("Request: token " + token + " jsonBody: " + jsonBodyString);
	  console.log("Response: "+ this.responseText);
	  let results = JSON.parse(this.responseText);
         } else {
    alert("error"+this.responseText)
	 }
       };


}
//================= Methods: HTTP_DELETE
function HTTPDelete(url, token){/*
    event.preventDefault();
	let request2 = new XMLHttpRequest();   // new HttpRequest instance
    alert("PRIMO");
	request2.open("DELETE", url, true);
	request2.setRequestHeader('Authorization', 'Bearer ' + token);
    alert("SECONDO");
	request2.onreadystatechange = function() {
        alert("TERZO");
	 if (this.readyState === 4 &&  this.status === 200 ) {
	  console.log("Request: token " + token );
	  console.log("Response: "+ this.responseText);
	  let results = JSON.parse(this.responseText);
         } else {
alert("error"+this.responseText)
	 }
       };*/


       var xhr = new XMLHttpRequest();
    xhr.open("DELETE", url, true);
    xhr.setRequestHeader('Authorization', 'Bearer ' + token);
   
    xhr.onload = function () {
    
	var users = JSON.parse(xhr.responseText);
	if (xhr.readyState == 4 && xhr.status == "200") {
        console.log("Request: token " + token );
        console.log("Response: "+ this.responseText);
	} else {
		lert("error"+this.responseText)
	}
}
xhr.send(null);

}




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

function acceptRequest(url,token,jsonBody){
    /**requestB.getId_requester(), 
				requestB.getSerial(), 
				DATE_FORMAT.parse(requestB.getDate()), 
				requestB.getNote(),
				RequestState.valueOf(requestB.getState()),
				RequestPriority.valueOf(requestB.getPriority())); */
    /*console.log(idRequester+serial+date+notes+state+priority)
    k=0;
    if(idRequester == ""){
         alert("id requester null"); 		
         k=1; 
    }
    if(serial == ""){
        alert("serial null"); 		
        k=1; 
    }
    if(state == ""){ 
        alert("state null");
        k=1; 
    }
    
    if(k==0){   //se ho superato i controlli eseguo la richiesta
        alert(idRequester + " - " + serial + " - " + date + " - " + notes + " - " + state + " - " + priority);*/
        /*var jsonBody 	 =  '{' + '"serial": "'     + serial 
                                + '", "date": "'    + date 
                                + '", "note": '     + notes 
                                + '","state": '     + state
                                + '"priority:"'     + priority+'}';*/
        /*
        alert("jsonBody: " + jsonBody);
        console.log("jsonBody: " + jsonBody);
        HTTPPost(url, token, jsonBody);
       }*/
       jsonBody = jsonBody.replace(/\\/g,"");
       console.log(url+token+jsonBody);
       alert("ciao");
       HTTPPost(url, token, jsonBody);
}




//=================BloodBag Methods: DELETE
function useBloodBag(url, token){
    alert(url);
HTTPDelete(url, token);

}





//=================BloodBag Methods: POST
function addBloodBag(url, token, group, donator, note){


alert("ciaoooooooooo");
 k=0;
 if(url == "") 		{ alert("url null"); 		k=1; }
 if(token == "") 	{ alert("token null"); 		k=1; }
 if(group == "") 	{ alert("group null"); 	        k=1; }
 if(donator == "") 	{ alert("donator null");  	k=1; }

 if(k==0){
  alert(url + " - " + token + " - " + group + " - " + donator + " - " + note );
  var jsonBody 	 = '{' + '"group": "' + group + '", "donator": "' + donator + '", "notes": "'+ note +'" }';
 
  alert("jsonBody: " + jsonBody);
  console.log("jsonBody: " + jsonBody);
  HTTPPost(url, token, jsonBody);
 }


}
//=================BloodBag Methods: POST
function addBloodBagCentral(url, token, serial){

    alert("ciaoooooooooo");
    k=0;
    if(url == "") 		{ alert("url null"); 		k=1; }
    if(token == "") 	{ alert("token null"); 		k=1; }
    if(serial == "") 	{ alert("group null"); 	        k=1; }


    if(k==0){
    alert(url + " - " + token + " - " + serial );
    var jsonBody 	 = '{' + '"serial": "' + serial +'" }';
    
    alert("jsonBody: " + jsonBody);
    console.log("jsonBody: " + jsonBody);
    HTTPPost(url, token, jsonBody);
    }
}


 //=================Request Methods: POST
 function addRequest(url, token, serial, priority, note){

     k=0;
     if(url == "") 		{ alert("url null"); 		k=1; }
     if(token == "") 	{ alert("token null"); 		k=1; }
     if(serial == "") 	{ alert("serial null");     k=1; }
     if(priority == "") { alert("priority null"); 	k=1; }
    
     if(k==0){

      var jsonBody 	 = '{' + '"serial": "' + serial +'", "note": "'+ note +'", "priority": "'+ priority +'" }';
     
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

        

/*
        function create() {
       
            event.preventDefault()
           
            let isNew = document.getElementById("createNuovoUser").checked;
            console.log(isNew)

         

            let url;
            let request = new XMLHttpRequest();   // new HttpRequest instance
            
            
            if (isNew){
                url = 'http://localhost:8093/rest/user/delete'
                request.open("POST", url, true);
                request.setRequestHeader("content-type", "application/json");

                request.onreadystatechange = function() {
                    if (this.readyState === 4) {
                        document.getElementById("createTextArea").value = username + "\n";
                        alert("User eliminato");
                    }
                };
 				let username = document.getElementById("username").value 
 				let role = document.getElementById("role").value
                request.send(JSON.stringify({"username": username}));
            }
            else{
                url = 'http://localhost:8093/rest/user/add'
                request.open("POST", url, true);
                request.setRequestHeader("content-type", "application/json");

                request.onreadystatechange = function() {
                    if (this.readyState === 4) {
                        document.getElementById("createTextArea").value = username + "\n" + role;
                        alert("User cereato");
		
                    }
                };
                
               let username = document.getElementById("username").value
               let role = document.getElementById("role").value
                request.send(JSON.stringify({"username": username, "role": role}));
        }
        }


	function add(){

	  event.preventDefault()  

        let url;
         url = 'http://localhost:8093/notes'
         let request = new XMLHttpRequest();   // new HttpRequest instance
              
              
           
          
                   let title = document.getElementById("ti").value
                  let content  = document.getElementById("co").value
             
 		request.open("POST", url, true);
                request.setRequestHeader("content-type", "application/json");
                 request.send(JSON.stringify({"title": title, "content": content}));

                request.onreadystatechange = function() {
                    if (this.readyState === 4) {
                        
                        alert("nota creata");
		
                    }
                };
                
        }

	
  function onCheckedNuovoUser(){
            let isNew = document.getElementById("createNuovoUser").checked;
            console.log(isNew)
	    

            
            let role = document.getElementById("role")
            if(isNew){
                role.disabled = true;
                role.value = ""
	
            }
            else
                role.disabled = false
		
        }
*/