package datamanagers;

import java.text.ParseException;

import users.User;

public interface adminInterface extends DataManager {
	void addUser(User u0) throws ParseException;
	public void deleteUser(User  u) throws ParseException;
	public void editUser(User  u) throws ParseException;
}
