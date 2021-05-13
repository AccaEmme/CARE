package it.gc.Prj_20_21_Pillola_03.mains;


import java.util.List;

import it.gc.Prj_20_21_Pillola_03.persistence.MySqlDataManager;
import it.gc.Prj_20_21_Pillola_03.sacca.GruppoSanguigno;
import it.gc.Prj_20_21_Pillola_03.sacca.Sacca;

public class TestSqlQuery {

	public static void main(String[] args) {
		GruppoSanguigno gs = GruppoSanguigno.values()[1];
		List<Sacca> l = (new MySqlDataManager()).getSacche(gs);
		for (Sacca s : l)
			System.out.println(s);

	}

}
