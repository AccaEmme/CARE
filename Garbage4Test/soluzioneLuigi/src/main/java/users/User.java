package users;

import java.util.Date;



public class User {
	/*
	 * Il costruttore genera una sessione utente.
	 * Il costruttore genera l'MD5 della password data in input, per confrontarla.
	 * Quando l'amministratore crea un utente attribuendogli un ruolo, la password viene generata in automatico ed è visibile.
	 * Al primo cambio password dell'utente, viene eliminata la password temporanea, impostata la password cifrata e viene segnato l'ultimo cambio password.
	 */
	
	public User(String username, String plainTextPassword){
    	this.username 	= username;
    	this.setPassword(plainTextPassword);
    	this.role=role.StoreManager;  /*mettere un ruolo generico*/
    	//this.session 	= MD5.getMd5(username).toUpperCase();
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
    	
    	this.password_lastupdate = new Date();
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

    public void setPassword(String plainTextPassword) {
    	Password.validatePassword(plainTextPassword);
        this.password 				= Password.getMd5( plainTextPassword+Constants.USER_MD5_SALT ).toUpperCase();
        this.password_lastupdate 	= new Date();
        this.temppass				= "";
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
   
   
    private String 		username, password, temppass;
    private Location 	residence;
    private Role 		role;
    private Date		password_lastupdate;
}