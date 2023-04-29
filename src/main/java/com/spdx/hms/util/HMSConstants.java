package com.spdx.hms.util;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class HMSConstants {

    public static final String PAGE = "page";
    public static final String SIZE = "size";
    public static final String DIRECTION = "direction";
    public static final String FIELDS = "fields";
    public static final List<String> STUDENT_CRITERIA_FIELDS = Arrays.asList("id", "name", "firstName", "lastName",
            "emailId", "mobileNumber", "alternateMobileNumber", "identityType", "identityProof", "addressOne",
            "addressTwo", "zipCode", "landMark", "district", "city", "state", "active", "dob", "gender",
            "maritalStatus", "physicallyChallenged");
    public static final Collection<String> COLLEGE_CRITERIA_FIELDS = Arrays.asList("collegeName", "collegeCode",
            "establishmentYear", "address1", "address2", "zipCode", "landMark", "district", "city",
            "state", "collegeLocation", "contactEmail", "contactNumber", "contactPerson", "website", "active","instituteType");
    public static final Collection<String> COLLEGE_CREDIT_CRITERIA_FIELDS = Arrays.asList("collegeId", "collegeCode",
            "creditPoints");
    
    public static final Collection<String> APPLIED_COLLEGE_CRITERIA_FIELDS = Arrays.asList("appId", "collegeCode",
            "collegeId", "collegeCourseId", "studentId", "collegeCourseBranchName", "studentMobileNumber", "studentEmailId", "studentName",
             "active", "status", "qualification", "comment");
    public static final Collection<String> SHORTLISTED_COLLEGE_CRITERIA_FIELDS = Arrays.asList("shrtId", "collegeCode",
            "collegeId", "collegeCourseId", "studentId", "collegeCourseBranchName", "studentMobileNumber", "studentEmailId", "studentName",
             "active");
    public static final Collection<String> Student_Admission_CRITERIA_FIELDS = Arrays.asList("stdAdmissionId", "admissionNumber",
            "appId", "studentId", "studentMobileNumber", "studentEmailId", "studentName", "collegeId", "courseId", "branchName",
             "active", "status", "admittedDateTime", "admissionStatus");
}
