package com.spdx.hms.v1.model.inbound.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Optional;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentSaveRequest {
    String name;
    @Size(min = 3)
    @NotBlank
    String firstName;
    @Size(min = 3)
    @NotBlank
    String lastName;
    @Size(min = 3)
    String fatherName;
    @Size(min = 3)
    String motherName;
    @Size(min = 3)
    String guardianName;
    @Pattern(regexp = "^[-a-z0-9~!$%^&*_=+}{\\'?]+(\\.[-a-z0-9~!$%^&*_=+}{\\'?]+)*@([a-z0-9_][-a-z0-9_]*(\\" +
            ".[-a-z0-9_]+)*\\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z" +
            "][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})?$")
    @NotBlank
    String emailId;
    @Size(min = 10, max=10)
    @Pattern(regexp = "^(^$|\\d{10})$")
    @NotBlank
    String mobileNumber;
    @Size(min = 10, max=10)
    @Pattern(regexp = "^(^$|\\d{10})$")
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
    @NotBlank
    String dob;
    @NotBlank
    String gender;
    
    String maritalStatus;
    String physicallyChallenged;
    String createdBy;
    String modifiedBy;
    StudentExperienceSaveRequest studentExperience;

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

    public Optional<String> getCreatedBy() {
        return Optional.ofNullable(createdBy);
    }

    public Optional<String> getModifiedBy() {
        return Optional.ofNullable(modifiedBy);
    }

    public Optional<StudentExperienceSaveRequest> getStudentExperience() {
        return Optional.ofNullable(studentExperience);
    }
}
