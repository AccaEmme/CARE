package it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;

public interface SecretaryInterface extends AdminInterface{

	
	public void addBloodBagRequest(BloodBag bloodBag);
}
