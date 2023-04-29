package com.spdx.hms.mapper;

import com.spdx.hms.entity.StudentEntity;
import com.spdx.hms.entity.StudentExperienceEntity;
import com.spdx.hms.v1.service.dto.request.StudentExperienceSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentExperienceUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.StudentExperienceResponseDto;
import com.spdx.hms.v1.service.dto.response.StudentResponseDto;
import org.mapstruct.*;

import java.time.Instant;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.WARN,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        implementationName = "IStudentExperienceDomainMapperImpl")
public interface IStudentExperienceDomainMapper extends IOptionalMapper {
    StudentExperienceEntity map(StudentExperienceSaveRequestDto requestDto);

    @AfterMapping
    default void mapAuditFields(
            @MappingTarget StudentExperienceEntity studentEntity,
            StudentExperienceSaveRequestDto requestDto) {
        studentEntity.setCreatedTimestamp(Instant.now().toEpochMilli());
        studentEntity.setModifiedTimestamp(Instant.now().toEpochMilli());
    }

    StudentExperienceResponseDto map(StudentExperienceEntity requestDto);

    default StudentExperienceEntity map(
            StudentExperienceEntity studentEntity,
            StudentExperienceUpdateRequestDto studentUpdateRequestDto) {
        studentUpdateRequestDto.getCompanyName()
                .ifPresent(studentEntity::setCompanyName);
        studentUpdateRequestDto.getTitle()
                .ifPresent(studentEntity::setTitle);
        studentUpdateRequestDto.getDepartment()
                .ifPresent(studentEntity::setDepartment);
        studentUpdateRequestDto.getEmploymentType()
                .ifPresent(studentEntity::setEmploymentType);
        studentUpdateRequestDto.getStartDate()
                .ifPresent(studentEntity::setStartDate);
        studentUpdateRequestDto.getEndDate()
                .ifPresent(studentEntity::setEndDate);
        studentUpdateRequestDto.getCreatedBy()
                .ifPresent(studentEntity::setCreatedBy);
        studentUpdateRequestDto.getModifiedBy()
                .ifPresent(studentEntity::setModifiedBy);
        return studentEntity;
    }
    @Mapping(target = "studentExperiences", ignore = true)
    StudentResponseDto map(StudentEntity responseDto);
}
