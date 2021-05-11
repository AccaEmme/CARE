package org.example.SimpleExamples;

import com.ranauro.manager.MongoManager;
import com.ranauro.sangue.BloodBag;
import com.ranauro.sangue.BloodGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddAllSaccheToDB {
    public static void main(String[] args) {
        BloodGroup[] bloodGroups = BloodGroup.values();
        BloodBag bloodBag = null;
        MongoManager mongoManager = new MongoManager();

        //addTwice(gruppiSanguigni, saccaOLD, mongoManager);

        //not efficent
        /*for (BloodGroup group : bloodGroups){
            bloodBag = new BloodBag(group, new Date(), new Date(), "FateBeneFratelli");
            mongoManager.addBloodBag(bloodBag);
        }*/

        List<BloodBag> bags = new ArrayList<>();
        for (BloodGroup group : bloodGroups){
            bags.add(new BloodBag(group, new Date(), new Date(), "FateBeneFratelli"));
        }

        mongoManager.addBloodBag(bags);

        //WAAAAAAY faster!
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
