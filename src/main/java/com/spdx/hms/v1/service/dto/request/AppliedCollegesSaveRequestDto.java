package com.spdx.hms.v1.service.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Optional;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppliedCollegesSaveRequestDto {
    Long collegeId;
    String collegeCode;
    Long collegeCourseId;
    Long studentId;
    String collegeCourseBranchName;
    String studentMobileNumber;
    String studentEmailId;
    String studentName;
    Boolean active=Boolean.TRUE;
    String comment="College reviewing application";
    String status = "InProgress";
    String qualification;
    Long contactId;
    String collegeCourse;
    String appDate;


    public Optional<Long> getCollegeId() {
        return Optional.ofNullable(collegeId);
    }

    public Optional<String> getCollegeCode() {
        return Optional.ofNullable(collegeCode);
    }

    public Optional<Long> getCollegeCourseId() {
        return Optional.ofNullable(collegeCourseId);
    }

    public Optional<Long> getStudentId() {
        return Optional.ofNullable(studentId);
    }
    
   
    public Optional<String> getCollegeCourseBranchName() {
        return Optional.ofNullable(collegeCourseBranchName);
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

    public Optional<Boolean> getActive() {
        return Optional.ofNullable(active);
    }
    
    public Optional<String> getComment() {
        return Optional.ofNullable(comment);
    }
    
    public Optional<String> getStatus() {
        return Optional.ofNullable(status);
    }
    
    public Optional<String> getQualification() {
        return Optional.ofNullable(qualification);
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
