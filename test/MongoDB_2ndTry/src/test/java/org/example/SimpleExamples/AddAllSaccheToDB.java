package org.example.SimpleExamples;

import com.ranauro.manager.MongoManager;
import com.ranauro.sangue.GruppoSanguigno;
import com.ranauro.sangue.Sacca;
import org.bson.Document;

public class AddAllSaccheToDB {
    public static void main(String[] args) {
        GruppoSanguigno[] gruppiSanguigni = GruppoSanguigno.values();
        Sacca sacca = null ;

        MongoManager mongoManager = new MongoManager();

        addTwice(gruppiSanguigni, sacca, mongoManager);

    }
    private static void addOnce(GruppoSanguigno[] gruppiSanguigni, Sacca sacca, MongoManager mongoManager){
        for (GruppoSanguigno gs : gruppiSanguigni) {
            sacca = new Sacca(gs);
            mongoManager.addSacca(sacca);


            System.out.println("Added sacca: "+sacca);
        }
    }
    private static void addTwice(GruppoSanguigno[] gruppiSanguigni, Sacca sacca, MongoManager mongoManager){
        for (GruppoSanguigno gs : gruppiSanguigni) {
            sacca = new Sacca(gs);
            mongoManager.addSacca(sacca);
            System.out.println("Added sacca: "+sacca);

            sacca = new Sacca(gs);
            mongoManager.addSacca(sacca);

            System.out.println("Added sacca: "+sacca);
        }
    }
}
