package com.spdx.hms.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.Collection;

@Data
@ToString(exclude = "studentExperiences")
@Entity
@Table(name = "student_profile")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentEntity extends AuditableEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "name")
    String name;
    @Column(name = "first_name")
    @NotBlank
    String firstName;
    @Column(name = "last_name")
    @NotBlank
    String lastName;
    @Column(name = "father_name")
    String fatherName;
    @Column(name = "mother_name")
    String motherName;
    @Column(name = "guardian_name")
    String guardianName;
    @Column(name = "email_id")
    @NotBlank
    String emailId;
    @Column(name = "mobile_number")
    @NotBlank
    String mobileNumber;
    @Column(name = "alternate_mobile_number")
    String alternateMobileNumber;
    @Column(name = "identity_type")
    String identityType;
    @Column(name = "id_proof")
    String identityProof;
    @Column(name = "address_one")
    String addressOne;
    @Column(name = "address_two")
    String addressTwo;
    @Column(name = "zip_code")
    String zipCode;
    @Column(name = "land_mark")
    String landMark;
    @Column(name = "district")
    String district;
    @Column(name = "city")
    String city;
    @Column(name = "state")
    String state;
    @Column(name = "active")
    @Convert(converter=BooleanToStringConverter.class)
    Boolean active;
    @Column(name = "dob")
    @NotBlank
    String dob;
    @Column(name = "gender")
    @NotBlank
    String gender;
    @Column(name = "marital_status")
    String maritalStatus;
    @Column(name = "physically_challenged")
    String physicallyChallenged;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "std_profile_id", referencedColumnName = "id", insertable = false,
                    updatable = false)
    })
    Collection<StudentExperienceEntity>  studentExperiences;
}
