/**
 * @author giuliano ranauro
 * Date: 13/05/2021 21:46
 * Ide: Intellij
 * JDK: 1.8
 * @version 1.0
 */
package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Testers;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodGroup;

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
