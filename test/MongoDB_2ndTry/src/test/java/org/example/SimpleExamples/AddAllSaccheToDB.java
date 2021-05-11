package org.example.SimpleExamples;

import com.ranauro.manager.MongoManager;
import com.ranauro.sangue.BloodBag;
import com.ranauro.sangue.BloodGroup;

import java.util.Date;

public class AddAllSaccheToDB {
    public static void main(String[] args) {
        BloodGroup[] bloodGroups = BloodGroup.values();
        BloodBag bloodBag = null;
        MongoManager mongoManager = new MongoManager();

        //addTwice(gruppiSanguigni, saccaOLD, mongoManager);

        for (BloodGroup group : bloodGroups){
            bloodBag = new BloodBag(group, new Date(), new Date(), "FateBeneFratelli");
            mongoManager.addBloodBag(bloodBag);
        }

    }











    /*private static void addOnce(BloodGroup[] gruppiSanguigni, SaccaOLD saccaOLD, MongoManager mongoManager){
        for (BloodGroup gs : gruppiSanguigni) {
            saccaOLD = new SaccaOLD(gs);
            mongoManager.addSacca(saccaOLD);


            System.out.println("Added sacca: "+ saccaOLD);
        }
    }
    private static void addTwice(BloodGroup[] gruppiSanguigni, SaccaOLD saccaOLD, MongoManager mongoManager){
        for (BloodGroup gs : gruppiSanguigni) {
            saccaOLD = new SaccaOLD(gs);
            mongoManager.addSacca(saccaOLD);
            System.out.println("Added sacca: "+ saccaOLD);

            saccaOLD = new SaccaOLD(gs);
            mongoManager.addSacca(saccaOLD);

            System.out.println("Added sacca: "+ saccaOLD);
        }
    }*/
}
