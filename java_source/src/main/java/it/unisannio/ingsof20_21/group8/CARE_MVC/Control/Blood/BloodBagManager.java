package it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Blood;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.DataManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Exceptions.BloodBagCloneException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Exceptions.StateException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodGroup;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Node2;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Node.Node;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Logger;

public class BloodBagManager{
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

    public BloodBagManager() {
    	
    	this.bloodBags = new HashSet();
    	this.
    }
	
    public BloodBagManager(User currentUser, DataManager dataManager) throws ParseException{
    	
    	this.currentUser = currentUser;
        this.dataManager = dataManager;
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
	
	
	public void addBloodBag(BloodGroup bg, String donatorCF, UserManager session) throws BloodBagCloneException{
		
		
		Node node = session.getUser().getNode();
		BloodBag bB = new BloodBag(bg, donatorCF, node);
		node.addBloodBag(bg);
		bloodBags.add(bg);
		
	}
	
	
	public void useBag(BloodBag bloodBag) {
		
		for(BloodBag bg : bloodBags) {
		
			if(bg.equals(bloodBag)) {
				try {
					bg.useBag();
				}catch(StateException e) {
					
					System.err.println(e);
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public void transferBag(BloodBag bloodBag, Node node) {
		
		for(BloodBag bg : bloodBags) {
			
			if(bg.equals(bloodBag) && !(bg.getNode().equals(node)) && bg.checkState()) {
				try {
					
					bg.setNode(node);
					BloodBag bg2 = bg.clone();
					bg.transferBag();
					bloodBags.add(bg2);
				}catch(StateException e) {
					
					System.err.println(e);
					e.printStackTrace();
				}
			}
		}
	}
	
	public void dropBag(BloodBag bloodBag) {
		
		for(BloodBag bg : bloodBags) {
			
			if(bg.equals(bloodBag) && bg.checkState()) {
				try {
					
					bg.dropBag();
				}catch(StateException e) {
					
					System.err.println(e);
					e.printStackTrace();
				}
			}
		}
	}
	
	public Iterator<BloodBag> getBloodBags(){
		
		return bloodBags.iterator();
	}
    
    private UserManager userM;
	private HashSet<BloodBag> bloodBags;
	private HashMap<String, Node> nodes;
}	
