package com.spdx.hms.v1.model.inbound.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Optional;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShortlistedCollegesSaveRequest {
    Long collegeId;
    String collegeCode;
    Long collegeCourseId;
    Long studentId;
    
    String collegeCourseBranchName;
    String studentMobileNumber;
    String studentEmailId;
    String studentName;
    Boolean active;


    public Optional<Long> getCollegeId() {
        return Optional.ofNullable(collegeId);
    }

    public Optional<String> getCollegeCode() {
        return Optional.ofNullable(collegeCode);
    }

    public Optional<Long> getCollegeCourseId() {
        return Optional.ofNullable(collegeCourseId);
    }

    public Optional<Long> getStudentId() {
        return Optional.ofNullable(studentId);
    }
    
    
    public Optional<String> getCollegeCourseBranchName() {
        return Optional.ofNullable(collegeCourseBranchName);
    }

    public Optional<String> getStudentMobileNumber() {
        return Optional.ofNullable(studentMobileNumber);
    }

    public Optional<String> getStudentEmailId() {
        return Optional.ofNullable(studentEmailId);
    }

    public Optional<String> getStudentName() {
        return Optional.ofNullable(studentName);
    }

    public Optional<Boolean> getActive() {
        return Optional.ofNullable(active);
    }

}
