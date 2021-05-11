/**
 * @author giuliano ranauro
 * Date: 29-10-20
 * Ide: Intellij
 * JDK: 1.8
 */
package com.ranauro.sangue;

import java.util.Date;

public class BloodBag {
    /**
     * @// TODO: 11/05/2021   modificare le classi {@code Seriale} e la classe {@code GruppoSanguigno}*/
    private Seriale serial;                     //the serial of the blood bag
    private GruppoSanguigno bloodGroup;         //the blood group of the blood bag
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
    public BloodBag(Seriale serial, GruppoSanguigno bloodGroup, Date expirationDate, Date creationDate, String origin){
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
    public BloodBag(GruppoSanguigno bloodGroup, Date expirationDate, Date creationDate, String origin){
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
        return "Seriale: " + serial + "Gruppo: " + this.bloodGroup + ", Origin: " + this.origin + ", Creation date: "+this.creationDate + ", Expiration date: "+expirationDate;
    }

    public boolean equals(Object o){
        assert o != null;
        return ((BloodBag) o).getSerial().toString().equals(this.getSerial().toString());
    }


    // ########################### GET ###########################
    public Seriale getSerial() {
        return serial;
    }

    public GruppoSanguigno getBloodGroup() {
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

    public void setBloodGroup(GruppoSanguigno bloodGroup) {
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
