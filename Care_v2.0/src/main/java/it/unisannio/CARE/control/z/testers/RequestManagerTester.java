package it.unisannio.CARE.control.z.testers;

import java.text.ParseException;
import java.util.Date;

import it.unisannio.CARE.model.bloodBag.*;
import it.unisannio.CARE.model.request.RequestPriority;
import it.unisannio.CARE.model.request.RequestState;
import it.unisannio.CARE.control.request.RequestManager;

public class RequestManagerTester {

	public static void main(String[] args) throws ParseException {

		BloodBag bb=new BloodBag(BloodGroup.ABpos,"RSSMRA80A01A509I");
		
		
		
		RequestManager rm=new RequestManager("mongodb+srv://ricciuto99:desk9123@cluster0.ksjti.mongodb.net/test","CARE","requestes");
  
		//Request r = new Request("3","1", bb.getSerial().toString(), new Date(), "notaProva", RequestState.pending, RequestPriority.red);
  
 
		//rm.addRequest(r);
	}
	
}
