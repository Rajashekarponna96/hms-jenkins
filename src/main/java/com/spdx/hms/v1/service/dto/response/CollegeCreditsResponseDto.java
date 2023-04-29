package com.spdx.hms.v1.service.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.util.Optional;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CollegeCreditsResponseDto {
    Long creditId;
    Long collegeId;
    String collegeCode;
    Long creditPoints;
    Timestamp startPeriod;
    Timestamp endPeriod;
    Boolean active;
    String createdBy;
    String modifiedBy;
    Long createdTimestamp;
    Long modifiedTimestamp;

    public Optional<Long> getCreditId() {
        return Optional.ofNullable(creditId);
    }

    public Optional<Long> getCollegeId() {
        return Optional.ofNullable(collegeId);
    }

    public Optional<String> getCollegeCode() {
        return Optional.ofNullable(collegeCode);
    }

    public Optional<Long> getCreditPoints() {
        return Optional.ofNullable(creditPoints);
    }

    public Optional<Timestamp> getStartPeriod() {
        return Optional.ofNullable(startPeriod);
    }

    public Optional<Timestamp> getEndPeriod() {
        return Optional.ofNullable(endPeriod);
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
