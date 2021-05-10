package org.example;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class SimpleConnection {

    @Test
    public void connect_to_db(){
        MongoClientURI clientURI = new MongoClientURI(createURI());
        MongoClient mongoClient = new MongoClient(clientURI);

        MongoDatabase md = mongoClient.getDatabase("MongoDB");
        MongoCollection collection = md.getCollection("test");

        Document document = new Document("name", "peppe");
        document.append("Sex", "a lot");
        document.append("Age", "21");

        collection.insertOne(document);
    }

    private String createURI(){
        String username = "";
        String password = "";

        Properties properties = new Properties();

        try {
            properties.loadFromXML(new FileInputStream("C:/Users/giuli/Desktop/uri.xml"));
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
