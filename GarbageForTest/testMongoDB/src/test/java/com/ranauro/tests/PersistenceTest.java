package com.ranauro.tests;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

import com.ranauro.persistence.DataManager;
import com.ranauro.persistence.MyMongoDataManager;
import com.ranauro.persistence.MySqlDataManager;
import com.ranauro.sangue.GruppoSanguigno;
import com.ranauro.sangue.Sacca;
import com.ranauro.sangue.Seriale;
import org.junit.Before;
import org.junit.Test;


public class PersistenceTest {

    @Before
    public void s() {
        DataManager dmSQL = new MySqlDataManager();
        DataManager dmMONGO = new MyMongoDataManager();

        dmSQL.dropDB();
        dmMONGO.dropDB();

        dmSQL.createDB();
        dmMONGO.createDB();

        final int NUMSACCHE=5;
        GruppoSanguigno gruppi[] = GruppoSanguigno.values();
        Random generatore = new Random();
        Sacca s;
        for (int i=0; i<NUMSACCHE; i++) {
            s = new Sacca(gruppi[generatore.nextInt(gruppi.length)]);
            dmSQL.addSacca(s);
            dmMONGO.addSacca(s);
        }
    }


    @Test
    public void queryBothDBs() {

        for (int i=0; i < GruppoSanguigno.values().length; i++) {
            GruppoSanguigno gs = GruppoSanguigno.values()[i];
            List<Sacca> lSQL = (new MySqlDataManager()).getSacche(gs);
            List<Sacca> lMONGO = (new MyMongoDataManager()).getSacche(gs);
            assertTrue(lMONGO.containsAll(lSQL));
            assertTrue(lSQL.containsAll(lMONGO));
        }

        Seriale.updateSettings();

    }

}
