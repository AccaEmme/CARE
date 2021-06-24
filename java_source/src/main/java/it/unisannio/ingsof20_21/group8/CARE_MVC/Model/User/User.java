package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User;

import java.io.IOException;
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
	**************************************************************************
	 * Metodo per la creazione dell'utente con una passw in chiaro
	 * @param String username, String plainTextPassword
	 * @exception UserException, NullPasswordException
	 **************************************************************************
    */
    public User(String username, String plainTextPassword) throws UserException, NullPasswordException {
        this.validateCredentials(username,plainTextPassword);
        this.username = username;
        this.setPassword(plainTextPassword); 
    }
    
    /**
	**************************************************************************
	 * Metodo per la creazione dell'utente con una passw criptata
	 * @param String username, String hiddenPassword
	 * @exception UserException, NullPasswordException
	 **************************************************************************
    */
    public User(String username, Password hiddenPassword) throws UserException, NullPasswordException {
        this.validateCredentials(username,hiddenPassword.getHiddenPassword());    //il metodo per prendere la password è getHiddenPassword
        this.username = username;
        this.password = hiddenPassword.getHiddenPassword();
    }
    
    /**
	**************************************************************************
	 * Metodo per la creazione dell'utente con il nome dell'utente ed il ruolo
	 * @param String username, Role role
	 **************************************************************************
    */
    @SuppressWarnings("deprecation")
	public User(String username, Role role) {
    	// This constructor can be invoked by Administrator creates users.
    	
    	this.username	= username;
    	this.temppass	= String.valueOf(
    								Password.generatePassword(Constants.USER_TEMPPASS_LENGTH)
    						);
    	this.setPassword(temppass);
    	this.role		= role;
    	
    	this.password_lastupdate = new Date();
    }

    /**
	**************************************************************************
	 * Metodo per la validazione delle credenziali dell'utente
	 * @param String username, String password
	 * @exception UserException, NullPasswordException
	 **************************************************************************
    */
    public void validateCredentials(String username, String password) throws UserException, NullPasswordException {
        if (username == null)
            throw new UserException("The username cannot be null!");  
        if (password == null || password ==  " ")
            throw new NullPasswordException("The password cannot be null!");
        if (username.length() < 5 || password.length() < 5)
            throw new UserException("The username or the password cannot be shorter than 5 chars");
    }
    
    /**
	**************************************************************************
	 * Metodo per la validazione delle credenziali dell'utente
	 * @param String username, String password
	 * @exception UserException, NullPasswordException
	 **************************************************************************
    */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    } 

    public String getPassword() {
        return password;
    }

    public void setPassword(String plainTextPassword) {
    	Password.validatePlaintextPasswordPattern(plainTextPassword);
        this.password 				= Password.getMd5( plainTextPassword + Constants.USER_MD5_SALT );
		/*
        System.out.println("plainTextPassword: "+plainTextPassword
				+"\t Constants.USER_MD5_SALT: "+ Constants.USER_MD5_SALT
				+"\t hiddenPassword:" + this.password);
				*/
        this.password_lastupdate 	= new Date();		// *** nei JUnit test stampa a video la data corrente, perché?
        this.temppass				= "";
    }
    
    public Date getPasswordLastUpdate() {
    	return password_lastupdate;
    }
    
    public Location getResidence() {
    	return residence;
    }
    
    public void setResidence(Location residence) {
    	this.residence=residence;
    }
    
    public Role getRole() {
    	return role;
    }
    
    public void setRole(Role r) {
    	this.role = r;
    }
   
    public Document getDocument(){
        Document document = new Document("username",this.getUsername());
        document.append("password",this.getPassword());
        if (this.getResidence()!=null)
            document.append("location",this.getResidence().getDocument());
        if (this.getRole()!=null)
            document.append("role",this.getRole().toString());
        if (this.getPasswordLastUpdate()!=null)
            document.append("password_last_update",this.getPasswordLastUpdate());
 
        return document;
    }
    
    public boolean exists() {
		return true;
	}
   
   
    private String 		username, password, temppass;
    private Location 	residence;
    private Role 		role;
    private Date		password_lastupdate;
}