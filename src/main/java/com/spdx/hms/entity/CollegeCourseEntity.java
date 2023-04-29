package com.spdx.hms.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.spdx.hms.dto.Branches;
import com.spdx.hms.dto.CollegeCourseBranches;
import com.spdx.hms.dto.FeeStructure;
import com.spdx.hms.dto.HostelFacilities;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "college_course")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CollegeCourseEntity extends AuditableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "college_course_id")
	private Long collegeCourseId;

	@Column(name = "college_university_id")
	private Long collegeUniversityId;

	@Column(name = "college_id")
	private Long collegeId;
	
	@Column(name = "course_id")
	private Long courseId;
	
	@Column(name = "course_name")
	private String courseName;
	
	@Column(name = "allotted_seats")
	private Long allottedSeats;

	@Column(name = "fees")
	private Long fees;
	
	@Convert(converter = ListToFeeStructureConverter.class)
	@Column(name = "fee_structure",columnDefinition = "json")
	private List<FeeStructure> feeStructure;
	
	@Column(name = "active")
	private Boolean active;

	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "about",length=2000, columnDefinition="TEXT")
	private String about;
	
	@Convert(converter = ListToCollegeCourseBranches.class)
	@Column(name = "branches",columnDefinition = "json")
	List<CollegeCourseBranches> branches;

}
