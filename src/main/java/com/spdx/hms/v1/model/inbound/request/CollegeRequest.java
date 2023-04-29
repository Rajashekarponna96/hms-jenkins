package com.spdx.hms.v1.model.inbound.request;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.spdx.hms.dto.ManagementInfo;

import com.spdx.hms.dto.ManagementInfo;
import com.spdx.hms.dto.BankAccountInfo;
import com.spdx.hms.dto.HostelFacilities;
import com.spdx.hms.entity.GpsLocation;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CollegeRequest {

    @NotBlank
    String collegeName;
    @NotBlank
    String shortName;
    @NotBlank
    String collegeCode;
    @NotBlank
    String establishmentYear;
    byte[] logo;
    byte[] image;
    @NotBlank
    String collegeLocation;
    @Size(min = 10)
    @Pattern(regexp = "^(^$|\\d{10})$")
    String contactNumber;
    String website;
    @Pattern(regexp = "^[-a-z0-9~!$%^&*_=+}{\\'?]+(\\.[-a-z0-9~!$%^&*_=+}{\\'?]+)*@([a-z0-9_][-a-z0-9_]*(\\" +
            ".[-a-z0-9_]+)*\\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z" +
            "][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})?$")
    String contactEmail;
    String contactPerson;
    String address1;
    String address2;
    String city;
    String state;
    String landMark;
    String geoLocation;
    GpsLocation gpsLocation;
    Timestamp createdTime;
    String createdBy;
    String modifiedBy;
    Long createdTimestamp;
    Long modifiedTimestamp;
    String landLineNumber;
    String instituteType;
	String hostelFacility;
	List<Long> universityIds;
	ManagementInfo managementInfo;
	List<HostelFacilities> hostelFacilities;
    String about;
    BankAccountInfo bankAccountInfo;


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

    public Optional<String> getCreatedBy() {
        return Optional.ofNullable(createdBy);
    }

    public Optional<String> getModifiedBy() {
        return Optional.ofNullable(modifiedBy);
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
