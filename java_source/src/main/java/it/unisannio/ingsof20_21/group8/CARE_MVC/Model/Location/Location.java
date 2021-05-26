package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Location;

import java.util.Iterator;
import java.util.LinkedList;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;

/*
 * Alessio: Questa classe definisce il nodo di appartenenza della sacca di sangue.
 * Hermann: no, questa classe che potrebbe anche essere astratta, definisce una locazione, può essere dov'è situata una sacca o la città di un utente.
 */

public class Location {

	/*
	 * Costruttore della classe 'Location'.
	 * 
	 * @param whCode La stringa che indica il codice del magazzino del nodo.
<<<<<<< HEAD
	 * @param denomination La stringa che indica la denominaziuone dell'ospedale, magazzino o nodo.
=======
>>>>>>> fefec62bad8c40547aa6f529f4a771fdfe451d2b
	 * @param street La stringa che indica la strada del nod.
	 * @param streetNumber L'intero che indica il numero civico della strada del nodo.
	 * @param city La stringa che indica la città del nodo.
	 * @param province La stringa che indica la provincia del nodo.
	 * @param region La stringa che indica la regione del nodo.
	 * @param country La stringa che indica la nazione del nodo.
	 * 
	 */
    public Location(String whCode, String denomination, String street, int streetNumber, City city, Province province, Region region, Country country) {
        
    	this.whCode = whCode;
    	this.denomination = denomination;
    	this.street = street;
        this.streetNumber = streetNumber;
        this.city = city;
        this.province = province;
        this.region = region;
        this.country = country;
        this.bloodBags = new LinkedList<BloodBag>();
        
    }
	/*
	 * Costruttore della classe 'Location'.
	 * 
	 * @param whCode La stringa che indica il codice del magazzino del nodo.
	 * @param denomination La stringa che indica la denominaziuone dell'ospedale, magazzino o nodo.
	 * @param street La stringa che indica la strada del nod.
	 * @param streetNumber L'intero che indica il numero civico della strada del nodo.
	 * @param city enum 'City' che indica la città del nodo.
	 * @param province enum 'Province' che indica la provincia del nodo.
	 * @param region enum 'Region' che indica la regione del nodo.
	 * @param country enum 'Country' che indica la nazione del nodo.
	 * 
	 */   

    public Location(String whCode, String denomination, String street, int streetNumber, String city, String province, String region, String country) {
        
    	this.whCode = whCode;
    	this.denomination = denomination;
    	this.street = street;
        this.streetNumber = streetNumber;
        this.city = City.valueOf(city);
        this.province = Province.valueOf(province);
        this.region = Region.valueOf(region);
        this.country = Country.valueOf(country);
        this.bloodBags = new LinkedList<BloodBag>();
        
    }
    
    public Location() {}
    
    public String getWhCode() { return whCode; }
    
    public String getDenomination() { return denomination; }
    
    public String getStreet() { return street; }
    
    public int getStreetNumber() { return streetNumber; }
    
    public City getCity() { return city; }
    
    public Province getProvince() { return province; }
    
    public Region getRegion() { return region; }
    
    public Country getCountry() { return country; }
    
    public Iterator<BloodBag> getBloodBags() { return bloodBags.iterator(); }
    
    public void setWhCode(String whCodeR) { whCode = whCodeR; }
    
    public void setDenomination(String denominationR) { denomination = denominationR; }
    
    public void setStreet(String streetR) { street = streetR; }
    
    public void setStreetNumber(int streetNumberR) { streetNumber = streetNumberR; }
    
    public void setCity(String cityR) { city = City.valueOf(cityR); }
    
    public void setProvince(String provinceR) { province = Province.valueOf(provinceR); }
    
    public void setRegion(String regionR) { region = Region.valueOf(regionR); }
    
    public void setCountry(String countryR) { country = Country.valueOf(countryR); }
    
    /*
     * metodo accessibile solo al manager che inserisce o sovrascrive una lista di sacche.
     * @param LinkedList<BloodBag> bloodBagsR
     */
    public void setBloodBags(LinkedList<BloodBag> bloodBagsR) { bloodBags = bloodBagsR; }
    
    public boolean addBloodBagAtLocation(BloodBag bloodBagR) { return bloodBags.add(bloodBagR); }
    
	private enum Country {
		Italia, Spagna, Croazia, Grecia;
	}
	
	private enum Region {
		Abruzzo, Basilicata, Bolzano, Calabria, Campania, Emilia_Romagna, Friuli_Venezia_Giulia, Lazio,	Liguria, Lombardia,	Marche,	Molise, Piemonte, Puglia, Sardegna, Sicilia, Toscana, Trento, Umbria, Valle_d_Aosta, Veneto;
		}
	
	private enum Province {
		
		Chieti, Potenza, Bolzano, Catanzaro, Napoli, Bologna, Udine, Roma, Genova, Varese, Ancona, Campobasso, Torino, Bari, Cagliari, Palermo, Firenze, Trento, Perugia, Aosta, Padova, Benevento, Avellino, Salerno, Caserta;
		
	}
	
	private enum City {
		Chieti, Potenza, Bolzano, Catanzaro, Napoli, Bologna, Udine, Roma, Genova, Varese, Ancona, Campobasso, Torino, Bari, Cagliari, Palermo, Firenze, Trento, Perugia, Aosta, Padova, Benevento, Avellino, Salerno, Caserta;
	}
    
    private String whCode;
    private String denomination;
	private String street;              
    private int streetNumber;          
    private City city;              
    private Province province;           
    private Region region;
    private Country country; 
    private LinkedList<BloodBag> bloodBags;
}
