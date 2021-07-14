package it.unisannio.ingsof20_21.group8.Care.controller;

import it.unisannio.CARE.model.bloodBag.BloodBagState;
import it.unisannio.CARE.model.bloodBag.RequestState;
import it.unisannio.CARE.model.exceptions.BloodBagNotFoundException;
import it.unisannio.CARE.model.exceptions.NodeNotFoundException;
import it.unisannio.CARE.model.exceptions.RequestNotFoundException;
import it.unisannio.CARE.model.util.XMLHelper;
import it.unisannio.CARE.modulep2p.P2PManager;
import it.unisannio.CARE.modulep2p.RequestException;
import it.unisannio.CARE.modulep2p.RequestType;
import it.unisannio.ingsof20_21.group8.Care.Spring.RequestDAO;
import it.unisannio.ingsof20_21.group8.Care.Spring.RequestRepository;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * this class is used to run http requests for the p2p bloodbag requests
 */
@CrossOrigin("*")
@RestController

@Consumes("application/json")
@Produces("application/json")
public class P2PRequestController {
    private final RequestRepository requestRepo;

    /**
     * @param requestRepo the repository interface containing all the queries
     */
    public P2PRequestController(RequestRepository requestRepo) {
        this.requestRepo = requestRepo;
    }

    /**
     * @return all the local requests
     */
    @GetMapping("p2prequest/get/all")
    public List<RequestDAO> getAllp2pRequests(){
        return this.requestRepo.findAll();
    }

    /**
     * @param request the new request
     * @return the added request
     */
    @PostMapping("p2prequest/add")
    public RequestDAO addp2pRequest(@RequestBody RequestDAO request){
        return this.requestRepo.save(request);
    }

    /**
     * used to send a remote bloodbag request
     * @param request the bloodbag request
     * @param nodeid the node having that blood bag
     * @param token the user's token
     * @return the added request
     * @throws NodeNotFoundException if there is no node having that id
     * @throws IOException null
     * @throws ParseException if the response is not valid
     */
    @PostMapping("p2prequest/add/remote/{nodeid}/{token}")
    public JSONArray addp2pRequestRemote(@RequestBody RequestDAO request, @PathVariable String nodeid, @PathVariable String token) throws NodeNotFoundException, IOException, ParseException {
        String remoteAddress = this.getAddressFromNodeID(nodeid);
        String httpRequest = "http://"+remoteAddress+"/p2prequest/add";
        JSONObject jsonBody = new JSONObject();

        jsonBody.put("serial" , request.getSerial());
        jsonBody.put("timestamp" , request.getTimestamp());
        jsonBody.put("requestingNode" , request.getRequestingNode());
        jsonBody.put("state" , request.getState());

        P2PManager manager = new P2PManager(httpRequest,token,jsonBody,RequestType.POST);
        return manager.sendRequest();
    }

    /**
     * get the node id
     * @return  the node id from the node_properties.xml
     */
    private String getNodeId(){
        Properties props = XMLHelper.getProps("localsettings/node_properties.xml");
        return props.getProperty("province")+props.getProperty("structureCode");
    }

    /**
     * get the node's address from the node's identifier
     * @param nodeID the node's id
     * @return the node's address
     * @throws NodeNotFoundException if there is no note having that id
     */
    private String getAddressFromNodeID(String nodeID) throws NodeNotFoundException {
        Properties props = XMLHelper.getProps("localsettings/RoutingTable.xml");
        String address = props.getProperty(nodeID);
        if (address==null)
            throw new NodeNotFoundException("there is no node having the provided id");
        return address;
    }

    /**
     * accept a local request, sending to the requesting node the requested bag
     * @param serial the request/bag's serial
     * @param token the user's token
     * @return the bloodbag's request
     * @throws Exception if the request is not valid
     */
    @PatchMapping("p2prequest/accept/{serial}/{token}")
    public JSONArray acceptRequest(@PathVariable String serial, @PathVariable String token) throws Exception {
        RequestDAO requestDAO = requestRepo.getRequestFromSerial(serial);
        //controlli preliminari
        if (requestDAO == null)
            throw new RequestNotFoundException("there is no request with the provided serial");
        else if (requestDAO.getState()!=RequestState.pending.toString()){
            if (requestDAO.getState().equals(RequestState.accepted))
                throw new RequestException("the request has already been accepted");
            else if (requestDAO.getState().equals(RequestState.refused))
                throw new RequestException("the request has already been refused");
            else if (requestDAO.getState().equals(RequestState.transfered))
                throw new RequestException("the request has already been sent");
            else throw new RequestException("unexpected exception");
        }


        requestRepo.updateRequestStateBySerial(RequestState.transfered.toString(), serial);

        String request = "http://"+this.getAddressFromNodeID(this.getNodeId())+"/bloodbag/get/serial/"+serial;
        P2PManager local = new P2PManager(request,token);
        JSONArray jsonArray = local.sendGet();
        if (jsonArray==null)
            throw new BloodBagNotFoundException("blood bag not found");
        JSONObject bag = (JSONObject) jsonArray.get(0);


        //salvo la bloodbag richiesta sul pc richiedente
        JSONObject bagJson = new JSONObject();
        bagJson.put("serial",bag.get("serial").toString());
        bagJson.put("group",bag.get("group").toString());
        bagJson.put("donator",bag.get("donator").toString());
        bagJson.put("creationDate",bag.get("creationDate"));
        bagJson.put("expirationDate",bag.get("expirationDate"));
        bagJson.put("state", BloodBagState.receiving.toString());
        bagJson.put("notes",bag.get("notes").toString());

        request = "http://"+this.getAddressFromNodeID(requestDAO.getRequestingNode())+"/bloodbag/add/forced";
        P2PManager remote = new P2PManager(request,token,bagJson, RequestType.POST);
        return remote.sendRequest();
    }

    /**
     * set a request's state
     * @param serial the request's serial
     * @param state the state
     */
    @PatchMapping("p2prequest/set/state/{serial}/{state}")
    private void setRequestStateBySerial(@PathVariable String serial, @PathVariable String state){
        requestRepo.updateRequestStateBySerial(RequestState.valueOf(state).toString(),serial);
    }

    /**
     * get a request from it's serial
     * @param serial the request's serial
     * @return the request
     */
    @GetMapping("p2prequest/get/{serial}")
    private RequestDAO getRequestFromSerial(@PathVariable String serial){
        return requestRepo.getRequestFromSerial(serial);
    }

    /**
     * get all the request having a specific state
     * @param state the request's state
     * @return all the request having that state
     */
    @GetMapping("p2prequest/get/state/{state}")
    public Iterable<RequestDAO> getRequestsByState(@PathVariable String state){
        return requestRepo.getRequestsByState(state);
    }

}
