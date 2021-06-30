package it.unisannio.CARE.model.report;


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

    private long usedThisWeek;
    private long expiredThisWeek;

    /**
	 **************************************************************************
	 * Costruttore della classe BloodBagReport
	 * @param total
	 * @param available
	 * @param used
	 * @param transfered
	 * @param dropped
	 * @param apos Tipo di sangue accettato
	 * @param aneg Tipo di sangue accettato
	 * @param bpos Tipo di sangue accettato
	 * @param bneg Tipo di sangue accettato
	 * @param ZEROpos Tipo di sangue accettato
	 * @param ZEROneg Tipo di sangue accettato
	 * @param ABpos Tipo di sangue accettato
	 * @param ABneg Tipo di sangue accettato
	 * @param usedThisWeek
	 * @param expiredThisWeek
	 **************************************************************************
	 */
    public BloodBagReport(long total, long available, long used, long transfered, long dropped, long apos, long aneg, long bpos, long bneg, long ZEROpos, long ZEROneg, long ABpos, long ABneg, long usedThisWeek, long expiredThisWeek) {
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
	 **************************************************************************
	 * Metodo GET per il ritorno del totale
	 * @return ritorna il totale 
	 **************************************************************************
	 */
    public long getTotal() {
        return total;
    }

    /**
	 **************************************************************************
	 * Metodo SET per inserire il totale
	 * @param total 
	 **************************************************************************
	 */
    public void setTotal(long total) {
        this.total = total;
    }

    /**
	 **************************************************************************
	 * Metodo GET per controllare se lo stato è disponibile
	 * @return ritorna lo stato available
	 **************************************************************************
	 */
    public long getAvailable() {
        return available;
    }

    /**
	 **************************************************************************
	 * Metodo SET per inserire available
	 * @param available inserisci il nuovo stato di available
	 **************************************************************************
	 */
    public void setAvailable(long available) {
        this.available = available;
    }

    /**
	 **************************************************************************
	 * Metodo GET per ottenere lo stato used
	 * @return ritorna lo stato di used
	 **************************************************************************
	 */
    public long getUsed() {
        return used;
    }

    /**
	 **************************************************************************
	 * Metodo SET per inserire used
	 * @param used inserisci il nuovo valore di used
	 **************************************************************************
	 */
    public void setUsed(long used) {
        this.used = used;
    }

    /**
	 **************************************************************************
	 * Metodo GET per ottenere il valore di trasferito 
	 * @return ritorna il valore di transfered
	 **************************************************************************
	 */
    public long getTransfered() {
        return transfered;
    }

    /**
	 **************************************************************************
	 * Metodo SET per inserire il valore di trasferito 
	 * @param transfered inserire il valore di transfered
	 **************************************************************************
	 */
    public void setTransfered(long transfered) {
        this.transfered = transfered;
    }

    /**
   	 **************************************************************************
   	 * Metodo GET per ottenere il valore di Dropped 
   	 * @return ritorna il valore di dropped
   	 **************************************************************************
   	 */
    public long getDropped() {
        return dropped;
    }
    
    /**
   	 **************************************************************************
   	 * Metodo SET per inserire il nuovo valore di Dropped 
   	 * @param dropped inserire il valore di dropped
   	 **************************************************************************
   	 */
    public void setDropped(long dropped) {
        this.dropped = dropped;
    }

    /**
   	 **************************************************************************
   	 * Metodo GET per ottenere il valore di apos
   	 * @return ritorna la quantià di apos
   	 **************************************************************************
   	 */
    public long getApos() {
        return Apos;
    }

    /**
   	 **************************************************************************
   	 * Metodo SET per inserire la quantità di apos
   	 * @param apos Quantità di apos
   	 **************************************************************************
   	 */
    public void setApos(long apos) {
        Apos = apos;
    }

    /**
   	 **************************************************************************
   	 * Metodo GET per ottenere il valore di aneg
   	 * @return ritorna la quantià di aneg
   	 **************************************************************************
   	 */
    public long getAneg() {
        return Aneg;
    }

    /**
   	 **************************************************************************
   	 * Metodo SET per inserire la quantità di aneg
   	 * @param aneg Quantità di aneg
   	 **************************************************************************
   	 */
    public void setAneg(long aneg) {
        Aneg = aneg;
    }

    /**
   	 **************************************************************************
   	 * Metodo GET per ottenere il valore di bpos
   	 * @return ritorna la quantià di bpos
   	 **************************************************************************
   	 */
    public long getBpos() {
        return Bpos;
    }

    /**
   	 **************************************************************************
   	 * Metodo SET per inserire la quantità di bpos
   	 * @param bpos Quantità di bpos
   	 **************************************************************************
   	 */
    public void setBpos(long bpos) {
        Bpos = bpos;
    }

    /**
   	 **************************************************************************
   	 * Metodo GET per ottenere il valore di bneg
   	 * @return ritorna la quantià di bneg
   	 **************************************************************************
   	 */
    public long getBneg() {
        return Bneg;
    }

    /**
   	 **************************************************************************
   	 * Metodo SET per inserire la quantità di bneg
   	 * @param bneg Quantità di bneg
   	 **************************************************************************
   	 */
    public void setBneg(long bneg) {
        Bneg = bneg;
    }

    /**
   	 **************************************************************************
   	 * Metodo GET per ottenere il valore di ZEROpos
   	 * @return ritorna la quantià di ZEROpos
   	 **************************************************************************
   	 */
    public long getZEROpos() {
        return ZEROpos;
    }

    /**
   	 **************************************************************************
   	 * Metodo SET per inserire la quantità di ZEROpos
   	 * @param ZEROpos Quantità di Zeropos
   	 **************************************************************************
   	 */
    public void setZEROpos(long ZEROpos) {
        this.ZEROpos = ZEROpos;
    }

    /**
   	 **************************************************************************
   	 * Metodo GET per ottenere il valore di ZEROneg
   	 * @return ritorna la quantià di ZEROneg
   	 **************************************************************************
   	 */
    public long getZEROneg() {
        return ZEROneg;
    }

    /**
   	 **************************************************************************
   	 * Metodo SET per inserire la quantità di ZEROneg
   	 * @param ZEROneg Quantità di Zeroneg
   	 **************************************************************************
   	 */
    public void setZEROneg(long ZEROneg) {
        this.ZEROneg = ZEROneg;
    }

    /**
   	 **************************************************************************
   	 * Metodo GET per ottenere il valore di ABpos
   	 * @return ritorna la quantià di ABpos
   	 **************************************************************************
   	 */
    public long getABpos() {
        return ABpos;
    }

    /**
   	 **************************************************************************
   	 * Metodo SET per inserire la quantità di ABpos
   	 * @param ABpos Quantità di Abpos
   	 **************************************************************************
   	 */
    public void setABpos(long ABpos) {
        this.ABpos = ABpos;
    }

    /**
   	 **************************************************************************
   	 * Metodo GET per ottenere il valore di ABneg
   	 * @return ritorna la quantià di ABneg
   	 **************************************************************************
   	 */
    public long getABneg() {
        return ABneg;
    }

    /**
   	 **************************************************************************
   	 * Metodo SET per inserire la quantità di Abneg
   	 * @param ABneg Quantità di Abneg
   	 **************************************************************************
   	 */
    public void setABneg(long ABneg) {
        this.ABneg = ABneg;
    }

    /**
   	 **************************************************************************
   	 * Metodo GET per ottenere il valore di Timestamp
   	 * @return ritorna la quantià di timestamp
   	 **************************************************************************
   	 */
    public long getTimestamp() {
        return timestamp;
    }

    /**
   	 **************************************************************************
   	 * Metodo SET per inserire la quantità di timestamp
   	 * @param timestamp Quantità di timestamp
   	 **************************************************************************
   	 */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    /**
   	 **************************************************************************
   	 * Metodo GET per ottenere il valore di UsedThisWeek
   	 * @return ritorna la quantià di usedThisWeek
   	 **************************************************************************
   	 */
    public long getUsedThisWeek() {
        return usedThisWeek;
    }

    /**
   	 **************************************************************************
   	 * Metodo SET per inserire la quantità di usedThisWeek
   	 * @param usedThisWeek Quantità di usedThisWeek
   	 **************************************************************************
   	 */
    public void setUsedThisWeek(long usedThisWeek) {
        this.usedThisWeek = usedThisWeek;
    }

    /**
   	 **************************************************************************
   	 * Metodo GET per ottenere il valore di expiredThisWeek
   	 * @return ritorna la quantià di expiredThisWeek
   	 **************************************************************************
   	 */
    public long getExpiredThisWeek() {
        return expiredThisWeek;
    }

    /**
   	 **************************************************************************
   	 * Metodo SET per inserire la quantità di expiredThisWeek
   	 * @param expiredThisWeek Quantità di expiredThisWeek
   	 **************************************************************************
   	 */
    public void setExpiredThisWeek(long expiredThisWeek) {
        this.expiredThisWeek = expiredThisWeek;
    }

    /**
   	 **************************************************************************
   	 * Metodo TOSTRING per il ritorno in formato di stringa di tutte le informazioni delle variabili della classe bloodBagReport
   	 **************************************************************************
   	 */
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
