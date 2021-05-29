package prove;

import static com.mongodb.client.model.Filters.eq;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;
/*UsrManager*/
public class MdbUserDataManager implements UsersDataManager {
	
	/*per prendere le informazioni dai setting*/
	private static final String TAG_DB = "db_mongo_name";
	private static final String TAG_HOST = "db_mongo_host";
	private static final String TAG_PORT = "db_mongo_port";
	
	/*tag nel json*/
	private static final String COLLECTION_USER= "users";
	private static final String ELEMENT_USERNAME = "username";
	private static final String ELEMENT_PASSWORD = "password";
	
	
	private final String db;
	private final MongoClientURI connectionString;
	
	/*tiro fuori le informazioni dal nostro db*/
	public MdbUserDataManager() {
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
	
	/* crea un documento e lo inserisce*/
	public void addUser(User u) throws ParseException {
		
			MongoClient mongoClient = new MongoClient(connectionString);
			MongoDatabase database = mongoClient.getDatabase(db);
			MongoCollection<Document> collection = database.getCollection(COLLECTION_USER);
			Document user = new Document(ELEMENT_USERNAME, u.getUsername())
                .append(ELEMENT_PASSWORD, u.getPassword());    
			collection.insertOne(user); /*inserimento*/
			mongoClient.close();
	}
	
	
	public void uptadeUser(User  u) throws ParseException {
	
		
		
			MongoClient mongoClient = new MongoClient(connectionString);
			MongoDatabase database = mongoClient.getDatabase(db);
	    

			MongoCollection<Document> collection = database.getCollection(COLLECTION_USER);
	
	   
			
			Document user = new Document(ELEMENT_USERNAME, u.getUsername())
              .append(ELEMENT_PASSWORD, u.getPassword()); 
			collection.replaceOne((eq(ELEMENT_USERNAME,u.getUsername())), user);
			
		
		
			mongoClient.close();
		
	}




public void deleteUser(User  u) throws ParseException {

	
			MongoClient mongoClient = new MongoClient(connectionString);
			MongoDatabase database = mongoClient.getDatabase(db);
    

			MongoCollection<Document> collection = database.getCollection(COLLECTION_USER);

   
		
		
			collection.deleteOne((eq(ELEMENT_USERNAME,u.getUsername())));
		
	
	
			mongoClient.close();
	
	}
	
}
