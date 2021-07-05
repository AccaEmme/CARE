package it.unisannio.CARE.spring;



import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.Assert.assertEquals;


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
import it.unisannio.CARE.model.util.Constants;
import it.unisannio.CARE.model.util.Password;


/*
 *  JUnit test for BloodBag class.
 */

public class BloodBagTest {
	
	
	/**
	 * Creazione del costruttore della classe BloodGroup
	 * @throws ParseException
	 * @result La sacca viene creata nel modo corretto non sviluppando nessuna eccezione
	 */
	@Test
	public void ValidityTest_Constructor1_notNullObject() throws ParseException {
		BloodBag bg = new BloodBag(BloodGroup.ABneg, "PLVDNT96P21A783A");
		assertNotNull(bg);
	}
	
	
	/**
	 * Creazione INVALIDA costruttore della classe BloodGroup
	 * @throws ParseException
	 * @result La sacca viene creata nel modo non corretto poichè viene inserito un codice fiscale errato
	 */
	@Test
	public void InvalidityTest_Constructor1_notNullObject() throws ParseException {
		assertThrows(Exception.class, ()->{
			BloodBag bg = new BloodBag(BloodGroup.ABneg, "PLVDNT96P21A7A");
		});
	}
	
	
	/**
	 * Creazione del costruttore della classe BloodGroup
	 * @result La sacca viene creata nel modo corretto non sviluppando nessuna eccezione
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
	 * Creazione invalida del costruttore della classe BloodGroup con data scadenza sbagliata
	 * @throws ParseException 
	 * @result La sacca viene creata nel modo corretto non sviluppando nessuna eccezione
	 */
	@Test(expected = ParseException.class) 
	public void InvalidityTest_Constructor2_notNullObject() throws ParseException{
		
		Serial S = new Serial ("IT-NA206000-Apos-20210416-0001");
	
		Date cd = Constants.dateFormat.parse("2020092"); 
		
		Date ed = Constants.dateFormat.parse("20201221");
		
		BloodBag bg1 = new BloodBag(S, BloodGroup.Aneg, cd, ed, "PLVDNT96P21A783A",BloodBagState.Arrived, "Sacca modificata");
		
	}
	
	
	
	
	/**
	 * Creazione invalida del costruttore della classe BloodGroup con data di sbagliata
	 * @throws ParseException
	 * @result La sacca viene creata nel modo corretto non sviluppando nessuna eccezione
	 */
	@Test
	public void InvalidityTest2_Constructor2_notNullObject() throws ParseException {
		
		Serial S = new Serial ("IT-NA206000-Apos-20210416-0001");
		
		assertThrows(Exception.class, ()->{
		BloodBag bg1 = new BloodBag(S, BloodGroup.Aneg, 
				Constants.dateFormat.parse("20200921"), 
				Constants.dateFormat.parse("2020122"), 
				"PLVDNT96P21A783A",BloodBagState.Arrived, "ciao");
		});
	} 
	
	/**
	 * Creazione invalida del costruttore della classe BloodGroup con Seriale Invalido
	 * @throws ParseException 
	 * @result La sacca viene creata nel modo corretto ma inserendo un errore nel seriale e quindi richiama l'eccezione
	 */
	@Test(expected = IllegalSerialException.class)  
	public void InvalidityTest3_Constructor2_notNullObject() throws ParseException{
		
		Serial S = new Serial ("IT-NA206000-Apos-20210416-000");
	
		Date cd = Constants.dateFormat.parse("20200921"); 
		
		Date ed = Constants.dateFormat.parse("20201221");
		
		BloodBag bg1 = new BloodBag(S, BloodGroup.Aneg, cd, ed, "PLVDNT96P21A783A",BloodBagState.Arrived, "Sacca modificata");
		
	}
	
	/**
	 * Creazione invalida del costruttore della classe BloodGroup con una data di scadenza precendente a quella di creazione
	 * @throws ParseException 
	 * @result La sacca viene creata nel modo corretto ma ritornerà un eccezione per la data di scadenza precedente a quella di creazione
	 */
	@Test(expected = IllegalDateException.class)  
	public void InvalidityTest4_Constructor2_notNullObject() throws ParseException{
		
		Serial S = new Serial ("IT-NA206000-Apos-20210416-0001");
	
		Date ed = Constants.dateFormat.parse("20200921"); 
		
		Date cd = Constants.dateFormat.parse("20201221");
		
		BloodBag bg1 = new BloodBag(S, BloodGroup.Aneg, cd, ed, "PLVDNT96P21A783A",BloodBagState.Arrived, "Sacca modificata");
		
	}
	
	/**
	 * Creazione invalida del costruttore della classe BloodGroup con un codice fiscale errato
	 * @throws ParseException 
	 * @result La sacca viene creata nel modo corretto ma ritornerà un eccezione per per il codice fiscale errato
	 */
	@Test(expected = IllegalArgumentException.class)  
	public void InvalidityTest5_Constructor2_notNullObject() throws ParseException{
		
		Serial S = new Serial ("IT-NA206000-Apos-20210416-0001");
	
		Date ed = Constants.dateFormat.parse("20200921"); 
		
		Date cd = Constants.dateFormat.parse("20201221");
		
		BloodBag bg1 = new BloodBag(S, BloodGroup.Aneg, cd, ed, "PLVDNT96P21A783AAAA",BloodBagState.Arrived, "Sacca modificata");
		
	}
	
	
	/**
	 * Creazione Junit per la verica del metodo GET Serial 
	 * @throws ParseException 
	 * @result ritorna il funzionamento del metodo getSerial
	 */
	@Test
	public void ValidityTest_getSerial_notNullObject() throws ParseException{
		Serial S = new Serial ("IT-NA206000-Apos-20210416-0001");
		
		BloodBag bg = new BloodBag(BloodGroup.ABneg, "PLVDNT96P21A783A");
		assertNotNull(bg.getSerial());
	}
	
	/**
	 * Creazione Junit per la verica del metodo GET del gruppo
	 * @throws ParseException 
	 * @result ritorna il Get del metodo del gruppo della sacca 
	 */
	@Test
	public void ValidityTest_getBloodGroup_notNullObject() throws ParseException{
		Serial S = new Serial ("IT-NA206000-Apos-20210416-0001");
		
		BloodBag bg = new BloodBag(BloodGroup.ABneg, "PLVDNT96P21A783A");
		assertNotNull(bg.getBloodGroup());
	}
	
	/**
	 * Creazione Junit per la verica del metodo SET del gruppo
	 * @throws ParseException 
	 * @exception IllegalArgumentException
	 * @result Viene utilizzato il metodo SET del tipo del gruppo della sacca di sangue,
	 * 			il quale ritorna errore e innesca una eccezione 
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
	 * Creazione Junit per la verica del metodo GET della creazione delle date
	 * @throws ParseException 
	 * @result ritorna il corretto funzionamento del metodo ritorno della data di creazione
	 */
	@Test
	public void ValidityTest_getCreationDate_notNullObject() throws ParseException{
		
		
		BloodBag bg = new BloodBag(BloodGroup.ABneg, "PLVDNT96P21A783A");
		assertNotNull(bg.getCreationDate());
	}
	
	/**
	 * Creazione Junit per la verica del metodo GET della data di scadenza
	 * @throws ParseException 
	 * @result ritorna il corretto funzionamento del metodo ritorno della data di scadenza
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
	 * Creazione Junit per la verica del metodo GET della data di creazione S
	 * @throws ParseException 
	 * @result ritorna il corretto funzionamento del metodo ritorno della data di creazione S
	 */
	@Test
	public void ValidityTest_getCreationDateS_notNullObject() throws ParseException{
		
		
		Serial S = new Serial ("IT-NA206000-Apos-20210416-0001");
		
		Date cd = Constants.dateFormat.parse("2020092"); 
		
		Date ed = Constants.dateFormat.parse("20201221");
		
		BloodBag bg1 = new BloodBag(S, BloodGroup.Aneg, cd, ed, "PLVDNT96P21A783A",BloodBagState.Arrived, "Sacca modificata");
		
		
	}
	
	/**
	 * Creazione Junit per la verica del metodo GET del codice fiscale del donatore
	 * @throws ParseException 
	 * @result ritorna il corretto funzionamento del metodo ritorno del codice fiscale del donatore
	 */
	@Test
	public void ValidityTest_getDonatorCF_notNullObject() throws ParseException{
	
		
		BloodBag bg = new BloodBag(BloodGroup.ABneg, "PLVDNT96P21A783A");
		
		assertNotNull(bg.getDonatorCF());
	}
	
	
	/**
	 * Creazione Junit per la verica del metodo GET del tipo del gruppo sanguineo della sacca
	 * @throws ParseException 
	 * @result ritorna il corretto funzionamento del metodo ritorno del gruppo sanguineo della sacca
	 */
	@Test
	public void ValidityTest_getBloodType_notNullObject() throws ParseException{
	
		
		BloodBag bg = new BloodBag(BloodGroup.ABneg, "PLVDNT96P21A783A");
		
		assertNotNull(bg.getBloodType());
	}
	
	
	/**
	 * Creazione Junit per la verica del metodo GET delle note della sacca
	 * @throws ParseException 
	 * @result ritorna il corretto funzionamento del metodo ritorno delle note della sacca
	 */
	@Test
	public void ValidityTest_getNote_notNullObject() throws ParseException{
	
		
		BloodBag bg = new BloodBag(BloodGroup.ABneg, "PLVDNT96P21A783A");
		
		assertNotNull(bg.getNote());
	}
	
	
	/**
	 * Creazione Junit per la verica del metodo GET dello stato delle sacche 
	 * @throws ParseException 
	 * @result ritorna il corretto funzionamento del metodo ritorno dello stato delle sacche 
	 */
	@Test
	public void ValidityTest_getBloodBagState_notNullObject() throws ParseException{
	
		
		BloodBag bg = new BloodBag(BloodGroup.ABneg, "PLVDNT96P21A783A");
		assertNotNull(bg.getBloodBagState());
	}
	
	/**
	 * Creazione Junit per la verica del metodo TOSTRING delle informazioni delle sacche
	 * @throws ParseException 
	 * @result ritorna tutte le informazioni delle sacche
	 */
	@Test
	public void ValidityTest_ToString_notNullObject() throws ParseException{

		BloodBag bg = new BloodBag(BloodGroup.ABneg, "PLVDNT96P21A783A");
		
		assertNotNull(bg.toString());
	}
	
	/**
	 * Creazione Junit per la verica del metodo del PRINT 
	 * @throws ParseException 
	 * @result ritorna tutte le informazioni di print
	 */
	@Test
	public void ValidityTest_Print_notNullObject() throws ParseException{

		BloodBag bg = new BloodBag(BloodGroup.ABneg, "PLVDNT96P21A783A");
		
		bg.print();
	}
	
	
	/**
	 * Creazione Junit per la verica del metodo Comprare
	 * @throws ParseException 
	 * @result ritorna se la comparazione di due oggetti è funzionante
	 */
	@Test
	public void ValidityTest_compareTo_notNullObject() throws ParseException{

		BloodBag bg = new BloodBag(BloodGroup.ABneg, "PLVDNT96P21A783A");
		
		BloodBag bg2 = new BloodBag(BloodGroup.ABpos, "PLVDNT96P21A784A");
		
		assertNotNull(bg.compareTo(bg2));
	}
	
	
	/**
	 * Creazione Junit per la verica del metodo getBean
	 * @throws ParseException 
	 * @result ritorna le informazioni getBean
	 */
	@Test
	public void ValidityTest_getBean_notNullObject() throws ParseException{

		BloodBag bg = new BloodBag(BloodGroup.ABneg, "PLVDNT96P21A783A");
			
		bg.getBean();
	}
	
	/**
	 * Creazione Junit per la verica del metodo AppendNote
	 * @throws ParseException 
	 * @result ritorna le informazioni getBean
	 */
	@Test
	public void ValidityTest_appendNote_notNullObject() throws ParseException{

		BloodBag bg = new BloodBag(BloodGroup.ABneg, "PLVDNT96P21A783A");
			
		bg.appendNote("Stringa di prova");
	}
	
	/**
	 * Creazione Junit per la verica del metodo equals 
	 * @throws ParseException 
	 * @result ritorna le informazioni equals, in questo caso ritorna falso perchè i due oggetti non sono uguali
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
	 * Creazione Junit per la verica del metodo equals 
	 * @throws ParseException 
	 * @result ritorna le informazioni equals, in questo caso ritorna falso perchè le due sacche non sono uguali
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
	 * Creazione Junit per la verica del metodo SET della creationDate con una data dopo il limite di 7gg
	 * @throws ParseException 
	 * @result ritorna il corretto funzionamento del metodo in caso la data sia dopo della data di creazione
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
	 * Creazione Junit per la verica del metodo SET della creationDate con una data dopo il limite di 7gg
	 * @throws ParseException 
	 * @result ritorna il corretto funzionamento del metodo in caso la data sia prima della data di creazione
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
