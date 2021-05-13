package it.unisannio.ingsof20_21.group8.CARE_MVC.Control;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.BloodBag;

import java.util.List;


public interface DataManager {
	void createDB();
	void dropDB();
	void addBloodBag(BloodBag bloodbag);
	List<BloodBag> getBloodBag(Blood blood);
}
