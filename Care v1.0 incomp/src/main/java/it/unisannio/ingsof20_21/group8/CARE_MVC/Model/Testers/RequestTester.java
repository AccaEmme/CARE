package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Testers;

import java.text.ParseException;
import java.util.Date;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.RequestManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Exceptions.NullPasswordException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Exceptions.UserException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodGroup;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Node.Node;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Request.Request;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.City;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.Country;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.Province;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.Region;

public class RequestTester {

	public static void main(String[] args) throws UserException, NullPasswordException, ParseException {
		// TODO Auto-generated method stub
		
		
		RequestManager vogliovogliovoglio = new RequestManager();
		Location	l = new Location( Country.Italy, Region.Campania, Province.Benevento, City.Benevento, "via Bellabona", "55", "82010" );
		Node		n = new Node("206", "Rummo", l);
		User 		u = new User("GiulianoDeAmicis", "Nemicus4Ever+");
		BloodBag 	b = new	BloodBag(BloodGroup.Apos, "DMCGLN94D14A504Q",n);
		vogliovogliovoglio.pushRequest(	new Request(u,b,new Date()) );
	
		vogliovogliovoglio.print(System.out);
		
	}

}
