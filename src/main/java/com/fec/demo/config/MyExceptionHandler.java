package com.fec.demo.config;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.fec.demo.entity.ErroMessage;

@ControllerAdvice
public class MyExceptionHandler {
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErroMessage> handleAllExceptions(Exception ex, WebRequest request) {

		ErroMessage errorObj = new ErroMessage(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorObj, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
