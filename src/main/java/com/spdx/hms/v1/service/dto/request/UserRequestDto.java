package com.spdx.hms.v1.service.dto.request;

import java.sql.Timestamp;

import javax.persistence.Column;

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
public class UserRequestDto {

	private long id;

	private String name;
	
	private String firstName;
	
	private String lastName;
	
	@Column(name = "email_id",unique = true)
	private String emailId;
	@Column(name = "mobile_Number",unique = true)
	private String mobileNumber;

	private String password;
	
	private String role;
	
	private String userType;
	
	private String state;
	
	private boolean active;
	
	private boolean resetPassword;
	
	private Timestamp createdTime;
	
	private Timestamp modifiedTime;
	
	private String createdBy;
	
	private String modifiedBy;
	
	private String tokenId;
}
