package it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.Interface;

import java.util.List;

import org.bson.Document;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodGroup;

public interface BloodBagDataManagerInterface {

	void addBloodBag(BloodBag bloodBag);
	
	void useBloodBag(BloodBag bloodBag);

	void dropBloodBag(BloodBag bloodBag);

	void transferBloodBag(BloodBag bloodBag);
	
	List<Document> getBagsByGroup(BloodGroup bloodGroup);

}
