//================= Methods: HTTP_POST
function HTTPPost(url, token, jsonBodyString, mex){
	event.preventDefault();
	
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
	       

            if(this.status === 500) { 
              let results = JSON.parse(this.responseText);
                alert("error"+this.responseText);
            }else if(this.status === 200){
                alert(mex);
            }
        }
    };
}


//================= Methods: HTTP_PUT
function HTTPPut(url, token, jsonBodyString, mex){
    let request = new XMLHttpRequest();   // new HttpRequest instance
	request.open("PUT", url, true);
	request.setRequestHeader('Authorization', 'Bearer ' + token);
	request.setRequestHeader("content-type", "application/json");
    
    request.onerror = function () {
        alert.log("** An error occurred during the transaction");
    };
	request.send(jsonBodyString);
	request.onreadystatechange = function() {
        if (this.readyState === 4  ) {
            console.log("Request: token " + token + " jsonBody: " + jsonBodyString);
	    console.log("Response: "+ this.responseText);
            if(this.status === 500) { 
              let results = JSON.parse(this.responseText);
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

// NON FUNZIONA: 403
function HTTPDelete(url, token, mex){
    let xhr = new XMLHttpRequest();
    xhr.open("DELETE", url, true);
    xhr.setRequestHeader('Authorization', 'Bearer ' + token);
    
    xhr.onload = function(){}; 
    
    
   
    xhr.send(null);
    alert(mex);
};


function HTTPPatch(url, token, jsonBodyString, mex){
    event.preventDefault();	
    let request = new XMLHttpRequest();   // new HttpRequest instance

    request.open("PATCH", url, true);
    request.setRequestHeader('Authorization', 'Bearer ' + token);
    request.setRequestHeader("content-type", "application/json");
    
    request.onerror = function () {
        alert.log("** An error occurred during the transaction");
    };
    request.send(jsonBodyString);
    request.onreadystatechange = function() {
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

  

//=================Method:POST
function login(){
	event.preventDefault();
	let url;
        

	url = 'http://localhost:8087/authenticate';
	let request = new XMLHttpRequest();   // new HttpRequest instance

	let username = document.getElementById("username").value;
	let password = document.getElementById("psw").value;

	request.open("POST", url, true);
	request.setRequestHeader("content-type", "application/json");
	request.send(JSON.stringify({"username": username, "password": password})); 
   
	request.onreadystatechange = function() {
    if (this.readyState === 4) {
     
	        


           if(this.status !== 200){ 
              
                alert("login fallito");
              
            }  
            else { 
                let results = JSON.parse(this.responseText);
                window.open("dashboard.php?token="+results.token);
            }
            
        }
       
        
            
    };
}