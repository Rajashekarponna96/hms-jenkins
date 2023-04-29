package com.spdx.hms.v1.service.dto.response;

import java.util.Optional;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentCounsellorFollowupResponseDto {
	Long cnlgReqId;
	Long stdCounsellorRequestId;
	String remarks;
	String createdTime;
	Boolean active;
	String createdBy;
	String modifiedBy;
	Long createdTimestamp;
	Long modifiedTimestamp;
	
	public Optional<Long> getCnlgReqId() {
        return Optional.ofNullable(cnlgReqId);
    } 
	public Optional<Long> getStdCounsellorRequestId() {
        return Optional.ofNullable(stdCounsellorRequestId);
    } 
	public Optional<String> getRemarks() {
	        return Optional.ofNullable(remarks);
	}
	public Optional<String> getCreatedTime() {
	        return Optional.ofNullable(createdTime);
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
