package it.unisannio.ingsof20_21.group8.Care.Spring;


import it.unisannio.CARE.controll.bloodBag.BloodBagManager;
import it.unisannio.CARE.controll.request.RequestManager;
import it.unisannio.CARE.model.bloodBag.BloodBag;
import it.unisannio.CARE.model.bloodBag.BloodBagState;
import it.unisannio.CARE.model.bloodBag.BloodGroup;
import it.unisannio.CARE.model.bloodBag.Serial;
import it.unisannio.CARE.model.exceptions.BloodBagCloneNotSupportedException;
import it.unisannio.CARE.model.exceptions.BloodBagNotFoundException;
import it.unisannio.CARE.model.exceptions.BloodBagStateException;
import it.unisannio.CARE.model.exceptions.IllegalFiscalCodeException;
import it.unisannio.CARE.model.exceptions.IllegalSerialException;
import it.unisannio.CARE.model.exceptions.NullPasswordException;
import it.unisannio.CARE.model.exceptions.RequestNotFoundException;
import it.unisannio.CARE.model.report.BloodBagReport;

import it.unisannio.CARE.model.util.Constants;
import it.unisannio.CARE.model.util.QRCode;

import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


@CrossOrigin("*")
@RestController

@Consumes("application/json")
@Produces("application/json")
public class BloodBagController implements ContainerResponseFilter {
    private final BloodBagRepository bagRepository;
    
    public BloodBagController(BloodBagRepository repository){
        this.bagRepository = repository;
    }


    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        responseContext.getHeaders().add("Access-Control-Allow-Headers",
                "CSRF-Token, X-Requested-By, Authorization, Content-Type");
        responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        responseContext.getHeaders().add("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }


    //############# GET #############

    /**
     * @return all the blood bags in the database
     */
    @GetMapping("/bloodbag/get/all")
    public Iterable<BloodBagDAO> getBloodBag(){
        return bagRepository.findAll();
    }


    /**
     * @param state the bloodbag state : {Available,Transfered,Used,Dropped}
     * @return all the blood bags with the given state
     */
    @GetMapping("/bloodbag/get/state/{state}")
    public Iterable<BloodBagDAO> filterBloodBagsByState(@PathVariable String state){
        return bagRepository.filterByState(state);
    }

    /**
     * @param group the bloodbag blood group : {Apos, Aneg, Bpos, Bneg, ZEROpos, ZEROneg, ABpos, ABneg}
     * @return all the blood bags with the given group
     */
    @GetMapping("/bloodbag/get/group/{group}")
    public Iterable<BloodBagDAO> getBloodBagByGroup(@PathVariable String group){
		return bagRepository.findByGroup(group); /*.orElseThrow();*/
    }


    /**
     * @param serial the bloodbag serial
     * @return all the bloodbags with the given state (probably just one)
     */
    @GetMapping("/bloodbag/get/serial/{serial}")
    public BloodBagDAO getBloodBagBySerial(@PathVariable String serial){
    	return bagRepository.findBySerial(serial); /*.orElseThrow();*/
    }

    // ############### CAN DONATE TO ###############
    @GetMapping("/bloodbag/candonateto/group/{group}")
    public Iterator<BloodGroup> canDonateToByGroup(@PathVariable String group){
        System.out.println(BloodGroup.valueOf(group));
        return BloodGroup.canDonateTo(BloodGroup.valueOf(group));
    }


    /**
     * this method returns a list of bloodgroups from the blood bag serial
     * 127.0.0.1:8087/bloodbag/get/candonateto/bloodbag/serial/IT-NA200009-Aneg-20210615-8056
     * @param serial
     * @return
     */
    @GetMapping("/bloodbag/candonateto/bloodbag/serial/{serial}")
    public Iterator<BloodGroup> canDonateToByBagSerial(@PathVariable String serial){
        BloodBagDAO bean = this.getBloodBagBySerial(serial);
        return BloodGroup.canDonateTo(BloodGroup.valueOf(bean.getGroup()));
    }


    // ############### CAN RECIVE FROM ###############
    @GetMapping("/bloodbag/canreceivefrom/group/{group}")
    public Iterator<BloodGroup> canReciveFromByGroup(@PathVariable String group){
        System.out.println(BloodGroup.valueOf(group));
        return BloodGroup.canReceiveFrom(BloodGroup.valueOf(group));
    }

    @GetMapping("/bloodbag/canreceivefrom/bloodbag/serial/{serial}")
    public Iterator<BloodGroup> canReciveFromByBagSerial(@PathVariable String serial){
        BloodBagDAO bean = this.getBloodBagBySerial(serial);
        return BloodGroup.canReceiveFrom(BloodGroup.valueOf(bean.getGroup()));
    }

    //############# get count ###############

    /**
     * @return the count of all bags
     */
    @GetMapping("bloodbag/count/all")
    public long getAllBagsCount(){
        return bagRepository.countAll();
    }

    /**
     * @return the count of all bags having the given blood group
     * @param group the blood group
     */
    @GetMapping("bloodbag/count/group/{group}")
    public long getCountByGroup(@PathVariable String group){
        return bagRepository.countByGroup(group);
    }

    /**
     * @return the count of all bags having the given state
     * @param state the given state
     */
    @GetMapping("bloodbag/count/state/{state}")
    public long getCountByState(@PathVariable String state){
        return bagRepository.countByState(state);
    }


    /**
     * @return the count of all bags expired after the given date
     * @param timestamp the given time
     */
    @GetMapping("bloodbag/count/expiring/after/{timestamp}")
    public long getCountExpiringAfterDate(@PathVariable long timestamp){
        return bagRepository.countExpirationAfterDate(timestamp);
    }

    /**
     * @return the count of all bags used after the given date
     * @param timestamp the given time
     */
    @GetMapping("/bloodvag/count/used/after/{timestamp}")
    public long getCountUsedAfterDate(@PathVariable long timestamp){
        return bagRepository.countUsedAfterDate(timestamp);
    }


    /**
     * @param firstDate the first date
     * @param secondDate the second date
     * @return the count of the bags expiring between the two dates
     */

    @GetMapping("/bloodbag/get/expiring/between/{firstdate}/{seconddate}")
    public long getCountExpiringBetweenDates(@PathVariable long firstDate, @PathVariable long secondDate){
        return bagRepository.countUsedBetweenDates(firstDate,secondDate);
    }


    //########### GET EXPIRING BEFORE/AFTER
    /**
     * @param timestamp the given date
     * @return all blood bags expired before a given date
     */
    @GetMapping("bloodbag/expiring/before/{timestamp}")
    public Iterable<BloodBagDAO> getBloodBagsExpiringBeforeDate(@PathVariable long timestamp){
        return bagRepository.findExpirationBeforeDate(timestamp);
    }

    /**
     * @param timestamp the given date
     * @return all blood bags expired after a given date
     */
    @GetMapping("bloodbag/expiring/after/{timestamp}")
    public Iterable<BloodBagDAO> getBloodBagsExpiringAfterDate(@PathVariable long timestamp){
        return bagRepository.findExpirationAfterDate(timestamp);
    }


    /**
     * @param firstdate the first date
     * @param seconddate the second date
     * @return all blood bags expired between the two dates
     */
    @GetMapping("bloodbag/expiring/between/{firstdate}/{seconddate}")
    public Iterable<BloodBagDAO> getBloodBagsExpiringBetweenDate(@PathVariable long firstdate,@PathVariable long seconddate){
        if (firstdate>seconddate){
            return bagRepository.findExpirationBetweenDate(seconddate,firstdate);
        }
        return bagRepository.findExpirationBetweenDate(firstdate,seconddate);
    }

    /**
     * @param firstdate the first date
     * @param seconddate the second date
     * @param bloodgroup the blood group
     * @return all blood bags expired between two dates of a specific blood group
     */
    @GetMapping("bloodbag/expiring/between/{firstdate}/{seconddate}/{bloodgroup}")
    public Iterable<BloodBagDAO> getBloodBagsExpiringBetweenDateWithGroup(@PathVariable long firstdate, @PathVariable long seconddate, @PathVariable String bloodgroup){
        return bagRepository.findExpirationBetweenDate_bloodGroup(firstdate,seconddate,bloodgroup);
    }


    /**
     * @return report the blood bag report having all stats
     */
    @GetMapping("bloodbag/report")
    public BloodBagReport getReport(){
        return new BloodBagReport(this.getAllBagsCount(),
                this.getCountByState(BloodBagState.Available.toString()),
                this.getCountByState(BloodBagState.Used.toString()),
                this.getCountByState(BloodBagState.Transfered.toString()),
                this.getCountByState(BloodBagState.Dropped.toString()),

                this.getCountByGroup(BloodGroup.ABpos.toString()),
                this.getCountByGroup(BloodGroup.Aneg.toString()),
                this.getCountByGroup(BloodGroup.Bpos.toString()),
                this.getCountByGroup(BloodGroup.Bneg.toString()),
                this.getCountByGroup(BloodGroup.ZEROpos.toString()),
                this.getCountByGroup(BloodGroup.ZEROneg.toString()),
                this.getCountByGroup(BloodGroup.ABpos.toString()),
                this.getCountByGroup(BloodGroup.ABneg.toString()),
                this.getCountExpiringBetweenDates(new Date().getTime()-Constants.SEVEN_DAYS_MILLIS, new Date().getTime()),
                this.getCountUsedAfterDate(new Date().getTime()-Constants.SEVEN_DAYS_MILLIS));
    }
    //not working
    private void writeReport(JSONObject report){
        try {
            FileWriter reportFile = new FileWriter("localsettings/BloodBagReports.txt");
            reportFile.write(report.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //############# POST ############

	/*    
    {
    	"group":"Bneg",
    	"donator":"LDDBXB52C07L287S",
    	"notes":"test note"
    }
 */
    @PostMapping("/bloodbag/add")
    public BloodBagDAO createBloodBag(@RequestBody BloodBagDAO bagDAO) {
	    try {
	        BloodBag tempBloodBagObj = new BloodBag(
	                BloodGroup.valueOf(bagDAO.getGroup()),
	                bagDAO.getDonator()
	                
	        );
	        tempBloodBagObj.setNote(bagDAO.getNotes());
	        bagDAO = tempBloodBagObj.getBean();
	        if (bagRepository.existsById(bagDAO.getSerial()))
	    		throw new BloodBagCloneNotSupportedException("La sacca che si vuole aggiungere è già esistente.", "/bloodbag/add");
	    	
	    	else if (!bagDAO.getState().equals(BloodBagState.Available.toString()))
	    		throw new BloodBagStateException("Lo stato dela sacca che si vuole aggiungere non è valido.", "/bloodbag/add");

	        //se la bag viene aggiunta come usata, aggiorno il momento di utilizzo all'ora corrente
	        if (bagDAO.getUsedTimeStamp() == 0)
	        	bagDAO.setUsedTimeStamp(new Date().getTime());


            JSONObject object = new JSONObject();
                object.put("serial",bagDAO.getSerial());

                QRCode code = new QRCode(object);

                code.createQRCode();

	        return bagRepository.save(bagDAO);
	        		
	    }catch(IllegalArgumentException e) {
	    	
	    	e.printStackTrace();
	    	throw new IllegalFiscalCodeException("codice fiscale non valido","/bloodbag/add");
	    	
	    }catch(ParseException e) {
	    	
	    	e.printStackTrace();
	    	return null;
	    }
    }
    
    
    
    @PostMapping("/bloodbag/central/add")
    public BloodBagDAO createAndShareBloodBag(@RequestBody BloodBagDAO bagDAO) throws ParseException {

	    	
	    try {
	        BloodBag tempBloodBagObj = new BloodBag(
	                BloodGroup.valueOf(bagDAO.getGroup()),
	                bagDAO.getDonator()
	                
	        );
	        bagDAO = tempBloodBagObj.getBean();
	        
	        if (bagRepository.existsById(bagDAO.getSerial()))
	    		throw new BloodBagCloneNotSupportedException("La sacca che si vuole aggiungere è già esistente.", "/bloodbag/add");
	    	
	    	else if (!bagDAO.getState().equals(BloodBagState.Available.toString()))
	    		throw new BloodBagStateException("Lo stato dela sacca che si vuole aggiungere non è valido.", "/bloodbag/add");

	        BloodBagManager managerB = new BloodBagManager();
	        managerB.addBloodBag(tempBloodBagObj);
	        
	        managerB.close();
	        //se la bag viene aggiunta come usata, aggiorno il momento di utilizzo all'ora corrente
	        if (bagDAO.getUsedTimeStamp() == 0)
	        	bagDAO.setUsedTimeStamp(new Date().getTime());
	        
	        return bagRepository.save(bagDAO);
	        		
	    }catch(IllegalArgumentException e) {
	    	
	    	e.printStackTrace();
	    	throw new IllegalFiscalCodeException("Formato del codice fiscale non valido","/bloodbag/add");
	    	
	    }
        

   
    }
    
    
    
    @PostMapping("/bloodbag/import")
    public BloodBagDAO importBloodBag(@RequestBody BloodBagDAO bagDAO) throws ParseException {

	        
    	BloodBagManager managerB = new BloodBagManager();
    	RequestManager managerR = new RequestManager();
    	try {
        
    		managerR.closeRequest(bagDAO.getSerial());
    	
	        Document bagD = managerB.importBloodBag(bagDAO.getSerial());
	    		
	        bagDAO.setCreationDate(Long.parseLong(bagD.getString("creation_date")));
	        bagDAO.setDonator(bagD.getString("donator"));
	        bagDAO.setExpirationDate(Long.parseLong(bagD.getString("expiration_date")));
	    	bagDAO.setGroup(bagD.getString("group"));
	    	bagDAO.setNotes(bagD.getString("notes"));
	    	bagDAO.setState(BloodBagState.Available.toString());
	    	
	        if (bagDAO.getUsedTimeStamp() == 0)
	        	bagDAO.setUsedTimeStamp(new Date().getTime());
	        
	        return bagRepository.save(bagDAO);
    	}catch(RequestNotFoundException e) {
    		
    		e.printStackTrace();
    		throw new RequestNotFoundException("La richiesta che si vuole concludere non è esistente.", "/bloodbag/import");
    	}catch(BloodBagNotFoundException e) {
    		
    		e.printStackTrace();
    		throw new BloodBagNotFoundException(e.getMessage(), "/bloodbag/add");
    	}finally {
    		
    		managerB.close();
    		managerR.close();
    	}

   
    }
    
    

    /**
     * this method is called to USE a blood bag, it changes the blood bag state to "Used"
     * 1) find blood bag
     * 2) delete the blood bag
     * 3) modify the blood bag state
     * 4) save the new bloood bag
     * @param serial the blood bag serial
     * @return blood bag
     */

    @DeleteMapping("/bloodbag/use/{serial}")
    public BloodBagDAO useBloodBag(@PathVariable("serial") String serial){
      	/*if  (!(bagRepository.existsById(serial)))
        throw new BloodBagStateException("la sacca non esiste");
        */
        if(bagRepository.getById(serial).getState().equals("Used"))
                throw new BloodBagStateException("la sacca è stata già usata");

        bagRepository.updateBloodBagStateBySerial(BloodBagState.Used.toString(),serial);
        bagRepository.updateBloodBagUsedTimestampBySerial(new Date().getTime(),serial);

        System.err.println(serial);
        return this.getBloodBagBySerial(serial);
    }


    /**
     * this method exports a json containing all the blood bags
     */
    @GetMapping("/export/bloodbag/json")
    public void exportToJson(){
        JSONArray sqlDatabase = new JSONArray();
        Iterable<BloodBagDAO> beans = this.getBloodBag();

        for (BloodBagDAO bean : beans){
            sqlDatabase.add(this.getJsonObjectFromBean(bean));
        }


        try {
            FileWriter fileWriter = new FileWriter("exports/jsonExport"+Constants.dateFormatString.format(new Date())+".json");
            fileWriter.write(sqlDatabase.toJSONString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @param bean the bean used to generate the json object
     * @return jsonObject the object generated from the bean
     */
    private JSONObject getJsonObjectFromBean(BloodBagDAO bean){
        JSONObject object = new JSONObject();
            object.put("serial",bean.getSerial());
            object.put("group",bean.getGroup());
            object.put("donator",bean.getDonator());
            object.put("creationDate",bean.getCreationDate());
            object.put("expirationDate",bean.getExpirationDate());
            object.put("state",bean.getState());
            object.put("notes",bean.getNotes());
            object.put("usedTimeStamp",bean.getUsedTimeStamp());
        return object;
    }

}

