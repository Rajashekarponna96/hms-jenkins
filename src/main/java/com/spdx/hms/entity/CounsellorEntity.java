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
@Table(name = "counsellor_profile")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CounsellorEntity extends AuditableEntity implements Serializable {
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
	@Column(name = "name")
	String name;
	@Column(name = "first_name")
	String firstName;
	@Column(name = "last_name")
	String lastName;
    @Column(name = "mobile_number")
	String mobileNumber;
    @Column(name = "email_id")
	String emailId;
    @Column(name = "alternate_mobile_number")
	String alternateMobileNumber;
    @Column(name = "address_one")
	String addressOne;
    @Column(name = "address_two")
	String addressTwo;
    @Column(name = "zip_code")
	String zipCode;
    @Column(name = "land_mark")
	String landMark;
    @Column(name = "district")
	String district;
    @Column(name = "city")
	String city;
    @Column(name = "state")
	String state;
    @Column(name = "dob")
	String dob;
    @Column(name = "gender")
	String gender;
    @Column(name = "marital_status")
	String maritalStatus;
    @Column(name = "active")
    @Convert(converter=BooleanToStringConverter.class)
	Boolean active;
    @Column(name = "created_by")
	String createdBy;
    @Column(name = " modified_by")
	String modifiedBy;
	

}
