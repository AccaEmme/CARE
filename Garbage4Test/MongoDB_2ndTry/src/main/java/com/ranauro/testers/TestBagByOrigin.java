/**
 * @author giuliano ranauro
 * Date: 12/05/2021 15:27
 * Ide: Intellij
 * JDK: 1.8
 * @version 1.0
 */
package com.ranauro.testers;

import com.ranauro.blood.BloodBag;

public class TestBagByOrigin<T> implements Tester<BloodBag> {
    String origin;  /**@// TODO: 12/05/2021 change the string with a proper class */
    boolean flag;

    /**
     * @param origin the origin of the blood bag (where the sample was taken)
     * @param flag  {@code true} ignore case
     *              {@code false} case sensitive
     *              */
    public TestBagByOrigin(String origin, boolean flag){
        this.origin = origin;
        this.flag = flag;
    }
    @Override
    public boolean test(BloodBag ToBeTested) {
        if (flag){
            if (ToBeTested.getOrigin().toLowerCase().equals(this.origin.toLowerCase()))
                return true;
        }else {
            if (ToBeTested.getOrigin().equals(this.origin))
                return true;
        }
        return false;
    }
}
