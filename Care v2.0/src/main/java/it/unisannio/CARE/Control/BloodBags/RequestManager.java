package it.unisannio.CARE.Control.BloodBags;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.or;
import static com.mongodb.client.model.Filters.ne;

import it.unisannio.CARE.Model.BloodBag.Request;
import it.unisannio.CARE.Model.BloodBag.Request.RequestState;
import it.unisannio.CARE.Model.Exceptions.RequestCloneNotSupportedException;
import it.unisannio.CARE.Model.Exceptions.RequestNotFoundException;



/**
 * Questa classe implementa i metodi che eseguono i vari inserimenti e le varie query, per gestire le richieste inviate dai nodi locali.
 * @author Omni-star
 *
 */
public class RequestManager {
	
	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;
	private MongoCollection<Document> collection;
    
	
	
	/**
	 * 
	 * @param URI
	 * @param databaseName
	 * @param collectionName
	 */
	public RequestManager(String URI, String databaseName, String collectionName) {
		
		this.mongoClient = new MongoClient(new MongoClientURI(URI));
		this.mongoDatabase = mongoClient.getDatabase(databaseName);
		this.collection =mongoDatabase.getCollection(collectionName);
	}
	
	
	
	public void addRequest(Request request) {
		
	    Bson condition = and(
	    							eq("id_requester", request.getIdRequester()),
						    		eq("serial", request.getRequestedBagSerial()),
						    		ne("state", RequestState.accepted.toString())
						    	);
	    Bson condition2 = and(
					    			eq("serial", request.getRequestedBagSerial()),
					    			ne("state", RequestState.pending.toString())
					    		);
	    Bson filter = or(condition, condition2);
		
	    MongoCursor<Document> iterator = this.collection.find(filter).iterator();
        	
        if(!iterator.hasNext()) {

	        	Document requestD = Document.parse(request.toString());	
	    		this.collection.insertOne(requestD);
        }
        else {
        	
            throw new RequestCloneNotSupportedException("La richiesta che si vuole inserire è stata già accettata, rifiutata o inviata...");
        }   
			
	}

	
	
	public void acceptRequest(Request request) {
		
		request.setState(RequestState.accepted);

	    Bson filter = and(
							eq("id_requester", request.getIdRequester()),
					    		eq("serial", request.getRequestedBagSerial()),
					    		eq("state", RequestState.pending.toString())
    						);
	    
        Bson update = new Document("$set", new Document("state", RequestState.refused.toString()) );
        
        Document editedRequest = Document.parse(request.toString()); 
		

			if(collection.replaceOne(filter, editedRequest) != null) {
				
				filter = and(
						    		eq("serial", request.getRequestedBagSerial()),
						    		eq("state", RequestState.pending.toString())
							);
				
				while(collection.findOneAndUpdate(filter, update) != null);
			} 
			else {
				
				throw new RequestNotFoundException("Richiesta non trovata o inesistente...");
			}
 
        
	}

	
	
	public void refuseRequest(Request request) {
		
		request.setState(RequestState.refused);

	    Bson filter = and(
							eq("id_requester", request.getIdRequester()),
					    		eq("serial", request.getRequestedBagSerial()),
					    		eq("state", RequestState.pending.toString())
						);
        
        Document editedRequest = Document.parse(request.toString()); 
		
			if(collection.replaceOne(filter, editedRequest) == null) {
				
				throw new RequestNotFoundException("Richiesta non trovata o inesistente...");
			}

	}
	
	
	
	public void emptyTrash() {
		
	    Bson filter = and(
		    					eq("state", RequestState.refused.toString())
	    					);
        
        MongoCursor<Document> iterator= collection.find().filter(filter).iterator();
        
        MongoCollection<Document> bean = this.mongoDatabase.getCollection("refused-requests");
        while(iterator.hasNext()) {
        	
        	bean.insertOne(iterator.next());
        	collection.deleteOne(filter);
        }
        	
	}
	
	
	
	public List<Document> getRequestesByState(RequestState state) {
		
		List<Document> requestes = new ArrayList<>();
		
	    Bson filter = and(
					    		eq("state", state.toString())
						);
        
        MongoCursor<Document> iterator= collection.find().filter(filter).iterator();
        
        while(iterator.hasNext()) {
        	
        	requestes.add(iterator.next());
        }
		
		return requestes;
	}
	
	
	
	
	public void close() {
		
		mongoClient.close();
	}
	
	
	
	/*
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
	 */
}