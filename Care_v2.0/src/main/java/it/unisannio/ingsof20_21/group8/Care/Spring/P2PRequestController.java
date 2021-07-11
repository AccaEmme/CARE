/**
 * @author giuliano ranauro
 * Date: 10/07/2021 18:22
 * Ide: Intellij
 * JDK: 1.8
 * @version 1.0
 */
package it.unisannio.ingsof20_21.group8.Care.Spring;

import it.unisannio.CARE.model.bloodBag.RequestState;
import it.unisannio.CARE.modulep2p.P2PManager;
import org.json.simple.JSONArray;
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

    @PatchMapping("p2prequest/accept/{serial}")
    public void acceptRequest(@PathVariable String serial){
        requestRepo.updateRequestStateBySerial(RequestState.accepted_waiting_for_response.toString(), serial);
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

    //serial-nodorichiedente-nodoripondente (indirizzo o hostname)
    @GetMapping("p2prequest/get/accepted/{serial}/{requesting}/{responding}/{token}")
    public JSONArray getAcceptedBag(@PathVariable String serial, @PathVariable String requesting, @PathVariable String responding, @PathVariable String token ) throws Exception {
        /*System.out.println("entro");
        String request = "http://"+"localhost"+"/p2prequest/get/"+serial;
        P2PManager manager = new P2PManager(request,token);
        JSONArray dao = manager.sendGet();*/
        Iterable<RequestDAO> waitingRequests = this.getRequestsByState(RequestState.accepted_waiting_for_response.toString());
        for (RequestDAO request : waitingRequests)
            System.out.println(request.getSerial());
        return null;
    }
}
