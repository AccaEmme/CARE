package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Testers;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.DataManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.MongoDataManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.SystemManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.UserManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Interfaces.AdministratorInterface;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;


public class CARE2 {
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
			DataManager userDB = new MongoDataManager(); 
			// CareManager:

			// ottengo user e password in chiaro
			// controllo nel db se l'utente esiste e quale ruolo ha
			// se non esiste, lo riferisco
			// se esiste faccio lo switch dell'interfaccia mostrando solo i metodi che quel ruolo può adoperare
			// e creo la sessione ovvero lo UserManager che contiene User e Role.

			// Caso2: Maria di questo nodo, autorizza a Francesca di un altro nodo al prelievo di una sacca
			
			// Caso3:
		
			String username = "Gerardo";
			String password = "MarkUs30Lode";

			User currentUserSession = UserManager.checkLogin(username, password); // contiene solo metodi per verificare la login, se trovata restituisce lo UserManager
			// LoginInterface currentUserSession = UserManager.recoverPassword(username, email);
			
			AdministratorInterface sm = new UserManager(currentUserSession, userDB);
			
	}

}
