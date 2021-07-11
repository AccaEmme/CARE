package it.unisannio.CARE.model.user;

/*
 * https://github.com/AccaEmme/CARE/wiki/5.3-User-authentication-privileges-and-accesses-to-resources
 */

/**
**************************************************************************
	* Enum with all the roles that users can have
 **************************************************************************
*/
public enum Role {
	ROLE_ADMINISTRATOR,
	ROLE_STOREMANAGER, 
	ROLE_OFFICER,
	ROLE_CENTRAL_ADMINISTRATOR,
	ROLE_CENTRAL_STOREMANAGER, 
	ROLE_CENTRAL_OFFICER;
}