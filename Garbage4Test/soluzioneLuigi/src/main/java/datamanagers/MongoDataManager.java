package datamanagers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import java.util.InvalidPropertiesFormatException;

import java.util.Properties;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import com.mongodb.client.MongoDatabase;

import users.BloodBag;
import users.User;

import com.mongodb.client.MongoCollection;

import static com.mongodb.client.model.Filters.*;

import org.bson.Document;





public class MongoDataManager implements magazziniereInterface,adminInterface {
	
	
	
	private static final String TAG_DB = "db_mongo_name";
	private static final String TAG_HOST = "db_mongo_host";
	private static final String TAG_PORT = "db_mongo_port";
	
	
	private static final String COLLECTION_USER= "users";
	private static final String COLLECTION_BAG= "blood-bags";
	private static final String ELEMENT_USERNAME = "username";
	private static final String ELEMENT_PASSWORD = "password";
	private static final String ELEMENT_ROLE = "role";
	private static final String ELEMENT_GROUP = "BloodGroup";
	private static final String ELEMENT_SERIAL = "serial";
	
	
	private final String db;
	private final MongoClientURI connectionString;
	
	
	
	
	public MongoDataManager() {
		Properties loadProps = new Properties();
	    try {
			loadProps.loadFromXML(new FileInputStream("localsettings/db_settings.xml"));
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    String host = loadProps.getProperty(TAG_HOST);
	    String port = loadProps.getProperty(TAG_PORT);
	    db = loadProps.getProperty(TAG_DB);
	    connectionString = new MongoClientURI("mongodb://"+host+":"+port);
	    
	   
	}
	
	public void createDB() {	
	}

	public void dropDB() {
		MongoClient mongoClient = new MongoClient(connectionString);
		MongoDatabase database = mongoClient.getDatabase(db);
		database.drop();
		mongoClient.close();
	}
	
	
	public void addUser(User u) throws ParseException {
		
			MongoClient mongoClient = new MongoClient(connectionString);
			MongoDatabase database = mongoClient.getDatabase(db);
			MongoCollection<Document> collection = database.getCollection(COLLECTION_USER);
			Document user = new Document(ELEMENT_USERNAME, u.getUsername())
                .append(ELEMENT_PASSWORD, u.getPassword()).append(ELEMENT_ROLE, u.getRole().name());    
			
			
			collection.insertOne(user); /*inserimento*/
			
	
			
			mongoClient.close();
	}
	public void addBloodBag(BloodBag b) throws ParseException {
		
		MongoClient mongoClient = new MongoClient(connectionString);
		MongoDatabase database = mongoClient.getDatabase(db);
		MongoCollection<Document> collection = database.getCollection(COLLECTION_BAG);
		Document bag = new Document(ELEMENT_GROUP, b.getBloodGroup().toString())
            .append(ELEMENT_SERIAL, b.getSerial().toString());    
		
		
		collection.insertOne(bag); /*inserimento*/
		

		
		mongoClient.close();
}
	
	public void editUser(User  u) throws ParseException {
	
		
		
			MongoClient mongoClient = new MongoClient(connectionString);
			MongoDatabase database = mongoClient.getDatabase(db);
	    

			MongoCollection<Document> collection = database.getCollection(COLLECTION_USER);
	
	   
			
			Document user = new Document(ELEMENT_USERNAME, u.getUsername())
              .append(ELEMENT_PASSWORD, u.getPassword()); 
			
			if((collection.replaceOne((eq(ELEMENT_USERNAME,u.getUsername())), user).getMatchedCount())==0) {
				System.out.println("user not found");
	
			}
			else {
				System.out.println("user uptated");
			}
			
		
		
			mongoClient.close();
		
	}




public void deleteUser(User  u) throws ParseException {

	
	MongoClient mongoClient = new MongoClient(connectionString);
	MongoDatabase database = mongoClient.getDatabase(db);


	MongoCollection<Document> collection = database.getCollection(COLLECTION_USER);

	if((collection.deleteOne((eq(ELEMENT_USERNAME,u.getUsername()))).getDeletedCount())==0) {
		System.out.println("user not found");

	}
	else {
		System.out.println("user deleted");
	}

		
	
	
			mongoClient.close();
	
	}






	
}