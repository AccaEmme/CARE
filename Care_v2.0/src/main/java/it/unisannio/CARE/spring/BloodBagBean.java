package it.unisannio.CARE.spring;


import javax.persistence.*;

import it.unisannio.CARE.model.bloodBag.BloodBag;
import it.unisannio.CARE.model.bloodBag.BloodGroup;
import it.unisannio.CARE.model.bloodBag.Serial;

import java.util.Date;

@Entity
@Table (name = "Bloodbag")
public class BloodBagBean {
    @Id
    private String serial;

    @Column(unique = false, nullable = false)
    private String group;
    @Column(unique = false, nullable = false)
    private String donator;
    @Column(unique = false, nullable = false)
    private long creationDate;
    @Column(unique = false, nullable = false)
    private long expirationDate;
    @Column(unique = false, nullable = true)
    private String state;
    private String notes;

    @Column(unique = false, nullable = true)
    private long usedTimeStamp;

    public BloodBagBean(){}

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDonator() {
        return donator;
    }

    public void setDonator(String donator) {
        this.donator = donator;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public long getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(long expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "BloodBagBean{" +
                "serial='" + serial + '\'' +
                ", group='" + group + '\'' +
                ", donator='" + donator + '\'' +
                ", creationDate=" + creationDate +
                ", expirationDate=" + expirationDate +
                ", state='" + state + '\'' +
                ", notes='" + notes + '\'' +
                ", usedTimeStamp=" + usedTimeStamp +
                '}';
    }

    public long getUsedTimeStamp() {
        return usedTimeStamp;
    }

    public void setUsedTimeStamp(long usedTimeStamp) {
        this.usedTimeStamp = usedTimeStamp;
    }

    /*
    public BloodBag getBloodBag(){

        Serial serial = new Serial(this.serial);
        BloodGroup group = BloodGroup.valueOf(this.group);
        Date creationDate = new Date(this.creationDate);
        Date expirationdate = new Date(this.expirationDate);

        BloodBag.BloodBagState state = BloodBag.BloodBagState.valueOf(this.state);


        return new BloodBag(serial,group,creationDate,expirationdate,this.donator,state,this.notes);
    }*/
}
