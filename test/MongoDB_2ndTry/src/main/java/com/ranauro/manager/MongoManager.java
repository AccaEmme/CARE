package com.ranauro.manager;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.ranauro.sangue.GruppoSanguigno;
import com.ranauro.sangue.Sacca;
import com.ranauro.sangue.Seriale;
import org.bson.Document;
import org.json.simple.JSONObject;

public class MongoManager {
    private String connectionStringURI = "";
    private String db_name = "";
    private String collection_name = "";

    private static String SERIALE = "seriale";
    private static String GRUPPO = "GRUPPO";

    public MongoManager(){
        connectionStringURI = createURI();
        String[] db_collection_names = getDbProperties();
        this.db_name = db_collection_names[0];
        this.collection_name = db_collection_names[1];
    }

    //implementazione non necessaria
    public void createDB(){}



    /** ################################ DELETE ################################*/
    public void deleteSacca(Sacca sacca){
        MongoClientURI clientURI = new MongoClientURI(this.connectionStringURI);
        MongoClient mongoClient = new MongoClient(clientURI);

        MongoDatabase mongoDatabase = mongoClient.getDatabase(this.db_name);
        MongoCollection mongoCollection = mongoDatabase.getCollection(this.collection_name);

        mongoCollection.deleteOne(Filters.eq("seriale", sacca.getSeriale().toString()));
        System.out.println("Deleted element: "+sacca.toString());
        mongoClient.close();
    }

    /** ################################ GET ################################*/
    public List<Sacca> getSacche(){
        List<Sacca> sacche = new ArrayList<>();

        MongoClientURI clientURI = new MongoClientURI(this.connectionStringURI);
        MongoClient mongoClient = new MongoClient(clientURI);

        MongoDatabase mongoDatabase = mongoClient.getDatabase(this.db_name);
        MongoCollection mongoCollection = mongoDatabase.getCollection(this.collection_name);

        FindIterable<Document> iterDoc = mongoCollection.find();

        GruppoSanguigno gruppoSanguigno;
        for (Document document : iterDoc){
            gruppoSanguigno = GruppoSanguigno.valueOf(document.getString(GRUPPO));

            Sacca sacca = new Sacca(new Seriale(document.getString(SERIALE)), gruppoSanguigno );
            sacche.add(sacca);
        }
        System.out.println(sacche.size()+" elements read.");
        return sacche;
    }



    /** ################################ CREATE ################################*/
    public void addSacca(Sacca sacca){
        MongoClientURI clientURI = new MongoClientURI(this.connectionStringURI);
        MongoClient mongoClient = new MongoClient(clientURI);

        MongoDatabase mongoDatabase = mongoClient.getDatabase(this.db_name);
        MongoCollection mongoCollection = mongoDatabase.getCollection(this.collection_name);

        Document document = new Document(SERIALE, sacca.getSerialeString());
            document.append(GRUPPO, sacca.getGruppoString());

        mongoCollection.insertOne(document);
        System.out.println("Added element: "+document);

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



        username = properties.getProperty("username");
        password = properties.getProperty("password");

        return "mongodb+srv://"+username+":"+password+"@care.a1sy7.mongodb.net/test";
    }
    /** ################################ ADVANCED ################################*/

    /**
     * eccezione da personalizzare in futuro
     * */
    public void dump() throws Exception{
        JSONObject jsonObject;
        FileWriter file = null;

        List<Sacca> sacche = this.getSacche();

        Date date = new Date();

        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd_HH:mm");//dd/MM/yyyy
        Date now = new Date();
        String dumpName = sdfDate.format(now);

        try {
            file = new FileWriter("dumps/"+"dump_"+dumpName+".json",true);
            file.write("[");

            for (int i = 0; i < sacche.size()-1; i++){
                jsonObject = new JSONObject();
                jsonObject.put(SERIALE, sacche.get(i).getSerialeString());
                jsonObject.put(GRUPPO, sacche.get(i).getGruppoString());

                file.write(jsonObject.toJSONString());
                file.write(",\n");
            }
            jsonObject = new JSONObject();
            jsonObject.put(SERIALE, sacche.get(sacche.size()-1).getSerialeString());
            jsonObject.put(GRUPPO, sacche.get(sacche.size()-1).getGruppoString());

            file.write(jsonObject.toJSONString());

            file.write("]");
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
