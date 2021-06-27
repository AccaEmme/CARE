package it.unisannio.CARE.Model.User;


import java.util.Date;

import org.bson.Document;

import it.unisannio.CARE.Model.Exceptions.NullPasswordException;
import it.unisannio.CARE.Model.Exceptions.UserException;
import it.unisannio.CARE.Model.Util.Constants;
import it.unisannio.CARE.Model.Util.Password;
import it.unisannio.ingsof20_21.group8.Care.Spring.UserBean;

public class User {
	private String 		username, hiddenPassword, temppass, plainTextPassword, email;
	//private Location 	residence;
	private Role 		role;
	private Date			password_lastupdate;
	
	
	// Costruttore creazione utente
	public User(String username, String plainTextPassword, Role role) {
		this.username 		= username;
		this.hiddenPassword	= Password.getMd5(plainTextPassword);
		this.role = role;
		this.password_lastupdate = null;
		//this.temppass = temppass;
	}

	
	
	   
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
	   /*
    public User(String username, String plainTextPassword) throws UserException, NullPasswordException {
        this.validateCredentials(username,plainTextPassword);
        this.username = username;
        this.setPassword(plainTextPassword); 
    }*/
    
    /**
	**************************************************************************
	 * Metodo per la creazione dell'utente con una passw criptata
	 * @param String username, String hiddenPassword
	 * @exception UserException, NullPasswordException
	 **************************************************************************
    */
	   /*
    public User(String username, Password hiddenPassword) throws UserException, NullPasswordException {
        this.validateCredentials(username,hiddenPassword.getHiddenPassword());    //il metodo per prendere la password è getHiddenPassword
        this.username = username;
        this.password = hiddenPassword.getHiddenPassword();
    }
    */
    /**
	**************************************************************************
	 * Metodo per la creazione dell'utente con il nome dell'utente ed il ruolo
	 * @param String username, Role role
	 **************************************************************************
    */
	   /*
    @SuppressWarnings("deprecation")
	public User(String username, Role role) {
    	// This constructor can be invoked by Administrator creates users.
    	
    	this.username	= username;
    	this.temppass	= Password.generatePassword(Constants.USER_TEMPPASS_LENGTH);
    	this.setPassword(temppass);
    	this.role		= role;
    	
    	this.password_lastupdate = new Date();
    }
    */
	   

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
	 * Metodo GET per ottenere lo username 
	 * @return username
	 **************************************************************************
    */
    public String getUsername() {
        return username;
    }

    /**
	**************************************************************************
	 * Metodo SET per modificare lo username 
	 * @param String username 
	 **************************************************************************
    */
    public void setUsername(String username) {
        this.username = username;
    } 

    /**
	**************************************************************************
	 * Metodo GET per ottenere la hidden password
	 * @return password
	 **************************************************************************
    */
    public String getPassword() {
        return hiddenPassword;
    }

    /**
   	**************************************************************************
   	 * Metodo SET per il moodificare la password
   	 * @param String plainTextPassword
   	 **************************************************************************
       */
    public void setPassword(String plainTextPassword) {
    	Password.validatePlaintextPasswordPattern(plainTextPassword);
        this.hiddenPassword			= Password.getMd5( plainTextPassword + Constants.USER_MD5_SALT );
		/*
        System.out.println("plainTextPassword: "+plainTextPassword
				+"\t Constants.USER_MD5_SALT: "+ Constants.USER_MD5_SALT
				+"\t hiddenPassword:" + this.password);
				*/
        this.password_lastupdate 	= new Date();		// *** nei JUnit test stampa a video la data corrente, perché?
        this.temppass				= "";
    }
    
    /**
	**************************************************************************
	 * Metodo GET per ottenere l'ultima password registrata
	 * @return password_lastupdate
	 **************************************************************************
    */
    public Date getPasswordLastUpdate() {
    	return password_lastupdate;
    }
    
    
   
    
    public void setEmail(String email) {
		this.email = email;
	}




	/**
	**************************************************************************
	 * Metodo GET per ottenere il ruolo dell'utente
	 * @return role
	 **************************************************************************
    */
    public Role getRole() {
    	return role;
    }
    
    /**
	**************************************************************************
	 * Metodo SET per modificare il Ruolo
	 * @param Role r
	 **************************************************************************
    */
    public void setRole(Role r) {
    	this.role = r;
    }
   
    /**
	**************************************************************************
	 * Metodo per il return dell'utente come document xml
	 * @return document
	 **************************************************************************
    */
    public Document getDocument(){
        Document document = new Document("username",this.getUsername());
        document.append("password",this.getPassword());
        if (this.getRole()!=null)
            document.append("role",this.getRole().toString());
        if (this.getPasswordLastUpdate()!=null)
            document.append("password_last_update",this.getPasswordLastUpdate());
 
        return document;
    }

    
    /**
	 **************************************************************************
	 * Metodo per eseguire il parsing da User in UserBean
	 * @return UserBean
	 **************************************************************************
	 */
    public UserBean	getUserBean() {
    	UserBean ub = new UserBean();
    	ub.setUsername(this.username);
    	ub.setPassword(this.hiddenPassword);
    	ub.setEmail(this.email);
    	ub.setUserRole( this.role.toString() );
    	
		return ub;
    }
    
    /**
	 **************************************************************************
	 * Metodo per il tester Junit per verificare l'esistenza dell'oggetto USER
	 * @return true
	 **************************************************************************
	 */
    public boolean exists() {
		return true;
	}
}