package it.unisannio.ingsof20_21.group8.Care.Spring;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
import org.springframework.web.bind.annotation.RestController;

import it.unisannio.CARE.Control.BloodBags.RequestManager;
import it.unisannio.CARE.Model.BloodBag.BloodBag;
import it.unisannio.CARE.Model.BloodBag.Request;
import it.unisannio.CARE.Model.BloodBag.Request.RequestPriority;
import it.unisannio.CARE.Model.BloodBag.Request.RequestState;
import it.unisannio.CARE.Model.Exceptions.RequestCloneNotSupportedException;
import it.unisannio.CARE.Model.Exceptions.RequestNotFoundException;
import it.unisannio.CARE.View.Classes.RequestBean;


@CrossOrigin("*")
@RestController

@Consumes("application/json")
@Produces("application/json")
public class RequestController implements ContainerResponseFilter {

	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	

	public RequestController() {}

	
	
	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		
		responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        responseContext.getHeaders().add("Access-Control-Allow-Headers",
                "CSRF-Token, X-Requested-By, Authorization, Content-Type");
        responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        responseContext.getHeaders().add("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
	}
	
	
	
	@GetMapping("request/state/{state}")	
	public ArrayList<RequestBean> getRequestesByState(@PathVariable String state){
		
		Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
		mongoLogger.setLevel(Level.SEVERE);
			
		ArrayList<RequestBean> result = new ArrayList<RequestBean>();
		
		
		RequestManager manager = new RequestManager("mongodb+srv://ricciuto99:desk9123@cluster0.ksjti.mongodb.net/test", "CARE", "requestes");
		List<Document> l = (manager.getRequestesByState(RequestState.valueOf(state)));
		
		for (Document s : l)
			result.add(new RequestBean(s.getString("id_requester"),
				s.getString("serial"),
				s.getString("date"),
				s.getString("note"),
				s.getString("state"),
				s.getString("priority")));
					
		manager.close();
		
		return result;

	}
	
	
	
	@PostMapping("request/add")	
	public String addRequest(@RequestBody RequestBean requestB) throws ParseException{

		Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
		mongoLogger.setLevel(Level.SEVERE);
		
		Request request = new Request(
				requestB.getId_requester(),
				requestB.getSerial(),
				DATE_FORMAT.getCalendar().getTime(),
				requestB.getNote(),
				RequestState.valueOf(requestB.getState()),
				RequestPriority.valueOf(requestB.getPriority()));
		
		RequestManager manager = new RequestManager("mongodb+srv://ricciuto99:desk9123@cluster0.ksjti.mongodb.net/test", "CARE", "requestes");
		try {
			manager.addRequest(request);
			manager.close();
			return request.toString();
		
		}catch(RequestCloneNotSupportedException e){
			
			manager.close();
			return "{"
					+ "\"timestamp\": \""+ new Date() +"\","
					+ "\"status\": \" -1 \","
					+ "\"error\": \""+ e.getMessage() +"\","
					+ "\"path\": \"request/add\",";
		}
			
		
		
	}
	
	
	
	@PostMapping("request/accept")
	public String acceptRequest(@RequestBody RequestBean requestB) throws ParseException{		

		Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
		mongoLogger.setLevel(Level.SEVERE);
		
		Request request = new Request(
				requestB.getId_requester(), 
				requestB.getSerial(), 
				DATE_FORMAT.parse(requestB.getDate()), 
				requestB.getNote(),
				RequestState.valueOf(requestB.getState()),
				RequestPriority.valueOf(requestB.getPriority()));
		
		RequestManager manager = new RequestManager("mongodb+srv://ricciuto99:desk9123@cluster0.ksjti.mongodb.net/test", "CARE", "requestes");
		try {
			manager.acceptRequest(request);
			manager.close();
			return request.toString();
		
		}catch(RequestNotFoundException e){
			
			manager.close();
			return "{"
					+ "\"timestamp\": \""+ new Date() +"\","
					+ "\"status\": \" -1 \","
					+ "\"error\": \""+ e.getMessage() +"\","
					+ "\"path\": \"request/accept\",";
		}
	}

	
	
	@PostMapping("request/refuse")	
	public String refuseRequest(@RequestBody RequestBean requestB) throws ParseException{		

		Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
		mongoLogger.setLevel(Level.SEVERE);
		
		Request request = new Request(
				requestB.getId_requester(), 
				requestB.getSerial(), 
				DATE_FORMAT.parse(requestB.getDate()), 
				requestB.getNote(),RequestState.valueOf(requestB.getState()),
				RequestPriority.valueOf(requestB.getPriority()));
		
			
		RequestManager manager = new RequestManager("mongodb+srv://ricciuto99:desk9123@cluster0.ksjti.mongodb.net/test", "CARE", "requestes");
		try {
			manager.refuseRequest(request);
			manager.close();
			return request.toString();
		
		}catch(RequestNotFoundException e){
			
			manager.close();
			return "{"
					+ "\"timestamp\": \""+ new Date() +"\","
					+ "\"status\": \" -1 \","
					+ "\"error\": \""+ e.getMessage() +"\","
					+ "\"path\": \"request/refuse\",";
		}
	}

	
	
	@PostMapping("request/empty-trash")	
	public void emptyTrash() throws ParseException{		

		Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
		mongoLogger.setLevel(Level.SEVERE);
			
		RequestManager manager = new RequestManager("mongodb+srv://ricciuto99:desk9123@cluster0.ksjti.mongodb.net/test", "CARE", "requestes");
		manager.emptyTrash();
		manager.close();
	}


}
