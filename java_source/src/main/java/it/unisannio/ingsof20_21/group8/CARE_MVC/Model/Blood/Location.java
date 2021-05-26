package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood;

/*
 * Questa classe definisce il nodo di appartenenza della sacca di sangue.
 */

public class Location {

	/*
	 * Costruttore della classe 'Location'.
	 * 
	 * @param whCode La stringa che indica il codice del magazzino.
	 * @param street La stringa che indica la strada vicino alla quale è custodita la sacca.
	 * @param streetNumber La stringa che indica il numero civico della strada.
	 * @param city La stringa che indica la città in cui è custodita la sacca.
	 * @param province La stringa che indica la provincia in cui è custodita la sacca.
	 * @param region La stringa che indica la regione in cui è custodita la sacca.
	 * @param country La stringa che indica la nazione in cui è custodita la sacca.
	 * 
	 */
    public Location(String whCode, String street, int streetNumber, String city, String province, String region, String country) {
        this.whCode = whCode;
    	this.street = street;
        this.streetNumber = streetNumber;
        this.city = city;
        this.province = province;
        this.region = region;
        this.country = country;
    }
    
    public String getWhCode() { return whCode; }
    
    public String getStreet() { return street; }
    
    public int getStreetNumber() { return streetNumber; }
    
    public String getCity() { return city; }
    
    public String getProvince() { return province; }
    
    public String getRegion() { return region; }
    
    public String getcountry() { return country; }
    
    public void setWhCode(String whCodeR) { whCode = whCodeR; }
    
    public void setStreet(String streetR) { street = streetR; }
    
    public void setStreetNumber(int streetNumberR) { streetNumber = streetNumberR; }
    
    public void setCity(String cityR) { city = cityR; }
    
    public void setProvince(String provinceR) { province = provinceR; }
    
    public void setRegion(String regionR) { region = regionR; }
    
    public void setcountry(String countryR) { country = countryR; }
    
    private String whCode;
	private String street;              
    private int streetNumber;          
    private String city;              
    private String province;           
    private String region;
    private String country;            
}
