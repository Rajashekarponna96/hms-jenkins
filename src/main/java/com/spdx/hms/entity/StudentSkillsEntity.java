package com.spdx.hms.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "student_skills")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentSkillsEntity extends AuditableEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "std_skill_id")
    private Long stdSkillId;
    @Column(name = "std_profile_id")
    Long stdProfileId;
    @Column(name = "category")
    String category;
    @Column(name = "specialization")
    String specialization;
    @Column(name = "level")
    String level;
    @Column(name = "total_experience")
    Integer totalExperience;
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
