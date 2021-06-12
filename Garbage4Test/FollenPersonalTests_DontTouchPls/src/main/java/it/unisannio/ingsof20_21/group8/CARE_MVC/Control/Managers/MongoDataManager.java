package it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Constants;
import org.bson.Document;
import org.json.simple.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;

public class MongoDataManager {
    private String connectionStringURI = "";
    private String db_name = "";
    private String collection_name = "";
/*
    private static String SERIALE = "SERIAL";
    private static String GRUPPO = "GROUP";
    private static String EXPIRATION = "EXPIRATION_DAY";
    private static String CREATION = "CREATION_DAY";
    private static String ORIGIN = "ORIGIN";*/

    public MongoDataManager(){
        connectionStringURI = createURI();
        String[] db_collection_names = getDbProperties();
        this.db_name = db_collection_names[0];
        this.collection_name = db_collection_names[1];
    }
    private String createURI(){
        String username = "";
        String password = "";

        Properties properties = new Properties();

        try {
            properties.loadFromXML(new FileInputStream("C:/Users/giuli/Desktop/mongo_login.xml"));  //pc fisso
            //properties.loadFromXML(new FileInputStream("/Users/folly/Desktop/uri.xml"));  //mac
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

    private String[] getDbProperties(){
        /**
         * reading database name and collection name from xml
         * */
        String[] db_collection_names = new String[2];

        Properties properties = new Properties();

        try {
            properties.loadFromXML(new FileInputStream("localsettings/mongo_settings.xml"));
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

    //implementazione non necessaria
    public void createDB(){}

    public void addUser(User user){
        MongoClientURI clientURI = new MongoClientURI(this.connectionStringURI);
        MongoClient mongoClient = new MongoClient(clientURI);

        MongoDatabase mongoDatabase = mongoClient.getDatabase(this.db_name);
        MongoCollection mongoCollection = mongoDatabase.getCollection(this.collection_name);

        Document document = new Document("username",user.getUsername());
            document.append("password",user.getPassword());
            if (user.getResidence()!=null)
                document.append("location",user.getResidence());
            if (user.getRole()!=null)
                document.append("location",user.getRole());
            if (user.getPasswordLastUpdate()!=null)
                document.append("location",user.getPasswordLastUpdate());

        mongoCollection.insertOne(document);
        System.out.println("Added element: "+document);
        mongoClient.close();
    }


    /** ################################ DELETE ################################*/
    /*
    public void deleteSacca(SaccaOLD saccaOLD){
        MongoClientURI clientURI = new MongoClientURI(this.connectionStringURI);
        MongoClient mongoClient = new MongoClient(clientURI);

        MongoDatabase mongoDatabase = mongoClient.getDatabase(this.db_name);
        MongoCollection mongoCollection = mongoDatabase.getCollection(this.collection_name);

        mongoCollection.deleteOne(Filters.eq("seriale", saccaOLD.getSeriale().toString()));
        System.out.println("Deleted element: "+ saccaOLD.toString());
        mongoClient.close();
    }*/

    /** ################################ GET ################################*/
    /*
    public BloodBag searchBag(Seriale serial){
        MongoClientURI clientURI = new MongoClientURI(this.connectionStringURI);
        MongoClient mongoClient = new MongoClient(clientURI);

        MongoDatabase mongoDatabase = mongoClient.getDatabase(this.db_name);
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(this.collection_name);

        Document document = mongoCollection.find(eq(Costants.SERIAL, serial.toString())).first();

        System.out.println("Document: " + document.toJson());

        BloodGroup bloodGroup = BloodGroup.valueOf(document.getString(Costants.GROUP));
        Seriale serialToUse = new Seriale(document.getString(Costants.SERIAL));

        DateFormat dateFormat = new SimpleDateFormat(Costants.DATE_FORMAT);

        Date expirationDate = null;
        Date creationDate = null;
        try {
            expirationDate = dateFormat.parse(document.getString(Costants.SERIAL));
            creationDate = dateFormat.parse(document.getString(Costants.CREATION));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String origin = document.getString(Costants.ORIGIN);

        mongoClient.close();

        assert expirationDate != null && creationDate != null && origin != null;
        return new BloodBag(serialToUse, bloodGroup, expirationDate, creationDate, origin);
    }*/
    /**
     * @// TODO: 11/05/2021 implementare i filtri come si deve, al momento non ricordo come si fa >:/ */
    /*
    public List<BloodBag> filterByBloodGroup(BloodGroup group){
        List<BloodBag> bags = new ArrayList<>();

        MongoClientURI clientURI = new MongoClientURI(this.connectionStringURI);
        MongoClient mongoClient = new MongoClient(clientURI);

        MongoDatabase mongoDatabase = mongoClient.getDatabase(this.db_name);
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(this.collection_name);


        BloodGroup bloodGroup = null;
        Seriale serial = null;
        Date expirationDate = null;
        Date creationDate = null;
        String origin = null;

        DateFormat dateFormat = new SimpleDateFormat(Costants.DATE_FORMAT);
        for (Document document : mongoCollection.find(eq(Costants.GROUP,group.toString()))){
            bloodGroup = BloodGroup.valueOf(document.getString(Costants.GROUP));
            serial = new Seriale(document.getString(Costants.SERIAL));

            try {
                expirationDate = dateFormat.parse(document.getString(Costants.EXPIRATION));
                creationDate = dateFormat.parse(document.getString(Costants.CREATION));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            origin = document.getString(Costants.ORIGIN);

            assert serial != null && bloodGroup != null && expirationDate != null && creationDate != null && origin != null;

            bags.add(new BloodBag(serial, bloodGroup, expirationDate, creationDate, origin));
        }

        mongoClient.close();

        return bags;
    }*/

    /*
    public BloodBag getFirst(){
        MongoClientURI clientURI = new MongoClientURI(this.connectionStringURI);
        MongoClient mongoClient = new MongoClient(clientURI);

        MongoDatabase mongoDatabase = mongoClient.getDatabase(this.db_name);
        MongoCollection mongoCollection = mongoDatabase.getCollection(this.collection_name);

        Document document = (Document) mongoCollection.find().first();
        System.out.println("Document: " + document.toJson());

        BloodGroup bloodGroup = BloodGroup.valueOf(document.getString(Costants.GROUP));
        Seriale serial = new Seriale(document.getString(Costants.SERIAL));

        DateFormat dateFormat = new SimpleDateFormat(Costants.DATE_FORMAT);

        Date expirationDate = null;
        Date creationDate = null;
        try {
            expirationDate = dateFormat.parse(document.getString(Costants.EXPIRATION));
            creationDate = dateFormat.parse(document.getString(Costants.CREATION));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String origin = document.getString(Costants.ORIGIN);

        mongoClient.close();

        assert bloodGroup != null && serial != null && expirationDate != null && creationDate != null && origin != null;
        return new BloodBag(serial, bloodGroup, expirationDate, creationDate, origin);
    }*/

    /*
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

        DateFormat dateFormat = new SimpleDateFormat(Costants.DATE_FORMAT);
        for (Document document : iterDoc){
            bloodGroup = BloodGroup.valueOf(document.getString(Costants.GROUP));
            serial = new Seriale(document.getString(Costants.SERIAL));


            expirationDate = dateFormat.parse(document.getString(Costants.EXPIRATION));
            creationDate = dateFormat.parse(document.getString(Costants.CREATION));

            origin = document.getString(Costants.ORIGIN);

            assert bloodGroup != null && serial != null && expirationDate != null && creationDate != null && origin != null;

            bags.add(new BloodBag(serial, bloodGroup, expirationDate, creationDate, origin));
        }

        mongoClient.close();
        return bags;
    }*/



    /** ################################ CREATE ################################*/
    /*
    public void addBloodBag(BloodBag bag){
        MongoClientURI clientURI = new MongoClientURI(this.connectionStringURI);
        MongoClient mongoClient = new MongoClient(clientURI);

        MongoDatabase mongoDatabase = mongoClient.getDatabase(this.db_name);
        MongoCollection mongoCollection = mongoDatabase.getCollection(this.collection_name);

        DateFormat dateFormat = new SimpleDateFormat(Costants.DATE_FORMAT);


        Document document = new Document(Costants.SERIAL, bag.getSerial().toString());
        document.append(Costants.GROUP, bag.getBloodGroup().toString());
        document.append(Costants.EXPIRATION, dateFormat.format(bag.getExpirationDate()));
        document.append(Costants.CREATION, dateFormat.format(bag.getCreationDate()));
        document.append(Costants.ORIGIN, bag.getOrigin());

        mongoCollection.insertOne(document);
        System.out.println("Added element: "+document);

        mongoClient.close();
    }*/

    /*
    public void addBloodBag(List<BloodBag> bags){
        MongoClientURI clientURI = new MongoClientURI(this.connectionStringURI);
        MongoClient mongoClient = new MongoClient(clientURI);

        MongoDatabase mongoDatabase = mongoClient.getDatabase(this.db_name);
        MongoCollection mongoCollection = mongoDatabase.getCollection(this.collection_name);

        DateFormat dateFormat = new SimpleDateFormat(Costants.DATE_FORMAT);

        for (BloodBag bag : bags){
            Document document = new Document(Costants.SERIAL, bag.getSerial().toString());
            document.append(Costants.GROUP, bag.getBloodGroup().toString());
            document.append(Costants.EXPIRATION, dateFormat.format(bag.getExpirationDate()));
            document.append(Costants.CREATION, dateFormat.format(bag.getCreationDate()));
            document.append(Costants.ORIGIN, bag.getOrigin());

            mongoCollection.insertOne(document);
            System.out.println("Added element: "+document);
        }

        mongoClient.close();

    }*/

/*
    public void addDocument(Document doc){
        MongoClientURI clientURI = new MongoClientURI(this.connectionStringURI);
        MongoClient mongoClient = new MongoClient(clientURI);

        MongoDatabase mongoDatabase = mongoClient.getDatabase(this.db_name);
        MongoCollection mongoCollection = mongoDatabase.getCollection(this.collection_name);

        mongoCollection.insertOne(doc);
        System.out.println("Added document: "+doc);
        mongoClient.close();
    }*/


    /*
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
    }*/

    /*
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
    }*/
    /** ################################ ADVANCED ################################*/

    /**
     * eccezione da personalizzare in futuro
     * */


    public void dump() throws Exception{
        JSONObject jsonObject;
        FileWriter file = null;

        List<BloodBag> bags =    null;   //this.getBloodBags();

        Date date = new Date();

        SimpleDateFormat sdfDate = new SimpleDateFormat(Constants.DATE_FORMAT);
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
                jsonObject.put(Constants.SERIAL, bags.get(i).getSerial().toString());
                jsonObject.put(Constants.GROUP, bags.get(i).getBloodGroup().toString());

                expirationString = sdfDate.format(bags.get(i).getExpirationDate());
                creationString = sdfDate.format(bags.get(i).getCreationDate());

                jsonObject.put(Constants.EXPIRATION, expirationString);
                jsonObject.put(Constants.CREATION, creationString);

                jsonObject.put(Constants.ORIGIN, bags.get(i).getNode().toString());


                file.write(jsonObject.toJSONString());
                file.write(",\n");
            }
            //adding last element withoud comma
            jsonObject = new JSONObject();
            jsonObject.put(Constants.SERIAL, bags.get(bags.size()-1).getSerial().toString());
            jsonObject.put(Constants.GROUP, bags.get(bags.size()-1).getBloodGroup().toString());
            expirationString = sdfDate.format(bags.get(bags.size()-1).getExpirationDate());
            creationString = sdfDate.format(bags.get(bags.size()-1).getCreationDate());

            jsonObject.put(Constants.EXPIRATION, expirationString);
            jsonObject.put(Constants.CREATION, creationString);

            jsonObject.put(Constants.ORIGIN, bags.get(bags.size()-1).getNode().toString());

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

    public String getConnectionStringURI() {
        return connectionStringURI;
    }

    public String getDb_name() {
        return db_name;
    }

    public String getCollection_name() {
        return collection_name;
    }

    public static String getDateFormat() {
        return Constants.DATE_FORMAT;
    }

    public void setConnectionStringURI(String connectionStringURI) {
        this.connectionStringURI = connectionStringURI;
    }

    public void setDb_name(String db_name) {
        this.db_name = db_name;
    }

    public void setCollection_name(String collection_name) {
        this.collection_name = collection_name;
    }

}
