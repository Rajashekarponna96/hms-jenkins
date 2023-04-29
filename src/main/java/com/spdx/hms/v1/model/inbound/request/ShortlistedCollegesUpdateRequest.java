package com.spdx.hms.v1.model.inbound.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Optional;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShortlistedCollegesUpdateRequest {
    Long shrtId;
    Long collegeId;
    String collegeCode;
    Long collegeCourseId;
    Long studentId;
    String collegeCourseBranchName;
    String studentMobileNumber;
    String studentEmailId;
    String studentName;
    Boolean active;
    String createdBy;
    String modifiedBy;
    Long createdTimestamp;
    Long modifiedTimestamp;

    public Optional<Long> getShrtId() {
        return Optional.ofNullable(shrtId);
    }

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

    public Optional<String> getCreatedBy() {
        return Optional.ofNullable(createdBy);
    }

    public Optional<String> getModifiedBy() {
        return Optional.ofNullable(modifiedBy);
    }

    public Optional<Long> getCreatedTimestamp() {
        return Optional.ofNullable(createdTimestamp);
    }

    public Optional<Long> getModifiedTimestamp() {
        return Optional.ofNullable(modifiedTimestamp);
    }
}
