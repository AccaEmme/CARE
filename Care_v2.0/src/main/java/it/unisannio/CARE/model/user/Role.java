package it.unisannio.CARE.model.user;

/*
 * https://github.com/AccaEmme/CARE/wiki/5.3-User-authentication-privileges-and-accesses-to-resources
 */

/**
**************************************************************************
 * Enum con tutti i ruoli che possono avere gli utenti
 **************************************************************************
*/
public enum Role {
	ROLE_ADMINISTRATOR,
	ROLE_STOREMANAGER, 
	ROLE_OFFICER, 
	ROLE_CENTRAL_STOREMANAGER, 
	ROLE_CENTRAL_OFFICER;
}