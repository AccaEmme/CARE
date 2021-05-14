
package com.ranauro.blood;

import com.ranauro.manager.MongoManager;
import com.ranauro.testers.Tester;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class BloodManager {
    private List<BloodBag> bags;
    private MongoManager mongoManager;

    public BloodManager(){
        mongoManager = new MongoManager();
        try {
            this.bags = mongoManager.getBloodBags();
        } catch (ParseException e) {
            /**
             * @// TODO: 12/05/2021 handle the exception */
            e.printStackTrace();
        }
    }

    public BloodManager(List<BloodBag> bags){
        mongoManager = new MongoManager();
        this.bags = bags;
    }

    public BloodManager testBloodBag(Tester<BloodBag> tester){
        List<BloodBag> filteredBags = new ArrayList<>();

        for (BloodBag bag : bags)
            if (tester.test(bag))
                filteredBags.add(bag);

        return new BloodManager(filteredBags);
    }

    /**
     * This method updates the bags in the list of the class
     * @return {@code true} if the update was successful
     *         {@code false} if the update wasn't successful
     * */
    public boolean update(){
        boolean flag = true;
        try {
            this.bags = mongoManager.getBloodBags();
        } catch (ParseException e) {
            /**
             * @// TODO: 12/05/2021  handle the exception */
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public List<BloodBag> getBags(){
        return this.bags;
    }

}
