package it.unisannio.CARE.Model.Testers;

import java.text.ParseException;
import java.util.Date;

import it.unisannio.CARE.Control.BloodBags.RequestManager;
import it.unisannio.CARE.Model.BloodBag.*;
import it.unisannio.CARE.Model.BloodBag.Request.RequestPriority;
import it.unisannio.CARE.Model.BloodBag.Request.RequestState;

public class RequestManagerTester {

	public static void main(String[] args) throws ParseException {

		BloodBag bb=new BloodBag(BloodGroup.ABpos,"RSSMRA80A01A509I");
		
		/*RequestManager rm=new RequestManager("mongodb+srv://ricciuto99:desk9123@cluster0.ksjti.mongodb.net/test","CARE","richieste");
  Request r=new Request("idlocatonprova","idrichiestaprova", bb, new Date(), "notaProva", RequestState.pending, RequestPriority.red);
  rm.addRequest(r);
  */
	}
	
}
