package it.unisannio.ingsof20_21.group8.Care.Spring;


import javax.persistence.*;

@Entity
public class BloodBagBean {
    @Id
    private String serial;

    private String group;
    private String donator;
    private String creationDate;
    private String expirationDate;
    private String state;
    private String notes;

    public BloodBagBean(){}
}
