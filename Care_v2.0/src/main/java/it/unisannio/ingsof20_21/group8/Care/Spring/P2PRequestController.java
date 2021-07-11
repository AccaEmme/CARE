/**
 * @author giuliano ranauro
 * Date: 10/07/2021 18:22
 * Ide: Intellij
 * JDK: 1.8
 * @version 1.0
 */
package it.unisannio.ingsof20_21.group8.Care.Spring;

import it.unisannio.CARE.model.bloodBag.Request;
import it.unisannio.CARE.model.bloodBag.RequestState;
import it.unisannio.CARE.modulep2p.P2PManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.util.List;

@CrossOrigin("*")
@RestController

@Consumes("application/json")
@Produces("application/json")
public class P2PRequestController {
    private final RequestRepository requestRepo;

    public P2PRequestController(RequestRepository requestRepo) {
        this.requestRepo = requestRepo;
    }

    @GetMapping("p2prequest/get/all")
    public List<RequestDAO> getAllp2pRequests(){
        return this.requestRepo.findAll();
    }

    @PostMapping("p2prequest/add")
    public RequestDAO addp2pRequest(@RequestBody RequestDAO request){
        return this.requestRepo.save(request);
    }

    @PatchMapping("p2prequest/accept/{serial}/{token}")
    public BloodBagDAO acceptRequest(@PathVariable String serial, @PathVariable String token) throws Exception {
        //requestRepo.updateRequestStateBySerial(RequestState.transfered.toString(), serial);
        //httprequest from java to the requesting node
        /*RequestDAO requestDAO = requestRepo.getRequestFromSerial(serial);

        JSONObject requestBody = new JSONObject();
        requestBody.put("serial",requestDAO.getSerial());
        requestBody.put("timestamp",requestDAO.getTimestamp());
        requestBody.put("requestingNode",requestDAO.getRequestingNode());
        requestBody.put("state",RequestState.receiving);*/

        //da cambiare con l'url da prendere dalla routing table
        String request = "http://192.168.1.25:8088/bloodbag/get/serial/"+serial;
        P2PManager manager = new P2PManager(request,token);
        JSONArray jsonArray = manager.sendGet();
        JSONObject bag = (JSONObject) jsonArray.get(0);


        BloodBagDAO bloodbag = new BloodBagDAO();
        bloodbag.setSerial(bag.get("serial").toString());
        bloodbag.setGroup(bag.get("group").toString());
        bloodbag.setDonator(bag.get("donator").toString());
        bloodbag.setCreationDate((Long) bag.get("creationDate"));
        bloodbag.setExpirationDate((Long) bag.get("expirationDate"));
        bloodbag.setState(bag.get("state").toString());
        bloodbag.setNotes(bag.get("notes").toString());

        return bloodbag;
    }

    @PatchMapping("p2prequest/set/state/{serial}/{state}")
    private void setRequestStateBySerial(@PathVariable String serial, @PathVariable String state){
        requestRepo.updateRequestStateBySerial(RequestState.valueOf(state).toString(),serial);
    }

    @GetMapping("p2prequest/get/{serial}")
    private RequestDAO getRequestFromSerial(@PathVariable String serial){
        return requestRepo.getRequestFromSerial(serial);
    }

    @GetMapping("p2prequest/get/state/{state}")
    public Iterable<RequestDAO> getRequestsByState(@PathVariable String state){
        return requestRepo.getRequestsByState(state);
    }

}
