package com.spdx.hms.v1.service.dto.request;

import java.util.Optional;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentReportingInfoGetRequestDto {
	
	Long stdAdmissionId;
	String dateTime;
    String contactPerson;
    String contactPersonNumber;
    String documentsRequired;
    String comments;
    String status;
    String createdBy;
    String modifiedBy;
    Long createdTimestamp;
    Long modifiedTimestamp;
      
    public Optional<Long> getStdAdmissionId() {
        return Optional.ofNullable(stdAdmissionId);
    }

    public Optional<String> getDateTime() {
        return Optional.ofNullable(dateTime);
      }
    
    public Optional<String> getContactPerson() {
        return Optional.ofNullable(contactPerson);
      }
    
    public Optional<String> getContactPersonNumber() {
        return Optional.ofNullable(contactPersonNumber);
        
      }
    
    public Optional<String> getDocumentsRequired() {
        return Optional.ofNullable(documentsRequired);
        
      }
    public Optional<String> getComments() {
        return Optional.ofNullable(comments);
        
      }
    
    public Optional<String> getStatus() {
        return Optional.ofNullable(status);
        
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
