package it.unisannio.ingsof20_21.group8.CARE_MVC;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Constants;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;


import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;

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
	
	
	@Test (expected = NullPointerException.class)
	public void donatorCFNULL() throws ParseException {
		BloodGroup bg = BloodGroup.valueOf("Bneg");
		new BloodBag(bg, null);
	}
	
	/*
	 * test sbagliato perché in verità sto testando l'enum BloodGroup
	 */
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
		String note = "non so cosa scrivere";
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
		String note = "non so cosa scrivere";
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
		String note = "non so cosa scrivere";
		BloodGroup bg = BloodGroup.valueOf("ZEROneg");
		BloodBag bb1 = new BloodBag(bg, codF);
		BloodBag bb2 = new BloodBag(bg, codF, note);
		System.out.println(bb1.toString());
		System.out.println(bb2.toString());
		assertFalse(bb1.equals(bb2));
	}
	
	@Test
	public void testDates() throws ParseException{
		String codF = "CRSDLCER86BH0911";
		BloodGroup bg = BloodGroup.valueOf("Bneg");
		BloodBag bb = new BloodBag(bg, codF);
		assertEquals(bb.getCreationDate(), bb.getExpirationDate());
	}
	
	
	
}
