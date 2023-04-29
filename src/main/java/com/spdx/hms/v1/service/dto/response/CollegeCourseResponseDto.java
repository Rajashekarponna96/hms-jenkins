package com.spdx.hms.v1.service.dto.response;

import com.spdx.hms.dto.CollegeCourseBranches;
import com.spdx.hms.dto.FeeStructure;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Optional;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class CollegeCourseResponseDto {
	
	private Long collegeCourseId;

	private Long collegeUniversityId;

	private Long collegeId;
	
	private Long courseId;
	
	private String courseName;
	
	private Long allottedSeats;

	private Long fees;
	
	private List<FeeStructure> feeStructure;
	
	private Boolean active;
	
	private Long createdTime;
	
	private Long modifiedTime;
	
	private String createdBy;
	
	private String modifiedBy;
	
	private String about;
	
	private  List<CollegeCourseBranches> branches;

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

	public Optional<Long> getCreatedTime() {
		return Optional.ofNullable(createdTime);
	}

	public Optional<Long> getModifiedTime() {
		return Optional.ofNullable(modifiedTime);
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
