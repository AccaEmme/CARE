/**
 * @author giuliano ranauro
 * Date: 11/05/2021 16:26
 * Ide: Intellij
 * JDK: 1.8
 * @version 1.0
 */
package org.example.SangueExamples;

import com.ranauro.sangue.BloodBag;
import com.ranauro.sangue.BloodGroup;

import java.util.Date;

public class BloodBagTest {
    public static void main(String[] args) {
        BloodGroup[] bloodGroups = BloodGroup.values();

        BloodBag bloodBag = new BloodBag(bloodGroups[0], new Date(), new Date(), "FateBeneFratelli");

        System.out.println(bloodBag.toString());
    }
}
