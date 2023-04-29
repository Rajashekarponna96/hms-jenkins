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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "college_student_followups")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CollegeStudentFollowUpsEntity extends AuditableEntity implements Serializable{
    
	@Id
	@Column(name = "followp_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    Long followpId;
	@Column(name = "contact_id")
	Long contactId;
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
	@Column(name = "active")
    @Convert(converter=BooleanToStringConverter.class)
    Boolean active;
	@Column(name = "remarks")
    String remarks;
	@Column(name = "date_time")
    String dateTime;
	@Column(name = "status")
    String status;
}
