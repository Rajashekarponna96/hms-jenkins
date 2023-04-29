package com.spdx.hms.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "university")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UniversityEntity extends AuditableEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long universityId;
  @Column(name = "university_code")
  String universityCode;
  @Column(name = "name")
  String name;
  @Column(name="short_name")
  String shortName;
  @Column(name = "logo")
  String logo; // this should be binary
  @Column(name = "website")
  String website;
  @Column(name = "contact_mail")
  String contactMail;
  @Column(name = "contact_mobile")
  String contactMobile;
  @Column(name = "contact_person")
  String contactPerson;
  @Column(name = "address_one")
  String addressOne;
  @Column(name = "address_two")
  String addressTwo;
  @Column(name = "zip_code")
  String zipCode;
  @Column(name = "land_mark")
  String landMark;
  @Column(name = "Geo_location")
  String geoLocation;
  @Convert(converter = GpsLocationConverter.class)
  @Column(name = "Gps_location",columnDefinition = "json")
  GpsLocation gpsLocation;
  @Column(name = "district")
  String district;
  @Column(name = "city")
  String city;
  @Column(name = "state")
  String state;
  @Column(name = "university_location")
  String universityLocation;
  @Column(name = "active")
  @Convert(converter = BooleanToStringConverter.class)
  Boolean active;
  @Column(name = "about",length=2000, columnDefinition="TEXT")
  String about;
}
