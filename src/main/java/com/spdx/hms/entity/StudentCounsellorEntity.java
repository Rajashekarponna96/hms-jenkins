package com.spdx.hms.entity;

import java.io.Serializable;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Table(name = "student_counsellor_request")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentCounsellorEntity extends AuditableEntity implements Serializable {
	@Id
    @Column(name = "std_counsellor_request_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long stdCounsellorRequestId;
    @Column(name = "student_id")
    Long studentId;
    @Column(name = "student_mobile_number")
    String studentMobileNumber;
    @Column(name = "student_email_id")
    String studentEmailId;
    @Column(name = "student_name")
    String studentName;
    @Column(name = "counsellor_id")
    Long counsellorId;
    @Column(name = "counsellor_name")
    String counsellorName;
    @Column(name = "counsellor_email_id")
    String counsellorEmailId;
    @Column(name = "counsellor_mobile_number")
    String counsellorMobileNumber;
    @Column(name = "active")
    @Convert(converter=BooleanToStringConverter.class)
    Boolean active;
    @Column(name = "appointment_created_by")
    String appointmentCreatedBy;
    @Column(name = "created_time")
    String createdTime;
    @Column(name = "counsellor_assigned_time")
    String counsellorAssignedTime;
    

}
