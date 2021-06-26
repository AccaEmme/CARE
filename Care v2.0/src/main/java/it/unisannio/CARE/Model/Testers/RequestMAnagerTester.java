package it.unisannio.CARE.Model.Testers;

import java.text.ParseException;
import java.util.Date;

import it.unisannio.CARE.Control.Classes.RequestManager;
import it.unisannio.CARE.Model.Classes.*;
import it.unisannio.CARE.Model.Classes.BloodBag.BloodBagState;
import it.unisannio.CARE.Model.Classes.Request.RequestPriority;
import it.unisannio.CARE.Model.Classes.Request.RequestState;
import it.unisannio.CARE.Model.Util.BloodGroup;
import it.unisannio.CARE.Model.Util.Serial;

public class RequestMAnagerTester {

	public static void main(String[] args) throws ParseException {

		BloodBag bb=new BloodBag(BloodGroup.ABpos,"RSSMRA80A01A509I");
		
		RequestManager rm=new RequestManager("mongodb+srv://ricciuto99:desk9123@cluster0.ksjti.mongodb.net/test","CARE","richieste");
  Request r=new Request("idlocatonprova","idrichiestaprova", bb, new Date(), "notaProva", RequestState.pending, RequestPriority.red);
  rm.addRequest(r);
	}
	
}
