package it.unisannio.CARE.Control.Testers;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.bson.Document;

import it.unisannio.CARE.Control.Classes.MongoRequestDataManager;
import it.unisannio.CARE.Control.Interfaces.RequestDataManagerInterface;
import it.unisannio.CARE.Control.u.Managers.RequestManager;
import it.unisannio.CARE.Exceptions.NullPasswordException;
import it.unisannio.CARE.Exceptions.UserException;
import it.unisannio.CARE.Model.Classes.BloodBag;
import it.unisannio.CARE.Model.Classes.Request;
import it.unisannio.CARE.Model.Classes.User;
import it.unisannio.CARE.Model.Classes.BloodBag.BloodBagState;
import it.unisannio.CARE.Model.Node.Node;
import it.unisannio.CARE.Model.Util.BloodGroup;
import it.unisannio.CARE.Model.Util.Location;
import it.unisannio.CARE.Model.Util.Serial;
import it.unisannio.CARE.Model.Util.Location.City;
import it.unisannio.CARE.Model.Util.Location.Country;
import it.unisannio.CARE.Model.Util.Location.Province;
import it.unisannio.CARE.Model.Util.Location.Region;

public class RequestManagerTester {

	public static void main(String[] args) throws UserException, NullPasswordException, ParseException {
		
		RequestDataManagerInterface MongoManager = new MongoRequestDataManager("mongodb+srv://ricciuto99:desk9123@cluster0.ksjti.mongodb.net/test", "CARE");
		Location	l = new Location( Country.Italy, Region.Campania, Province.Benevento, City.Benevento, "via Bellabona", "55", "82010" );
		Node		n = new Node("206", "Rummo", l);
		User 		u = new User("GiulianoDeAmicis", "Nemicus4Ever+");
		
		BloodBag 	b = new	BloodBag(BloodGroup.Apos, "DMCGLN94D14A504Q",n);
		BloodBag 	b2 = new BloodBag(BloodGroup.Apos, "DMCGLN94D14A504Q",n);
		
		/*
		BloodBag 	b = new	BloodBag(new Serial("IT-NA205101-Apos-20210624-0005"), BloodGroup.Apos, new Date(), new Date(), "DMCGLN94D14A504Q",n, BloodBagState.Available, null);
		BloodBag 	b2 = new	BloodBag(new Serial("IT-NA205101-Apos-20210624-0006"), BloodGroup.Apos, new Date(), new Date(), "DMCGLN94D14A504Q",n, BloodBagState.Available, null);
		*/
		Request req = new Request(u,b,new Date());
		Request req2 = new Request(u,b2,new Date());
		
		MongoManager.addRequest(req);
		MongoManager.addRequest(req);
		//MongoManager.acceptRequest(req);
		MongoManager.addRequest(req2);
		/*
		MongoManager.refuseRequest(req);
		MongoManager.refuseRequest(req2);
		*/
		
		List<Document> documents1 = MongoManager.getRequestesInPendling();
		System.out.println("\nRichieste in attesa:\n");
		for(Document reqD : documents1) {
			
			System.out.println(reqD);
		}
		
		List<Document> documents2 = MongoManager.getRequestesAccepted();
		System.out.println("\nRichieste accettate:\n");
		for(Document reqD : documents2) {
			
			System.out.println(reqD);
		}
		
		List<Document> documents3 = MongoManager.getRequestesRefused();
		System.out.println("\nRichieste rifiutate:\n");
		for(Document reqD : documents3) {
			
			System.out.println(reqD);
		}
	/*
		System.out.println(req);
		System.out.println(req2);
	*/	
	}

}
