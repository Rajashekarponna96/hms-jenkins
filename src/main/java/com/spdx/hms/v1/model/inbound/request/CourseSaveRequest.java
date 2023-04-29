package com.spdx.hms.v1.model.inbound.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

import com.spdx.hms.dto.Branches;

import java.util.List;
import java.util.Optional;

@Data
public class CourseSaveRequest {
  @NotBlank
  String category; //HM ,Eng, Nurse
  String courseType; // Diploma,PG,Degree
  @NotBlank
  String level; //SSC,12th/Intermediate,UG,PG,MBA,Diploma,Craft Course
  @NotBlank
  String courseName;
  String shortCourseName;
  @NotBlank
  String duration;
  //specilization json,
  Integer termYears;
  String qualificationCriteria;
  List<Branches> branches;
  String about;

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
