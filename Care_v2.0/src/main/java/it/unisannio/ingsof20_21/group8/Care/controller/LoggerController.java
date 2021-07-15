
package it.unisannio.ingsof20_21.group8.Care.controller;

import it.unisannio.CARE.model.util.Constants;
import it.unisannio.ingsof20_21.group8.Care.Spring.LoggerDAO;
import it.unisannio.ingsof20_21.group8.Care.Spring.LoggerRepository;

import org.springframework.web.bind.annotation.*;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;
import java.util.Date;

@CrossOrigin("*")
@RestController
public class LoggerController {

	private final LoggerRepository loggerRepository;

	public LoggerController(LoggerRepository loggerRepository) {
		this.loggerRepository = loggerRepository;
	}

	// ########## POST METHODS ###########

	@GetMapping("logger/get/all")
	public Iterable<LoggerDAO> getAllLogs() {
		return loggerRepository.findAll();
	}

	@GetMapping("logger/get/user/username/{username}")
	public Iterable<LoggerDAO> getLogsByUsername(@PathVariable String username) {
		return loggerRepository.filterLogsByUsername(username);
	}

	@GetMapping("logger/get/id/{id}")
	public LoggerDAO getLogById(@PathVariable long id) {
		return loggerRepository.findLogById(id);
	}

	@GetMapping("logger/get/lastday")
	public Iterable<LoggerDAO> getLast24HLogs() {
		return loggerRepository.filterLogsByDate(new Date().getTime() - Constants.ONE_DAY_MILLIS, new Date().getTime());
	}

	@GetMapping("logger/get/lastweek")
	public Iterable<LoggerDAO> getLastWeekLogs() {
		return loggerRepository.filterLogsByDate(new Date().getTime() - Constants.SEVEN_DAYS_MILLIS,
				new Date().getTime());
	}
}
