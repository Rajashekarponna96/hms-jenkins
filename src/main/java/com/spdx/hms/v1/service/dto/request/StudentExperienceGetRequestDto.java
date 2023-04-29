package com.spdx.hms.v1.service.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentExperienceGetRequestDto {
    Long stdExprId;
    Long stdProfileId;
    String title;
    String department;
    String employmentType;
    String companyName;
    String startDate;
    String endDate;
    Boolean active = Boolean.TRUE;

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
}
