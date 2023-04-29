package com.spdx.hms.mapper;

import com.spdx.hms.entity.CourseEntity;
import com.spdx.hms.entity.UniversityEntity;
import com.spdx.hms.v1.service.dto.request.*;
import com.spdx.hms.v1.service.dto.response.CourseResponseDto;
import com.spdx.hms.v1.service.dto.response.UniversityResponseDto;
import org.mapstruct.*;

import java.time.Instant;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.WARN,
    collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
    implementationName = "IUniversityDomainMapperImpl")
public interface IUniversityDomainMapper extends IOptionalMapper {
  UniversityEntity map(UniversitySaveRequestDto requestDto);
  @AfterMapping
  default void mapAuditFields(@MappingTarget UniversityEntity universityEntity) {
    universityEntity.setCreatedTimestamp(Instant.now().toEpochMilli());
    universityEntity.setModifiedTimestamp(Instant.now().toEpochMilli());
  }

  default UniversityEntity map(UniversityEntity requestDto, UniversityUpdateRequestDto request){
	  request.getUniversityId().ifPresent(requestDto::setUniversityId);
	  request.getUniversityCode().ifPresent(requestDto::setUniversityCode);
	  request.getName().ifPresent(requestDto::setName);
	  request.getShortName().ifPresent(requestDto::setShortName);
	  request.getLogo().ifPresent(requestDto::setLogo);
	  request.getWebsite().ifPresent(requestDto::setWebsite);
	  request.getContactMail().ifPresent(requestDto::setContactMail);
	  request.getContactMobile().ifPresent(requestDto::setContactMobile);
	  request.getContactPerson().ifPresent(requestDto::setContactPerson);
	  request.getAddressOne().ifPresent(requestDto::setAddressOne);
	  request.getAddressTwo().ifPresent(requestDto::setAddressTwo);
	  request.getZipCode().ifPresent(requestDto::setZipCode);
	  request.getLandMark().ifPresent(requestDto::setLandMark);
	  request.getGeoLocation().ifPresent(requestDto::setGeoLocation);
	  request.getGpsLocation().ifPresent(requestDto::setGpsLocation);
	  request.getDistrict().ifPresent(requestDto::setDistrict);
	  request.getCity().ifPresent(requestDto::setCity);
	  request.getState().ifPresent(requestDto::setState);
	  request.getUniversityLocation().ifPresent(requestDto::setUniversityLocation);
	  request.getAbout().ifPresent(requestDto::setAbout);
	  request.getActive().ifPresent(requestDto::setActive);
	  request.getModifiedBy().ifPresent(requestDto::setModifiedBy);
	  request.getModifiedTimestamp().ifPresent(requestDto::setModifiedTimestamp);
      return requestDto;
  }

  UniversityEntity map(UniversityUpdateRequestDto universityUpdateRequestDto);

  UniversityResponseDto map(UniversityEntity requestDto);

  UniversityResponseDto map(UniversityGetRequestDto requestDto);
}

