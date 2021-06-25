/**
 * @author giuliano ranauro
 * Date: 12/05/2021 12:43
 * Ide: Intellij
 * JDK: 1.8
 * @version 1.0
 */
package org.example.BloodManager;

import com.ranauro.blood.BloodBag;
import com.ranauro.blood.BloodGroup;
import com.ranauro.blood.BloodManager;
import com.ranauro.testers.TestBagByGroup;

public class FilterBagsByGroup {
    public static void main(String[] args) {
        BloodManager bloodManager = new BloodManager();
        BloodGroup bloodGroup = BloodGroup.ABp;

        BloodManager filteredBags = bloodManager.testBloodBag(new TestBagByGroup(bloodGroup));


        System.out.println("Filtered bags by group: "+bloodGroup);
        for (BloodBag bag : filteredBags.getBags())
            System.out.println(bag.toString());

    }


}
