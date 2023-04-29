package com.spdx.hms.v1.model.inbound.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Optional;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentGetRequest {
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
    String createdBy;
    String modifiedBy;
    Long createdTimestamp;
    Long modifiedTimestamp;

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

}
