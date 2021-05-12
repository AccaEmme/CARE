package it.gc.Prj_20_21_Pillola_03.mains;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.gc.Prj_20_21_Pillola_03.persistence.MyMongoDataManager;
import it.gc.Prj_20_21_Pillola_03.sacca.GruppoSanguigno;
import it.gc.Prj_20_21_Pillola_03.sacca.Sacca;

public class TestMongoQuery {

	public static void main(String[] args) {
		
		Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
		mongoLogger.setLevel(Level.SEVERE);
		
		GruppoSanguigno gs = GruppoSanguigno.values()[2];
		List<Sacca> l = (new MyMongoDataManager()).getSacche(gs);
		for (Sacca s : l)
			System.out.println(s);
	}

}
