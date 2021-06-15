package it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers;

import java.text.ParseException;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;


/* operazioni che un admin puo effettuare rigurdanti la collezione di dati*/
//aggiungere user e operazioni coorelate

public interface AdminInterface extends DataManager {
	void addUser(User u0) throws ParseException;
	public void deleteUser(User  u) throws ParseException;
	public void editUser(User  u) throws ParseException;
	public void report() throws ParseException;
}
