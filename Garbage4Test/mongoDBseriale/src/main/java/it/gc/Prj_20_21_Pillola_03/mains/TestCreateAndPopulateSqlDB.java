package it.gc.Prj_20_21_Pillola_03.mains;

import java.util.Random;

import it.gc.Prj_20_21_Pillola_03.persistence.DataManager;
import it.gc.Prj_20_21_Pillola_03.persistence.MySqlDataManager;
import it.gc.Prj_20_21_Pillola_03.sacca.GruppoSanguigno;
import it.gc.Prj_20_21_Pillola_03.sacca.Sacca;
import it.gc.Prj_20_21_Pillola_03.sacca.Seriale;

public class TestCreateAndPopulateSqlDB {

	public static void main(String[] args) {
		DataManager dm = new MySqlDataManager();
		dm.createDB();
		
		final int NUMSACCHE=15;
	    GruppoSanguigno gruppi[] = GruppoSanguigno.values();
		Random generatore = new Random();
		Sacca s;
		for (int i=0; i<NUMSACCHE; i++) {
			s = new Sacca(gruppi[generatore.nextInt(gruppi.length)]);
			dm.addSacca(s);
			System.out.println("Added sacca: "+s);			
		}		
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
		    public void run() {
		        Seriale.updateSettings();
		    }
		}));

	}

}
