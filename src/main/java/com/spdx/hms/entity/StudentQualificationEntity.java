package com.spdx.hms.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;


@Data
@Entity
@Table(name = "student_qualification")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentQualificationEntity extends AuditableEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "std_qualification_id")
  Long stdQualificationId;
  @Column(name = "std_profile_id")
  Long stdProfileId;
  @Column(name = "qualification",unique = true)
  String qualification;
  @Column(name = "university")
  String university;
  @Column(name = "start_date")
  String startDate;
  @Column(name = "end_date")
  String endDate;
  @Column(name = "year_of_passing")
  String yearOfPassing;
  @Column(name = "grade")
  String grade;
  @Column(name = "percentage")
  String percentage;
  @Column(name = "active")
  @Convert(converter=BooleanToStringConverter.class)
  Boolean active;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumns({
          @JoinColumn(name = "std_profile_id", referencedColumnName = "id", insertable = false,
                  updatable = false)
  })
  StudentEntity student;
}
