package com.spdx.hms.v1.model.inbound.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

import java.sql.Timestamp;
import java.util.Optional;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentQualificationSaveRequest {
    Long stdProfileId;
    @NotBlank
    String qualification;
    @NotBlank
    String university;
    @NotBlank
    String startDate;
    @NotBlank
    String endDate;
    @NotBlank
    String yearOfPassing;
    String grade;
    @NotBlank
    String percentage;
    Boolean active;
    
    public Optional<Long> getStdProfileId() {
        return Optional.ofNullable(stdProfileId);
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
}
