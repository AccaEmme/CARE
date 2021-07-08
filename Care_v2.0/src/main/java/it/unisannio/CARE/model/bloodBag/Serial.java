package it.unisannio.CARE.model.bloodBag;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import it.unisannio.CARE.model.exceptions.IllegalSerialException;
import it.unisannio.CARE.model.util.Constants;
import it.unisannio.CARE.model.util.XMLHelper;

/** 
 * Class that contains all the methods of the serial
 */

/*
 * Each serial must uniquely identify a BloodBag blood bag.
 * The uniqueness between the bags of different structures is defined in the self-determination of the serial code, while the uniqueness between the bags of the same structure is defined on the basis of the day and the counter.
 * The counter is a progressive dependent on the date, i.e. the first bag of the new day has progressive 1.
 * The serial therefore, in addition to preserving the uniqueness of the bags, is self-explanatory of immediate information.
 * The serial does not provide for a regional code, as the concept of region is purely abstract, the headquarters is nothing more than an office with a provincial code with different powers, functions and therefore authorizations.
 *
 * The serial is made up of the following codes:
 * [IT]
 * -
 * [NA] province
 * [206] facility code
 * [000] 3-digit internal office code, if any
 * -
 * [Apos] group
 * -
 * [20210416] yyyy mm dd labeling, presumed to be contextual to the sampling; it is known that the blood bag has an expiry date of 1 month from collection.
 * -
 * [0001] progressive of 4 digits, it is assumed that a single structure in a day cannot collect more than 9999 bags
 *
 * Serial example: IT-NA206000-Apos-20210416-0001
 * The size of the characters assumed by the serial can only be of:
 * - 30 characters
 * - 31 characters
 * - 33 characters
 */


public class Serial{
    private static int lastdate;
    private static int counter;
    private static Date dNow = new Date();
    //private static SimpleDateFormat ft = new SimpleDateFormat(Constants.DATE_FORMAT);
    private static String currentDate_aaaaMMdd = Constants.dateFormat.format(dNow);
    private static String filesettings=Constants.SERIAL_SETTINGS_FILENAME_RELATIVEPATH;

    private final static String serialmatrix;
    private final String serial;
    
    static {
        Properties loadProps = new Properties(); 
        
        File f = new File(filesettings);
        if(!f.exists())  {
        	Scanner sc = new Scanner(System.in);
            String nationality="", prov="", codstr="", codint="", initserialmatrix="";

            do {
                System.out.println( Constants.InitSettings_askNationality );
                nationality = sc.nextLine();
            } while(nationality.length() <0 || nationality.length()>2 );

            System.out.println( Constants.InitSettings_askProvince );
            prov = sc.nextLine();

            System.out.println( Constants.InitSettings_askCodStr );
            codstr = sc.nextLine();

            System.out.println( Constants.InitSettings_askIntCod );
            codint = sc.nextLine();
            sc.close();
            initserialmatrix = nationality+"-"+prov+codstr+codint;
            
        	XMLHelper.initSerialXML( initserialmatrix );
        }
        	
        try {
            loadProps.loadFromXML(new FileInputStream(filesettings));
        } catch(FileNotFoundException e){
        	e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 

        serialmatrix 	= 	loadProps.getProperty("serialmatrix");
        counter 		= 	Integer.valueOf(loadProps.getProperty("counter"));
        lastdate 		= 	Integer.valueOf(loadProps.getProperty("lastdate"));
    }

    /**
	 **************************************************************************
	 * Manufacturer that logs each bag with a unique identifying value
	 * @param bloodgroup indicates the bag group
	 **************************************************************************
	 */
    public Serial(BloodGroup bloodgroup) {
        Serial.counter = (Integer.parseInt(currentDate_aaaaMMdd) > lastdate)? 0 : counter;	//if today > lastdate -> counter = 0 else counter++

        serial = serialmatrix+ 
                "-"+bloodgroup+
                "-"+currentDate_aaaaMMdd+
                "-"+(
                (new DecimalFormat("0000")).format( counter++ )
        );
        Serial.updateSerial();
    }
    
    /**
	 **************************************************************************
	 * Method used by both the JUnit Test, but is also used for
	 * generate a Serial object to search, in this case it does not have to update the serial
	 * @param s new serial
	 **************************************************************************
	 */
    public Serial(String s) {
    	validateSerial(s);
    	this.serial = s;
	}
 
    /**
	 **************************************************************************
	 * GET method to get the serial
	 * @return returns the serial of the bag
	 **************************************************************************
	 */
    public String getSerial() {
    	return this.serial;
    }
    
    
    /**
	 **************************************************************************
	 * UPDATE method to insert a new serial
	 **************************************************************************
	 */
	private static void updateSerial() {
        Properties saveProps = new Properties();
        saveProps.setProperty("serialmatrix", serialmatrix);
        saveProps.setProperty("lastdate", currentDate_aaaaMMdd );
        saveProps.setProperty("counter", Integer.toString(counter));

        try {
        	FileOutputStream f= new FileOutputStream(filesettings);
            saveProps.storeToXML(f, "");
        	f.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
	/**
	 **************************************************************************
	 * Method for verifying the validity of the serial
	 * @param s indicates the serial to be checked
	 * @exception IllegalArgumentException
	 **************************************************************************
	 */
    public static boolean validateSerial(String s) {
    	if( !s.matches( Constants.RegexSerial ) ) 
    		throw new IllegalSerialException("Il seriale inserito della sacca che si vuole aggiungere non è in un formato valido.", "/bloodbag/add"); // return false; 
    	return true;
    	// *** Hermann: In questo caso ho chiesto un parere anche alla community Java su telegram, c'è forte diatriba se preferibile lanciare una eccezione o gestire il booleano. Entrambi non sembra una scelta condivisa. https://softwareengineering.stackexchange.com/questions/330824/function-returning-true-false-vs-void-when-succeeding-and-throwing-an-exception
    }
    
    /**
	 **************************************************************************
	 * TOSTRING method for bag information
	 * @return returns bag information
	 **************************************************************************
	 */
    public String toString() {
        return serial;
    }
    
    /**
	 **************************************************************************
	 * Method for comparing two objects
	 * @param o the object to be compared is passed as a parameter
	 * @return returns a boolean variable (true / false) for comparison
	 **************************************************************************
	 */
    public boolean equals(Object o) {
    	if(o.getClass() != Serial.class) return false;
    	
    	Serial s = (Serial) o;
		return serial.equals( s.getSerial() );
	}


}


