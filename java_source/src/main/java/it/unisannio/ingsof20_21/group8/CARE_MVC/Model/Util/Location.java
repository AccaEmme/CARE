package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util;

import org.bson.Document;

/*
 * Hermann: no, questa classe che potrebbe anche essere astratta, definisce una locazione, può essere dov'è situata una sacca o la città di un utente.
 */

public class Location {

	private String street, streetNumber, ZIPCode;          
    private City city;              
    private Province province;           
    private Region region;
    private Country country;
	
	/*
	 * Costruttore della classe 'Location'.
	 * 
	 * @param country enum 'Country' che indica la nazione del nodo.
	 * @param region enum 'Region' che indica la regione del nodo.
	 * @param province enum 'Province' che indica la provincia del nodo.
	 * @param city enum 'City' che indica la città del nodo.
	 * @param streetNumber L'intero che indica il numero civico della strada del nodo.
	 * @param street La stringa che indica la strada del nod.
	 */
    public Location(
    				Country country, 	Region region, 			Province province,
    				City city, 			String street,			String streetNumber, String ZIPCode
    		){
    	
    	setStreet(street);
    	setStreetNumber(streetNumber);
    	setZipCode(ZIPCode);
        this.city = city;
        this.province = province;
        this.region = region;
        this.country = country;
    }
    
     /**
     **************************************************************************
     * Descrizione del metodo
     * @param 
     * @return 
     **************************************************************************
     */
    public Document getDocument(){
    	Document document = new Document();
    		document.append("street",		this.street.toString());
			document.append("street_number",this.streetNumber.toString());
			document.append("city",			this.city.toString());
			document.append("province",		this.province.toString());
			document.append("region",		this.region.toString());
			document.append("country",		this.country.toString());
			document.append("zip_code",		this.getZipCode());

		return document;
	}

    /**@// TODO: 14/06/2021 dobbiamo prendere dal documento mongo ogni pezzo di location e creare l'oggetto adeguato */
	/* @TODO DELETE ME PLEASE!!!
    private String lazyLocation;
	public Location(String lazyLocation){
		this.lazyLocation = lazyLocation;
	}
	public String getLocation(){
		return this.lazyLocation;
	}
	*/

    

    /**
     **************************************************************************
     * Metodo Get per ottenere la strada
     * @return stret
     **************************************************************************
     */
    public String getStreet() { return street; }
    
     /**
     **************************************************************************
     * Metodo Get per ottenere il numero della strada 
     * @return streetNumber
    **************************************************************************
     */
    public String getStreetNumber() { return streetNumber; }
    
    /**
     **************************************************************************
     * Metodo Get per ottenere la città
    * @return city
     **************************************************************************
     */
    public City getCity() { return city; }
    
    /**
     **************************************************************************
     * Metodo Get per ottenere la città
     * @return city
     **************************************************************************
     */  
    public Province getProvince() { return province; }
    
    /**
     **************************************************************************
     * Metodo Get per ottenere la città
     * @return city
     **************************************************************************
     */
    public Region getRegion() { return region; }
    
    /**
     **************************************************************************
     * Metodo Get per ottenere la nazione
     * @return country
     **************************************************************************
     */
    public Country getCountry() { return country; }
    
    /**
     **************************************************************************
     * Metodo Get per ottenere lo ZIPCode
     * @return ZIPCode
     **************************************************************************
     */
    public String getZipCode() { return ZIPCode; }
    
    
    
    /**
     **************************************************************************
     * Metodo Set per inserire una nuova strada
     * @param String streetR
     * @exception IllegalArgumentException
     **************************************************************************
     */
    public void setStreet(String streetR) throws IllegalArgumentException{ 
		if( streetR.equals("") )
			throw new IllegalArgumentException("Il nome della strada non è stato inserito");
		this.street = streetR;
	}
    
    /**
     **************************************************************************
     * Metodo Set per inserire un nuovo numero della strada
     * @param String streetNumberR
     * @exception IllegalArgumentException
     **************************************************************************
     */
    public void setStreetNumber(String streetNumberR) throws IllegalArgumentException{
		if( streetNumberR.equals("") ) 
			throw new IllegalArgumentException("Il numero della strada non è stato inserito");
		this.streetNumber  = streetNumberR; 
	}
   
    /**
     **************************************************************************
     * Metodo Set per inserire una nuova città
     * @param String city
     **************************************************************************
     */
    public void setCity(String cityR) { city = City.valueOf(cityR); }
    
    /**
     **************************************************************************
     * Metodo Set per inserire una nuova provincia
     * @param String provinceR
     **************************************************************************
     */
    public void setProvince(String provinceR) { province = Province.valueOf(provinceR); }
    
    /**
     **************************************************************************
     * Metodo Set per inserire una nuova regione
     * @param String regionR
     **************************************************************************
     */
    public void setRegion(String regionR) { region = Region.valueOf(regionR); }
    
    /**
     **************************************************************************
     * Metodo Set per inserire una nuova nazione
     * @param String countryR
     **************************************************************************
     */
    public void setCountry(String countryR) { country = Country.valueOf(countryR); }
    
    /**
     **************************************************************************
     * Metodo Set per inserire un nuovo zipcode
     * @param String ZIPcode
     **************************************************************************
     */
    public void setZipCode(String ZIPcode) { 
    	if( ZIPcode.equals("") ) 
			throw new IllegalArgumentException("Il numero zipcode non è valido");
    	this.ZIPCode = ZIPcode; 
    }
    
    
    /**
     **************************************************************************
     * Metodo ToString per ottenere tutte le informazioni registrate nella classe Location
     * @return (Street, StreetNumber, City, Province, Region, Country, ZIPcode)
     **************************************************************************
     */
	public String toString() {
		return  "{\"street\": \"" 	  		+ this.street   		+ "\""
				+", \"streetNumber\": \""  	+ this.streetNumber 	+ "\"" 
				+", \"city\": \"" 			+ this.city 			+ "\""
				+", \"province\": \"" 		+ this.province 		+ "\""
				+", \"region\": \"" 		+ this.region 			+ "\""
				+", \"country\": \"" 		+ this.country 			+ "\""
				+", \"ZIPCode\": \"" 		+ this.ZIPCode 			+ "\""
				+ "}";
	}
	
	/**
	 **************************************************************************
	 * Metodo Enum per registrare tutte le informazioni della Nazione
	 **************************************************************************
	 */
	public enum Country {
		Italy, Spain, Croatia, Greece;
	}
	
	/**
	 **************************************************************************
	 * Metodo Enum per registrare tutte le informazioni della Regione
	 **************************************************************************
	 */
	
	public enum Region {
		Abruzzo, Basilicata, Bolzano, Calabria, Campania, Emilia_Romagna, Friuli_Venezia_Giulia, Lazio,	Liguria, Lombardia,	Marche,	Molise, Piemonte, Puglia, Sardegna, Sicilia, Toscana, Trento, Umbria, Valle_d_Aosta, Veneto;
		}
	/**
	 **************************************************************************
	 * Metodo Enum per registrare tutte le informazioni della provincia
	 **************************************************************************
	 */
	
	public enum Province {
		Chieti, Potenza, Bolzano, Catanzaro, Napoli, Bologna, Udine, Roma, Genova, Varese, Ancona, Campobasso, Torino, Bari, Cagliari, Palermo, Firenze, Trento, Perugia, Aosta, Padova, Benevento, Avellino, Salerno, Caserta;
	}
	/**
	 **************************************************************************
	 * Metodo Enum per registrare tutte le informazioni della Città
	 **************************************************************************
	 */
	
	public enum City {
		Chieti, Potenza, Bolzano, Catanzaro, Napoli, Bologna, Udine, Roma, Genova, Varese, Ancona, Campobasso, Torino, Bari, Cagliari, Palermo, Firenze, Trento, Perugia, Aosta, Padova, Benevento, Avellino, Salerno, Caserta;
	}
	
	/**
	 **************************************************************************
	 * Metodo per il tester Junit per verificare l'esistenza dell'oggetto Location
	 * @return true
	 **************************************************************************
	 */
	public boolean exists() { return true; }
}
