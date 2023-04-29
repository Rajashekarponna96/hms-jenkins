package com.spdx.hms.v1.model.inbound.response;

import java.util.List;

import com.spdx.hms.dto.Branches;

import lombok.Data;

@Data
public class CourseResponse {
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
}
