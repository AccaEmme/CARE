/**
 * @author giuliano ranauro
 * Date: 17/05/2021 23:00
 * Ide: Intellij
 * JDK: 1.8
 * @version 1.0
 */
package com.ranauro.Node;

public class Location {
    private String street;              //la via
    private int streetNumber;           //il numero
    private String country;             //stato (italia)
    private String city;                //città
    private String province;            //provincia
    private int ZIP_code;

    public Location(String street, int streetNumber, String country, String city, String province, int ZIP_code) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.country = country;
        this.city = city;
        this.province = province;
        this.ZIP_code = ZIP_code;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getZIP_code() {
        return ZIP_code;
    }

    public void setZIP_code(int ZIP_code) {
        this.ZIP_code = ZIP_code;
    }
}
