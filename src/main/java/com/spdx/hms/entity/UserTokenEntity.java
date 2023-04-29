package com.spdx.hms.entity;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Entity
@Table(name = "user_login_token")
public class UserTokenEntity {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue
	@Column(name = "token_id", columnDefinition = "uuid", updatable = false )
	//@Column(name = "token_id")
	private UUID tokenId;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "email_id")
	private String emailId;
	
	@Column(name = "mobile_number")
	private String mobileNumber;

	@Column(name = "email_token")
	private String emailToken;
	
	@Column(name = "mobile_token")
	private String mobileToken;
	
	@Column(name = "expire_time_seconds")
	private Long expireTimeSeconds;
	
	@Column(name = "active")
	private boolean active;
	
	@Column(name = "verfication_status")
	private String verficationStatus;
	
	@Column(name = "created_time")
	private Timestamp createdTime;
	
	@Column(name = "modified_time")
	private Timestamp modifiedTime;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "modified_by")
	private String modifiedBy;
}
