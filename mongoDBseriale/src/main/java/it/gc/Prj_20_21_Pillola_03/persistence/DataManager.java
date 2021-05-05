package it.gc.Prj_20_21_Pillola_03.persistence;

import java.util.List;

import it.gc.Prj_20_21_Pillola_03.sacca.GruppoSanguigno;
import it.gc.Prj_20_21_Pillola_03.sacca.Sacca;

public interface DataManager {
	void createDB();
	void dropDB();
	void addSacca(Sacca s);
	List<Sacca> getSacche(GruppoSanguigno g);
	void deleteSacca(String s);
}
