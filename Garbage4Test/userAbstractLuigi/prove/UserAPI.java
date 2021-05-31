package prove;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.MdbUserDataManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;



@Consumes("application/json")
@Produces("application/json")
@Path("rest")
public class UserAPI {
	
	/*servizi che vado a reralizzare*/

	@POST
	@Path("user/add")	
	public void addUser(UserBean u) throws ParseException{		

		Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
		mongoLogger.setLevel(Level.SEVERE);
		
		User us = new User((u.getUsername()), u.getPassword());
		(new UserManager()).addUser(us);
			
	}
	
	@POST
	@Path("user/update")	
	public void updateUser(UserBean u) throws ParseException{		

		Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
		mongoLogger.setLevel(Level.SEVERE);
		
		User us = new User((u.getUsername()), u.getPassword());
		(new UserManager()).updateUser(us);
			
	}
	
}
