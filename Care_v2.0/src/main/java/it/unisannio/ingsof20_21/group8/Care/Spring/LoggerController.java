/**
 * @author giuliano ranauro
 * Date: 01/07/2021 12:13
 * Ide: Intellij
 * JDK: 1.8
 * @version 1.0
 */
package it.unisannio.ingsof20_21.group8.Care.Spring;


import org.springframework.web.bind.annotation.*;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;

@CrossOrigin("*")
@RestController
public class LoggerController implements ContainerResponseFilter {

    private final LoggerRepository loggerRepository;

    public LoggerController(LoggerRepository loggerRepository) {
        this.loggerRepository = loggerRepository;
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        // TODO Auto-generated method stub
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        responseContext.getHeaders().add("Access-Control-Allow-Headers",
                "CSRF-Token, X-Requested-By, Authorization, Content-Type");
        responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        responseContext.getHeaders().add("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }

    // ########## POST METHODS ###########
    @PostMapping("logger/add")
    public void addLog(@RequestBody LoggerBean loggerBean){
        loggerRepository.save(loggerBean);
    }

    @GetMapping("logger/get/all")
    public Iterable<LoggerBean> getAllLogs(){
        return loggerRepository.findAll();
    }

}
