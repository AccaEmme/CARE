package prove;

import java.text.ParseException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.MdbUserDataManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.UsersDataManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;


public class UserDbTest {
public static void main(String[] args) throws ParseException {
		
		Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
		mongoLogger.setLevel(Level.SEVERE);
		
		UsersDataManager dm = new UserManager();
		dm.createDB();
		
		User u=new User("giovanni","12345");
		User u1=new User("marco","12345");
	 // dm.updateUser(u);
		
	/*dm.addUser(u);*/
dm.deleteUser(u1);

	}
}
