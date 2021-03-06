package it.unisannio.ingsof20_21.group8.Care.Spring;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import it.unisannio.CARE.model.exceptions.*;
import it.unisannio.CARE.spring.bean.ErrorBean;

@ControllerAdvice
public class GlobalExceptionHandler {

	// handling specific exception
	@ExceptionHandler(RegisterException.class)

	public ResponseEntity<?> registerExceptionHandling(RegisterException exception) {

		ErrorBean errorBean = new ErrorBean(new Date().toString(), "500", exception.getClass().getName(),
				exception.getMessage(), "/register");

		return new ResponseEntity<>(errorBean, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(BloodBagCloneNotSupportedException.class)

	public ResponseEntity<?> BloodBagCloneNotSupportedHandling(BloodBagCloneNotSupportedException exception) {

		ErrorBean errorBean = new ErrorBean(new Date().toString(), "500", exception.getClass().getName(),
				exception.getMessage(), exception.getPath());

		return new ResponseEntity<>(errorBean, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(BloodBagNotFoundException.class)

	public ResponseEntity<?> BloodBagAlredyExistsHandling(BloodBagNotFoundException exception) {

		ErrorBean errorBean = new ErrorBean(new Date().toString(), "500", exception.getClass().getName(),
				exception.getMessage(), exception.getPath());

		return new ResponseEntity<>(errorBean, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(BloodBagStateException.class)

	public ResponseEntity<?> BloodBagStateHandler(BloodBagStateException exception) {

		ErrorBean errorBean = new ErrorBean(new Date().toString(), "500", exception.getClass().getName(),
				exception.getMessage(), exception.getPath());

		return new ResponseEntity<>(errorBean, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(IllegalSerialException.class)

	public ResponseEntity<?> IllegalSerialExceptionHandler(IllegalSerialException exception) {

		ErrorBean errorBean = new ErrorBean(new Date().toString(), "500", exception.getClass().getName(),
				exception.getMessage(), exception.getPath());

		return new ResponseEntity<>(errorBean, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(IllegalDateException.class)

	public ResponseEntity<?> IllegalDateExceptionHandler(IllegalDateException exception) {

		ErrorBean errorBean = new ErrorBean(new Date().toString(), "500", exception.getClass().getName(),
				exception.getMessage(), exception.getPath());

		return new ResponseEntity<>(errorBean, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(IllegalFiscalCodeException.class)

	public ResponseEntity<?> IllegalFiscalCodeExceptionHandler(IllegalFiscalCodeException exception) {

		ErrorBean errorBean = new ErrorBean(new Date().toString(), "500", exception.getClass().getName(),
				exception.getMessage(), exception.getPath());

		return new ResponseEntity<>(errorBean, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(RequestCloneNotSupportedException.class)

	public ResponseEntity<?> RequestCloneNotSupportedExceptionHandler(RequestCloneNotSupportedException exception) {

		ErrorBean errorBean = new ErrorBean(new Date().toString(), "500", exception.getClass().getName(),
				exception.getMessage(), exception.getPath());

		return new ResponseEntity<>(errorBean, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(RequestNotFoundException.class)

	public ResponseEntity<?> RequestNotFoundExceptionHandler(RequestNotFoundException exception) {

		ErrorBean errorBean = new ErrorBean(new Date().toString(), "500", exception.getClass().getName(),
				exception.getMessage(), exception.getPath());

		return new ResponseEntity<>(errorBean, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(UserException.class)
	public ResponseEntity<?> UserExceptionHandler(UserException exception) {
		ErrorBean errorBean = new ErrorBean(new Date().toString(), "500", exception.getClass().getName(),
				exception.getMessage(), exception.getPath());
		return new ResponseEntity<>(errorBean, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
