/**
 * @author giuliano ranauro
 * Date: 29-10-20
 * Ide: Intellij
 * JDK: 1.8
 */
package org.example.getFromDB;

import com.ranauro.manager.MongoManager;
import com.ranauro.sangue.Sacca;

import java.util.List;

public class GetSaccheTest {
    public static void main(String[] args) {
        MongoManager mongoManager = new MongoManager();
        List<Sacca> sacche = mongoManager.getSacche();

        for (Sacca sacca : sacche)
            System.out.println(sacca.toString());
    }
}
