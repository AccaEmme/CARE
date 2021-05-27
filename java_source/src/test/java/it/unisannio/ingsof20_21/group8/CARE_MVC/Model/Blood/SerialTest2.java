package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Constants;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.InitSettings;

/*
 * Si preferisce generare due SerialTest in quanto entrambi hanno un @BeforeClass che porta in due stati differenti.
 *
 */

public class SerialTest2 {
	public static String path = Constants.SERIAL_SETTINGS_RELATIVEPATH;
	public static String filename = Constants.SERIAL_SETTINGS_FILENAME; 
	   private static SimpleDateFormat ft = new SimpleDateFormat(Constants.DATE_FORMAT);
	private static Date dNow = new Date();
	private static String currentDate_aaaaMMdd = ft.format(dNow);
	@BeforeClass
	public static void createFile() throws IOException {
		
		String path1 = path+"serial_settings.xml";
		File from = new File(path1);
		String path2 = path+"serial_settings_temp.xml";
		File to = new File(path2);
        Files.copy(from.toPath(), to.toPath(), StandardCopyOption.REPLACE_EXISTING);
		InitSettings.initXML(100,path1);
		BloodGroup g= BloodGroup.valueOf("ZEROneg") ;
		Serial s=new Serial(g);
		  Properties loadProps = new Properties();
		/*uso direttamente serial_setting_temp*/
		
	}
		@Test
	public void testCounter() throws InvalidPropertiesFormatException, FileNotFoundException, IOException {
		String path3 = path+"serial_settings.xml";


		  Properties loadProps = new Properties();
		  loadProps.loadFromXML(new FileInputStream(path3));
	      int  lastdate =Integer.valueOf(loadProps.getProperty("lastdate"));
	    	assertTrue(lastdate==Integer.parseInt(currentDate_aaaaMMdd));
		/*contatore deve partire da uno*/
	}

	
	@Test
	public void testNewDate() throws InvalidPropertiesFormatException, FileNotFoundException, IOException {	
		String path3 = path+"serial_settings.xml";
	 	
	    

		  Properties loadProps = new Properties();
		  loadProps.loadFromXML(new FileInputStream(path3));
	      int  counter = 		Integer.valueOf(loadProps.getProperty("counter"));
		assertTrue(counter==1);
		/*testo se ha la data di oggo*/
	}

	
	@AfterClass
	public static void regeneratonFile() throws IOException {
	
		String path1 = path+"serial_settings_temp.xml";
		File from = new File(path1);
		String path2 = path+"serial_settings.xml";
		File to = new File(path2);
        Files.copy(from.toPath(), to.toPath(), StandardCopyOption.REPLACE_EXISTING);
	}
	
}
