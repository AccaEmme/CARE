package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User;

import java.util.Date;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.Exceptions.UserException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Constants;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Exceptions.NullPasswordException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Password;
import org.bson.Document;

public class User {
	/*
	 * Il costruttore genera una sessione utente.
	 * Il costruttore genera l'MD5 della password data in input, per confrontarla.
	 * Quando l'amministratore crea un utente attribuendogli un ruolo, la password viene generata in automatico ed è visibile.
	 * Al primo cambio password dell'utente, viene eliminata la password temporanea, impostata la password cifrata e viene segnato l'ultimo cambio password.
	 */

    /**
     * @param flag  @<code>flag = 1</code> if you want to encode the password
     *              @<code>flag = 0</code> if you dont want to encode the password
     *              OLD!*/
    /*
	public User(String username, String password, Boolean flag){
    	this.username 	= username;
    	if (flag)
            this.setPassword(password);
    	else this.password = password;
    	//this.session 	= MD5.getMd5(username).toUpperCase();
    }*/
    public User(String username, String password) throws UserException, NullPasswordException {
        this.validateCredentials(username,password);
	    this.username = username;
	    this.setPassword(password);
    }
    public User(String username, Password password) throws UserException, NullPasswordException {
        this.validateCredentials(username,password.getPassword());
	    this.username = username;
	    this.password = password.getPassword();
    }
    private void validateCredentials(String username, String password) throws UserException, NullPasswordException {
        if (username == null)
            throw new UserException("The username cannot be null!");
        if (password == null)
            throw new NullPasswordException("The password cannot be null!");
        if (username.length() < 5 || password.length() < 5)
            throw new UserException("The username or the password cannot be shorter than 5 chars");
    }

    
    @SuppressWarnings("deprecation")
	public User(String username, Role role) {
    	// This constructor can be invoked by Administrator creates users.
    	
    	this.username	= username;
    	this.temppass	= String.valueOf(
    								Password.generatePassword(Constants.USER_TEMPPASS_LENGTH)
    						); 
    	this.setPassword(password);
    	this.role		= role;
    	
    	this.password_lastupdate = new Date("1900-01-01");
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
        this.password 				= Password.getMd5( password+Constants.USER_MD5_SALT ).toUpperCase();
        this.password_lastupdate 	= new Date();
        this.temppass="";
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
    public Document getDocument(){
        Document document = new Document("username",this.getUsername());
        document.append("password",this.getPassword());
        if (this.getResidence()!=null)
            document.append("location",this.getResidence());
        if (this.getRole()!=null)
            document.append("role",this.getRole().toString());
        if (this.getPasswordLastUpdate()!=null)
            document.append("password_last_update",this.getPasswordLastUpdate());

        return document;
    }
   
   
    private String 		username, password, temppass;
    private Location 	residence;
    private Role 		role;
    private Date		password_lastupdate;
}

