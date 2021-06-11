package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Exceptions.StreetNotFounfException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.City;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.Country;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.Province;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.Region;

public class LocationJunitTest {
	
	/*@Test
	 * Mancano i paesi che possono avere un ospedale
	  public void Country() {
		  Location l = new Location(Country.Italy, Region.Campania, Province.Avellino ,City.ArianoIrpino,"25 Aprile","5");
		  assertTrue(l.exists());
	  }*/
	
	
	@Test
	 public void VerifyCreateCountry(){
		  Location l = new Location(Country.Italy, Region.Campania, Province.Avellino ,City.Avellino,"25 Aprile","5");
		  assertTrue(l.exists());
	  }
	
	@Test(expected = StreetNotFounfException.class)
	 public void Country1(){
		  Location l = new Location(Country.Italy, Region.Campania, Province.Avellino ,City.Avellino,"","5");
	  }

	
	  
	}
	

