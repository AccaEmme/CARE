package it.unisannio.ingsof20_21.group8.Care.Spring;

import it.unisannio.CARE.Model.BloodBag.BloodBag;
import it.unisannio.CARE.Model.BloodBag.BloodGroup;
import it.unisannio.CARE.Model.BloodBag.Serial;
import it.unisannio.CARE.Model.Util.BloodBagReport;
import it.unisannio.CARE.Model.Util.Constants;
import org.glassfish.jersey.internal.guava.Iterators;
import org.json.JSONObject;
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

    @GetMapping("/bloodBag")
    public Iterable<BloodBagBean> getBloodBag(){
        return bagRepository.findAll();
    }

    @GetMapping("/bloodBag/state/{state}")
    public Iterable<BloodBagBean> filterBloodBagsByState(@PathVariable String state){
        return bagRepository.filterByState(state);
    }
    
    @GetMapping("/bloodBag/group/{group}")
    public Iterable<BloodBagBean> getBloodBagByGroup(@PathVariable String group){
		return bagRepository.findByGroup(group); /*.orElseThrow();*/
    }
    
    @GetMapping("/bloodBag/serial/{serial}")
    public Iterable<BloodBagBean> getBloodBagBySerial(@PathVariable String serial){
    	return bagRepository.findBySerial(serial); /*.orElseThrow();*/
    }


    //working
    @GetMapping("bloodBag/count/all")
    public long getAllBagsCount(){
        return bagRepository.countAll();
    }
    //working
    @GetMapping("bloodBag/count/group/{group}")
    public long getCountByGroup(@PathVariable String group){
        return bagRepository.countByGroup(group);
    }
    //working
    @GetMapping("bloodBag/count/state/{state}")
    public long getCountByState(@PathVariable String state){
        return bagRepository.countByState(state);
    }


    @GetMapping("bloodBag/report")
    public BloodBagReport getReport(){
        long total = this.getAllBagsCount();
        long available = this.getCountByState(BloodBag.BloodBagState.Available.toString());
        long used = this.getCountByState(BloodBag.BloodBagState.Used.toString());
        long transfered = this.getCountByState(BloodBag.BloodBagState.Transfered.toString());
        long dropped = this.getCountByState(BloodBag.BloodBagState.Dropped.toString());


        //Apos, Aneg, Bpos, Bneg, ZEROpos, ZEROneg, ABpos, ABneg;
        long Apos = this.getCountByGroup(BloodGroup.ABpos.toString());
        long Aneg = this.getCountByGroup(BloodGroup.Aneg.toString());
        long Bpos = this.getCountByGroup(BloodGroup.Bpos.toString());
        long Bneg = this.getCountByGroup(BloodGroup.Bneg.toString());
        long ZEROpos = this.getCountByGroup(BloodGroup.ZEROpos.toString());
        long ZEROneg = this.getCountByGroup(BloodGroup.ZEROneg.toString());
        long ABpos = this.getCountByGroup(BloodGroup.ABpos.toString());
        long ABneg = this.getCountByGroup(BloodGroup.ABneg.toString());

        BloodBagReport report = new BloodBagReport(total,available,used,transfered,dropped,Apos,Aneg,Bpos,Bneg,ZEROpos,ZEROneg,ABpos,ABneg);

        return report;
    }


    //############# POST #############

    /**
     * {
     *     "serial":"IT-NA205101-Aneg-20210615-0037",
     *     "group":"Bneg",
     *     "creationDate":965837967,
     *     "expirationDate":965837968,
     *     "donator":"CRSDLCER86BH0919",
     *     "state":"Available",
     *     "notes":"test note"
     * }*/
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



    @DeleteMapping("/bloodBag/use/{serial}")
    public BloodBagBean useBloodBag(@PathVariable("serial") String serial){
        System.err.println("ENTROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
        //should change bloodbag state!
        //find blood bag
        //delete the blood bag
        //modify the blood bag state
        //save the new bloood bag

        Iterable<BloodBagBean> beans = this.getBloodBagBySerial(serial);
        BloodBagBean beanToChange = null;

        System.err.println(beans);
        for (BloodBagBean bean : beans){
            System.err.println(bean.toString());
            beanToChange = bean;

            bagRepository.delete(beanToChange);
            beanToChange.setState(BloodBag.BloodBagState.Used.toString());

            bagRepository.save(beanToChange);
        }
        return beanToChange;
    }


}

