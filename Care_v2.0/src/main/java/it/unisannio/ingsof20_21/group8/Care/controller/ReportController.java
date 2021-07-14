package it.unisannio.ingsof20_21.group8.Care.controller;

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
import it.unisannio.CARE.model.report.CompositReportable;
import it.unisannio.CARE.model.report.UserReport;
import it.unisannio.CARE.model.user.Role;
import it.unisannio.CARE.model.util.Constants;
import it.unisannio.CARE.model.util.QRCode;
import it.unisannio.CARE.model.util.Logger.Actions;
import it.unisannio.CARE.model.util.Logger.LogManager;
import it.unisannio.CARE.model.util.Logger.Results;
import it.unisannio.CARE.modulep2p.Node.NodeIDs;
import it.unisannio.ingsof20_21.group8.Care.Spring.BloodBagRepository;
import it.unisannio.ingsof20_21.group8.Care.Spring.UserRepository;
import it.unisannio.CARE.modulep2p.P2PManager;
@CrossOrigin("*")
@RestController

@Consumes("application/json")
@Produces("application/json")
public class ReportController  {
	private final BloodBagRepository bagRepository;
	private final UserRepository userRepository;

	public ReportController(BloodBagRepository repository1, UserRepository repository2) {
		this.bagRepository = repository1;
		this.userRepository = repository2;

	}
	
	// ############# get count ###############


		/**
		 * counts all the blood bags expiring between a specific time interval, having a
		 * specific blood group (timestamp)
		 *
		 * @return the report
		 */
		@GetMapping("/report")
		public void getReport() throws IOException {
			BloodBagReport reportbb =  new BloodBagReport(bagRepository.countAll(),bagRepository.countByState(BloodBagState.Available.toString()),
					bagRepository.countByState(BloodBagState.Used.toString()),
					bagRepository.countByState(BloodBagState.Transfered.toString()),
					bagRepository.countByState(BloodBagState.Dropped.toString()),

					bagRepository.countByGroup(BloodGroup.ABpos.toString()), 	bagRepository.countByGroup(BloodGroup.Aneg.toString()),
					bagRepository.countByGroup(BloodGroup.Bpos.toString()), 	bagRepository.countByGroup(BloodGroup.Bneg.toString()),
					bagRepository.countByGroup(BloodGroup.ZEROpos.toString()),
					bagRepository.countByGroup(BloodGroup.ZEROneg.toString()), 	bagRepository.countByGroup(BloodGroup.ABpos.toString()),
					bagRepository.countByGroup(BloodGroup.ABneg.toString()),
					bagRepository.countUsedBetweenDates(new Date().getTime() - Constants.SEVEN_DAYS_MILLIS,
							new Date().getTime()),
					bagRepository.countUsedAfterDate(new Date().getTime() - Constants.SEVEN_DAYS_MILLIS));
		
		    	UserReport reportu =  new UserReport(userRepository.countAllUsers(),userRepository.countUsersByState((short) -1),userRepository.countUsersByState((short) -2),
		    			userRepository.countUsersByLastLogin((new Date().getTime() - 3600000), new Date().getTime()),userRepository.countDeletedUsers(),userRepository.countDeletedUsers(),
		    			userRepository.countUsersByRole(Role.ROLE_ADMINISTRATOR.toString()),userRepository.countUsersByRole(Role.ROLE_STOREMANAGER.toString()),
		    			userRepository.countUsersByRole(Role.ROLE_OFFICER.toString()));
			
		    	CompositReportable cr=new CompositReportable();
		    	
		    	cr.add(reportu);
		    	cr.add(reportbb);
		    	cr.saveReport();
		    	
			
	
		}
}