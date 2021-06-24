package it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.Interface;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Exceptions.RequestCloneNotSupportedException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Exceptions.RequestNotFoundException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Request.Request;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Request.RequestState;

public class MongoRequestDataManager implements RequestDataManagerInterface {

 
    
	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;
	private MongoCollection<Document> collection;
    
	public MongoRequestDataManager(String URI, String mongoDatabaseName) {
		
		this.mongoClient = new MongoClient(new MongoClientURI(URI));
		this.mongoDatabase = mongoClient.getDatabase(mongoDatabaseName);
		collection = getCollection();
	}
	
	
	/* un nodo periferico richiede una sacca aggiungendo una richiesta nella
	 * collezione  RICHIESTE che si trova nel DataBase Mongo Cloud d
	 */
	
	@Override
	public void addRequest(Request request) {
		
        Bson condition = new Document("$eq", request.getRequestedBloodBag().getSerial().toString());
        Bson filter = new Document("bloodbag", condition);
        
        try {
	        if(collection.find().filter(filter).iterator().hasNext())
	        	
	        	throw new RequestCloneNotSupportedException("Richiesta già esistente");
	        else {
	        	
	        	Document requestD = Document.parse(request.toString());
	        	collection.insertOne(requestD);
	        }
        }catch(RequestCloneNotSupportedException e) {
        	
        	System.err.println(e.getMessage());
        	System.err.println("La richiesta "+ request +" non può essere effettuata su una stessa sacca...");
        	e.printStackTrace();
        }
	}

	/*il magazziniere del nodopossedente la scca può accettare o meno le richieste
	 * verso il suo nodo 
	 */

	
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
	//un nodo pò rifiutare le richieste verso il suo nodo
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
	
	// metodi per visulizzare lo stato delle richieste fatte dal suo nodo
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
	// metodi per visulizzare lo stato delle richieste fatte dal suo nodo
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
	// metodi per visulizzare lo stato delle richieste fatte dal suo nodo
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
	}
}
