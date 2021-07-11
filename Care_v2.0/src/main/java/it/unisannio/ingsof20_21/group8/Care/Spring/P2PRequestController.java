/**
 * @author giuliano ranauro
 * Date: 10/07/2021 18:22
 * Ide: Intellij
 * JDK: 1.8
 * @version 1.0
 */
package it.unisannio.ingsof20_21.group8.Care.Spring;

import it.unisannio.CARE.model.bloodBag.BloodBagState;
import it.unisannio.CARE.model.bloodBag.RequestState;
import it.unisannio.CARE.modulep2p.P2PManager;
import it.unisannio.CARE.modulep2p.RequestType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
    public JSONArray acceptRequest(@PathVariable String serial, @PathVariable String token) throws Exception {
        requestRepo.updateRequestStateBySerial(RequestState.transfered.toString(), serial);

        String request = "http://192.168.1.204:8087/bloodbag/get/serial/"+serial;
        P2PManager local = new P2PManager(request,token);
        JSONArray jsonArray = local.sendGet();
        JSONObject bag = (JSONObject) jsonArray.get(0);
        System.out.println(bag.get("serial").toString());

        //salvo la bloodbag richiesta sul pc richiedente
        request = "http://"+"192.168.1.25:8088"+"/bloodbag/add/forced";

        JSONObject bagJson = new JSONObject();
        bagJson.put("serial",bag.get("serial").toString());
        bagJson.put("group",bag.get("group").toString());
        bagJson.put("donator",bag.get("donator").toString());
        bagJson.put("creationDate",bag.get("creationDate"));
        bagJson.put("expirationDate",bag.get("expirationDate"));
        bagJson.put("state", BloodBagState.receiving.toString());
        bagJson.put("notes",bag.get("notes").toString());

        //porta da ricavare dinamicamente
        request = "http://192.168.1.25:8088/bloodbag/add/forced";
        P2PManager remote = new P2PManager(request,token,bagJson, RequestType.POST);
        return remote.sendRequest();
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
