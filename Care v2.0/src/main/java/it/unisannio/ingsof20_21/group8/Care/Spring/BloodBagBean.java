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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
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
                ", creationDate='" + creationDate + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", state='" + state + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
