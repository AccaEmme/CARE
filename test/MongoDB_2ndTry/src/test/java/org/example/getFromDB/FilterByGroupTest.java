/**
 * @author giuliano ranauro
 * Date: 11/05/2021 22:53
 * Ide: Intellij
 * JDK: 1.8
 * @version 1.0
 */
package org.example.getFromDB;

import com.ranauro.manager.MongoManager;
import com.ranauro.sangue.BloodBag;
import com.ranauro.sangue.BloodGroup;

import java.util.List;

public class FilterByGroupTest {
    public static void main(String[] args) {
        MongoManager mongoManager = new MongoManager();
        BloodGroup[] bloodGroup =  BloodGroup.values();


        List<BloodBag> bags =  mongoManager.filterByBloodGroup(bloodGroup[0]);
        System.out.println("Sacche con gruppo "+ bloodGroup[0]);
        for (BloodBag bag : bags)
            System.out.println(bag.toString());

        bags =  mongoManager.filterByBloodGroup(bloodGroup[2]);
        System.out.println("Sacche con gruppo "+ bloodGroup[2]);
        for (BloodBag bag : bags)
            System.out.println(bag.toString());

        bags =  mongoManager.filterByBloodGroup(bloodGroup[4]);
        System.out.println("Sacche con gruppo "+ bloodGroup[4]);
        for (BloodBag bag : bags)
            System.out.println(bag.toString());
    }
}
