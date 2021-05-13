package it.gc.Prj_20_21_Pillola_03.sacca;



import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * Unit test for seriale.
 */
public class SerialeTest 
{
	

		
		@BeforeClass
		public static void createFile() throws IOException {
			String path1 = "localsettings.xml";
			File to = new File(path1);
			String path2 = "localsettings_temp.xml";
			File from = new File(path2);
	        Files.copy(to.toPath(), from.toPath(), StandardCopyOption.REPLACE_EXISTING);
			to.delete();
		
		}
		@AfterClass
		public static void regeneratonFile() throws IOException {
			String path1 = "localsettings_temp.xml";
			File to = new File(path1);
			String path2 = "localsettings.xml";
			File from = new File(path2);
	        Files.copy(to.toPath(), from.toPath(), StandardCopyOption.REPLACE_EXISTING);
		
		
		}
    		@Test
		public void testFile() {
			String path = "localsettings.xml";
			File f = new File(path);
		   assertTrue(f.exists());
    		}
  /*  @Test
    public void fileCreationSerialmatrix() 
    {
    	
		try {
			Method m = Seriale.class.getDeclaredMethod("initXML", (Class<?>[]) null);
			m.setAccessible(true);
			try {
				m.invoke("IT-NA206000");
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
    	
    }*/
	
   @Test(expected = NullPointerException.class)
    public void serialDefineNull()
    {
	   GruppoSanguigno g= GruppoSanguigno.valueOf(null);
   	    new Seriale(g);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void serialDefineWrong()
    {
    	 GruppoSanguigno g= GruppoSanguigno.valueOf("ads");
    	    new Seriale(g);
    }
   
    @Test
    public void serialDefineGood1()
    {
    	GruppoSanguigno g= GruppoSanguigno.valueOf("Ap") ;
    	Seriale s = new Seriale(g);
    	assertTrue((s.equals(new Seriale(s.toString()))));
    }
    
    
    @Test
    public void serialDefineGood2()
    {
    	GruppoSanguigno g= GruppoSanguigno.valueOf("Am") ;
    	Seriale s = new Seriale(g);
    	assertTrue((s.equals(new Seriale(s.toString()))));
    }
    @Test
    public void serialDefineGood3()
    {
    	GruppoSanguigno g= GruppoSanguigno.valueOf("Bp") ;
    	Seriale s = new Seriale(g);
    	assertTrue((s.equals(new Seriale(s.toString()))));
    }
    @Test
    public void sserialDefineGood4()
    {
    	GruppoSanguigno g= GruppoSanguigno.valueOf("Bm") ;
    	Seriale s = new Seriale(g);
    	assertTrue((s.equals(new Seriale(s.toString()))));
    }
    @Test
    public void serialDefineGood5()
    {
    	GruppoSanguigno g= GruppoSanguigno.valueOf("ZEROp") ;
    	Seriale s = new Seriale(g);
    	assertTrue((s.equals(new Seriale(s.toString()))));
    }
    @Test
    public void serialDefineGood6()
    {
    	GruppoSanguigno g= GruppoSanguigno.valueOf("ZEROm") ;
    	Seriale s = new Seriale(g);
    	assertTrue((s.equals(new Seriale(s.toString()))));
    }
    @Test
    public void serialDefineGood7()
    {
    	GruppoSanguigno g= GruppoSanguigno.valueOf("ABm") ;
    	Seriale s = new Seriale(g);
    	assertTrue((s.equals(new Seriale(s.toString()))));
    }
    @Test
    public void serialDefineGood8()
    {
    	GruppoSanguigno g= GruppoSanguigno.valueOf("ABp") ;
    	Seriale s = new Seriale(g);
    	assertTrue((s.equals(new Seriale(s.toString()))));
    }
    
    
	
    
}

