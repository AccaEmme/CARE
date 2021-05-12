/**
 * @author giuliano ranauro
 * Date: 12/05/2021 14:32
 * Ide: Intellij
 * JDK: 1.8
 * @version 1.0
 */
package org.example.BloodManager;

import com.ranauro.blood.BloodBag;
import com.ranauro.blood.BloodManager;
import com.ranauro.testers.TestBagByExpirationDate;
import com.ranauro.util.Costants;
import com.ranauro.util.DateGenerator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FilterBagsByExpirationDate {
    public static void main(String[] args) throws ParseException {
        BloodManager bloodManager = new BloodManager();

        //yyyy-mm-dd_hh-mm-ss
        //getting all bags expiring before the date
        BloodManager filteredBags = bloodManager.testBloodBag(new TestBagByExpirationDate(DateGenerator.generateDate("2021-12-25_13-00-00"), true));


        System.out.println("Filtered bags by date: ");
        for (BloodBag bag : filteredBags.getBags())
            System.out.println(bag.toString());
    }
}
