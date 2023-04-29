package com.spdx.hms.v1.model.inbound.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Optional;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentUpdateRequest {
    
    Long id;
    String name;
   
    String firstName;
    
    String lastName;
    
    String fatherName;
    
    String motherName;
   
    String guardianName;
   
    String emailId;
    
    String mobileNumber;
   
    String alternateMobileNumber;
    String identityType;
    String identityProof;
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
    String physicallyChallenged;
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
    
    public Optional<String> getFatherName() {
        return Optional.ofNullable(fatherName);
    }
    public Optional<String> getMotherName() {
        return Optional.ofNullable(motherName);
    }
    public Optional<String> getGuardianName() {
        return Optional.ofNullable(guardianName);
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

    public Optional<String> getIdentityType() {
        return Optional.ofNullable(identityType);
    }

    public Optional<String> getIdentityProof() {
        return Optional.ofNullable(identityProof);
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

    public Optional<String> getPhysicallyChallenged() {
        return Optional.ofNullable(physicallyChallenged);
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
