package it.gc.Prj_20_21_Pillola_03.mains;

import it.gc.Prj_20_21_Pillola_03.persistence.DataManager;
import it.gc.Prj_20_21_Pillola_03.persistence.MySqlDataManager;

public class TestDropSqlDB {

	public static void main(String[] args) {
		DataManager dm = new MySqlDataManager();
		dm.dropDB();
	}

}
