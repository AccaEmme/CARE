package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Testers;

import java.text.ParseException;
import java.util.Random;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.DataManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.MySqlDataManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Blood;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;


public class TestCreateAndPopulateSqlDB {

	public static void main(String[] args) {
		DataManager dm = new MySqlDataManager();
		dm.createDB();
		
		final int NUMSACCHE=15;
	    Blood gruppi[] = Blood.values();
		Random generatore = new Random();
		BloodBag s = null;
		for (int i=0; i<NUMSACCHE; i++) {
			try {
				s = new BloodBag(gruppi[generatore.nextInt(gruppi.length)]);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dm.addBloodBag(s);
			System.out.println("Added BloodBag: "+s);			
		}		
		
		/*
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
		    public void run() {
		        Serial.updateSettings();
		    }
		}));
		*/

	}

}
