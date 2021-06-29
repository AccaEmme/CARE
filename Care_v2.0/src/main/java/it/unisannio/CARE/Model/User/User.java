package it.unisannio.CARE.Model.User;


import java.util.Date;

import org.bson.Document;

import it.unisannio.CARE.Model.Exceptions.NullPasswordException;
import it.unisannio.CARE.Model.Exceptions.UserException;
import it.unisannio.CARE.Model.Util.Constants;
import it.unisannio.CARE.Model.Util.Password;
import it.unisannio.ingsof20_21.group8.Care.Spring.UserBean;


/**
 * Classe utilizzata per la creazione di un utente del programma CARE
 */

public class User {
	private String 		username, hiddenPassword, temppass, plainTextPassword, email;
	//private Location 	residence;
	private Role 		role;
	private Date			password_lastupdate;
	
	
	/**
	**************************************************************************
	 * Metodo per la creazione dell'utente con una passw in chiaro
	 * @param username - Nome dell'utente
	 * @param plainTextPassword - Passw dell'utente
	 * @param role - Ruolo dell'utente
	 **************************************************************************
    */
	public User(String username, String plainTextPassword, Role role) {
		this.setUsername(username);
		if(plainTextPassword.equals("")) {
			this.temppass 		= Password.generatePassword(10);
			this.hiddenPassword	= Password.getMd5(this.temppass);
		} else {
			this.temppass 		= "";
			this.hiddenPassword	= Password.getMd5(plainTextPassword);
		}
		System.out.println("temppass:" + this.temppass + " hiddenPass: " + this.hiddenPassword);
		this.role 			= role;
		
		//this.password_lastupdate = null;
		//
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
	 * Metodo per la gestione dell'utente con una passw criptata
	 * @param String nome utente
	 * @param String Passw criptata in MD5
	 * @exception UserException, NullPasswordException
	 **************************************************************************
    */
	 
    public User(String username, Password hiddenPassword) throws UserException, NullPasswordException {
        //this.validateCredentials(username,hiddenPassword.getHiddenPassword());    //il metodo per prendere la password è getHiddenPassword
        this.username 		= username;
        this.hiddenPassword = hiddenPassword.getHiddenPassword();
    }
   
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
	/*
    public void validateCredentials(String username, String password) throws UserException, NullPasswordException {
        if (username == null)
            throw new UserException("The username cannot be null!");  
        if (password == null || password ==  " ")
            throw new NullPasswordException("The password cannot be null!");
        if (username.length() < 5 || password.length() < 5)
            throw new UserException("The username or the password cannot be shorter than 5 chars");
    }
    */
    

	/**
	**************************************************************************
	 * Metodo GET per ottenere lo username 
	 * @return ritorna l'username dell'utente 
	 **************************************************************************
    */
    public String getUsername() {
        return username;
    }

    /**
	**************************************************************************
	 * Metodo SET per modificare lo username 
	 * @param String Nome utente che si vuole inserire
	 **************************************************************************
    */
    public void setUsername(String username) {
    	if(username.length()<3) throw new IllegalArgumentException("Username size too short, should be greater than 3 chars.");
        this.username = username;
    } 

    /**
	**************************************************************************
	 * Metodo GET per ottenere la hidden password
	 * @return ritorna la passw dell'utente
	 **************************************************************************
    */
    public String getPassword() {
        return hiddenPassword;
    }

    /**
   	**************************************************************************
   	 * Metodo SET per il moodificare la password
   	 * @param String password che si vuole inserire nuova
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
	 * Metodo SET per inserire una nuova email
	 * @param String nuova Email da inserire 
	 **************************************************************************
    */
    public void setEmail(String email) {
		this.email = email;
	}


	/**
	**************************************************************************
	 * Metodo GET per ottenere il ruolo dell'utente
	 * @return ritorna il ruolo dell'utente
	 **************************************************************************
    */
    public Role getRole() {
    	return role;
    }
    
    /**
	**************************************************************************
	 * Metodo SET per modificare il Ruolo
	 * @param Role ruolo da inserire tra quelli disponibili (Administrator, StoreManager, Officer)
	 **************************************************************************
    */
    public void setRole(Role r) {
    	this.role = r;
    }
   
    
    /**
	 **************************************************************************
	 * Metodo per ottenere un bean
	 * @return ritorna un bean ovvero un'oggetto contentente dei tipi di dato primitivi
	 **************************************************************************
	 */
    public UserBean	getUserBean() {
    	UserBean ub = new UserBean();
    	ub.setUsername(this.username);
    	ub.setPassword(this.hiddenPassword);
    	ub.setTemppass(this.temppass);
    	ub.setEmail(this.email);
    	ub.setUserRole( this.role.toString() );
    	
		return ub;
    }
    
    /**
	 **************************************************************************
	 * Metodo per il tester Junit per verificare l'esistenza dell'oggetto USER
	 * @return ritorna vero se esiste l'utente
	 **************************************************************************
	 */
    public boolean exists() {
		return true;
	}
}