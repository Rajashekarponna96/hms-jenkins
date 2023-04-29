package com.spdx.hms.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_login")
public class UserEntity  extends AuditableEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "user_name")
	private String name;
	
	@Column(name="first_name")
	@NotBlank
	private String firstName;
	
	@Column(name="last_name")
	@NotBlank
	private String lastName;
	
	@Column(name = "email_id",unique = true)
	private String emailId;
	
	@Column(name = "mobile_number",unique = true)
	private String mobileNumber;

	@Column(name = "password")
	private String password;
	
	@Column(name = "user_role")
	private String role;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "user_type")
	private String userType;
	
	@Column(name = "active")
	private boolean active;
	
	@Column(name = "reset_password")
	private boolean resetPassword;
	
	@Column(name = "created_time")
	private Timestamp createdTime;
	
	@Column(name = "modified_time")
	private Timestamp modifiedTime;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "modified_by")
	private String modifiedBy;
}
