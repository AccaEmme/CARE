package it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.Testers.MongoManagerTesters;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.AdminInterface;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.MongoDataManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.WareHouseWorkerInterface;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag.BloodBagState;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodGroup;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Serial;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Interfaces.BloodBagInterface;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Interfaces.StoreManagerInterface;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Node.Node;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.Exceptions.UserException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.City;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.Country;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.Province;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.Region;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Exceptions.NullPasswordException;






public class UsrDbTest {
public static void main(String[] args) throws ParseException, UserException, NullPasswordException {
		
	Logger mongoLogger = Logger.getLogger( "org.mongodb.driver");
	mongoLogger.setLevel(Level.SEVERE);

	  AdminInterface dm = new MongoDataManager();
	  WareHouseWorkerInterface mi=new MongoDataManager();
		dm.createDB();
	
		User u0=new User("giangi","12345Aa@21");
		User u1=new User("lucalucone","12345Aa@11");
		Node n1 = new Node("999","morte",		new Location(Country.Italy, Region.Campania, Province.Benevento, City.Benevento, "via Roma", "44C","82023"));
		String CF1="BNCMRA86A41A509Y";
		//BloodBag bb = new BloodBag(BloodGroup.Bpos, CF1);
		BloodBagInterface bbi = new BloodBag(BloodGroup.Bpos, CF1, n1); 
		
		Serial s=new Serial(BloodGroup.Apos);
		DateFormat format=new  SimpleDateFormat("yyyy-MM-dd");
		Date d1=format.parse("2021-06-13");
		Date d2=format.parse("2021-06-08");
		Date d3=format.parse("2021-06-06");
		Date d4=format.parse("1999-06-06");
		
		BloodBag bbi1 = new BloodBag(s,BloodGroup.Apos,d4,d1,CF1,n1,BloodBagState.Dropped,"");
		BloodBag  bbi2 = new BloodBag(s,BloodGroup.Apos,d4,d2,CF1,n1,BloodBagState.Dropped,"");
		BloodBag bbi3 = new BloodBag(s,BloodGroup.Apos,d4,d3,CF1,n1,BloodBagState.Dropped,"");
		
	//	public BloodBag( Serial s,BloodGroup b, Date creationD,Date expirationD,String donator,Node n,BloodBagState BagState,String not) 
	//dm.updateUser(u);

	/*	u0.setRole(Role.Officer);*/
		/*dm.addUser(u0);
		dm.addUser(u1);*/


		mi.addBloodBag(bbi1);
		mi.addBloodBag(bbi2);
		mi.addBloodBag(bbi3);
//dm.report();



/*
mi.getBloodBagExpiring( cds , BloodGroup.Bpos);*/

	}
}

