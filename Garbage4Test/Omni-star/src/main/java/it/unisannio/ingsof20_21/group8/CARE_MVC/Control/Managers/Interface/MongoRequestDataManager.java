package it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.Interface;

import java.sql.Date;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.InsertOneOptions;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Request.Request;

public class MongoRequestDataManager implements RequestDataManagerInterface {

    private static final String TAG_DB 					= "db_mongo_name";
    private static final String TAG_HOST 				= "db_mongo_host";
    private static final String TAG_PORT 				= "db_mongo_port";

//#######################################################
    private static final String COLLECTION_USER			= "users";
    
    private static final String ELEMENT_USERNAME 		= "username";
    private static final String ELEMENT_PASSWORD 		= "password";
    private static final String ELEMENT_ROLE 			= "role"; 
    
  //#######################################################
    private static final String COLLECTION_BAG			= "blood-bags";
    
    private static final String ELEMENT_GROUP 			= "BloodGroup";
    private static final String ELEMENT_SERIAL 			= "serial";
    private static final String ELEMENT_CREATIONDATE 	= "creationDate";
    private static final String ELEMENT_EXPIRATIONDATE 	= "expirationDate";
    private static final String ELEMENT_DONATORCF 		= "donatorCF";
    private static final String ELEMENT_NODE 			= "node";
    private static final String ELEMENT_BLOODBAGSTATE 	= "bloodBagState";
    private static final String ELEMENT_NOTE 			= "note";
  
  //#######################################################
    private static final String COLLECTION_REQUEST		= "request";
    
    private static final String ELEMENT_SERIALBAG 		= "serial_Bag";
    private static final String ELEMENT_REQUESTEDDATE 		= "requested_Date";
    private static final String ELEMENT_STATE			= "state"; 
    private static final String ELEMENT_USERREQUESTING	= "user-requesting";
    
  //#######################################################
    private static final String COLLECTION_NODE			= "node";
    
    private static final String ELEMENT_CODSTR 			= "cod_str";
    private static final String ELEMENT_NODENAME 		= "Node_Name";
    private static final String ELEMENT_WAREHOUSE		= "Warehouse"; 
  
  //#######################################################
    private static final String COLLECTION_LOCATION		= "location";
    
    private static final String ELEMENT_COUNTRY 		= "country";
    private static final String ELEMENT_REGION 			= "region";
    private static final String ELEMENT_PROVINCE		= "province"; 
    private static final String ELEMENT_CITY			= "city"; 
    private static final String ELEMENT_STREET			= "street"; 
    private static final String ELEMENT_STREEETNUMBER	= "streetNumber"; 
    private static final String ELEMENT_ZIPCODE			= "ZIPCodee"; 

    
    
	private String connectionStringURL = "mongodb+srv://ricciuto99:desk9123@cluster0.ksjti.mongodb.net/test";
    
	@Override
	public void addRequest(Request request) {
		
		//String name_db = request.getRequestedBloodBag().getNode().getWarehouse();
		MongoClientURI clientURI = new MongoClientURI(this.connectionStringURL /*+ name_db*/);
	    MongoClient mongoClient = new MongoClient(clientURI);
	    MongoDatabase mongoDatabase = mongoClient.getDatabase("CARE");
        
	    if(!this.exists(COLLECTION_REQUEST, mongoDatabase))
	    	 mongoDatabase.createCollection("request");
        
        MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION_REQUEST);
        
        //Verificare se vi è gia una richiesta su una data sacca
        Bson condition = new Document("$eq", request.getRequestedBloodBag().getSerial().toString());
        Bson filter = new Document("bloodbag", condition);
        if(collection.find().filter(filter).iterator().hasNext())
        	new Exception("esiste già");
        else {
        	Document requestD = Document.parse(request.toString());
        	collection.insertOne(requestD);
        }
        mongoClient.close();
	}
	
	private boolean exists(String nameDB, MongoDatabase mongoDatabase) {
		
		boolean exists = false;
        for (String name : mongoDatabase.listCollectionNames()) {
                    System.out.println(name);
                    if(name.equals("request")) exists=true;
        }
        
        return exists;
	}

	@Override
	public void setState(Request request) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<BloodBag> getBB(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

}
