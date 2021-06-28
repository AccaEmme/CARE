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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.unisannio.CARE.Control.BloodBags.RequestManager;
import it.unisannio.CARE.Model.BloodBag.BloodBag;
import it.unisannio.CARE.Model.BloodBag.Request;
import it.unisannio.CARE.Model.BloodBag.Request.RequestPriority;
import it.unisannio.CARE.Model.BloodBag.Request.RequestState;
import it.unisannio.CARE.View.Classes.RequestBean;


@CrossOrigin("*")
@RestController
public class RequestAPI implements ContainerResponseFilter {


	public RequestAPI() {}

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
	public ArrayList<RequestBean> getRequestesByState(@RequestBody RequestState state){
		
		Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
		mongoLogger.setLevel(Level.SEVERE);
			
		ArrayList<RequestBean> result = new ArrayList<RequestBean>();
		
		List<Document> l = (new RequestManager("richieste").getRequestesByState(state));
		
		for (Document s : l)
			result.add(new RequestBean(s.getString("id_requester"), 
				s.getString("id_request"),
				s.getString("serial"),
				s.getString("date"),
				s.getString("note"),
				s.getString("state"),
				s.getString("priority")));
					
		return result;

	}
	
/*	public Request(String idLocation, String idRequest, BloodBag requestedBag, 
			Date requestDate, String note, RequestState state, RequestPriority priority) {*/
	
	@PostMapping("request/add")	
	public void addRequest(@RequestBody RequestBean requestB) throws ParseException{		

		Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
		mongoLogger.setLevel(Level.SEVERE);
		
		Request request = new Request(requestB.getId_requester(), requestB.getId_request(), requestB.getSerial(), 
				new Date(), requestB.getNote(),RequestState.valueOf(requestB.getState()),RequestPriority.valueOf(requestB.getPriority()));		
		(new RequestManager("richieste")).addRequest(request);
			
	}
	
	/*
	 * migliorare i metodi accept e refuse nel manager e modifcare Request
	 */
	
	@PutMapping("request/accept")	
	public void acceptRequest(@RequestBody RequestBean requestB) throws ParseException{		

		Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
		mongoLogger.setLevel(Level.SEVERE);
		
		Request request = new Request(requestB.getId_requester(), requestB.getId_request(), requestB.getSerial(), 
				new SimpleDateFormat().parse(requestB.getDate()), requestB.getNote(),RequestState.valueOf(requestB.getState()),RequestPriority.valueOf(requestB.getPriority()));		
		(new RequestManager("richieste")).acceptRequest(request);
			
	}

	@PutMapping("request/refuse")	
	public void refuseRequest(@RequestBody RequestBean requestB) throws ParseException{		

		Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
		mongoLogger.setLevel(Level.SEVERE);
		
		Request request = new Request(requestB.getId_requester(), requestB.getId_request(), requestB.getSerial(), 
				new SimpleDateFormat().parse(requestB.getDate()), requestB.getNote(),RequestState.valueOf(requestB.getState()),RequestPriority.valueOf(requestB.getPriority()));		
		(new RequestManager("richieste")).refuseRequest(request);
			
	}


}
