package com.spdx.hms.v1.model.inbound.response;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentReportingInfoResponse {
	
 Long stdAdmissionId;
 String dateTime;
 String contactPerson;    
 String contactPersonNumber;
 String documentsRequired;
 String comments;
 String status;
 Boolean active;
 String createdBy;
 String modifiedBy;
 @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
 LocalDateTime createdTimestamp;
 @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
 LocalDateTime modifiedTimestamp;
    
 
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
    public Optional<Boolean> getActive() {
        return Optional.ofNullable(active);
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
