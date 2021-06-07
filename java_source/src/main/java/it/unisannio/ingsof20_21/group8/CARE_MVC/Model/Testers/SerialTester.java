	package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Testers;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodGroup;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Serial;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Constants;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.InitSettings;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.Scanner;

public class SerialTester {
	public static String path = Constants.SERIAL_SETTINGS_RELATIVEPATH;
	
	public static void main(String[] args) throws InvalidPropertiesFormatException, FileNotFoundException, IOException {
	
		// Tester1
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Gruppo sanguigno ["+BloodGroup.delimitedValues(", ")+"]:");
		String gsang = sc.nextLine();
		sc.close();
		
		Serial s = new Serial( BloodGroup.valueOf(gsang) );
		
		far lanciare una eccezione a validateSerial altrimenti in caso di errore ok che non genera il Seriale ma non dice nulla lasciando pensare di averlo creato.
		Serial s = new Serial( "ZeroA" );
		System.out.println( s.toString() +"\n");
		System.out.println( s.toString() +" validateSerial e': "+ Serial.validateSerial(s.toString()) );
		
		// Tester2
		String path2 = path+"serial_settings.xml";
		Properties loadProps = new Properties();
		InitSettings.initXML(22,path2);
		loadProps.loadFromXML(new FileInputStream(path2));
		  
		System.out.println(Integer.valueOf(loadProps.getProperty("counter")));
		System.out.println(Integer.valueOf(loadProps.getProperty("lastdate")));
		
		Serial s2 = new Serial( BloodGroup.valueOf("Apos"));
		
		Properties loadProps2 = new Properties();
		loadProps2.loadFromXML(new FileInputStream(path2));
		  
		System.out.println(Integer.valueOf(loadProps2.getProperty("counter")));
		System.out.println(Integer.valueOf(loadProps2.getProperty("lastdate")));

		
		
		
		//Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
		//    public void run() {
		        //Serial.updateSerial();
		//    }
		//}));
	}

}
