/**
 * @author giuliano ranauro
 * Date: 11/05/2021 22:34
 * Ide: Intellij
 * JDK: 1.8
 * @version 1.0
 */
package org.example.getFromDB;

import com.ranauro.manager.MongoManager;
import com.ranauro.sangue.Seriale;

public class SearchBySerial {
    public static void main(String[] args) {
        Seriale serial = new Seriale();
        MongoManager mongoManager = new MongoManager();
        System.out.println(mongoManager.searchBag(serial));
    }
}
