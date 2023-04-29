package com.spdx.hms.mapper;

import java.time.Instant;

import org.mapstruct.AfterMapping;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import com.spdx.hms.entity.CourseEntity;
import com.spdx.hms.entity.StudentCounsellorEntity;
import com.spdx.hms.v1.service.dto.request.CourseGetRequestDto;
import com.spdx.hms.v1.service.dto.request.CourseSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.CourseUpdateRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentCounsellorGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentCounsellorSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentCounsellorUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.CourseResponseDto;
import com.spdx.hms.v1.service.dto.response.StudentCounsellorResponseDto;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.WARN,
collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
implementationName = "IStudentCounsellorDomainMapperImpl")
public interface IStudentCounsellorDomainMapper  extends IOptionalMapper {
	StudentCounsellorEntity map(StudentCounsellorSaveRequestDto requestDto);
	 @AfterMapping
	  default void mapAuditFields(@MappingTarget StudentCounsellorEntity studentCounsellorEntity) {
		 studentCounsellorEntity.setCreatedTimestamp(Instant.now().toEpochMilli());
		 studentCounsellorEntity.setModifiedTimestamp(Instant.now().toEpochMilli());
	  }
	 default StudentCounsellorEntity map(StudentCounsellorEntity studentCounsellorEntity, StudentCounsellorUpdateRequestDto request){
		    request.getCounsellorName()
		            .ifPresent(studentCounsellorEntity::setCounsellorName);
		    request.getStudentName()
		             .ifPresent(studentCounsellorEntity::setStudentName);
		    request.getCounsellorEmailId()
		             .ifPresent(studentCounsellorEntity::setCounsellorEmailId);
		    request.getActive()
		             .ifPresent(studentCounsellorEntity::setActive);
		    request.getAppointmentCreatedBy()
		             .ifPresent(studentCounsellorEntity::setAppointmentCreatedBy);
		    request.getCounsellorId()
		              .ifPresent(studentCounsellorEntity::setCounsellorId);
		               
		    request.getCounsellorName()
		             .ifPresent(studentCounsellorEntity::setCounsellorName);
		    request.getCreatedBy()
		              .ifPresent(studentCounsellorEntity::setCreatedBy);
		    request.getCreatedTime()
		              .ifPresent(studentCounsellorEntity::setCreatedTime);
		    request.getCreatedTimestamp()
		             .ifPresent(studentCounsellorEntity::setCreatedTimestamp);
		    request.getModifiedBy()
		              .ifPresent(studentCounsellorEntity::setModifiedBy);
		    request.getModifiedTimestamp()
		                 .ifPresent(studentCounsellorEntity::setModifiedTimestamp);
		    request.getStdCounsellorRequestId()
		                  .ifPresent(studentCounsellorEntity::setStdCounsellorRequestId);
		    request.getStudentEmailId()
		                   .ifPresent(studentCounsellorEntity::setStudentEmailId);
		    request.getStudentId()
		                    .ifPresent(studentCounsellorEntity::setStudentId);
		    request.getStudentMobileNumber()
		                       .ifPresent(studentCounsellorEntity::setStudentMobileNumber);
		    request.getStudentName()
		                      .ifPresent(studentCounsellorEntity::setStudentName);
		    request.getActive()
		               .ifPresent(studentCounsellorEntity::setActive);
		    		   
		    return studentCounsellorEntity;
		  }
	 
	 StudentCounsellorEntity map(StudentCounsellorUpdateRequestDto studentCounsellorUpdateRequestDto);

	 StudentCounsellorResponseDto map(StudentCounsellorEntity requestDto);

	 StudentCounsellorResponseDto map(StudentCounsellorGetRequestDto requestDto);


}
