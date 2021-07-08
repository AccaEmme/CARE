 //=================Request Methods: POST
 function addRequest(url, token, serial, priority, note){

     k=0;
     if(url == "") 		{ alert("url null"); 		k=1; }
     if(token == "") 	{ alert("token null"); 		k=1; }
     if(serial == "") 	{ alert("serial null");     k=1; }
     if(priority == "") { alert("priority null"); 	k=1; }
    
     if(k==0){

        var jsonBody 	 = '{' + '"serial": "' + serial +'", "note": "'+ note +'", "priority": "'+ priority +'" }';
        
        console.log("jsonBody: " + jsonBody);
        HTTPPost(url, token, jsonBody, 'Richiesta aggiunta con successo...');
     }

}

function deleteRequest(url, token, serial){

    k=0;
    if(url == "") 		{ alert("url null"); 		k=1; }
    if(token == "") 	{ alert("token null"); 		k=1; }
    if(serial == "") 	{ alert("serial null");     k=1; }
   
    if(k==0){

        var jsonBody 	 = '{' + '"serial": "' + serial +'" }';
        
        console.log("jsonBody: " + jsonBody);
        HTTPPost(url, token, jsonBody, 'Richiesta annullata con successo...');
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