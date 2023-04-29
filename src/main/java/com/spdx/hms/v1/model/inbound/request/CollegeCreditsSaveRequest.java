package com.spdx.hms.v1.model.inbound.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.util.Optional;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CollegeCreditsSaveRequest {
    Long collegeId;
    String collegeCode;
    Long creditPoints;
    Timestamp startPeriod;
    Timestamp endPeriod;
    Boolean active;

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

}
