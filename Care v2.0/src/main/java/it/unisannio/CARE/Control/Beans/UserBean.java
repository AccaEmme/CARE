/**
 * 
 */
package it.unisannio.CARE.Control.Beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
* User class JPA
* *
* @author AccaEmme on 2021-06-26
*/

@Entity
public class UserBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int 		idUser;
	
    @Column(unique = true, nullable = false)
	private String  	userame;

    //private Password	hiddenPasswordObj;
	private String		hiddenPassword;
	//private String		plainTextPassword;
	// temp password gestita sempre attraverso hiddenPassword
	private	int			loginAttempts;
	
    @Column(unique = true, nullable = false)
	private String		email;
		
	private Date		creationDate;
	private Date		lastAccess;
		
	public UserBean() {}
	

}
