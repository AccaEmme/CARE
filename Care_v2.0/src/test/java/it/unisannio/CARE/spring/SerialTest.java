package it.unisannio.CARE.spring;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.util.stream.Stream;

import it.unisannio.CARE.model.util.Constants;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import it.unisannio.CARE.model.bloodBag.BloodBag;
import it.unisannio.CARE.model.bloodBag.BloodGroup;
import it.unisannio.CARE.model.bloodBag.Serial;
/*
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;*/
import it.unisannio.CARE.model.exceptions.IllegalSerialException;

import org.junit.jupiter.params.ParameterizedTest;

/**
 * JUnit test for Serial.
 */
public class SerialTest {
	public static String path 			= Constants.SERIAL_SETTINGS_RELATIVEPATH;
	public static String filename 			= Constants.SERIAL_SETTINGS_FILENAME;
	public static final String tempfilename = "serial_settings_temp.xml"; 
	
	
	/**
	* Before class for the correct creation of the configuration file
	* @result a copy of the configuration file is created, plus the first serial_settings file is successfully created
	*/
	@BeforeClass
	public static void createFile() throws IOException {
		String path1 = path+filename;
		File from = new File(path1);
		String path2 = path+tempfilename;
		File to = new File(path2);
        Files.copy(from.toPath(), to.toPath(), StandardCopyOption.REPLACE_EXISTING);
		from.delete();
	}
	
	/**
	* Junit test to verify the correct creation of the file
	* @result the result expects true, to indicate the correct creation of the file
	*/
	@Test
	public void testFile() {
	   File f = new File(path+filename);
	   assertTrue(f.exists()); 
	}
	
	/**
	* Junit test to verify the absence of the configuration file
	* @result the result expects false with the FileNotFound exception call
	*/
	@Test(expected = FileNotFoundException.class)
	public void InvalidityTest_File() {
	   File f = new File(path+"serial.xml");
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
		String path1 = path+tempfilename;
		File from = new File(path1);
		String path2 = path+filename;
		File to = new File(path2);
        Files.copy(from.toPath(), to.toPath(), StandardCopyOption.REPLACE_EXISTING);
	}
	
	
    static Stream<String> bloodGroupWellDefined() {
        return Stream.of(
                "Apos",
                "Aneg",
                "Bpos", 
                "Bneg", 
                "ZEROpos",
                "ZEROneg",
                "ABpos",
                "ABneg"
        );
    }
    
}