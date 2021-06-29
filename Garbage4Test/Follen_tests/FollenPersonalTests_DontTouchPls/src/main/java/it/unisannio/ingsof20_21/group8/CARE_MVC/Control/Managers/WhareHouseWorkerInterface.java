package it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Interfaces.BloodBagInterface;




/* operaazioni che un magazziniere puo effettuare riguardanti la collezione di dati*/
//aggiungere user e operazioni coorelate


public interface WhareHouseWorkerInterface extends DataManager {
	public void addBloodBag(BloodBagInterface bbi) throws ParseException;
	
/*	public List<BloodBag> getBloodBagExpiring(Date d,BloodGroup b)throws ParseException;*/
}
