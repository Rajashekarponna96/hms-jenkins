package com.spdx.hms.v1.service.dto.request;

import java.util.Optional;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentAdmissionUpdateRequestDto {
	Long stdAdmissionId;

	String admissionNumber;

	Long appId;

	Long studentId;

	String studentMobileNumber;

	String studentEmailId;

	String studentName;

	Long collegeId;

	Long courseId;


	String branchName;

	String admittedDateTime;

	Boolean active;

	String status="InProgress";

	String admissionStatus;

	String createdBy;

	String modifiedBy;

	Long createdTimestamp;

	Long modifiedTimestamp;
	
	Long contactId;
	
	String collegeCourse;
	
	String appDate;
	

	public Optional<Long> getStdAdmissionId() {
		return Optional.ofNullable(stdAdmissionId);
	}

	public Optional<String> getAdmissionNumber() {
		return Optional.ofNullable(admissionNumber);
	}

	public Optional<Long> getAppId() {
		return Optional.ofNullable(appId);
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

	public Optional<Long> getCourseId() {
		return Optional.ofNullable(courseId);
	}

	public Optional<String> getBranchName() {
		return Optional.ofNullable(branchName);
	}

	public Optional<String> getAdmittedDateTime() {
		return Optional.ofNullable(admittedDateTime);
	}

	public Optional<Boolean> getActive() {
		return Optional.ofNullable(active);
	}

	public Optional<String> getStatus() {
		return Optional.ofNullable(status);
	}

	public Optional<String> getAdmissionStatus() {
		return Optional.ofNullable(admissionStatus);
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
	
	public Optional<Long> getContactId() {
		return Optional.ofNullable(contactId);
	}

	public Optional<String> getCollegeCourse() {
		return Optional.ofNullable(collegeCourse);
	}

	public Optional<String> getAppDate() {
		return Optional.ofNullable(appDate);
	}


}
