package com.spdx.hms.v1.service.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentQualificationGetRequestDto {
    Long stdQualificationId;
    Long stdProfileId;
    String qualification;
    String university;
    String startDate;
    String endDate;
    String yearOfPassing;
    Boolean active = Boolean.TRUE;
    String grade;
    String percentage;

    public Optional<Long> getStdQualificationId() {
        return Optional.ofNullable(stdQualificationId);
    }

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
