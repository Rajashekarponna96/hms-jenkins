package com.spdx.hms.v1.service.dto.request;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import com.spdx.hms.dto.CollegeCourseBranches;
import com.spdx.hms.dto.FeeStructure;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CollegeCourseRequestDto {
	
	Long collegeCourseId;
	Long collegeUniversityId;
	Long collegeId;
	Long courseId;
	String courseName;
	Long allottedSeats;
	Long fees;
	List<FeeStructure> feeStructure;
	Boolean active;
	Long createdTimestamp;
	Long modifiedTimestamp;
	String createdBy;
	String modifiedBy;
	String about;
	List<CollegeCourseBranches> branches;

	public Optional<Long> getCollegeCourseId() {
		return Optional.ofNullable(collegeCourseId);
	}

	public Optional<Long> getCollegeUniversityId() {
		return Optional.ofNullable(collegeUniversityId);
	}

	public Optional<Long> getCollegeId() {
		return Optional.ofNullable(collegeId);
	}

	public Optional<Long> getCourseId() {
		return Optional.ofNullable(courseId);
	}

	public Optional<String> getCourseName() {
		return Optional.ofNullable(courseName);
	}

	public Optional<Long> getAllottedSeats() {
		return Optional.ofNullable(allottedSeats);
	}

	public Optional<Long> getFees() {
		return Optional.ofNullable(fees);
	}

	public Optional<List<FeeStructure>> getFeeStructure() {
		return Optional.ofNullable(feeStructure);
	}

	public Optional<Boolean> getActive() {
		return Optional.ofNullable(active);
	}

	public Optional<Long> getCreatedTimestamp() {
		return Optional.ofNullable(createdTimestamp);
	}

	public Optional<Long> getModifiedTimestamp() {
		return Optional.ofNullable(modifiedTimestamp);
	}

	public Optional<String> getCreatedBy() {
		return Optional.ofNullable(createdBy);
	}

	public Optional<String> getModifiedBy() {
		return Optional.ofNullable(modifiedBy);
	}
	
	public Optional<String> getAbout() {
		return Optional.ofNullable(about);
	}
	
	public Optional<List<CollegeCourseBranches>> getCollegeCourseBranches() {
		return Optional.ofNullable(branches);
	}
}
