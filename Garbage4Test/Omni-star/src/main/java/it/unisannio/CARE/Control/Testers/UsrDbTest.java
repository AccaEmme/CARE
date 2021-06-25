package it.unisannio.CARE.Control.Testers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unisannio.CARE.Control.Classes.AdminInterface;
import it.unisannio.CARE.Control.Classes.WareHouseWorkerInterface;
import it.unisannio.CARE.Control.u.Managers.MongoDataManager;
import it.unisannio.CARE.Exceptions.NullPasswordException;
import it.unisannio.CARE.Exceptions.UserException;
import it.unisannio.CARE.Model.Classes.BloodBag;
import it.unisannio.CARE.Model.Classes.User;
import it.unisannio.CARE.Model.Classes.BloodBag.BloodBagState;
import it.unisannio.CARE.Model.Classes.Interfaces.BloodBagInterface;
import it.unisannio.CARE.Model.Classes.Interfaces.StoreManagerInterface;
import it.unisannio.CARE.Model.Node.Node;
import it.unisannio.CARE.Model.Util.BloodGroup;
import it.unisannio.CARE.Model.Util.Location;
import it.unisannio.CARE.Model.Util.Serial;
import it.unisannio.CARE.Model.Util.Location.City;
import it.unisannio.CARE.Model.Util.Location.Country;
import it.unisannio.CARE.Model.Util.Location.Province;
import it.unisannio.CARE.Model.Util.Location.Region;






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
		
		BloodBagInterface bbi1 = new BloodBag(s,BloodGroup.Apos,d4,d1,CF1,n1,BloodBagState.Dropped,"");
		BloodBagInterface bbi2 = new BloodBag(s,BloodGroup.Apos,d4,d2,CF1,n1,BloodBagState.Dropped,"");
		BloodBagInterface bbi3 = new BloodBag(s,BloodGroup.Apos,d4,d3,CF1,n1,BloodBagState.Dropped,"");
		
	//	public BloodBag( Serial s,BloodGroup b, Date creationD,Date expirationD,String donator,Node n,BloodBagState BagState,String not) 
	//dm.updateUser(u);

	/*	u0.setRole(Role.Officer);*/
		dm.addUser(u0);
		dm.addUser(u1);


		mi.addBloodBag(bbi1);
		mi.addBloodBag(bbi2);
		mi.addBloodBag(bbi3);
//dm.report();



/*
mi.getBloodBagExpiring( cds , BloodGroup.Bpos);*/

	}
}

