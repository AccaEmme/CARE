package it.unisannio.ingsof20_21.group8.CARE_MVC;


import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.junit.AfterClass;

import org.junit.BeforeClass;
import org.junit.Test;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Blood;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Serial;


/**
 * Unit test for Serial.
 */
public class SerialTest {
		public static String path = "./localsettings/";
		public static String filename = "serial_settings.xml";

		
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
    public void serialDefineNull()
    {
	   Blood g= Blood.valueOf(null);
   	    new Serial(g);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void serialDefineWrong()
    {
    	 Blood g= Blood.valueOf("ads");
    	    new Serial(g);
    }
   
    @Test
    public void serialDefineGood1()
    {
    	Blood g= Blood.valueOf("Apos") ;
    	Serial s = new Serial(g);
    	assertTrue((s.equals(new Serial(s.toString()))));
    }
    
    
    @Test
    public void serialDefineGood2()
    {
    	Blood g= Blood.valueOf("Aneg") ;
    	Serial s = new Serial(g);
    	assertTrue((s.equals(new Serial(s.toString()))));
    }
    @Test
    public void serialDefineGood3()
    {
    	Blood g= Blood.valueOf("Bpos") ;
    	Serial s = new Serial(g);
    	assertTrue((s.equals(new Serial(s.toString()))));
    }
    @Test
    public void serialDefineGood4()
    {
    	Blood g= Blood.valueOf("Bneg") ;
    	Serial s = new Serial(g);
    	assertTrue((s.equals(new Serial(s.toString()))));
    }
    @Test
    public void serialDefineGood5()
    {
    	Blood g= Blood.valueOf("ZEROpos") ;
    	Serial s = new Serial(g);
    	assertTrue((s.equals(new Serial(s.toString()))));
    }
    @Test
    public void serialDefineGood6()
    {
    	Blood g= Blood.valueOf("ZEROneg") ;
    	Serial s = new Serial(g);
    	assertTrue((s.equals(new Serial(s.toString()))));
    }
    @Test
    public void serialDefineGood7()
    {
    	Blood g= Blood.valueOf("ABneg") ;
    	Serial s = new Serial(g);
    	assertTrue((s.equals(new Serial(s.toString()))));
    }
    @Test
    public void serialDefineGood8()
    {
    	Blood g= Blood.valueOf("ABpos") ;
    	Serial s = new Serial(g);
    	assertTrue((s.equals(new Serial(s.toString()))));
    }
    
    
	
    
}