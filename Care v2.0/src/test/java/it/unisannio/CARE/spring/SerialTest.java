package it.unisannio.CARE.spring;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import it.unisannio.CARE.Model.BloodBag.BloodGroup;
import it.unisannio.CARE.Model.BloodBag.Serial;
import it.unisannio.CARE.Model.Util.Constants;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
/*
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;*/

import it.unisannio.CARE.Model.Util.Password;

import org.junit.jupiter.params.ParameterizedTest;


/**
 * Unit test for Serial.
 */
public class SerialTest {
	public static String path 			= Constants.SERIAL_SETTINGS_RELATIVEPATH;
	public static String filename 			= Constants.SERIAL_SETTINGS_FILENAME;
	public static final String tempfilename = "serial_settings_temp.xml";
	
	@BeforeClass
	public static void createFile() throws IOException {
		String path1 = path+filename;
		File from = new File(path1);
		String path2 = path+tempfilename;
		File to = new File(path2);
        Files.copy(from.toPath(), to.toPath(), StandardCopyOption.REPLACE_EXISTING);
		from.delete();
	}
	
	@AfterClass
	public static void regeneratonFile() throws IOException {
		String path1 = path+tempfilename;
		File from = new File(path1);
		String path2 = path+filename;
		File to = new File(path2);
        Files.copy(from.toPath(), to.toPath(), StandardCopyOption.REPLACE_EXISTING);
	}
	
    @Test(expected = IllegalArgumentException.class)
    public void serialDefineWrong() {
    	BloodGroup g= BloodGroup.valueOf("ads");
    	new Serial(g);
    }
   
    @ParameterizedTest(name = "")
	@Test(expected = NullPointerException.class)
    public void serialDefineNull(String bloodGroup) {
	   BloodGroup g= BloodGroup.valueOf(bloodGroup);
   	   new Serial(g);
    }   
   
	/*
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
    */
    
    /*
    @ParameterizedTest(name = "#{index} - Run serial test with valid bloodGroup = {0}")
    @MethodSource("bloodGroupWellDefined")
    //@MethodSource(value = {"Apos", "Aneg"})
    //@Test
    public void (String bloodGroup) {
    	BloodGroup g= BloodGroup.valueOf( bloodGroup );
    	Serial s = new Serial(g);
    	assertTrue((s.equals(new Serial(s.toString()))));
    }
    */
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
    
    @Test
	public void testFile() {
	   File f = new File(path+filename);
	   assertTrue( f.exists() );
	}
    
}