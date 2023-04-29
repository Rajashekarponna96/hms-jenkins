package com.spdx.hms.v1.model.inbound.request;

import java.util.Optional;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentAdmissionSavetRequest {

	String admissionNumber;

	Long appId;

	Long studentId;

	@Size(min = 10, max = 10)
	@Pattern(regexp = "^(^$|\\d{10})$")
	String studentMobileNumber;

	@Pattern(regexp = "^[-a-z0-9~!$%^&*_=+}{\\'?]+(\\.[-a-z0-9~!$%^&*_=+}{\\'?]+)*@([a-z0-9_][-a-z0-9_]*(\\"
			+ ".[-a-z0-9_]+)*\\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z"
			+ "][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})?$")
	String studentEmailId;

	String studentName;

	Long collegeId;

	Long courseId;

	

	String branchName;

	String admittedDateTime;

	Boolean active;

	String status;

	String admissionStatus;

	private String createdBy;

	private String modifiedBy;

	Long contactId;
	
	String collegeCourse;
	 
	String appDate;

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
