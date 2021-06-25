package prove;

import java.util.Date;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Constants;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.MD5;

public class User {
    public User(String username, String password){
    	/*
    	 * Il costruttore genera una sessione utente.
    	 * Il costruttore genera l'MD5 della password data in input, per confrontarla.
    	 */
    	this.username 	= username;
    	this.password 	= MD5.getMd5( password+Constants.MD5_SALT ).toUpperCase();
    	//this.session 	= MD5.getMd5(username).toUpperCase();
    }
    
    public User(String username, Role role) {
    	// This constructor can be invoked by Administrator creates users.
    	
    	
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password 				= MD5.getMd5( password+Constants.MD5_SALT ).toUpperCase();
        this.password_lastupdate 	= new Date(); 
    }
    
    public Date getPasswordLastUpdate() {
    	return this.password_lastupdate;
    }
    
    public Location getResidence() {
    	return residence;
    }
    
    public void setResidence(Location residence) {
    	this.residence=residence;
    }
    
    public Role getRole() {
    	return this.role;
    }
    
    public void setRole(Role r) {
    	this.role = r;
    }
    
    private String 		username, password;
    private Location 	residence;
    private Role 		role;
    private Date		password_lastupdate;
}
