package com.spdx.hms.v1.service.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Optional;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentSkillsUpdateRequestDto {
    Long stdSkillId;
    Long stdProfileId;
    String category;
    String specialization;
    boolean active=Boolean.TRUE;
    String level;
        Integer totalExperience;

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

	public Optional<Boolean> getActive() {
		return Optional.ofNullable(active);
	}


    public Optional<String> getLevel() {
        return Optional.ofNullable(level);
    }

    public Optional<Integer> getTotalExperience() {
        return Optional.ofNullable(totalExperience);
    }
}
