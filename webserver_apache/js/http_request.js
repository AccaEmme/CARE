//=================Request Methods: POST
function addRequest(token, serial, priority, note) {

    k = 0;
    if (token == "") {
        alert("token null");
        k = 1;
    }
    if (serial == "") {
        alert("serial null");
        k = 1;
    }
    if (priority == "") {
        alert("priority null");
        k = 1;
    }

    if (k == 0) {

        var jsonBody = '{' + '"serial": "' + serial + '", "note": "' + note + '", "priority": "' + priority + '" }';

        console.log("jsonBody: " + jsonBody);
        HTTPPost('http://localhost:8087/request/add', token, jsonBody, 'Richiesta aggiunta con successo...');
    }

}

function deleteRequest(token, serial) {

    k = 0;
    if (token == "") {
        alert("token null");
        k = 1;
    }
    if (serial == "") {
        alert("serial null");
        k = 1;
    }

    if (k == 0) {

        var jsonBody = '{' + '"serial": "' + serial + '" }';

        console.log("jsonBody: " + jsonBody);
        HTTPPost('http://localhost:8087/request/delete', token, jsonBody, 'Richiesta annullata con successo...');
    }

}

function acceptRequest(url,token,id_requester,serial){
   
    var jsonBody 	 = '{' + '"id_requester": "' + id_requester + '", "serial": "'+ serial +'" }';
       
    console.log("jsonBody:" + jsonBody);
    HTTPPost(url,token,jsonBody, 'Richiesta accetata con successo...');
}



function refuseRequest(url,token,id_requester,serial){
   
    var jsonBody 	 = '{' + '"id_requester": "' + id_requester + '", "serial": "'+ serial +'" }';
       
    console.log("jsonBody:" + jsonBody);
    HTTPPost(url,token,jsonBody, 'Richiesta rifiutata con successo...');
}