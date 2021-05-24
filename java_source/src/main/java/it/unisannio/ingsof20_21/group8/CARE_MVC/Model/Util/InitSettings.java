package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class InitSettings {
	public static void main(String[] args) {
		initXML();
		
	}
	
	public static void initXML() {
		String filesettings = Constants.SERIAL_SETTINGS_FILENAME_RELATIVEPATH;
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
        
        do {
            System.out.println("Nazionalita' [IT]:");
            nationality = sc.nextLine();
        } while(nationality.length() <0 || nationality.length()>2 );

        System.out.println("Provincia [NA]:");
        prov = sc.nextLine();

        System.out.println("Codice Struttura [206]:");
        codstr = sc.nextLine();

        System.out.println("Eventuale codice ufficio interno [000]:");
        codint = sc.nextLine();
        sc.close();
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
                + "<entry key=\"lastdate\">"+19000101+"</entry>\r\n"
                + "<entry key=\"counter\">"+0+"</entry>\r\n"
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
