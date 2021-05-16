package it.unisannio.ingsof20_21.group8.CARE_MVC.Control;

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

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Blood;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;

public class MongoDataManager implements DataManager {
	
	private static final String TAG_HOST = "db_mongo_host";
	private static final String TAG_PORT = "db_mongo_port";
	private static final String TAG_DB = "db_mongo_name";
	
	private static final String COLLECTION_SACCHE = "SACCHE";
	private static final String ELEMENT_Serial = "Serial";
	private static final String ELEMENT_GRUPPO = "gruppo";
	
	
	
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

	public void addBloodBag(BloodBag s) {
		MongoClient mongoClient = new MongoClient(connectionString);
	    MongoDatabase database = mongoClient.getDatabase(db);
	    MongoCollection<Document> collection = database.getCollection(COLLECTION_SACCHE);
	    Document unaBloodBag = new Document(ELEMENT_Serial, s.getSerial().toString())
                .append(ELEMENT_GRUPPO, s.getBloodType().toString());    
	    collection.insertOne(unaBloodBag); 
	    mongoClient.close();
	}

	public List<BloodBag> getBloodBag(Blood g) {
		
		List<BloodBag> sacche = new ArrayList<BloodBag>();
		
		MongoClient mongoClient = new MongoClient(connectionString);
	    MongoDatabase database = mongoClient.getDatabase(db);
	    MongoCollection<Document> collection = database.getCollection(COLLECTION_SACCHE);
	
		for (Document current : collection.find(eq(ELEMENT_GRUPPO,g.toString()))) {
			BloodBag s = null;
			try {
				s = new BloodBag(
											Blood.valueOf( current.getString(ELEMENT_GRUPPO) )
										);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    sacche.add(s);		
		}
		mongoClient.close();
		return sacche;

	}

	
}
