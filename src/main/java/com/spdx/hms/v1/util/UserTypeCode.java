package com.spdx.hms.v1.util;

public enum UserTypeCode {

    HMSUSTYP0001("HMS_USTYP_0001","Student"),
    HMSUSTYP0002("HMS_USTYP_0002", "College"),
    HMSUSTYP0003("HMS_USTYP_0003","Admin"),
    HMSUSTYP0004("HMS_USTYP_0004","Intern");
   

    private final String code;
    private final String message;

    UserTypeCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
