package it.gc.Prj_20_21_Pillola_03.sacca;



import static org.junit.Assert.assertTrue;

import org.junit.Test;


/**
 * Unit test for simple App.
 */
public class SerialeTest 
{
	
    @Test(expected = AssertionError.class)
    public void shoulDetectNull()
    {
    	new Seriale(null);
    }
    
    @Test(expected = AssertionError.class)
    public void shouldDetectWrongSeparator()
    {
    	new Seriale("BN01x100000000010");
    }

    @Test(expected = AssertionError.class)
    public void shouldDetecTooLobg()
    {
    	new Seriale("BN01-1000000000109");
    }

    @Test(expected = AssertionError.class)
    public void shouldDetecTooShort()
    {
    	new Seriale("BN01-10000");
    }

    
    @Test(expected = AssertionError.class)
    public void shouldDtectNotEndingWithNumber()
    {
    	new Seriale("BN01-10000A000010");
    }

    @Test
    public void shouldDefineGoodSeriale()
    {
    	Seriale s = new Seriale();
    	assertTrue((s.equals(new Seriale(s.toString()))));
    }
    
}
