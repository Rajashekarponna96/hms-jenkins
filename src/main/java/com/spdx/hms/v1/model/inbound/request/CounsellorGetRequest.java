package com.spdx.hms.v1.model.inbound.request;

import java.util.Optional;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CounsellorGetRequest {
	
	Long id;
	String name;
	String firstName;
	String lastName;
	String mobileNumber;
	String emailId;
	String alternateMobileNumber;
	String addressOne;
	String addressTwo;
	String zipCode;
	String landMark;
	String district;
	String city;
	String state;
	String dob;
	String gender;
	String maritalStatus;
	Boolean active;
	String createdBy;
	String modifiedBy;
	Long createdTimestamp;
	Long modifiedTimestamp;
	
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
	
	public Optional<String> getMobileNumber() {
        return Optional.ofNullable(mobileNumber);
    } 
	
	public Optional<String> getEmailId() {
        return Optional.ofNullable(emailId);
    } 
	
	public Optional<String> getAlternateMobileNumber() {
        return Optional.ofNullable(alternateMobileNumber);
    } 
	
	public Optional<String> getAddressOne() {
        return Optional.ofNullable(addressOne);
    } 
	
	public Optional<String> getAddressTwo() {
        return Optional.ofNullable(addressTwo);
    } 
	
	public Optional<String> getZipCode() {
        return Optional.ofNullable(zipCode);
    } 
	
	public Optional<String> getLandMark() {
        return Optional.ofNullable(landMark);
    } 
	
	public Optional<String> getDistrict() {
        return Optional.ofNullable(district);
    } 
	
	public Optional<String> getCity() {
        return Optional.ofNullable(city);
    } 
	
	public Optional<String> getState() {
        return Optional.ofNullable(state);
    } 
	
	public Optional<String> getDob() {
        return Optional.ofNullable(dob);
    } 
	
	public Optional<String> getGender() {
        return Optional.ofNullable(gender);
    } 
	
	public Optional<String> getMaritalStatus() {
        return Optional.ofNullable(maritalStatus);
    } 
	
	public Optional<Boolean> getActive() {
        return Optional.ofNullable(active);
    }
	
	 public Optional<String> getCreatedBy() {
	        return Optional.ofNullable(createdBy);
	    }

	 public Optional<String> getModifiedBy() {
	        return Optional.ofNullable(modifiedBy);
	    }

	 public Optional<Long> getCreatedTimestamp() {
	        return Optional.ofNullable(createdTimestamp);
	    }

	 public Optional<Long> getModifiedTimestamp() {
	        return Optional.ofNullable(modifiedTimestamp);
	    }
	
	
	
	
	
	

}
