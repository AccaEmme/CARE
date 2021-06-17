package it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodGroup;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Serial;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Interfaces.BloodBagInterface;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Interfaces.StoreManagerInterface;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Node.Node;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.Exceptions.NullUserException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.Exceptions.UserException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.Role;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Constants;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Exceptions.NullPasswordException;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.City;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.Country;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.Province;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.Region;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Logger;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Password;
import org.bson.Document;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.json.JSONObject;
/*import org.json.simple.JSONObject;*/
import static com.mongodb.client.model.Filters.eq;



public class MongoDataManager implements AdminInterface, WhareHouseWorkerInterface,StoreManagerInterface {
    private String connectionStringURI = "";
    private String db_name = "";
    private String collection_name = "";

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
/*
    private static String SERIALE = "SERIAL";
    private static String GRUPPO = "GROUP";
    private static String EXPIRATION = "EXPIRATION_DAY";
    private static String CREATION = "CREATION_DAY";
    private static String ORIGIN = "ORIGIN";*/

    
    /*il costruttore deve solo andarsi a prendere i parametri generali*/
    // il costruttore decide quale collezione utilizzare senno devo fare un set collection name
    
    public MongoDataManager(){
        connectionStringURI = createURI();
        String[] db_collection_names = getDbProperties();
        this.db_name = db_collection_names[0];

    }
    private String createURI(){
        String username = "";
        String password = "";
        String db_host = "";

        Properties properties = new Properties();

        try {
        	properties.loadFromXML(new FileInputStream("./../../uri.xml"));

        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



        username = properties.getProperty("username");      //reading username from xml (private, local)
        password = properties.getProperty("password");      //reading password from xml (private, local)
        db_host = properties.getProperty("db_host");
        return "mongodb+srv://"+username+":"+password+"@"+db_host;
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

    public void addUser(User user){
        MongoClientURI clientURI = new MongoClientURI(this.connectionStringURI);
        MongoClient mongoClient = new MongoClient(clientURI);

        MongoDatabase mongoDatabase = mongoClient.getDatabase(this.db_name);
        MongoCollection mongoCollection = mongoDatabase.getCollection(COLLECTION_USER);

        Document document = user.getDocument();

        mongoCollection.insertOne(document);
        System.out.println("Added element: "+document);
        mongoClient.close();
    }

    @Override
    public void deleteUser(User u) throws ParseException {
        MongoClientURI clientURI = new MongoClientURI(this.connectionStringURI);
        MongoClient mongoClient = new MongoClient(clientURI);

        MongoDatabase mongoDatabase = mongoClient.getDatabase(this.db_name);
        MongoCollection mongoCollection = mongoDatabase.getCollection(COLLECTION_USER);

        if((mongoCollection.deleteOne((eq(ELEMENT_USERNAME,u.getUsername()))).getDeletedCount())==0) {
            System.out.println("user not found");

        }
        else {
            System.out.println("user deleted");
        }




        mongoClient.close();
    }

    @Override
    public void editUser(User u) throws ParseException {

    }

    private void validateUser(User user) throws UserException, NullUserException, NullPasswordException {
        if (user == null)
            throw new NullUserException("The user cannot be null!");
        if (user.getPassword() == null)
            throw new NullPasswordException("The password cannot be null");
        if (user.getUsername() == null)
            throw new UserException("The username cannot be null");
    }

    public User validateLogin(String username, String password) throws UserException, NullPasswordException {
        MongoDataManager manager = new MongoDataManager();

        User dbUser = manager.getUser(username);

        System.out.println(dbUser.getDocument());
        if (dbUser==null)   throw new UserException("User not found!");

        User inUser = null;
        inUser = new User(username,password);

        if (inUser.getPassword().equals(dbUser.getPassword())) {
            User user = new User(username, password);   //lascio lanciare l'eccezione perche non è il manager a doverla gestire
            if (dbUser.getRole()!=null)
                user.setRole(dbUser.getRole());
            if (dbUser.getResidence()!=null)
                user.setResidence(dbUser.getResidence());

            return user;
        }
        else throw new UserException("Wrong password!");
    }



    public User getUser(String username){
        MongoClientURI clientURI = new MongoClientURI(this.connectionStringURI);
        MongoClient mongoClient = new MongoClient(clientURI);

        MongoDatabase mongoDatabase = mongoClient.getDatabase(this.db_name);
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(COLLECTION_USER);

        Document document = mongoCollection.find(eq("username", username)).first();

        Password password = new Password(document.getString("password"));   //already encoded

        User user;
        Location location;
        try {
            user = new User(document.getString("username"),password);
            if (document.getString("role") != null){
                String role = document.getString("role");
                Role roleObj = Role.valueOf(role);
                user.setRole(roleObj);
            }
            try {

                Document locationDoc = (Document) document.get("location");
                if (locationDoc!=null){
                    String test = locationDoc.getString("street");
                    /**
                     *                  Country country, 	Region region, 			Province province,
                     *     				City city, 			String street,			String streetNumber*/

                    /*
                    some debug
                    Country country = Country.valueOf(locationDoc.getString("country"));
                    Region region = Region.valueOf(locationDoc.getString("region"));
                    Province province = Province.valueOf(locationDoc.getString("province"));
                    City city = City.valueOf(locationDoc.getString("city"));
                    String street = locationDoc.getString("street");
                    String streetNumber = locationDoc.getString("street_number");



                    System.out.println("Country: "+country);
                    System.out.println("region: "+region);
                    System.out.println("province: "+province);
                    System.out.println("city: "+city);
                    System.out.println("street: "+street);
                    System.out.println("streetNumber: "+streetNumber);
                    */

                    location = new Location(Country.valueOf(locationDoc.getString("country")),
                            Region.valueOf(locationDoc.getString("region")),
                            Province.valueOf(locationDoc.getString("province")),
                            City.valueOf(locationDoc.getString("city")),
                            locationDoc.getString("street"),
                            locationDoc.getString("street_number"),
                            locationDoc.getString("zip_code"));
                    user.setResidence(location);
                }

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            return user;
        } catch (UserException | NullPasswordException e) {
            //preferisco silenziare l'eccezione perchè non puo verificarsi, in quanto i controlli vengono
            //effettuati al momento di inserimento nel database
        }
        return null;
    }
    
    
    public void getBloodBagExpiring(Date d,BloodGroup b) throws ParseException{
    	
    	List<BloodBag> sacche = new ArrayList<BloodBag>();
    	
    	MongoClientURI clientURI = new MongoClientURI(this.connectionStringURI);
        MongoClient mongoClient = new MongoClient(clientURI);
        MongoDatabase mongoDatabase = mongoClient.getDatabase(this.db_name);
        MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION_BAG);
        
        
        for (Document current : collection.find()){
        	String serial =current.getString(ELEMENT_SERIAL);
        	String BloodG =current.getString(ELEMENT_GROUP);
        	String creationD =current.getString(ELEMENT_CREATIONDATE);
        	String expirationD =current.getString(ELEMENT_EXPIRATIONDATE);
        	String donatorCF =current.getString(ELEMENT_DONATORCF);
        	String node =current.getString(ELEMENT_NODE);
        	String BloodBagState =current.getString(ELEMENT_BLOODBAGSTATE);
        	String note =current.getString(ELEMENT_NOTE);
      
        	
       
        	SimpleDateFormat format= new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", new Locale("us"));
        	Date cd=format.parse(creationD);
        	Date ed=format.parse(expirationD);
        	
        	JSONObject obj = new JSONObject(node);
        	String cod = obj.getString( "codStr" );
        	String name = obj.getString( "nodeName" );
        	
        	JSONObject obj2 =obj.getJSONObject( "wareHouse" );
        	String street = obj2.getString( "street" );
        	String sNumber = obj2.getString( "streetNumber" );
        	String city = obj2.getString( "city" );
        	String province = obj2.getString( "province" );
        	String region=obj2.getString("region");
        	String country = obj2.getString( "country" );
        	String zip_code = obj2.getString("zip_code");
        	
        	
        	Node n1;
			try {
				n1 = new Node(cod, name,	new Location(Country.valueOf(country), Region.valueOf(region), Province.valueOf(province), City.valueOf(city) , street, sNumber,zip_code));
				BloodBag  bl=new  BloodBag(new Serial(serial),BloodGroup.valueOf(BloodG),cd,ed,donatorCF,n1,BloodBag.BloodBagState.valueOf(BloodBagState),note);
				if(( bl.getExpirationDate().before(d))&&(bl.getBloodGroup()==b)) {
        
                    sacche.add(bl);
                    System.out.println("****************");
                    bl.print();
                    System.out.println("****************");
    		    }
			} catch (IllegalArgumentException e) {
                e.printStackTrace();
            }


        }
        	
        /*public BloodBag( Serial s,BloodGroup b, Date creationD,Date expirationD,String donator,Node n,BloodBagState BagState,String not) throws ParseException {*/
        
    	mongoClient.close();


    }


    public void report() throws ParseException {
    	int count=0;
    	
    	
    	MongoClientURI clientURI = new MongoClientURI(this.connectionStringURI);
        MongoClient mongoClient = new MongoClient(clientURI);
        MongoDatabase mongoDatabase = mongoClient.getDatabase(this.db_name);
        MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION_BAG);
        
        
        
        for (Document current : collection.find()){
        	
        	String expirationD =current.getString(ELEMENT_EXPIRATIONDATE);
     
        	String bloodBagState =current.getString(ELEMENT_BLOODBAGSTATE);
        	Calendar cal = Calendar.getInstance();
    	    cal.set(Calendar.HOUR_OF_DAY, 0);
    	    cal.set(Calendar.MINUTE, 0);
    	    cal.set(Calendar.SECOND, 0);
    	    cal.set(Calendar.MILLISECOND, 0);
    		cal.add(Calendar.DAY_OF_MONTH, 1);
             Date d1=cal.getTime();
             
          
    		cal.add(Calendar.DAY_OF_MONTH, -9);

    	    Date d2=cal.getTime();
     	     // stato==dropped                   dataoogi-1 settimana    < expiration < dataoggi
       
        	SimpleDateFormat format= new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", new Locale("us"));
      
        	Date ed=format.parse(expirationD);
        	

        
       
        
       		if(  (bloodBagState.equals("Dropped"))   && (ed.after(d2)) && (ed.before(d1))) {
       		    
       		count++;
       			}
       	
        } 	System.out.println("****************");
     	   System.out.println(count);
     	   System.out.println("****************");
        	
        /*public BloodBag( Serial s,BloodGroup b, Date creationD,Date expirationD,String donator,Node n,BloodBagState BagState,String not) throws ParseException {*/
        
    	mongoClient.close();


    }
    
    public void addBloodBag(BloodBagInterface s) throws ParseException {
    	
    	MongoClientURI clientURI = new MongoClientURI(this.connectionStringURI);
        MongoClient mongoClient = new MongoClient(clientURI);
        MongoDatabase mongoDatabase = mongoClient.getDatabase(this.db_name);
        MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION_BAG);

        Document unaSacca = new Document(ELEMENT_SERIAL, s.getSerial().toString())
        		.append(ELEMENT_GROUP, s.getBloodGroup().toString()) 
                .append(ELEMENT_CREATIONDATE, s.getCreationDate().toString()) 
                .append(ELEMENT_EXPIRATIONDATE, s.getExpirationDate().toString())
                .append(ELEMENT_DONATORCF, s.getDonatorCF().toString()) .append(ELEMENT_NODE, s.getNode().toString())
                .append (ELEMENT_BLOODBAGSTATE, s.getBloodBagState().toString()) .append(ELEMENT_NOTE, s.getNote().toString());    
        collection.insertOne(unaSacca); /*inserimento*/
        mongoClient.close();
    }


    @Override
    public void writeLog(Logger logger) {

    }

    //implementazione non necessaria
    public void createDB(){}

    @Override
    public void dropDB() {

    }

    @Override
    public void writeLog(Date currentDate, User currentUser, String currentClass, String currentMethod, String currentResult) {

    }


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


                file.write(jsonObject.toString());
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

            file.write(jsonObject.toString());  //writing last element

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
