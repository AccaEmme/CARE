package it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.Interface;

import java.sql.Date;
import java.util.List;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Request.Request;

public interface RequestDataManagerInterface {

	void addRequest(Request request);
	
	void setState(Request request);
	
	List<BloodBag> getBB(Date date);


}
