/**
 * @author giuliano ranauro
 * Date: 12/05/2021 12:31
 * Ide: Intellij
 * JDK: 1.8
 * @version 1.0
 */
package com.ranauro.testers;

import com.ranauro.blood.BloodBag;
import com.ranauro.blood.BloodGroup;

public class TestBagByGroup<T> implements Tester<BloodBag> {
    private BloodGroup bloodGroup;

    public TestBagByGroup(BloodGroup bloodGroup){
        this.bloodGroup = bloodGroup;
    }
    @Override
    public boolean test(BloodBag ToBeTested) {
        if (ToBeTested.getBloodGroup().toString().equals(bloodGroup.toString()))
            return true;
        return false;
    }
}
