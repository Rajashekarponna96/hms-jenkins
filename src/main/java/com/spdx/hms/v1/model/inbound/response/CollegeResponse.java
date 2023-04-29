package com.spdx.hms.v1.model.inbound.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spdx.hms.dto.ManagementInfo;
import com.spdx.hms.dto.BankAccountInfo;
import com.spdx.hms.dto.HostelFacilities;

import com.spdx.hms.entity.GpsLocation;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Setter
@Getter
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CollegeResponse {

    Long collegeId;
    String collegeName;
    String shortName;
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
    String geoLocation;
    GpsLocation gpsLocation;
    boolean active;
    String createdBy;
    String modifiedBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime createdTimestamp;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime modifiedTimestamp;
    String landLineNumber;
    String instituteType;
    String hostelFacility;
    List<Long> universityIds;
    Collection<CollegeCourseResponse> collegeCourses;
    ManagementInfo managementInfo;
    List<HostelFacilities> hostelFacilities;
    String about;
    BankAccountInfo bankAccountInfo;


}
