package it.unisannio.CARE.controll.bloodBag;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.ne;
import static com.mongodb.client.model.Filters.or;

import java.util.Date;
import java.util.Properties;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import it.unisannio.CARE.model.bloodBag.BloodBag;
import it.unisannio.CARE.model.bloodBag.BloodBagState;
import it.unisannio.CARE.model.bloodBag.Request;
import it.unisannio.CARE.model.bloodBag.RequestState;
import it.unisannio.CARE.model.exceptions.RequestCloneNotSupportedException;
import it.unisannio.CARE.model.util.Constants;
import it.unisannio.CARE.model.util.XMLHelper;

public class BloodBagManager {

	
	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;
	private MongoCollection<Document> collection;
    
	
	
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
	
	
	
	public void addBloodBag(BloodBag bloodBag) {
		

    	Document bloodBagD = Document.parse(bloodBag.toString());	
		this.collection.insertOne(bloodBagD);	
	}
	
	
	
	public void deleteBloodBag(BloodBag bloodBag) {
		
    	Document bloodBagD = Document.parse(bloodBag.toString());	
		this.collection.deleteOne(bloodBagD);
	}
	
	public boolean BloodBagRequestable(String serial_r) {
	Bson filter = and(
			eq("sate", BloodBagState.Available.toString()),
    		eq("serial", serial_r));
	
	  MongoCursor<Document> iterator = this.collection.find().filter(filter).iterator();
	  if(!iterator.hasNext()) 
		  return true;
	  	  return false;
	  
}
	
	
	public void close() {
		
		this.mongoClient.close();
	}

}
