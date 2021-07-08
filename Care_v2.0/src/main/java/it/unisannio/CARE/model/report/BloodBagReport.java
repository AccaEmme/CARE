package it.unisannio.CARE.model.report;


import org.json.simple.JSONObject;

import java.util.Date;

/*
 * Class that contains all methods for blood bag reports
 */
public class BloodBagReport {
    private long total;
    private long available;
    private long used;
    private long transfered;
    private long dropped;


    //Apos, Aneg, Bpos, Bneg, ZEROpos, ZEROneg, ABpos, ABneg;
    private long Apos;
    private long Aneg;
    private long Bpos;
    private long Bneg;
    private long ZEROpos;
    private long ZEROneg;
    private long ABpos;
    private long ABneg;

    private long timestamp;

    private long usedThisWeek;
    private long expiredThisWeek;

    
    /**
      * Constructor method of the BloodBagReport class
      * @param total total of the bags of the report
      * @param available how many bags are available
      * @param used how many bags have been used
      * @param transfered how many bags have been transferred
      * @param dropped how many bags were thrown
      * @param apos how many pockets of A + there are
      * @param aneg how many pockets of A- are there
      * @param bpos how many pockets of B + there are
      * @param bneg how many pockets of B- are there
      * @param ZEROpos how many pockets of 0+ there are
      * @param ZEROneg how many pockets of 0- are there
      * @param ABpos how many pockets of AB + there are
      * @param ABneg how many pockets of AB- are there
      * @param usedThisWeek how many bags have been used this weekend
      * @param expiredThisWeek how many bags are expiring this weekend
     */
    public BloodBagReport(long total, long available, long used, long transfered, long dropped, 
    		long apos, long aneg, long bpos, long bneg, long ZEROpos, 
    		long ZEROneg, long ABpos, long ABneg, long usedThisWeek, long expiredThisWeek) {	
        this.total = total;
        this.available = available;
        this.used = used;
        this.transfered = transfered;
        this.dropped = dropped;
        Apos = apos;
        Aneg = aneg;
        Bpos = bpos;
        Bneg = bneg;
        this.ZEROpos = ZEROpos;
        this.ZEROneg = ZEROneg;
        this.ABpos = ABpos;
        this.ABneg = ABneg;
        this.timestamp = new Date().getTime();
        this.usedThisWeek = usedThisWeek;
        this.expiredThisWeek = expiredThisWeek;
    }

    /**
      * GET method to return the total of the bags
      * @return returns the total of bags in long format
     */
    public long getTotal() {
        return total;
    }

    /**
     * Metodo SET per inserire il totale delle sacche 
     * @param total totale delle sacche in formato long
     */
    public void setTotal(long total) {
        this.total = total;
    }

    /**
      * GET method to return available bags
      * @return returns the available bags in long format
     */
    public long getAvailable() {
        return available;
    }

    /**
      * SET method to insert available blood bags
      * @param available the total number of bags available
     */
    public void setAvailable(long available) {
        this.available = available;
    }

    /**
      * GET method to return used bags
      * @return returns the used bags in long format
     */
    public long getUsed() {
        return used;
    }

    /**
      * SET method to insert used bags
      * @param used the total number of bags used
     */
    public void setUsed(long used) {
        this.used = used;
    }

    /**
      * GET method to return the quantity of bags transferred
      * @return returns the transferred bags in long format
     */
    public long getTransfered() {
        return transfered;
    }

    /**
      * SET method to insert the transferred bags
      * @param transfered the number in long format of the bags transferred
     */
    public void setTransfered(long transfered) {
        this.transfered = transfered;
    }

    /**
      * GET method to return the quantity of expired bags
      * @return returns expired bags in long format
     */
    public long getDropped() {
        return dropped;
    }

    /**
      * SET method to insert expired bags
      * @param transfered the number in long format of the expired bags
     */
    public void setDropped(long dropped) {
        this.dropped = dropped;
    }

    /**
      * GET method to return the quantity of Apos bags
      * @return returns the Apos bags in long format
     */
    public long getApos() {
        return Apos;
    }

    /**
      * SET method to insert the apos bags
      * @param apos the number in long format of the apos bags
     */
    public void setApos(long apos) {
        Apos = apos;
    }

    /**
      * GET method to return the quantity of Aneg bags
      * @return returns in long format the Aneg bags
     */
    public long getAneg() {
        return Aneg;
    }

    /**
      * SET method to insert the aneg bags
      * @param aneg the number in long format of the aneg bags
     */
    public void setAneg(long aneg) {
        Aneg = aneg;
    }

    /**
      * GET method to return the quantity of Bpos bags
      * @return returns the Bpos bags in long format
     */
    public long getBpos() {
        return Bpos;
    }

    /**
      * SET method to insert bpos bags
      * @param bpos the number in long format of the bpos bags
     */
    public void setBpos(long bpos) {
        Bpos = bpos;
    }

    /**
      * GET method to return the quantity of Bneg bags
      * @return returns in long format the Bneg bags
     */
    public long getBneg() {
        return Bneg;
    }

    /**
      * SET method to insert the bneg bags
      * @param bneg the number in long format of the bneg bags
     */
    public void setBneg(long bneg) {
        Bneg = bneg;
    }

    /**
      * GET method to return the quantity of ZEROpos bags
      * @return returns the ZEROpos bags in long format
     */
    public long getZEROpos() {
        return ZEROpos;
    }

    /**
      * SET method to insert ZEROpos bags
      * @param ZEROpos the number in long format of the ZEROpos bags
     */
    public void setZEROpos(long ZEROpos) {
        this.ZEROpos = ZEROpos;
    }

    /**
      * GET method to return the quantity of ZEROneg bags
      * @return returns the ZEROneg bags in long format
     */
    public long getZEROneg() {
        return ZEROneg;
    }

    /**
      * SET method to insert ZEROneg bags
      * @param ZEROneg the number in long format of the ZEROneg bags
     */
    public void setZEROneg(long ZEROneg) {
        this.ZEROneg = ZEROneg;
    }

    /**
      * GET method to return the quantity of ABpos bags
      * @return returns the ABpos bags in long format
     */
    public long getABpos() {
        return ABpos;
    }

    /**
      * SET method to insert ABpos bags
      * @param ABpos the number in long format of the ABpos bags
     */
    public void setABpos(long ABpos) {
        this.ABpos = ABpos;
    }

    /**
      * GET method to return the quantity of ABneg bags
      * @return returns the ABneg bags in long format
     */
    public long getABneg() {
        return ABneg;
    }

    /**
      * SET method to insert ABneg bags
      * @param ABneg the number in long format of the ABneg bags
     */
    public void setABneg(long ABneg) {
        this.ABneg = ABneg;
    }

    /**
      * GET method to get the printing time
      * @return returns the long-time format of the print
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
      * SET method to enter the printing time
      * @param timestamp printing time in long format
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    /**
      * GET method to get used bags this week
      * @return returns in long format the bags used this week
     */
    public long getUsedThisWeek() {
        return usedThisWeek;
    }

    /**
      * SET method to insert the bags used this week
      * @param usedThisWeek bags used this week
     */
    public void setUsedThisWeek(long usedThisWeek) {
        this.usedThisWeek = usedThisWeek;
    }

    /**
      * GET method to get bags that have expired this week
      * @return returns the bags expired this week in long format
     */
    public long getExpiredThisWeek() {
        return expiredThisWeek;
    }

    /**
      * SET method to insert the bags that have expired this week
      * @param expiredThisWeek bags expired this week
     */
    public void setExpiredThisWeek(long expiredThisWeek) {
        this.expiredThisWeek = expiredThisWeek;
    }

    /**
     * TOSTRING method for returning all information of the BloodBagReport class
     */
    @Override
    public String toString() {
        return "BloodBagReport{" +
                "total=" + total +
                ", available=" + available +
                ", used=" + used +
                ", transfered=" + transfered +
                ", dropped=" + dropped +
                ", Apos=" + Apos +
                ", Aneg=" + Aneg +
                ", Bpos=" + Bpos +
                ", Bneg=" + Bneg +
                ", ZEROpos=" + ZEROpos +
                ", ZEROneg=" + ZEROneg +
                ", ABpos=" + ABpos +
                ", ABneg=" + ABneg +
                ", timestamp=" + timestamp +
                ", usedThisWeek=" + usedThisWeek +
                ", expiredThisWeek=" + expiredThisWeek +
                '}';
    }

    /*
    public org.json.simple.JSONObject getJsonObject(){
        JSONObject report = new JSONObject();
        report.put("total",total);
        report.put("available",available);
        report.put("used",used);
        report.put("transfered",transfered);
        report.put("dropped",dropped);
        report.put("Apos",Apos);
        report.put("Aneg",Aneg);
        report.put("Bpos",Bpos);
        report.put("Bneg",Bneg);
        report.put("ZEROpos",ZEROpos);
        report.put("ZEROneg",ZEROneg);
        report.put("ABpos",ABpos);
        report.put("ABneg",ABneg);
        report.put("timestamp",timestamp);

        return report;
    }*/
}
