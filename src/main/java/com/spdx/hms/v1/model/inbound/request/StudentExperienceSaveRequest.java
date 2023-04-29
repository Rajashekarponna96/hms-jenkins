package com.spdx.hms.v1.model.inbound.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.util.Optional;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentExperienceSaveRequest {
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
}
