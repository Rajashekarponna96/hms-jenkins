package com.spdx.hms.v1.service.dto.response;

import lombok.Data;

import java.util.List;
import java.util.Optional;

import com.spdx.hms.dto.Branches;

@Data
public class CourseResponseDto {
  Long courseId;
  String category; //HM ,Eng, Nurse
  String courseType; // Diplome,PG,Degree
  String level; //SSC,12th/Intermediate,UG,PG,MBA,Diploma,Craft Course
  String courseName;
  String duration;
  String shortCourseName;
  //specilization json,
  Integer termYears;
  String qualificationCriteria;
  Boolean active;
  String createdBy;
  String modifiedBy;
  Long createdTimestamp;
  Long modifiedTimestamp;
  List<Branches> branches;
  String about;

  public Optional<Long> getCourseId() {
    return Optional.ofNullable(courseId);
  }

  public Optional<String> getCategory() {
    return Optional.ofNullable(category);
  }

  public Optional<String> getCourseType() {
    return Optional.ofNullable(courseType);
  }

  public Optional<String> getLevel() {
    return Optional.ofNullable(level);
  }

  public Optional<String> getCourseName() {
    return Optional.ofNullable(courseName);
  }

  public Optional<String> getDuration() {
    return Optional.ofNullable(duration);
  }

  public Optional<Integer> getTermYears() {
    return Optional.ofNullable(termYears);
  }

  public Optional<String> getQualificationCriteria() {
    return Optional.ofNullable(qualificationCriteria);
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
  
  public Optional<List<Branches>> getBranches() {
	    return Optional.ofNullable(branches);
	  }
  
  public Optional<String> getAbout() {
	    return Optional.ofNullable(about);
	  }
}
