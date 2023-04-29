package com.spdx.hms.mapper;

import java.time.Instant;

import org.mapstruct.AfterMapping;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import com.spdx.hms.entity.CounsellorEntity;
import com.spdx.hms.entity.StudentCounsellorEntity;
import com.spdx.hms.v1.service.dto.request.CounsellorGetRequestDto;
import com.spdx.hms.v1.service.dto.request.CounsellorSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.CounsellorUpdateRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentCounsellorGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentCounsellorSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentCounsellorUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.CounsellorResponseDto;
import com.spdx.hms.v1.service.dto.response.StudentCounsellorResponseDto;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.WARN,
collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
implementationName = "ICounsellorDomainMapperImpl")
public interface ICounsellorDomainMapper extends IOptionalMapper{
	
	CounsellorEntity map(CounsellorSaveRequestDto requestDto);
	 @AfterMapping
	  default void mapAuditFields(@MappingTarget CounsellorEntity counsellorEntity) {
		 counsellorEntity.setCreatedTimestamp(Instant.now().toEpochMilli());
		 counsellorEntity.setModifiedTimestamp(Instant.now().toEpochMilli());
	  }
	 default CounsellorEntity map(CounsellorEntity counsellorEntity, CounsellorUpdateRequestDto request){
		 request.getName()
		          .ifPresent(counsellorEntity::setName);
		 request.getFirstName()
		          .ifPresent(counsellorEntity::setFirstName);
		 request.getLastName()
		          .ifPresent(counsellorEntity::setLastName);
		 request.getMobileNumber()
		          .ifPresent(counsellorEntity::setMobileNumber);
		 request.getEmailId()
		          .ifPresent(counsellorEntity::setEmailId);
		 request.getAlternateMobileNumber()
		          .ifPresent(counsellorEntity::setAlternateMobileNumber);
		 request.getAddressOne()
		          .ifPresent(counsellorEntity::setAddressOne);
		 request.getAddressTwo()
		          .ifPresent(counsellorEntity::setAddressTwo);
		 request.getZipCode()
		          .ifPresent(counsellorEntity::setZipCode);
		 request.getLandMark()
		          .ifPresent(counsellorEntity::setLandMark);
		 request.getDistrict()
		          .ifPresent(counsellorEntity::setLandMark);
		 request.getCity()
		          .ifPresent(counsellorEntity::setCity);
		 request.getState()
		          .ifPresent(counsellorEntity::setState);
		 request.getDob()
		          .ifPresent(counsellorEntity::setDob);
		 request.getGender()
		          .ifPresent(counsellorEntity::setGender);
		 request.getMaritalStatus()
		          .ifPresent(counsellorEntity::setMaritalStatus);
		 request.getActive()
		          .ifPresent(counsellorEntity::setActive);
		 request.getCreatedBy()
		          .ifPresent(counsellorEntity::setCreatedBy);
		 request.getModifiedBy()
		          .ifPresent(counsellorEntity::setModifiedBy);
		 request.getCreatedTimestamp()
		          .ifPresent(counsellorEntity::setCreatedTimestamp);
		 request.getModifiedTimestamp()
		          .ifPresent(counsellorEntity::setModifiedTimestamp);
		 return counsellorEntity;
		 
		}
	 CounsellorEntity map(CounsellorUpdateRequestDto counsellorUpdateRequestDto);

	 CounsellorResponseDto map(CounsellorEntity requestDto);

	 CounsellorResponseDto map(CounsellorGetRequestDto requestDto);



}
