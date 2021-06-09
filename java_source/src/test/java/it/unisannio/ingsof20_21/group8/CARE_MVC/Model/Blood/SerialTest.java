package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Constants;
import static org.junit.Assert.assertTrue;


import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.junit.AfterClass;

import org.junit.BeforeClass;
import org.junit.Test;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodGroup;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Serial;


/**
 * Unit test for Serial.
 */
public class SerialTest {
	public static String path = Constants.SERIAL_SETTINGS_RELATIVEPATH;
	public static String filename = Constants.SERIAL_SETTINGS_FILENAME;
	
	@BeforeClass
	public static void createFile() throws IOException {
		String path1 = path+filename;
		File from = new File(path1);
		String path2 = path+"serial_settings_temp.xml";
		File to = new File(path2);
        Files.copy(from.toPath(), to.toPath(), StandardCopyOption.REPLACE_EXISTING);
		from.delete();
	}
	
	@AfterClass
	public static void regeneratonFile() throws IOException {
		String path1 = path+"serial_settings_temp.xml";
		File from = new File(path1);
		String path2 = path+filename;
		File to = new File(path2);
        Files.copy(from.toPath(), to.toPath(), StandardCopyOption.REPLACE_EXISTING);
	}
	
	@Test
	public void testFile() {
		File f = new File(path+filename);
	   assertTrue(f.exists());
	}
	
   @Test(expected = NullPointerException.class)
    public void serialDefineNull() {
	   BloodGroup g= BloodGroup.valueOf(null);
   	   new Serial(g);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void serialDefineWrong() {
    	BloodGroup g= BloodGroup.valueOf("ads");
    	new Serial(g);
    }
   
    @Test
    public void serialBloodGroupWellDefinedApos() {
    	BloodGroup g= BloodGroup.valueOf("Apos");
    	Serial s = new Serial(g);
    	assertTrue( (s.equals(new Serial(s.toString()))) );
    }
    
    @Test
    public void serialBloodGroupWellDefinedAneg() {
    	BloodGroup g= BloodGroup.valueOf("Aneg");
    	Serial s = new Serial(g);
    	assertTrue((s.equals(new Serial(s.toString()))));
    }
    
    @Test
    public void serialBloodGroupWellDefinedBpos() {
    	BloodGroup g= BloodGroup.valueOf("Bpos");
    	Serial s = new Serial(g);
    	assertTrue((s.equals(new Serial(s.toString()))));
    }
    
    @Test
    public void serialBloodGroupWellDefinedBneg() {
    	BloodGroup g= BloodGroup.valueOf("Bneg");
    	Serial s = new Serial(g);
    	assertTrue((s.equals(new Serial(s.toString()))));
    }
    
    @Test
    public void serialBloodGroupWellDefinedZEROpos() {
    	BloodGroup g= BloodGroup.valueOf("ZEROpos");
    	Serial s = new Serial(g);
    	assertTrue((s.equals(new Serial(s.toString()))));
    }
    
    @Test
    public void serialBloodGroupWellDefinedZEROneg() {
    	BloodGroup g= BloodGroup.valueOf("ZEROneg");
    	Serial s = new Serial(g);
    	assertTrue((s.equals(new Serial(s.toString()))));
    }

    @Test
    public void serialBloodGroupWellDefinedABpos() {
    	BloodGroup g= BloodGroup.valueOf("ABpos");
    	Serial s = new Serial(g);
    	assertTrue((s.equals(new Serial(s.toString()))));
    }
    
    @Test
    public void serialBloodGroupWellDefinedABneg() {
    	BloodGroup g= BloodGroup.valueOf("ABneg");
    	Serial s = new Serial(g);
    	assertTrue((s.equals(new Serial(s.toString()))));
    }
}