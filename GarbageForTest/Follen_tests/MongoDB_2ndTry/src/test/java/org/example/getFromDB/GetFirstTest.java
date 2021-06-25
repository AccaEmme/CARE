/**
 * @author giuliano ranauro
 * Date: 11/05/2021 22:07
 * Ide: Intellij
 * JDK: 1.8
 * @version 1.0
 */
package org.example.getFromDB;

import com.ranauro.manager.MongoManager;

public class GetFirstTest {
    public static void main(String[] args) {
        MongoManager mongoManager = new MongoManager();
        System.out.println(mongoManager.getFirst());
    }
}
