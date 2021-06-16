package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood;

import static org.junit.Assert.assertTrue;

import java.util.stream.Stream;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Exceptions.StreetNotFoundException;
import org.junit.Test;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.City;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.Country;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.Province;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.Region;

public class LocationTest {


	/* TEST PER INFORMAZIONI VALIDE DELLA CLASSE LOCATION.JAVA */


	//Verifica del corretto funzionamento della classe Location
	@Test
	 public void VerifyCreateCountry() throws StreetNotFoundException {
		Location l = new Location(
				Country.Italy, Region.Campania, Province.Avellino ,City.Avellino,"via 25 Aprile","5"
				);
		assertTrue(l.exists());
	}

	/* ATTENZIONE VEDERE PROBLEMA TO STRING*/
	//Verifica del corretto funzionamento del metodo ToString
	@Test ()
	 public void VerifyToString() throws StreetNotFoundException {
		Location l = new Location(
			Country.Italy, Region.Campania, Province.Avellino ,City.Avellino,"via 25 Aprile","5"
			);
		l.toString();
	}

	//Verifica il corretto inserimento delle Nazioni
	@ParameterizedTest(name = "#{index} - Run test with valid Coutry = {0}")
	@MethodSource(string="ValidCoutry")
	 public void test_ValidCoutry(String CoutryT) throws StreetNotFoundException {
		Location l = new Location(
				Country.Italy, Region.Campania, Province.Avellino ,City.Avellino,"via 25 Aprile","5"
				);
		l.setCountry(CoutryT);
	}

	//Verifica il corretto inserimento delle Regioni
	@ParameterizedTest(name = "#{index} - Run test with valid Region = {0}")
    @MethodSource(string="ValidRegion")
	 public void test_ValidRegion(String RegionT) throws StreetNotFoundException {
		Location l = new Location(
				Country.Italy, Region.Campania, Province.Avellino ,City.Avellino,"via 25 Aprile","5"
				);
		l.setRegion(RegionT);
	}

	//Verifica il corretto inserimento delle Province
	@ParameterizedTest(name = "#{index} - Run test with valid Province = {0}")
	@MethodSource(string="ValidProvince")
	 public void test_ValidProvince(String ProvinceT) throws StreetNotFoundException {
		Location l = new Location(
				Country.Italy, Region.Campania, Province.Avellino ,City.Avellino,"via 25 Aprile","5"
				);

		l.setProvince(ProvinceT);
	}


	//Verifica il corretto inserimento delle Province
	@ParameterizedTest(name = "#{index} - Run test with valid City = {0}")
	@MethodSource(string="ValidCity")
	 public void test_ValidCity(String CityT) throws StreetNotFoundException {
		Location l = new Location(
			Country.Italy, Region.Campania, Province.Avellino ,City.Avellino,"via 25 Aprile","5"
			);
		l.setCity(CityT);
	}




	/* TEST PER INSERIMENTO DELLE INFORMAZIONI NON VALIDE NELLA CLASSE LOCATION.JAVA */


	//Verifica il non corretto inserimento di una Nazione
	@Test(expected = IllegalArgumentException.class)
	 public void InvalidCoutry() throws StreetNotFoundException {
		  Location l = new Location(
				  Country.Italy, Region.Campania, Province.Avellino ,City.Avellino,"","5"
				  );
		  l.setCountry("Germania");
	 }

	//Verifica il non corretto inserimento di una Regione
	@Test(expected = IllegalArgumentException.class)
	 public void InvalidRegion() throws StreetNotFoundException {
		Location l = new Location(
			Country.Italy, Region.Campania, Province.Avellino ,City.Avellino,"","5"
			);
		l.setRegion("Leonard");
	}

	//Verifica il non corretto inserimento di una Provincia
	@Test(expected = IllegalArgumentException.class)
	 public void InvalidProvince() throws StreetNotFoundException {
		Location l = new Location(
				Country.Italy, Region.Campania, Province.Avellino ,City.Avellino,"","5"
				);
		l.setProvince("Bazinga");
	}

	//Verifica il non corretto inserimento di una Città
	@Test(expected = IllegalArgumentException.class)
	public void InvalidCity() throws StreetNotFoundException {
		Location l = new Location(
				Country.Italy, Region.Campania, Province.Avellino ,City.Avellino,"","5"
				);
		l.setCity("Penny");
	}

	//Verifica dell'attivazione dell'eccezione in caso in cui non venga inserita una strada
	@Test(expected = IllegalArgumentException.class)
	 public void ContryStreetNotFound() throws StreetNotFoundException {
		  Location l = new Location(
				  Country.Italy, Region.Campania, Province.Avellino ,City.Avellino,"","5"
				  );
	  }

	//Verifica dell'attivazione dell'eccezione in caso in cui non venga inserita una strada
	@Test(expected = IllegalArgumentException.class)
	 public void CountryStreetNumberNotFound() throws StreetNotFoundException {
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
	

