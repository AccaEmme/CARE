package it.unisannio.ingsof20_21.group8.Care.Spring;


import it.unisannio.CARE.model.bloodBag.BloodBag;
import it.unisannio.CARE.model.bloodBag.BloodGroup;
import it.unisannio.CARE.model.bloodBag.Serial;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * this class is the modeling class of a blood bag
 */
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table (name = "bloodbags")
public class BloodBagDAO {
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

    /**
     * empty constructor
     */
    public BloodBagDAO(){}

    /**
     * main blood bag constructor
     * @param serial the bag's serial
     * @param creationDate the bag's creation date
     * @param donator the bag's donator
     * @param expirationDate the bag's expiration date
     * @param group the bag's blood group
     * @param notes the bag's optional notes
     * @param state the bag's state
     */
    public BloodBagDAO(String serial, long creationDate, String donator, long expirationDate, String group, String notes,
			String state ) {
    	
		this.serial = serial;
		this.group = group;
		this.donator = donator;
		this.creationDate = creationDate;
		this.expirationDate = expirationDate;
		this.state = state;
		this.notes = notes;
	}

    /**
     * set the blood bag serial
     * @return the blood bag serial
     */
	public String getSerial() {
        return serial;
    }

    /**
     * get the blood bag serial
     * @param serial the blood bag serial
     */
    public void setSerial(String serial) {
        this.serial = serial;
    }

    /**
     * get  the blood bag blood group
     * @return the blood bag blood group
     */
    public String getGroup() {
        return group;
    }

    /**
     * set the blood bag group
     * @param group the blood bag group
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * get the blood bag donator
     * @return the blood bag donator
     */
    public String getDonator() {
        return donator;
    }

    /**
     * set the blood bag donator
     * @param donator the blood bag donator
     */
    public void setDonator(String donator) {
        this.donator = donator.toUpperCase();
    }

    /**
     * get the blood bag creation date
     * @return the blood bag creation date
     */
    public long getCreationDate() {
        return creationDate;
    }

    /**
     * set the blood bag creation date
     * @param creationDate the creation date
     */
    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * get the blood bag creation date
     * @return the blood bag creation date
     */
    public long getExpirationDate() {
        return expirationDate;
    }

    /**
     * set the blood bag expiration date
     * @param expirationDate the blood bag expiration date
     */
    public void setExpirationDate(long expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * get the blood bag state
     * @return the blood bag state
     */
    public String getState() {
        return state;
    }

    /**
     * set the blood bag's blood bag state
     * @param state the state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * get the blood bag notes
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * set te blood bag notes
     * @param notes the notes
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * get the string rappresentation of the class
     * @return the String containing all the global variables
     */
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

    /**
     * get the used time stamp
     * @return the used time stamp
     */
    public long getUsedTimeStamp() {
        return usedTimeStamp;
    }

    /**
     * thi method is used to update the "used timestamp"
     * @param usedTimeStamp the date when the bag was used
     */
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
