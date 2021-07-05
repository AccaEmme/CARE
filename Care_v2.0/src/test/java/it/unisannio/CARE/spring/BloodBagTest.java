package it.unisannio.CARE.spring;



import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
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
	@Ignore
	@Test(expected = ParseException.class) 
	public void InvalidityTest_Constructor2_notNullObject() throws ParseException{
		
		Serial S = new Serial ("IT-NA206000-Apos-20210416-0001");
	
		Date cd = Constants.dateFormat.parse("2020092"); 
		
		Date ed = Constants.dateFormat.parse("20201221");
		
		BloodBag bg1 = new BloodBag(S, BloodGroup.Aneg, cd, ed, "PLVDNT96P21A783A",BloodBagState.Arrived, "Sacca modificata");
		
	}
	
	
	/**
	 * Creazione invalida del costruttore della classe BloodGroup con data di sbagliata
	 * @result La sacca viene creata nel modo corretto non sviluppando nessuna eccezione
	 */
	@Ignore
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
	 * @result La sacca viene creata nel modo corretto non sviluppando nessuna eccezione
	 */
	@Test(expected = IllegalSerialException.class)  
	public void InvalidityTest3_Constructor2_notNullObject() throws ParseException{
		
		Serial S = new Serial ("IT-NA206000-Apos-20210416-000");
	
		Date cd = Constants.dateFormat.parse("20200921"); 
		
		Date ed = Constants.dateFormat.parse("20201221");
		
		BloodBag bg1 = new BloodBag(S, BloodGroup.Aneg, cd, ed, "PLVDNT96P21A783A",BloodBagState.Arrived, "Sacca modificata");
		
	}
	
	
	/**
	 * Creazione Junit per la verica del metodo GET Serial 
	 * @throws ParseException 
	 * @result Il seriale viene correttamente visualizzato
	 */
	@Test
	public void ValidityTest_getSerial_notNullObject() throws ParseException{
		Serial S = new Serial ("IT-NA206000-Apos-20210416-0001");
		
		BloodBag bg1 = new BloodBag(S, BloodGroup.Aneg, 
				Constants.dateFormat.parse("20200921"), 
				Constants.dateFormat.parse("20201221"), 
				"PLVDNT96P21A783A",BloodBagState.Arrived, "Sacca Modificata");
		assertNotNull(bg1.getSerial());
	}
	
	/**
	 * Creazione Junit per la verica del metodo GET del gruppo
	 * @throws ParseException 
	 * @result Il seriale viene correttamente visualizzato
	 */
	@Test
	public void ValidityTest_getBloodGroup_notNullObject() throws ParseException{
		Serial S = new Serial ("IT-NA206000-Apos-20210416-0001");
		
		BloodBag bg1 = new BloodBag(S, BloodGroup.Aneg, 
				Constants.dateFormat.parse("20200921"), 
				Constants.dateFormat.parse("20201221"), 
				"PLVDNT96P21A783A",BloodBagState.Arrived, "Sacca Modificata");
		assertNotNull(bg1.getBloodBagState());
	}
	
	/**
	 * Creazione Junit per la verica del metodo SET del gruppo
	 * @throws ParseException 
	 * @result Il seriale viene correttamente visualizzato
	 */
	@Test(expected = IllegalArgumentException.class)
	public void ValidityTest_setBloodGroup_notNullObject() throws ParseException{
		Serial S = new Serial ("IT-NA206000-Apos-20210416-0001");
		
		BloodBag bg1 = new BloodBag(S, null, 
				Constants.dateFormat.parse("20200921"), 
				Constants.dateFormat.parse("20201221"), 
				"PLVDNT96P21A783A",BloodBagState.Arrived, "Sacca Modificata");
	}
	
	
	
	
	
/*	

	@Test (expected = NullPointerException.class)
	public void donatorCFNULL() throws ParseException {
		BloodGroup bg = BloodGroup.valueOf("Bneg");
		new BloodBag(bg, null);
	}
	
	/*
	 * test sbagliato perché in verità sto testando l'enum BloodGroup
	 */
	/*
	@Test (expected = IllegalArgumentException.class)
	public void wrongGroup() throws ParseException {
		String codF = "CRSDLCER86BH0911";
		BloodGroup bg = BloodGroup.valueOf("Bm");
		new BloodBag(bg, codF);
	}
	
	@Test (expected = NullPointerException.class)
	public void nullGroup() throws ParseException {
		String codF = "CRSDLCER86BH0911";
		new BloodBag(null, codF);
	}
	
	@Test 
	public void testCostructorsInstance1() throws ParseException {
		String codF = "CRSDLCER86BH0911";
		String note = "Prima donazione del paziente: abbiamo prelevato 50ml meno del previsto.";
		BloodGroup bg = BloodGroup.valueOf("ZEROneg");
		BloodBag bb1 = new BloodBag(bg, codF);
		BloodBag bb2 = new BloodBag(bg, codF);
		System.out.println(bb1.toString());
		System.out.println(bb2.toString());
		assertFalse(bb1.equals(bb2));
	}
	
	@Test 
	public void testCostructorsInstance2() throws ParseException {
		String codF = "CRSDLCER86BH0911";
		String note = "Prima donazione del paziente: abbiamo prelevato 50ml meno del previsto.";
		BloodGroup bg = BloodGroup.valueOf("ZEROneg");
		BloodBag bb1 = new BloodBag(bg, codF, note);
		BloodBag bb2 = new BloodBag(bg, codF, note);
		System.out.println(bb1.toString());
		System.out.println(bb2.toString());
		assertFalse(bb1.equals(bb2));
	}
	
	@Test 
	public void testCostructorsInstance3() throws ParseException {
		String codF = "CRSDLCER86BH0911";
		String note = "Prima donazione del paziente: abbiamo prelevato 50ml meno del previsto.";
		BloodGroup bg = BloodGroup.valueOf("ZEROneg");
		BloodBag bb1 = new BloodBag(bg, codF);
		BloodBag bb2 = new BloodBag(bg, codF, note);
		System.out.println(bb1.toString());
		System.out.println(bb2.toString());
		assertFalse(bb1.equals(bb2));
	}
	
	@Test
	public void testDates1() throws ParseException{
		String codF = "CRSDLCER86BH0911";
		BloodGroup bg = BloodGroup.valueOf("Bneg");
		BloodBag bb = new BloodBag(bg, codF);
		assertEquals(bb.getCreationDate(), bb.getExpirationDate());
	}
	
	@Test
	public void testDates2() throws ParseException{
		String codF = "CRSDLCER86BH0911";
		BloodGroup bg = BloodGroup.valueOf("Bneg");
		BloodBag bb = new BloodBag(bg, codF);
		assertFalse(bb.getCreationDate().after(bb.getExpirationDate()));
	}
	
	@Test
	public void testDates3() throws ParseException{
		String codF = "CRSDLCER86BH0911";
		BloodGroup bg = BloodGroup.valueOf("Bneg");
		BloodBag bb = new BloodBag(bg, codF);
		assertFalse(bb.getExpirationDate().before(bb.getCreationDate()));
	}
	
	@Test
	public void testDates4() throws ParseException{
		String codF = "CRSDLCER86BH0911";
		BloodGroup bg = BloodGroup.valueOf("Bneg");
		BloodBag bb = new BloodBag(bg, codF);
		Calendar cal = Calendar.getInstance();
		cal.setTime( bb.getCreationDate() );
		cal.add(Calendar.DAY_OF_MONTH, 1);
		System.out.println(cal.getTime());
		assertEquals(bb.getCreationDate(), cal.getTime());
	}
	
	@Test
	public void testCloneMethod() throws ParseException{
		
		String codF = "CRSDLCER86BH0911";
		BloodGroup bg = BloodGroup.valueOf("Bneg");
		BloodBag bbOldNode = new BloodBag(bg, codF, "BN03");
		BloodBag bbNewNode = bbOldNode.clone();
		bbOldNode.setState("Transfered");
		System.out.println(bbOldNode.toString());
		System.out.println(bbNewNode.toString());
	}
	*/
	
	@Test
	public void testManager() throws FileNotFoundException{
		/*
		Scanner nodeSc = new Scanner(new File("Nodes.txt"));
		Scanner bloodSc = new Scanner(new File("BloodBags.txt"));
		BloodBagManager2 bM = new BloodBagManager2(nodeSc, bloodSc);
		
		bM.print();
		
		BloodBagManager2 bMBen = bM.filteredByProvince("Benevento");
		bMBen.print();
		
		BloodBagManager2 bMAve = bM.filteredByProvince("Avellino");
		bMAve.print();
		 */
	}
}
