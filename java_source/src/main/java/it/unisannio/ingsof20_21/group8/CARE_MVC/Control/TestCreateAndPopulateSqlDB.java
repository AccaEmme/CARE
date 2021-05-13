package it.unisannio.ingsof20_21.group8.CARE_MVC.Control;

import java.util.Random;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Blood;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;


public class TestCreateAndPopulateSqlDB {

	public static void main(String[] args) {
		DataManager dm = new MySqlDataManager();
		dm.createDB();
		
		final int NUMSACCHE=15;
	    Blood gruppi[] = Blood.values();
		Random generatore = new Random();
		BloodBag s;
		for (int i=0; i<NUMSACCHE; i++) {
			s = new BloodBag(gruppi[generatore.nextInt(gruppi.length)]);
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
