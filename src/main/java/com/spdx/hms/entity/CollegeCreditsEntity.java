package com.spdx.hms.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "college_credits")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CollegeCreditsEntity extends AuditableEntity implements Serializable {
    @Id
    @Column(name = "credit_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long creditId;
    @Column(name = "college_id")
    Long collegeId;
    @Column(name = "college_code")
    String collegeCode;
    @Column(name = "credit_points")
    Long creditPoints;
    @Column(name = "start_period")
    Timestamp startPeriod;
    @Column(name = "end_period")
    Timestamp endPeriod;
    @Column(name = "active")
    @Convert(converter=BooleanToStringConverter.class)
    Boolean active;
}
