package it.unisannio.CARE.Control.Classes;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import it.unisannio.CARE.Exceptions.RequestCloneNotSupportedException;
import it.unisannio.CARE.Exceptions.RequestNotFoundException;
import it.unisannio.CARE.Model.Classes.Request;
import it.unisannio.CARE.Model.Util.RequestState;

public class RequestManager {
/*
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
*/
    
	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;
	private MongoCollection  collection;
    
	public RequestManager(String URI, String mongoDatabaseName,String collectionName) {
		
		this.mongoClient = new MongoClient(new MongoClientURI(URI));
		this.mongoDatabase = mongoClient.getDatabase(mongoDatabaseName);
		this.collection =mongoDatabase.getCollection(collectionName);;
	}
	
	public void addRequest(Request request) {
	        	Document requestD = Document.parse(request.toString());
	        	this.collection.insertOne(requestD);
	        	this.mongoClient.close();
	}
/*
	@Override
	public void acceptRequest(Request request) {
		
		request.setRequestState(RequestState.accepted);

	    Bson condition = new Document("$eq", request.getRequestedBloodBag().getSerial().toString());
	    Bson condition2 = new Document("$eq", RequestState.pending.toString());
        Bson filter = new Document("bloodbag", condition).append("state", condition2);
        
        Document editedRequest = Document.parse(request.toString()); 
		
        try {
			if(collection.replaceOne(filter, editedRequest).getMatchedCount()==0) {
				
				throw new RequestNotFoundException("Richiesta non trovata o inesistente...");
			} 
			else {
				
				System.out.println("Richiesta aggiornata.");
			}
        }catch(RequestNotFoundException e) {
        	
        	System.err.println(e.getMessage());
        	System.err.println("La richiesta "+ request +" può essere già stata elaborata...");
        	e.printStackTrace();
        	
        }
        
	}
	
	@Override
	public void refuseRequest(Request request) {
		
		request.setRequestState(RequestState.refused);

	    Bson condition = new Document("$eq", request.getRequestedBloodBag().getSerial().toString());
        Bson condition2 = new Document("$eq", RequestState.pending.toString());
        Bson filter = new Document("bloodbag", condition).append("state", condition2);
        
        Document editedRequest = Document.parse(request.toString()); 
		
        try {
			if(collection.replaceOne(filter, editedRequest).getMatchedCount()==0) {
				
				throw new RequestNotFoundException("Richiesta non trovata o inesistente...");
			} 
			else {
				
				System.out.println("Richiesta aggiornata.");
			}
        }catch(RequestNotFoundException e) {
        	
        	System.err.println(e.getMessage());
        	System.err.println("La richiesta "+ request +" può essere già stata elaborata...");
        	e.printStackTrace();
        	
        }
	}
	
	@Override
	public List<Document> getRequestesInPendling() {
		
		List<Document> requestes = new ArrayList<>();
		
        Bson condition = new Document("$eq", RequestState.pending.toString());
        Bson filter = new Document("state", condition);
        
        MongoCursor<Document> iterator= collection.find().filter(filter).iterator();
        
        while(iterator.hasNext()) {
        	
        	requestes.add(iterator.next());
        }
		
		return requestes;
	}

	@Override
	public List<Document> getRequestesAccepted() {
		
		List<Document> requestes = new ArrayList<>();

        Bson condition = new Document("$eq", RequestState.accepted.toString());
        Bson filter = new Document("state", condition);
        
        MongoCursor<Document> iterator= collection.find().filter(filter).iterator();
        
        while(iterator.hasNext()) {
        	
        	requestes.add(iterator.next());
        }
		
		return requestes;
	}

	@Override
	public List<Document> getRequestesRefused() {
		
		List<Document> requestes = new ArrayList<>();
		
        Bson condition = new Document("$eq", RequestState.refused.toString());
        Bson filter = new Document("state", condition);
        
        MongoCursor<Document> iterator= collection.find().filter(filter).iterator();
        
        while(iterator.hasNext()) {
        	
        	requestes.add(iterator.next());
        }
		
		return requestes;
	}

	private MongoCollection<Document> getCollection() {
		
		boolean exists = false;
        for (String name : mongoDatabase.listCollectionNames()) {
        	
                    if(name.equals("requestes")) exists=true;
        }
        
        if(exists)
        	return mongoDatabase.getCollection("requestes");
        mongoDatabase.createCollection("requestes");
        return mongoDatabase.getCollection("requestes");
	}*/
}