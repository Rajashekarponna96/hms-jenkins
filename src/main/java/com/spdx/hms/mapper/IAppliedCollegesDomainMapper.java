package com.spdx.hms.mapper;

import com.spdx.hms.entity.AppliedCollegesEntity;
import com.spdx.hms.v1.service.dto.request.AppliedCollegesSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.AppliedCollegesUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.AppliedCollegesPaginationResponseDto;
import com.spdx.hms.v1.service.dto.response.AppliedCollegesResponseDto;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.time.Instant;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.WARN,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        implementationName = "IAppliedCollegesDomainMapperImpl")
public interface IAppliedCollegesDomainMapper extends IOptionalMapper {
    AppliedCollegesEntity map(AppliedCollegesSaveRequestDto requestDto);
    @AfterMapping
    default void mapAuditFields(@MappingTarget AppliedCollegesEntity AppliedCollegesEntity, AppliedCollegesSaveRequestDto requestDto) {
        AppliedCollegesEntity.setCreatedTimestamp(Instant.now().toEpochMilli());
        AppliedCollegesEntity.setModifiedTimestamp(Instant.now().toEpochMilli());
        //requestDto.getCreatedBy().ifPresent(AppliedCollegesEntity::setModifiedBy);
    }

    AppliedCollegesResponseDto map(AppliedCollegesEntity requestDto);

    default AppliedCollegesEntity map(AppliedCollegesEntity AppliedCollegesEntity, AppliedCollegesUpdateRequestDto AppliedCollegesUpdateRequestDto) {


        AppliedCollegesUpdateRequestDto.getModifiedBy()
                .ifPresent(AppliedCollegesEntity::setModifiedBy);
        AppliedCollegesUpdateRequestDto.getAppId().ifPresent(AppliedCollegesEntity::setAppId);
        AppliedCollegesUpdateRequestDto.getCollegeId().ifPresent(AppliedCollegesEntity::setCollegeId);
        AppliedCollegesUpdateRequestDto.getCollegeCode().ifPresent(AppliedCollegesEntity::setCollegeCode);
        AppliedCollegesUpdateRequestDto.getCollegeCourseId().ifPresent(AppliedCollegesEntity::setCollegeCourseId);
        AppliedCollegesUpdateRequestDto.getStudentId().ifPresent(AppliedCollegesEntity::setStudentId);
        AppliedCollegesUpdateRequestDto.getCollegeCourseBranchName().ifPresent(AppliedCollegesEntity::setCollegeCourseBranchName);
        AppliedCollegesUpdateRequestDto.getStudentMobileNumber().ifPresent(AppliedCollegesEntity::setStudentMobileNumber);
        AppliedCollegesUpdateRequestDto.getStudentEmailId().ifPresent(AppliedCollegesEntity::setStudentEmailId);
        AppliedCollegesUpdateRequestDto.getStudentName().ifPresent(AppliedCollegesEntity::setStudentName);
        AppliedCollegesUpdateRequestDto.getActive().ifPresent(AppliedCollegesEntity::setActive);
        AppliedCollegesUpdateRequestDto.getComment().ifPresent(AppliedCollegesEntity::setComment);
        AppliedCollegesUpdateRequestDto.getStatus().ifPresent(AppliedCollegesEntity::setStatus);
        AppliedCollegesUpdateRequestDto.getQualification().ifPresent(AppliedCollegesEntity::setQualification);
        AppliedCollegesUpdateRequestDto.getContactId().ifPresent(AppliedCollegesEntity::setContactId);
        AppliedCollegesUpdateRequestDto.getCollegeCourse().ifPresent(AppliedCollegesEntity::setCollegeCourse);
        AppliedCollegesUpdateRequestDto.getAppDate().ifPresent(AppliedCollegesEntity::setAppDate);
        
        AppliedCollegesEntity.setModifiedTimestamp(Instant.now().toEpochMilli());
        return AppliedCollegesEntity;
    }
    default AppliedCollegesPaginationResponseDto mapPaginationResponse(Page<AppliedCollegesEntity> p) {
        AppliedCollegesPaginationResponseDto AppliedCollegesPaginationResponseDto = new AppliedCollegesPaginationResponseDto();
        AppliedCollegesPaginationResponseDto.setCurrentPage(p.getNumber());
        AppliedCollegesPaginationResponseDto.setTotalItems(p.getTotalElements());
        AppliedCollegesPaginationResponseDto.setTotalPages(p.getTotalPages());
        Collection<AppliedCollegesResponseDto> AppliedCollegesResponses = Optional.of(p)
                .map(Page::getContent)
                .map(Collection::stream)
                .orElseGet(Stream::empty)
                .map(this::map)
                .collect(Collectors.toList());
        AppliedCollegesPaginationResponseDto.setAppliedColleges(AppliedCollegesResponses);
        return AppliedCollegesPaginationResponseDto;
    }

}
