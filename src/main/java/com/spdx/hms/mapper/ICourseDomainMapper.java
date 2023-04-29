package com.spdx.hms.mapper;

import com.spdx.hms.entity.CourseEntity;
import com.spdx.hms.v1.service.dto.request.*;
import com.spdx.hms.v1.service.dto.response.CourseResponseDto;
import org.mapstruct.*;

import java.time.Instant;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.WARN,
    collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
    implementationName = "ICourseDomainMapperImpl")
public interface ICourseDomainMapper extends IOptionalMapper {
  CourseEntity map(CourseSaveRequestDto requestDto);
  @AfterMapping
  default void mapAuditFields(@MappingTarget CourseEntity courseEntity) {
    courseEntity.setCreatedTimestamp(Instant.now().toEpochMilli());
    courseEntity.setModifiedTimestamp(Instant.now().toEpochMilli());
  }

  default CourseEntity map(CourseEntity courseEntity, CourseUpdateRequestDto request){
    request.getCourseType()
            .ifPresent(courseEntity::setCourseType);
    request.getCategory()
            .ifPresent(courseEntity::setCategory);
    request.getDuration()
            .ifPresent(courseEntity::setDuration);
    courseEntity.setModifiedTimestamp(Instant.now().toEpochMilli());
    return courseEntity;
  }

  CourseEntity map(CourseUpdateRequestDto courseUpdateRequestDto);

  CourseResponseDto map(CourseEntity requestDto);

  CourseResponseDto map(CourseGetRequestDto requestDto);
}

