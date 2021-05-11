/**
 * @author giuliano ranauro
 * Date: 11/05/2021 17:10
 * Ide: Intellij
 * JDK: 1.8
 * @version 1.0
 */
package org.example.getFromDB;

import com.ranauro.manager.MongoManager;
import com.ranauro.sangue.BloodBag;

import java.text.ParseException;
import java.util.List;

public class GetSaccheNew {
    public static void main(String[] args) {
        MongoManager mongoManager = new MongoManager();
        try {
            List<BloodBag> bags = mongoManager.getBloodBags();

            if (bags.size() == 0)
                System.out.println("There are no bags in the database");
            else
                for (BloodBag bag : bags)
                    System.out.println(bag.toString());

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
