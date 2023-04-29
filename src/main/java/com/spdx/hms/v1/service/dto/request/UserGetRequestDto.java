package com.spdx.hms.v1.service.dto.request;

import java.sql.Timestamp;
import java.util.Optional;

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
public class UserGetRequestDto {

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
	
	private String tokenId;
	
	
    public Optional<Long> getId() {
        return Optional.ofNullable(id);
    }

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public Optional<String> getEmailId() {
        return Optional.ofNullable(emailId);
    }

    public Optional<String> getMobileNumber() {
        return Optional.ofNullable(mobileNumber);
    }
}
