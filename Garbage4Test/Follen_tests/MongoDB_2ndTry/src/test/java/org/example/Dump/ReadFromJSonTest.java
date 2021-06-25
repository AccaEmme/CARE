/**
 * @author giuliano ranauro
 * Date: 14/05/2021 18:41
 * Ide: Intellij
 * JDK: 1.8
 * @version 1.0
 */
package org.example.Dump;

import com.ranauro.blood.BloodGroup;
import com.ranauro.util.Costants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class ReadFromJSonTest {
    public static void main(String[] args) throws IOException, ParseException {
        JSONParser parser = new JSONParser();

        File dumpFile = new File("dumps/readTest.json");

        JSONArray a = (JSONArray) parser.parse(new FileReader(dumpFile));

        for (Object o : a)
        {
            JSONObject bag = (JSONObject) o;

            String serial = (String) bag.get(Costants.SERIAL);
            System.out.println("Serial: "+serial);

            String group = (String) bag.get(Costants.GROUP);
            System.out.println("Group: "+group);

            String origin = (String) bag.get(Costants.ORIGIN);
            System.out.println("Origin: "+origin);

            String creationDay = (String) bag.get(Costants.CREATION);
            System.out.println("Group: "+creationDay);

            /*
            nel caso ci fosse un array nell'oggetto
            JSONArray cars = (JSONArray) bag.get("cars");

            for (Object c : cars)
            {
                System.out.println(c+"");
            }*/
        }
    }
}
