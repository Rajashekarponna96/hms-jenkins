package com.spdx.hms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.spdx.hms.dto.Errors;

import lombok.extern.slf4j.Slf4j;

//@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Errors> exceptionHandler(Exception ex) {
		log.error("ExceptionControllerAdvice,globalException"+ex.getMessage());
		Errors error = new Errors();
		error.setErrorType(HttpStatus.INTERNAL_SERVER_ERROR.value()+"");
		error.setErrorMessage("Please contact your administrator");
		return new ResponseEntity<Errors>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
