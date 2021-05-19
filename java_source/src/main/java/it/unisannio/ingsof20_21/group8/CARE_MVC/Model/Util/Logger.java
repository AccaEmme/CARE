package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.DataManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.MySqlDataManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;

import java.lang.reflect.Method;
import java.util.Date;

public class Logger {
    /**
     * QUANDO:  data corrente
     * CHI:     l'utente chiamante
     * DOVE     la classe chiamante (this.class)
     * COSA:    l'azione eseguita
     * ESITO:   se l'azione è andata a buon fine (eccezione, errore, ecc.)
     * */
    public Logger(User currentUser, Class c, Method method, String result){
        this.currentUser = currentUser;
        this.fromClass = c.toString();
        this.method = method.toString();
        this.result = result;
    }

    public void addLog(DataManager manager){
        manager.writeLog(this.now, this.currentUser, this.fromClass, this.method, this.result);
    }


    private final Date now = new Date();
    User currentUser;
    String fromClass;
    String method;
    String result;
}
