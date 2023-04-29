package com.spdx.hms.v1.model.inbound.request;

import java.sql.Timestamp;
import java.util.List;

import com.spdx.hms.dto.CollegeCourseBranches;
import com.spdx.hms.dto.FeeStructure;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CollegeCourseRequest {
	
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


}
