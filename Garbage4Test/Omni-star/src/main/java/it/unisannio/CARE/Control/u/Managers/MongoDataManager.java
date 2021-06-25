package it.unisannio.CARE.Control.u.Managers;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import it.unisannio.CARE.Exceptions.NullPasswordException;
import it.unisannio.CARE.Exceptions.NullUserException;
import it.unisannio.CARE.Exceptions.UserException;
import it.unisannio.CARE.Model.Classes.BloodBag;
import it.unisannio.CARE.Model.Classes.Request;
import it.unisannio.CARE.Model.Classes.User;
import it.unisannio.CARE.Model.Classes.Interfaces.BloodBagInterface;
import it.unisannio.CARE.Model.Classes.Interfaces.StoreManagerInterface;
import it.unisannio.CARE.Model.Node.Node;
import it.unisannio.CARE.Model.Util.BloodGroup;
import it.unisannio.CARE.Model.Util.Constants;
import it.unisannio.CARE.Model.Util.Location;
import it.unisannio.CARE.Model.Util.Logger;
import it.unisannio.CARE.Model.Util.Password;
import it.unisannio.CARE.Model.Util.Role;
import it.unisannio.CARE.Model.Util.Serial;
import it.unisannio.CARE.Model.Util.XMLHelper;
import it.unisannio.CARE.Model.Util.Location.City;
import it.unisannio.CARE.Model.Util.Location.Country;
import it.unisannio.CARE.Model.Util.Location.Province;
import it.unisannio.CARE.Model.Util.Location.Region;

import org.bson.Document;


import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.json.JSONObject;
/*import org.json.simple.JSONObject;*/
import static com.mongodb.client.model.Filters.eq;



public class MongoDataManager implements AdminInterface, WareHouseWorkerInterface,StoreManagerInterface,SecretaryInterface {
    private String connectionStringURI = "";
    private String db_name = "";
    private String collection_name = "";

    private static final String TAG_DB 					= "db_mongo_name";
    private static final String TAG_HOST 				= "db_mongo_host";
    private static final String TAG_PORT 				= "db_mongo_port";

//#######################################################
    private static final String COLLECTION_USER			= "users";
    
    private static final String ELEMENT_USERNAME 		= "username";
    private static final String ELEMENT_PASSWORD 		= "password";
    private static final String ELEMENT_ROLE 			= "role"; 
    
  //#######################################################
    private static final String COLLECTION_BAG			= "blood-bags";
    
    private static final String ELEMENT_GROUP 			= "BloodGroup";
    private static final String ELEMENT_SERIAL 			= "serial";
    private static final String ELEMENT_CREATIONDATE 	= "creationDate";
    private static final String ELEMENT_EXPIRATIONDATE 	= "expirationDate";
    private static final String ELEMENT_DONATORCF 		= "donatorCF";
    private static final String ELEMENT_NODE 			= "node";
    private static final String ELEMENT_BLOODBAGSTATE 	= "bloodBagState";
    private static final String ELEMENT_NOTE 			= "note";
  
  //#######################################################
    private static final String COLLECTION_REQUEST		= "request";
    
    private static final String ELEMENT_SERIALBAG 		= "serial_Bag";
    private static final String ELEMENT_REQUESTEDDATE 		= "requested_Date";
    private static final String ELEMENT_STATE			= "state"; 
    private static final String ELEMENT_USERREQUESTING	= "user-requesting";
    
  //#######################################################
    private static final String COLLECTION_NODE			= "node";
    
    private static final String ELEMENT_CODSTR 			= "cod_str";
    private static final String ELEMENT_NODENAME 		= "Node_Name";
    private static final String ELEMENT_WAREHOUSE		= "Warehouse"; 
  
  //#######################################################
    private static final String COLLECTION_LOCATION		= "location";
    
    private static final String ELEMENT_COUNTRY 		= "country";
    private static final String ELEMENT_REGION 			= "region";
    private static final String ELEMENT_PROVINCE		= "province"; 
    private static final String ELEMENT_CITY			= "city"; 
    private static final String ELEMENT_STREET			= "street"; 
    private static final String ELEMENT_STREEETNUMBER	= "streetNumber"; 
    private static final String ELEMENT_ZIPCODE			= "ZIPCodee"; 
    

/*
    private static String SERIALE = "SERIAL";
    private static String GRUPPO = "GROUP";
    private static String EXPIRATION = "EXPIRATION_DAY";
    private static String CREATION = "CREATION_DAY";
    private static String ORIGIN = "ORIGIN";*/

    
    /*il costruttore deve solo andarsi a prendere i parametri generali*/
    // il costruttore decide quale collezione utilizzare senno devo fare un set collection name
      public void dropDB()   {
      	MongoClientURI clientURI 		= new MongoClientURI(this.connectionStringURI);
        MongoClient mongoClient 		= new MongoClient(clientURI);
        MongoDatabase mongoDatabase 	= mongoClient.getDatabase(this.db_name);
        mongoDatabase.drop();

      }
      
      public void dropUserCollection() {
      	MongoClientURI clientURI 		= new MongoClientURI(this.connectionStringURI);
        MongoClient mongoClient 		= new MongoClient(clientURI);
        MongoDatabase mongoDatabase 	= mongoClient.getDatabase(this.db_name);
        MongoCollection mongoCollection = mongoDatabase.getCollection(COLLECTION_USER);
        mongoCollection.drop();
      }
      
      
      
    public MongoDataManager(){
        connectionStringURI 			= createURI();
        String[] db_collection_names 	= getDbProperties();
        this.db_name 					= db_collection_names[0];
    }
    
    private String createURI(){
        Properties properties = XMLHelper.getProps(Constants.MONGODB_CREDENTIALS);
        String username, password, db_host;
        
        username = properties.getProperty("username");      //reading username from xml (private, local)
        password = properties.getProperty("password");      //reading password from xml (private, local)
        db_host  = properties.getProperty("db_host");
        
        assert username!=null;
        assert password!=null;
        assert db_host !=null;
        
        return "mongodb+srv://"+username+":"+password+"@"+db_host;
    }

    private String[] getDbProperties(){
        /**
         * reading database name and collection name from xml
         * */
        String[] db_collection_names = new String[2];
        Properties properties = XMLHelper.getProps(Constants.MONGODBL_SETTINGS_PATH);

        /**
         * reading from an array, so i can return multiple values insted of only one
         * */
        db_collection_names[0] = properties.getProperty("db_name");
        db_collection_names[1] = properties.getProperty("collection_name");

        return db_collection_names;
    }

    public void addUser(User user){
        MongoClientURI clientURI 		= new MongoClientURI(this.connectionStringURI);
        MongoClient mongoClient 		= new MongoClient(clientURI);
        MongoDatabase mongoDatabase 	= mongoClient.getDatabase(this.db_name);
        MongoCollection mongoCollection = mongoDatabase.getCollection(COLLECTION_USER);

        Document document = user.getDocument();
        mongoCollection.insertOne(document);
        
        System.out.println("Added element: "+document);
        mongoClient.close();
    }

    @Override
    public void deleteUser(User u) throws Exception {
    	MongoClientURI clientURI 		= new MongoClientURI(this.connectionStringURI);
        MongoClient mongoClient 		= new MongoClient(clientURI);
        MongoDatabase mongoDatabase 	= mongoClient.getDatabase(this.db_name);
        MongoCollection mongoCollection = mongoDatabase.getCollection(COLLECTION_USER);
        
        if((mongoCollection.deleteOne((eq(ELEMENT_USERNAME,u.getUsername()))).getDeletedCount())==0) 
            throw new Exception("MongoDataManager - deleteUser: User "+u.getUsername()+" not found");

        mongoClient.close();
    }

    @Override
    public void editUser(User u) throws ParseException {
        MongoClientURI clientURI = new MongoClientURI(this.connectionStringURI);
        MongoClient mongoClient = new MongoClient(clientURI);

        MongoDatabase mongoDatabase = mongoClient.getDatabase(this.db_name);
		MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION_USER);

		Document user = new Document(ELEMENT_USERNAME, u.getUsername()).append(ELEMENT_PASSWORD, u.getPassword()); 
		
		if((collection.replaceOne((eq(ELEMENT_USERNAME,u.getUsername())), user).getMatchedCount())==0) {
			System.out.println("user not found");
		} else {
			System.out.println("user uptated");
		}

		mongoClient.close();
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
    	
    	MongoClientURI clientURI 				= new MongoClientURI(this.connectionStringURI);
        MongoClient mongoClient 				= new MongoClient(clientURI);
        MongoDatabase mongoDatabase 			= mongoClient.getDatabase(this.db_name);
        MongoCollection<Document> collection 	= mongoDatabase.getCollection(COLLECTION_BAG);
        
        for (Document current : collection.find()){
        	String serial 			= current.getString(ELEMENT_SERIAL);
        	String BloodG 			= current.getString(ELEMENT_GROUP);
        	String creationD 		= current.getString(ELEMENT_CREATIONDATE);
        	String expirationD 		= current.getString(ELEMENT_EXPIRATIONDATE);
        	String donatorCF 		= current.getString(ELEMENT_DONATORCF);
        	String node 			= current.getString(ELEMENT_NODE);
        	String BloodBagState 	= current.getString(ELEMENT_BLOODBAGSTATE);
        	String note 			= current.getString(ELEMENT_NOTE);
      
        	SimpleDateFormat format= new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", new Locale("us"));
        	Date cd=format.parse(creationD);
        	Date ed=format.parse(expirationD);
        	
        	JSONObject obj 	= new JSONObject(node);
        	String cod 		= obj.getString( "codStr" );
        	String name 	= obj.getString( "nodeName" );
        	
        	JSONObject obj2 = obj.getJSONObject( "wareHouse" );
        	String street 	= obj2.getString( "street" );
        	String sNumber 	= obj2.getString( "streetNumber" );
        	String city 	= obj2.getString( "city" );
        	String province = obj2.getString( "province" );
        	String region 	= obj2.getString("region");
        	String country 	= obj2.getString( "country" );
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

       		if(  (bloodBagState.equals("Dropped"))   && (ed.after(d2)) && (ed.before(d1))) 
       			count++;
        }
        System.out.println("****************");
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
    public void addNode(Node n) {
    	MongoClientURI clientURI = new MongoClientURI(this.connectionStringURI);
        MongoClient mongoClient = new MongoClient(clientURI);
        MongoDatabase mongoDatabase = mongoClient.getDatabase(this.db_name);
        MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION_NODE);
        
        Document node = new Document(ELEMENT_CODSTR,n.getCodStr()).append(ELEMENT_NODENAME, n.getNodeName())
        		.append(ELEMENT_WAREHOUSE, n.getWarehouse());
        collection.insertOne(node);
        mongoClient.close();
    }
    
    
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


 
 @Override
	public void acceptRequest(Request r) {
	 MongoClientURI clientURI = new MongoClientURI(this.connectionStringURI);
     MongoClient mongoClient = new MongoClient(clientURI);
     MongoDatabase mongoDatabase = mongoClient.getDatabase(this.db_name);
     MongoCollection<Document> collection = mongoDatabase.getCollection("Request_List");
     Document request = new Document(ELEMENT_USERREQUESTING,r.getUserRequesting()).append(ELEMENT_SERIALBAG,r.getSerial())
    		 .append(ELEMENT_REQUESTEDDATE ,r.getRequestedDate()).append(ELEMENT_STATE, "accepted");
    		
	
			
     for (Document current : collection.find()){
    	 if((current.get(ELEMENT_USERREQUESTING)==r.getSerial())&&(current.get(ELEMENT_USERREQUESTING)==r.getUserRequesting())) {
    		 current.replace(ELEMENT_STATE, "accepted");
    		 
    		 collection.replaceOne(current,request);
    	 }
    	 
     }
     
	}

	public void addBloodBagRequest(Request r) {
		MongoClientURI clientURI = new MongoClientURI(this.connectionStringURI);
        MongoClient mongoClient = new MongoClient(clientURI);
        MongoDatabase mongoDatabase = mongoClient.getDatabase(this.db_name);
        MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION_LOCATION);
        
        Document request = new Document(ELEMENT_SERIALBAG,r.getSerial()).append(ELEMENT_REQUESTEDDATE, r.getRequestedDate())
        		.append(ELEMENT_STATE, r.getState()).append(ELEMENT_USERREQUESTING, r.getUserRequesting());
        collection.insertOne(request);
        mongoClient.close();
	}    
	
	
	@Override
    public void addLocation(Location l) {
    	MongoClientURI clientURI = new MongoClientURI(this.connectionStringURI);
        MongoClient mongoClient = new MongoClient(clientURI);
        MongoDatabase mongoDatabase = mongoClient.getDatabase(this.db_name);
        MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION_LOCATION);
        
        Document location = new Document(ELEMENT_COUNTRY,l.getCountry()).append(ELEMENT_REGION, l.getRegion())
        		.append(ELEMENT_PROVINCE, l.getProvince()).append(ELEMENT_CITY,l.getCity()).append(ELEMENT_STREET, l.getStreet())
        		.append(ELEMENT_STATE, l.getStreetNumber()).append(ELEMENT_ZIPCODE, l.getZipCode());
        collection.insertOne(location);
        mongoClient.close();

    }

  @Override
    public void writeLog(Logger logger) {

    }

    //implementazione non necessaria
    public void createDB(){}



    @Override
    public void writeLog(Date currentDate, User currentUser, String currentClass, String currentMethod, String currentResult) {

    }


    @Override
    public void addRoles() {

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


	@Override
	public void addStates() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addBloodBag(BloodBag s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BloodBag> getBloodBag(BloodGroup bloodGroup) {
		// TODO Auto-generated method stub
		return null;
	}



    
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


  

 

   