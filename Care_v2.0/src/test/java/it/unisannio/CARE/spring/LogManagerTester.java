package it.unisannio.CARE.spring;

import it.unisannio.CARE.model.exceptions.UserException;
import it.unisannio.CARE.model.util.Logger.LogManager;
import it.unisannio.ingsof20_21.group8.Care.Spring.LoggerDAO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;

public class LogManagerTester {
    @Test
    public void testLogWrite() throws IOException {
        LoggerDAO loggerDAO = new LoggerDAO();
        loggerDAO.setCurrentTimeStamp(new Date().getTime());
        loggerDAO.setCurrentUserUsername("peppe");
        //loggerDAO.setCurrentUserEmail("peppe99@gmail.com");
        loggerDAO.setFromClass(String.valueOf(this.getClass()));
        loggerDAO.setIdLog(123213123);
        loggerDAO.setResult("ok");
        loggerDAO.setAction("the user logged in.");

        LogManager logManager = new LogManager(loggerDAO);

        logManager.writeLog();
    }

    @Test
    public void testGetUserInfoFromToken() throws ParseException, UserException {
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJmb2xsZW45OSIsInVzZXJSb2xlIjoiUk9MRV9BRE1JTklTVFJBVE9SIiwiZXhwIjoxNjI1ODY2NTc5LCJpYXQiOjE2MjU4NjE1Nzl9.D3BxrrsMVb2h8dxzTiWi_LzYaqbPUzhFvTS9k_ZimUKM3PAWoyLs8h3YSB8mlkmRXwbCVszNuQb1G5ToSgTkuQ";
        System.out.println(this.getUsernameFromToken(token));
    }


    //returns: {"sub":"follen99","userRole":"ROLE_ADMINISTRATOR","exp":1625866579,"iat":1625861579}
    private String getUsernameFromToken(String token) throws org.json.simple.parser.ParseException, UserException {
        String[] chunks = token.split("\\.");

        Base64.Decoder decoder = Base64.getDecoder();
        String payload = new String(decoder.decode(chunks[1]));

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(payload);

        return json.toJSONString();
    }


    @Test
    public void testSaveToDB(){
        LoggerDAO loggerDAO = new LoggerDAO();
        loggerDAO.setCurrentTimeStamp(new Date().getTime());
        loggerDAO.setCurrentUserUsername("peppe");
        //loggerDAO.setCurrentUserEmail("peppe99@gmail.com");
        loggerDAO.setFromClass(String.valueOf(this.getClass()));
        loggerDAO.setIdLog(123213123);
        loggerDAO.setResult("ok");
        loggerDAO.setAction("the user logged in.");

        LogManager logManager = new LogManager(loggerDAO);

        logManager.saveToDB();
    }
}
