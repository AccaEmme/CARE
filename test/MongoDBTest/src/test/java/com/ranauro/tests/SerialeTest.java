package com.ranauro.tests;

import com.ranauro.sangue.Seriale;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class SerialeTest {

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
