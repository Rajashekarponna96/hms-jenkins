package com.spdx.hms.v1.exception;

import com.spdx.hms.dto.Errors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;


@ControllerAdvice(value = "com.spdx.hms.v1")
@Slf4j
public class HMSControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    protected ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex) {
        return buildResponseEntity(
                new HMSError(ex.getStatus(), ex.getReason(), ex.getMessage(), ex.getLocalizedMessage()));
    }

    @ExceptionHandler(HMSException.class)
    protected ResponseEntity<Object> handleHMSException(HMSException ex) {
        Optional<HMSError> apiError = Optional.ofNullable(ex.getError());
        return apiError.map(this::buildResponseEntity)
                .orElseGet(() -> buildResponseEntity(
                    new HMSError(HMSHttpStatus.getOrDefault(ex.getCode().getCode()), ex.getCode().getCode(),
                        ex.getCode().getMessage(), ex.getDebugMessage())));
    }

    private ResponseEntity<Object> buildResponseEntity(HMSError apiError) {
        log.error("Returning response entity with error [{}]", apiError);
        return new ResponseEntity<>(apiError, HttpStatus.valueOf(apiError.getStatus()));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    protected ResponseEntity<Object> handleWebExchangeBindException(WebExchangeBindException ex) {
        HMSError error = new HMSError(ex.getStatus(), ErrorCode.HMS0002.getCode(), ErrorCode.HMS0002.getMessage());
        error.addValidationErrors(ex.getBindingResult().getFieldErrors());
        error.addValidationError(ex.getBindingResult().getGlobalErrors());
        return buildResponseEntity(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Errors> exceptionHandler(Exception ex) {
        log.error("ExceptionControllerAdvice,globalException{}", ex.getMessage());
        Errors error = new Errors();
        error.setErrorType(HttpStatus.INTERNAL_SERVER_ERROR.value() + "");
        error.setErrorMessage("Please contact your administrator");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    @ResponseStatus
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        HMSError error = new HMSError(HttpStatus.BAD_REQUEST, ErrorCode.HMS0002.getCode(),
                                      ErrorCode.HMS0002.getMessage());
        error.addValidationErrors(ex.getBindingResult().getFieldErrors());
        error.addValidationError(ex.getBindingResult().getGlobalErrors());
        return buildResponseEntity(error);
    }
}
