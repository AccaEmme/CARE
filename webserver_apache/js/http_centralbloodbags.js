//=================BloodBag Methods: POST
function addBloodBagCentral(url, token, serial){
    var jsonBody 	 = '{' + '"serial": "' + serial +'" }'; 
    console.log("jsonBody: " + jsonBody);
    var positiveMsg='sacca aggiunta in magazzino';
    HTTPPost(url, token, jsonBody, positiveMsg);
}








//=================BloodBag Methods: POST
function transferBloodBagC(url, token, serial){
    var jsonBody 	 = '{' + '"serial": "' + serial +'" }'; 
    console.log("jsonBody: " + jsonBody);
    var positiveMsg='informazioni sacca spedite';
    HTTPPost(url, token, jsonBody, positiveMsg);
}


//=================BloodBag Methods: POST
function confirmBloodBagCentral(url, token, serial){
    var jsonBody 	 = '{' + '"serial": "' + serial +'" }'; 
    console.log("jsonBody: " + jsonBody);
    var positiveMsg='sacca resa disponibile';
    HTTPPost(url, token, jsonBody, positiveMsg);
}