package datamanagers;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import users.Role;
import users.User;



public class UsrDbTest {
public static void main(String[] args) throws ParseException {
		
		Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
		mongoLogger.setLevel(Level.SEVERE);

       DataManager  dm = new MongoDataManager();
		dm.createDB();
		
		User u0=new User("pippo","12345");
		User u1=new User("paperina","12345");
	 // dm.updateUser(u);
	
		u0.setRole(Role.Officer);
	dm.addUser(u0);
dm.addUser(u1);

	}
}

