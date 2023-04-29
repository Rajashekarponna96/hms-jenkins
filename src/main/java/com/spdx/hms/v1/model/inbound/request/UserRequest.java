package com.spdx.hms.v1.model.inbound.request;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
public class UserRequest {

	private long id;

	//@NotBlank
	private String name;
	
	@NotBlank
	private String firstName;
	
	@NotBlank
	private String lastName;

	@Pattern(regexp = "^[-a-z0-9~!$%^&*_=+}{\\'?]+(\\.[-a-z0-9~!$%^&*_=+}{\\'?]+)*@([a-z0-9_][-a-z0-9_]*(\\" +
            ".[-a-z0-9_]+)*\\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z" +
            "][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})?$")
	@Column(name="email_Id", unique=true)
	private String emailId;
	
	@Size(min = 10, max=10)
	@Pattern(regexp = "^(^$|\\d{10})$")
	@Column(name="mobile_Number", unique=true)
	private String mobileNumber;

	@Size(min = 8)
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
