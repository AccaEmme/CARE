/**
 * @author giuliano ranauro
 * Date: 12/05/2021 15:09
 * Ide: Intellij
 * JDK: 1.8
 * @version 1.0
 */
package com.ranauro.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateGenerator {
    String date;
    String format;
    public DateGenerator(String date){
        this.date = date;
        this.format = Costants.DATE_FORMAT;
    }
    public Date getDate() throws ParseException {
        Date date = null;

        SimpleDateFormat sdformat = new SimpleDateFormat(this.format);
        date = sdformat.parse(this.date);   //yyyy-mm-dd_hh-mm-ss

        return date;
    }
    public Date getDate(String format) throws ParseException {
        SimpleDateFormat sdformat = new SimpleDateFormat(format);
        return sdformat.parse(this.date);
    }


    public static Date generateDate(String date) throws ParseException {
        SimpleDateFormat sdformat = new SimpleDateFormat(Costants.DATE_FORMAT);
        return sdformat.parse(date);
    }
    public static Date generateDate(String date, String format) throws ParseException {
        SimpleDateFormat sdformat = new SimpleDateFormat(format);
        return sdformat.parse(date);
    }


    public String getFormat(){
        return this.format;
    }
    public void setFormat(String format){
        this.format = format;
    }
}
