package datamanagers;

import java.text.ParseException;


import users.BloodBagInterface;




/* operaazioni che un magazziniere puo effettuare riguardanti la collezione di dati*/
//aggiungere user e operazioni coorelate


public interface magazziniereInterface extends DataManager {
	public void addBloodBag(BloodBagInterface bbi) throws ParseException;
	
	/*public void cercaSaccheCheScadonoinsettimana() throws ParseException;*/
}
