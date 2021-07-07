package it.unisannio.CARE.controll.bloodBag;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.ne;
import static com.mongodb.client.model.Filters.or;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;

import it.unisannio.CARE.model.bloodBag.BloodBag;
import it.unisannio.CARE.model.bloodBag.BloodBagState;
import it.unisannio.CARE.model.bloodBag.Request;
import it.unisannio.CARE.model.bloodBag.RequestState;
import it.unisannio.CARE.model.exceptions.BloodBagNotFoundException;
import it.unisannio.CARE.model.exceptions.RequestCloneNotSupportedException;
import it.unisannio.CARE.model.util.Constants;
import it.unisannio.CARE.model.util.XMLHelper;



public class BloodBagManager {

	
	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;
	private MongoCollection<Document> collection;
    
	/**
     **************************************************************************
     * Metodo costruttore per la classe BloodManager
     * @param URI Il link del Database
     * @param databaseName Nome del Database
     * @param collectionName
     **************************************************************************
     */
	
	public BloodBagManager(String URI, String databaseName, String collectionName) {
		
		this.mongoClient = new MongoClient(new MongoClientURI(URI));
		this.mongoDatabase = mongoClient.getDatabase(databaseName);
		this.collection = mongoDatabase.getCollection(collectionName);
	}
	
	
	
	public BloodBagManager() {
		
		Properties properties = XMLHelper.getProps(Constants.MONGODB_CREDENTIALS);
    	
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        String db_host = properties.getProperty("db_host");
        String databaseName = properties.getProperty("db_name");
        String collectionName = properties.getProperty("bloodbags");
		this.mongoClient = new MongoClient(new MongoClientURI("mongodb+srv://"+username+":"+password+"@cluster0"+db_host));
		this.mongoDatabase = mongoClient.getDatabase(databaseName);
		this.collection = mongoDatabase.getCollection(collectionName);
	}
	
	/**
     **************************************************************************
     * Metodo Per aggiungere una sacca di sangue 
     * @param bloodBag  Oggetto per le informazioni sulla sacca di sangue
     **************************************************************************
     */
	
	public void addBloodBag(BloodBag bloodBag) {
    	Document bloodBagD = Document.parse(bloodBag.toString());	
		this.collection.insertOne(bloodBagD);	
	}
	
	/**
     **************************************************************************
     * Metodo Per eliminare una sacca di sangue 
     * @param bloodBag  Oggetto per le informazioni sulla sacca di sangue
     **************************************************************************
     */
	
	public void deleteBloodBag(BloodBag bloodBag) {
    	Document bloodBagD = Document.parse(bloodBag.toString());	
		this.collection.deleteOne(bloodBagD);
	}
	
	/**
     **************************************************************************
     * Metodo Per importare una sacca di sangue 
     * @param serial Il seriale della sacca di sangue da importare
     * @throws BloodBagNotFoundException
     * @return bloodBagD
     **************************************************************************
     */
	
	public Document importBloodBag(String serial) {
		Bson filter = and(
						eq("serial", serial),
						eq("state", BloodBagState.Transfered.toString())
						);
		Bson update = Updates.set("state",BloodBagState.Arrived.toString() );
		
		Document bloodBagD = this.collection.findOneAndUpdate(filter, update);
		
		if(bloodBagD != null)
			return bloodBagD;
		throw new BloodBagNotFoundException("Sacca non trovata.");
	}
	
	/**
     **************************************************************************
     * Metodo Per ottenere la sacca di sangue
     * @param serial Il seriale della sacca di sangue da leggere
     * @throws BloodBagNotFoundException
     * @return bloodBagD
     **************************************************************************
     */
	
	public Document getBloodBag(String serial) {
		
		Bson filter = and(
						eq("serial", serial),
						eq("state", BloodBagState.Transfered.toString())
						);
		
		Document bloodBagD = this.collection.find(filter).first();
		
		if(bloodBagD != null)
			return bloodBagD;
		throw new BloodBagNotFoundException("Sacca non trovata.");
	}
	
	/**
     **************************************************************************
     * Metodo Per ottenere una lista di sacche di sangue
     * @return bloodBags
     **************************************************************************
     */
	
	public List<Document> getBloodBags() {
		
		List<Document> bloodBags = new ArrayList<>();
		
		Bson filter = eq("state", BloodBagState.Available.toString());
        
        MongoCursor<Document> iterator= collection.find(filter).iterator();
        
        while(iterator.hasNext()) {
        	
        	bloodBags.add(iterator.next());
        }
		
		return bloodBags;
	}
	
	/**
     **************************************************************************
     * Metodo ?
     * @param serial_r Il seriale della sacca di sangue da ?
     * @return iterator.hasNext()
     **************************************************************************
     */
	
	public boolean BloodBagRequestable(String serial_r) {
		
       Bson filter = and(
    		   
			       		eq("serial", serial_r),
						eq("state", BloodBagState.Available.toString())
					);
	
	  MongoCursor<Document> iterator = this.collection.find(filter).iterator();

	  return iterator.hasNext();
	  
    }
	
	/**
     **************************************************************************
     * Metodo Filtrare se la sacca esiste 
     * @param serial_r Il seriale della sacca di sangue da ricercare
     * @throws BloodBagNotFoundException
     **************************************************************************
     */
	
	public void BloodBagMarkNotAvailable(String serial_r) {
		
		Bson filter=and(
						eq("serial", serial_r),
						eq("state", BloodBagState.Available.toString())
						);
		Bson update=Updates.set("state",BloodBagState.Transfered.toString() );
		if(this.collection.updateOne(filter, update) == null)
			throw new BloodBagNotFoundException("La sacca su cui si vuole operare non è esistente.");
	}
	
	/**
     **************************************************************************
     * Metodo per la chiusura del client di mongoDB
     **************************************************************************
     */
	
	public void close() {
		
		this.mongoClient.close();
	}

}
