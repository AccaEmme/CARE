function hello(){
 alert("ciao");
}

//=================Method:GET
function login(){
	event.preventDefault();
	let url;
        //var ok=0;

	url = 'http://localhost:8087/authenticate';
	let request = new XMLHttpRequest();   // new HttpRequest instance

	let username = document.getElementById("username").value;
	let password = document.getElementById("psw").value;

	request.open("POST", url, true);
	request.setRequestHeader("content-type", "application/json");
	request.send(JSON.stringify({"username": username, "password": password}));
	request.onreadystatechange = function() {
	 if (this.readyState === 4 &&  this.status === 200 ) {
	  //ok=1;
	  console.log("token:"+ this.responseText);
	  let results = JSON.parse(this.responseText);
  	  alert(" provola " + results.token ); 
	  window.open("dashboard.php?token="+results.token);
         }
       };
       //if(ok==0) alert("===Login failed==="+ok);
}

//=================Users Methods: POST
function HTTPPost(url, token, jsonBodyString){
	event.preventDefault();
	let request = new XMLHttpRequest();   // new HttpRequest instance

	request.open("POST", url, true);
	//request.setRequestHeader('Authorization', 'Bearer ' + token);
	request.setRequestHeader("content-type", "application/json");
	request.send(JSON.stringify(jsonBodyString));
	request.onreadystatechange = function() {
	 if (this.readyState === 4 &&  this.status === 200 ) {
	  console.log("Request: token " + token + " jsonBody: " + jsonBodyString);
	  console.log("Response: "+ this.responseText);
	  let results = JSON.parse(this.responseText);
  	  //alert(" Result: " + results ); 
	  //window.open("dashboard.php?token="+results.token);
         }
       };
}


//=================Users Methods: POST

function addUser(url, token, username, password, email, userRole, loginAttempts, activeUser){
//var url='http://localhost:8087/authenticate';
/*
http://localhost:8087/register
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
 if(email == "") 	{ alert("email null");  	k=1; }
 if(userRole == "") 	{ alert("userRole null");  	k=1; }

 if(k==0){
  alert(url + " - " + token + " - " + username + " - " + password + " - " + email + " - " + userRole);
  var jsonBody = '{' + '"username": "' + username + '", "password": "' + password + '", "email": "' + email + '", "userRole": "' + userRole + '", "loginAttempts": ' + loginAttempts + ', "activeUser": ' + activeUser + '}';
  alert("jsonBody: " + jsonBody);
  console.log("jsonBody: " + jsonBody);
  HTTPPost(url, token, jsonBody);
 }


}
   
function deleteUser(token, username){

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