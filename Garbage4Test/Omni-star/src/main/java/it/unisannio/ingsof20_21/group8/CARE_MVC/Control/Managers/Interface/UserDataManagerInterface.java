package it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.Interface;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;

public interface UserDataManagerInterface {

	void addUser(User user);
	
	void deleteUser(User user);
	
	void assignRole(User user);
}
