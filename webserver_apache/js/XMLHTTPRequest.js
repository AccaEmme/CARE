//================= Methods: HTTP_POST
function HTTPPost(url, token, jsonBodyString,mex){
	event.preventDefault();
	alert("ciao");
    let request = new XMLHttpRequest();   // new HttpRequest instance

	request.open("POST", url, true);
	request.setRequestHeader('Authorization', 'Bearer ' + token);
	request.setRequestHeader("content-type", "application/json");
    
    request.onerror = function () {
        alert.log("** An error occurred during the transaction");
    };
//	request.send(JSON.stringify(jsonBodyString));
	request.send(jsonBodyString);
	request.onreadystatechange = function() {
	    
    /*
    request.onreadystatechange = function() {
        if (this.readyState === 4  ) {
            console.log("Request: token " + token + " jsonBody: " + jsonBodyString);
            console.log("Response: "+ this.responseText);
            let results = JSON.parse(this.responseText);
        }
        if(this.status == 500) {
            alert("error"+this.responseText)
        }else{
            alert(mex);
        }
    };
    */

        if (this.readyState === 4  ) {
            console.log("Request: token " + token + " jsonBody: " + jsonBodyString);
	        console.log("Response: "+ this.responseText);
	        let results = JSON.parse(this.responseText);

            if(this.status === 500) {
                alert("error"+this.responseText);
            }else if(this.status === 200){
                alert(mex);
            }
        }
    };
}
//================= Methods: HTTP_DELETE
/*
    event.preventDefault();
	let request2 = new XMLHttpRequest();   // new HttpRequest instance
  
	request2.open("DELETE", url, true);
	request2.setRequestHeader('Authorization', 'Bearer ' + token);
    alert("SECONDO");
	request2.onload = function() {
        alert("TERZO");
	 if (this.readyState === 4 ) {
	  console.log("Request: token " + token );
	  console.log("Response: "+ this.responseText);
	  let results = JSON.parse(this.responseText);
      if(this.status === 500) {
        alert("error"+this.responseText);
    }else if(this.status === 200){
        alert(mex);
    }
}
       };
    }*/

function HTTPDelete(url, token, mex){
    let xhr = new XMLHttpRequest();
    xhr.open("DELETE", url, true);
    xhr.setRequestHeader('Authorization', 'Bearer ' + token);

    xhr.onload = function(){}; 
     
    alert(mex);
    xhr.send(null);
};




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

