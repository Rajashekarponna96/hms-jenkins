package com.spdx.hms.v1.service.dto.request;

import java.util.List;
import java.util.Optional;

import com.spdx.hms.dto.AdmissionJourney;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentAdmissionJourneySaveRequestDto {
	
	Long stdProfileId;
	List<AdmissionJourney> AdmissionJourney;
	Boolean active;
	String createdBy;
    String modifiedBy;
    Long createdTimestamp;
    Long modifiedTimestamp;
    
	
	
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

	    public Optional<Long> getCreatedTimestamp() {
	        return Optional.ofNullable(createdTimestamp);
	    }

	    public Optional<Long> getModifiedTimestamp() {
	        return Optional.ofNullable(modifiedTimestamp);
	    }
}
