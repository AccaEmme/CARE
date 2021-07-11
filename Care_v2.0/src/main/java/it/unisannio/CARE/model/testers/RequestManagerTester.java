package it.unisannio.CARE.model.testers;

import java.text.ParseException;
import java.util.Date;

import it.unisannio.CARE.controll.request.RequestManager;
import it.unisannio.CARE.model.bloodBag.BloodBag;
import it.unisannio.CARE.model.bloodBag.BloodGroup;
import it.unisannio.CARE.model.bloodBag.RequestPriority;
import it.unisannio.CARE.model.bloodBag.RequestState;
import it.unisannio.CARE.model.util.Password;

public class RequestManagerTester {

	public static void main(String[] args) throws ParseException {

		BloodBag bb=new BloodBag(BloodGroup.ABpos,"RSSMRA80A01A509I");
		
		System.out.println(Password.getBCrypt("Care@2021"));
		
		RequestManager rm=new RequestManager("mongodb+srv://ricciuto99:desk9123@cluster0.ksjti.mongodb.net/test","CARE","requestes");
  
		//Request r = new Request("3","1", bb.getSerial().toString(), new Date(), "notaProva", RequestState.pending, RequestPriority.red);
  
 
		//rm.addRequest(r);
	}
	
}
