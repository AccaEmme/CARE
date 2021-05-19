package it.unisannio.ingsof20_21.group8.CARE_MVC.Control;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Blood;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;

import java.util.Date;
import java.util.List;


public interface DataManager {
	void createDB();
	void dropDB();
	void addBloodBag(BloodBag bloodbag);
	List<BloodBag> getBloodBag(Blood blood);

	/**
	 * private final Date now = new Date();
	 *     User currentUser;
	 *     String fromClass;
	 *     String method;
	 *     String result;*/
	void writeLog(Date currentDate, User currentUser, String currentClass, String currentMethod, String currentResult);
}
