/**
 * @author giuliano ranauro
 * Date: 29-10-20
 * Ide: Intellij
 * JDK: 1.8
 */
package org.example.getFromDB;

import com.ranauro.manager.MongoManager;
import com.ranauro.sangue.SaccaOLD;

import java.util.List;

public class GetSaccheTest {
    public static void main(String[] args) {
        MongoManager mongoManager = new MongoManager();
        List<SaccaOLD> sacche = mongoManager.getSacche();


        if (sacche.size() == 0)
            System.out.println("There are no bags in the database.");
        else
        for (SaccaOLD saccaOLD : sacche)
            System.out.println(saccaOLD.toString());
    }
}
