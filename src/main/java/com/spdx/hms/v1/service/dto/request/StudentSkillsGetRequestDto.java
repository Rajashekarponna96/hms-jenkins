package com.spdx.hms.v1.service.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Optional;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentSkillsGetRequestDto {
    Long stdSkillId;
    Long stdProfileId;
    String category;
    String specialization;
    String level;
    boolean active;
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
