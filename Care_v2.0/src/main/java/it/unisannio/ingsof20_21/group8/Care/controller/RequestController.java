package it.unisannio.ingsof20_21.group8.Care.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

import org.bson.Document;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unisannio.CARE.controll.bloodBag.BloodBagManager;
import it.unisannio.CARE.controll.request.RequestManager;
import it.unisannio.CARE.model.bloodBag.Request;
import it.unisannio.CARE.model.bloodBag.RequestPriority;
import it.unisannio.CARE.model.bloodBag.RequestState;
import it.unisannio.CARE.model.exceptions.BloodBagNotFoundException;
import it.unisannio.CARE.model.exceptions.RequestCloneNotSupportedException;
import it.unisannio.CARE.model.exceptions.RequestNotFoundException;
import it.unisannio.CARE.model.util.Constants;
import it.unisannio.CARE.model.util.XMLHelper;
import it.unisannio.CARE.spring.bean.ErrorBean;
import it.unisannio.CARE.spring.bean.RequestBean;

@RestController
@RequestMapping("request")

@Consumes("application/json")
@Produces("application/json")
public class RequestController /* implements ContainerResponseFilter */ {

	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	public RequestController() {
	}

	/*
	 * @Override public void filter(ContainerRequestContext requestContext,
	 * ContainerResponseContext responseContext) throws IOException {
	 * 
	 * responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
	 * responseContext.getHeaders().add("Access-Control-Allow-Headers",
	 * "CSRF-Token, X-Requested-By, Authorization, Content-Type");
	 * responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
	 * responseContext.getHeaders().add("Access-Control-Allow-Methods",
	 * "GET, POST, PUT, DELETE, OPTIONS, HEAD"); }
	 */

	// ################################################### GET METHOD
	// ####################################################
	@GetMapping("get/state/{state}")
	public Iterable<RequestBean> getRequestsByState(@PathVariable String state) {

		ArrayList<RequestBean> array = new ArrayList<>();

		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);

		RequestManager manager = new RequestManager();
		Iterable<Document> iterable = (manager.getRequestsByState(RequestState.valueOf(state)));

		for (Document requestD : iterable)
			array.add(new RequestBean(requestD.getString("id_requester"), requestD.getString("serial"),
					requestD.getString("date"), requestD.getString("note"), requestD.getString("state"),
					requestD.getString("priority")));

		manager.close();

		return array;

	}

	@GetMapping("get/priority/{priority}")
	public Iterable<RequestBean> getRequestsByPriority(@PathVariable String priority) {

		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);

		ArrayList<RequestBean> array = new ArrayList<>();

		RequestManager manager = new RequestManager();
		Iterable<Document> iterable = (manager.getRequestsByPriority(RequestPriority.valueOf(priority)));

		for (Document requestD : iterable)
			array.add(new RequestBean(requestD.getString("id_requester"), requestD.getString("serial"),
					requestD.getString("date"), requestD.getString("note"), requestD.getString("state"),
					requestD.getString("priority")));

		manager.close();

		return array;

	}

	@GetMapping("get/all")
	public Iterable<RequestBean> getAllRequests() {

		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);

		ArrayList<RequestBean> array = new ArrayList<>();

		RequestManager manager = new RequestManager();
		Iterable<Document> iterable = (manager.getAllRequests());

		for (Document requestD : iterable)
			array.add(new RequestBean(requestD.getString("id_requester"), requestD.getString("serial"),
					requestD.getString("date"), requestD.getString("note"), requestD.getString("state"),
					requestD.getString("priority")));

		manager.close();

		return array;

	}

	@GetMapping("get/our")
	public Iterable<RequestBean> getOurRequests() {

		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);

		ArrayList<RequestBean> array = new ArrayList<>();

		RequestManager manager = new RequestManager();
		Iterable<Document> iterable = (manager.getOurRequests());

		for (Document requestD : iterable)
			array.add(new RequestBean(requestD.getString("id_requester"), requestD.getString("serial"),
					requestD.getString("date"), requestD.getString("note"), requestD.getString("state"),
					requestD.getString("priority")));

		manager.close();

		return array;

	}

	@GetMapping("get/our/state/{state}")
	public Iterable<RequestBean> getOurRequestsByState(@PathVariable String state) {

		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);

		ArrayList<RequestBean> array = new ArrayList<>();

		RequestManager manager = new RequestManager();
		Iterable<Document> iterable = (manager.getOurRequestsByState(RequestState.valueOf(state)));

		for (Document requestD : iterable)
			array.add(new RequestBean(requestD.getString("id_requester"), requestD.getString("serial"),
					requestD.getString("date"), requestD.getString("note"), requestD.getString("state"),
					requestD.getString("priority")));

		manager.close();

		return array;

	}

	@GetMapping("get/other")
	public Iterable<RequestBean> getOtherRequests() {

		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);

		ArrayList<RequestBean> array = new ArrayList<>();

		RequestManager manager = new RequestManager();
		Iterable<Document> iterable = (manager.getOtherRequests());

		for (Document requestD : iterable)
			array.add(new RequestBean(requestD.getString("id_requester"), requestD.getString("serial"),
					requestD.getString("date"), requestD.getString("note"), requestD.getString("state"),
					requestD.getString("priority")));

		manager.close();

		return array;

	}

	// ################################################### POST METHOD
	// ####################################################
	@PostMapping("/add")
	public RequestBean addRequest(@RequestBody RequestBean requestB) throws ParseException {

		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);

		BloodBagManager bbm = new BloodBagManager();
		if (!bbm.BloodBagRequestable(requestB.getSerial())) {

			bbm.close();
			throw new BloodBagNotFoundException(
					"La sacca su cui ?? stata fatta la richiesta non esiste o non ?? disponibile", "/request/add");

		}

		Properties props = XMLHelper.getProps(Constants.NODE_PROPERTIES);

		requestB.setId_requester(props.getProperty("province") + props.getProperty("structureCode"));
		requestB.setDate(DATE_FORMAT.format(new Date()));
		requestB.setState(RequestState.pending.toString());

		Request request = new Request(requestB.getId_requester(), requestB.getSerial(),
				DATE_FORMAT.parse(requestB.getDate()), requestB.getNote(), RequestState.valueOf(requestB.getState()),
				RequestPriority.valueOf(requestB.getPriority()));

		RequestManager manager = new RequestManager();

		try {

			manager.addRequest(request);
			return requestB;

		} catch (RequestCloneNotSupportedException e) {

			e.printStackTrace();
			throw new RequestCloneNotSupportedException(
					"La richiesta che si vuole aggiungere ?? gi?? esistente o ?? stata gi?? accettata.", "request/add");

		} finally {

			bbm.close();
			manager.close();
		}

	}

	// testato
	@PostMapping("/accept")
	public RequestBean acceptRequest(@RequestBody RequestBean requestB) throws ParseException {
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);

		RequestManager manager = new RequestManager();

		Document requestDoc = manager.getRequestByIdSerial(requestB.getId_requester(), requestB.getSerial());
		Request request = new Request(requestDoc.getString("id_requester"), requestDoc.getString("serial"),
				DATE_FORMAT.parse(requestDoc.getString("date")), requestDoc.getString("note"),
				RequestState.valueOf(requestDoc.getString("state")),
				RequestPriority.valueOf(requestDoc.getString("priority")));

		BloodBagManager bbm = new BloodBagManager();
		try {

			manager.acceptRequest(request);
			bbm.BloodBagMarkNotAvailable(requestB.getSerial());

			return requestB;

		} catch (RequestNotFoundException e) {

			e.printStackTrace();
			throw new RequestCloneNotSupportedException(e.getMessage(), "/request/accept");

		} catch (BloodBagNotFoundException e) {

			e.printStackTrace();
			throw new RequestCloneNotSupportedException(e.getMessage(), "/request/accept");

		} finally {

			manager.close();
			bbm.close();
		}
	}

	@PostMapping("/refuse")
	public RequestBean refuseRequest(@RequestBody RequestBean requestB) throws ParseException {

		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);

		RequestManager manager = new RequestManager();

		Document requestDoc = manager.getRequestByIdSerial(requestB.getId_requester(), requestB.getSerial());
		Request request = new Request(requestDoc.getString("id_requester"), requestDoc.getString("serial"),
				DATE_FORMAT.parse(requestDoc.getString("date")), requestDoc.getString("note"),
				RequestState.valueOf(requestDoc.getString("state")),
				RequestPriority.valueOf(requestDoc.getString("priority")));

		BloodBagManager bbm = new BloodBagManager();
		try {

			manager.refuseRequest(request);

			return requestB;

		} catch (RequestNotFoundException e) {

			e.printStackTrace();
			throw new RequestCloneNotSupportedException(e.getMessage(), "/request/refuse");

		} finally {

			manager.close();
		}
	}

	@PostMapping("delete")
	public RequestBean deleteRequest(@RequestBody RequestBean requestB) throws ParseException {

		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);

		Properties props = XMLHelper.getProps(Constants.NODE_PROPERTIES);

		requestB.setId_requester(props.getProperty("province") + props.getProperty("structureCode"));
		requestB.setDate(DATE_FORMAT.format(new Date()));
		requestB.setState(RequestState.pending.toString());
		requestB.setPriority(RequestPriority.green.toString());

		Request request = new Request(requestB.getId_requester(), requestB.getSerial(),
				DATE_FORMAT.parse(requestB.getDate()), requestB.getNote(), RequestState.valueOf(requestB.getState()),
				RequestPriority.valueOf(requestB.getPriority()));

		RequestManager manager = new RequestManager();
		try {

			manager.deleteRequest(request);

			return requestB;

		} catch (RequestNotFoundException e) {

			e.printStackTrace();
			throw new RequestNotFoundException(e.getMessage(), "/request/delete");

		} finally {

			manager.close();
		}
	}

	@PutMapping("emptytrash")
	public void emptyTrash() throws ParseException {

		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);

		RequestManager manager = new RequestManager("mongodb+srv://ricciuto99:desk9123@cluster0.ksjti.mongodb.net/test",
				"CARE", "requests");
		manager.emptyTrash();
		manager.close();
	}

}
