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
@Table(name = "student_counsellor_followup")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentCounsellorFollowupEntity extends AuditableEntity implements Serializable{
	@Id
    @Column(name = "clng_req_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long cnlgReqId;
	@Column(name ="std_counsellor_request_id")
	Long stdCounsellorRequestId;
	@Column(name = "remarks")
	String remarks;
	@Column(name= "creasted_time")
	String createdTime;
	@Column(name = "active")
	@Convert(converter=BooleanToStringConverter.class)
	Boolean active;

}
