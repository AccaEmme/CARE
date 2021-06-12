package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Interfaces;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.Role;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;

public interface AdministratorInterface {

	public void addUser(User u);
	public void editUser(User u);
	public void dropUser(User u);
	public void setRole(User u, Role r);
	
	public boolean setRoutingTable();

	public boolean reportGiacenza();
}
