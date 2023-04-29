package com.spdx.hms.mapper;

import java.time.Instant;

import org.mapstruct.AfterMapping;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import com.spdx.hms.entity.CollegeStudentFollowUpsEntity;
import com.spdx.hms.v1.service.dto.request.CollegeStudentFollowUpsGetRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeStudentFollowUpsSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeStudentFollowUpsUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.CollegeStudentFollowUpsResponseDto;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.WARN,
collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
implementationName = "ICollegeStudentFollowUpsDomainMapperImpl")
public interface ICollegeStudentFollowUpsDomainMapper extends IOptionalMapper{
    
	CollegeStudentFollowUpsEntity map(CollegeStudentFollowUpsSaveRequestDto requestDto);
	@AfterMapping
	  default void mapAuditFields(@MappingTarget CollegeStudentFollowUpsEntity CollegeStudentFollowUpsEntity) {
		CollegeStudentFollowUpsEntity.setCreatedTimestamp(Instant.now().toEpochMilli());
		CollegeStudentFollowUpsEntity.setModifiedTimestamp(Instant.now().toEpochMilli());
	  }
	default CollegeStudentFollowUpsEntity map(CollegeStudentFollowUpsEntity requestDto, CollegeStudentFollowUpsUpdateRequestDto request){
		request.getFollowpId().ifPresent(requestDto::setFollowpId);
		request.getContactId().ifPresent(requestDto::setContactId);
		request.getStudentId().ifPresent(requestDto::setStudentId);
		request.getStudentMobileNumber().ifPresent(requestDto::setStudentMobileNumber);
		request.getStudentEmailId().ifPresent(requestDto::setStudentEmailId);
		request.getStudentName().ifPresent(requestDto::setStudentName);
		request.getCollegeId().ifPresent(requestDto::setCollegeId);
		request.getRemarks().ifPresent(requestDto::setRemarks);
		request.getDateTime().ifPresent(requestDto::setDateTime);
		request.getStatus().ifPresent(requestDto::setStatus);
		request.getModifiedBy().ifPresent(requestDto::setModifiedBy);
		request.getModifiedTimestamp().ifPresent(requestDto::setModifiedTimestamp);
		request.getCreatedTimestamp().ifPresent(requestDto::setCreatedTimestamp);
	    return requestDto;
	  }
	CollegeStudentFollowUpsEntity map(CollegeStudentFollowUpsUpdateRequestDto CollegeStudentFollowUpsUpdateRequestDto);
	CollegeStudentFollowUpsResponseDto map(CollegeStudentFollowUpsEntity requestDto);
	CollegeStudentFollowUpsResponseDto map(CollegeStudentFollowUpsGetRequestDto requestDto);

}
