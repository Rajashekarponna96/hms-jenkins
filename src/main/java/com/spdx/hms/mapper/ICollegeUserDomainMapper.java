package com.spdx.hms.mapper;

import java.time.Instant;

import org.mapstruct.AfterMapping;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import com.spdx.hms.entity.CollegeUserEntity;
import com.spdx.hms.v1.service.dto.request.CollegeUserGetRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeUserSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeUserUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.CollegeUserResponseDto;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.WARN,
collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
implementationName = "ICollegeUserDomainMapperImpl")
public interface ICollegeUserDomainMapper  extends IOptionalMapper {
	CollegeUserEntity map(CollegeUserSaveRequestDto requestDto);
	@AfterMapping
	  default void mapAuditFields(@MappingTarget CollegeUserEntity CollegeUserEntity) {
		CollegeUserEntity.setCreatedTimestamp(Instant.now().toEpochMilli());
		CollegeUserEntity.setModifiedTimestamp(Instant.now().toEpochMilli());
	  }
	
	default CollegeUserEntity map(CollegeUserEntity requestDto, CollegeUserUpdateRequestDto request){
		request.getId().ifPresent(requestDto::setId);
		request.getName().ifPresent(requestDto::setName);
		request.getFirstName().ifPresent(requestDto::setFirstName);
		request.getLastName().ifPresent(requestDto::setLastName);
		request.getEmailId().ifPresent(requestDto::setEmailId);
		request.getMobileNumber().ifPresent(requestDto::setMobileNumber);
		request.getAlternateMobileNumber().ifPresent(requestDto::setAlternateMobileNumber);
		request.getCollegeId().ifPresent(requestDto::setCollegeId);
		request.getAddressOne().ifPresent(requestDto::setAddressOne);
		request.getAddressTwo().ifPresent(requestDto::setAddressTwo);
		request.getZipCode().ifPresent(requestDto::setZipCode);
		request.getLandMark().ifPresent(requestDto::setLandMark);
		request.getDistrict().ifPresent(requestDto::setDistrict);
		request.getCity().ifPresent(requestDto::setCity);
		request.getState().ifPresent(requestDto::setState);
		request.getActive().ifPresent(requestDto::setActive);
		request.getDob().ifPresent(requestDto::setDob);
		request.getGender().ifPresent(requestDto::setGender);
		request.getMaritalStatus().ifPresent(requestDto::setMaritalStatus);
		request.getModifiedBy().ifPresent(requestDto::setModifiedBy);
		request.getModifiedTimestamp().ifPresent(requestDto::setModifiedTimestamp);
	    return requestDto;
	  }
	  CollegeUserEntity map(CollegeUserUpdateRequestDto CollegeUserUpdateRequestDto);
	  CollegeUserResponseDto map(CollegeUserEntity requestDto);
	  CollegeUserResponseDto map(CollegeUserGetRequestDto requestDto);
	
}
