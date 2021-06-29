package it.unisannio.ingsof20_21.group8.Care.Spring;

import it.unisannio.CARE.Model.BloodBag.BloodBag;
import it.unisannio.CARE.Model.BloodBag.BloodGroup;
import it.unisannio.CARE.Model.BloodBag.Serial;
import it.unisannio.CARE.Model.Report.BloodBagReport;

import it.unisannio.CARE.Model.Util.Constants;
import org.json.simple.JSONArray;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


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

    /**
     * @return all the blood bags in the database
     */
    @GetMapping("/bloodbag/get/all")
    public Iterable<BloodBagBean> getBloodBag(){
        return bagRepository.findAll();
    }


    /**
     * @param state the bloodbag state : {Available,Transfered,Used,Dropped}
     * @return all the blood bags with the given state
     */
    @GetMapping("/bloodbag/get/state/{state}")
    public Iterable<BloodBagBean> filterBloodBagsByState(@PathVariable String state){
        return bagRepository.filterByState(state);
    }

    /**
     * @param group the bloodbag blood group : {Apos, Aneg, Bpos, Bneg, ZEROpos, ZEROneg, ABpos, ABneg}
     * @return all the blood bags with the given group
     */
    @GetMapping("/bloodbag/get/group/{group}")
    public Iterable<BloodBagBean> getBloodBagByGroup(@PathVariable String group){
		return bagRepository.findByGroup(group); /*.orElseThrow();*/
    }


    /**
     * @param serial the bloodbag serial
     * @return all the bloodbags with the given state (probably just one)
     */
    @GetMapping("/bloodbag/get/serial/{serial}")
    public Iterable<BloodBagBean> getBloodBagBySerial(@PathVariable String serial){
    	return bagRepository.findBySerial(serial); /*.orElseThrow();*/
    }

    // ############### CAN DONATE TO ###############
    @GetMapping("/bloodbag/candonateto/group/{group}")
    public Iterator<BloodGroup> canDonateToByGroup(@PathVariable String group){
        System.out.println(BloodGroup.valueOf(group));
        return BloodGroup.canDonateTo(BloodGroup.valueOf(group));
    }


    /**
     * this method returns a list of bloodgroups from the blood bag serial
     * 127.0.0.1:8087/bloodbag/get/candonateto/bloodbag/serial/IT-NA200009-Aneg-20210615-8056
     * @param serial
     * @return
     */
    @GetMapping("/bloodbag/candonateto/bloodbag/serial/{serial}")
    public Iterator<BloodGroup> canDonateToByBagSerial(@PathVariable String serial){
        Iterable<BloodBagBean> beanIterator = this.getBloodBagBySerial(serial);
        for (BloodBagBean bean : beanIterator){
            return BloodGroup.canDonateTo(BloodGroup.valueOf(bean.getGroup()));
        }
        return null;
    }


    // ############### CAN RECIVE FROM ###############
    @GetMapping("/bloodbag/canreceivefrom/group/{group}")
    public Iterator<BloodGroup> canReciveFromByGroup(@PathVariable String group){
        System.out.println(BloodGroup.valueOf(group));
        return BloodGroup.canReceiveFrom(BloodGroup.valueOf(group));
    }

    @GetMapping("/bloodbag/canreceivefrom/bloodbag/serial/{serial}")
    public Iterator<BloodGroup> canReciveFromByBagSerial(@PathVariable String serial){
        Iterable<BloodBagBean> beanIterator = this.getBloodBagBySerial(serial);
        for (BloodBagBean bean : beanIterator){
            return BloodGroup.canReceiveFrom(BloodGroup.valueOf(bean.getGroup()));
        }
        return null;
    }

    //############# get count ###############

    /**
     * @return the count of all bags
     */
    @GetMapping("bloodbag/count/all")
    public long getAllBagsCount(){
        return bagRepository.countAll();
    }

    /**
     * @return the count of all bags having the given blood group
     * @param group the blood group
     */
    @GetMapping("bloodbag/count/group/{group}")
    public long getCountByGroup(@PathVariable String group){
        return bagRepository.countByGroup(group);
    }

    /**
     * @return the count of all bags having the given state
     * @param state the given state
     */
    @GetMapping("bloodbag/count/state/{state}")
    public long getCountByState(@PathVariable String state){
        return bagRepository.countByState(state);
    }


    /**
     * @return the count of all bags expired after the given date
     * @param timestamp the given time
     */
    @GetMapping("bloodbag/count/expiring/after/{timestamp}")
    public long getCountExpiringAfterDate(@PathVariable long timestamp){
        return bagRepository.countExpirationAfterDate(timestamp);
    }

    /**
     * @return the count of all bags used after the given date
     * @param timestamp the given time
     */
    @GetMapping("/bloodvag/count/used/after/{timestamp}")
    public long getCountUsedAfterDate(@PathVariable long timestamp){
        return bagRepository.countUsedAfterDate(timestamp);
    }


    /**
     * @param firstDate the first date
     * @param secondDate the second date
     * @return the count of the bags expiring between the two dates
     */

    @GetMapping("/bloodbag/get/expiring/between/{firstdate}/{seconddate}")
    public long getCountExpiringBetweenDates(@PathVariable long firstDate, @PathVariable long secondDate){
        return bagRepository.countUsedBetweenDates(firstDate,secondDate);
    }


    //########### GET EXPIRING BEFORE/AFTER


    /**
     * @param timestamp the given date
     * @return all blood bags expired before a given date
     */
    @GetMapping("bloodbag/expiring/before/{timestamp}")
    public Iterable<BloodBagBean> getBloodBagsExpiringBeforeDate(@PathVariable long timestamp){
        return bagRepository.findExpirationBeforeDate(timestamp);
    }

    /**
     * @param timestamp the given date
     * @return all blood bags expired after a given date
     */
    @GetMapping("bloodbag/expiring/after/{timestamp}")
    public Iterable<BloodBagBean> getBloodBagsExpiringAfterDate(@PathVariable long timestamp){
        return bagRepository.findExpirationAfterDate(timestamp);
    }


    /**
     * @param firstdate the first date
     * @param seconddate the second date
     * @return all blood bags expired between the two dates
     */
    @GetMapping("bloodbag/expiring/between/{firstdate}/{seconddate}")
    public Iterable<BloodBagBean> getBloodBagsExpiringBetweenDate(@PathVariable long firstdate,@PathVariable long seconddate){
        if (firstdate>seconddate){
            return bagRepository.findExpirationBetweenDate(seconddate,firstdate);
        }
        return bagRepository.findExpirationBetweenDate(firstdate,seconddate);
    }

    /**
     * @param firstdate the first date
     * @param seconddate the second date
     * @param bloodgroup the blood group
     * @return all blood bags expired between two dates of a specific blood group
     */
    @GetMapping("bloodbag/expiring/between/{firstdate}/{seconddate}/{bloodgroup}")
    public Iterable<BloodBagBean> getBloodBagsExpiringBetweenDateWithGroup(@PathVariable long firstdate, @PathVariable long seconddate, @PathVariable String bloodgroup){
        return bagRepository.findExpirationBetweenDate_bloodGroup(firstdate,seconddate,bloodgroup);
    }


    /**
     * @return report the blood bag report having all stats
     */
    @GetMapping("bloodbag/report")
    public BloodBagReport getReport(){
        return new BloodBagReport(this.getAllBagsCount(),
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
                this.getCountExpiringBetweenDates(new Date().getTime()-Constants.SEVEN_DAYS_MILLIS, new Date().getTime()),
                this.getCountUsedAfterDate(new Date().getTime()-Constants.SEVEN_DAYS_MILLIS));
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
      {
          "serial":"IT-NA205101-Aneg-20210615-0037",
          "group":"Bneg",
          "creationDate":965837967,
          "expirationDate":965837968,
          "donator":"CRSDLCER86BH0919",
          "state":"Available",
          "notes":"test note"
      }*/

    @PostMapping("/bloodbag/add")
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

        BloodBagBean beanToSave = tempBloodBagObj.getBean();
        //se la bag viene aggiunta come usata, aggiorno il momento di utilizzo all'ora corrente
        if (beanToSave.getUsedTimeStamp() == 0)
            beanToSave.setUsedTimeStamp(new Date().getTime());
        return bagRepository.save(beanToSave);
    }


    /**
     * this method is called to USE a blood bag, it changes the blood bag state to "Used"
     * 1) find blood bag
     * 2) delete the blood bag
     * 3) modify the blood bag state
     * 4) save the new bloood bag
     * @param serial the blood bag serial
     * @return blood bag
     */
    @DeleteMapping("/bloodbag/use/{serial}")
    public BloodBagBean useBloodBag(@PathVariable("serial") String serial){
        Iterable<BloodBagBean> beans = this.getBloodBagBySerial(serial);
        BloodBagBean beanToChange = null;

        System.err.println(beans);
        for (BloodBagBean bean : beans){
            System.err.println(bean.toString());
            beanToChange = bean;

            bagRepository.delete(beanToChange);
            beanToChange.setState(BloodBag.BloodBagState.Used.toString());
            beanToChange.setUsedTimeStamp(new Date().getTime());

            bagRepository.save(beanToChange);
        }
        return beanToChange;
    }


    /**
     * this method exports a json containing all the blood bags
     */
    @GetMapping("/export/bloodbag/json")
    public void exportToJson(){
        JSONArray sqlDatabase = new JSONArray();
        Iterable<BloodBagBean> beans = this.getBloodBag();

        for (BloodBagBean bean : beans){
            sqlDatabase.add(this.getJsonObjectFromBean(bean));
        }


        try {
            FileWriter fileWriter = new FileWriter("exports/jsonExport"+Constants.dateFormatString.format(new Date())+".json");
            fileWriter.write(sqlDatabase.toJSONString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @param bean the bean used to generate the json object
     * @return jsonObject the object generated from the bean
     */
    private JSONObject getJsonObjectFromBean(BloodBagBean bean){
        JSONObject object = new JSONObject();
            object.put("serial",bean.getSerial());
            object.put("group",bean.getGroup());
            object.put("donator",bean.getDonator());
            object.put("creationDate",bean.getCreationDate());
            object.put("expirationDate",bean.getExpirationDate());
            object.put("state",bean.getState());
            object.put("notes",bean.getNotes());
            object.put("usedTimeStamp",bean.getUsedTimeStamp());
        return object;
    }

}
