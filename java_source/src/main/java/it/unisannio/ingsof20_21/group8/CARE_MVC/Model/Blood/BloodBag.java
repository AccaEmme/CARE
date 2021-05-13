/**
 * @author giuliano ranauro
 * Date: 13/05/2021 21:39
 * Ide: Intellij
 * JDK: 1.8
 * @version 1.0
 */
package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood;
import java.util.Date;
public class BloodBag implements Comparable<BloodBag> {
    /**
     * @// TODO: 11/05/2021   modificare le classi {@code Seriale} e la classe {@code GruppoSanguigno}*/
    private Seriale serial;                     //the serial of the blood bag
    private BloodGroup bloodGroup;         //the blood group of the blood bag
    private Date expirationDate;                //the day the bag will expire
    private Date creationDate;                  //the day the bag was taken
    /**@// TODO: 11/05/2021 create class "hospital" that can be the origin or the destination of a bag */
    private String origin;                      //the hospital where the blood sample was taken



    /**
     * @param serial bag's serial
     * @param bloodGroup bag's blood group
     * @param expirationDate bag's expiration date
     * @param creationDate bag's creation date
     * @param origin where the bag was taken
     * */
    public BloodBag(Seriale serial, BloodGroup bloodGroup, Date expirationDate, Date creationDate, String origin){
        assert bloodGroup != null && serial != null && serial != null && creationDate != null;

        this.serial = serial;
        this.bloodGroup = bloodGroup;
        this.expirationDate = expirationDate;
        this.creationDate = creationDate;
        this.origin = origin;
    }
    /**
     * @param bloodGroup bag's blood group
     * @param expirationDate bag's expiration date
     * @param creationDate bag's creation date
     * @param origin where the bag was taken
     * */
    public BloodBag(BloodGroup bloodGroup, Date expirationDate, Date creationDate, String origin){
        assert bloodGroup != null && serial != null && serial != null && creationDate != null;

        this.serial = new Seriale();
        this.bloodGroup = bloodGroup;
        this.expirationDate = expirationDate;
        this.creationDate = creationDate;
        this.origin = origin;
    }


    /**
     * @// TODO: 11/05/2021  implementare bene questo costruttore, in modo da fargli accettare delle stringhe invece di oggetti
     * */
    public BloodBag(String serial, String bloodGroup, String expirationDate, String creationDate){
        assert bloodGroup != null && serial != null && serial != null && creationDate != null;

    }

    // ########################### UTIL ###########################
    public String toString(){
        return " Seriale: " + serial + ",\n\t Blood Group: " + this.bloodGroup + ",\n\t Origin: " + this.origin + ",\n\t Creation date: "+this.creationDate + ",\n\t Expiration date: "+expirationDate + ".";
    }

    public boolean equals(Object o){
        assert o != null;
        return ((BloodBag) o).getSerial().toString().equals(this.getSerial().toString());
    }

    @Override
    public int compareTo(BloodBag o) {
        return this.serial.toString().compareTo(o.getSerial().toString());
    }


    // ########################### GET ###########################
    public Seriale getSerial() {
        return serial;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getOrigin() {
        return origin;
    }

    // ########################### SET ###########################

    public void setSerial(Seriale serial) {
        this.serial = serial;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }


}
