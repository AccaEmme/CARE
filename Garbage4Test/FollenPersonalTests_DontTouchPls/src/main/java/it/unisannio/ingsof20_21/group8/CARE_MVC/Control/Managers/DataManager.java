package it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.Exceptions.UserException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Exceptions.NullPasswordException;

import java.util.Date;
import java.util.List;


public interface DataManager {
	void createDB();
	void dropDB();

	User validateLogin(String username, String password) throws UserException, NullPasswordException;

	void writeLog(Date now, User currentUser, String fromClass, String method, String result);
}
