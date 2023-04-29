package com.spdx.hms.v1.model.inbound.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentExperienceResponse {
    Long stdExprId;
    Long stdProfileId;
    String title;
    String department;
    String employmentType;
    String companyName;
    String startDate;
    String endDate;
    Boolean active;
    String createdBy;
    String modifiedBy;
    Long createdTimestamp;
    Long modifiedTimestamp;
    StudentResponse student;

    public Optional<Long> getStdExprId() {
        return Optional.ofNullable(stdExprId);
    }

    public Optional<Long> getStdProfileId() {
        return Optional.ofNullable(stdProfileId);
    }

    public Optional<String> getTitle() {
        return Optional.ofNullable(title);
    }

    public Optional<String> getDepartment() {
        return Optional.ofNullable(department);
    }

    public Optional<String> getEmploymentType() {
        return Optional.ofNullable(employmentType);
    }

    public Optional<String> getCompanyName() {
        return Optional.ofNullable(companyName);
    }

    public Optional<String> getStartDate() {
        return Optional.ofNullable(startDate);
    }

    public Optional<String> getEndDate() {
        return Optional.ofNullable(endDate);
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

    public Optional<StudentResponse> getStudent() {
        return Optional.ofNullable(student);
    }
}
