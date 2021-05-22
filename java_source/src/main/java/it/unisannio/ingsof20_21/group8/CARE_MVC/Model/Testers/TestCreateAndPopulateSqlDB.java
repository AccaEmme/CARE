package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Testers;

import java.text.ParseException;
import java.util.Random;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.DataManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.MySqlDataManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodGroup;

public class TestCreateAndPopulateSqlDB {

	public static void main(String[] args) {
		DataManager dm = new MySqlDataManager();
		dm.createDB();
		
		final int NUMSACCHE=15;
	    BloodGroup gruppi[] = BloodGroup.values();
		Random generatore = new Random();
		
		BloodBag s = null;
		for (int i=0; i<NUMSACCHE; i++) {
			try {
				s = new BloodBag(
									gruppi[generatore.nextInt(gruppi.length)],
									"CF"+i+""
								);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dm.addBloodBag(s);
			System.out.println("Added BloodBag: "+s);			
		}
		
	}
}
		/*
		
				
		

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
		    public void run() {
		        Serial.updateSettings();
		    }
		}));


	}
}
*/
