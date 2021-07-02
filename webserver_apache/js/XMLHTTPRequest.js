function hello(){
 alert("ciao");
}

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