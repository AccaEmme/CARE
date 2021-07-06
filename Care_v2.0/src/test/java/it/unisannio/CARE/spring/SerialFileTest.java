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
 * Si preferisce generare due SerialTest in quanto entrambi hanno un @BeforeClass che porta in due stati differenti.
 *
 */

public class SerialFileTest {
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
		//InitSettings.initXML(100,path1);
		BloodGroup g= BloodGroup.valueOf("ZEROneg") ;
		Serial s=new Serial(g);
		Properties loadProps = new Properties();
		/*uso direttamente serial_setting_temp*/
		
	}

	@Test
	public void testNewDate() throws InvalidPropertiesFormatException, FileNotFoundException, IOException {	
		String path3 = path+"serial_settings.xml";
	 	
	    

		  Properties loadProps = new Properties();
		  loadProps.loadFromXML(new FileInputStream(path3));
	      int  counter = Integer.valueOf(loadProps.getProperty("counter"));
		 assertTrue(counter==3); 
		/*testo se ha la data di oggo*/
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

	
	/**
	 * Creazione del costruttore della classe Serial
	 * @result Il seriale viene creata nel modo corretto non sviluppando nessuna eccezione
	 */
	@Test
	public void ValidityTest_Constructor1_notNullObject() {
		Serial s = new Serial(BloodGroup.ABneg);
		assertNotNull(s);
	}
	
	/**
	 * Creazione del costruttore della classe Serial
	 * @result Se la stringa del seriale è giusta, viene inserito il seriale nuovo
	 */
	@Test
	public void ValidityTest_Serial_notNullObject() {
		Serial s = new Serial("IT-NA206000-Apos-20210416-0001");
		assertNotNull(s);
	}
	
	/**
	 * Creazione del costruttore della classe Serial invalido
	 * @result La stringa del seriale non è valida, quindi ritorna una eccezione
	 */
	@Test (expected = IllegalSerialException.class)
	public void InvalidityTest_Serial_notNullObject() {
		Serial s = new Serial("IT-NA206000-Apos-20210416-000");
		assertNotNull(s);
	}
	
	/**
	 * Test del medoto GetSerial
	 * @result ritorna la convalida del metodo getSerial
	 */
	@Test
	public void ValidityTest_getSerial_notNullObject() {
		Serial s = new Serial("IT-NA206000-Apos-20210416-0001");
		assertNotNull(s.getSerial());
	}
	
	/**
	 * Test del medoto TOSTRING
	 * @result ritorna tutte le informazioni della classe Serial
	 */
	@Test
	public void ValidityTest_ToString_notNullObject() {
		Serial s = new Serial("IT-NA206000-Apos-20210416-0001");
		s.toString();
	}
	
	/**
	 * Verifica del funzionamento del metodo equal
	 * @result ritorna true perchè paragono due oggetti Serial
	 */
	@Test
	public void ValidityTest_Equals_notNullObject() {
		Serial s = new Serial("IT-NA206000-Apos-20210416-0001");
		Serial s2 = new Serial("IT-NA206000-Apos-20210416-0002");
		assertNotNull(s.equals(s2));
	}
	
	/**
	 * Verifica del funzionamento del metodo equal
	 * @throws ParseException 
	 * @result ritorna false perchè non paragono due oggetti Serial
	 */
	@Test
	public void InvalidityTest_Equals_notNullObject() throws ParseException {
		Serial s = new Serial("IT-NA206000-Apos-20210416-0001");
		BloodBag bg = new BloodBag(BloodGroup.ABneg, "PLVDNT96P21A783A");
		assertFalse(s.equals(bg));
	}

	
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
