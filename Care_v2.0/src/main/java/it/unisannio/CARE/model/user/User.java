package it.unisannio.CARE.model.user;
import java.util.Date;

import org.bson.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import it.unisannio.CARE.model.exceptions.IllegalPatternException;
import it.unisannio.CARE.model.exceptions.NullPasswordException;
import it.unisannio.CARE.model.exceptions.UserException;
import it.unisannio.CARE.model.exceptions.lllegalEmailException;
import it.unisannio.CARE.model.util.Constants;
import it.unisannio.CARE.model.util.Password;
import it.unisannio.ingsof20_21.group8.Care.Spring.UserDAO;

/**
 * User class that contains all user methods
 */

public class User {
	private String 		username, hiddenPassword, temppass, plainTextPassword, email;
	//private Location 	residence;
	private Role 		role;
	private Date		password_lastupdate;
	
	

	
	/**
	**************************************************************************
	 * Method for creating a user with a clear password and role
	 * @param username Username
	 * @param plainTextPassword unencrypted password choice
	 * @param role The user's role
	 * @throws IllegalPatternException
	 **************************************************************************
    */
	public User(String username, String plainTextPassword, Role role)throws IllegalPatternException {		
		this.setUsername(username);
		if(plainTextPassword.equals("")) {
			this.temppass 		= Password.generatePassword(10);
		
			this.hiddenPassword	=Password.getBCrypt(this.temppass);
		} else {
			this.temppass 		= "";

		Password.validatePlaintextPasswordPattern(plainTextPassword);
			this.hiddenPassword	=Password.getBCrypt(/*this.temppass*/plainTextPassword);
			
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
	* Method for managing the user with an encrypted password
	* @param username username
	* @param hiddenPassword encrypted password
	* @throws UserException, NullPasswordException
	 **************************************************************************
    */
	 
    public User(String username, Password hiddenPassword) throws UserException, NullPasswordException {
        //this.validateCredentials(username,hiddenPassword.getHiddenPassword());    //il metodo per prendere la password è getHiddenPassword
        setUsername(username);
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
	* GET method to get the username
	* @return returns the username as a string
	 **************************************************************************
    */
    public String getUsername() {
        return username;
    }

    /**
	**************************************************************************
	* SET method to change the username
	* @param username username user
	* @exception IllegalArgumentException
	 **************************************************************************
    */
    public void setUsername(String username) {
    	if(username.length()<3) throw new IllegalArgumentException("Username size too short, should be greater than 3 chars.");
        this.username = username;
    } 

    /**
	**************************************************************************
	* GET method to get the hidden password
	* @return Returns the password as a string
	 **************************************************************************
    */
    public String getPassword() {
        return hiddenPassword;
    }

    /**
   	**************************************************************************
   	* SET method for changing the password
   	* @param plainTextPassword password to enter
   	* @throws IllegalPatternException
   	 **************************************************************************
       */
    
    
    public void setPassword(String plainTextPassword) throws IllegalPatternException {
    	Password.validatePlaintextPasswordPattern(plainTextPassword);

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		this.hiddenPassword	=Password.getBCrypt(this.temppass);
     
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
	* GET method to get the last registered password
	* @return returns the last registered password date
	 **************************************************************************
    */
    public Date getPasswordLastUpdate() {
    	return password_lastupdate;
    }
   
    
    /**
   	**************************************************************************
   	* SET method to change the email entered
   	* @param email Email to be entered
    * @throws lllegalEmailException
   	 **************************************************************************
       */
    
    public void setEmail(String email) throws lllegalEmailException {
    	if(!email.matches(Constants.RegexEmail) ) throw new lllegalEmailException("Email not valid");
		this.email = email;
	}
    
    
    /**
	**************************************************************************
	* GET method to get the email
	* @return returns the user's email
	 **************************************************************************
    */
	public String getEmail(){
		return this.email;
		}

	/**
	**************************************************************************
	* GET method to get the user's role
	* @return returns the user's role
	 **************************************************************************
    */
    public Role getRole() {
    	return role;
    }
    
    /**
	**************************************************************************
	* SET method to change the Role
	* @param r The new role of the user
	 **************************************************************************
    */
    public void setRole(Role r) {
    	this.role = r;
    }
   
    /**
	**************************************************************************
	* Method for returning user as document xml
	* @return returns information for the xml document
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
	 * Method for parsing by User in UserDAO
	 * @return UserDAO
	 **************************************************************************
	 */
    public UserDAO	getUserDAO() {
    	UserDAO ud = new UserDAO();
    	ud.setUsername(this.username);
    	ud.setPassword(this.hiddenPassword);
    	ud.setTemppass(this.temppass);
    	ud.setEmail(this.email);
    	ud.setUserRole( this.role.toString() );
    	
		return ud;
    }
    
    /**
	 **************************************************************************
	 * Metodo per eseguire il parsing da UserDAO in User
	 * @return User
	 **************************************************************************
	 */
    /*
     * POSSIBILE IMPLEMENTAZIONE FUTURA.
    public static User	getUser(UserDAO dao) {
    	User user2Dao = new User(
                dao.getUsername(),                	// HTTP username
                dao.getPassword(),                	// HTTP plainTextPassword
                Role.valueOf(dao.getUserRole()) 	// HTTP Role
                );
    	user2Dao.setEmail(dao.getEmail());
    	
		return user2Dao;
    }
    */
    
    /**
	 **************************************************************************
	 * Method for the Junit tester to verify the existence of the USER object
	 * @return true
	 **************************************************************************
	 */
    public boolean exists() {
		return true;
	}
}