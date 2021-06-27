/**
 * 
 */
package it.unisannio.ingsof20_21.group8.Care.Spring;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



import it.unisannio.CARE.Model.Util.Constants;

/**
* DAO: User class JPA
* Si definisce la classe UserBean mediante il pattern architetturale DAO(Data Access Object) la quale rappresenta l'entità tabellare del RDBMS.
* La classe stratifica e isola l'accesso alla tabella mediante il data layer della business logic, creando un maggiore livello di astrazione e una più facile anuntenibilità.
* La classe verrà adoperata da "JPA Entity Manager".
* 
* La classe rispetterà le convenzioni previste:
*  - costruttore senza argomenti
*  - proprietà accessibili mediante metodi getter, setter, is
*  - classe serializzabile
*  - non conterrà alcun metodo per la gestione degli eventi
*  https://it.wikipedia.org/wiki/JavaBean
* *
* @author AccaEmme on 2021-06-26
*/

@Entity
@Table(name="users")
public class UserBean { // *** UserDAO ???
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int 		idUser;
	
	// ============USER Attributes
    @Column(unique = true, nullable = false)
	private String  	username;
    
    //@Column(nullable = false)				// If null, will be generated
    private String		password;			// when used in REST is plaintextpassword, when used in DB is hiddenpassword
	
	@Column(unique = true, nullable = true)
	private String		email;

	// ============ROLE Attributes
	@Column(nullable = false)
	private String		userRole;
	
	// ============Access Attributes
	@Column(nullable = false)
	private Date		creationDate;
	
	@Column(nullable = false)
	private Date		lastAccess;
	
	@Column(nullable = false)
    private	int			loginAttempts;
	
	@Column(nullable = false)
	private boolean		activeUser;
	
	
	public UserBean() {}

	/**
	 * @return the idUser
	 */
	public int getIdUser() {
		return idUser;
	}

	/**
	 * @param idUser the idUser to set
	 */
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password:				when used in REST is plaintextpassword, when used in DB is hiddenpassword
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set: when used in REST is plaintextpassword, when used in DB is hiddenpassword
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the loginAttempts
	 */
	public int getLoginAttempts() {
		return loginAttempts;
	}

	/**
	 * @param loginAttempts the loginAttempts to set
	 */
	public void setLoginAttempts(int loginAttempts) {
		this.loginAttempts = loginAttempts;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the userRole
	 */
	public String getUserRole() {
		return userRole;
	}



	/**
	 * @param userRole the userRole to set
	 */
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}



	/**
	 * @return the creationDate
	 */
	public String getCreationDate() {
		SimpleDateFormat creationDateString = new SimpleDateFormat(Constants.DATE_FORMAT_STRING);
		return creationDateString.format(creationDate).toString();
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the lastAccess
	 */
	public String getLastAccess() {
		SimpleDateFormat lastAccessString = new SimpleDateFormat(Constants.DATE_FORMAT_STRING);
		return lastAccessString.format(lastAccess).toString();
	}

	/**
	 * @param lastAccess the lastAccess to set
	 */
	public void setLastAccess(Date lastAccess) {
		this.lastAccess = lastAccess;
	}



	/**
	 * @return the activeUser
	 */
	public boolean isActiveUser() {	
		return activeUser;
	}

	/**
	 * @param activeUser the activeUser to set
	 */
	public void setActiveUser(boolean activeUser) {
		this.activeUser = activeUser;
	}

}
