package com.spdx.hms.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

import com.spdx.hms.dto.Branches;
import com.spdx.hms.dto.HostelFacilities;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "courses")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseEntity extends AuditableEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="course_id")
  Long courseId;
  @Column(name="category")
  String category; //HM ,Eng, Nurse
  @Column(name="course_type")
  String courseType; // Diplome,PG,Degree
  @Column(name="level")
  String level; //SSC,12th/Intermediate,UG,PG,MBA,Diploma,Craft Course
  @Column(name="course_name")
  String courseName;
  @Column(name="short_course_name")
  String shortCourseName;
  @Column(name="duration")
  String duration;
  //specialization json,
  @Column(name="term_years")
  Integer termYears;
  @Column(name="qualification_criteria")
  String qualificationCriteria;
  Boolean active;
  @Convert(converter = ListToBranches.class)
  @Column(name = "branches",columnDefinition = "json")
  List<Branches> branches;
  @Column(name = "about",length=2000, columnDefinition="TEXT")
  String about;

}
