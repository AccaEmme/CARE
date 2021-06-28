package it.unisannio.CARE.Model.Util;

import it.unisannio.CARE.Model.BloodBag.BloodBag;
import it.unisannio.CARE.Model.BloodBag.BloodGroup;
import it.unisannio.ingsof20_21.group8.Care.Spring.BloodBagBean;

import java.util.List;

public class BloodBagReport {
    private int total;
    private int available;
    private int used;
    private int transfered;
    private int dropped;


    //Apos, Aneg, Bpos, Bneg, ZEROpos, ZEROneg, ABpos, ABneg;
    private int Apos;
    private int Aneg;
    private int Bpos;
    private int Bneg;
    private int ZEROpos;
    private int ZEROneg;
    private int ABpos;
    private int ABneg;


    public BloodBagReport(int total, int available, int used, int transfered, int dropped, int apos, int aneg, int bpos, int bneg, int ZEROpos, int ZEROneg, int ABpos, int ABneg) {
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
    }
    public BloodBagReport(){}

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public int getTransfered() {
        return transfered;
    }

    public void setTransfered(int transfered) {
        this.transfered = transfered;
    }

    public int getDropped() {
        return dropped;
    }

    public void setDropped(int dropped) {
        this.dropped = dropped;
    }

    public int getApos() {
        return Apos;
    }

    public void setApos(int apos) {
        Apos = apos;
    }

    public int getAneg() {
        return Aneg;
    }

    public void setAneg(int aneg) {
        Aneg = aneg;
    }

    public int getBpos() {
        return Bpos;
    }

    public void setBpos(int bpos) {
        Bpos = bpos;
    }

    public int getBneg() {
        return Bneg;
    }

    public void setBneg(int bneg) {
        Bneg = bneg;
    }

    public int getZEROpos() {
        return ZEROpos;
    }

    public void setZEROpos(int ZEROpos) {
        this.ZEROpos = ZEROpos;
    }

    public int getZEROneg() {
        return ZEROneg;
    }

    public void setZEROneg(int ZEROneg) {
        this.ZEROneg = ZEROneg;
    }

    public int getABpos() {
        return ABpos;
    }

    public void setABpos(int ABpos) {
        this.ABpos = ABpos;
    }

    public int getABneg() {
        return ABneg;
    }

    public void setABneg(int ABneg) {
        this.ABneg = ABneg;
    }
}
