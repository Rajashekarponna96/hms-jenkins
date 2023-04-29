package com.spdx.hms.mapper;

import com.spdx.hms.entity.StudentEntity;
import com.spdx.hms.entity.StudentQualificationEntity;
import com.spdx.hms.v1.service.dto.request.StudentQualificationSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentQualificationUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.StudentQualificationResponseDto;
import com.spdx.hms.v1.service.dto.response.StudentResponseDto;
import org.mapstruct.*;

import java.time.Instant;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.WARN,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        implementationName = "IStudentQualificationDomainMapperImpl")
public interface IStudentQualificationDomainMapper extends IOptionalMapper {
    StudentQualificationEntity map(StudentQualificationSaveRequestDto requestDto);

    @AfterMapping
    default void mapAuditFields(@MappingTarget StudentQualificationEntity qualificationEntity) {
        qualificationEntity.setCreatedTimestamp(Instant.now().toEpochMilli());
        qualificationEntity.setModifiedTimestamp(Instant.now().toEpochMilli());
    }

    StudentQualificationResponseDto map(StudentQualificationEntity requestDto);

    @Mapping(target = "studentExperiences", ignore = true)
    StudentResponseDto map(StudentEntity responseDto);

    default StudentQualificationEntity map(
            StudentQualificationEntity qualificationEntity,
            StudentQualificationUpdateRequestDto qualificationUpdateRequestDto) {
        qualificationUpdateRequestDto.getQualification()
                .ifPresent(qualificationEntity::setQualification);
        qualificationUpdateRequestDto.getUniversity()
                .ifPresent(qualificationEntity::setUniversity);
      qualificationUpdateRequestDto.getEndDate()
              .ifPresent(qualificationEntity::setEndDate);
      qualificationUpdateRequestDto.getGrade()
              .ifPresent(qualificationEntity::setGrade);
      qualificationUpdateRequestDto.getStartDate()
              .ifPresent(qualificationEntity::setStartDate);
      qualificationUpdateRequestDto.getPercentage()
              .ifPresent(qualificationEntity::setPercentage);
      qualificationUpdateRequestDto.getYearOfPassing()
              .ifPresent(qualificationEntity::setYearOfPassing);
        return qualificationEntity;
    }
}

