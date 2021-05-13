/**
 * @author giuliano ranauro
 * Date: 13/05/2021 21:46
 * Ide: Intellij
 * JDK: 1.8
 * @version 1.0
 */
package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Testers;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;

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
