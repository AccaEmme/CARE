package it.unisannio.ingsof20_21.group8.Care.Spring;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import it.unisannio.CARE.model.Exceptions.RegisterException;

@ControllerAdvice
public class  GlobalExceptionHandler {

    // handling specific exception
    @ExceptionHandler(RegisterException.class)
    
    public ResponseEntity<?> autenticazioneErrorHandling(RegisterException exception, WebRequest request){
        
    	ErrorBean errorBean = new ErrorBean( exception.getMessage());
    	
        return new ResponseEntity<>(errorBean, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
