package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.DataManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Logger;

public class BloodBagManager extends BloodBag {
    /**
     * @param currentUser l'utente corrente
     * L'idea di base è quella di avere un manager che viene creato con l'utente della sessione corrente,
     * in questo caso si puo usare il manager per chiamare i metodi del MySQLmanager o MongoManager
     * cosi facendo non dobbiamo preoccuparci dei permessi nei DB managers, ma preoccuparcene solo qui.
     *
     * seguendo l'idea di poter utilizzare sia mysql che mongo, potremmo aggiungere un argomento (magari proprio il manager)
     * che verrà utilizzato nella classe
     * STREAM OF CONSCIOUSNESS: mi è piaciuta l'idea precedente, la implemento direttamente cosi.:
     * @throws ParseException */
    
    
    /*
     * Hermann:
     * L'utente gestisce una sacca per volta, quindi il Manager gestisce una sacca per volta (?). 
     * Quindi mi viene facile pensare che BloodBagManager non faccia altro che estendere la classe BloodBag.
     * Il manager crea una sacca
     * Il manager elimina una sacca -> imposta lo stato su elimina
     * Il manager trasferisce una sacca -> imposta lo stato su trasferita e setta il nuovo magazzino associato a quella sacca
     * 
     * Il manager può settare la data di creazione, evitando di farlo fare al BloodBag che non può conoscere l'utente! Ecco la soluzione.
     */

    public BloodBagManager(User currentUser, DataManager dataManager, BloodGroup bloodGroup, String donatorCF) throws ParseException{
    	super(bloodGroup, donatorCF);
    	this.currentUser = currentUser;
        this.dataManager = dataManager;
        /* Location */
    }
	
    public BloodBagManager(User currentUser, DataManager dataManager, BloodGroup bloodGroup, String donatorCF, String note) throws ParseException{
    	super(bloodGroup, donatorCF, note);
    	this.currentUser = currentUser;
        this.dataManager = dataManager;
    }
    
	public void setDonatorCF(String newDonatorCF) {
		dataManager.updateDonatorCF(newDonatorCF);
	}
	
    
	public void setCreationDate(Date creationDate){
		Calendar cal = Calendar.getInstance();
		cal.setTime(creationDate);
		cal.add(Calendar.MONTH, BloodBag.monthIncrementAmount);
		Date expirationDate =  cal.getTime();

		/*
		 * TODO: la data non può essere superiore a quella odierna, la data non può essere antecedente a -7gg
		 */
		
		dataManager.updateExpirationDate(this, expirationDate);
		dataManager.writeLog(
							cal.getTime(),
							currentUser,
							this.getClass().toString(),
							"setCreationDate", /* @TODO: *** da vedere */
							"esito eccezione"
							);
	
		/* @TODO: *** il logger potrebbe anche non servire più, da vedere */
		Logger logger = new Logger(
								currentUser,
								this.getClass(),
								null, /* da vedere */
								"esito eccezione"
						);
	}
    
	/*
    private void setBloodBagList() {
    	
    }
    
    public Iterator<BloodBag> getBloodBagsList() {
    	
    }
    */
	
    private User currentUser;
    private DataManager dataManager;
}	
