package it.unisannio.CARE.spring;

import it.unisannio.CARE.model.util.Constants;
import it.unisannio.CARE.spring.BloodBagController;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FollenTests {
    @Test
    public void testTimeStamp(){
        System.out.println(new Date().getTime());
    }

    @Test
    public void dateTests(){
        SimpleDateFormat format = Constants.dateFormatString;
        String dateStr = format.format(new Date());
        System.out.println(dateStr);
    }

}
