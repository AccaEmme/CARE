/**
 * @author giuliano ranauro
 * Date: 29-10-20
 * Ide: Intellij
 * JDK: 1.8
 */
package org.example.deleteFromDB;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.ranauro.sangue.BloodGroup;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class DeleteMultipleMain {
    public static void main(String[] args) {
        MongoClientURI clientURI = new MongoClientURI(createURI());
        MongoClient mongoClient = new MongoClient(clientURI);

        MongoDatabase mongoDatabase = mongoClient.getDatabase("MongoDB");
        MongoCollection mongoCollection = mongoDatabase.getCollection("test");

        //mongoCollection.deleteOne(Filters.eq("GRUPPO", "Bm"));

        BloodGroup[] gruppi = BloodGroup.values();
        for (BloodGroup bloodGroup : gruppi){
            mongoCollection.deleteMany(Filters.eq("GRUPPO", bloodGroup.toString()));
        }


        System.out.println("Elementi eliminati.");
    }
    private static String createURI(){
        String username = "";
        String password = "";

        Properties properties = new Properties();

        try {
            //properties.loadFromXML(new FileInputStream("C:/Users/giuli/Desktop/uri.xml"));  //pc fisso
            properties.loadFromXML(new FileInputStream("/Users/folly/Desktop/uri.xml"));  //mac
        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        username = properties.getProperty("username");
        password = properties.getProperty("password");

        return "mongodb+srv://"+username+":"+password+"@care.a1sy7.mongodb.net/test";
    }
}


