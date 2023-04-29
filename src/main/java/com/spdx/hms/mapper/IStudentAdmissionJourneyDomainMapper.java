package com.spdx.hms.mapper;

import java.time.Instant;

import org.mapstruct.AfterMapping;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import com.spdx.hms.entity.CourseEntity;
import com.spdx.hms.entity.StudentAdmissionJourneyEntity;
import com.spdx.hms.entity.StudentEntity;
import com.spdx.hms.entity.StudentReportingInfoEntity;
import com.spdx.hms.v1.service.dto.request.StudentAdmissionJourneySaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentAdmissionJourneyUpdateRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentReportingInfoUpdateRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentSaveRequestDto;
import com.spdx.hms.v1.service.dto.response.CourseResponseDto;
import com.spdx.hms.v1.service.dto.response.StudentAdmissionJourneyResponseDto;



@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.WARN,
collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
implementationName = "IStudentAdmissionJourneyDomainMapperImpl")

public interface IStudentAdmissionJourneyDomainMapper extends IOptionalMapper {
	
	StudentAdmissionJourneyEntity map(StudentAdmissionJourneySaveRequestDto requestDto);
    @AfterMapping
    default void mapAuditFields(@MappingTarget StudentAdmissionJourneyEntity studentEntity, StudentAdmissionJourneySaveRequestDto requestDto) {
        studentEntity.setCreatedTimestamp(Instant.now().toEpochMilli());
        studentEntity.setModifiedTimestamp(Instant.now().toEpochMilli());
        requestDto.getCreatedBy().ifPresent(studentEntity::setModifiedBy);
    }
	 
	 StudentAdmissionJourneyResponseDto  map(StudentAdmissionJourneyEntity requestDto);
	
	
	 default StudentAdmissionJourneyEntity map(StudentAdmissionJourneyEntity studentEntity, StudentAdmissionJourneyUpdateRequestDto studentUpdateRequestDto) {
	        studentUpdateRequestDto.getStdProfileId()
	            .ifPresent(studentEntity::setStdProfileId);
	        studentUpdateRequestDto.getAdmissionJourney()
	            .ifPresent(studentEntity::setAdmissionJourney);
	        studentUpdateRequestDto.getCreatedBy().ifPresent(studentEntity::setCreatedBy);
	        studentUpdateRequestDto.getModifiedBy().ifPresent(studentEntity::setModifiedBy);
	        studentUpdateRequestDto.getCreatedTimestamp().ifPresent(studentEntity::setCreatedTimestamp);
	        studentUpdateRequestDto.getModifiedTimestamp().ifPresent(studentEntity::setModifiedTimestamp);
	
	        return studentEntity;
	    }

}
