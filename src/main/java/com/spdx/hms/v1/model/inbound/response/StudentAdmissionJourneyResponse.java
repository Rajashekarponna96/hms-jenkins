package com.spdx.hms.v1.model.inbound.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spdx.hms.dto.AdmissionJourney;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentAdmissionJourneyResponse {
	
	 Long stdProfileId;
	 List<AdmissionJourney> AdmissionJourney;
	 Boolean active;
	 String createdBy;
	 String modifiedBy;
	 @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	 LocalDateTime createdTimestamp;
	 @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	 LocalDateTime modifiedTimestamp;
	    
	
	public Optional<Long> getStdProfileId() {
		return Optional.ofNullable(stdProfileId);
	}
	 public Optional<Boolean> getActive() {
	        return Optional.ofNullable(active);
	    }
	    public Optional<List<AdmissionJourney>> getAdmissionJourney() {
		    return Optional.ofNullable(AdmissionJourney);
		  }
	    public Optional<String> getCreatedBy() {
	        return Optional.ofNullable(createdBy);
	    }

	    public Optional<String> getModifiedBy() {
	        return Optional.ofNullable(modifiedBy);
	    }

	    public Optional<LocalDateTime> getCreatedTimestamp() {
	        return Optional.ofNullable(createdTimestamp);
	    }

	    public Optional<LocalDateTime> getModifiedTimestamp() {
	        return Optional.ofNullable(modifiedTimestamp);
	    }
}
