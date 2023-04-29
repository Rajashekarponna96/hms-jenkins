package com.spdx.hms.mapper;

import java.time.Instant;
import org.mapstruct.AfterMapping;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import com.spdx.hms.entity.CollegeStudentContactEntity;
import com.spdx.hms.v1.service.dto.request.CollegeStudentContactGetRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeStudentContactSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeStudentContactUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.CollegeStudentContactResponseDto;
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.WARN,
collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
implementationName = "ICollegeStudentContactDomainMapperImpl")

public interface ICollegeStudentContactDomainMapper extends IOptionalMapper{
	
	CollegeStudentContactEntity map(CollegeStudentContactSaveRequestDto requestDto);
	@AfterMapping
	  default void mapAuditFields(@MappingTarget CollegeStudentContactEntity CollegeStudentContactEntity) {
		CollegeStudentContactEntity.setCreatedTimestamp(Instant.now().toEpochMilli());
		CollegeStudentContactEntity.setModifiedTimestamp(Instant.now().toEpochMilli());
	  }
	
	
	default CollegeStudentContactEntity map(CollegeStudentContactEntity requestDto, CollegeStudentContactUpdateRequestDto request){
		request.getContactId().ifPresent(requestDto::setContactId);
		request.getStudentId().ifPresent(requestDto::setStudentId);
		request.getStudentMobileNumber().ifPresent(requestDto::setStudentMobileNumber);
		request.getStudentEmailId().ifPresent(requestDto::setStudentEmailId);
		request.getStudentName().ifPresent(requestDto::setStudentName);
		request.getCollegeId().ifPresent(requestDto::setCollegeId);
		request.getActive().ifPresent(requestDto::setActive);
		request.getCreatedOn().ifPresent(requestDto::setCreatedOn);
		request.getModifiedBy().ifPresent(requestDto::setModifiedBy);
		request.getModifiedTimestamp().ifPresent(requestDto::setModifiedTimestamp);
	    return requestDto;
	  }
	  CollegeStudentContactEntity map(CollegeStudentContactUpdateRequestDto CollegeStudentContactUpdateRequestDto);
	  CollegeStudentContactResponseDto map(CollegeStudentContactEntity requestDto);
	  CollegeStudentContactResponseDto map(CollegeStudentContactGetRequestDto requestDto);

}
