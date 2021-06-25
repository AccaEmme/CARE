package it.unisannio.CARE.Control.Interfaces;

import java.sql.Date;
import java.util.List;

import org.bson.Document;

import it.unisannio.CARE.Model.Classes.BloodBag;
import it.unisannio.CARE.Model.Classes.Request;

public interface RequestDataManagerInterface {

	void addRequest(Request request);
	
	void acceptRequest(Request request);
	
	void refuseRequest(Request request);
	
	List<Document> getRequestesInPendling();
	
	List<Document> getRequestesAccepted();
	
	List<Document> getRequestesRefused();


}
