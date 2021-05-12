/**
 * @author giuliano ranauro
 * Date: 12/05/2021 12:31
 * Ide: Intellij
 * JDK: 1.8
 * @version 1.0
 */
package com.ranauro.testers;

import com.ranauro.blood.BloodGroup;

public class TestBagByGroup<T> implements Tester<BloodGroup> {
    private BloodGroup bloodGroup;

    public TestBagByGroup(BloodGroup group){
        this.bloodGroup = group;
    }
    @Override
    public boolean test(BloodGroup ToBeTested) {
        if (ToBeTested.toString().equals(bloodGroup.toString()))
            return true;
        return false;
    }
}
