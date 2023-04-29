package com.spdx.hms.v1.model.inbound.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Optional;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentSkillsResponse {
    Long stdSkillId;
    Long stdProfileId;
    String category;
    String specialization;
    String level;
    Integer totalExperience;
    Boolean active;
    String createdBy;
    String modifiedBy;
    Long createdTimestamp;
    Long modifiedTimestamp;

    public Optional<Long> getStdSkillId() {
        return Optional.ofNullable(stdSkillId);
    }

    public Optional<Long> getStdProfileId() {
        return Optional.ofNullable(stdProfileId);
    }

    public Optional<String> getCategory() {
        return Optional.ofNullable(category);
    }

    public Optional<String> getSpecialization() {
        return Optional.ofNullable(specialization);
    }

    public Optional<String> getLevel() {
        return Optional.ofNullable(level);
    }

    public Optional<Integer> getTotalExperience() {
        return Optional.ofNullable(totalExperience);
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
