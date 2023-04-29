package com.spdx.hms.mapper;

import java.time.Instant;

import org.mapstruct.AfterMapping;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import com.spdx.hms.entity.StudentCounsellorFollowupEntity;
import com.spdx.hms.v1.service.dto.request.StudentCounsellorFollowupGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentCounsellorFollowupSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentCounsellorFollowupUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.StudentCounsellorFollowupResponseDto;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.WARN,
collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
implementationName = "IStudentCounsellorFollowupDomainMapperImpl")
public interface IStudentCounsellorFollowupDomainMapper extends IOptionalMapper{
	
	StudentCounsellorFollowupEntity map(StudentCounsellorFollowupSaveRequestDto requestDto);
	@AfterMapping
	  default void mapAuditFields(@MappingTarget StudentCounsellorFollowupEntity studentCounsellorFollowupEntity) {
		studentCounsellorFollowupEntity.setCreatedTimestamp(Instant.now().toEpochMilli());
		studentCounsellorFollowupEntity.setModifiedTimestamp(Instant.now().toEpochMilli());
		
	}
	default StudentCounsellorFollowupEntity map(StudentCounsellorFollowupEntity studentCounsellorFollowupEntity, StudentCounsellorFollowupUpdateRequestDto studentCounsellorFollowupUpdateRequestDto) {
		studentCounsellorFollowupUpdateRequestDto.getStdCounsellorRequestId()
		                                              .ifPresent(studentCounsellorFollowupEntity::setStdCounsellorRequestId);
		studentCounsellorFollowupUpdateRequestDto.getRemarks()
		                                               .ifPresent(studentCounsellorFollowupEntity::setRemarks);
		studentCounsellorFollowupUpdateRequestDto.getCreatedTime()
		                                               .ifPresent(studentCounsellorFollowupEntity::setCreatedTime);
		studentCounsellorFollowupUpdateRequestDto.getActive()
		                                                .ifPresent(studentCounsellorFollowupEntity::setActive);
		studentCounsellorFollowupUpdateRequestDto.getCreatedBy()
		                                                .ifPresent(studentCounsellorFollowupEntity::setCreatedBy);
		studentCounsellorFollowupUpdateRequestDto.getModifiedBy()
		                                                 .ifPresent(studentCounsellorFollowupEntity::setModifiedBy);
		studentCounsellorFollowupUpdateRequestDto.getCreatedTimestamp()
		                                                 .ifPresent(studentCounsellorFollowupEntity::setCreatedTimestamp);
		studentCounsellorFollowupUpdateRequestDto.getModifiedTimestamp()
		                                                  .ifPresent(studentCounsellorFollowupEntity::setModifiedTimestamp);
		return studentCounsellorFollowupEntity;
		                                                  
		
	}
	StudentCounsellorFollowupEntity map(StudentCounsellorFollowupUpdateRequestDto  studentCounsellorFollowupUpdateRequestDto);
	StudentCounsellorFollowupResponseDto map(StudentCounsellorFollowupEntity requestDto);
	StudentCounsellorFollowupResponseDto map(StudentCounsellorFollowupGetRequestDto requestDto);

}














