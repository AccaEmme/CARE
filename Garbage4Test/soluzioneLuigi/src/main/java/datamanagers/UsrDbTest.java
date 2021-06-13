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

      adminInterface dm = new MongoDataManager();
		dm.createDB();
		
		User u0=new User("tarzan","12345");
		User u1=new User("luigi","12345");
	/*	Node n1 = new Node("206","Rummo",		new Location(Country.Italy, Region.Campania, Province.Benevento, City.Benevento, "via Roma", "44C"));
	
		//BloodBag bb = new BloodBag(BloodGroup.Bpos, CF1);
		BloodBagInterface bbi = new BloodBag(BloodGroup.Bpos, CF1, n1); */
	 // dm.updateUser(u);
	
		u0.setRole(Role.Officer);
	dm.addUser(u0);
dm.addUser(u1);

	}
}

