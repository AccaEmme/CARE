/**
 * @author giuliano ranauro
 * Date: 01/07/2021 12:13
 * Ide: Intellij
 * JDK: 1.8
 * @version 1.0
 */
package it.unisannio.ingsof20_21.group8.Care.Spring;


import it.unisannio.CARE.model.util.Constants;

import org.springframework.web.bind.annotation.*;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;
import java.util.Date;

@CrossOrigin("*")
@RestController
public class LoggerController /*implements ContainerResponseFilter */{

    private final LoggerRepository loggerRepository;

    public LoggerController(LoggerRepository loggerRepository) {
        this.loggerRepository = loggerRepository;
    }
/*
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        // TODO Auto-generated method stub
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        responseContext.getHeaders().add("Access-Control-Allow-Headers",
                "CSRF-Token, X-Requested-By, Authorization, Content-Type");
        responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        responseContext.getHeaders().add("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }*/

    
    // ########## POST METHODS ###########
/*	Logs can't be added by others, only by the system.
    @PostMapping("logger/add")
    public void addLog(@RequestBody LoggerDAO loggerBean){
        loggerRepository.save(loggerBean);
    }
*/

    @GetMapping("logger/get/all")
    public Iterable<LoggerDAO> getAllLogs(){
        return loggerRepository.findAll();
    }

    /*@GetMapping("logger/get/user/usermail/{mail}")
    public Iterable<LoggerDAO> getLogsByUserMail(@PathVariable String mail){
        return loggerRepository.filterLogsByEmail(mail);
    }*/

    @GetMapping("logger/get/user/username/{username}")
    public Iterable<LoggerDAO> getLogsByUsername(@PathVariable String username){
        return loggerRepository.filterLogsByUsername(username);
    }

    @GetMapping("logger/get/id/{id}")
    public LoggerDAO getLogById(@PathVariable long id){
        return loggerRepository.findLogById(id);
    }

    @GetMapping("logger/get/lastday")
    public Iterable<LoggerDAO> getLast24HLogs(){
        return loggerRepository.filterLogsByDate(new Date().getTime()- Constants.ONE_DAY_MILLIS, new Date().getTime());
    }
    @GetMapping("logger/get/lastweek")
    public Iterable<LoggerDAO> getLastWeekLogs(){
        return loggerRepository.filterLogsByDate(new Date().getTime()- Constants.SEVEN_DAYS_MILLIS, new Date().getTime());
    }
}
