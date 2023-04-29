package com.spdx.hms.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "student_applied_colleges")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppliedCollegesEntity extends AuditableEntity implements Serializable {
    @Id
    @Column(name = "app_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long appId;
    @Column(name = "college_id")
    Long collegeId;
    @Column(name = "college_code")
    String collegeCode;
    @Column(name = "college_course_id")
    Long collegeCourseId;
    @Column(name = "student_id")
    Long studentId;
    @Column(name = "college_course_branch_name")
    String collegeCourseBranchName;
    @Column(name = "student_mobile_number")
    String studentMobileNumber;
    @Column(name = "student_email_id")
    String studentEmailId;
    @Column(name = "student_name")
    String studentName;
    @Column(name = "active")
    @Convert(converter=BooleanToStringConverter.class)
    Boolean active;
    @Column(name = "comment")
    String comment;
    @Column(name = "status")
    String status;
    @Column(name = "qualification")
    String qualification;
    @Column(name = "college_course")
	String collegeCourse;
	@Column(name = "contact_id")
	Long contactId;
	@Column(name = "app_date")
	String appDate;
    
}
