package it.unisannio.CARE.Control.Interfaces;

import java.util.List;

import org.bson.Document;

import it.unisannio.CARE.Model.Classes.BloodBag;
import it.unisannio.CARE.Model.Util.BloodGroup;

public interface BloodBagDataManagerInterface {

	void addBloodBag(BloodBag bloodBag);
	
	void useBloodBag(BloodBag bloodBag);

	void dropBloodBag(BloodBag bloodBag);

	void transferBloodBag(BloodBag bloodBag);
	
	List<Document> getBagsByGroup(BloodGroup bloodGroup);

}
