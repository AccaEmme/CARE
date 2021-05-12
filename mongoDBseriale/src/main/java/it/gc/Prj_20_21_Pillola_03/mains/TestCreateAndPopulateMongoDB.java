package it.gc.Prj_20_21_Pillola_03.mains;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.gc.Prj_20_21_Pillola_03.persistence.DataManager;
import it.gc.Prj_20_21_Pillola_03.persistence.MyMongoDataManager;
import it.gc.Prj_20_21_Pillola_03.sacca.GruppoSanguigno;
import it.gc.Prj_20_21_Pillola_03.sacca.Sacca;
import it.gc.Prj_20_21_Pillola_03.sacca.Seriale;

public class TestCreateAndPopulateMongoDB {

	public static void main(String[] args) {
		
		Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
		mongoLogger.setLevel(Level.SEVERE);
		
		DataManager dm = new MyMongoDataManager();
		dm.createDB();
		
		final int NUMSACCHE=5;
	    GruppoSanguigno gruppi[] = GruppoSanguigno.values();
		Random generatore = new Random();
		Sacca s;
		for (int i=0; i<NUMSACCHE; i++) {
			s = new Sacca(gruppi[generatore.nextInt(gruppi.length)]);
			dm.addSacca(s);
			System.out.println("Added sacca: "+s);			
		}		
		/*  aggiorna */
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
		    public void run() {
		        Seriale.updateSeriale();
		    }
		}));

	}

}
