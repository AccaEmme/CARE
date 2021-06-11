package it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Node.Node;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.Role;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Logger;

import java.util.Date;
import java.util.List;


public interface DataManager {
	void createDB();
	void dropDB();
	
	// filling Working tables.
	void setStateTable(/*short id_state, */String state);

	void addBloodBag(BloodBag bloodbag);
	List<BloodBag> getBloodBag(BloodBag blood);
	
	void addNode (Node node);
	void updateNode(Node currentNode);
	
	void addUser (User user);
	void updateUser(User user);
	void dropUser(User user);
	
	public void setRole(User user, Role role);
	
	void updateExpirationDate(BloodBag b, Date newExpirationDate);
	void writeLog(Date currentDate, User currentUser, String currentClass, String currentMethod, String currentResult);
	
	public void restoreDump(String filename);
	public boolean createDump();
	void writeLog(Logger logger);
}
