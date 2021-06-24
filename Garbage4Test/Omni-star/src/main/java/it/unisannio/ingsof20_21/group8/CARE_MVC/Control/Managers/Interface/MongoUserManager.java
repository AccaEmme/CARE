package it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.Interface;

import static com.mongodb.client.model.Filters.eq;

import java.text.ParseException;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;

public class MongoUserManager implements  UserDataManagerInterface {
	
	
	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;
	private MongoCollection<Document> collection;
	
	public MongoUserManager(String URI, String mongoDatabaseName) {
		
		this.mongoClient = new MongoClient(new MongoClientURI(URI));
		this.mongoDatabase = mongoClient.getDatabase(mongoDatabaseName);
		collection = getCollection();
	}
	
	
	@Override
	public void addUser(User user) {
		
		MongoCollection<Document> collection = mongoDatabase.getCollection("users");

       
		Bson condition = new Document("$eq", user.getUsername().toString());
		Bson filter = new Document("username", condition);
       
		if(collection.find().filter(filter).iterator().hasNext())
   	
			new Exception("esiste già");
		else {
   
			Document requestD = Document.parse(user.toString());
			collection.insertOne(requestD);
   }
	}
	
	@Override
    public void deleteUser(User user) {
		MongoCollection<Document> collection = mongoDatabase.getCollection("users");

	       
		Bson condition = new Document("$eq", user.getUsername().toString());
		Bson filter = new Document("username", condition);
       
		if(collection.deleteOne(filter).getDeletedCount()==0) {
			new Exception("non esite");
		}
	
	
    }

    @Override
    public void assignRole(User u)  {
        MongoClientURI clientURI = new MongoClientURI(this.connectionStringURI);
        MongoClient mongoClient = new MongoClient(clientURI);

        MongoDatabase mongoDatabase = mongoClient.getDatabase(this.db_name);
		MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION_USER);
        Document userD=u.getDocument();

		if((collection.replaceOne((eq(ELEMENT_USERNAME,u.getUsername())), userD).getMatchedCount())==0) {
			System.out.println("user not found");
		} else {
			System.out.println("user uptated");
		}

		mongoClient.close();
    }
    
    
    
    
private MongoCollection<Document> getCollection() {
		
		boolean exists = false;
        for (String name : mongoDatabase.listCollectionNames()) {
        	
                    if(name.equals("users")) exists=true;
        }
        
        if(exists)
        	return mongoDatabase.getCollection("users");
        mongoDatabase.createCollection("users");
        return mongoDatabase.getCollection("users");
	}
}
