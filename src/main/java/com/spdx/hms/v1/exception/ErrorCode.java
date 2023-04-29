package com.spdx.hms.v1.exception;

public enum ErrorCode {

    HMS0001("HMS0001","Error occurred"),
    HMS0002("HMS0002", "Field Validation Error"),
    HMS0003("HMS0003","Please select at least one criteria"),
    HMS0004("HMS0004","User Already exist with emailId/mobile number"),
    HMS0005("HMS0005","Mandatory fields missed"),
    HMS0006("HMS0006","User not found"),
    HMS0007("HMS0007","Mandatory field student profile id missing"),
    HMS0008("HMS0008","Student qualification already exist"),
    HMS0009("HMS0009","Student qualification max limit exceeded"),
    HMS0010("HMS0010","Mandatory field course name missing"),
    HMS0011("HMS0011","Course name already exist"),
    HMS0012("HMS0012","Unable to acquire JDBC Connection"),
    HMS0013("HMS0013","Invalid criteria"),
    HMS0014("HMS0014","CollegeCode already exists"),
    HMS0015("HMS0015","Student does not found"),
    HMS0016("HMS0016","College does not found"),
    HMS0017("HMS0017","CollegeCourse already exists"),
    HMS0018("HMS0018","CollegeCourse does not exists"),
    HMS0019("HMS0019","CollegeCredit already exists"),
    HMS0020("HMS0020","CollegeCredit does not found"),
    HMS0021("HMS0021","Student profile id not found"),
    HMS0022("HMS0022","User token validation failed"),	
	HMS0023("HMS0023","CounsellorName already exists"),
    HMS0024("HMS0024","Student does not exist"),
	HMS0025("HMS0025","University does not found"),
	HMS0026("HMS0026","Mandatory field university code missing"),
    HMS0027("HMS0027","University code already exist"),
	HMS0028("HMS0028","Student ID already exist"),
	HMS0029("HMS0029","User is in InActive state"),
	HMS0031("HMS0031","Student experience already exist");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
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
