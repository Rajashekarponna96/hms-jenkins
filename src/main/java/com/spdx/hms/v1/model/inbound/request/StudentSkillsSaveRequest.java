package com.spdx.hms.v1.model.inbound.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Optional;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentSkillsSaveRequest {
    Long stdProfileId;
    String category;
    String specialization;
    String level;
    Integer totalExperience;
    boolean active=Boolean.TRUE;
    String createdBy;
    String modifiedBy;

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
}
