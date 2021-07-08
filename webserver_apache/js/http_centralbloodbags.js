//=================BloodBag Methods: POST
function addBloodBagCentral(url, token, serial){
    var jsonBody 	 = '{' + '"serial": "' + serial +'" }'; 
    console.log("jsonBody: " + jsonBody);
    var positiveMsg='sacca aggiunta in magazzino';
    HTTPPost(url, token, jsonBody, positiveMsg);
}