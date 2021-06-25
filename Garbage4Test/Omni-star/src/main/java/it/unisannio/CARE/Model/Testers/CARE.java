package it.unisannio.CARE.Model.Testers;

import it.unisannio.CARE.Control.Blood.BloodBagManager;
import it.unisannio.CARE.Control.Classes.DataManager;
import it.unisannio.CARE.Control.User.LoginInterface;
import it.unisannio.CARE.Control.u.Managers.MongoDataManager;
import it.unisannio.CARE.Control.u.Managers.SystemManager;
import it.unisannio.CARE.Control.u.Managers.UserManager;
import it.unisannio.CARE.Model.Classes.Interfaces.AdministratorInterface;
import it.unisannio.CARE.Model.Classes.Interfaces.OfficerInterface;
import it.unisannio.CARE.Model.Classes.Interfaces.StoreManagerInterface;
import it.unisannio.CARE.Model.Util.Role;


public class CARE {
/*
 * CARE conosce il db usato per le sacche(mysql) e il db usato per gli utenti(mongodb).
 * 
 * l'utente per accedere nel sistema, richiama il checkLogin(username, plainTextPassword)
 *   - checkLogin cerca nel database(?),
 *   	- se trova username=db.username && MD5(plainTextPassword)=db.md5password => ritorna tutti i dati User e relativo ruolo
 *      - se non lo trova, restituisce null o eccezione?
 *      
 *   - 
 * 
 */
	public static void main(String[] args) {
		/*
			DataManager userDB = new MongoDataManager(); 
			// CareManager:

			// ottengo user e password in chiaro
			// controllo nel db se l'utente esiste e quale ruolo ha
			// se non esiste, lo riferisco
			// se esiste faccio lo switch dell'interfaccia mostrando solo i metodi che quel ruolo può adoperare
			// e creo la sessione ovvero lo UserManager che contiene User e Role.

		
			String username = "Gerardo";
			String password = "MarkUs30Lode";

			LoginInterface currentUserSession = UserManager.checkLogin(username, password); // contiene solo metodi per verificare la login, se trovata restituisce lo UserManager
			// LoginInterface currentUserSession = UserManager.recoverPassword(username, email);
			
			switch( currentUserSession.getUser().getRole().toString() ){
			  // Adoperiamo le interfacce per consentire di utilizzare solo i metodi definiti nell'interfaccia
				case Role.Officer.toString(): 			OfficerInterface 		userInterface = new BloodBagManager(currentUserSession.getUser(), userDB);
					 break;
				case Role.StoreManager.toString(): 		StoreManagerInterface 	userInterface2 = new BloodBagManager(currentUserSession.getUser(), userDB);
					 break;
				case Role.Administrator.toString():		AdministratorInterface	userInterface3 = new SystemManager(currentUserSession.getUser(), userDB); // o UserManager
					 break;
			  default:									OfficerInterface		userInterface4 = null;
			}
			

			userInterface.findBagsByGroup( "Apos" ); // funziona solo se è un officer (es. segretaria)
			userInterface.addBloodBag( 
										BloodBagManager session //,...
									); // funziona solo se è uno store manager (es. magazziniere)
			userInterface.addUser( 
								//...
								 ); // funziona solo se è un amministratore

			// potrebbe anche esistere un metodo del tipo userInterface.showGUI() che fa apparire la relativa schermata grafica interfaccia utente


			// Utente amministratore: 


			//Utente amministratore genera report 
			userInterface3.
			*/
	}

}
