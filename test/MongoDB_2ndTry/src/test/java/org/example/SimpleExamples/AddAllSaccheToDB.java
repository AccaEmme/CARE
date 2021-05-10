package org.example.SimpleExamples;

import com.ranauro.manager.MongoManager;
import com.ranauro.sangue.GruppoSanguigno;
import com.ranauro.sangue.Sacca;
import org.bson.Document;

public class AddAllSaccheToDB {
    public static void main(String[] args) {
        GruppoSanguigno[] gruppiSanguigni = GruppoSanguigno.values();
        Sacca sacca ;

        MongoManager mongoManager = new MongoManager();

        for (GruppoSanguigno gs : gruppiSanguigni) {
            sacca = new Sacca(gs);
            mongoManager.addSacca(sacca);


            System.out.println("Added sacca: "+sacca);
        }

    }
}
