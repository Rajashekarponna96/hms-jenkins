package com.spdx.hms.mapper;

import com.spdx.hms.entity.StudentEntity;
import com.spdx.hms.v1.service.dto.request.StudentSaveRequestDto;
import org.mapstruct.*;

import com.spdx.hms.entity.CollegeCourseEntity;
import com.spdx.hms.v1.service.dto.request.CollegeCourseRequestDto;
import com.spdx.hms.v1.service.dto.response.CollegeCourseResponseDto;

import java.time.Instant;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.WARN,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        implementationName = "ICollegeCourseDomainMapperImpl")
public interface ICollegeCourseDomainMapper extends IOptionalMapper {
    CollegeCourseEntity map(CollegeCourseRequestDto requestDto);

    @AfterMapping
    default void mapAuditFields(@MappingTarget CollegeCourseEntity studentEntity, CollegeCourseRequestDto requestDto) {
        studentEntity.setCreatedTimestamp(Instant.now().toEpochMilli());
        studentEntity.setModifiedTimestamp(Instant.now().toEpochMilli());
        requestDto.getCreatedBy().ifPresent(studentEntity::setModifiedBy);
    }

    CollegeCourseResponseDto map(CollegeCourseEntity requestDto);

    default CollegeCourseEntity map(CollegeCourseEntity studentEntity, CollegeCourseRequestDto studentUpdateRequestDto) {
       
       /* studentUpdateRequestDto.getModifiedBy()
                .ifPresent(studentEntity::setModifiedBy);
        studentEntity.setModifiedTimestamp(Instant.now().toEpochMilli());
        studentUpdateRequestDto.isActive()
                .ifPresent(studentEntity::setActive);*/
        return studentEntity;
    }
}
