/**
 * @author giuliano ranauro
 * Date: 29-10-20
 * Ide: Intellij
 * JDK: 1.8
 */
package org.example.Dump;

import com.ranauro.manager.MongoManager;

public class DumpTester {
    public static void main(String[] args) {
        MongoManager mongoManager = new MongoManager();
        try {
            mongoManager.dump();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
