package it.unisannio.ingsof20_21.group8.Care.Spring;

import it.unisannio.CARE.Model.BloodBag.BloodBag;
import it.unisannio.CARE.Model.BloodBag.BloodGroup;
import it.unisannio.CARE.Model.BloodBag.Serial;
import it.unisannio.CARE.Model.Report.BloodBagReport;

import it.unisannio.CARE.Model.Util.Constants;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
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

    //############# get count ###############
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
    @GetMapping("bloodBag/count/expiring/after/{timestamp}")

    public long getCountExpiringAfterDate(@PathVariable long timestamp){
        return bagRepository.countExpirationAfterDate(timestamp);
    }

    //########### GET EXPIRING BEFORE/AFTER
    @GetMapping("bloodBag/expiring/before/{timestamp}")
    public Iterable<BloodBagBean> getBloodBagsExpiringBeforeDate(@PathVariable long timestamp){
        return bagRepository.findExpirationBeforeDate(timestamp);
    }
    @GetMapping("bloodBag/expiring/after/{timestamp}")
    public Iterable<BloodBagBean> getBloodBagsExpiringAfterDate(@PathVariable long timestamp){
        return bagRepository.findExpirationAfterDate(timestamp);
    }

    @GetMapping("bloodBag/expiring/between/{firstdate}/{seconddate}")
    public Iterable<BloodBagBean> getBloodBagsExpiringBetweenDate(@PathVariable long firstdate,@PathVariable long seconddate){
        //test: localhost:8087/bloodBag/expiring/between/965837960/965837970
        //example swaps the dates.
        if (firstdate>seconddate){
            return bagRepository.findExpirationBetweenDate(seconddate,firstdate);
        }
        return bagRepository.findExpirationBetweenDate(firstdate,seconddate);
    }

    @GetMapping("bloodBag/expiring/between/{firstdate}/{seconddate}/{bloodgroup}")
    public Iterable<BloodBagBean> getBloodBagsExpiringBetweenDateWithGroup(@PathVariable long firstdate, @PathVariable long seconddate, @PathVariable String bloodgroup){
        return bagRepository.findExpirationBetweenDate_bloodGroup(firstdate,seconddate,bloodgroup);
    }



    @GetMapping("bloodBag/report")
    public BloodBagReport getReport(){
        BloodBagReport report = new BloodBagReport(this.getAllBagsCount(),
                this.getCountByState(BloodBag.BloodBagState.Available.toString()),
                this.getCountByState(BloodBag.BloodBagState.Used.toString()),
                this.getCountByState(BloodBag.BloodBagState.Transfered.toString()),
                this.getCountByState(BloodBag.BloodBagState.Dropped.toString()),

                this.getCountByGroup(BloodGroup.ABpos.toString()),
                this.getCountByGroup(BloodGroup.Aneg.toString()),
                this.getCountByGroup(BloodGroup.Bpos.toString()),
                this.getCountByGroup(BloodGroup.Bneg.toString()),
                this.getCountByGroup(BloodGroup.ZEROpos.toString()),
                this.getCountByGroup(BloodGroup.ZEROneg.toString()),
                this.getCountByGroup(BloodGroup.ABpos.toString()),
                this.getCountByGroup(BloodGroup.ABneg.toString()),
                this.getCountExpiringAfterDate(new Date().getTime()-Constants.SEVEN_DAYS_MILLIS),
                this.getCountExpiringAfterDate(new Date().getTime()));
        return report;
    }
    //not working
    private void writeReport(JSONObject report){
        try {
            FileWriter reportFile = new FileWriter("localsettings/BloodBagReports.txt");
            reportFile.write(report.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        BloodBag tempBloodBagObj = new BloodBag(
                new Serial(bagBean.getSerial()),
                BloodGroup.valueOf(bagBean.getGroup()),
                new Date(bagBean.getCreationDate()),
                new Date(bagBean.getExpirationDate()),
                bagBean.getDonator(),
                BloodBag.BloodBagState.valueOf(bagBean.getState()),
                bagBean.getNotes()
        );

        return bagRepository.save(tempBloodBagObj.getBean());
    }



    @DeleteMapping("/bloodBag/use/{serial}")
    public BloodBagBean useBloodBag(@PathVariable("serial") String serial){
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

