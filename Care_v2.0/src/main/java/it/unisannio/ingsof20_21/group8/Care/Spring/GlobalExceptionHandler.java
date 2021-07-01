package it.unisannio.ingsof20_21.group8.Care.Spring;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import it.unisannio.CARE.model.Exceptions.RegisterException;
import it.unisannio.CARE.spring.bean.ErrorBean;

@ControllerAdvice
public class  GlobalExceptionHandler {

    // handling specific exception
    @ExceptionHandler(RegisterException.class)
    
    public ResponseEntity<?> registerExceptionHandling(RegisterException exception){
      
    	ErrorBean errorBean = new ErrorBean(new Date().toString(), "-1", exception.getClass().getName(), exception.getMessage(), "/register");
    	
        return new ResponseEntity<>(errorBean, HttpStatus.INTERNAL_SERVER_ERROR);
        
    }

}
