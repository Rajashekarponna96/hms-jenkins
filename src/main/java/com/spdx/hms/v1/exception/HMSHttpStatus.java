package com.spdx.hms.v1.exception;

import org.springframework.http.HttpStatus;

import java.util.Arrays;

public enum HMSHttpStatus {
    HMS0001("HMS0001", HttpStatus.INTERNAL_SERVER_ERROR),
    HMS0004("HMS0004", HttpStatus.BAD_REQUEST),
    HMS0006("HMS0006", HttpStatus.NOT_FOUND),
    HMS0008("HMS0008", HttpStatus.BAD_REQUEST),
    HMS0013("HMS0013", HttpStatus.BAD_REQUEST),
    HMS0014("HMS0014", HttpStatus.BAD_REQUEST),
    HMS0015("HMS0015", HttpStatus.NOT_FOUND),
    HMS0016("HMS0016", HttpStatus.NOT_FOUND),
    HMS0017("HMS0017", HttpStatus.NOT_FOUND),
    HMS0019("HMS0019", HttpStatus.BAD_REQUEST),
    HMS0020("HMS0020", HttpStatus.NOT_FOUND),
    HMS0021("HMS0021", HttpStatus.BAD_REQUEST),
    UNKNOWN("HMS0000", HttpStatus.INTERNAL_SERVER_ERROR);


    private String code;
    private HttpStatus httpStatus;

    HMSHttpStatus(String code, HttpStatus httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public static HttpStatus getOrDefault(String code) {
        return Arrays.stream(HMSHttpStatus.values())
                .filter(hmsHttpStatus -> hmsHttpStatus.getCode().equals(code))
                .map(HMSHttpStatus::getHttpStatus)
                .findFirst()
                .orElse(UNKNOWN.getHttpStatus());
    }

    public String getCode() {
        return this.code;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }
}
