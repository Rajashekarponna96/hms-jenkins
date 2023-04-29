package com.spdx.hms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Table(name = "students_admission")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentAdmissionEntity extends AuditableEntity implements Serializable {
	@Id
	@Column(name = "stdAdmission_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long stdAdmissionId;

	@Column(name = "admission_number")
	String admissionNumber;

	@Column(name = "app_id")
	Long appId;

	@Column(name = "student_id")
	Long studentId;

	@Column(name = "student_mobile_number")
	String studentMobileNumber;

	@Column(name = "student_email_id")
	String studentEmailId;

	@Column(name = "student_name")
	String studentName;

	@Column(name = "college_id")
	Long collegeId;

	@Column(name = "course_id")
	Long courseId;
	
	@Column(name = "college_course")
	String collegeCourse;
	
	@Column(name = "contact_id")
	Long contactId;
	
	@Column(name = "app_date")
	String appDate;
	

	@Column(name = "branch_name")
	String branchName;

	@Column(name = "admitted_date_time")
	String admittedDateTime;

	@Column(name = "active")
	@Convert(converter = BooleanToStringConverter.class)
	Boolean active;

	@Column(name = "status")
	String status;

	@Column(name = "admission_status")
	String admissionStatus;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "modified_by")
	private String modifiedBy;

}
