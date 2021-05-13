/**
 * @author giuliano ranauro
 * Date: 12/05/2021 16:11
 * Ide: Intellij
 * JDK: 1.8
 * @version 1.0
 */
package org.example.BloodManager;

import com.ranauro.blood.BloodBag;
import com.ranauro.blood.BloodManager;
import com.ranauro.sorters.Sorter;

import java.util.List;

public class SorterTester {
    public static void main(String[] args) {
        BloodManager manager = new BloodManager();


        List<BloodBag> bags = manager.getBags();
        BloodBag[] toSort = new BloodBag[bags.size()];

        for (int i = 0; i < bags.size(); i++)
            toSort[i] = bags.get(i);

        Sorter<BloodBag> sorter1 = new Sorter<>(toSort);
        sorter1.sort();

        for (BloodBag bag : sorter1.getToBeSorted())
            System.out.println(bag);
    }
}
