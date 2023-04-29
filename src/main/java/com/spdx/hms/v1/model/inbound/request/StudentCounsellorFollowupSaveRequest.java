package com.spdx.hms.v1.model.inbound.request;

import java.util.Optional;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentCounsellorFollowupSaveRequest {
	Long stdCounsellorRequestId;
	String remarks;
	String createdTime;
	Boolean active;
	
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

	

}
