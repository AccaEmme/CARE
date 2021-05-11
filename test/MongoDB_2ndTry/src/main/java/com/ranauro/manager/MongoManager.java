/**
 * @Author  Giuliano Ranauro
 * @Date    11/05/21
 * @IDE     Intellij
 * @Dependencies:   junit, org.mongodb, com.googlecode.json-simple
 * */


package com.ranauro.manager;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.ranauro.sangue.BloodBag;
import com.ranauro.sangue.BloodGroup;
import com.ranauro.sangue.SaccaOLD;
import com.ranauro.sangue.Seriale;
import org.bson.Document;
import org.json.simple.JSONObject;

public class MongoManager {
    private String connectionStringURI = "";
    private String db_name = "";
    private String collection_name = "";

    private static String SERIALE = "SERIAL";
    private static String GRUPPO = "GROUP";
    private static String EXPIRATION = "EXPIRATION_DAY";
    private static String CREATION = "CREATION_DAY";
    private static String ORIGIN = "ORIGIN";

    private static final String DATE_FORMAT = "yyyy-mm-dd_hh-mm-ss";

    public MongoManager(){
        connectionStringURI = createURI();
        String[] db_collection_names = getDbProperties();
        this.db_name = db_collection_names[0];
        this.collection_name = db_collection_names[1];
    }

    //implementazione non necessaria
    public void createDB(){}



    /** ################################ DELETE ################################*/
    public void deleteSacca(SaccaOLD saccaOLD){
        MongoClientURI clientURI = new MongoClientURI(this.connectionStringURI);
        MongoClient mongoClient = new MongoClient(clientURI);

        MongoDatabase mongoDatabase = mongoClient.getDatabase(this.db_name);
        MongoCollection mongoCollection = mongoDatabase.getCollection(this.collection_name);

        mongoCollection.deleteOne(Filters.eq("seriale", saccaOLD.getSeriale().toString()));
        System.out.println("Deleted element: "+ saccaOLD.toString());
        mongoClient.close();
    }

    /** ################################ GET ################################*/
    public List<SaccaOLD> getSacche(){
        List<SaccaOLD> sacche = new ArrayList<>();

        MongoClientURI clientURI = new MongoClientURI(this.connectionStringURI);
        MongoClient mongoClient = new MongoClient(clientURI);

        MongoDatabase mongoDatabase = mongoClient.getDatabase(this.db_name);
        MongoCollection mongoCollection = mongoDatabase.getCollection(this.collection_name);

        FindIterable<Document> iterDoc = mongoCollection.find();

        BloodGroup bloodGroup;
        for (Document document : iterDoc){
            bloodGroup = BloodGroup.valueOf(document.getString(GRUPPO));

            SaccaOLD saccaOLD = new SaccaOLD(new Seriale(document.getString(SERIALE)), bloodGroup);
            sacche.add(saccaOLD);
        }
        System.out.println(sacche.size()+" elements read.");
        return sacche;
    }
    public List<BloodBag> getBloodBags() throws ParseException {
        List<BloodBag> bags = new ArrayList<>();
        MongoClientURI clientURI = new MongoClientURI(this.connectionStringURI);
        MongoClient mongoClient = new MongoClient(clientURI);

        MongoDatabase mongoDatabase = mongoClient.getDatabase(this.db_name);
        MongoCollection mongoCollection = mongoDatabase.getCollection(this.collection_name);

        FindIterable<Document> iterDoc = mongoCollection.find();
        BloodGroup bloodGroup;
        Seriale serial;
        Date expirationDate;
        Date creationDate;
        String origin;

        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        for (Document document : iterDoc){
            bloodGroup = BloodGroup.valueOf(document.getString(GRUPPO));
            serial = new Seriale(document.getString(SERIALE));


            expirationDate = dateFormat.parse(document.getString(EXPIRATION));
            creationDate = dateFormat.parse(document.getString(CREATION));

            origin = document.getString(ORIGIN);

            /*
            System.out.println(bloodGroup);
            System.out.println(serial);
            System.out.println(expirationDate);
            System.out.println(creationDate);
            System.out.println(origin+"\n");*/

            bags.add(new BloodBag(serial, bloodGroup, expirationDate, creationDate, origin));
        }

        return bags;
    }



    /** ################################ CREATE ################################*/
    public void addBloodBag(BloodBag bag){
        MongoClientURI clientURI = new MongoClientURI(this.connectionStringURI);
        MongoClient mongoClient = new MongoClient(clientURI);

        MongoDatabase mongoDatabase = mongoClient.getDatabase(this.db_name);
        MongoCollection mongoCollection = mongoDatabase.getCollection(this.collection_name);

        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);


        Document document = new Document(SERIALE, bag.getSerial().toString());
            document.append(GRUPPO, bag.getBloodGroup().toString());
            document.append(EXPIRATION, dateFormat.format(bag.getExpirationDate()));
            document.append(CREATION, dateFormat.format(bag.getCreationDate()));
            document.append(ORIGIN, bag.getOrigin());

        mongoCollection.insertOne(document);
        System.out.println("Added element: "+document);

        mongoClient.close();
    }

    public void addBloodBag(List<BloodBag> bags){
        MongoClientURI clientURI = new MongoClientURI(this.connectionStringURI);
        MongoClient mongoClient = new MongoClient(clientURI);

        MongoDatabase mongoDatabase = mongoClient.getDatabase(this.db_name);
        MongoCollection mongoCollection = mongoDatabase.getCollection(this.collection_name);

        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

        for (BloodBag bag : bags){
            Document document = new Document(SERIALE, bag.getSerial().toString());
            document.append(GRUPPO, bag.getBloodGroup().toString());
            document.append(EXPIRATION, dateFormat.format(bag.getExpirationDate()));
            document.append(CREATION, dateFormat.format(bag.getCreationDate()));
            document.append(ORIGIN, bag.getOrigin());

            mongoCollection.insertOne(document);
            System.out.println("Added element: "+document);
        }

        mongoClient.close();

    }

    public void addDocument(Document doc){
        MongoClientURI clientURI = new MongoClientURI(this.connectionStringURI);
        MongoClient mongoClient = new MongoClient(clientURI);

        MongoDatabase mongoDatabase = mongoClient.getDatabase(this.db_name);
        MongoCollection mongoCollection = mongoDatabase.getCollection(this.collection_name);

        mongoCollection.insertOne(doc);
        System.out.println("Added document: "+doc);
        mongoClient.close();
    }


    private String[] getDbProperties(){
        /**
         * reading database name and collection name from xml
         * */
        String[] db_collection_names = new String[2];

        Properties properties = new Properties();

        try {
            properties.loadFromXML(new FileInputStream("settings.xml"));
        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * reading from an array, so i can return multiple values insted of only one
         * */
        db_collection_names[0] = properties.getProperty("db_name");
        db_collection_names[1] = properties.getProperty("collection_name");

        return db_collection_names;
    }
    private String createURI(){
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



        username = properties.getProperty("username");      //reading username from xml (private, local)
        password = properties.getProperty("password");      //reading password from xml (private, local)

        return "mongodb+srv://"+username+":"+password+"@care.a1sy7.mongodb.net/test";
    }
    /** ################################ ADVANCED ################################*/

    /**
     * eccezione da personalizzare in futuro
     * */
    public void dump() throws Exception{
        JSONObject jsonObject;
        FileWriter file = null;

        List<BloodBag> bags = this.getBloodBags();

        Date date = new Date();

        SimpleDateFormat sdfDate = new SimpleDateFormat(DATE_FORMAT);
        Date now = new Date();  //time of the dump
        String dumpName = sdfDate.format(now);

        try {
            //new filewriter in append mode
            file = new FileWriter("dumps/"+"dump_"+dumpName+".json",true);
            file.write("[");//array of objects

            //adding every element to the dump
            String expirationString;
            String creationString;
            for (int i = 0; i < bags.size()-1; i++){
                jsonObject = new JSONObject();
                jsonObject.put(SERIALE, bags.get(i).getSerial().toString());
                jsonObject.put(GRUPPO, bags.get(i).getBloodGroup().toString());

                expirationString = sdfDate.format(bags.get(i).getExpirationDate());
                creationString = sdfDate.format(bags.get(i).getCreationDate());

                jsonObject.put(EXPIRATION, expirationString);
                jsonObject.put(CREATION, creationString);

                jsonObject.put(ORIGIN, bags.get(i).getOrigin());


                file.write(jsonObject.toJSONString());
                file.write(",\n");
            }
            //adding last element withoud comma
            jsonObject = new JSONObject();
            jsonObject.put(SERIALE, bags.get(bags.size()-1).getSerial().toString());
            jsonObject.put(GRUPPO, bags.get(bags.size()-1).getBloodGroup().toString());
            expirationString = sdfDate.format(bags.get(bags.size()-1).getExpirationDate());
            creationString = sdfDate.format(bags.get(bags.size()-1).getCreationDate());

            jsonObject.put(EXPIRATION, expirationString);
            jsonObject.put(CREATION, creationString);

            jsonObject.put(ORIGIN, bags.get(bags.size()-1).getOrigin());

            file.write(jsonObject.toJSONString());  //writing last element

            file.write("]");                    //closing the array
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
