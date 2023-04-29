package com.spdx.hms.mapper;

import java.time.Instant;

import org.mapstruct.AfterMapping;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import com.spdx.hms.entity.StudentEntity;
import com.spdx.hms.entity.StudentReportingInfoEntity;
import com.spdx.hms.v1.service.dto.request.StudentReportingInfoGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentReportingInfoSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentReportingInfoUpdateRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.StudentReportingInfoResponseDto;
import com.spdx.hms.v1.service.dto.response.StudentResponseDto;


@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.WARN,
collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
implementationName = "IStudentReportingInfoDomainMapperImpl")

public interface IStudentReportingInfoDomainMapper extends IOptionalMapper {
	
	StudentReportingInfoEntity map(StudentReportingInfoSaveRequestDto requestDto );	
	
	 @AfterMapping
	  default void mapAuditFields(@MappingTarget StudentReportingInfoEntity studentReportingInfoEntity) {
	   studentReportingInfoEntity.setCreatedTimestamp(Instant.now().toEpochMilli());
	    studentReportingInfoEntity.setModifiedTimestamp(Instant.now().toEpochMilli());
	    
	 }
	 	 
	  StudentReportingInfoResponseDto map(StudentReportingInfoEntity requestDto);

	    default StudentReportingInfoEntity map(StudentReportingInfoEntity studentEntity, StudentReportingInfoUpdateRequestDto studentUpdateRequestDto) {
	        studentUpdateRequestDto.getStdAdmissionId()
	            .ifPresent(studentEntity::setStdAdmissionId);
	        studentUpdateRequestDto.getContactPerson()
	            .ifPresent(studentEntity::setContactPerson);
	        studentUpdateRequestDto.getContactPersonNumber()
	            .ifPresent(studentEntity::setContactPersonNumber);
	        studentUpdateRequestDto.getDateTime()
	        .ifPresent(studentEntity::setDateTime);
	        studentUpdateRequestDto.getDocumentsRequired()
	        .ifPresent(studentEntity::setDocumentsRequired);
	        studentUpdateRequestDto.getComments()
	        .ifPresent(studentEntity::setComments);
	        studentUpdateRequestDto.getStatus()
	           .ifPresent(studentEntity::setStatus);
	        studentUpdateRequestDto.getCreatedBy().ifPresent(studentEntity::setCreatedBy);
	        studentUpdateRequestDto.getModifiedBy().ifPresent(studentEntity::setModifiedBy);
	        studentUpdateRequestDto.getCreatedTimestamp().ifPresent(studentEntity::setCreatedTimestamp);
	        studentUpdateRequestDto.getModifiedTimestamp().ifPresent(studentEntity::setModifiedTimestamp);
	
	        return studentEntity;
	    }
	 
	/* default StudentReportingInfoEntity map(StudentReportingInfoEntity requestDto, StudentReportingInfoUpdateRequestDto request){
		    return requestDto;
		  }

		  StudentReportingInfoEntity map(StudentReportingInfoUpdateRequestDto studentReportingInfoUpdateRequestDto);

		  StudentReportingInfoResponseDto map(StudentReportingInfoEntity requestDto);

		  StudentReportingInfoResponseDto map(StudentReportingInfoGetRequestDto requestDto);
		  
		*/
}

		
	

