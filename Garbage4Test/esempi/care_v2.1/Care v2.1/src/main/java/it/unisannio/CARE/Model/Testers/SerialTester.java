package it.unisannio.CARE.Model.Testers;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.Scanner;

import it.unisannio.CARE.Model.BloodBag.BloodGroup;
import it.unisannio.CARE.Model.BloodBag.Serial;
import it.unisannio.CARE.Model.Util.Constants;

public class SerialTester {
	public static String path = Constants.SERIAL_SETTINGS_RELATIVEPATH;
	
	public static void main(String[] args) throws InvalidPropertiesFormatException, FileNotFoundException, IOException {
	  
		// Tester1
		Scanner sc = new Scanner(System.in);
		System.out.println("Definisci il Gruppo sanguigno ["+BloodGroup.delimitedValues(", ")+"]:");
		String gsang = sc.nextLine();		
		
		Serial s = new Serial( BloodGroup.valueOf(gsang) );
		
		//Serial s = new Serial( "ZeroA" ); // Serve a verificare che lanci una eccezione
		System.out.println( s.toString() +"\n");
		System.out.println( s.toString() +" validateSerial e': "+ Serial.validateSerial(s.toString()) );
		
		// Tester2
		String path2 = path+"serial_settings_test.xml";
		Properties loadProps = new Properties();
		//InitSettings.initXML(22,path2);
		loadProps.loadFromXML(new FileInputStream(path2));
		  
		System.out.println("loadProps1 counter: " + Integer.valueOf(loadProps.getProperty("counter")));
		System.out.println("loadProps1 lastdate: " + Integer.valueOf(loadProps.getProperty("lastdate")));
		
		Serial s2 = new Serial( BloodGroup.valueOf("Apos"));
		
		Properties loadProps2 = new Properties();
		loadProps2.loadFromXML(new FileInputStream(path2));
		  
		System.out.println("loadProps2 counter: " + Integer.valueOf(loadProps2.getProperty("counter")));
		System.out.println("loadProps2 lastdate: " + Integer.valueOf(loadProps2.getProperty("lastdate")));

		
		
		
		//Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
		//    public void run() {
		        //Serial.updateSerial();
		//    }
		//}));
		sc.close();
	}

}
