package com.spdx.hms.v1.service.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import com.spdx.hms.dto.ManagementInfo;
import com.spdx.hms.dto.ManagementInfo;
import com.spdx.hms.dto.BankAccountInfo;
import com.spdx.hms.dto.HostelFacilities;
import com.spdx.hms.entity.GpsLocation;
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CollegeUpdateRequestDto {
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
    Long createdTimestamp;
    Long modifiedTimestamp;
    Timestamp createdTime;
    String landLineNumber;
    String instituteType;
    String hostelFacility;
    List<Long> universityIds;
    ManagementInfo managementInfo;
    List<HostelFacilities> hostelFacilities;
    String about;
    BankAccountInfo bankAccountInfo;

    public Optional<List<Long>> getUniversityIds() {
        return Optional.ofNullable(universityIds);
    }
    public Optional<String> getHostelFacility() {
        return Optional.ofNullable(hostelFacility);
    }
    
    public Optional<String> getInstituteType() {
        return Optional.ofNullable(instituteType);
    }
    public Optional<String> getLandLineNumber() {
        return Optional.ofNullable(landLineNumber);
    }
    public Optional<Long> getCollegeId() {
        return Optional.ofNullable(collegeId);
    }

    public Optional<String> getCollegeName() {
        return Optional.ofNullable(collegeName);
    }
    
    public Optional<String> getShortName() {
        return Optional.ofNullable(shortName);
    }

    public Optional<String> getCollegeCode() {
        return Optional.ofNullable(collegeCode);
    }

    public Optional<String> getEstablishmentYear() {
        return Optional.ofNullable(establishmentYear);
    }

    public Optional<byte[]> getLogo() {
        return Optional.ofNullable(logo);
    }

    public Optional<byte[]> getImage() {
        return Optional.ofNullable(image);
    }

    public Optional<String> getCollegeLocation() {
        return Optional.ofNullable(collegeLocation);
    }

    public Optional<String> getContactNumber() {
        return Optional.ofNullable(contactNumber);
    }

    public Optional<String> getWebsite() {
        return Optional.ofNullable(website);
    }

    public Optional<String> getContactEmail() {
        return Optional.ofNullable(contactEmail);
    }

    public Optional<String> getContactPerson() {
        return Optional.ofNullable(contactPerson);
    }

    public Optional<String> getAddress1() {
        return Optional.ofNullable(address1);
    }

    public Optional<String> getAddress2() {
        return Optional.ofNullable(address2);
    }

    public Optional<String> getCity() {
        return Optional.ofNullable(city);
    }

    public Optional<String> getState() {
        return Optional.ofNullable(state);
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
    
    public Optional<Timestamp> getCreatedTime() {
        return Optional.ofNullable(createdTime);
    }

    public Optional<Long> getCreatedTimestamp() {
        return Optional.ofNullable(createdTimestamp);
    }

    public Optional<Long> getModifiedTimestamp() {
        return Optional.ofNullable(modifiedTimestamp);
    }
    public Optional<ManagementInfo> getManagementInfo() {
        return Optional.ofNullable(managementInfo);
    }
    
    public Optional<List<HostelFacilities>> getHostelFacilities() {
		return Optional.ofNullable(hostelFacilities);
	}
	 public Optional<String> getAbout() {
	        return Optional.ofNullable(about);
	    }
	 public Optional<BankAccountInfo> getBankAccountInfo() {
	        return Optional.ofNullable(bankAccountInfo);
	    }

}
