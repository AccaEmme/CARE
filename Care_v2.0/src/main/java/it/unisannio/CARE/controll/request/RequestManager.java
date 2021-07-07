package it.unisannio.CARE.controll.request;

import java.util.ArrayList;
import java.util.Iterator;
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

/*
* This class implements the methods that perform the various insertions and the various queries, to manage the requests sent by the local nodes.
*/
public class RequestManager {
	
	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;
	private MongoCollection<Document> collection;
    
	
	
	/**
     **************************************************************************
      * Constructor method to create the Request Manager
      * @param URI Uniform Resource Identifie
      * @param databaseName The database name must be provided
      * @param collectionName
     **************************************************************************
     */
	public RequestManager(String URI, String databaseName, String collectionName) {
		
		this.mongoClient = new MongoClient(new MongoClientURI(URI));
		this.mongoDatabase = mongoClient.getDatabase(databaseName);
		this.collection = mongoDatabase.getCollection(collectionName);
	}
	
	
	/**
     **************************************************************************
      * Constructor method to create the Request Manager
     **************************************************************************
     */
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
      * Method To add a request
      * @param request Requestor object for request information
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
		
	    MongoCursor<Document> iterator = this.collection.find().filter(filter).iterator();
        	
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
      * Method To delete the submitted requests
      * @param request Request object for information about the request
      * @throws RequestNotFoundException
     **************************************************************************
     */	
	public void deleteRequest(Request request) {
		
	    Bson filter = and(
							eq("id_requester", request.getIdRequester()),
				    		eq("serial", request.getRequestedBagSerial()),
				    		eq("state", RequestState.pending.toString())
						);

		if(collection.findOneAndDelete(filter) == null)
			throw new RequestNotFoundException("Richiesta non trovata o stato della richiesta diversa da \"pending\"...");
 
        
	}

	
	
	/**
     **************************************************************************
      * Method To accept submitted requests
      * @param request Request object for information about the request
      * @exception RequestCloneNotSupportedException
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
		
		if(collection.replaceOne(filter, editedRequest).getMatchedCount() != 0) {
			
			filter = and(
							ne("id_requester", request.getIdRequester()),
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
      * Method To close the submitted requests
      * @param request Request object for information about the request
      * @exception RequestCloneNotSupportedException
     **************************************************************************
     */
	public void closeRequest(String serial) {
        
		Properties prop = XMLHelper.getProps(Constants.NODE_PROPERTIES);
		String id_requester = prop.getProperty("province") + prop.getProperty("structureCode");

	    Bson filter = and(
	    		eq("id_requester", id_requester),
	    		eq("serial", serial),
	    		eq("state", RequestState.accepted.toString())
	    		);
		
        Bson update = new Document("$set", new Document("state", RequestState.closed.toString()) );
        
        if(collection.findOneAndUpdate(filter, update) == null)
        	throw new RequestNotFoundException("Richiesta non trovata o inesistente...");
	}
	
	/**
     **************************************************************************
      * Method To decline a request
      * @param request Request object for information about the request
      * @exception RequestCloneNotSupportedException
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
		
			if(collection.replaceOne(filter, editedRequest).getMatchedCount() != 0) {
				
				throw new RequestNotFoundException("Richiesta non trovata o inesistente...");
			}

	}
	
	
	/**
     **************************************************************************
     * 
     **************************************************************************
     */
	public void emptyTrash() {
		
	    Bson filter = eq("state", RequestState.refused.toString());
        
        MongoCursor<Document> iterator= collection.find().filter(filter).iterator();
        
        MongoCollection<Document> bean = this.mongoDatabase.getCollection("refused-requests");
        while(iterator.hasNext()) {
        	
        	bean.insertOne(iterator.next());
        	collection.deleteOne(filter);
        }
        	
	}
	
	
	
	/**
     **************************************************************************
      * Method To get requests from the state
      * @param state Object that contains the information of the status request
      * @return requestes
     **************************************************************************
     */
	public List<Document> getRequestsByState(RequestState state) {
		
		List<Document> requestes = new ArrayList<>();
		
	    Bson filter = eq("state", state.toString());
        
        MongoCursor<Document> iterator= collection.find().filter(filter).iterator();
        
        while(iterator.hasNext()) {
        	
        	requestes.add(iterator.next());
        }
		
		return requestes;
	}
	
	
	
	/**
     **************************************************************************
      * Method To get requests from priority
      * @param priority Object that contains the request priority information
      * @return requestes
     **************************************************************************
     */
	public List<Document> getRequestsByPriority(RequestPriority priority) {
		
		
		List<Document> requestes = new ArrayList<>();
		
		Bson filter = eq("priority", priority.toString());
		        
	    MongoCursor<Document> iterator= collection.find().filter(filter).iterator();
    
	    while(iterator.hasNext()) {
	    	
	    	requestes.add(iterator.next());
    	}
			
	    return requestes;
	}
	
	/**
     **************************************************************************
     * Method To read the many requests 
     * @return requests
     **************************************************************************
     */
	
	public List<Document> getAllRequests() {
        
		List<Document> requestes = new ArrayList<>();
		
        MongoCursor<Document> iterator= collection.find().iterator();
         
	    while(iterator.hasNext()) {
	    	
	    	requestes.add(iterator.next());
    	}
			
	    return requestes;
	}
	
	/**
     **************************************************************************
     * Method To read our requests 
     * @return requests
     **************************************************************************
     */
	
	public List<Document> getOurRequests() {
        
		Properties prop = XMLHelper.getProps(Constants.NODE_PROPERTIES);
		String id_requester = prop.getProperty("province") + prop.getProperty("structureCode");
		
		List<Document> requestes = new ArrayList<>();
		
	    Bson filter = eq("id_requester", id_requester);
		
        MongoCursor<Document> iterator= collection.find().filter(filter).iterator();
		
	    while(iterator.hasNext()) {
	    	
	    	requestes.add(iterator.next());
    	}
			
	    return requestes;
	}
	
	
	/**
     **************************************************************************
     * Method To read other requests 
	 * @return requests
     **************************************************************************
     */
	
	public List<Document> getOtherRequests() {
        
		Properties prop = XMLHelper.getProps(Constants.NODE_PROPERTIES);
		String id_requester = prop.getProperty("province") + prop.getProperty("structureCode");
		
		List<Document> requestes = new ArrayList<>();
		
	    Bson filter = ne("id_requester", id_requester);
		
        MongoCursor<Document> iterator= collection.find().filter(filter).iterator();
		
	    while(iterator.hasNext()) {
	    	
	    	requestes.add(iterator.next());
    	}
			
	    return requestes;
	}
	
	
	/**
     **************************************************************************
     * Method for closing the mongoDB client
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