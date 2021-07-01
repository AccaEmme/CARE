package it.unisannio.CARE.Control.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.or;
import static com.mongodb.client.model.Filters.ne;

import it.unisannio.CARE.model.bloodBag.Request;
import it.unisannio.CARE.model.bloodBag.RequestPriority;
import it.unisannio.CARE.model.bloodBag.RequestState;
import it.unisannio.CARE.model.exceptions.RequestCloneNotSupportedException;
import it.unisannio.CARE.model.exceptions.RequestNotFoundException;
import it.unisannio.CARE.model.util.Constants;
import it.unisannio.CARE.model.util.XMLHelper;


/**Questa classe implementa i metodi che eseguono i vari inserimenti e le varie query, per gestire le richieste inviate dai nodi locali.
 */
public class RequestManager {
	
	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;
	private MongoCollection<Document> collection;
    
	
	
	/**
     **************************************************************************
     * Metodo costruttore per creare il Request Manager 
     * @param URI Uniform Resource Identifie
     * @param databaseName  Deve essere fornito il nome del database
     * @param collectionName 
     **************************************************************************
     */
	public RequestManager(String URI, String databaseName, String collectionName) {
		
		this.mongoClient = new MongoClient(new MongoClientURI(URI));
		this.mongoDatabase = mongoClient.getDatabase(databaseName);
		this.collection = mongoDatabase.getCollection(collectionName);
	}
	
	
	
	public RequestManager() {
		
		Properties properties = XMLHelper.getProps(Constants.MONGODB_CREDENTIALS);
    	
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        String db_host = properties.getProperty("db_host");
        String databaseName = properties.getProperty("db_name");
        String collectionName = properties.getProperty("requests");
        
		this.mongoClient = new MongoClient(new MongoClientURI("mongodb+srv://"+username+":"+password+"@cluster0"+db_host));
		this.mongoDatabase = mongoClient.getDatabase(databaseName);
		this.collection = mongoDatabase.getCollection(collectionName);
	}

	
	
	
	/**
     **************************************************************************
     * Metodo per aggiungere una richiesta 
     * @param request  Oggetto Richista per le informazioni sulla richiesta
     * @throws RequestCloneNotSupportedException
     **************************************************************************
     */
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

	
	
	/**
     **************************************************************************
     * Metodo per accettare le richieste inoltrate
     * @param request  Oggetto Richista per le informazioni sulla richiesta
     * @throws RequestCloneNotSupportedException
     **************************************************************************
     */
	public void acceptRequest(Request request) {
		
		request.setState(RequestState.accepted);

	    Bson filter = and(
							eq("id_requester", request.getIdRequester()),
				    		eq("serial", request.getRequestedBagSerial()),
				    		eq("state", RequestState.pending.toString())
						);
        
        Document editedRequest = Document.parse(request.toString()); 
		

		if(collection.replaceOne(filter, editedRequest) != null) {
			
			filter = and(
				    		eq("serial", request.getRequestedBagSerial()),
				    		eq("state", RequestState.pending.toString())
						);
	        Bson update = new Document("$set", new Document("state", RequestState.refused.toString()) );
			
			while(collection.findOneAndUpdate(filter, update) != null);
		} 
		else {
			
			throw new RequestNotFoundException("Richiesta non trovata o inesistente...");
		}
 
        
	}

	
	/**
     **************************************************************************
     * Metodo per declinare una richiesta
     * @param request  Oggetto Richista per le informazioni sulla richiesta
     * @throws RequestCloneNotSupportedException
     **************************************************************************
     */
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
	
	
	/**
     **************************************************************************
     * 
     **************************************************************************
     */
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
	
	
	/**
     **************************************************************************
     * Metodo per ottenere la richieste dallo stato
     * @param state  Oggetto che contiene le informazioni della richiesta di stato
     **************************************************************************
     */
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
	
	
	
	/**
     **************************************************************************
     * Metodo per ottenere le richieste dalla priorità
     * @param state  Oggetto che contiene le informazioni della priorità della richiesta
     **************************************************************************
     */
	public List<Document> getRequestesByPriority(RequestPriority priority) {
		
		List<Document> requestes = new ArrayList<>();
		
	    Bson filter = and(
					    		eq("priority", priority.toString())
						);
        
        MongoCursor<Document> iterator= collection.find().filter(filter).iterator();
        
        while(iterator.hasNext()) {
        	
        	requestes.add(iterator.next());
        }
		
		return requestes;
	}
	
	
	
	/**
     **************************************************************************
     * Metodo per la chiusura del client di mongoDB
     **************************************************************************
     */
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