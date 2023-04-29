package com.spdx.hms.v1.model.inbound.response;

import java.time.LocalDateTime;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CollegeStudentFollowUpsResponse {
	Long followpId;
	Long contactId;
	Long studentId;
	String studentMobileNumber;
	String studentEmailId;
	String studentName;
	Long collegeId;
	Boolean active;
	String remarks;
	String dateTime;
	String status;
	String createdBy;
    String modifiedBy;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    LocalDateTime createdTimestamp;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    LocalDateTime modifiedTimestamp;
    
	public Optional<Long> getFollowpId() {
        return Optional.ofNullable(followpId);
    }
	
	public Optional<Long> getContactId() {
        return Optional.ofNullable(contactId);
    }
	
	public Optional<Long> getStudentId() {
        return Optional.ofNullable(studentId);
    }
    
	public Optional<String> getStudentMobileNumber() {
        return Optional.ofNullable(studentMobileNumber);
    }
	
	public Optional<String> getStudentEmailId() {
        return Optional.ofNullable(studentEmailId);
    }
	
	public Optional<String> getStudentName() {
        return Optional.ofNullable(studentName);
    }
	
	public Optional<Long> getCollegeId() {
        return Optional.ofNullable(collegeId);
    }
	
	public Optional<Boolean> getActive() {
        return Optional.ofNullable(active);
    }
	
	public Optional<String> getRemarks() {
        return Optional.ofNullable(remarks);
    }
	
	public Optional<String> getDateTime() {
        return Optional.ofNullable(dateTime);
    }
	
	public Optional<String> getStatus() {
        return Optional.ofNullable(status);
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
