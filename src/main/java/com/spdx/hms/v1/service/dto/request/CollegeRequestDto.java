package com.spdx.hms.v1.service.dto.request;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
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
public class CollegeRequestDto {
	
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
	Boolean active = Boolean.TRUE;
	Timestamp createdTime;
	String createdBy;
	String modifiedBy;
	Long createdTimestamp;
	Long modifiedTimestamp;
	String landLineNumber;
	String instituteType;
	String hostelFacility;
	List<Long> universityIds;
	List<HostelFacilities> hostelFacilities;
	ManagementInfo managementInfo;
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

	public Optional<Boolean> getActive() {
		return Optional.ofNullable(active);
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
	public Optional<List<HostelFacilities>> getHostelFacilities() {
		return Optional.ofNullable(hostelFacilities);
	}
	
	public Optional<ManagementInfo> getManagementInfo() {
        return Optional.ofNullable(managementInfo);
    }
	public Optional<String> getAbout() {
		return Optional.ofNullable(about);
	}
	public Optional<BankAccountInfo> getBankAccountInfo() {
        return Optional.ofNullable(bankAccountInfo);
    }

}
