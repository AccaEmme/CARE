	package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Testers;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodGroup;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Serial;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Constants;

import java.util.Scanner;

public class SerialTester {

	public static void main(String[] args) {
		System.out.println("->"+Constants.SERIAL_SETTINGS_FILENAME_RELATIVEPATH);
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Gruppo sanguigno [Apos, Aneg, Bpos, Bneg, ABpos, ABneg, ZEROpos, ZEROneg]:");
		String gsang = sc.nextLine();
		sc.close();
		
		Serial s = new Serial( BloodGroup.valueOf(gsang) );
		System.out.println( s.toString() +"\n");
		
		//Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
		//    public void run() {
		        //Serial.updateSerial();
		//    }
		//}));
	}

}
