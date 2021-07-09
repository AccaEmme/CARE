package it.unisannio.CARE.model.util.Logger;

import it.unisannio.ingsof20_21.group8.Care.Spring.LoggerDAO;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogManager {

    /*
    private long idLog;

    private long currentTimeStamp;
    private String currentUserEmail;
    private String currentUserUsername;
    private String fromClass;           //the calling class
    private String result; **/
    private LoggerDAO loggerDAO;
    public LogManager(long id, String currentEmail, String currentUsername, String fromClass, String result, String action,String explanation){
        this.loggerDAO = new LoggerDAO();
        loggerDAO.setIdLog(id);
        loggerDAO.setCurrentUserEmail(currentEmail);
        loggerDAO.setCurrentUserUsername(currentUsername);
        loggerDAO.setFromClass(fromClass);
        loggerDAO.setResult(result);
        loggerDAO.setAction(action);
        loggerDAO.setExplanation(explanation);
    }

    public LogManager(LoggerDAO loggerDAO){
        this.loggerDAO = loggerDAO;
    }

    public void writeLog() throws IOException {
        File logFile = new File("logs/log.txt");
        FileWriter fw = new FileWriter(logFile,true);
        fw.write(this.toString()+"\n");
        fw.close();
    }

    public String toString(){
        return this.getJSONObject().toJSONString();
    }

    public JSONObject getJSONObject(){
        JSONObject json = new JSONObject();
        json.put("id",this.loggerDAO.getIdLog());
        json.put("currentUserEmail",this.loggerDAO.getCurrentUserEmail());
        json.put("currentUserUsername",this.loggerDAO.getCurrentUserUsername());
        json.put("fromClass",this.loggerDAO.getFromClass());
        json.put("result",this.loggerDAO.getResult());
        json.put("action",this.loggerDAO.getAction());
        json.put("explanation",this.loggerDAO.getExplanation());

        return json;
    }
}
