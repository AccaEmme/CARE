package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.stream.Stream;


import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.City;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.Country;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.Province;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.Region;


public class LocationTest {    
	/* TEST PER INFORMAZIONI VALIDE DELLA CLASSE LOCATION.JAVA */
	
	
	
	
	//Verifica del corretto funzionamento dei get
	@Test
	 public void VerifyGetCountry()  {
		Location l = new Location( Country.Italy, Region.Campania, Province.Avellino ,City.Avellino,"via 25 Aprile","5"); 
		assertFalse(l.getCountry() == null);
	}
	
	@Test
	 public void VerifyGetRegion()  {
		Location l = new Location( Country.Italy, Region.Campania, Province.Avellino ,City.Avellino,"via 25 Aprile","5"); 
		assertFalse(l.getRegion() == null);
	}
	
	@Test
	 public void VerifyGetvProvince()  {
		Location l = new Location( Country.Italy, Region.Campania, Province.Avellino ,City.Avellino,"via 25 Aprile","5"); 
		assertFalse(l.getProvince() == null);
	}
	
	@Test
	 public void VerifyGetCity()  {
		Location l = new Location( Country.Italy, Region.Campania, Province.Avellino ,City.Avellino,"via 25 Aprile","5"); 
		assertFalse(l.getCity() == null);
	}
	
	@Test
	 public void VerifyGetStreet()  {
		Location l = new Location( Country.Italy, Region.Campania, Province.Avellino ,City.Avellino,"via 25 Aprile","5"); 
		assertFalse(l.getStreet() == null);
	}

	@Test
	 public void VerifyGetStreetNumber()  {
		Location l = new Location( Country.Italy, Region.Campania, Province.Avellino ,City.Avellino,"via 25 Aprile","5"); 
		assertFalse(l.getStreetNumber() == null);
	}
	
	@Test
	 public void VerifyToString()  {
		Location l = new Location( Country.Italy, Region.Campania, Province.Avellino ,City.Avellino,"via 25 Aprile","5"); 
		assertFalse(l.toString() == null);
	}

	//Verifica del corretto funzionamento della classe Location
	@Test
	 public void VerifyCreateCountry()  {
		Location l = new Location( Country.Italy, Region.Campania, Province.Avellino ,City.Avellino,"via 25 Aprile","5"); 
		assertTrue(l.exists());
	}

	//Verifica il corretto inserimento delle Nazioni
	@ParameterizedTest
	@MethodSource("ValidCoutry")
	  void test_ValidCoutry(String CoutryT) {
		Location l = new Location( Country.Italy, Region.Campania, Province.Avellino ,City.Avellino,"via 25 Aprile","5"); 
		l.setCountry(CoutryT);
		
	}

	//Verifica il corretto inserimento delle Regioni
	@ParameterizedTest
	@MethodSource("ValidRegion")
	 public void test_ValidRegion(String RegionT) {
		Location l = new Location( Country.Italy, Region.Campania, Province.Avellino ,City.Avellino,"via 25 Aprile","5"); 
		l.setRegion(RegionT);
	}

	//Verifica il corretto inserimento delle Province
	@ParameterizedTest
	@MethodSource("ValidProvince")
	 public void test_ValidProvince(String ProvinceT) {
		Location l = new Location( Country.Italy, Region.Campania, Province.Avellino ,City.Avellino,"via 25 Aprile","5"); 
		l.setProvince(ProvinceT); 
	} 


	//Verifica il corretto inserimento delle Province
	@ParameterizedTest
	@MethodSource("ValidCity")
	 public void test_ValidCity(String CityT)  {
		Location l = new Location( Country.Italy, Region.Campania, Province.Avellino ,City.Avellino,"via 25 Aprile","5"); 
	 	l.setCity(CityT); 
	}




	/* TEST PER INSERIMENTO DELLE INFORMAZIONI NON VALIDE NELLA CLASSE LOCATION.JAVA */


	//Verifica il non corretto inserimento di una Nazione
	@Test(expected = IllegalArgumentException.class)
	 public void InvalidCoutry() {
		  Location l = new Location( Country.Italy, Region.Campania, Province.Avellino ,City.Avellino,"via 25 Aprile","5"); 
		  l.setCountry("Germania");
	 }

	//Verifica il non corretto inserimento di una Regione
	@Test(expected = IllegalArgumentException.class)
	 public void InvalidRegion() {
		Location l = new Location( Country.Italy, Region.Campania, Province.Avellino ,City.Avellino,"via 25 Aprile","5"); 
		l.setRegion("Leonard");
	}

	//Verifica il non corretto inserimento di una Provincia
	@Test(expected = IllegalArgumentException.class)
	 public void InvalidProvince(){
		Location l = new Location( Country.Italy, Region.Campania, Province.Avellino ,City.Avellino,"via 25 Aprile","5"); 
		l.setProvince("Bazinga");
	}

	//Verifica il non corretto inserimento di una Città
	@Test(expected = IllegalArgumentException.class)
	public void InvalidCity(){
		Location l = new Location( Country.Italy, Region.Campania, Province.Avellino ,City.Avellino,"via 25 Aprile","5"); 
		l.setCity("Penny");
	}

	//Verifica dell'attivazione dell'eccezione in caso in cui non venga inserita una strada
	@Test(expected = IllegalArgumentException.class)
	 public void ContryStreetNotFound() {
		  Location l = new Location(
				  Country.Italy, Region.Campania, Province.Avellino ,City.Avellino,"","5"
				  );
	  }

	//Verifica dell'attivazione dell'eccezione in caso in cui non venga inserita una strada
	@Test(expected = IllegalArgumentException.class)
	 public void CountryStreetNumberNotFound() {
		  Location l = new Location(
				  Country.Italy, Region.Campania, Province.Avellino ,City.Avellino,"Via piane",""
				  );
	  }
	 
	
	
	
	
	 static Stream<String> ValidCoutry() {
	        return Stream.of(
	        		"Italy",
	        		"Spain",
	        		"Croatia",
	        		"Greece"
	        );
	    }
	 
	 static Stream<String> ValidRegion() {
	        return Stream.of(
	        		"Abruzzo", 
	        		"Basilicata", 
	        		"Bolzano", 
	        		"Calabria", 
	        		"Campania", 
	        		"Emilia_Romagna", 
	        		"Friuli_Venezia_Giulia", 
	        		"Lazio",	
	        		"Liguria", 
	        		"Lombardia",	
	        		"Marche",	
	        		"Molise", 
	        		"Piemonte", 
	        		"Puglia", 
	        		"Sardegna", 
	        		"Sicilia", 
	        		"Toscana", 
	        		"Trento", 
	        		"Umbria", 
	        		"Valle_d_Aosta", 
	        		"Veneto"
	        );
	    }
	 
	 static Stream<String> ValidProvince() {
	        return Stream.of(
	        		"Chieti",
	        		"Potenza", 
	        		"Bolzano",
	        		"Catanzaro",
	        		"Napoli",
	        		"Bologna", 
	        		"Udine", 
	        		"Roma", 
	        		"Genova", 
	        		"Varese", 
	        		"Ancona", 
	        		"Campobasso", 
	        		"Torino", 
	        		"Bari", 
	        		"Cagliari", 
	        		"Palermo", 
	        		"Firenze", 
	        		"Trento", 
	        		"Perugia", 
	        		"Aosta", 
	        		"Padova", 
	        		"Benevento", 
	        		"Avellino", 
	        		"Salerno", 
	        		"Caserta"
	        );
	    }
	 
	 static Stream<String> ValidCity() {
	        return Stream.of(
	        		"Chieti",
	        		"Potenza", 
	        		"Bolzano",
	        		"Catanzaro",
	        		"Napoli",
	        		"Bologna", 
	        		"Udine", 
	        		"Roma", 
	        		"Genova", 
	        		"Varese", 
	        		"Ancona", 
	        		"Campobasso", 
	        		"Torino", 
	        		"Bari", 
	        		"Cagliari", 
	        		"Palermo", 
	        		"Firenze", 
	        		"Trento", 
	        		"Perugia", 
	        		"Aosta", 
	        		"Padova", 
	        		"Benevento", 
	        		"Avellino", 
	        		"Salerno", 
	        		"Caserta"
	        );
	    }
	
	}
	

