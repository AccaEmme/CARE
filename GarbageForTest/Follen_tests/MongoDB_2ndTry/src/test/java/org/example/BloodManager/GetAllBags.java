/**
 * @author giuliano ranauro
 * Date: 12/05/2021 12:41
 * Ide: Intellij
 * JDK: 1.8
 * @version 1.0
 */
package org.example.BloodManager;

import com.ranauro.blood.BloodBag;
import com.ranauro.blood.BloodManager;

public class GetAllBags {
    public static void main(String[] args) {
        BloodManager bloodManager = new BloodManager();
        for (BloodBag bag : bloodManager.getBags())
            System.out.println(bag.toString());
    }
}
