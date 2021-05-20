package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Testers;

import java.text.ParseException;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.DataManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.MySqlDataManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBagManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodGroup;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;

public class BloodBagManagerTester {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		User u 			= new User();
		DataManager db 	= null;
		MySqlDataManager db2 = new MySqlDataManager();
		
		String CF1="BNCMRA86A41A509Y", CF2="RSSMRA80A01A509I";
		
		BloodBagManager bbm = new BloodBagManager(u, db2, BloodGroup.ABneg, CF1 );
		bbm.getBlood();
		
	}

}
