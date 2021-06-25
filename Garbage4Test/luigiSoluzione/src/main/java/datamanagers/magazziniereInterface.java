package datamanagers;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import users.BloodBagInterface;
import users.BloodGroup;




/* operaazioni che un magazziniere puo effettuare riguardanti la collezione di dati*/
//aggiungere user e operazioni coorelate


public interface magazziniereInterface extends DataManager {
	public void addBloodBag(BloodBagInterface bbi) throws ParseException;
	
/*	public List<BloodBag> getBloodBagExpiring(Date d,BloodGroup b)throws ParseException;*/
}
