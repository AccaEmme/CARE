package it.unisannio.ingsof20_21.group8.Care.Spring;

import it.unisannio.CARE.Model.BloodBag.BloodBag;
import it.unisannio.CARE.Model.BloodBag.BloodGroup;
import it.unisannio.CARE.Model.BloodBag.Serial;
import it.unisannio.CARE.Model.Util.Constants;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.synth.SynthUI;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


@CrossOrigin("*")
@RestController
@Consumes("application/json")
@Produces("application/json")
public class BloodBagController implements ContainerResponseFilter {
    private final BloodBagRepository bagRepository;
    
    public BloodBagController(BloodBagRepository repository){
        this.bagRepository = repository;
    }


    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        responseContext.getHeaders().add("Access-Control-Allow-Headers",
                "CSRF-Token, X-Requested-By, Authorization, Content-Type");
        responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        responseContext.getHeaders().add("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }


    //############# GET #############

    @GetMapping("/bloodbag")
    public BloodBagBean /*Iterable<BloodBagBean> ritornerà un iterable*/ getBloodBag(){
        BloodBagBean bagBean = new BloodBagBean();

        bagBean.setSerial("SERIALE_DI_PROVA_TEST");
        bagBean.setGroup("ABp");
        bagBean.setDonator("CODF_DEL_DONATORE");
        long creation = 934215560L;
        long expiration = 934215560L;
        bagBean.setCreationDate(creation);
        bagBean.setExpirationDate(expiration);
        bagBean.setState("Transfered");
        bagBean.setNotes("niente da dichairare");
        
       
        return bagBean;
    }
    
    @GetMapping("/bloodbag/group/{group}")
    public Iterable<BloodBagBean> getBloodBagByGroup(@PathVariable String group){
		return bagRepository.findByGroup(group); /*.orElseThrow();*/
    }
    
    @GetMapping("/bloodbag/serial/{serial}")
    public Iterable<BloodBagBean> getBloodBagBySerial(@PathVariable String serial){
    	return bagRepository.findBySerial(serial); /*.orElseThrow();*/
    }

    //############# POST #############
    @PostMapping("/addBloodBag")
    public BloodBagBean createBloodBag(@RequestBody BloodBagBean bagBean) throws ParseException {
        /**
         * Serial serial, BloodGroup valueOf, Date cd, Date ed, String donatorCF2,
         * 			BloodBagState valueOf2, String note2*/

        Serial serial = new Serial(bagBean.getSerial());
        BloodGroup group = BloodGroup.valueOf(bagBean.getGroup());

        /*
        String creationStr = String.valueOf(bagBean.getCreationDate());
        String expirationStr = String.valueOf(bagBean.getExpirationDate());

        Date creationDate = new SimpleDateFormat(Constants.DATE_FORMAT_STRING).parse(creationStr);
        Date expirationDate = new SimpleDateFormat(Constants.DATE_FORMAT_STRING).parse(expirationStr);*/

        long creationTS = bagBean.getCreationDate();
        long expirationTS = bagBean.getExpirationDate();

        Date creationDate = new Date(creationTS);
        Date expirationDate = new Date(expirationTS);

        String donatorCF = bagBean.getDonator();
        BloodBag.BloodBagState state = BloodBag.BloodBagState.valueOf(bagBean.getState());
        String note = bagBean.getNotes();



        BloodBag tempBloodBagObj = new BloodBag(
                serial,
                group,
                creationDate,
                expirationDate,
                donatorCF,
                state,
                note
        );

        BloodBagBean saveBean = tempBloodBagObj.getBean();
        
        
        return bagRepository.save(saveBean);
    }

    @DeleteMapping("/deleteBloodBag")
    public void deleteBloodBag(@RequestBody BloodBagBean deleteBloodBag){
        //should change bloodbag state!
    }
}

