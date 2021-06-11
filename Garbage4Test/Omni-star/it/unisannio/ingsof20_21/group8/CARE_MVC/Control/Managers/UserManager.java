package it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers;

import java.util.Calendar;
import java.util.Date;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.User.LoginInterface;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Interfaces.AdministratorInterface;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Node.Node;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.Role;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Logger;

public abstract class UserManager implements AdministratorInterface, LoginInterface {
	
	private User currentUser;
	private Node currentNode;
	private DataManager dataManager;
	
	public UserManager(User currentUser, Node currentNode, DataManager dataManager) {
		
		this.currentUser = currentUser;
		this.currentNode = currentNode;
		this.dataManager = dataManager;
	}

	public void addUser(User userToAdd) {
		
		dataManager.addUser(userToAdd);
		dataManager.writeLog(
						new Logger(currentUser, "SystemManager", "addUser:"+userToAdd.toString(), "ok")
				);
	}
	
	public void editUser(User userToModified) {
		
		dataManager.updateUser(userToModified);
		dataManager.writeLog(
						new Logger(currentUser, "SystemManager", "editUser:"+userToModified.toString(), "ok")
				);
	}
	
	public void dropUser(User userToModified) {
		
		dataManager.dropUser(userToModified);
		dataManager.writeLog(
						new Logger(currentUser, "SystemManager", "dropUser:"+userToModified.toString(), "ok")
				);
	}
	
	public void setRoleUser(User userToModified, Role r) {
		
		dataManager.setRole(userToModified, r);
		dataManager.writeLog(
						new Logger(currentUser, "SystemManager", "setRole:"+userToModified.toString(), "ok")
				);
	}
	
	//##################################################################################

	public void updateNodeName(String newName){
		
		currentNode.setNodeName(newName);
		dataManager.updateNode(currentNode);
		dataManager.writeLog( new Logger(currentUser, "SystemManager", "updateNodeName:"+ currentNode.toString(), "ok") );
	}
	
	public void updateNodeCode(String newCode){
		
		currentNode.setCodStr(newCode);
		dataManager.updateNode(currentNode);
		dataManager.writeLog( new Logger(currentUser, "SystemManager", "updateNodeCode:"+ currentNode.toString(), "ok") );
		
	}
	
	public void updateLocation(Location newLocation){
		
		currentNode.setWarehouse(newLocation);
		dataManager.updateNode(currentNode);
		dataManager.writeLog( new Logger(currentUser, "SystemManager", "updateLocation:"+ currentNode.getWarehouse().toString(), "ok") );
	}
	
	//##################################################################################
	
	//Omni-star: come implementare?
	public void reportGiacenza() {
		
	}
	
}
