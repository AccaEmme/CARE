package it.unisannio.CARE.Model.Util;


import org.json.simple.JSONObject;

import java.util.Date;

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

    public BloodBagReport(long total, long available, long used, long transfered, long dropped, long apos, long aneg, long bpos, long bneg, long ZEROpos, long ZEROneg, long ABpos, long ABneg) {
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
    }

    public BloodBagReport(){}

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getAvailable() {
        return available;
    }

    public void setAvailable(long available) {
        this.available = available;
    }

    public long getUsed() {
        return used;
    }

    public void setUsed(long used) {
        this.used = used;
    }

    public long getTransfered() {
        return transfered;
    }

    public void setTransfered(long transfered) {
        this.transfered = transfered;
    }

    public long getDropped() {
        return dropped;
    }

    public void setDropped(long dropped) {
        this.dropped = dropped;
    }

    public long getApos() {
        return Apos;
    }

    public void setApos(int apos) {
        Apos = apos;
    }

    public long getAneg() {
        return Aneg;
    }

    public void setAneg(int aneg) {
        Aneg = aneg;
    }

    public long getBpos() {
        return Bpos;
    }

    public void setBpos(int bpos) {
        Bpos = bpos;
    }

    public long getBneg() {
        return Bneg;
    }

    public void setBneg(int bneg) {
        Bneg = bneg;
    }

    public long getZEROpos() {
        return ZEROpos;
    }

    public void setZEROpos(long ZEROpos) {
        this.ZEROpos = ZEROpos;
    }

    public long getZEROneg() {
        return ZEROneg;
    }

    public void setZEROneg(long ZEROneg) {
        this.ZEROneg = ZEROneg;
    }

    public long getABpos() {
        return ABpos;
    }

    public void setABpos(long ABpos) {
        this.ABpos = ABpos;
    }

    public long getABneg() {
        return ABneg;
    }

    public void setABneg(long ABneg) {
        this.ABneg = ABneg;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

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
