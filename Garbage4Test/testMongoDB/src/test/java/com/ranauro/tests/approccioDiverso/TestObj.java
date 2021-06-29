package com.ranauro.tests.approccioDiverso;

public class TestObj {
    public TestObj(){

    }

    public String getMemberID(){
        return this.memberID;
    }
    public int getTimer(){
        return this.timer;
    }
    public int getXp(){
        return this.xp;
    }
    public void setMemberID(String memberID){
        this.memberID = memberID;
    }
    public void setTimer(int timer){
        this.timer=timer;
    }
    public void setXp(int xp){
        this.xp=xp;
    }

    private String memberID = "";
    private int timer = 0;
    private int xp = 0;
}
