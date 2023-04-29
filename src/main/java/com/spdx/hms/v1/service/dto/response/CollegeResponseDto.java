package com.spdx.hms.v1.service.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Collection;
import java.util.List;

import com.spdx.hms.dto.BankAccountInfo;
import com.spdx.hms.dto.HostelFacilities;
import com.spdx.hms.dto.ManagementInfo;
import com.spdx.hms.entity.GpsLocation;

@Setter
@Getter
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CollegeResponseDto {

    Long collegeId;
    String collegeName;
    String collegeCode;
    String establishmentYear;
    byte[] logo;
    byte[] image;
    String collegeLocation;
    String contactNumber;
    String website;
    String contactEmail;
    String contactPerson;
    String address1;
    String address2;
    String city;
    String state;
    String landMark;
    boolean active;
    String createdBy;
    String modifiedBy;
    Long createdTimestamp;
    Long modifiedTimestamp;
    Collection<CollegeCourseResponseDto> collegeCourses;
    String landLineNumber;
    String instituteType;
    String hostelFacility;
    List<Long> universityIds;
    ManagementInfo managementInfo;
    List<HostelFacilities> hostelFacilities;
    GpsLocation gpsLocation;
    String geoLocation;
    String shortName;
    String about;
    BankAccountInfo bankAccountInfo;


}
