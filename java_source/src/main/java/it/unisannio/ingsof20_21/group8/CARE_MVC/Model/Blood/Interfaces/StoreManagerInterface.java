package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Interfaces;

import java.text.ParseException;
import java.util.Date;


import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodGroup;



public interface StoreManagerInterface {
	// implemented by BloodBagManager
	/*public void addBloodBag(BloodBag b);*/
/*	public void useBloodBag(BloodBag b);
	public void transferBloodBag(BloodBag b, Node n);*/
	void getBloodBagExpiring(Date d,BloodGroup b)throws ParseException;
}
