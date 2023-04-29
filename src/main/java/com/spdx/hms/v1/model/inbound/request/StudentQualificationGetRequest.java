package com.spdx.hms.v1.model.inbound.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.util.Optional;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentQualificationGetRequest {
    Long stdQualificationId;
    Long stdProfileId;
    String qualification;
    String university;
    String startDate;
    String endDate;
    String yearOfPassing;
    String grade;
    String percentage;
    Boolean active;

    public Optional<Long> getStdQualificationId() {
        return Optional.ofNullable(stdQualificationId);
    }

    public Optional<String> getQualification() {
        return Optional.ofNullable(qualification);
    }

    public Optional<String> getUniversity() {
        return Optional.ofNullable(university);
    }

    public Optional<String> getStartDate() {
        return Optional.ofNullable(startDate);
    }

    public Optional<String> getEndDate() {
        return Optional.ofNullable(endDate);
    }

    public Optional<String> getYearOfPassing() {
        return Optional.ofNullable(yearOfPassing);
    }

    public Optional<String> getGrade() {
        return Optional.ofNullable(grade);
    }

    public Optional<String> getPercentage() {
        return Optional.ofNullable(percentage);
    }

    public Optional<Boolean> getActive() {
        return Optional.ofNullable(active);
    }

    public Optional<Long> getStdProfileId() {
        return Optional.ofNullable(stdProfileId);
    }
}
