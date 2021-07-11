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
import it.unisannio.CARE.model.bloodBag.BloodGroup;
import it.unisannio.CARE.model.bloodBag.Request;
import it.unisannio.CARE.model.bloodBag.RequestState;
import it.unisannio.CARE.model.exceptions.BloodBagNotFoundException;
import it.unisannio.CARE.model.exceptions.RequestCloneNotSupportedException;
import it.unisannio.CARE.model.util.Constants;
import it.unisannio.CARE.model.util.XMLHelper;

/**
 * Class that contains all methods for handling blood bags
 */


public class BloodBagManager {

	
	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;
	private MongoCollection<Document> collection;
    
	/**
     **************************************************************************
      * Constructor method for the BloodManager class
      * @param URI The Database link
      * @param databaseName Name of the Database
      * @param collectionName Collection of names
     **************************************************************************
     */
	
	public BloodBagManager(String URI, String databaseName, String collectionName) {
		
		this.mongoClient = new MongoClient(new MongoClientURI(URI));
		this.mongoDatabase = mongoClient.getDatabase(databaseName);
		this.collection = mongoDatabase.getCollection(collectionName);
	}
	
	
	/**
     **************************************************************************
     * Constructor method for the BloodManager class
     **************************************************************************
     */
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
     * Method To add a blood bag
     * @param bloodBag Object for blood bag information
     **************************************************************************
     */
	
	public void addBloodBag(BloodBag bloodBag) {
    	Document bloodBagD = Document.parse(bloodBag.toString());	
		this.collection.insertOne(bloodBagD);	
	}
	
	/**
     **************************************************************************
     * Method To eliminate a blood bag
     * @param bloodBag Object for blood bag information
     **************************************************************************
     */
	
	public void deleteBloodBag(BloodBag bloodBag) {
    	Document bloodBagD = Document.parse(bloodBag.toString());	
		this.collection.deleteOne(bloodBagD);
	}
	
	/**
     **************************************************************************
      * Method To import a blood bag
      * @param serial The serial of the blood bag to import
      * @throws BloodBagNotFoundException The bag was not found
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
      * Method To get the blood bag
      * @param serial The serial of the blood bag to read
      * @throws BloodBagNotFoundException The bag was not found
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
     * Method To get a list of blood bags
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
     * Method To get a list of blood bags of a blood type
     * @return bloodBags
     **************************************************************************
     */
	
	public List<Document> getBloodBagsByGroup(BloodGroup group) {
		
		List<Document> bloodBags = new ArrayList<>();
		
		Bson filter = and(
						eq("state", BloodBagState.Available.toString()),
						eq("group", group.toString())
						);
        
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
      * Method Filter if the bag exists
      * @param serial_r The serial of the blood bag to search for
      * @throws BloodBagNotFoundException The bag was not found
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
	
	
	public void confirm(String serial_r) {
		Bson filter=and(
				eq("serial", serial_r),
				eq("state", BloodBagState.receiving.toString())
				);
Bson update=Updates.set("state",BloodBagState.Available.toString() );
     if(this.collection.updateOne(filter, update).getModifiedCount()== 0)
	throw new BloodBagNotFoundException("La sacca su cui si vuole operare non è esistente.");
	}
	
	
	
	/**
     **************************************************************************
     * Method for closing the mongoDB client
     **************************************************************************
     */
	
	public void close() {
		
		this.mongoClient.close();
	}

}
