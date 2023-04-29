package com.spdx.hms.v1.service.dto.request;

import java.sql.Timestamp;
import java.util.Optional;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequestDto {
	private long id;

	private String name;
	
	private String firstName;
	
	private String lastName;
	
	@Column(name="email_id", unique=true)
	@Pattern(regexp = "^[-a-z0-9~!$%^&*_=+}{\\'?]+(\\.[-a-z0-9~!$%^&*_=+}{\\'?]+)*@([a-z0-9_][-a-z0-9_]*(\\" +
            ".[-a-z0-9_]+)*\\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z" +
            "][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})?$")
    private String emailId;
	
	@Column(name="mobile_Number", unique=true)
	private String mobileNumber;

	private String password;
	
	private String role;
	
	private String userType;
	
	private String state;
	
	private Boolean active;
	
    private Boolean resetPassword;
	
	private Timestamp createdTime;
	
	private Timestamp modifiedTime;
	
	private String createdBy;
	
	private String modifiedBy;
	
	public Optional<Long> getId() {
        return Optional.ofNullable(id);
    } 
	
	public Optional<String> getName() {
        return Optional.ofNullable(name);
    } 
	
	public Optional<String> getFirstName() {
        return Optional.ofNullable(firstName);
    } 
  
	public Optional<String> getLastName() {
        return Optional.ofNullable(lastName);
    } 
	
	public Optional<String> getEmailId() {
        return Optional.ofNullable(emailId);
    } 
	
	public Optional<String> getMobileNumber() {
        return Optional.ofNullable(mobileNumber);
    } 
	
	public Optional<String> getPassword() {
        return Optional.ofNullable(password);
    } 
	
	public Optional<String> getRole() {
        return Optional.ofNullable(role);
    } 
	
	public Optional<String> getUserType() {
        return Optional.ofNullable(userType);
    } 
	
	public Optional<String> getState() {
        return Optional.ofNullable(state);
    } 
	
	public Optional<Boolean> getActive() {
        return Optional.ofNullable(active);
    } 
	
	public Optional<Boolean> getResetPassword() {
        return Optional.ofNullable(resetPassword);
    } 
	
	public Optional<String> getCreatedBy() {
        return Optional.ofNullable(createdBy);
    }

    public Optional<String> getModifiedBy() {
        return Optional.ofNullable(modifiedBy);
    }

    public Optional<Timestamp> getCreatedTime() {
        return Optional.ofNullable(createdTime);
    }

    public Optional<Timestamp> getModifiedTime() {
        return Optional.ofNullable(modifiedTime);
    }

}
