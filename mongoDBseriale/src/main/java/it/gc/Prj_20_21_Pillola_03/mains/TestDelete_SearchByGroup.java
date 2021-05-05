package it.gc.Prj_20_21_Pillola_03.mains;


import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.gc.Prj_20_21_Pillola_03.persistence.DataManager;
import it.gc.Prj_20_21_Pillola_03.persistence.MyMongoDataManager;
import it.gc.Prj_20_21_Pillola_03.sacca.GruppoSanguigno;
import it.gc.Prj_20_21_Pillola_03.sacca.Sacca;

public class TestDelete_SearchByGroup {

	public static void main(String[] args) {
		
		Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
		mongoLogger.setLevel(Level.SEVERE);
		DataManager dm = new MyMongoDataManager();
		
		/* 
		 * deleteSacca elimina il Documento con il seriale scelto
		 */
		
		
		Scanner input = new Scanner(System.in);
		System.out.println("di quale gruppo?");
		String c= input.nextLine();
				
		GruppoSanguigno g = GruppoSanguigno.valueOf(c);
		
		List<Sacca> l= dm.getSacche(g);
		System.out.println("#####################");
		for(Sacca s : l) {
		System.out.println(s.getSeriale());
		System.out.println(s.getGruppo());
		System.out.println("---------------------");
		}
		System.out.println("#####################");
		
		/* 
		 * deleteSacca elimina il Documento con il seriale scelto
		 */
	    /* dm.deleteSacca("NA10-000000000133");*/

}
}