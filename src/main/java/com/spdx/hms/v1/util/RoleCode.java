package com.spdx.hms.v1.util;

public enum RoleCode {

    HMSRL0001("HMS_RL_0001","Student"),
    HMSRL0002("HMS_RL_0002", "College"),
    HMSRL0003("HMS_RL_0003","Admin"),
    HMSRL0004("HMS_RL_0004","Intern");
   

    private final String code;
    private final String message;

    RoleCode(String code, String message) {
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
