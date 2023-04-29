package com.spdx.hms.v1.model.inbound.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Optional;

import com.spdx.hms.dto.Branches;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseGetRequest {
  Long courseId;
  String category; //HM ,Eng, Nurse
  String courseType; // Diplome,PG,Degree
  String level; //SSC,12th/Intermediate,UG,PG,MBA,Diploma,Craft Course
  String courseName;
  String shortCourseName;
  String duration;
  //specilization json,
  Integer termYears;
  String qualificationCriteria;
  Boolean active;
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


  public Optional<List<Branches>> getBranches() {
	    return Optional.ofNullable(branches);
	  }
  public Optional<String> getAbout() {
	    return Optional.ofNullable(about);
	  }
  
  
}
