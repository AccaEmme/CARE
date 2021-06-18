package it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.Exceptions.UserException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Exceptions.NullPasswordException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Logger;

import java.util.Date;
import java.util.List;


public interface DataManager {
	/*void createDB();
	void dropDB();

	
	
	void addBloodBag(BloodBag bloodbag);
	List<BloodBag> getBloodBag(BloodBag blood);
	
	void updateExpirationDate(BloodBag b, Date newExpirationDate);
	void writeLog(Date currentDate, User currentUser, String currentClass, String currentMethod, String currentResult);
	
	public void restoreDump(String filename);
	public boolean createDump();
	*/
	void writeLog(Logger logger);
	void createDB();
	void dropDB();

	User validateLogin(String username, String password) throws UserException, NullPasswordException;

	void writeLog(Date now, User currentUser, String fromClass, String method, String result);

    void addBloodBag(BloodBag bag);

	void addStates();

	void addRoles();

	void addLocation(Location location);
}
