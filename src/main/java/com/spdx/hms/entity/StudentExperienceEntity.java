package com.spdx.hms.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "student_experience")
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(exclude = "student")
public class StudentExperienceEntity extends AuditableEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "std_expr_id")
    Long stdExprId;
    @Column(name = "std_profile_id")
    Long stdProfileId;
    @Column(name = "title")
    String title;
    @Column(name = "department")
    String department;
    @Column(name = "employment_type")
    String employmentType;
    @Column(name = "company_name")
    String companyName;
    @Column(name = "start_date")
    String startDate;
    @Column(name = "end_date")
    String endDate;
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
