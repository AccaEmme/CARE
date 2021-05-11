/**
 * @author giuliano ranauro
 * Date: 29-10-20
 * Ide: Intellij
 * JDK: 1.8
 */
package org.example.getFromDB;

import com.ranauro.manager.MongoManager;
import com.ranauro.sangue.BloodBag;
import com.ranauro.sangue.SaccaOLD;

import java.text.ParseException;
import java.util.List;

public class GetSaccheTest {
    public static void main(String[] args) {
        MongoManager mongoManager = new MongoManager();
        List<BloodBag> sacche = null;
        try {
            sacche = mongoManager.getBloodBags();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        if (sacche.size() == 0)
            System.out.println("There are no bags in the database.");
        else
        for (BloodBag saccaOLD : sacche)
            System.out.println(saccaOLD.toString());
    }
}
