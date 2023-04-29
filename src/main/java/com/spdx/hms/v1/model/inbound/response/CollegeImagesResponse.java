package com.spdx.hms.v1.model.inbound.response;

import java.sql.Timestamp;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CollegeImagesResponse {
	
	private long collegeImageId;

	private long collegeId;

	private String collegeName;

	private String collegeCode;
	
	private String imageUrls;
	
	private String tag;
	
	private boolean active;
	
	private Timestamp createdTime;
	
	private Timestamp modifiedTime;
	
	private String createdBy;
	
	private String modifiedBy;
	
	   public Optional<String> getDob() {
	        return Optional.ofNullable(collegeName);
	    }

	    public Optional<String> getGender() {
	        return Optional.ofNullable(collegeCode);
	    }
}
