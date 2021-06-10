package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Interfaces;

public interface AdministratorInterface {

	public void addUser();
	public void editUser();
	public void dropUser();
	public void setRole();
	
	public boolean setRoutingTable();

	public boolean reportGiacenza();
}
