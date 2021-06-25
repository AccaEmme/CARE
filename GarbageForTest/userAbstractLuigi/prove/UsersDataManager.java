package prove;

import java.text.ParseException;

/*AdministratorInterface*/

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;

public interface UsersDataManager {
	
	void createDB();
	
	void dropDB();
	
	void addUser(User s) throws ParseException ;
	
	void deleteUser(User s) throws ParseException ;
	
	void uptadeUser(User s) throws ParseException;
	/* aggiornare qualcosa passo user*/
	
	
}
