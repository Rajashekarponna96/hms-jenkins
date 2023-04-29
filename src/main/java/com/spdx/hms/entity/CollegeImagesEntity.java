package com.spdx.hms.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "college_images")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CollegeImagesEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "college_image_id")
	private long collegeImageId;

	@Column(name = "college_id")
	private long collegeId;

	@Column(name = "college_name")
	private String collegeName;

	@Column(name = "college_code")
	private String collegeCode;
	
	@Column(name = "image_urls")
	private String imageUrls;
	
	@Column(name = "tag")
	private String tag;
	
	@Column(name = "active")
	private boolean active;
	
	@Column(name = "created_time")
	private Timestamp createdTime;
	
	@Column(name = "modified_time")
	private Timestamp modifiedTime;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "modified_by")
	private String modifiedBy;
}
