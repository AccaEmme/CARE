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
	
	@Test
	public void testFile() {
	   File f = new File(path+filename);
	   assertTrue(f.exists()); 
	}
	
	
	@Test(expected = FileNotFoundException.class)
	public void InvalidityTest_File() {
	   File f = new File(path+"serial.xml");
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
	
	
	/*
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
    }  */ 


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