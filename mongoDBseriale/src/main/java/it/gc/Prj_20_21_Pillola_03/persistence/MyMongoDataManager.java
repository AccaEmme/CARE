package it.gc.Prj_20_21_Pillola_03.persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import it.gc.Prj_20_21_Pillola_03.sacca.GruppoSanguigno;
import it.gc.Prj_20_21_Pillola_03.sacca.Sacca;
import it.gc.Prj_20_21_Pillola_03.sacca.Seriale;

import com.mongodb.client.MongoCollection;

import static com.mongodb.client.model.Filters.*;

import org.bson.Document;

public class MyMongoDataManager implements DataManager {
	
	private static final String TAG_DB = "db_mongo_name";
	private static final String TAG_HOST = "db_mongo_host";
	private static final String TAG_PORT = "db_mongo_port";
	
	private static final String COLLECTION_SACCHE = "SACCHE";
	private static final String ELEMENT_SERIALE = "seriale";
	private static final String ELEMENT_GRUPPO = "gruppo";
	
	
	
	private final String db;
	private final MongoClientURI connectionString;
	
	
	public MyMongoDataManager() {
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
	
	
	public void deleteSacca(String s) {
		MongoClient mongoClient = new MongoClient(connectionString);
		MongoDatabase database = mongoClient.getDatabase(db);
		MongoCollection<Document> collection = database.getCollection(COLLECTION_SACCHE);
         
	     collection.deleteOne(Filters.eq("seriale", s ));
		mongoClient.close();
	}

	public void addSacca(Sacca s) {
		MongoClient mongoClient = new MongoClient(connectionString);
	    MongoDatabase database = mongoClient.getDatabase(db);
	    MongoCollection<Document> collection = database.getCollection(COLLECTION_SACCHE);
	    Document unaSacca = new Document(ELEMENT_SERIALE, s.getSeriale().toString())
                .append(ELEMENT_GRUPPO, s.getGruppo().toString());    
	    collection.insertOne(unaSacca); 
	    mongoClient.close();
	}

	public List<Sacca> getSacche(GruppoSanguigno g) {
		
		List<Sacca> sacche = new ArrayList<Sacca>();
		
		MongoClient mongoClient = new MongoClient(connectionString);
	    MongoDatabase database = mongoClient.getDatabase(db);
	    MongoCollection<Document> collection = database.getCollection(COLLECTION_SACCHE);
	
		for (Document current : collection.find(eq(ELEMENT_GRUPPO,g.toString()))) {
			Sacca s = new Sacca(new Seriale(current.getString(ELEMENT_SERIALE)), GruppoSanguigno.valueOf(current.getString(ELEMENT_GRUPPO)));
		    sacche.add(s);		
		}
		mongoClient.close();
		return sacche;

	}



	
	
}
