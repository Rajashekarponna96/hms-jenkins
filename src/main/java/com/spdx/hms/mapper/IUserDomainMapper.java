package com.spdx.hms.mapper;

import java.time.Instant;

import org.mapstruct.AfterMapping;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import com.spdx.hms.entity.StudentCounsellorEntity;
import com.spdx.hms.entity.UserEntity;
import com.spdx.hms.entity.UserTokenEntity;
import com.spdx.hms.v1.model.inbound.response.OTPResponse;
import com.spdx.hms.v1.service.dto.request.StudentCounsellorUpdateRequestDto;
import com.spdx.hms.v1.service.dto.request.UserRequestDto;
import com.spdx.hms.v1.service.dto.request.UserUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.UserResponseDto;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.WARN,
collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
implementationName = "IUserDomainMapperImpl")
public interface IUserDomainMapper extends IOptionalMapper {
	
	UserEntity map(UserRequestDto userRequest);
	@AfterMapping
	  default void mapAuditFields(@MappingTarget UserEntity userEntity) {
		userEntity.setCreatedTimestamp(Instant.now().toEpochMilli());
		userEntity.setModifiedTimestamp(Instant.now().toEpochMilli());
	  }
	
	default UserEntity map(UserEntity userEntity, UserUpdateRequestDto request){
		request.getName()
		         .ifPresent(userEntity::setName);
		request.getFirstName()
		          .ifPresent(userEntity::setFirstName);
		request.getLastName()
		          .ifPresent(userEntity::setLastName);
		request.getEmailId()
		          .ifPresent(userEntity::setEmailId);
		request.getMobileNumber()
		          .ifPresent(userEntity::setMobileNumber);
		request.getPassword()
		           .ifPresent(userEntity::setPassword);
		request.getRole()
		         .ifPresent(userEntity::setUserType);
		request.getState()
		           .ifPresent(userEntity::setState);
		request.getActive()
		            .ifPresent(userEntity::setActive);
		request.getResetPassword()
		           .ifPresent(userEntity::setResetPassword);
		request.getCreatedBy()
		           .ifPresent(userEntity::setCreatedBy);
		request.getModifiedBy()
		              .ifPresent(userEntity::setModifiedBy);
		request.getCreatedTime()
		               .ifPresent(userEntity::setCreatedTime);
		request.getModifiedTime()
		               .ifPresent(userEntity::setModifiedTime);
		return userEntity;
		 
	}
	
	UserEntity map(UserUpdateRequestDto userUpdateRequestDto);
	
    @Mappings({ @Mapping(target = "password", ignore = true) })
	UserResponseDto map(UserEntity userRequest);
	
	@Mappings({ @Mapping(target = "status", ignore = true), @Mapping(target = "otp", ignore = true)})
	OTPResponse map(UserTokenEntity entity);
	

}
