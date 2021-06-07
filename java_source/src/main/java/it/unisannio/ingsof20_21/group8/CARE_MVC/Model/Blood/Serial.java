package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

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


public class Serial implements Comparable<Serial>{
    private final static String serialmatrix;
    private static int lastdate;
    private static int counter;
    private static Date dNow = new Date();
    //private static SimpleDateFormat ft = new SimpleDateFormat(Constants.DATE_FORMAT);
    private static String currentDate_aaaaMMdd = Constants.dateFormat.format(dNow);
    private static String filesettings=Constants.SERIAL_SETTINGS_FILENAME_RELATIVEPATH;

    static {
        Properties loadProps = new Properties();
        try {
            loadProps.loadFromXML(new FileInputStream(filesettings));
        } catch(FileNotFoundException ex){
            InitSettings.initXML();
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
    	// viene usato sia dal JUnit Test, ma viene adoperato anche per generare un oggetto Serial da ricercare.
    	validateSerial(s);
		serial = s;
		Serial.updateSerial();  // @TODO *** Hermann: inutile e pericoloso!
	}

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
    	String regex_valid_pattern = "^IT-\\w{2}\\d{6}-("
    								+ BloodGroup.delimitedValues("|")
    								+ ")-\\d{8}-\\d{4}$";
    	System.out.println(regex_valid_pattern);
    	boolean b = s.matches(regex_valid_pattern);
    	assert !b;
    	return b;
    }
    
    public String toString() {
        return serial;
    }

    public int hashCode() {
    	int h = serial.hashCode();
    	/*
    	h = 31 * lastdate + h;
    	h = 31 * counter + h;
    	*/
    	return h;
    }
    
    public boolean equals (Object o) {  	
    	if(o.getClass()!= Serial.class) return false;
		return ((Serial) o ).serial.equals(this.serial);
	}
    
    public int compareTo(Serial serial) {
    	return this.dNow.compareTo(dNow);
    }

    private final String serial;
}
