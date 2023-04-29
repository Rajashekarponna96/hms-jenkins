package com.spdx.hms.v1.service.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Collection;
import java.util.Optional;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentResponseDto {
    Long id;
    String name;
    String firstName;
    String fatherName;
    String motherName;
    String guardianName;
    String lastName;
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
    Boolean active;
    String dob;
    String gender;
    String maritalStatus;
    String physicallyChallenged;
    String createdBy;
    String modifiedBy;
    Long createdTimestamp;
    Long modifiedTimestamp;
    Collection<StudentExperienceResponseDto> studentExperiences;

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

    public Optional<Collection<StudentExperienceResponseDto>> getStudentExperiences() {
        return Optional.ofNullable(studentExperiences);
    }
}
