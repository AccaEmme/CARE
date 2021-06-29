package it.gc.Prj_20_21_Pillola_03.mains;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.gc.Prj_20_21_Pillola_03.persistence.DataManager;
import it.gc.Prj_20_21_Pillola_03.persistence.MyMongoDataManager;
import it.gc.Prj_20_21_Pillola_03.persistence.MySqlDataManager;
import it.gc.Prj_20_21_Pillola_03.sacca.GruppoSanguigno;
import it.gc.Prj_20_21_Pillola_03.sacca.Sacca;
import it.gc.Prj_20_21_Pillola_03.sacca.Seriale;

public class TestVergineDB {

	public static void main(String[] args) {
		
		Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
		mongoLogger.setLevel(Level.SEVERE);

		DataManager dmSQL = new MySqlDataManager();
		DataManager dmMONGO = new MyMongoDataManager();
		
		dmSQL.dropDB();
		dmMONGO.dropDB();
		
		dmSQL.createDB();
		dmMONGO.createDB();

		final int NUMSACCHE=15;
	    GruppoSanguigno gruppi[] = GruppoSanguigno.values();
		Random generatore = new Random();
		Sacca s;
		for (int i=0; i<NUMSACCHE; i++) {
			s = new Sacca(gruppi[generatore.nextInt(gruppi.length)]);
			dmSQL.addSacca(s);
			dmMONGO.addSacca(s);
			System.out.println("Added sacca: "+s);			
		}		
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
		    public void run() {
		        Seriale.updateSettings();
		    }
		}));

	}

}
