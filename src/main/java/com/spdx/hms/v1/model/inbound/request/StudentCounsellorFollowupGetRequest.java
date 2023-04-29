package com.spdx.hms.v1.model.inbound.request;

import java.util.Optional;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentCounsellorFollowupGetRequest {
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
