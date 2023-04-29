package com.spdx.hms.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "student_shortlisted_colleges")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShortlistedCollegesEntity extends AuditableEntity implements Serializable {
    @Id
    @Column(name = "shrt_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long shrtId;
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
}
