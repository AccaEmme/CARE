package prove;

import java.io.Serializable;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;



@SuppressWarnings("serial")

public class UserBean implements Serializable {
	
	private String username;
	private String password;
	
	public UserBean() {}
	
	

	public UserBean(User u) {
		this.username=u.getUsername();
		this.password=u.getPassword();
	}
	
	public String getUsername(){
		return username;
	}
	
	public String getPassword(){
		return password;
	}
	
	
	public void setPassword(String p){
		this.password=p;
	}

	public void setUsername(String u){
	  this.username=u;
	}
	
	
	
	public String toString() {
		return username  + " - gruppo: " + password; 
	}
	

}
