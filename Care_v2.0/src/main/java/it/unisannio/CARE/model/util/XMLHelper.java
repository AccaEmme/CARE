package it.unisannio.CARE.model.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.*;
import java.io.*;

/**
 * is a helper class for handling XML Properties, with file initialization and value reading methods
 */

public class XMLHelper {
	
	public static void main(String[] args) {
		String nationality="", prov="", codstr="", codint="", serialmatrix="";
		nationality ="IT";
        prov = "NA";
        codstr = "206";
        codint = "000";
        serialmatrix = nationality+"-"+prov+codstr+codint;
		initSerialXML( serialmatrix );
	}

	/**
	************************************************** ************************
	* Method to get properties from an xml file
	* @param xmlfilepath Path of the XML file
	* @return returns NULL if it does not find the path
	* @exception InvalidPropertiesFormatException, FileNotFoundException, IOException
	************************************************** ************************
	*/
	public static Properties getProps(String xmlfilepath) { 
		Properties loadProps = new Properties();
		try {
			loadProps.loadFromXML(new FileInputStream(xmlfilepath));
			return loadProps;
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	************************************************** ************************
	* Method that initializes the XML of the serial
	* @param serialmatrix XML file serial
	* @exception IOException
	* @exception IllegalArgumentException
	************************************************** ************************
	*/
	public static void initSerialXML( String serialmatrix ) {
		String filesettings = Constants.SERIAL_SETTINGS_FILENAME_RELATIVEPATH;

        File f = null;
        String xmlData;
        FileOutputStream fos;
        BufferedOutputStream bos;
        
        /*
        String nationality="", prov="", codstr="", codint="", serialmatrix="";
        Scanner sc = new Scanner(System.in);
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
        serialmatrix = nationality+"-"+prov+codstr+codint;
       */
        
        try {
        	f = new File(filesettings);
        	if( f.exists() ) throw new IllegalArgumentException( "File already exists." );
        	f.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
                + "<!DOCTYPE properties SYSTEM \"http://java.sun.com/dtd/properties.dtd\">\r\n"
                + "<properties>\r\n"
                + "<entry key=\"serialmatrix\">"+serialmatrix+"</entry>\r\n"
                + "<entry key=\"lastdate\">"+19000101+"</entry>\r\n"
                + "<entry key=\"counter\">"+0+"</entry>\r\n"
                + "</properties>\r\n"
                + "";

        try {
            fos = new FileOutputStream(f);
            bos = new BufferedOutputStream(fos);
            byte[] bytes = xmlData.getBytes();
            bos.write(bytes);
            bos.close();
            fos.close();
            System.out.println("Data written to file "+filesettings+" successfully.");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	
	/**
	************************************************** ************************
	* Method that initializes the XML of the serial given a specific path (Utiel also for Junit Test)
	* @param x specifies the counter to start from
	* @param filesettings specifies the path where to save the setting file
	* @exception IOException
	************************************************** ************************
	*/
	public static void initXMLz(int x, String filesettings) {
        Scanner sc = new Scanner(System.in);
        String nationality, prov, codstr, codint, serialmatrix;

        File f = null;
        String data;
        FileOutputStream fos;
        BufferedOutputStream bos;
        
        /* @TODO: ***
        if(!f.exists()) {
        	f.createNewFile();
        }
        */
        
        nationality ="IT";
        prov = "NA";
        codstr = "206";
        codint = "000";
        serialmatrix = nationality+"-"+prov+codstr+codint;
        
        try {
        	f = new File(filesettings);
        	f.createNewFile();
            		
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        data = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
                + "<!DOCTYPE properties SYSTEM \"http://java.sun.com/dtd/properties.dtd\">\r\n"
                + "<properties>\r\n"
                + "<entry key=\"serialmatrix\">"+serialmatrix+"</entry>\r\n"
                + "<entry key=\"lastdate\">"+20210522+"</entry>\r\n"
                + "<entry key=\"counter\">"+x+"</entry>\r\n"
                + "</properties>\r\n"
                + "";

        try {
            fos = new FileOutputStream(f);
            bos = new BufferedOutputStream(fos);
            byte[] bytes = data.getBytes();
            bos.write(bytes);
            bos.close();
            fos.close();
            System.out.println("Data written to file "+filesettings+" successfully.");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}	
}