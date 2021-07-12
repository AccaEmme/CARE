package it.unisannio.ingsof20_21.group8.Care.Spring;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import it.unisannio.CARE.model.util.XMLHelper;
import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
import it.unisannio.CARE.model.exceptions.NodeNotFoundException;
import it.unisannio.CARE.model.exceptions.RequestNotFoundException;
import it.unisannio.CARE.model.exceptions.UserException;
import it.unisannio.CARE.model.report.BloodBagReport;
import it.unisannio.CARE.model.util.Constants;
import it.unisannio.CARE.model.util.QRCode;
import it.unisannio.CARE.model.util.Logger.Actions;
import it.unisannio.CARE.model.util.Logger.LogManager;
import it.unisannio.CARE.model.util.Logger.Results;
import it.unisannio.CARE.modulep2p.Node.NodeIDs;
import it.unisannio.CARE.modulep2p.P2PManager;

/**
 * used to send the bloodbag http requests
 */
@CrossOrigin("*")
@RestController

@Consumes("application/json")
@Produces("application/json")
public class BloodBagController /* implements ContainerResponseFilter */ {
	private final BloodBagRepository bagRepository;
	private final LoggerRepository logRepository;

	public BloodBagController(BloodBagRepository repository1, LoggerRepository repository2) {
		this.bagRepository = repository1;
		this.logRepository = repository2;

	}

	/*
	 * @Override public void filter(ContainerRequestContext requestContext,
	 * ContainerResponseContext responseContext) throws IOException {
	 * responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
	 * responseContext.getHeaders().add("Access-Control-Allow-Headers",
	 * "CSRF-Token, X-Requested-By, Authorization, Content-Type");
	 * responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
	 * responseContext.getHeaders().add("Access-Control-Allow-Methods",
	 * "GET, POST, PUT, DELETE, OPTIONS, HEAD");
	 * 
	 * }
	 */

	// ############# GET #############

	/**
	 * gets all the blood bags
	 * 
	 * @return all the blood bags in the database
	 */
	@GetMapping("/bloodbag/get/all")
	public Iterable<BloodBagDAO> getBloodBag() {
		return bagRepository.findAll();
	}

	/**
	 * gets all the blood bags having a specific state
	 * 
	 * @param state the bloodbag state : {Available,Transfered,Used,Dropped}
	 * @return all the blood bags with the given state
	 */
	@GetMapping("/bloodbag/get/state/{state}")
	public Iterable<BloodBagDAO> filterBloodBagsByState(@PathVariable String state) {
		return bagRepository.filterByState(state);
	}

	/**
	 * get all the blood bags having a specific blood group
	 * 
	 * @param group the bloodbag blood group : {Apos, Aneg, Bpos, Bneg, ZEROpos,
	 *              ZEROneg, ABpos, ABneg}
	 * @return all the blood bags with the given group
	 */
	@GetMapping("/bloodbag/get/group/{group}")
	public Iterable<BloodBagDAO> getBloodBagByGroup(@PathVariable String group) {
		return bagRepository.findByGroup(group); /* .orElseThrow(); */
	}

	/**
	 * get a blood bag from it's serial
	 * 
	 * @param serial the bloodbag serial
	 * @return all the bloodbags with the given state (probably just one)
	 */
	@GetMapping("/bloodbag/get/serial/{serial}")
	public BloodBagDAO getBloodBagBySerial(@PathVariable String serial) {
		return bagRepository.findBySerial(serial); /* .orElseThrow(); */
	}

	/**
	 * gets all the blood bags from the main node database
	 * 
	 * @return all the bloodbags in the main node database
	 */
	@GetMapping("/bloodbag/get/central")
	public Iterable<BloodBagDAO> getCentralBloodBags() {

		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);

		ArrayList<BloodBagDAO> array = new ArrayList<>();

		BloodBagManager managerB = new BloodBagManager();
		Iterable<Document> iterable = (managerB.getBloodBags());

		for (Document bloodBagD : iterable) {

			BloodBagDAO bagDAO = new BloodBagDAO(bloodBagD.getString("serial"),
					Long.parseLong(bloodBagD.getString("creation_date")), bloodBagD.getString("donator"),
					Long.parseLong(bloodBagD.getString("expiration_date")), bloodBagD.getString("group"),
					bloodBagD.getString("notes"), bloodBagD.getString("state"));

			array.add(bagDAO);
		}

		managerB.close();

		return array;
	}

	/**
	 * gets all the blood bags of a group type from the main node database
	 * 
	 * @return all the bloodbags of a group type in the main node database
	 */
	@GetMapping("/bloodbag/get/central/group/{group}")
	public Iterable<BloodBagDAO> getCentralBloodBagsByGroup(@PathVariable String group) {

		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);

		ArrayList<BloodBagDAO> array = new ArrayList<>();

		BloodBagManager managerB = new BloodBagManager();
		Iterable<Document> iterable = (managerB.getBloodBagsByGroup(BloodGroup.valueOf(group)));

		for (Document bloodBagD : iterable) {

			BloodBagDAO bagDAO = new BloodBagDAO(bloodBagD.getString("serial"),
					Long.parseLong(bloodBagD.getString("creation_date")), bloodBagD.getString("donator"),
					Long.parseLong(bloodBagD.getString("expiration_date")), bloodBagD.getString("group"),
					bloodBagD.getString("notes"), bloodBagD.getString("state"));

			array.add(bagDAO);
		}

		managerB.close();

		return array;
	}

	/**
	 * gets all the groups a specific group can donate to
	 * 
	 * @param group the given group
	 * @return all the groups a specific group can donate to
	 */
	// ############### CAN DONATE TO ###############
	@GetMapping("/bloodbag/candonateto/group/{group}")
	public Iterator<BloodGroup> canDonateToByGroup(@PathVariable String group) {
		System.out.println(BloodGroup.valueOf(group));
		return BloodGroup.canDonateTo(BloodGroup.valueOf(group));
	}

	/**
	 * this method returns a list of bloodgroups from the blood bag serial
	 * 127.0.0.1:8087/bloodbag/get/candonateto/bloodbag/serial/IT-NA200009-Aneg-20210615-8056
	 * 
	 * @param serial
	 * @return
	 */
	@GetMapping("/bloodbag/candonateto/bloodbag/serial/{serial}")
	public Iterator<BloodGroup> canDonateToByBagSerial(@PathVariable String serial) {
		BloodBagDAO bean = this.getBloodBagBySerial(serial);
		return BloodGroup.canDonateTo(BloodGroup.valueOf(bean.getGroup()));
	}

	/**
	 * gets all the groups a specific group can receive from
	 * 
	 * @param group the given group
	 * @return all the groups a specific group can receive from
	 */
	// ############### CAN RECIVE FROM ###############
	@GetMapping("/bloodbag/canreceivefrom/group/{group}")
	public Iterator<BloodGroup> canReciveFromByGroup(@PathVariable String group) {
		System.out.println(BloodGroup.valueOf(group));
		return BloodGroup.canReceiveFrom(BloodGroup.valueOf(group));
	}

	/**
	 * gets all the groups a specific blood bag can receive from
	 * 
	 * @param serial the bag's serial
	 * @return all the groups the blood bag can receive from
	 */
	@GetMapping("/bloodbag/canreceivefrom/bloodbag/serial/{serial}")
	public Iterator<BloodGroup> canReciveFromByBagSerial(@PathVariable String serial) {
		BloodBagDAO bean = this.getBloodBagBySerial(serial);
		return BloodGroup.canReceiveFrom(BloodGroup.valueOf(bean.getGroup()));
	}

	// ############# get count ###############

	/**
	 * counts all the blood bags
	 * 
	 * @return the count of all bags
	 */
	@GetMapping("bloodbag/count/all")
	public long getAllBagsCount() {
		return bagRepository.countAll();
	}

	/**
	 * counts the blood bags having a specific group
	 * 
	 * @return the count of all bags having the given blood group
	 * @param group the blood group
	 */
	@GetMapping("bloodbag/count/group/{group}")
	public long getCountByGroup(@PathVariable String group) {
		return bagRepository.countByGroup(group);
	}

	/**
	 * counts all the blood bags having a specific state
	 * 
	 * @return the count of all bags having the given state
	 * @param state the given state
	 */
	@GetMapping("bloodbag/count/state/{state}")
	public long getCountByState(@PathVariable String state) {
		return bagRepository.countByState(state);
	}

	/**
	 * counts all the blood bags expiring after a specific date (timestamp)
	 * 
	 * @return the count of all bags expired after the given date
	 * @param timestamp the given time
	 */
	@GetMapping("bloodbag/count/expiring/after/{timestamp}")
	public long getCountExpiringAfterDate(@PathVariable long timestamp) {
		return bagRepository.countExpirationAfterDate(timestamp);
	}

	/**
	 * counts all the blood bags used after a specific date (timestamp)
	 * 
	 * @return the count of all bags used after the given date
	 * @param timestamp the given time
	 */
	@GetMapping("/bloodvag/count/used/after/{timestamp}")
	public long getCountUsedAfterDate(@PathVariable long timestamp) {
		return bagRepository.countUsedAfterDate(timestamp);
	}

	/**
	 * counts all the blood bags expiring between a specific time interval
	 * (timestamp)
	 * 
	 * @param firstDate  the first date
	 * @param secondDate the second date
	 * @return the count of the bags expiring between the two dates
	 */

	@GetMapping("/bloodbag/get/expiring/between/{firstdate}/{seconddate}")
	public long getCountExpiringBetweenDates(@PathVariable long firstDate, @PathVariable long secondDate) {
		return bagRepository.countUsedBetweenDates(firstDate, secondDate);
	}

	// ########### GET EXPIRING BEFORE/AFTER
	/**
	 * counts all the blood bags expiring before a specific time (timestamp)
	 * 
	 * @param timestamp the given date
	 * @return all blood bags expired before a given date
	 */
	@GetMapping("bloodbag/expiring/before/{timestamp}")
	public Iterable<BloodBagDAO> getBloodBagsExpiringBeforeDate(@PathVariable long timestamp) {
		return bagRepository.findExpirationBeforeDate(timestamp);
	}

	/**
	 * counts all the blood bags expiring after a specific time (timestamp)
	 * 
	 * @param timestamp the given date
	 * @return all blood bags expired after a given date
	 */
	@GetMapping("bloodbag/expiring/after/{timestamp}")
	public Iterable<BloodBagDAO> getBloodBagsExpiringAfterDate(@PathVariable long timestamp) {
		return bagRepository.findExpirationAfterDate(timestamp);
	}

	/**
	 * counts all the blood bags expiring between a specific time interval
	 * (timestamp)
	 * 
	 * @param firstdate  the first date
	 * @param seconddate the second date
	 * @return all blood bags expired between the two dates
	 */
	@GetMapping("bloodbag/expiring/between/{firstdate}/{seconddate}")
	public Iterable<BloodBagDAO> getBloodBagsExpiringBetweenDate(@PathVariable long firstdate,
			@PathVariable long seconddate) {
		if (firstdate > seconddate) {
			return bagRepository.findExpirationBetweenDate(seconddate, firstdate);
		}
		return bagRepository.findExpirationBetweenDate(firstdate, seconddate);
	}

	/**
	 * counts all the blood bags expiring between a specific time interval, having a
	 * specific blood group (timestamp)
	 * 
	 * @param firstdate  the first date
	 * @param seconddate the second date
	 * @param bloodgroup the blood group
	 * @return all blood bags expired between two dates of a specific blood group
	 */
	@GetMapping("bloodbag/report")
	public BloodBagReport getReport() throws IOException {
		BloodBagReport report =  new BloodBagReport(this.getAllBagsCount(), this.getCountByState(BloodBagState.Available.toString()),
				this.getCountByState(BloodBagState.Used.toString()),
				this.getCountByState(BloodBagState.Transfered.toString()),
				this.getCountByState(BloodBagState.Dropped.toString()),

				this.getCountByGroup(BloodGroup.ABpos.toString()), this.getCountByGroup(BloodGroup.Aneg.toString()),
				this.getCountByGroup(BloodGroup.Bpos.toString()), this.getCountByGroup(BloodGroup.Bneg.toString()),
				this.getCountByGroup(BloodGroup.ZEROpos.toString()),
				this.getCountByGroup(BloodGroup.ZEROneg.toString()), this.getCountByGroup(BloodGroup.ABpos.toString()),
				this.getCountByGroup(BloodGroup.ABneg.toString()),
				this.getCountExpiringBetweenDates(new Date().getTime() - Constants.SEVEN_DAYS_MILLIS,
						new Date().getTime()),
				this.getCountUsedAfterDate(new Date().getTime() - Constants.SEVEN_DAYS_MILLIS));
		
		File file = new File("Report/report("+Constants.dateFormatFile.format(new Date())+").txt");
		file.createNewFile();
			
		PrintStream ps = new PrintStream(file);
		report.print(ps);
		
		return report;
	}

	// not working
	private void writeReport(JSONObject report) {
		try {
			FileWriter reportFile = new FileWriter("localsettings/BloodBagReports.txt");
			reportFile.write(report.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ############# POST ############
	@PostMapping("/bloodbag/add/forced")
	public BloodBagDAO addBloodBag(@RequestBody BloodBagDAO bagDAO){
		return bagRepository.save(bagDAO);
	}

	/**
	 * add a blood bag
	 * 
	 * @param bagDAO the bag
	 * @return the added blood bag example: { "group":"Bneg",
	 *         "donator":"LDDBXB52C07L287S", "notes":"test note" }
	 */
	@SuppressWarnings("deprecation")
	@PostMapping("/bloodbag/add")
	public BloodBagDAO createBloodBag(@RequestBody BloodBagDAO bagDAO) throws IOException {
		long id = new Date().getTime(); // soluzione "lazy"
		LoggerDAO loggerDAO = new LoggerDAO();
		loggerDAO.setIdLog(id);

		loggerDAO.setCurrentUserUsername(SecurityContextHolder.getContext().getAuthentication().getName().toString());
		loggerDAO.setFromClass(this.getClass().toString());
		loggerDAO.setAction(Actions.BLOODBAG_ADD.toString());
		loggerDAO.setExplanation("");
		try {
			BloodBag tempBloodBagObj = new BloodBag(BloodGroup.valueOf(bagDAO.getGroup()), bagDAO.getDonator()

			);
			loggerDAO.setInformation(tempBloodBagObj.getSerial().toString());
			tempBloodBagObj.setNote(bagDAO.getNotes());

			bagDAO = tempBloodBagObj.getBean();
			bagDAO.setUsedTimeStamp(Constants.TIMESTAMP1900);
			if (bagRepository.existsById(bagDAO.getSerial())) {
				loggerDAO.setResult(Results.OPERATION_REFUSED.toString());
				loggerDAO.setExplanation("Bloodbag already exists");
				it.unisannio.CARE.model.util.Logger.LogManager logManager = new LogManager(loggerDAO);
				logManager.writeLog();
				logRepository.save(loggerDAO);
				throw new BloodBagCloneNotSupportedException("La sacca che si vuole aggiungere è già esistente.",
						"/bloodbag/add");
			} else if (!bagDAO.getState().equals(BloodBagState.Available.toString())) {
				loggerDAO.setResult(Results.OPERATION_REFUSED.toString());
				loggerDAO.setExplanation("Bloodbag state not valid");
				it.unisannio.CARE.model.util.Logger.LogManager logManager = new LogManager(loggerDAO);
				logManager.writeLog();
				logRepository.save(loggerDAO);
				throw new BloodBagStateException("Lo stato dela sacca che si vuole aggiungere non è valido.",
						"/bloodbag/add");
			}

			JSONObject object = new JSONObject();
			object.put("serial", bagDAO.getSerial());

			QRCode code = new QRCode(object);

			code.createQRCode();

			loggerDAO.setResult(Results.OPERATION_SUCCESSFUL.toString());
			it.unisannio.CARE.model.util.Logger.LogManager logManager = new LogManager(loggerDAO);
			logManager.writeLog();
			logRepository.save(loggerDAO);
			return bagRepository.save(bagDAO);

		} catch (IllegalArgumentException e) {

			e.printStackTrace();
			throw new IllegalFiscalCodeException("codice fiscale non valido", "/bloodbag/add");

		} catch (ParseException e) {

			e.printStackTrace();
			return null;
		}
	}

	/**
	 * add a blood bag in the central node to mongo database
	 * 
	 * @param bagDAO the blood bag
	 * @return the added blood bag
	 * @throws ParseException if the blood bag is not valid
	 */
	@PostMapping("/bloodbag/central/add")
	public BloodBagDAO createAndShareBloodBag(@RequestBody BloodBagDAO bagDAO) throws ParseException {

		BloodBagManager managerB = new BloodBagManager();
		long id = new Date().getTime();
		LoggerDAO loggerDAO = new LoggerDAO();
		loggerDAO.setIdLog(id);
		loggerDAO.setInformation(bagDAO.getSerial());
		loggerDAO.setCurrentUserUsername(SecurityContextHolder.getContext().getAuthentication().getName().toString());
		loggerDAO.setFromClass(this.getClass().toString());
		loggerDAO.setAction(Actions.BLOODBAG_TRANSFERED.toString());
		loggerDAO.setExplanation("");

		try {
			BloodBagDAO TempBagDAO = bagRepository.findBySerial(bagDAO.getSerial());
			BloodBag tempBloodBagObj = new BloodBag(new Serial(TempBagDAO.getSerial()),
					BloodGroup.valueOf(TempBagDAO.getGroup()), new Date(TempBagDAO.getCreationDate()),
					new Date(TempBagDAO.getExpirationDate()), TempBagDAO.getDonator(), BloodBagState.Available,
					TempBagDAO.getNotes());

			if (!bagRepository.existsById(TempBagDAO.getSerial()))
				throw new BloodBagNotFoundException("La sacca che si vuole aggiungere non è presente in magazzino.",
						"/bloodbag/central/add");

			else if (!TempBagDAO.getState().equals(BloodBagState.Available.toString()))
				throw new BloodBagStateException("Lo stato dela sacca che si vuole aggiungere non è valido.",
						"/bloodbag/central/add");

			else if (bagRepository.getById(TempBagDAO.getSerial()).getState().equals("Trasfered")) {
				loggerDAO.setResult(Results.OPERATION_GONEBAD.toString());
				loggerDAO.setExplanation("the blood bag was already trasfered.");
				logRepository.save(loggerDAO);
				throw new BloodBagStateException("the blood bag was already used.");
			}
			bagRepository.updateBloodBagStateBySerial(BloodBagState.Transfered.toString(), TempBagDAO.getSerial());
			bagRepository.updateBloodBagUsedTimestampBySerial(new Date().getTime(), TempBagDAO.getSerial());

			loggerDAO.setResult(Results.OPERATION_SUCCESSFUL.toString());
			logRepository.save(loggerDAO);

			managerB.addBloodBag(tempBloodBagObj);

			return TempBagDAO;

		} catch (IllegalArgumentException e) {

			e.printStackTrace();
			throw new IllegalFiscalCodeException("Formato del codice fiscale non valido", "/bloodbag/central/add");

		} catch (IllegalSerialException e) {

			e.printStackTrace();
			throw new IllegalSerialException("Formato del seriale non valido", "/bloodbag/central/add");

		} finally {

			managerB.close();
		}

	}

	@PostMapping("/bloodbag/central/send")
	public void BloodBagCentralTransfer(@RequestBody BloodBagDAO bagDAO) throws ParseException {

		BloodBagDAO bbd = bagRepository.findBySerial(bagDAO.getSerial());

		try {
			BloodBag tempBloodBagObj = new BloodBag(new Serial(bbd.getSerial()), BloodGroup.valueOf(bbd.getGroup()),
					new Date(bbd.getCreationDate()), new Date(bbd.getExpirationDate()), bbd.getDonator(),
					BloodBagState.receiving, bbd.getNotes()

			);

			BloodBagManager managerB = new BloodBagManager();
			managerB.addBloodBag(tempBloodBagObj);

			managerB.close();

			bagRepository.updateBloodBagStateBySerial("Transfered", bbd.getSerial());

		} catch (IllegalArgumentException e) {

			e.printStackTrace();
			throw new IllegalFiscalCodeException("Formato del codice fiscale non valido", "/bloodbag/add");

		}
	}

	@PostMapping("/bloodbag/central/confirm")
	public void BloodBagCentralTransferConfirm(@RequestBody BloodBagDAO bagDAO) throws ParseException {
		try {
			BloodBagManager managerB = new BloodBagManager();
			managerB.confirm(bagDAO.getSerial());
			managerB.close();
		} catch (BloodBagNotFoundException e) {
			throw new BloodBagNotFoundException(e.getMessage(), "/bloodbag/central/confirm");
		}

	}

	@PatchMapping("/bloodbag/p2p/import/{serial}")
	public BloodBagDAO importP2PBloodBag(@PathVariable String serial){
		this.bagRepository.updateBloodBagStateBySerial(BloodBagState.Available.toString(),serial);
		return this.bagRepository.findBySerial(serial);
	}

	/**
	 * import a bag from the central node database
	 * 
	 * @param bagDAO the bag to import
	 * @return the imported bag
	 * @throws ParseException if the bag is not valid
	 */
	/* @TODO: serial not bagDAO */
	@PostMapping("/bloodbag/import")
	public BloodBagDAO importBloodBag(@RequestBody BloodBagDAO bagDAO) throws ParseException, IOException {
		long id = new Date().getTime(); // soluzione "lazy"
		LoggerDAO loggerDAO = new LoggerDAO();
		loggerDAO.setIdLog(id);
		loggerDAO.setInformation(bagDAO.getSerial());
		loggerDAO.setCurrentUserUsername(SecurityContextHolder.getContext().getAuthentication().getName().toString());
		loggerDAO.setFromClass(this.getClass().toString());
		loggerDAO.setAction(Actions.BLOODBAG_IMPORT.toString());
		loggerDAO.setExplanation("");

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

			loggerDAO.setResult(Results.OPERATION_SUCCESSFUL.toString());
			it.unisannio.CARE.model.util.Logger.LogManager logManager = new LogManager(loggerDAO);
			logManager.writeLog();
			logRepository.save(loggerDAO);

			return bagRepository.save(bagDAO);
		} catch (RequestNotFoundException e) {
			loggerDAO.setResult(Results.OPERATION_REFUSED.toString());
			loggerDAO.setExplanation("request not found");
			it.unisannio.CARE.model.util.Logger.LogManager logManager = new LogManager(loggerDAO);
			logManager.writeLog();
			logRepository.save(loggerDAO);
			e.printStackTrace();
			throw new RequestNotFoundException("La richiesta che si vuole concludere non è esistente.",
					"/bloodbag/import");
		} catch (BloodBagNotFoundException e) {
			loggerDAO.setResult(Results.OPERATION_REFUSED.toString());
			loggerDAO.setExplanation("blood bag not found");
			it.unisannio.CARE.model.util.Logger.LogManager logManager = new LogManager(loggerDAO);
			logManager.writeLog();
			logRepository.save(loggerDAO);
			e.printStackTrace();
			throw new BloodBagNotFoundException(e.getMessage(), "/bloodbag/add");
		} finally {

			managerB.close();
			managerR.close();
		}

	}

	/**
	 * this method is called to USE a blood bag, it changes the blood bag state to
	 * "Used" 1) find blood bag 2) delete the blood bag 3) modify the blood bag
	 * state 4) save the new bloood bag
	 * 
	 * @param serial the blood bag serial
	 * @return blood bag
	 */

	@DeleteMapping("/bloodbag/use/{serial}")
	public BloodBagDAO useBloodBag(@PathVariable("serial") String serial) throws IOException {
		long id = new Date().getTime(); // soluzione "lazy"
		LoggerDAO loggerDAO = new LoggerDAO();
		loggerDAO.setIdLog(id);
		loggerDAO.setInformation(serial);
		loggerDAO.setCurrentUserUsername(SecurityContextHolder.getContext().getAuthentication().getName().toString());
		loggerDAO.setFromClass(this.getClass().toString());
		loggerDAO.setAction(Actions.BLOODBAG_USED.toString());
		loggerDAO.setExplanation("");

		if (bagRepository.getById(serial).getState().equals("Used")) {
			loggerDAO.setResult(Results.OPERATION_GONEBAD.toString());
			loggerDAO.setExplanation("the blood bag was already used.");
			it.unisannio.CARE.model.util.Logger.LogManager logManager = new LogManager(loggerDAO);
			logManager.writeLog();
			logRepository.save(loggerDAO);
			throw new BloodBagStateException("the blood bag was already used.");
		}
		bagRepository.updateBloodBagStateBySerial(BloodBagState.Used.toString(), serial);
		bagRepository.updateBloodBagUsedTimestampBySerial(new Date().getTime(), serial);

		loggerDAO.setResult(Results.OPERATION_SUCCESSFUL.toString());
		it.unisannio.CARE.model.util.Logger.LogManager logManager = new LogManager(loggerDAO);
		logManager.writeLog();
		logRepository.save(loggerDAO);
		return this.getBloodBagBySerial(serial);
	}

	// miscellaneous
	@GetMapping("/bloodbag/get/remote/{serial}/{nodeid}")
	public BloodBagDAO getRemoteBloodBag(@PathVariable String serial, @PathVariable String nodeid)
			throws NodeNotFoundException, IOException {
		long id = new Date().getTime(); // soluzione "lazy"
		LoggerDAO loggerDAO = new LoggerDAO();
		loggerDAO.setIdLog(id);
		loggerDAO.setInformation(serial);
		loggerDAO.setCurrentUserUsername(SecurityContextHolder.getContext().getAuthentication().getName().toString());
		loggerDAO.setFromClass(this.getClass().toString());
		loggerDAO.setAction(Actions.BLOODBAG_GET.toString());
		loggerDAO.setExplanation("");

		Set<String> nodes = new HashSet<>();
		for (NodeIDs str : NodeIDs.values()) {
			nodes.add(str.toString());
		}
		if (nodes.contains(nodeid)) {
			// invia la sacca
			System.out.println("entro");
			BloodBagDAO dao = this.getBloodBagBySerial(serial);
			if (dao == null) {
				loggerDAO.setResult(Results.OPERATION_GONEBAD.toString());
				loggerDAO.setExplanation("There is no bloodbag with the given serial.");
				it.unisannio.CARE.model.util.Logger.LogManager logManager = new LogManager(loggerDAO);
				logManager.writeLog();
				logRepository.save(loggerDAO);
				throw new BloodBagNotFoundException("There is no bloodbag with the given serial.");
			}
			if (dao.getState().equals(BloodBagState.Available.toString())) {
				loggerDAO.setResult(Results.OPERATION_SUCCESSFUL.toString());
				it.unisannio.CARE.model.util.Logger.LogManager logManager = new LogManager(loggerDAO);
				logManager.writeLog();
				return dao;
			}
			loggerDAO.setResult(Results.OPERATION_REFUSED.toString());
			loggerDAO.setExplanation("The bag is not available");
			it.unisannio.CARE.model.util.Logger.LogManager logManager = new LogManager(loggerDAO);
			logManager.writeLog();
			logRepository.save(loggerDAO);
			throw new BloodBagStateException("The bag is not available");

		}
		loggerDAO.setResult(Results.OPERATION_REFUSED.toString());
		loggerDAO.setExplanation("The provided node does not exists.");
		it.unisannio.CARE.model.util.Logger.LogManager logManager = new LogManager(loggerDAO);
		logManager.writeLog();
		logRepository.save(loggerDAO);
		throw new NodeNotFoundException("The provided node does not exists.");
	}

	private String getAddress(String nodeid) throws NodeNotFoundException {
		Properties properties = XMLHelper.getProps("localsettings/RoutingTable.xml");
		String address = properties.getProperty(nodeid);
		System.out.println(address);
		if (address==null)
			throw new NodeNotFoundException("there is no node with the given nodeid.");
		return address;
	}


	@GetMapping("/bloodbag/transfer/{serial}/{nodeid}/{token}")
	public void transferBloodBag(@PathVariable String serial, @PathVariable String nodeid, @PathVariable String token)
			throws Exception {
		String address = this.getAddress(nodeid);

		JSONObject userInfo = this.getUsernameFromToken(token);
		long id = new Date().getTime(); // soluzione "lazy"
		LoggerDAO loggerDAO = new LoggerDAO();
		loggerDAO.setIdLog(id);
		loggerDAO.setInformation(serial);
		loggerDAO.setCurrentUserUsername(userInfo.get("sub").toString());
		loggerDAO.setFromClass(this.getClass().toString());
		loggerDAO.setAction(Actions.BLOODBAG_TRANSFERED.toString());
		try {
			String request = "http://"+address+"/bloodbag/get/remote/" + serial + "/" + nodeid;
			P2PManager manager = new P2PManager(request, token);
			JSONArray object = manager.sendGet();

			JSONObject bloodbag = (JSONObject) object.get(0);
			System.out.println(bloodbag);

			// manager.setRequest("http://192.168.1.45:8088/bloodbag/update/state/"+BloodBagState.Transfered+"/"+serial);
			manager.setRequest(
					"http://"+address+"/bloodbag/update/state/" + BloodBagState.Transfered + "/" + serial);
			manager.sendGetNoResponse();

			BloodBagDAO bagToSave = new BloodBagDAO();
			bagToSave.setSerial(bloodbag.get("serial").toString());
			bagToSave.setGroup(bloodbag.get("group").toString());
			bagToSave.setDonator(bloodbag.get("donator").toString());
			bagToSave.setCreationDate((Long) bloodbag.get("creationDate"));
			bagToSave.setExpirationDate((Long) bloodbag.get("expirationDate"));
			bagToSave.setState(BloodBagState.receiving.toString());
			bagToSave.setNotes(bloodbag.get("notes").toString());
			bagToSave.setUsedTimeStamp(0L);

			System.out.println(bagToSave.toString());
			bagRepository.save(bagToSave);

			// scrivo il log qui perchè se arriva a questa linea non ci sono errori
			// long id, String currentEmail, String currentUsername, String fromClass,
			// String result, String action

			loggerDAO.setResult(Results.OPERATION_SUCCESSFUL.toString());
			it.unisannio.CARE.model.util.Logger.LogManager logManager = new LogManager(loggerDAO);
			logManager.writeLog();
			logRepository.save(loggerDAO);

		} catch (Exception e) {
			e.printStackTrace();
			loggerDAO.setResult(Results.OPERATION_REFUSED.toString());
			it.unisannio.CARE.model.util.Logger.LogManager logManager = new LogManager(loggerDAO);
			logManager.writeLog();
			logRepository.save(loggerDAO);
		}
	}

	private JSONObject getUsernameFromToken(String token) throws org.json.simple.parser.ParseException, UserException {
		String[] chunks = token.split("\\.");

		Base64.Decoder decoder = Base64.getDecoder();
		String payload = new String(decoder.decode(chunks[1]));

		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(payload);

		return json;
	}

	@PostMapping("bloodbag/update/state")
	public void updateBloodBagStateBySerial(@RequestBody String state, @RequestBody String serial) {
		bagRepository.updateBloodBagStateBySerial(state, serial);
	}

	/**
	 * this method exports a json containing all the blood bags
	 */
	@GetMapping("/export/bloodbag/json")
	public void exportToJson() {
		JSONArray sqlDatabase = new JSONArray();
		Iterable<BloodBagDAO> beans = this.getBloodBag();

		for (BloodBagDAO bean : beans) {
			sqlDatabase.add(this.getJsonObjectFromBean(bean));
		}

		try {
			FileWriter fileWriter = new FileWriter(
					"exports/jsonExport" + Constants.dateFormatString.format(new Date()) + ".json");
			fileWriter.write(sqlDatabase.toJSONString());
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * get the json from a specific bag
	 * 
	 * @param bean the bean used to generate the json object
	 * @return jsonObject the object generated from the bean
	 */
	private JSONObject getJsonObjectFromBean(BloodBagDAO bean) {
		JSONObject object = new JSONObject();
		object.put("serial", bean.getSerial());
		object.put("group", bean.getGroup());
		object.put("donator", bean.getDonator());
		object.put("creationDate", bean.getCreationDate());
		object.put("expirationDate", bean.getExpirationDate());
		object.put("state", bean.getState());
		object.put("notes", bean.getNotes());
		object.put("usedTimeStamp", bean.getUsedTimeStamp());
		return object;
	}

}
