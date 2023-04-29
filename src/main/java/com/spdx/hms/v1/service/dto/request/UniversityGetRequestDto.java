package com.spdx.hms.v1.service.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Optional;

import com.spdx.hms.entity.GpsLocation;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UniversityGetRequestDto {
  Long universityId;
  String universityCode;
  String name;
  String shortName;
  String logo; // this should be binary
  String website;
  String contactMail;
  String contactMobile;
  String contactPerson;
  String addressOne;
  String addressTwo;
  String zipCode;
  String landMark;
  String geoLocation;
  GpsLocation gpsLocation;
  String district;
  String city;
  String state;
  String universityLocation;
  String about;
  Boolean active = Boolean.TRUE;
  String createdBy;
  String modifiedBy;
  Long createdTimestamp;
  Long modifiedTimestamp;

  public Optional<Long> getUniversityId() {
    return Optional.ofNullable(universityId);
  }

  public Optional<String> getUniversityCode() {
    return Optional.ofNullable(universityCode);
  }

  public Optional<String> getName() {
    return Optional.ofNullable(name);
  }
  
  public Optional<String> getShortName() {
	    return Optional.ofNullable(shortName);
	  }

  public Optional<String> getLogo() {
    return Optional.ofNullable(logo);
  }

  public Optional<String> getWebsite() {
    return Optional.ofNullable(website);
  }

  public Optional<String> getContactMail() {
    return Optional.ofNullable(contactMail);
  }

  public Optional<String> getContactMobile() {
    return Optional.ofNullable(contactMobile);
  }

  public Optional<String> getContactPerson() {
    return Optional.ofNullable(contactPerson);
  }

  public Optional<String> getAddressOne() {
    return Optional.ofNullable(addressOne);
  }

  public Optional<String> getAddressTwo() {
    return Optional.ofNullable(addressTwo);
  }

  public Optional<String> getZipCode() {
    return Optional.ofNullable(zipCode);
  }

  public Optional<String> getLandMark() {
    return Optional.ofNullable(landMark);
  }
  
  public Optional<String> getGeoLocation() {
	    return Optional.ofNullable(geoLocation);
	  }

  public Optional<GpsLocation> getGpsLocation() {
	    return Optional.ofNullable(gpsLocation);
	  }

  public Optional<String> getDistrict() {
    return Optional.ofNullable(district);
  }

  public Optional<String> getCity() {
    return Optional.ofNullable(city);
  }

  public Optional<String> getState() {
    return Optional.ofNullable(state);
  }

  public Optional<String> getUniversityLocation() {
    return Optional.ofNullable(universityLocation);
  }

  public Optional<Boolean> getActive() {
    return Optional.ofNullable(active);
  }
  
  public Optional<String> getAbout() {
	    return Optional.ofNullable(about);
	  }
  public Optional<String> getCreatedBy() {
      return Optional.ofNullable(createdBy);
  }
  public Optional<String> getModifiedBy() {
      return Optional.ofNullable(modifiedBy);
  }
  public Optional<Long> getCreatedTimestamp() {
      return Optional.ofNullable(createdTimestamp);
  }
  public Optional<Long> getModifiedTimestamp() {
      return Optional.ofNullable(modifiedTimestamp);
  }

}
