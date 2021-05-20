package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.DataManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;

public class BloodBagManager {
    private User currentUser;
    private DataManager dataManager;
    /**
     * @param currentUser l'utente corrente
     * L'idea di base è quella di avere un manager che viene creato con l'utente della sessione corrente,
     * in questo caso si puo usare il manager per chiamare i metodi del MySQLmanager o MongoManager
     * cosi facendo non dobbiamo preoccuparci dei permessi nei DB managers, ma preoccuparcene solo qui.
     *
     * seguendo l'idea di poter utilizzare sia mysql che mongo, potremmo aggiungere un argomento (magari proprio il manager)
     * che verrà utilizzato nella classe
     * STREAM OF CONSCIOUSNESS: mi è piaciuta l'idea precedente, la implemento direttamente cosi.:*/
    public BloodBagManager(User currentUser, DataManager dataManager){
        this.currentUser = currentUser;
        this.dataManager = dataManager;
    }
}
