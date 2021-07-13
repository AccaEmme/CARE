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
 * Managed respectively in BloodBagController and in UserController
 */

