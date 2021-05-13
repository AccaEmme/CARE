/**
 * @author giuliano ranauro
 * Date: 13/05/2021 21:45
 * Ide: Intellij
 * JDK: 1.8
 * @version 1.0
 */
package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Testers;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;

import java.util.Date;

public class TestBagByExpirationDate<T> implements Tester<BloodBag> {
    private boolean flag;
    private Date date;
    /**
     * @param date the date to check
     * @param flag  {@code true} to get the elements before that date,
     *              {@code false} to get the elements before that date*/
    public TestBagByExpirationDate(Date date, boolean flag){
        this.date = date;
        this.flag = flag;
    }
    @Override
    public boolean test(BloodBag ToBeTested) {
        if (flag){
            if (ToBeTested.getExpirationDate().before(this.date))
                return true;
        }else {
            if (ToBeTested.getExpirationDate().after(this.date))
                return true;
        }
        return false;
    }
}
