package datamanagers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import com.mongodb.client.MongoDatabase;
import com.mongodb.connection.Stream;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.City;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.Country;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.Province;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.Region;
import users.BloodBagInterface;
import users.BloodGroup;
import users.Node;
import users.User;
import users.BloodBag.BloodBagState;
import users.BloodBag;
import users.Serial;

import com.mongodb.client.MongoCollection;

import static com.mongodb.client.model.Filters.*;

import org.bson.Document;


// classe che implementa tutte le operazioni possibili per il DataBase*/


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
	private static final String ELEMENT_CREATIONDATE = "creationDate";
	private static final String ELEMENT_EXPIRATIONDATE = "expirationDate";
	private static final String ELEMENT_DONATORCF = "donatorCF";
	private static final String ELEMENT_NODE = "node";
	private static final String ELEMENT_BLOODBAGSTATE = "bloodBagState";
	private static final String ELEMENT_NOTE = "note";

	
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

public void addBloodBag(BloodBagInterface s) throws ParseException {
	
	MongoClient mongoClient = new MongoClient(connectionString);
    MongoDatabase database = mongoClient.getDatabase(db);
    
    MongoCollection<Document> collection = database.getCollection(COLLECTION_BAG);
    Document unaSacca = new Document(ELEMENT_SERIAL, s.getSerial().toString())
            .append(ELEMENT_CREATIONDATE, s.getCreationDate().toString()) .append(ELEMENT_EXPIRATIONDATE, s.getExpirationDate().toString())
            .append(ELEMENT_DONATORCF, s.getDonatorCF().toString()) .append(ELEMENT_NODE, s.getNode().toString())
            .append (ELEMENT_BLOODBAGSTATE, s.getBloodBagState().toString()) .append(ELEMENT_NOTE, s.getNote().toString());    
    collection.insertOne(unaSacca); /*inserimento*/
    mongoClient.close();
}


/*
public List<BloodBag> getBloodBagExpiring(Date d,BloodGroup b) throws ParseException {
	
	List<BloodBag> sacche = new ArrayList<BloodBag>();
	
	MongoClient mongoClient = new MongoClient(connectionString);
    MongoDatabase database = mongoClient.getDatabase(db);
    MongoCollection<Document> collection = database.getCollection(COLLECTION_BAG);
    
    
    for (Document current : collection.find()){
    	String serial =current.getString(ELEMENT_SERIAL);
    	String BloodG =current.getString(ELEMENT_GROUP);
    	String creationD =current.getString(ELEMENT_CREATIONDATE);
    	String expirationD =current.getString(ELEMENT_EXPIRATIONDATE);
    	String donatorCF =current.getString(ELEMENT_DONATORCF);
    	String node =current.getString(ELEMENT_NODE);
    	String BloodBagState =current.getString(ELEMENT_BLOODBAGSTATE);
    	String note =current.getString(ELEMENT_NOTE);
    	
    	BloodGroup bg =BloodGroup.valueOf(BloodG);
    	DateFormat format=new  SimpleDateFormat("yyyy-MM-dd");
    	Date cd=format.parse(creationD);
    	Date ed=format.parse(expirationD);
    	
    	/*perche non lo prende ?*/
        /*

    	BloodBagState bbs=BloodBagState.valueOf(BloodBagState);
    	
    	*/
    	/*devo estrarre le stringhe da node per richiamare costruttore node*/
    	
    	/*
    	BloodBag  bl=new  BloodBag(new Serial(serial),bg,cd,ed,donatorCF,n1,bbs,note);
		if(bl.getExpirationDate().before(d)) {
    
	    sacche.add(bl);		
		}
    }
    	
    /*public BloodBag( Serial s,BloodGroup b, Date creationD,Date expirationD,String donator,Node n,BloodBagState BagState,String not) throws ParseException {*/
    /*
	mongoClient.close();
	return sacche;


}*/









	
}