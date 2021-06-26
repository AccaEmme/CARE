/**
 * 
 */
package it.unisannio.CARE.Model.Beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
public class UserBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int 		idUser;
	
    @Column(unique = true, nullable = false)
	private String  	userame;
    private String		hiddenPassword;
    private	int			loginAttempts;
    
    //private Password	hiddenPasswordObj;
	//private String		plainTextPassword;
	// temp password gestita sempre attraverso hiddenPassword
	
	@Column(unique = true, nullable = false)
	private String		email;
		
	private Date		creationDate;
	private Date		lastAccess;
		
	public UserBean() {}
	

}
