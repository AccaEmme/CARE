package it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.Interface;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Exceptions.StateException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag.BloodBagState;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodGroup;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Constants;

public class MongoBloodBagDataManager implements BloodBagDataManagerInterface {

	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;
	private MongoCollection<Document> collection;
	
	public MongoBloodBagDataManager(String URI, String mongoDatabaseName) {
		
		this.mongoClient = new MongoClient(new MongoClientURI(URI));
		this.mongoDatabase = mongoClient.getDatabase(mongoDatabaseName);
		collection = getCollection();
	}
	
	/*
	"{\"serial\": \"" 	  			+ this.serial   		+ "\""
	+", \"bloodGroup\": \""  		+ this.bloodGroup 		+ "\"" 
	+", \"creationDate\": \"" 		+ new SimpleDateFormat(Constants.DATE_FORMAT_STRING).format(creationDate) 	+ "\""
	+", \"expireDate\": \"" 		+ new SimpleDateFormat(Constants.DATE_FORMAT_STRING).format(expirationDate) + "\""
	+", \"donatorCF\": \"" 			+ this.donatorCF 		+ "\""
	+", \"node\": " 				+ this.node.toString()	+ ""
	+", \"bloodBagState\": \"" 		+ this.bloodBagState 	+ "\""
	+", \"note\": \"" + this.note 	+ "\""
	+ "}";
	*/

	
	/*aggiunge sacche già esistenti perche provengono da nodi esterni
	 * al magazino centrale, le sacche dei magazzini periferici invece vengono gestite con SQL
	 * (quindi solo il magazziniere centrale lo può fare)
	 */
	
	@Override
	public void addBloodBag(BloodBag bloodBag) {
		
		MongoCollection<Document> collection = mongoDatabase.getCollection("blood-bags");

       
		Bson condition = new Document("$eq", bloodBag.getSerial().toString());
		Bson filter = new Document("serial", condition);
       
		if(collection.find().filter(filter).iterator().hasNext())
   	
			new Exception("esiste già");
		else {
   
			Document requestD = Document.parse(bloodBag.toString());
			collection.insertOne(requestD);
   }

	}
// metodi per modificare le saccche che usa il magazziniere centrale
	@Override
	public void useBloodBag(BloodBag bloodBag) {
		
		Bson condition = new Document("$eq", bloodBag.getSerial().toString());
		Bson condition2 = new Document("$eq", BloodBagState.Available.toString());
		Bson filter = new Document("serial", condition).append("bloodBagState", condition2);
     
		bloodBag.useBag();
		Document editedRequest = Document.parse(bloodBag.toString()); 
			
	      try {
				if(collection.replaceOne(filter, editedRequest).getMatchedCount()==0) {
					
					throw new StateException("Stato della sacca non compatibile con l'operazione da eseguire. La sacca potrebbe essere stata trasferita o cestinata precedentemente.");
				} 
				else {
					
					System.out.println("Sacca aggiornata.");
				}
	      }catch(StateException e) {
	     	
	     	System.err.println(e.getMessage());
	     	System.err.println("La sacca "+ bloodBag +" potrebbe essere già stata elaborata...");
	     	e.printStackTrace();
	     	
	     }
	}

	@Override
	public void dropBloodBag(BloodBag bloodBag) {
		
		Bson condition = new Document("$eq", bloodBag.getSerial().toString());
	    Bson condition2 = new Document("$eq", BloodBagState.Available.toString());
        Bson filter = new Document("serial", condition).append("bloodBagState", condition2);
       
        bloodBag.dropBag();
        Document editedRequest = Document.parse(bloodBag.toString()); 
		
        try {
			if(collection.replaceOne(filter, editedRequest).getMatchedCount()==0) {
				
				throw new StateException("Stato della sacca non compatibile con l'operazione da eseguire. La sacca potrebbe essere stata trasferita o cestinata precedentemente.");
			} 
			else {
				
				System.out.println("Sacca aggiornata.");
			}
        }catch(StateException e) {
       	
       	System.err.println(e.getMessage());
       	System.err.println("La sacca "+ bloodBag +" potrebbe essere già stata elaborata...");
       	e.printStackTrace();
       	
       }
	}

	@Override
	public void transferBloodBag(BloodBag bloodBag) {
		
		Bson condition = new Document("$eq", bloodBag.getSerial().toString());
	    Bson condition2 = new Document("$eq", BloodBagState.Available.toString());
	    Bson filter = new Document("serial", condition).append("bloodBagState", condition2);
	    
	    bloodBag.transferBag();
	    Document editedRequest = Document.parse(bloodBag.toString()); 
		
	    try {
			if(collection.replaceOne(filter, editedRequest).getMatchedCount()==0) {
				
				throw new StateException("Stato della sacca non compatibile con l'operazione da eseguire. La sacca potrebbe essere stata trasferita o cestinata precedentemente.");
			}
			else {
				
					System.out.println("Sacca aggiornata.");
				}
			}catch(StateException e) {
      	
      	System.err.println(e.getMessage());
      	System.err.println("La sacca "+ bloodBag +" potrebbe essere già stata elaborata...");
      	e.printStackTrace();
      	
      }
	}

	@Override
	public List<Document> getBagsByGroup(BloodGroup bloodGroup) {
		
		List<Document> bloodBags = new ArrayList<>();
		
		Bson condition = new Document("$eq", BloodBagState.Available.toString());
		Bson condition2 = new Document("$eq", bloodGroup);
		Bson filter = new Document("bloodBagState", condition).append("bloodGroup", condition2);
        
		MongoCursor<Document> iterator= collection.find().filter(filter).iterator();
        
        while(iterator.hasNext()) {
        	
        	bloodBags.add(iterator.next());
        }
        
        return bloodBags;
	}
	
	private MongoCollection<Document> getCollection() {
		
		boolean exists = false;
        for (String name : mongoDatabase.listCollectionNames()) {
        	
                    if(name.equals("blood-bags")) exists=true;
        }
        
        if(exists)
        	return mongoDatabase.getCollection("blood-bags");
        mongoDatabase.createCollection("blood-bags");
        return mongoDatabase.getCollection("blood-bags");
	}

}
