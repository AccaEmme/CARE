package com.ranauro.tests;

import com.ranauro.persistence.DataManager;
import com.ranauro.persistence.MyMongoDataManager;
import com.ranauro.sangue.GruppoSanguigno;
import com.ranauro.sangue.Sacca;
import com.ranauro.sangue.Seriale;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestCreateAndPopulateMongoDB {
    public static void main(String[] args) {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        DataManager dm = new MyMongoDataManager();
        dm.createDB();      //non fa niente

        final int NUMSACCHE = 5;
        GruppoSanguigno gs[] = GruppoSanguigno.values();
        Random generatore = new Random();
        Sacca s;
        //aggiungo 5 sacche
        for (int i = 0; i<NUMSACCHE; i++){
            s = new Sacca(gs[generatore.nextInt(gs.length)]);       //aggiungo un gs random
            dm.addSacca(s);     //aggiungo la sacca
            System.out.println("Sacca aggiunta: "+s);
        }

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                Seriale.updateSettings();
            }
        }));
    }
}
