package it.gc.Prj_20_21_Pillola_03.mains;

import java.util.logging.Level;
import java.util.logging.Logger;

import it.gc.Prj_20_21_Pillola_03.persistence.DataManager;
import it.gc.Prj_20_21_Pillola_03.persistence.MyMongoDataManager;


public class TestDropMongoDB {

	public static void main(String[] args) {

		Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
		mongoLogger.setLevel(Level.SEVERE);

		DataManager dm = new MyMongoDataManager();
		dm.dropDB();

	}

}
