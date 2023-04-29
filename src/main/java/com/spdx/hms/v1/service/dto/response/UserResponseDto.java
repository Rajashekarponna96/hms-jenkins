package com.spdx.hms.v1.service.dto.response;

import java.sql.Timestamp;

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
public class UserResponseDto {

	private long id;

	private String name;
	
	private String firstName;
	
	private String lastName;

	private String emailId;
	
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
}
