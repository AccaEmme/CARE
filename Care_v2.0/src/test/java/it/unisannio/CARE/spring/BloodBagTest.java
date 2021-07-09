package it.unisannio.CARE.spring;



import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import org.junit.AfterClass;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import it.unisannio.CARE.model.bloodBag.BloodBag;
import it.unisannio.CARE.model.bloodBag.BloodBagState;
import it.unisannio.CARE.model.bloodBag.BloodGroup;
import it.unisannio.CARE.model.bloodBag.Serial;
import it.unisannio.CARE.model.exceptions.IllegalDateException;
import it.unisannio.CARE.model.exceptions.IllegalSerialException;
import it.unisannio.CARE.model.user.User;
import it.unisannio.CARE.model.util.Constants;
import it.unisannio.CARE.model.util.Password;


/*
 *  JUnit test for BloodBag class.
 */

public class BloodBagTest {
	
	/**
	* Creation of the BloodGroup class constructor
	* @throws ParseException The date entered by the user is not well written or has a wrong format
	*/
	@Test
	public void ValidityTest_Constructor1_notNullObject() throws ParseException {
		BloodBag bg = new BloodBag(BloodGroup.ABneg, "PLVDNT96P21A783A");
		assertNotNull(bg);
	}
	
	
	/**
	* Invalid creation of the BloodGroup class constructor
	* @throws ParseException The date entered by the user is not well written or has a wrong format
	*/
	@Test
	public void InvalidityTest_Constructor1_notNullObject() throws ParseException {
		assertThrows(Exception.class, ()->{
			BloodBag bg = new BloodBag(BloodGroup.ABneg, "PLVDNT96P21A7A");
		});
	}
	
	
	/**
	* Creation of the BloodGroup class constructor
	* @throws ParseException The date entered by the user is not well written or has a wrong format
	*/
	@Test
	public void ValidityTest_Constructor2_notNullObject() throws ParseException {
		
		Serial S = new Serial ("IT-NA206000-Apos-20210416-0001");
		
		BloodBag bg1 = new BloodBag(S, BloodGroup.Aneg, 
				Constants.dateFormat.parse("20200921"), 
				Constants.dateFormat.parse("20201221"), 
				"PLVDNT96P21A783A",BloodBagState.Arrived, "ciao");
		
		assertNotNull(bg1);
	}
	
	/**
	* Invalid creation of the BloodGroup class constructor with wrong expiration date
	* @throws ParseException The date entered by the user is not well written or has a wrong format
	*/
	//@Test(expected = ParseException.class) 
	public void InvalidityTest_Constructor2_notNullObject() throws ParseException{
		assertThrows(Exception.class, () -> {
			Serial S = new Serial ("IT-NA206000-Apos-20210416-0001");
			Date cd = Constants.dateFormat.parse("2020092"); 
			Date ed = Constants.dateFormat.parse("20201221");
			BloodBag bg1 = new BloodBag(S, BloodGroup.Aneg, cd, ed, "PLVDNT96P21A783A",BloodBagState.Arrived, "Sacca modificata");
			}
		);
	}
	
	
	/**
	* Invalid creation of the BloodGroup class constructor with wrong date
	* @throws ParseException The date entered by the user is not well written or has a wrong format
	*/
	@Test
	public void InvalidityTest2_Constructor2_notNullObject() throws ParseException {
		assertThrows(Exception.class, ()->{
			    Serial S = new Serial ("IT-NA206000-Apos-20210416-0001");
		        BloodBag bg1 = new BloodBag(S, BloodGroup.Aneg, 
		        							Constants.dateFormat.parse("20200921"), 
		        							Constants.dateFormat.parse("20200815"), 
		        							"PLVDNT96P21A783A",BloodBagState.Arrived, "ciao");
		        bg1.setExpirationDate(Constants.dateFormat.parse("20200815"));
		});
	} 
	
	/**
	* Invalid creation of the BloodGroup class constructor with Invalid Serial
	* @throws ParseException The date entered by the user is not well written or has a wrong format
	*/
	@Test(expected = IllegalSerialException.class)  
	public void InvalidityTest3_Constructor2_notNullObject() throws ParseException{
		
		Serial S = new Serial ("IT-NA206000-Apos-20210416-000");
	
		Date cd = Constants.dateFormat.parse("20200921"); 
		
		Date ed = Constants.dateFormat.parse("20201221");
		
		BloodBag bg1 = new BloodBag(S, BloodGroup.Aneg, cd, ed, "PLVDNT96P21A783A",BloodBagState.Arrived, "Sacca modificata");
		
	}
	
	/**
	* Invalid creation of the BloodGroup class constructor with an expiration date earlier than the creation date
	* @throws ParseException The date entered by the user is not well written or has a wrong format
	*/
	//@Test(expected = IllegalDateException.class)  
	public void InvalidityTest4_Constructor2_notNullObject() throws ParseException{
		    assertThrows(Exception.class, () -> {
			Serial S = new Serial ("IT-NA206000-Apos-20210416-0001");
			Date ed = Constants.dateFormat.parse("20200921"); 
			Date cd = Constants.dateFormat.parse("20201221");
			BloodBag bg1 = new BloodBag(S, BloodGroup.Aneg, cd, ed, "PLVDNT96P21A783A",BloodBagState.Arrived, "Sacca modificata");
			}
		);
	}
	
	/**
	* Invalid creation of the BloodGroup class constructor with an incorrect tax code
	* @throws ParseException The date entered by the user is not well written or has a wrong format
	*/
	@Test(expected = IllegalArgumentException.class)  
	public void InvalidityTest5_Constructor2_notNullObject() throws ParseException{
		
		Serial S = new Serial ("IT-NA206000-Apos-20210416-0001");
	
		Date ed = Constants.dateFormat.parse("20200921"); 
		
		Date cd = Constants.dateFormat.parse("20201221");
		
		BloodBag bg1 = new BloodBag(S, BloodGroup.Aneg, cd, ed, "PLVDNT96P21A783AAAA",BloodBagState.Arrived, "Sacca modificata");
		
	}
	
	
	/**
	* Junit creation for the verification of the GET Serial method
	* @throws ParseException The date entered by the user is not well written or has a wrong format
	*/
	@Test
	public void ValidityTest_getSerial_notNullObject() throws ParseException{
		Serial S = new Serial ("IT-NA206000-Apos-20210416-0001");
		
		BloodBag bg = new BloodBag(BloodGroup.ABneg, "PLVDNT96P21A783A");
		assertNotNull(bg.getSerial());
	}
	
	/**
	* Junit creation for the verification of the group's GET method
	* @throws ParseException The date entered by the user is not well written or has a wrong format
	*/
	@Test
	public void ValidityTest_getBloodGroup_notNullObject() throws ParseException{
		Serial S = new Serial ("IT-NA206000-Apos-20210416-0001");
		
		BloodBag bg = new BloodBag(BloodGroup.ABneg, "PLVDNT96P21A783A");
		assertNotNull(bg.getBloodGroup());
	}
	
	/**
	* Junit creation for the verification of the group SET method
	* @throws ParseException The date entered by the user is not well written or has a wrong format
	*/
	@Test(expected = IllegalArgumentException.class)
	public void ValidityTest_setBloodGroup_notNullObject() throws ParseException{
		Serial S = new Serial ("IT-NA206000-Apos-20210416-0001");
		
		BloodBag bg1 = new BloodBag(S, null, 
				Constants.dateFormat.parse("20200921"), 
				Constants.dateFormat.parse("20201221"), 
				"PLVDNT96P21A783A",BloodBagState.Arrived, "Sacca Modificata");
	}
	
	/**
	* Junit creation for the verification of the GET method of the creation of the dates
	* @throws ParseException The date entered by the user is not well written or has a wrong format
	*/
	@Test
	public void ValidityTest_getCreationDate_notNullObject() throws ParseException{
		
		
		BloodBag bg = new BloodBag(BloodGroup.ABneg, "PLVDNT96P21A783A");
		assertNotNull(bg.getCreationDate());
	}
	
	/**
	* Junit creation for the verification of the GET method of the expiration date
	* @throws ParseException The date entered by the user is not well written or has a wrong format
	*/
	@Test
	public void ValidityTest_getExpirationDate_notNullObject() throws ParseException{
	
		
		Serial S = new Serial ("IT-NA206000-Apos-20210416-0001");
		
		Date cd = Constants.dateFormat.parse("2020092"); 
		
		Date ed = Constants.dateFormat.parse("20201221");
		
		BloodBag bg1 = new BloodBag(S, BloodGroup.Aneg, cd, ed, "PLVDNT96P21A783A",BloodBagState.Arrived, "Sacca modificata");
		
		
		assertNotNull(bg1.getExpirationDate());
	}
	

	/**
	* Junit creation for the verification of the GET method of the creation date S
	* @throws ParseException The date entered by the user is not well written or has a wrong format
	*/
	@Test
	public void ValidityTest_getCreationDateS_notNullObject() throws ParseException{
		
		
		Serial S = new Serial ("IT-NA206000-Apos-20210416-0001");
		
		Date cd = Constants.dateFormat.parse("2020092"); 
		
		Date ed = Constants.dateFormat.parse("20201221");
		
		BloodBag bg1 = new BloodBag(S, BloodGroup.Aneg, cd, ed, "PLVDNT96P21A783A",BloodBagState.Arrived, "Sacca modificata");
		
		
	}
	
	/**
	* Junit creation for the verification of the GET method of the donor's tax code
	* @throws ParseException The date entered by the user is not well written or has a wrong format
	*/
	@Test
	public void ValidityTest_getDonatorCF_notNullObject() throws ParseException{
	
		
		BloodBag bg = new BloodBag(BloodGroup.ABneg, "PLVDNT96P21A783A");
		
		assertNotNull(bg.getDonatorCF());
	}
	
	
	/**
	* Junit creation for the verification of the GET method of the blood group type of the bag
	* @throws ParseException The date entered by the user is not well written or has a wrong format
	*/
	@Test
	public void ValidityTest_getBloodType_notNullObject() throws ParseException{
	
		
		BloodBag bg = new BloodBag(BloodGroup.ABneg, "PLVDNT96P21A783A");
		
		assertNotNull(bg.getBloodType());
	}
	
	
	/**
	* Junit creation for the verification of the GET method of the bag notes
	* @throws ParseException The date entered by the user is not well written or has a wrong format
	*/
	@Test
	public void ValidityTest_getNote_notNullObject() throws ParseException{
	
		
		BloodBag bg = new BloodBag(BloodGroup.ABneg, "PLVDNT96P21A783A");
		
		assertNotNull(bg.getNote());
	}
	
	
	/**
	* Junit creation for the verification of the GET method of the status of the bags
	* @throws ParseException The date entered by the user is not well written or has a wrong format
	*/
	@Test
	public void ValidityTest_getBloodBagState_notNullObject() throws ParseException{
	
		
		BloodBag bg = new BloodBag(BloodGroup.ABneg, "PLVDNT96P21A783A");
		assertNotNull(bg.getBloodBagState());
	}
	
	/**
	* Junit creation for the verification of the TOSTRING method of the bag information
	* @throws ParseException The date entered by the user is not well written or has a wrong format
	*/
	@Test
	public void ValidityTest_ToString_notNullObject() throws ParseException{

		BloodBag bg = new BloodBag(BloodGroup.ABneg, "PLVDNT96P21A783A");
		
		assertNotNull(bg.toString());
	}
	
	/**
	* Junit creation for the verification of the PRINT method
	* @throws ParseException The date entered by the user is not well written or has a wrong format
	*/
	@Test
	public void ValidityTest_Print_notNullObject() throws ParseException{

		BloodBag bg = new BloodBag(BloodGroup.ABneg, "PLVDNT96P21A783A");
		
		bg.print();
	}
	
	
	/**
	* Junit creation to verify the Buy method
	* @throws ParseException The date entered by the user is not well written or has a wrong format
	*/
	@Test
	public void ValidityTest_compareTo_notNullObject() throws ParseException{

		BloodBag bg = new BloodBag(BloodGroup.ABneg, "PLVDNT96P21A783A");
		
		BloodBag bg2 = new BloodBag(BloodGroup.ABpos, "PLVDNT96P21A784A");
		
		assertNotNull(bg.compareTo(bg2));
	}
	
	
	/**
	* Junit creation to verify the getBean method
	* @throws ParseException The date entered by the user is not well written or has a wrong format
	*/
	@Test
	public void ValidityTest_getBean_notNullObject() throws ParseException{

		BloodBag bg = new BloodBag(BloodGroup.ABneg, "PLVDNT96P21A783A");
			
		bg.getBean();
	}
	
	/**
	* Junit creation for the verification of the AppendNote method
	* @throws ParseException The date entered by the user is not well written or has a wrong format
	*/
	@Test
	public void ValidityTest_appendNote_notNullObject() throws ParseException{

		BloodBag bg = new BloodBag(BloodGroup.ABneg, "PLVDNT96P21A783A");
			
		bg.appendNote("Stringa di prova");
	}
	
	/**
	* Junit creation for the verification of the equals method
	* @throws ParseException The date entered by the user is not well written or has a wrong format
	*/
	@Test
	public void InvalidityTest_equals_notNullObject() throws ParseException{

		Serial S = new Serial ("IT-NA206000-Apos-20210416-0001");
		
		Date cd = Constants.dateFormat.parse("2020092"); 
		
		Date ed = Constants.dateFormat.parse("20201221");
		
		BloodBag bg1 = new BloodBag(S, BloodGroup.Aneg, cd, ed, "PLVDNT96P21A783A",BloodBagState.Arrived, "Sacca modificata");
		
		Serial S2 = new Serial ("IT-NA206000-Apos-20210416-0000");
	
		assertFalse(bg1.equals(S2));
	}
	
	/**
	* Junit creation for the verification of the equals method
	* @throws ParseException The date entered by the user is not well written or has a wrong format
	*/
	@Test
	public void ValidityTest_equals_notNullObject() throws ParseException{

		Serial S = new Serial ("IT-NA206000-Apos-20210416-0001");
		
		Date cd = Constants.dateFormat.parse("2020092"); 
		
		Date ed = Constants.dateFormat.parse("20201221");
		
		BloodBag bg1 = new BloodBag(S, BloodGroup.Aneg, cd, ed, "PLVDNT96P21A783A",BloodBagState.Arrived, "Sacca modificata");
		
		Serial S2 = new Serial ("IT-NA206000-Apos-20210416-0000");
		
		Date cd2 = Constants.dateFormat.parse("2020092"); 
		
		Date ed2 = Constants.dateFormat.parse("20201221");
		
		BloodBag bg3 = new BloodBag(S2, BloodGroup.Aneg, cd2, ed2, "PLVDNT96P21A783A",BloodBagState.Arrived, "Sacca modificata");
		
			
		assertNotNull(bg1.equals(bg3));
	}
	

	/**
	* Junit creation for the verification of the SET method of the creationDate with a date after the limit of 7 days
	* @throws ParseException The date entered by the user is not well written or has a wrong format
	*/
	@Test(expected = IllegalDateException.class)
	public void ValidityTest_setCreationDate_notNullObject() throws ParseException{
		
		
		Serial S = new Serial ("IT-NA206000-Apos-20210416-0001");
		
		Date cd = Constants.dateFormat.parse("2020092"); 
		
		Date ed = Constants.dateFormat.parse("20201221");
		
		BloodBag bg1 = new BloodBag(S, BloodGroup.Aneg, cd, ed, "PLVDNT96P21A783A",BloodBagState.Arrived, "Sacca modificata");
		
		bg1.setCreationDate(Constants.dateFormat.parse("20291221"));
	}
	
	/**
	* Junit creation for the verification of the SET method of the creationDate with a date after the limit of 7 days
	* @throws ParseException The date entered by the user is not well written or has a wrong format
	*/
	@Test(expected = IllegalDateException.class)
	public void ValidityTest_setCreationDate2_notNullObject() throws ParseException{
		
		
		Serial S = new Serial ("IT-NA206000-Apos-20210416-0001");
		
		Date cd = Constants.dateFormat.parse("2020092"); 
		
		Date ed = Constants.dateFormat.parse("20201221");
		
		BloodBag bg1 = new BloodBag(S, BloodGroup.Aneg, cd, ed, "PLVDNT96P21A783A",BloodBagState.Arrived, "Sacca modificata");
		
		bg1.setCreationDate(Constants.dateFormat.parse("20001221"));
	}
	
}
