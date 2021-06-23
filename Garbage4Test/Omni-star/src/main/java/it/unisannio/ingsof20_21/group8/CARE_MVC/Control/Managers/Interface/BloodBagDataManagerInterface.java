package it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.Interface;

import java.util.List;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodGroup;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Request.Request;

public interface BloodBagDataManagerInterface {

	void addBloodBag(BloodBag bloodBag);
	
	List<BloodBag> getBagByGroup(BloodGroup bloodGroup);
	
	void addRequest(Request request);
	
	void setState(Request request);
	
}
