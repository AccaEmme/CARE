/**
 * @author giuliano ranauro
 * Date: 29-10-20
 * Ide: Intellij
 * JDK: 1.8
 */
package org.example.Dump;

import com.ranauro.manager.MongoManager;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
