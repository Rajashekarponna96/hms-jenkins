package com.spdx.hms.v1.exception;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class HMSError implements Serializable {
    private static final long serialVersionUID = 1L;
    private int status;
    private String error;
    private String errorCode;
    private String message;
    private String debugMessage;
    private List<SubError> subErrors; //NOSONAR SubError should be transient or Serializable

    public HMSError(HttpStatus httpStatus, String code) {
        this.status = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
        this.errorCode = code;
    }

    public HMSError(HttpStatus httpStatus, String code, String message) {
        this.status = httpStatus.value();
        this.errorCode = code;
        this.message = message;
    }

    public HMSError(HttpStatus httpStatus, String code, String message, String debugMessage) {
        this.status = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
        this.errorCode = code;
        this.message = message;
        this.debugMessage = debugMessage;
    }

    private void addSubError(SubError subError) {
        if (subErrors == null) {
            subErrors = new ArrayList<>();
        }
        subErrors.add(subError);
    }

    private void addValidationError(String object, String field, Object rejectedValue, String message) {
        addSubError(new ApiValidationError(object, field, rejectedValue, message));
    }

    private void addValidationError(String object, String message) {
        addSubError(new ApiValidationError(object, message));
    }

    /* Response Errors */
    public void addResponseError(String object, String field, Object rejectedValue, String message) {
        addSubError(new ApiValidationError(object, field, rejectedValue, message));
    }

    public void addResponseError(String object, String field, Object rejectedValue, String message, String code) {
        addSubError(new ApiValidationError(object, field, rejectedValue, message, code));
    }

    public void addResponseError(String object, String message) {
        addSubError(new ApiValidationError(object, message));
    }

    /*
     * Field Validation Errors
     */
    private void addValidationError(FieldError fieldError) {
        this.addValidationError(
                fieldError.getObjectName(),
                fieldError.getField(),
                fieldError.getRejectedValue(),
                fieldError.getDefaultMessage());
    }

    void addValidationErrors(List<FieldError> fieldErrors) {
        fieldErrors.forEach(this::addValidationError);
    }

    /*
     * Object Errors
     */
    private void addValidationError(ObjectError objectError) {
        this.addValidationError(
                objectError.getObjectName(),
                objectError.getDefaultMessage());
    }

    void addValidationError(List<ObjectError> globalErrors) {
        globalErrors.forEach(this::addValidationError);
    }

    // abstract class for sub errors
    interface SubError {}

    @Data
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = false)
    static class ApiValidationError implements SubError {
        private String object;
        private String field;
        private Object rejectedValue;
        private String message;
        private String code;

        ApiValidationError(String object, String message) {
            this.object = object;
            this.message = message;
        }

        ApiValidationError(String object, String field, Object rejectedValue, String message) {
            this.object = object;
            this.field = field;
            this.rejectedValue = rejectedValue;
            this.message = message;
        }
    }
}
