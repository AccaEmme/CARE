package it.gc.Prj2021Pillola04.persistence;

import java.text.ParseException;

import users.User;

public interface UserAdministrator extends DataManager {

	
	public void addUser(User u) throws ParseException;
	
	public void deleteUser(User u)throws ParseException;
	
	public void editUser(User u)throws ParseException;
	
	
}
