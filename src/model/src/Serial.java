import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

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
 *  [APOS] gruppo
 *  -
 *  [20210416] aaaa mm gg di etichettatura, si presume sia contestuale al prelievo; è noto che la sacca di sangue ha validità di scadenza 1 mese dal prelievo.
 *  -
 *  [0001] progressivo di 4 cifre, si presume che una singola struttura in un giorno non riesca a raccogliere più di 9999 sacche
 *  
 *  Esempio seriale: IT-NA206000-APOS-20210416-0001
 */

public class Serial {
	private final static String serialmatrix;
	private static int lastdate;
	private static int counter;
	private static Date dNow = new Date();
	private static SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
	private static String currentDate_aaaaMMdd = ft.format(dNow);
	
	static {
		Properties loadProps = new Properties();
		try {
			loadProps.loadFromXML(new FileInputStream("localsettings.xml"));
		} catch(FileNotFoundException ex){
			Scanner sc = new Scanner(System.in);
			String nationality;
		
			do {
				System.out.println("Nazionalità [IT]:");
				nationality = sc.nextLine();
			} while(nationality.length() <0 || nationality.length()>2 );
			
			System.out.println("Provincia [NA]:");
			String prov = sc.nextLine();
			
			System.out.println("Codice Struttura [206]:");
			String codstr = sc.nextLine();
			
			System.out.println("Eventuale codice ufficio interno [000]:");
			String codint = sc.nextLine();

			String serialmatrix = nationality+"-"+prov+codstr+codint;
			initXML(serialmatrix);
			try {
				loadProps.loadFromXML(new FileInputStream("localsettings.xml"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	serialmatrix = 	loadProps.getProperty("serialmatrix");
	lastdate = 		Integer.valueOf(loadProps.getProperty("lastdate")); 
	counter = 		Integer.valueOf(loadProps.getProperty("counter"));
	}
	

	public Serial(Blood bloodgroup) {
		Serial.counter=(Integer.parseInt(currentDate_aaaaMMdd) > lastdate)?1:counter++;	//if today > lastdate -> counter = 0 else counter++
				
		serial = serialmatrix+
				"-"+bloodgroup+
				"-"+currentDate_aaaaMMdd+
				"-"+(
						(new DecimalFormat("0000")).format( counter++ )	
					);
		Serial.updateSerial();
	}
	
	private static boolean initXML(String serialmatrix) {
		File f;
		String data;
		FileOutputStream fos;
		BufferedOutputStream bos;
		
		f = new File("localsettings.xml");
		try {
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
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
			System.out.println("Data written to file successfully.");
		return true;
	}
	
	public String toString() {
		return serial;
	}
	
	public static void updateSerial() {	
		Properties saveProps = new Properties();
		saveProps.setProperty("serialmatrix", serialmatrix);
		saveProps.setProperty("lastdate", currentDate_aaaaMMdd );
		saveProps.setProperty("counter", Integer.toString(counter));
		
		try {
			saveProps.storeToXML(new FileOutputStream("localsettings.xml"), "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private final String serial;
}
