package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util;

import java.util.Date;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.DataManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;

public class Logger {
    /**
     * QUANDO:  data corrente
     * CHI:     l'utente chiamante
     * DOVE     la classe chiamante (this.class)
     * COSA:    l'azione eseguita
     * ESITO:   se l'azione è andata a buon fine (eccezione, errore, ecc.)
     * */
    public Logger(User currentUser, String c, String method, String result){
    	this.now = new Date();
        this.currentUser = currentUser;
        this.fromClass = c;
        this.method = method;
        this.result = result;
    }
    
    /**
     **************************************************************************
     * Descrizione del metodo
     * @param 
     * @return 
     **************************************************************************
     */
    public void addLog(DataManager manager){
        manager.writeLog(this.now, this.currentUser, this.fromClass, this.method, this.result);
    }


    private final Date now;
    User currentUser;
    String fromClass;
    String method;
    String result;
}
