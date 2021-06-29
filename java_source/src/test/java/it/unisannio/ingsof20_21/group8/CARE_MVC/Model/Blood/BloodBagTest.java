package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Constants;

import static org.junit.Assert.assertFalse;
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
import java.util.Scanner;

import org.junit.AfterClass;

import org.junit.BeforeClass;
import org.junit.Test;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodGroup;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Serial;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;

/*
 *  JUnit test for BloodBag class.
 */
public class BloodBagTest {
	package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood;

	import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Node.Node;
	import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location;
	import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.City;
	import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.Country;
	import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.Province;
	import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.Region;

	import static org.junit.Assert.assertNotNull;
	import static org.junit.Assert.assertThrows;

	import java.text.ParseException;

	import org.junit.Test;

	/*
	 *  JUnit test for BloodBag class.
	 */

		//JUnit test per il primo Costruttore presente nella classe BloodBag
		@Test
		public void  ValidityTest_Constructor1_BagAneg() throws ParseException {
			
				BloodGroup bg = BloodGroup.Aneg;
				String codiceFiscale = "PRTGCM45F28P678L";
				Location location = new Location(Country.Italy, Region.Campania, Province.Avellino, City.Avellino, "via 25 Aprile", "5","82020");
				Node node = new Node("123", "Ospedale Frangipane", location);
			
				BloodBag bloodBag   = new BloodBag(bg, codiceFiscale, node );
				assertNotNull(bloodBag);
			
		}
		
		@Test
		public void  InvalidityCFTest_Constructor1_BagAneg() throws ParseException {
			
				BloodGroup bg = BloodGroup.Aneg;
				String codiceFiscale = "PTGCM45F28P678L";
				Location location = new Location(Country.Italy, Region.Campania, Province.Avellino, City.Avellino, "via 25 Aprile", "5","82020");
				Node node = new Node("123", "Ospedale Frangipane", location);
				
				
				assertThrows(IllegalArgumentException.class, () -> {
					BloodBag bloodBag   = new BloodBag(bg, codiceFiscale, node );
				}
				);
		}
		
		@Test
		public void  ValidityTest_Constructor1_BagApos() throws ParseException {
			
			BloodGroup bg = BloodGroup.Apos;
			String codiceFiscale = "PRTGCM45F28P678L";
			Location location = new Location(Country.Italy, Region.Campania, Province.Avellino, City.Avellino, "via 25 Aprile", "5","82020");
			Node node = new Node("123", "Ospedale Frangipane", location);
		
			BloodBag bloodBag   = new BloodBag(bg, codiceFiscale, node );
			assertNotNull(bloodBag);
			
		}
		
		@Test
		public void  ValidityTest_Constructor1_BagBneg() throws ParseException {
			
			BloodGroup bg = BloodGroup.Bneg;
			String codiceFiscale = "PRTGCM45F28P678L";
			Location location = new Location(Country.Italy, Region.Campania, Province.Avellino, City.Avellino, "via 25 Aprile", "5","82020");
			Node node = new Node("123", "Ospedale Frangipane", location);
		
			BloodBag bloodBag   = new BloodBag(bg, codiceFiscale, node );
			assertNotNull(bloodBag);
			
		}
		
		@Test
		public void  ValidityTest_Constructor1_BagBpos() throws ParseException {
			
			BloodGroup bg = BloodGroup.Bpos;
			String codiceFiscale = "PRTGCM45F28P678L";
			Location location = new Location(Country.Italy, Region.Campania, Province.Avellino, City.Avellino, "via 25 Aprile", "5","82020");
			Node node = new Node("123", "Ospedale Frangipane", location);
		
			BloodBag bloodBag   = new BloodBag(bg, codiceFiscale, node );
			assertNotNull(bloodBag);
			
		}
		
		@Test
		public void  ValidityTest_Constructor1_BagABneg() throws ParseException {
			
			BloodGroup bg = BloodGroup.ABneg;
			String codiceFiscale = "PRTGCM45F28P678L";
			Location location = new Location(Country.Italy, Region.Campania, Province.Avellino, City.Avellino, "via 25 Aprile", "5","82020");
			Node node = new Node("123", "Ospedale Frangipane", location);
			
			BloodBag bloodBag   = new BloodBag(bg, codiceFiscale, node );
			assertNotNull(bloodBag);
		
		}
		@Test
		public void  ValidityTest_Constructor1_BagABpos() throws ParseException {
			
			BloodGroup bg = BloodGroup.ABpos;
			String codiceFiscale = "PRTGCM45F28P678L";
			Location location = new Location(Country.Italy, Region.Campania, Province.Avellino, City.Avellino, "via 25 Aprile", "5","82020");
			Node node = new Node("123", "Ospedale Frangipane", location);
		
			BloodBag bloodBag   = new BloodBag(bg, codiceFiscale, node );
			assertNotNull(bloodBag);
		}
		@Test
		public void  ValidityTest_Constructor1_BagZeroneg() throws ParseException {
			
			BloodGroup bg = BloodGroup.ZEROneg;
			String codiceFiscale = "PRTGCM45F28P678L";
			Location location = new Location(Country.Italy, Region.Campania, Province.Avellino, City.Avellino, "via 25 Aprile", "5","82020");
			Node node = new Node("123", "Ospedale Frangipane", location);
		
			BloodBag bloodBag   = new BloodBag(bg, codiceFiscale, node );
			assertNotNull(bloodBag);
		}
		@Test
		public void  ValidityTest_Constructor1_BagZeropos() throws ParseException {
			
			BloodGroup bg = BloodGroup.ZEROpos;
			String codiceFiscale = "PRTGCM45F28P678L";
			Location location = new Location(Country.Italy, Region.Campania, Province.Avellino, City.Avellino, "via 25 Aprile", "5","82020");
			Node node = new Node("123", "Ospedale Frangipane", location);
		
			BloodBag bloodBag   = new BloodBag(bg, codiceFiscale, node );
			assertNotNull(bloodBag);
			
		}

		
		//JUnit test per il secondo Costruttore presente nella classe BloodBag
		
		
		@Test
		public void  ValidityTest_Constructor2_BagZeropos() throws ParseException {
			
			BloodGroup bg = BloodGroup.ZEROpos;
			String codiceFiscale = "PRTGCM45F28P678L";
			Location location = new Location(Country.Italy, Region.Campania, Province.Avellino, City.Avellino, "via 25 Aprile", "5","82020");
			Node node = new Node("123", "Ospedale Frangipane", location);
		
			BloodBag bloodBag   = new BloodBag(bg, codiceFiscale, node );
			assertNotNull(bloodBag);
			
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
