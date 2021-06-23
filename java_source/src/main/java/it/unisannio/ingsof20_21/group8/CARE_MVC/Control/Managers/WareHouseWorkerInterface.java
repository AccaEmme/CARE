package it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Interfaces.BloodBagInterface;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Request.Request;




/* operaazioni che un magazziniere puo effettuare riguardanti la collezione di dati*/
//aggiungere user e operazioni coorelate


public interface WareHouseWorkerInterface extends DataManager {
	public void addBloodBag(BloodBag bbi) throws ParseException;
	
	public void acceptRequest(Request request);
	
/*	public List<BloodBag> getBloodBagExpiring(Date d,BloodGroup b)throws ParseException;*/
}
