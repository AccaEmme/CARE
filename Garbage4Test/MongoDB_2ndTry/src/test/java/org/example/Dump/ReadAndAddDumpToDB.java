/**
 * @author giuliano ranauro
 * Date: 14/05/2021 19:14
 * Ide: Intellij
 * JDK: 1.8
 * @version 1.0
 */
package org.example.Dump;

import com.ranauro.manager.MongoManager;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;

public class ReadAndAddDumpToDB {
    public static void main(String[] args) throws ParseException, java.text.ParseException, IOException {
        MongoManager mongoManager = new MongoManager();
        mongoManager.readDump(new File("dumps/readTest.json"));
    }
}
