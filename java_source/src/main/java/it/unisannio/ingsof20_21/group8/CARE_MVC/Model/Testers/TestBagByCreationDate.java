/**
 * @author giuliano ranauro
 * Date: 13/05/2021 21:44
 * Ide: Intellij
 * JDK: 1.8
 * @version 1.0
 */
package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Testers;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;

import java.util.Date;

public class TestBagByCreationDate<T> implements Tester<BloodBag> {
    private Date date;
    private boolean flag;

    /**
     * @param date the date to check
     * @param flag  {@code true} to get the elements before that date,
     *              {@code false} to get the elements before that date*/
    public TestBagByCreationDate(Date date, boolean flag){
        this.date = date;
        this.flag = flag;
    }

    @Override
    public boolean test(BloodBag ToBeTested) {
        if (flag){
            if (ToBeTested.getCreationDate().before(this.date))
                return true;
        }else {
            if (ToBeTested.getCreationDate().after(this.date))
                return true;
        }
        return false;
    }
}
