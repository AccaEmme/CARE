package it.unisannio.CARE.spring;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import it.unisannio.CARE.model.bloodBag.BloodBag;
import it.unisannio.CARE.model.bloodBag.BloodGroup;
import it.unisannio.CARE.model.bloodBag.Serial;
import it.unisannio.CARE.model.exceptions.IllegalSerialException;
import it.unisannio.CARE.model.util.Constants;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/*
 *
 * JUnit test for Serial.
 * Si preferisce generare due SerialTest in quanto entrambi hanno un @BeforeClass che porta in due stati differenti.
 *
 */

public class SerialFileTest {
	public static String path = Constants.SERIAL_SETTINGS_RELATIVEPATH;
	public static String filename = Constants.SERIAL_SETTINGS_FILENAME; 
	private static SimpleDateFormat ft = new SimpleDateFormat(Constants.DATE_FORMAT);
	private static Date dNow = new Date();
	private static String currentDate_aaaaMMdd = ft.format(dNow);
	
	
	/**
	* Before class for the correct creation of the configuration file
	* @throws IOException
	* @result a copy of the configuration file is created, plus the first serial_settings file is successfully created
	*/
	@BeforeClass
	public static void createFile() throws IOException {
		
		String path1 = path+"serial_settings.xml";
		File from = new File(path1);
		String path2 = path+"serial_settings_temp.xml";
		File to = new File(path2);
        Files.copy(from.toPath(), to.toPath(), StandardCopyOption.REPLACE_EXISTING);
		//InitSettings.initXML(100,path1);
		BloodGroup g= BloodGroup.valueOf("ZEROneg") ;
		Serial s=new Serial(g);
		Properties loadProps = new Properties();
		/*uso direttamente serial_setting_temp*/
		
	}
 
	/**
	* Test to verify the correct functioning of the NewDate
	* @throws InvalidPropertiesFormatException
	* @throws InvalidPropertiesFormatException
	* @throws IOException
	* @result The result is true if the counter has a value of 3
	*/
	@Test
	public void testNewDate() throws InvalidPropertiesFormatException, InvalidPropertiesFormatException, IOException {	
		String path3 = path+"serial_settings.xml";
		Properties loadProps = new Properties();
		loadProps.loadFromXML(new FileInputStream(path3));
	    int  counter = Integer.valueOf(loadProps.getProperty("counter"));
	    assertTrue(counter==3); 
		/*testo se ha la data di oggo*/
	} 
	
	/**
	* Test to verify the correct functioning of the Counter
	* @throws InvalidPropertiesFormatException
	* @throws InvalidPropertiesFormatException
	* @throws IOException
	* @result The result is true if the counter is right 
	*/
	@Test
	public void testCounter() throws InvalidPropertiesFormatException, FileNotFoundException, IOException {
		String path3 = path+"serial_settings.xml";
		Properties loadProps = new Properties();
		loadProps.loadFromXML(new FileInputStream(path3));
	    int  lastdate =Integer.valueOf(loadProps.getProperty("lastdate"));
	    assertTrue(lastdate==Integer.parseInt(currentDate_aaaaMMdd));
		/*contatore deve partire da uno*/
	}

	
	/**
	* Creation of the constructor of the Serial class
	* @result The serial is created correctly by not developing any exceptions
	*/
	@Test
	public void ValidityTest_Constructor1_notNullObject() {
		Serial s = new Serial(BloodGroup.ABneg);
		assertNotNull(s);
	}
	
	/**
	* Creation of the constructor of the Serial class
	* @result If the serial string is correct, the new serial is inserted
	*/
	@Test
	public void ValidityTest_Serial_notNullObject() {
		Serial s = new Serial("IT-NA206000-Apos-20210416-0001");
		assertNotNull(s);
	}
	
	/**
	* Invalid creation of the Serial class constructor
	* @result The serial string is invalid, so it returns an exception
	*/
	@Test (expected = IllegalSerialException.class)
	public void InvalidityTest_Serial_notNullObject() {
		Serial s = new Serial("IT-NA206000-Apos-20210416-000");
		assertNotNull(s);
	}
	
	/**
	* Test of the Get Serial method
	* @result returns the validation of the getSerial method
	*/
	@Test
	public void ValidityTest_getSerial_notNullObject() {
		Serial s = new Serial("IT-NA206000-Apos-20210416-0001");
		assertNotNull(s.getSerial());
	}
	
	/**
	* Test of the TOSTRING method
	* @result returns all information of the Serial class
	*/
	@Test
	public void ValidityTest_ToString_notNullObject() {
		Serial s = new Serial("IT-NA206000-Apos-20210416-0001");
		s.toString();
	}
	
	/**
	* Verification of the operation of the equal method
	* @result returns true because I compare two Serial objects
	*/
	@Test
	public void ValidityTest_Equals_notNullObject() {
		Serial s = new Serial("IT-NA206000-Apos-20210416-0001");
		Serial s2 = new Serial("IT-NA206000-Apos-20210416-0002");
		assertNotNull(s.equals(s2));
	}
	
	/**
	* Verification of the operation of the equal method
	* @throws ParseException
	* @result returns false because I don't compare two Serial objects
	*/
	@Test
	public void InvalidityTest_Equals_notNullObject() throws ParseException {
		Serial s = new Serial("IT-NA206000-Apos-20210416-0001");
		BloodBag bg = new BloodBag(BloodGroup.ABneg, "PLVDNT96P21A783A");
		assertFalse(s.equals(bg));
	}

	/**
	* After class for the correct restoration of the configuration file
	* @throws IOException
	* @result The result is the successful restore of the serial_settings configuration file
	*/
	@AfterClass
	public static void regeneratonFile() throws IOException {
	
		String path1 = path+"serial_settings_temp.xml";
		File from = new File(path1);
		String path2 = path+"serial_settings.xml";
		File to = new File(path2);
		String path3 = path+"serial_settings_test.xml";
		File test = new File(path3);
        Files.copy(to.toPath(), test.toPath(), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(from.toPath(), to.toPath(), StandardCopyOption.REPLACE_EXISTING);
	}
	
}
