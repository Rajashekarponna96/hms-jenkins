package com.spdx.hms.v1.model.inbound.response;

import java.time.LocalDateTime;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CollegeUserResponse {
	Long id;
	String name;
	String firstName;
	String lastName;
	String mobileNumber;
	String emailId;
	String alternateMobileNumber;
	Long collegeId;
	String addressOne;
	String addressTwo;
	String zipCode;
	String landMark;
	String district;
	String city;
	String state;
	Boolean active;
	String dob;
	String gender;
	String maritalStatus;
	String createdBy;
    String modifiedBy;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    LocalDateTime createdTimestamp;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    LocalDateTime modifiedTimestamp;
    
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

    public Optional<String> getAlternateMobileNumber() {
        return Optional.ofNullable(alternateMobileNumber);
    }
    
    public Optional<Long> getCollegeId() {
        return Optional.ofNullable(collegeId);
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
    
    public Optional<Boolean> getActive() {
        return Optional.ofNullable(active);
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

    public Optional<String> getCreatedBy() {
        return Optional.ofNullable(createdBy);
    }

    public Optional<String> getModifiedBy() {
        return Optional.ofNullable(modifiedBy);
    }
    public Optional<LocalDateTime> getCreatedTimestamp() {
        return Optional.ofNullable(createdTimestamp);
    }
    public Optional<LocalDateTime> getModifiedTimestamp() {
        return Optional.ofNullable(modifiedTimestamp);
    }


}
