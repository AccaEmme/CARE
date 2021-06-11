package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood;

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

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Constants;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.InitSettings;

/*
 * Ogni seriale deve identificare univocamente una sacca di sangue BloodBag.
 * L'univocità tra le sacche di differenti strutture è definita nell'autodeterminazione del codice seriale, mentre l'univocità tra le sacche della stessa struttura è definita in base al giorno e al contatore.
 * Il contatore è un progressivo dipendente dalla data, ovvero la prima sacca del nuovo giorno ha progressivo 1.
 * Il seriale pertanto oltre a preservare l'univocità delle sacche, risulta autoesplicativo di informazioni immediate.
 * Il seriale non prevede un codice regionale, in quanto il concetto di regione è puramente astratto, la sede centrale non è altro che una sede con un codice provinciale con poteri, funzioni e dunque autorizzazioni diverse. 
 * 
 * Il seriale è composto dai seguenti codici:
 * 	[IT]
 *  -
 *  [NA] provincia
 *  [206] codice struttura
 *  [000] eventuale codice uffici interni 3 cifre
 *  -
 *  [Apos] gruppo
 *  -
 *  [20210416] aaaa mm gg di etichettatura, si presume sia contestuale al prelievo; è noto che la sacca di sangue ha validità di scadenza 1 mese dal prelievo.
 *  -
 *  [0001] progressivo di 4 cifre, si presume che una singola struttura in un giorno non riesca a raccogliere più di 9999 sacche
 *  
 *  Esempio seriale: IT-NA206000-Apos-20210416-0001
 *  La dimensione dei caratteri assunta dal seriale può essere esclusivamente di:  
 *   - 30 caratteri
 *   - 31 caratteri
 *   - 33 caratteri
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
            
        	InitSettings.initSerialXML( initserialmatrix );
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

    
    public Serial(BloodGroup bloodgroup) {
        Serial.counter = (Integer.parseInt(currentDate_aaaaMMdd) > lastdate)? 0 : counter ;	//if today > lastdate -> counter = 0 else counter++

        serial = serialmatrix+
                "-"+bloodgroup+
                "-"+currentDate_aaaaMMdd+
                "-"+(
                (new DecimalFormat("0000")).format( counter++ )
        );
        Serial.updateSerial();
    }
    
    public Serial(String s) {
		// viene usato sia dal JUnit Test, ma viene adoperato anche per generare un oggetto Serial da ricercare, ma in questo caso non deve aggiornare il seriale
    	validateSerial(s);
    	this.serial = s;
	}


    public String getSerial() {
    	return this.serial;
    }
    
    private void setSerial() {}
    
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
    
    public static boolean validateSerial(String s) {
    	if( !s.matches( Constants.RegexSerial ) ) 
    		throw new IllegalArgumentException( Constants.ExceptionIllegalArgument_SerialNotValid+s ); // return false; 
    	return true;
    	// *** Hermann: In questo caso ho chiesto un parere anche alla community Java su telegram, c'è forte diatriba se preferibile lanciare una eccezione o gestire il booleano. Entrambi non sembra una scelta condivisa. https://softwareengineering.stackexchange.com/questions/330824/function-returning-true-false-vs-void-when-succeeding-and-throwing-an-exception
    }
    
    public String toString() {
        return serial;
    }
    
    public boolean equals(Object o) {
    	if(o.getClass() != Serial.class) return false;
    	
    	Serial s = (Serial) o;
		return serial.equals( s.getSerial() );
	}
}


