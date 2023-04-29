package com.spdx.hms.v1.service.dto.request;

import java.util.List;
import java.util.Optional;

import com.spdx.hms.dto.Branches;
import com.spdx.hms.v1.model.inbound.request.StudentCounsellorGetRequest;

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
public class StudentCounsellorGetRequestDto {
	Long stdCounsellorRequestId;
	Long studentId;
	String studentMobileNumber;
	String studentEmailId;
	String studentName;
	Long counsellorId;
	String counsellorName;
	String counsellorEmailId;
	String counsellorMobileNumber;
	Boolean active = Boolean.TRUE;;
	String appointmentCreatedBy;
	String createdTime;
	String counsellorAssignedTime;
	String createdBy;
	String modifiedBy;
	Long createdTimestamp;
	Long modifiedTimestamp;
	
	public Optional<Long> getStdCounsellorRequestId() {
        return Optional.ofNullable(stdCounsellorRequestId);
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
	 public Optional<Long> getCounsellorId() {
	        return Optional.ofNullable(counsellorId);
	    }
	 
	 public Optional<String> getCounsellorName() {
	        return Optional.ofNullable(counsellorName);
	    }
	 
	 public Optional<String> getCounsellorEmailId() {
	        return Optional.ofNullable(counsellorEmailId);
	    }
	 public Optional<String> getCounsellorMobileNumber() {
	        return Optional.ofNullable(counsellorMobileNumber);
	    }
	 
	 public Optional<Boolean> getActive() {
	        return Optional.ofNullable(active);
	    }
	 public Optional<String> getAppointmentCreatedBy() {
	        return Optional.ofNullable(appointmentCreatedBy);
	    }
	 
	 public Optional<String> getCreatedTime() {
	        return Optional.ofNullable(createdTime);
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
