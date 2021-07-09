
//=================BloodBag Methods: POST
function addBloodBag(url, token, group, donator, note){
  var jsonBody 	 = '{' + '"group": "' + group + '", "donator": "' + donator + '", "notes": "'+ note +'" }';
  console.log("jsonBody: " + jsonBody);

  var positiveMsg='sacca creata ed aggiunta in magazzino';
  HTTPPost(url, token, jsonBody, positiveMsg);
}

//=================BloodBag Methods: DELETE
function useBloodBag(url, token){
	var positiveMsg='sacca rimossa';
	HTTPDelete(url, token, positiveMsg);
 

}